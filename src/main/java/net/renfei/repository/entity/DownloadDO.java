package net.renfei.repository.entity;

import java.util.Date;

public class DownloadDO {
    private Long id;

    private String disableArea;

    private String name;

    private String icon;

    private String size;

    private Date created;

    private String hash;

    private String fileName;

    private String bucket;

    private String filePath;

    private String baiduYunPanUrl;

    private String baiduYunPanCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisableArea() {
        return disableArea;
    }

    public void setDisableArea(String disableArea) {
        this.disableArea = disableArea == null ? null : disableArea.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash == null ? null : hash.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket == null ? null : bucket.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getBaiduYunPanUrl() {
        return baiduYunPanUrl;
    }

    public void setBaiduYunPanUrl(String baiduYunPanUrl) {
        this.baiduYunPanUrl = baiduYunPanUrl == null ? null : baiduYunPanUrl.trim();
    }

    public String getBaiduYunPanCode() {
        return baiduYunPanCode;
    }

    public void setBaiduYunPanCode(String baiduYunPanCode) {
        this.baiduYunPanCode = baiduYunPanCode == null ? null : baiduYunPanCode.trim();
    }
}