package com.ht.dao.impl;

import com.ht.model.User;
import com.ht.dao.IUserDao;
import com.ht.dao.mapper.UserMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserDaoImpl extends BaseDao implements IUserDao {
    private static Log logger = LogFactory.getLog(UserDaoImpl.class);

    @Override
    public User getUserById(int userId) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectByPrimaryKey(userId);
    }
}
