package com.ht.service.impl;

import com.ht.constants.UserRole;
import com.ht.constants.UserStatus;
import com.ht.model.UserInfo;
import com.ht.service.IUserService;
import com.ht.dao.IUserInfoDao;
import com.ht.util.PasswordEncryptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    private static final Log logger = LogFactory.getLog(UserServiceImpl.class);
    // 密码加密算法
    private static final String EncryptAlgorithm = "sha-256";

    @Autowired
    IUserInfoDao userDao;

    @Override
    public UserInfo getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public UserInfo getUserByUserNo(String userNo) {
        try {
            return userDao.getUserByUserNo(userNo);
        } catch (Exception e) {
            logger.error(e);
            // TODO
            throw e;
        }
    }

    @Override
    public boolean registerUser(UserInfo userInfo) {

        // 加密密码
        String salt = UUID.randomUUID().toString().replace("-", "");
        PasswordEncryptor encryptor = new PasswordEncryptor(salt, EncryptAlgorithm);
        String encodedPassword = encryptor.encode(userInfo.getPassword());
        userInfo.setSalt(salt);
        userInfo.setPassword(encodedPassword);

        // TODO
        userInfo.setRole(UserRole.SYS_ADMIN.getRole());
        userInfo.setStatus(UserStatus.NORMAL.getStatus());

        try {
            userDao.insertUser(userInfo);
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }

    }

    @Override
    public boolean checkUserPassword(UserInfo userInfo, String password) {
        PasswordEncryptor encryptor = new PasswordEncryptor(userInfo.getSalt(), EncryptAlgorithm);
        String encodedPassword = encryptor.encode(password);
        return encodedPassword.equals(userInfo.getPassword());
    }

}
