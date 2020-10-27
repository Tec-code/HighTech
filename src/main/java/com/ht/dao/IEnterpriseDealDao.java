package com.ht.dao;

import com.ht.model.EnterpriseInfo;

import java.util.List;

public interface IEnterpriseDealDao {
    List<EnterpriseInfo> getEnterpriseListByUserId(int UserId);

    List<EnterpriseInfo> getEnterpriseListByEnterpriseName(int UserId, String EnterpriseName);
}
