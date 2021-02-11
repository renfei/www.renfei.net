package net.renfei.repository.entity;

public class LetsEncryptDOWithBLOBs extends LetsEncryptDO {
    private String cert;

    private String certKey;

    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert == null ? null : cert.trim();
    }

    public String getCertKey() {
        return certKey;
    }

    public void setCertKey(String certKey) {
        this.certKey = certKey == null ? null : certKey.trim();
    }
}