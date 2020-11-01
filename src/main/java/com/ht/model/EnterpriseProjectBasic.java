package com.ht.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class EnterpriseProjectBasic {
    private String projectId;

    private Integer enterpriseId;

    private Integer projectSource;

    private Integer totalIncome;

    private String projectResearchType;

    private Integer techField;

    private Integer totalCostBudget;

    private Integer govCostBudget;

    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
    private Date updateTime;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getProjectSource() {
        return projectSource;
    }

    public void setProjectSource(Integer projectSource) {
        this.projectSource = projectSource;
    }

    public Integer getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Integer totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getProjectResearchType() {
        return projectResearchType;
    }

    public void setProjectResearchType(String projectResearchType) {
        this.projectResearchType = projectResearchType == null ? null : projectResearchType.trim();
    }

    public Integer getTechField() {
        return techField;
    }

    public void setTechField(Integer techField) {
        this.techField = techField;
    }

    public Integer getTotalCostBudget() {
        return totalCostBudget;
    }

    public void setTotalCostBudget(Integer totalCostBudget) {
        this.totalCostBudget = totalCostBudget;
    }

    public Integer getGovCostBudget() {
        return govCostBudget;
    }

    public void setGovCostBudget(Integer govCostBudget) {
        this.govCostBudget = govCostBudget;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}