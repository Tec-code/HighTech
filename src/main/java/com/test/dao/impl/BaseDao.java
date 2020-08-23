package com.test.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 所有Dao的基类，用来注入sqlSession
 */
public class BaseDao {
    @Autowired
    protected SqlSession sqlSession;

}
