package com.ht.service.impl;

import com.ht.service.IUserService;
import com.ht.model.UserInfo;
import com.ht.dao.IUserInfoDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    private static Log logger = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    IUserInfoDao userDao;

    @Override
    public UserInfo getUserById(int userId) {
        return userDao.getUserById(userId);
    }
}
