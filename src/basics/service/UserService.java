package basics.service;

import basics.entity.User;
import basics.mapper.UserMapper;
import core.annotation.Transaction;
import core.base.BaseService;


public class UserService extends BaseService<User,UserMapper> {
	@Transaction
	public void test(String text) throws Exception{
		System.out.println(text);
	}
	
	@Transaction
	public User dologin(String clientName,String clientPassword) {
		return mapper.doLogin(clientName,clientPassword);
	}
}
