package com.ht.dao;

import com.ht.model.EnterpriseProject;
import com.ht.model.EnterpriseProjectApproval;

import java.util.List;

public interface IEnterpriseProjectApprovalDao {

    int insertEnterpriseProjectApproval(EnterpriseProjectApproval enterpriseProjectApproval);

    int deleteEnterpriseProjectApproval(String projectId);

}
