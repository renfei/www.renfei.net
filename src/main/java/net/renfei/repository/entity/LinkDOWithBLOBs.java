package net.renfei.repository.entity;

public class LinkDOWithBLOBs extends LinkDO {
    private String sitename;

    private String sitelink;

    private String inSiteLink;

    private String contactName;

    private String remarks;

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename == null ? null : sitename.trim();
    }

    public String getSitelink() {
        return sitelink;
    }

    public void setSitelink(String sitelink) {
        this.sitelink = sitelink == null ? null : sitelink.trim();
    }

    public String getInSiteLink() {
        return inSiteLink;
    }

    public void setInSiteLink(String inSiteLink) {
        this.inSiteLink = inSiteLink == null ? null : inSiteLink.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}