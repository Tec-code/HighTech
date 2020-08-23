package com.test.dao.impl;

import com.test.model.User;
import com.test.dao.IUserDao;
import com.test.dao.mapper.UserMapper;
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
