package net.renfei.entity;

import lombok.Data;

@Data
public class ShortUrlVO {
    public static final String BASE_DOMAIN = "https://rnf.pw/";
    private Long id;
    private String shortUrl;
    private String url;
    private Long views;
}
