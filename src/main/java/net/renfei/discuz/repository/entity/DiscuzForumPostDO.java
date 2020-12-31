package net.renfei.discuz.repository.entity;

public class DiscuzForumPostDO extends DiscuzForumPostDOKey {
    private Integer pid;

    private Integer fid;

    private Boolean first;

    private String author;

    private Integer authorid;

    private String subject;

    private Integer dateline;

    private String useip;

    private Short port;

    private Boolean invisible;

    private Boolean anonymous;

    private Boolean usesig;

    private Boolean htmlon;

    private Boolean bbcodeoff;

    private Boolean smileyoff;

    private Boolean parseurloff;

    private Boolean attachment;

    private Short rate;

    private Byte ratetimes;

    private Integer status;

    private String tags;

    private Boolean comment;

    private Integer replycredit;

    private String message;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public Integer getDateline() {
        return dateline;
    }

    public void setDateline(Integer dateline) {
        this.dateline = dateline;
    }

    public String getUseip() {
        return useip;
    }

    public void setUseip(String useip) {
        this.useip = useip == null ? null : useip.trim();
    }

    public Short getPort() {
        return port;
    }

    public void setPort(Short port) {
        this.port = port;
    }

    public Boolean getInvisible() {
        return invisible;
    }

    public void setInvisible(Boolean invisible) {
        this.invisible = invisible;
    }

    public Boolean getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }

    public Boolean getUsesig() {
        return usesig;
    }

    public void setUsesig(Boolean usesig) {
        this.usesig = usesig;
    }

    public Boolean getHtmlon() {
        return htmlon;
    }

    public void setHtmlon(Boolean htmlon) {
        this.htmlon = htmlon;
    }

    public Boolean getBbcodeoff() {
        return bbcodeoff;
    }

    public void setBbcodeoff(Boolean bbcodeoff) {
        this.bbcodeoff = bbcodeoff;
    }

    public Boolean getSmileyoff() {
        return smileyoff;
    }

    public void setSmileyoff(Boolean smileyoff) {
        this.smileyoff = smileyoff;
    }

    public Boolean getParseurloff() {
        return parseurloff;
    }

    public void setParseurloff(Boolean parseurloff) {
        this.parseurloff = parseurloff;
    }

    public Boolean getAttachment() {
        return attachment;
    }

    public void setAttachment(Boolean attachment) {
        this.attachment = attachment;
    }

    public Short getRate() {
        return rate;
    }

    public void setRate(Short rate) {
        this.rate = rate;
    }

    public Byte getRatetimes() {
        return ratetimes;
    }

    public void setRatetimes(Byte ratetimes) {
        this.ratetimes = ratetimes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public Boolean getComment() {
        return comment;
    }

    public void setComment(Boolean comment) {
        this.comment = comment;
    }

    public Integer getReplycredit() {
        return replycredit;
    }

    public void setReplycredit(Integer replycredit) {
        this.replycredit = replycredit;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}