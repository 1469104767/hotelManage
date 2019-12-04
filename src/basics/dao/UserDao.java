package basics.dao;

import basics.entity.User;
import core.base.BaseDao;

public class UserDao extends BaseDao<User> {
	
	public UserDao() {
    	this.insert = "insert into user(clientName,clientPassword,clientTel,clientType) values(?,?,?,?)";
    	this.select = "select * from user";
    	this.update = "update user set clientName  =  ?,clientPassword = ?,clientTel=?,clientType=? where id  =  ?";
    	this.delete = "delete from user where id  =  ?";
	}
}
