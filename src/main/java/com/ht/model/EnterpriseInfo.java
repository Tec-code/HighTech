package com.ht.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class EnterpriseInfo {
    private Integer enterpriseId;

    private String enterpriseName;

    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
    private Date update;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
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