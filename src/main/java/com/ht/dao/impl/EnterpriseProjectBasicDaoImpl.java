package com.ht.dao.impl;

import com.ht.dao.IEnterpriseProjectBasicDao;
import com.ht.dao.mapper.EnterpriseProjectBasicMapper;
import com.ht.model.EnterpriseProjectBasic;

public class EnterpriseProjectBasicDaoImpl extends BaseDao implements IEnterpriseProjectBasicDao {

    @Override
    public int insertEnterpriseProjectBasic(EnterpriseProjectBasic enterpriseProjectBasic) {
        EnterpriseProjectBasicMapper mapper = sqlSession.getMapper(EnterpriseProjectBasicMapper.class);
        return mapper.insert(enterpriseProjectBasic);
    }

    @Override
    public int deleteEnterpriseProjectBasic(String projectId) {
        EnterpriseProjectBasicMapper mapper = sqlSession.getMapper(EnterpriseProjectBasicMapper.class);
        return mapper.deleteByPrimaryKey(projectId);
    }
}
