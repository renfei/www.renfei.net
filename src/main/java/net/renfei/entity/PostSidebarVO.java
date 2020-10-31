package net.renfei.entity;

import lombok.Data;

import java.util.List;

/**
 * <p>Title: PostSidebarVO</p>
 * <p>Description: 文章侧边栏</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Data
public class PostSidebarVO {
    private List<PostSidebar> postSidebars;

    @Data
    public static class PostSidebar {
        private String title;
        private List<LinkVO> link;
    }
}
