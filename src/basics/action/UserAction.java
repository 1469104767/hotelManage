package basics.action;

import java.util.List;
import java.util.Map;

import core.web.Message;

import basics.entity.User;
import basics.service.UserService;
import core.base.BaseAction;

public class UserAction extends BaseAction<User,UserService>{
	
	public Map<String,String> test(Map<String,String> parMap) throws Exception{
		service.test("");
		return parMap;
	}
	public List<User> getUserList(){
		return service.findList("");
	}
	public User doLogin(String clientName,String clientPassword) {
		return service.dologin(clientName,clientPassword);
	}
	public Message addUser(String clientName,String clientPassword,String clientTel,Integer clientType) {
		User user = new User();
		user.setClientName(clientName);
		user.setClientPassword(clientPassword);
		user.setClientTel(clientTel);
		user.setClientType(clientType);
		return service.save(user);

	}
	
}
