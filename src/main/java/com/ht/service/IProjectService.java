package com.ht.service;

import com.ht.model.EnterpriseProjectApproval;
import com.ht.model.EnterpriseProjectBasic;
import com.ht.model.EnterpriseProjectFee;

import java.util.List;

public interface IProjectService {

    List<EnterpriseProjectFee> getProjectFee(String projectId, String monthId, String feeType, int offset, int limit);

    int getCount(String projectId, String monthId, String feeType);

    int modifyProjectApproval(EnterpriseProjectApproval enterpriseProjectApproval);

    EnterpriseProjectApproval queryProjectApproval(String projectId);

    int modifyProjectBasic(EnterpriseProjectBasic enterpriseProjectBasic);

    EnterpriseProjectBasic queryProjectBasic(String projectId);



}
