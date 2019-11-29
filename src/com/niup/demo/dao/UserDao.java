package com.niup.demo.dao;

import com.niup.demo.entity.User;
import com.niup.jdbc.JdbcUtil;

public class UserDao extends BaseDao<User>{
    private static UserDao instance = new UserDao();
    private UserDao(){
    }
    public static UserDao getInstance(){
        return instance;
    }
    public User findByName(String name) {
    	String sql = JdbcUtil.getSql(User.class, "findByName");
    	return JdbcUtil.selectOne(sql, User.class, name);
    }
}