package com.ht.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class EnterpriseProjectApproval {
    private String projectId;

    private Integer enterpriseId;

    private String applyUserName;

    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
    private Date applyTime;

    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
    private Date finishTime;

    private Integer finGoal;

    private String researchInfo;

    private String expectTarget;

    private Integer planFileId;

    private Integer resolutionFileId;

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

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName == null ? null : applyUserName.trim();
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getFinGoal() {
        return finGoal;
    }

    public void setFinGoal(Integer finGoal) {
        this.finGoal = finGoal;
    }

    public String getResearchInfo() {
        return researchInfo;
    }

    public void setResearchInfo(String researchInfo) {
        this.researchInfo = researchInfo == null ? null : researchInfo.trim();
    }

    public String getExpectTarget() {
        return expectTarget;
    }

    public void setExpectTarget(String expectTarget) {
        this.expectTarget = expectTarget == null ? null : expectTarget.trim();
    }

    public Integer getPlanFileId() {
        return planFileId;
    }

    public void setPlanFileId(Integer planFileId) {
        this.planFileId = planFileId;
    }

    public Integer getResolutionFileId() {
        return resolutionFileId;
    }

    public void setResolutionFileId(Integer resolutionFileId) {
        this.resolutionFileId = resolutionFileId;
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