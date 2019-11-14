package com.niup.demo.dao;

import com.niup.demo.entity.Test;

public class TestDao extends BaseDao<Test>{
    private static TestDao instance = new TestDao();
    private TestDao(){
    }
    public static TestDao getInstance(){
        return instance;
    }
    
}