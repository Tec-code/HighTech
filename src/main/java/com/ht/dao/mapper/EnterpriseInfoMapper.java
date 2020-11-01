package com.ht.dao.mapper;

import com.ht.model.EnterpriseInfo;

import java.util.List;

public interface EnterpriseInfoMapper {
    int deleteByPrimaryKey(Integer enterpriseId);

    int insert(EnterpriseInfo record);

    int insertSelective(EnterpriseInfo record);

    EnterpriseInfo selectByPrimaryKey(Integer enterpriseId);

    int selectExactEnterpriseName(String enterpriseName);

    int updateByPrimaryKeySelective(EnterpriseInfo record);

    int updateByPrimaryKey(EnterpriseInfo record);

    List<EnterpriseInfo> getEnterpriseListByUserId(int userId);

    List<EnterpriseInfo> getEnterpriseListByEnterpriseInfo(int userId,String enterpriseName);
}