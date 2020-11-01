package com.ht.dao.mapper;

import com.ht.model.EnterpriseProjectApproval;

public interface EnterpriseProjectApprovalMapper {
    int deleteByPrimaryKey(String projectId);

    int insert(EnterpriseProjectApproval record);

    int insertSelective(EnterpriseProjectApproval record);

    EnterpriseProjectApproval selectByPrimaryKey(String projectId);

    int updateByPrimaryKeySelective(EnterpriseProjectApproval record);

    int updateByPrimaryKey(EnterpriseProjectApproval record);
}