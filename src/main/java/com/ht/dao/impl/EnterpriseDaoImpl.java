package com.ht.dao.impl;

import com.ht.dao.IEnterpriseDao;
import com.ht.dao.mapper.EnterpriseInfoMapper;
import com.ht.model.EnterpriseInfo;

import java.util.List;

public class EnterpriseDaoImpl extends BaseDao implements IEnterpriseDao {
    @Override
    public int insertEnterprise(EnterpriseInfo enterpriseInfo) {
        EnterpriseInfoMapper mapper = sqlSession.getMapper(EnterpriseInfoMapper.class);
        return mapper.insert(enterpriseInfo);
    }

    @Override
    public List<EnterpriseInfo> getEnterpriseListByEnterpriseName(int userId, String enterpriseName) {
        EnterpriseInfoMapper mapper = sqlSession.getMapper(EnterpriseInfoMapper.class);
        return mapper.getEnterpriseListByEnterpriseInfo(userId,enterpriseName);
    }

    @Override
    public List<EnterpriseInfo> getEnterpriseListByUserId(int userId) {

        EnterpriseInfoMapper mapper = sqlSession.getMapper(EnterpriseInfoMapper.class);
        return mapper.getEnterpriseListByUserId(userId);
    }

    @Override
    public int checkEnterpriseNameExists(String enterpriseName) {
        EnterpriseInfoMapper mapper = sqlSession.getMapper(EnterpriseInfoMapper.class);

        return mapper.selectExactEnterpriseName(enterpriseName);
    }
}
