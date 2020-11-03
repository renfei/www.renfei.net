package net.renfei.repository.entity;

public class SystemLogWithBLOBs extends SystemLog {
    private String logDesc;

    private String requParam;

    private String respParam;

    public String getLogDesc() {
        return logDesc;
    }

    public void setLogDesc(String logDesc) {
        this.logDesc = logDesc == null ? null : logDesc.trim();
    }

    public String getRequParam() {
        return requParam;
    }

    public void setRequParam(String requParam) {
        this.requParam = requParam == null ? null : requParam.trim();
    }

    public String getRespParam() {
        return respParam;
    }

    public void setRespParam(String respParam) {
        this.respParam = respParam == null ? null : respParam.trim();
    }
}