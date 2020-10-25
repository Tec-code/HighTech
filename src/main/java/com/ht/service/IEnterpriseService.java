package com.ht.service;

import com.ht.model.EnterpriseInfo;

public interface IEnterpriseService {

    EnterpriseInfo GetEnterpriseList(String userId);
    int CreateEnterprise(String userId,EnterpriseInfo enterpriseInfo);
}
