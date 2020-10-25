package com.ht.model;

import java.util.Date;

public class EnterpriseUserRela {
    private Integer enterpriseId;

    private String userId;

    private String relaType;

    private String relaStatus;

    private Integer relaUserId;

    private Date createTime;

    private Date update;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRelaType() {
        return relaType;
    }

    public void setRelaType(String relaType) {
        this.relaType = relaType == null ? null : relaType.trim();
    }

    public String getRelaStatus() {
        return relaStatus;
    }

    public void setRelaStatus(String relaStatus) {
        this.relaStatus = relaStatus == null ? null : relaStatus.trim();
    }

    public Integer getRelaUserId() {
        return relaUserId;
    }

    public void setRelaUserId(Integer relaUserId) {
        this.relaUserId = relaUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }
}