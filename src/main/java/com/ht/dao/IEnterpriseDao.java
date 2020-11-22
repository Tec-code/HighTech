package com.ht.dao;

import com.ht.model.EnterpriseInfo;

import java.util.List;

public interface IEnterpriseDao {
    int insertEnterprise(EnterpriseInfo enterpriseInfo);

    EnterpriseInfo getEnterpriseById(int enterpriseId);

    List<EnterpriseInfo> getEnterpriseListByUserId(int UserId);

    List<EnterpriseInfo> getEnterpriseListByEnterpriseName(int UserId, String EnterpriseName);

    int checkEnterpriseNameExists(String enterpriseName);
}
