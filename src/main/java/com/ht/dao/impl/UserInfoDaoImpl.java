package com.ht.dao.impl;

import com.ht.dao.IUserInfoDao;
import com.ht.dao.mapper.UserInfoMapper;
import com.ht.model.UserInfo;

public class UserInfoDaoImpl extends BaseDao implements IUserInfoDao {

    @Override
    public UserInfo getUserById(int userId) {
        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
        return mapper.selectByPrimaryKey(userId);
    }

    @Override
    public UserInfo getUserByUserNo(String userNo) {
        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
        return mapper.selectByUserNo(userNo);
    }

    @Override
    public int insertUser(UserInfo userInfo) {
        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
        return mapper.insert(userInfo);
    }

}
