package net.renfei.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PageVO {
    private Long id;
    private Long categoryId;
    private Long views;
    private Date releaseTime;
    private String title;
    private String content;
    private String describes;
    private String keyword;
}
