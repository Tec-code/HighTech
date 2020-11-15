package com.ht.dao.mapper;

import com.ht.model.NodeInfo;

public interface NodeInfoMapper {
    int deleteByPrimaryKey(Integer nodeId);

    int insert(NodeInfo record);

    int insertSelective(NodeInfo record);

    NodeInfo selectByPrimaryKey(Integer nodeId);

    int updateByPrimaryKeySelective(NodeInfo record);

    int updateByPrimaryKey(NodeInfo record);
}