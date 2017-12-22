package com.blog.model;

import java.util.Date;

public class SafeCode {
    private Integer id;

    private Integer userId;

    private String sceneId;

    private String openId;

    private String sKey;

    private String sValue;

    private String scangenKey;

    private String businessType;

    private Date scanTime;

    private Date expiryTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId == null ? null : sceneId.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getsKey() {
        return sKey;
    }

    public void setsKey(String sKey) {
        this.sKey = sKey == null ? null : sKey.trim();
    }

    public String getsValue() {
        return sValue;
    }

    public void setsValue(String sValue) {
        this.sValue = sValue == null ? null : sValue.trim();
    }

    public String getScangenKey() {
        return scangenKey;
    }

    public void setScangenKey(String scangenKey) {
        this.scangenKey = scangenKey == null ? null : scangenKey.trim();
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    public Date getScanTime() {
        return scanTime;
    }

    public void setScanTime(Date scanTime) {
        this.scanTime = scanTime;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}