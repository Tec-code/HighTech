package com.ht.dao.impl;

import com.ht.dao.IProjectDao;
import com.ht.dao.mapper.EnterpriseProjectApprovalMapper;
import com.ht.dao.mapper.EnterpriseProjectBasicMapper;
import com.ht.dao.mapper.EnterpriseProjectFeeMapper;
import com.ht.model.EnterpriseProjectApproval;
import com.ht.model.EnterpriseProjectBasic;
import com.ht.model.EnterpriseProjectFee;
import com.ht.service.IProjectService;
import org.apache.ibatis.session.SqlSession;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ProjectDaoImpl extends BaseDao implements IProjectDao {
    @Override
    public List<EnterpriseProjectFee> getProjectFee(String projectId, String monthId, String feeType, int offset, int limit) {
        EnterpriseProjectFeeMapper enterpriseProjectFeeMapper = sqlSession.getMapper(EnterpriseProjectFeeMapper.class);

        List<EnterpriseProjectFee> enterpriseProjectFeeList = enterpriseProjectFeeMapper.selectByPrimaryKey(projectId, feeType, monthId, offset, limit);
        return enterpriseProjectFeeList;
    }

    @Override
    public int selctCount(String projectId, String monthId, String feeType) {
        EnterpriseProjectFeeMapper enterpriseProjectFeeMapper = sqlSession.getMapper(EnterpriseProjectFeeMapper.class);

        return enterpriseProjectFeeMapper.selectCount(projectId, feeType, monthId);
    }

    @Override
    public int modifyProjectApproval(EnterpriseProjectApproval enterpriseProjectApproval) {
        EnterpriseProjectApprovalMapper enterpriseProjectApprovalMapper = sqlSession.getMapper(EnterpriseProjectApprovalMapper.class);
        enterpriseProjectApprovalMapper.updateByPrimaryKeySelective(enterpriseProjectApproval);
        return 0;
    }

    @Override
    public EnterpriseProjectApproval queryProjectApproval(String projectId) {
        EnterpriseProjectApprovalMapper enterpriseProjectApprovalMapper = sqlSession.getMapper(EnterpriseProjectApprovalMapper.class);

        return enterpriseProjectApprovalMapper.selectByPrimaryKey(projectId);
    }

    @Override
    public int modifyProjectBasic(EnterpriseProjectBasic enterpriseProjectBasic) {
        EnterpriseProjectBasicMapper enterpriseProjectBasicMapper = sqlSession.getMapper(EnterpriseProjectBasicMapper.class);
        enterpriseProjectBasicMapper.updateByPrimaryKeySelective(enterpriseProjectBasic);
        return 0;
    }

    @Override
    public EnterpriseProjectBasic queryProjectBasic(String projectId) {
        EnterpriseProjectBasicMapper enterpriseProjectBasicMapper = sqlSession.getMapper(EnterpriseProjectBasicMapper.class);

        return enterpriseProjectBasicMapper.selectByPrimaryKey(projectId);
    }
}
