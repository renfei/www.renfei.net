package net.renfei.entity;

import lombok.Data;

import java.util.List;

/**
 * <p>Title: FooterVO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i@renfei.net)
 */
@Data
public class FooterVO {
    private List<String> jss;
    private String jsText;
    private List<LinkVO> smallMenu;
    private List<LinkVO> friendlyLink;
    private List<FooterMenuVO> footerMenus;
    private String version;
    private String buildTime;
}
