package com.niup.demo.dao;

import java.util.List;

import com.niup.demo.entity.Student;
import com.niup.jdbc.JdbcUtil;

public class StudentDao extends BaseDao<Student>{
    private static StudentDao instance = new StudentDao();
    private StudentDao(){
    }
    public static StudentDao getInstance(){
        return instance;
    }
    public List<Student> studentByAge(int age) {
    	String sql = JdbcUtil.getSql(Student.class, "getStudentByAge");
		return JdbcUtil.selectList(sql, Student.class, age);
    }
}