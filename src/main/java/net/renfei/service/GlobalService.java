package net.renfei.service;

import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseService;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.*;
import net.renfei.repository.entity.LinkDOWithBLOBs;
import net.renfei.repository.entity.MenuDOWithBLOBs;
import net.renfei.sdk.utils.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>Title: GlobalService</p>
 * <p>Description: 通用服务</p>
 *
 * @author RenFei
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "GlobalService")
public class GlobalService extends BaseService {
    protected final RenFeiConfig renFeiConfig;
    protected final LinkService linkService;
    protected final MenuService menuService;
    protected final SettingService settingService;

    GlobalService(RenFeiConfig renFeiConfig,
                  LinkService linkService,
                  MenuService menuService,
                  SettingService settingService) {
        this.renFeiConfig = renFeiConfig;
        this.linkService = linkService;
        this.menuService = menuService;
        this.settingService = settingService;
    }

    @Cacheable
    public HeadVO getGlobalHead() {
        HeadVO headVO = new HeadVO();
        headVO.setCss(new ArrayList<String>() {
            {
                this.add("https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css");
                this.add("/fontawesome/css/all.min.css");
                this.add("/css/bs4.pop.css");
                this.add("/css/style.css");
            }
        });
        return headVO;
    }

    @Cacheable
    public HeaderVO getGlobalHeader() {
        HeaderVO headerVO = new HeaderVO();
        headerVO.setSiteName(renFeiConfig.getSiteName());
        headerVO.setNotice(getGlobalNotice());
        List<MenuDOWithBLOBs> menuDOWithBLOBs = menuService.getMenus();
        if (!BeanUtils.isEmpty(menuDOWithBLOBs)) {
            List<LinkVO> menus = new ArrayList<>();
            menuDOWithBLOBs.forEach(menu -> {
                LinkVO linkVO = new LinkVO();
                linkVO.setHref(menu.getMenuLink());
                linkVO.setRel("");
                linkVO.setStyle("");
                linkVO.setTarget(menu.getIsNewWin() ? "_blank" : "");
                linkVO.setText(menu.getMenuText());
                List<MenuDOWithBLOBs> subMenu = menuService.getMenus(menu.getId());
                if (subMenu != null) {
                    List<LinkVO> subMenus = new ArrayList<>();
                    subMenu.forEach(sub -> {
                        LinkVO subLinkVO = new LinkVO();
                        subLinkVO.setHref(sub.getMenuLink());
                        subLinkVO.setRel("");
                        subLinkVO.setStyle("");
                        subLinkVO.setTarget(sub.getIsNewWin() ? "_blank" : "");
                        subLinkVO.setText(sub.getMenuText());
                        subMenus.add(subLinkVO);
                    });
                    linkVO.setSubLink(subMenus);
                }
                menus.add(linkVO);
            });
            headerVO.setMenus(menus);
        }
        return headerVO;
    }

    @Cacheable
    public FooterVO getGlobalFooter() {
        FooterVO footerVO = new FooterVO();
        footerVO.setVersion(renFeiConfig.getVersion());
        footerVO.setBuildTime(renFeiConfig.getBuildTime());
        footerVO.setJss(new ArrayList<String>() {
            {
                this.add("https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js");
                this.add("https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js");
                this.add("https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.min.js");
                this.add("https://cdn.jsdelivr.net/npm/clipboard@2.0.6/dist/clipboard.min.js");
                this.add("/js/bs4.pop.js");
                this.add("/js/main.js");
                this.add("https://www.googletagmanager.com/gtag/js?id=" + renFeiConfig.getGoogle().getAnalytics());
                this.add("https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js");
            }
        });
        // 页脚菜单
        List<MenuDOWithBLOBs> footerMenus = menuService.getFooterMenus();
        if (!BeanUtils.isEmpty(footerMenus)) {
            List<FooterMenuVO> footerMenuVOS = new ArrayList<>();
            footerMenus.forEach(menu -> {
                FooterMenuVO footerMenuVO = new FooterMenuVO();
                footerMenuVO.setTitle(menu.getMenuText());
                List<MenuDOWithBLOBs> footerMenu = menuService.getFooterMenus(menu.getId());
                if (!BeanUtils.isEmpty(footerMenu)) {
                    List<LinkVO> links = new ArrayList<>();
                    footerMenu.forEach(menuDOWithBLOBs -> {
                        LinkVO linkVO = new LinkVO();
                        linkVO.setHref(menuDOWithBLOBs.getMenuLink());
                        linkVO.setRel("");
                        linkVO.setStyle("");
                        linkVO.setTarget(menuDOWithBLOBs.getIsNewWin() ? "_blank" : "");
                        linkVO.setText(menuDOWithBLOBs.getMenuText());
                        links.add(linkVO);
                    });
                    footerMenuVO.setLinks(links);
                }
                footerMenuVOS.add(footerMenuVO);
            });
            footerVO.setFooterMenus(footerMenuVOS);
        }
        // friendlyLink
        List<LinkDOWithBLOBs> linkDOWithBLOBs = linkService.getLinks();
        if (!BeanUtils.isEmpty(linkDOWithBLOBs)) {
            List<LinkVO> linkVOS = new ArrayList<>();
            linkDOWithBLOBs.forEach(linkDO -> {
                LinkVO linkVO = new LinkVO();
                linkVO.setHref(linkDO.getSitelink());
                linkVO.setRel("noopener");
                linkVO.setStyle("");
                linkVO.setTarget("_blank");
                linkVO.setText(linkDO.getSitename());
                linkVOS.add(linkVO);
            });
            footerVO.setFriendlyLink(linkVOS);
        }
        // smallMenu
        List<MenuDOWithBLOBs> menuDOWithBLOBs = menuService.getSmallMenu();
        if (!BeanUtils.isEmpty(menuDOWithBLOBs)) {
            List<LinkVO> linkVOS = new ArrayList<>();
            menuDOWithBLOBs.forEach(menu -> {
                LinkVO linkVO = new LinkVO();
                linkVO.setHref(menu.getMenuLink());
                linkVO.setRel("");
                linkVO.setStyle("");
                linkVO.setTarget(menu.getIsNewWin() ? "_blank" : "");
                linkVO.setText(menu.getMenuText());
                linkVOS.add(linkVO);
            });
            footerVO.setSmallMenu(linkVOS);
        }

        // jsText
        String jsText = "var _hmt = _hmt || [];\n" +
                "(function() {\n" +
                "  var hm = document.createElement(\"script\");\n" +
                "  hm.src = \"https://hm.baidu.com/hm.js?" + renFeiConfig.getBaidu().getTongji() + "\";\n" +
                "  var s = document.getElementsByTagName(\"script\")[0]; \n" +
                "  s.parentNode.insertBefore(hm, s);\n" +
                "})();\n";
        jsText += "window.dataLayer = window.dataLayer || [];\n" +
                "    function gtag(){dataLayer.push(arguments);}\n" +
                "    gtag('js', new Date());\n" +
                "    gtag('config', 'UA-141370164-1');\n";
        footerVO.setJsText(jsText);
        return footerVO;
    }

    public String getGlobalNotice() {
        String notice = settingService.getNotice();
        if (!BeanUtils.isEmpty(notice)) {
            return notice;
        } else {
            return null;
        }
    }
}
