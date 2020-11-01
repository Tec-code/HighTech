package com.ht.dao.mapper;

import com.ht.model.EnterpriseProjectBasic;

public interface EnterpriseProjectBasicMapper {
    int deleteByPrimaryKey(String projectId);

    int insert(EnterpriseProjectBasic record);

    int insertSelective(EnterpriseProjectBasic record);

    EnterpriseProjectBasic selectByPrimaryKey(String projectId);

    int updateByPrimaryKeySelective(EnterpriseProjectBasic record);

    int updateByPrimaryKey(EnterpriseProjectBasic record);
}