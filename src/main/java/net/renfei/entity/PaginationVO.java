package net.renfei.entity;

import lombok.Data;

@Data
public class PaginationVO {
    private String link;
    private String page;
    private boolean active;
}
