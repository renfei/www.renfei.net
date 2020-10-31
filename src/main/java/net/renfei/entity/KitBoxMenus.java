package net.renfei.entity;

import lombok.Data;

import java.util.List;

/**
 * <p>Title: KitboxMenus</p>
 * <p>Description: 工具箱菜单</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Data
public class KitBoxMenus {
    private String title;
    private String elementId;
    private Boolean isOpen = false;
    private List<LinkVO> links;
}
