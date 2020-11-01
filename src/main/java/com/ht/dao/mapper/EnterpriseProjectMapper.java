package com.ht.dao.mapper;

import com.ht.model.EnterpriseProject;

public interface EnterpriseProjectMapper {
    int deleteByPrimaryKey(String projectId);

    int insert(EnterpriseProject record);

    int insertSelective(EnterpriseProject record);

    EnterpriseProject selectByPrimaryKey(String projectId);

    int updateByPrimaryKeySelective(EnterpriseProject record);

    int updateByPrimaryKey(EnterpriseProject record);
}