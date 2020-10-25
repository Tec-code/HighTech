package com.ht.dao.impl;

import com.ht.dao.IEnterpriseUserRelaDao;
import com.ht.dao.mapper.EnterpriseInfoMapper;
import com.ht.dao.mapper.EnterpriseUserRelaMapper;
import com.ht.model.EnterpriseUserRela;

public class EnterpriseUserRelaDaoImpl extends BaseDao implements IEnterpriseUserRelaDao {
    @Override
    public int insertEnterpriseUserRela(EnterpriseUserRela enterpriseUserRela) {
        EnterpriseUserRelaMapper enterpriseUserRelaMapper = sqlSession.getMapper(EnterpriseUserRelaMapper.class);
        return enterpriseUserRelaMapper.insert(enterpriseUserRela);
    }
}
