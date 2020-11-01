package com.ht.dao;

import com.ht.model.EnterpriseProjectApproval;
import com.ht.model.EnterpriseProjectBasic;
import com.ht.model.EnterpriseProjectFee;

import java.util.List;

public interface IProjectDao {

    List<EnterpriseProjectFee> getProjectFee(String projectId, String monthId, String feeType,int offset, int limit);

    int selctCount(String projectId, String monthId, String feeType);

    int modifyProjectApproval(EnterpriseProjectApproval enterpriseProjectApproval);

    EnterpriseProjectApproval queryProjectApproval(String projectId);

    int modifyProjectBasic(EnterpriseProjectBasic enterpriseProjectBasic);

    EnterpriseProjectBasic queryProjectBasic(String projectId);
}
