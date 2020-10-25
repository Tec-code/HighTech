package com.ht.dao.mapper;

import com.ht.model.EnterpriseUserRela;
import org.apache.ibatis.annotations.Param;

public interface EnterpriseUserRelaMapper {
    int deleteByPrimaryKey(@Param("enterpriseId") Integer enterpriseId, @Param("userId") String userId);

    int insert(EnterpriseUserRela record);

    int insertSelective(EnterpriseUserRela record);

    EnterpriseUserRela selectByPrimaryKey(@Param("enterpriseId") Integer enterpriseId, @Param("userId") String userId);

    int updateByPrimaryKeySelective(EnterpriseUserRela record);

    int updateByPrimaryKey(EnterpriseUserRela record);
}