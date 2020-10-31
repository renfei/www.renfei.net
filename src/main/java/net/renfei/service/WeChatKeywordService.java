package net.renfei.service;

import com.alibaba.fastjson.JSON;
import net.renfei.base.BaseService;
import net.renfei.entity.ListData;
import net.renfei.entity.SearchItem;
import net.renfei.repository.WechatKeywordDOMapper;
import net.renfei.repository.entity.DownloadDO;
import net.renfei.repository.entity.WechatKeywordDO;
import net.renfei.repository.entity.WechatKeywordDOExample;
import net.renfei.sdk.entity.WeChatMsg;
import net.renfei.sdk.entity.WeChatMsgType;
import net.renfei.sdk.entity.WeChatResponseNewsMsg;
import net.renfei.sdk.entity.WeChatResponseTextMsg;
import net.renfei.sdk.utils.StringUtils;
import net.renfei.sdk.utils.WeChatUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WeChatKeywordService extends BaseService {
    private final CacheService cacheService;
    private final SearchService searchService;
    private final DownloadService downloadService;
    private final WechatKeywordDOMapper wechatKeywordDOMapper;

    public WeChatKeywordService(CacheService cacheService,
                                SearchService searchService,
                                DownloadService downloadService,
                                WechatKeywordDOMapper wechatKeywordDOMapper) {
        this.cacheService = cacheService;
        this.searchService = searchService;
        this.downloadService = downloadService;
        this.wechatKeywordDOMapper = wechatKeywordDOMapper;
    }

    public String talk(WeChatMsg requestMsg) {
        String content = "";
        if (requestMsg.getMsgType().equals(WeChatMsgType.TEXT)) {
            content = requestMsg.getContent();
        } else if (requestMsg.getMsgType().equals(WeChatMsgType.VOICE)) {
            content = requestMsg.getRecognition();
        }
        WeChatUtils weChatUtils = new WeChatUtils();
        WeChatResponseTextMsg responseTextMsg = new WeChatResponseTextMsg(requestMsg);
        WechatKeywordDO keyword = getWeChatKeyword(content);
        if (keyword != null) {
            if ("text".equals(keyword.getKeyTypw().toLowerCase())) {
                responseTextMsg.setContent(keyword.getKeyValue());
                return weChatUtils.textMessageToXml(responseTextMsg);
            } else {
                //执行其他预留命令
                return Search(requestMsg);
            }
        } else {
            //极速下载服务
            String result = JiSuDownload(requestMsg);
            if (result != null) {
                return result;
            }
            return Search(requestMsg);
        }
    }

    /**
     * @param weChatMessage
     * @return
     */
    private String Search(WeChatMsg weChatMessage) {
        String content = "";
        if (weChatMessage.getMsgType().equals(WeChatMsgType.TEXT)) {
            content = weChatMessage.getContent();
        } else if (weChatMessage.getMsgType().equals(WeChatMsgType.VOICE)) {
            content = weChatMessage.getRecognition();
        }
        WeChatUtils weChatUtils = new WeChatUtils();
        // 默认回复文本消息
        WeChatResponseTextMsg responseTextMsg = new WeChatResponseTextMsg(weChatMessage);
        //搜索文章
        /**
         * 微信最多回复1条图文消息
         * 见公告：https://mp.weixin.qq.com/cgi-bin/announce?action=getannouncement&announce_id=115383153198yAvN&version=&lang=zh_CN&token=
         * */
        ListData<SearchItem> search = searchService.search(content, "1", "1");
        if (search != null && search.getData() != null && search.getData().size() > 0) {
            //回复图文消息
            WeChatResponseNewsMsg newsMessage = new WeChatResponseNewsMsg(weChatMessage);
            newsMessage.setArticleCount(search.getData().size() + "");
            List<WeChatResponseNewsMsg.ArticlesItem> item = new ArrayList<>();
            for (SearchItem indexDO : search.getData()
            ) {
                WeChatResponseNewsMsg.ArticlesItem articlesItem = new WeChatResponseNewsMsg.ArticlesItem();
                articlesItem.setTitle(indexDO.getTitle());
                String desc = StringUtils.delHtmlTags(content);
                articlesItem.setDescription(sub(desc));
                articlesItem.setPicUrl(indexDO.getImage());
                articlesItem.setUrl(indexDO.getUrl());
                item.add(articlesItem);
            }
            newsMessage.setArticles(item);
            return weChatUtils.newsMessageToXml(newsMessage);
        } else {
            responseTextMsg.setContent("我们已经收到您发送的消息，但在「RENFEI.NET」全站搜索中未能匹配到任何信息，请尝试更换关键字尝试。");
            return weChatUtils.textMessageToXml(responseTextMsg);
        }
    }

    /**
     * 极速下载服务
     *
     * @param weChatMessage
     * @return
     */
    private String JiSuDownload(WeChatMsg weChatMessage) {
        WeChatUtils weChatUtils = new WeChatUtils();
        // 默认回复文本消息
        WeChatResponseTextMsg textMessage = new WeChatResponseTextMsg(weChatMessage);
        String content = "";
        if (weChatMessage.getMsgType().equals(WeChatMsgType.TEXT)) {
            content = weChatMessage.getContent();
        } else if (weChatMessage.getMsgType().equals(WeChatMsgType.VOICE)) {
            content = weChatMessage.getRecognition();
        }
        if (content != null) {
            if (content.startsWith("极速下载") ||
                    content.startsWith("極速下載") ||
                    content.startsWith("急速下載")) {
                String fileid = content
                        .replace("极速下载", "")
                        .replace("極速下載", "")
                        .replace("急速下載", "");
                //去查询下载资源列表，生成随机码，记录到cache中
                DownloadDO downloadDO = downloadService.getDownloadInfoById(fileid);
                if (downloadDO != null) {
                    String key = cacheService.getRandomKey();
                    //过期时间十分钟，10*60*1000=600000
                    cacheService.set(key, JSON.toJSONString(downloadDO), new Date(System.currentTimeMillis() + 600000));
                    textMessage.setContent("您的极速下载授权码是：" + key + "。有效期10分钟，请尽快使用。");
                    return weChatUtils.textMessageToXml(textMessage);
                } else {
                    textMessage.setContent("您请求的极速下载文件编号不存在，请重新查证后重试。");
                    return weChatUtils.textMessageToXml(textMessage);
                }
            }
        }
        return null;
    }

    private String sub(String msg) {
        if (msg != null && msg.length() > 120) {
            return msg.substring(0, 119);
        }
        return msg;
    }

    private WechatKeywordDO getWeChatKeyword(String keyword) {
        WechatKeywordDOExample weChatKeywordDOExample = new WechatKeywordDOExample();
        weChatKeywordDOExample.createCriteria()
                .andKeywordEqualTo(keyword);
        List<WechatKeywordDO> weChatKeywordDOS = wechatKeywordDOMapper.selectByExample(weChatKeywordDOExample);
        if (weChatKeywordDOS != null && weChatKeywordDOS.size() > 0) {
            return weChatKeywordDOS.get(0);
        }
        return null;
    }
}
