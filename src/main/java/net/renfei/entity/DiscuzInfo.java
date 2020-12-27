package net.renfei.entity;

import lombok.Data;

/**
 * <p>Title: DiscuzInfo</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Data
public class DiscuzInfo {
    private String userGroup;
    private Integer postsCount;
    private Short essenceCount;
    private Short oltime;
    private Integer points;
    private Integer money;
    private Integer prestige;
    private Integer contribution;
}
