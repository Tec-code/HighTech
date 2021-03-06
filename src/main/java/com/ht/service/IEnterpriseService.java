package com.ht.service;

import com.ht.model.EnterpriseInfo;

import java.util.List;

public interface IEnterpriseService {

    List<EnterpriseInfo> GetEnterpriseList(int userId, String enterpriseName);

    int CreateEnterprise(int userId, EnterpriseInfo enterpriseInfo);

    EnterpriseInfo getEnterpriseById(int enterpriseId);

    boolean checkEnterpriseNameExist(String enterpriseName);

}
