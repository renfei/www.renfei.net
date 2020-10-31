package net.renfei.entity;

import lombok.Data;

import java.util.List;

/**
 * <p>Title: HeaderVO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Data
public class HeaderVO {
    private String siteName;
    private List<LinkVO> menus;
    private String notice;
}
