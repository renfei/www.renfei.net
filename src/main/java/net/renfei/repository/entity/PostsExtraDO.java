package net.renfei.repository.entity;

public class PostsExtraDO {
    private Long id;

    private Long postId;

    private String extraType;

    private String extraValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getExtraType() {
        return extraType;
    }

    public void setExtraType(String extraType) {
        this.extraType = extraType == null ? null : extraType.trim();
    }

    public String getExtraValue() {
        return extraValue;
    }

    public void setExtraValue(String extraValue) {
        this.extraValue = extraValue == null ? null : extraValue.trim();
    }
}