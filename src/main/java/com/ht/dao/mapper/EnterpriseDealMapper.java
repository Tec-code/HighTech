package com.ht.dao.mapper;

import com.ht.model.EnterpriseDeal;
import com.ht.model.EnterpriseInfo;

import java.util.List;

public interface EnterpriseDealMapper {
    List<EnterpriseInfo> getEnterpriseListByUserId(int UserId);

    List<EnterpriseInfo> getEnterpriseListByEnterpriseInfo(EnterpriseDeal enterpriseDeal);
}
