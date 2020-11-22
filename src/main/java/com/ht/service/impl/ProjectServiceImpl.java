package com.ht.service.impl;


import com.ht.dao.IProjectDao;
import com.ht.model.EnterpriseProjectApproval;
import com.ht.model.EnterpriseProjectBasic;
import com.ht.model.EnterpriseProjectFee;
import com.ht.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ProjectServiceImpl implements IProjectService {

    @Autowired
    IProjectDao projectDao;

    @Override
    public List<EnterpriseProjectFee> getProjectFee(String projectId, String monthId, String feeType, int offset, int limit) {

        offset = offset < 1 ? limit : (offset - 1) * limit;

        return projectDao.getProjectFee(projectId, monthId, feeType, offset, limit);
    }

    @Override
    public int getCount(String projectId, String monthId, String feeType) {
        return projectDao.selctCount(projectId,monthId,feeType);
    }

    @Override
    public int modifyProjectApproval(EnterpriseProjectApproval enterpriseProjectApproval) {
        enterpriseProjectApproval.setUpdateTime(new Date());
        return projectDao.modifyProjectApproval(enterpriseProjectApproval);
    }

    @Override
    public EnterpriseProjectApproval queryProjectApproval(String projectId) {
        return projectDao.queryProjectApproval(projectId);
    }

    @Override
    public int modifyProjectBasic(EnterpriseProjectBasic enterpriseProjectBasic) {
        enterpriseProjectBasic.setUpdateTime(new Date());
        return projectDao.modifyProjectBasic(enterpriseProjectBasic);
    }

    @Override
    public EnterpriseProjectBasic queryProjectBasic(String projectId) {
        return projectDao.queryProjectBasic(projectId);
    }


}
