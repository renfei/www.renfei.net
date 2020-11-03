package net.renfei.repository.entity;

import java.util.Date;

public class SystemLog {
    private Long id;

    private String logLevel;

    private String logModel;

    private String logType;

    private String userUuid;

    private String userName;

    private String requMethod;

    private String requUri;

    private String requIp;

    private String requAgent;

    private Date logTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel == null ? null : logLevel.trim();
    }

    public String getLogModel() {
        return logModel;
    }

    public void setLogModel(String logModel) {
        this.logModel = logModel == null ? null : logModel.trim();
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType == null ? null : logType.trim();
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getRequMethod() {
        return requMethod;
    }

    public void setRequMethod(String requMethod) {
        this.requMethod = requMethod == null ? null : requMethod.trim();
    }

    public String getRequUri() {
        return requUri;
    }

    public void setRequUri(String requUri) {
        this.requUri = requUri == null ? null : requUri.trim();
    }

    public String getRequIp() {
        return requIp;
    }

    public void setRequIp(String requIp) {
        this.requIp = requIp == null ? null : requIp.trim();
    }

    public String getRequAgent() {
        return requAgent;
    }

    public void setRequAgent(String requAgent) {
        this.requAgent = requAgent == null ? null : requAgent.trim();
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
}