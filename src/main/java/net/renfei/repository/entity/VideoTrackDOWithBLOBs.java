package net.renfei.repository.entity;

public class VideoTrackDOWithBLOBs extends VideoTrackDO {
    private String kind;

    private String label;

    private String srclang;

    private String src;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind == null ? null : kind.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getSrclang() {
        return srclang;
    }

    public void setSrclang(String srclang) {
        this.srclang = srclang == null ? null : srclang.trim();
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src == null ? null : src.trim();
    }
}