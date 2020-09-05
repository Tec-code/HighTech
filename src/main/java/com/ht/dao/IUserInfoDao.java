package com.ht.dao;

import com.ht.model.UserInfo;

public interface IUserInfoDao {
    UserInfo getUserById(int userId);
}
