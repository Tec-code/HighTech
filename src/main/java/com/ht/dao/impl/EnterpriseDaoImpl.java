package com.ht.dao.impl;

import com.ht.dao.IEnterpriseDao;
import com.ht.dao.mapper.EnterpriseInfoMapper;
import com.ht.model.EnterpriseInfo;

public class EnterpriseDaoImpl extends BaseDao implements IEnterpriseDao {
    @Override
    public int insertEnterprise(EnterpriseInfo enterpriseInfo) {
        EnterpriseInfoMapper mapper = sqlSession.getMapper(EnterpriseInfoMapper.class);
        return mapper.insert(enterpriseInfo);
    }
}
