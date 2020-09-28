package com.ht.service;

import com.ht.model.UserInfo;

public interface IUserService {
    UserInfo getUserById(int userId);

    UserInfo getUserByUserNo(String userNo);

    boolean registerUser(UserInfo userInfo);

    boolean checkUserPassword(UserInfo userInfo, String password);

}
