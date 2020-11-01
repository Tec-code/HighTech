package com.ht.dao.mapper;

import com.ht.model.EnterpriseProjectFee;
import org.apache.ibatis.annotations.Param;

public interface EnterpriseProjectFeeMapper {
    int deleteByPrimaryKey(@Param("projectId") String projectId, @Param("feeType") String feeType, @Param("monthId") String monthId);

    int insert(EnterpriseProjectFee record);

    int insertSelective(EnterpriseProjectFee record);

    EnterpriseProjectFee selectByPrimaryKey(@Param("projectId") String projectId, @Param("feeType") String feeType, @Param("monthId") String monthId);

    int updateByPrimaryKeySelective(EnterpriseProjectFee record);

    int updateByPrimaryKey(EnterpriseProjectFee record);
}