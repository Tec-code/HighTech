package com.ht.dao;

import com.ht.model.EnterpriseProjectBasic;

public interface IEnterpriseProjectBasicDao {

    int insertEnterpriseProjectBasic(EnterpriseProjectBasic enterpriseProjectBasic);

    int deleteEnterpriseProjectBasic(String projectId);
}
