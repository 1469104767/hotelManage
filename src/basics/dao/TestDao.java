package basics.dao;

import basics.entity.Test;
import core.base.BaseDao;

public class TestDao extends BaseDao<Test>{
    
    public TestDao(){
    	this.insert = "insert into test(name,time) values(?,?)";
    	this.select = "select * from test";
    	this.update = "update test set name  =  ?,time = ? where id  =  ?";
    	this.delete = "delete from test where id  =  ?";
    }

}