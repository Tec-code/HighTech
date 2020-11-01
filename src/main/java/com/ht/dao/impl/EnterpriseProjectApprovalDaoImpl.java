package com.ht.dao.impl;

import com.ht.dao.IEnterpriseProjectApprovalDao;
import com.ht.dao.mapper.EnterpriseProjectApprovalMapper;
import com.ht.model.EnterpriseProjectApproval;

public class EnterpriseProjectApprovalDaoImpl extends BaseDao implements IEnterpriseProjectApprovalDao {

    @Override
    public int insertEnterpriseProjectApproval(EnterpriseProjectApproval enterpriseProjectApproval) {
        EnterpriseProjectApprovalMapper mapper = sqlSession.getMapper(EnterpriseProjectApprovalMapper.class);
        return mapper.insert(enterpriseProjectApproval);
    }

    @Override
    public int deleteEnterpriseProjectApproval(String projectId) {
        EnterpriseProjectApprovalMapper mapper = sqlSession.getMapper(EnterpriseProjectApprovalMapper.class);
        return mapper.deleteByPrimaryKey(projectId);
    }
}
