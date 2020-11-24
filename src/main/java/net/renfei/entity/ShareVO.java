package net.renfei.entity;

public class ShareVO {
    private String title;
    private String describes;
    private String url;
    private String pics;
    private String views;
    private String fb_appid = "205704373959112";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribes() {
        return describes == null ? title : describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFb_appid() {
        return fb_appid;
    }

    public String getPics() {
        if(pics==null){
            return "https://cdn.renfei.net/Logo/ogimage.png";
        }
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public void setFb_appid(String fb_appid) {
        this.fb_appid = fb_appid;
    }
}
