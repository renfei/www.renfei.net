package net.renfei.service;

import net.renfei.base.BaseService;
import net.renfei.entity.KitBoxMenus;
import net.renfei.entity.LinkVO;
import net.renfei.sdk.utils.Builder;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: KitBoxService</p>
 * <p>Description: 工具箱服务</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Service
@CacheConfig(cacheNames = "KitBoxService")
public class KitBoxService extends BaseService {
    public static final String NETWORK_TOOL = "networkTool";
    public static final String DEVELOPMENT_TOOL = "developmentTool";
    public static final String ENCRYPTION_TOOL = "encryptionTool";
    public static final String OTHER_TOOL = "otherTool";

    @Cacheable
    public List<KitBoxMenus> getKitBoxMenus(String key) {
        List<KitBoxMenus> kitBoxMenus = getKitBoxMenus();
        for (KitBoxMenus kitBoxMenu : kitBoxMenus
        ) {
            if (kitBoxMenu.getElementId().equals(key)) {
                kitBoxMenu.setIsOpen(true);
                break;
            }
        }
        return kitBoxMenus;
    }

    @Cacheable
    public List<KitBoxMenus> getKitBoxMenus() {
        List<KitBoxMenus> kitBoxMenus = new ArrayList<>();
        kitBoxMenus.add(Builder.of(KitBoxMenus::new)
                .with(KitBoxMenus::setTitle, "网络工具")
                .with(KitBoxMenus::setElementId, NETWORK_TOOL)
                .with(KitBoxMenus::setLinks, new ArrayList<LinkVO>() {
                    {
                        this.add(Builder.of(LinkVO::new)
                                .with(LinkVO::setHref, "/kitbox/ip")
                                .with(LinkVO::setRel, "IP地址信息查询工具，开放服务接口实现IP信息查询")
                                .with(LinkVO::setText, "IP地址信息查询工具")
                                .build());
                        this.add(Builder.of(LinkVO::new)
                                .with(LinkVO::setHref, "/kitbox/digtrace")
                                .with(LinkVO::setRel, "域名解析查询工具，开放服务接口实现dig+trace查询")
                                .with(LinkVO::setText, "域名解析Dig查询工具")
                                .build());
                        this.add(Builder.of(LinkVO::new)
                                .with(LinkVO::setHref, "/kitbox/whois")
                                .with(LinkVO::setRel, "查询域名是否已经被注册，以及注册域名的详细信息")
                                .with(LinkVO::setText, "域名Whois查询工具")
                                .build());
                        this.add(Builder.of(LinkVO::new)
                                .with(LinkVO::setHref, "/kitbox/getmyip")
                                .with(LinkVO::setRel, "公网IP获取工具支持Linux、Windows、API")
                                .with(LinkVO::setText, "公网IP获取工具")
                                .build());
                    }
                })
                .build());
        kitBoxMenus.add(Builder.of(KitBoxMenus::new)
                .with(KitBoxMenus::setTitle, "开发类工具")
                .with(KitBoxMenus::setElementId, DEVELOPMENT_TOOL)
                .with(KitBoxMenus::setLinks, new ArrayList<LinkVO>() {
                    {
                        this.add(Builder.of(LinkVO::new)
                                .with(LinkVO::setHref, "/kitbox/uuid")
                                .with(LinkVO::setRel, "在线批量生成 UUID/GUID 工具，支持大小写连词符")
                                .with(LinkVO::setText, "UUID/GUID在线生成工具")
                                .build());
                        this.add(Builder.of(LinkVO::new)
                                .with(LinkVO::setHref, "/kitbox/freemarkerTest")
                                .with(LinkVO::setRel, "FreeMarker在线测试工具，在线测试Ftl文件")
                                .with(LinkVO::setText, "FreeMarker在线测试工具")
                                .build());
                        this.add(Builder.of(LinkVO::new)
                                .with(LinkVO::setHref, "/kitbox/ueditor")
                                .with(LinkVO::setRel, "百度团队的UEditor在线文本编辑器演示与体验")
                                .with(LinkVO::setText, "UEditor富文本在线编辑器")
                                .build());
                    }
                })
                .build());
        kitBoxMenus.add(Builder.of(KitBoxMenus::new)
                .with(KitBoxMenus::setTitle, "加解密工具")
                .with(KitBoxMenus::setElementId, ENCRYPTION_TOOL)
                .with(KitBoxMenus::setLinks, new ArrayList<LinkVO>() {
                    {
                        this.add(Builder.of(LinkVO::new)
                                .with(LinkVO::setHref, "/kitbox/md5")
                                .with(LinkVO::setRel, "MD5加密,对字符串进行MD5计算得出MD5加密字符串")
                                .with(LinkVO::setText, "MD5加密工具")
                                .build());
                        this.add(Builder.of(LinkVO::new)
                                .with(LinkVO::setHref, "/kitbox/sha1")
                                .with(LinkVO::setRel, "SHA-1加密,对字符串进行SHA-1计算加密字符串")
                                .with(LinkVO::setText, "SHA-1加密工具")
                                .build());
                        this.add(Builder.of(LinkVO::new)
                                .with(LinkVO::setHref, "/kitbox/sha256")
                                .with(LinkVO::setRel, "SHA-256加密,对字符串进行SHA-256计算加密字符串")
                                .with(LinkVO::setText, "SHA-256加密工具")
                                .build());
                        this.add(Builder.of(LinkVO::new)
                                .with(LinkVO::setHref, "/kitbox/sha512")
                                .with(LinkVO::setRel, "SHA-512加密,对字符串进行SHA-512计算加密字符串")
                                .with(LinkVO::setText, "SHA-512加密工具")
                                .build());
                        this.add(Builder.of(LinkVO::new)
                                .with(LinkVO::setHref, "/kitbox/url16")
                                .with(LinkVO::setRel, "URL网址16进制加密工具,对网址进行16进制编码")
                                .with(LinkVO::setText, "URL16进制加密")
                                .build());
                    }
                })
                .build());
        kitBoxMenus.add(Builder.of(KitBoxMenus::new)
                .with(KitBoxMenus::setTitle, "其他类工具")
                .with(KitBoxMenus::setElementId, OTHER_TOOL)
                .with(KitBoxMenus::setLinks, new ArrayList<LinkVO>() {
                    {
                        this.add(Builder.of(LinkVO::new)
                                .with(LinkVO::setHref, "/kitbox/qrcode")
                                .with(LinkVO::setRel, "免费实用的二维码图片在线生成工具")
                                .with(LinkVO::setText, "二维码生成工具")
                                .build());
                        this.add(Builder.of(LinkVO::new)
                                .with(LinkVO::setHref, "/kitbox/ShortURL")
                                .with(LinkVO::setRel, "免费的短网址在线生成服务，缩短网址")
                                .with(LinkVO::setText, "短网址生成工具")
                                .build());
                    }
                })
                .build());
        return kitBoxMenus;
    }

}
