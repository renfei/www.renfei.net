package net.renfei.repository.entity;

public class WechatKeywordDO {
    private Long id;

    private String keyword;

    private String keyTypw;

    private String keyValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getKeyTypw() {
        return keyTypw;
    }

    public void setKeyTypw(String keyTypw) {
        this.keyTypw = keyTypw == null ? null : keyTypw.trim();
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue == null ? null : keyValue.trim();
    }
}