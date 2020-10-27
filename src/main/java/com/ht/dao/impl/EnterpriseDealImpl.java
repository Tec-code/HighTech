package com.ht.dao.impl;

import com.ht.dao.IEnterpriseDealDao;
import com.ht.dao.mapper.EnterpriseDealMapper;
import com.ht.model.EnterpriseDeal;
import com.ht.model.EnterpriseInfo;
import com.ht.model.EnterpriseUserRela;
import org.springframework.util.StringUtils;

import java.util.List;

public class EnterpriseDealImpl extends BaseDao implements IEnterpriseDealDao {

    @Override
    public List<EnterpriseInfo> getEnterpriseListByEnterpriseName(int userId, String enterpriseName) {
        EnterpriseDealMapper mapper = sqlSession.getMapper(EnterpriseDealMapper.class);

        EnterpriseDeal enterpriseDeal = new EnterpriseDeal();
        enterpriseDeal.setUserId(userId);
        if (!StringUtils.isEmpty(enterpriseName)) {
            enterpriseDeal.setEnterpriseName(enterpriseName);
        }

        return mapper.getEnterpriseListByEnterpriseInfo(enterpriseDeal);
    }

    @Override
    public List<EnterpriseInfo> getEnterpriseListByUserId(int userId) {

        EnterpriseDealMapper mapper = sqlSession.getMapper(EnterpriseDealMapper.class);
        return mapper.getEnterpriseListByUserId(userId);
    }
}
