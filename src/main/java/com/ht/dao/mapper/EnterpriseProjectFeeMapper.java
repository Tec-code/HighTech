package com.ht.dao.mapper;

import com.ht.model.EnterpriseProjectFee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnterpriseProjectFeeMapper {
    int deleteByPrimaryKey(@Param("projectId") String projectId, @Param("feeType") String feeType, @Param("monthId") String monthId);

    int insert(EnterpriseProjectFee record);

    int insertSelective(EnterpriseProjectFee record);

    int selectCount(@Param("projectId") String projectId, @Param("feeType") String feeType, @Param("monthId") String monthId);

    List<EnterpriseProjectFee> selectByPrimaryKey(@Param("projectId") String projectId, @Param("feeType") String feeType, @Param("monthId") String monthId, @Param("offset") int offset, @Param("limit") int limit);

    int updateByPrimaryKeySelective(EnterpriseProjectFee record);

    int updateByPrimaryKey(EnterpriseProjectFee record);
}