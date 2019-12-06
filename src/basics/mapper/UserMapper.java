package basics.mapper;

import java.util.List;
import java.util.Map;

import basics.entity.User;
import core.annotation.Sql;
import core.base.BaseMapper;
import core.enumx.SqlType;

public interface UserMapper extends BaseMapper<User>{
	
	String insert = "insert into user(clientName,clientPassword,clientTel,clientType) values(#{clientName},#{clientPassword},#{clientTel},#{clientType})";
	String delete = "delete from user where id = #{id}";
	String update = "update user set clientName = #{clientName},clientPassword = #{clientPassword} ,clientTel = #{clientTel} ,clientType = #{clientType} where id = #{id}";
	String select = "select * from user";
	String findById = select + "where id = #{id}";
	String login = select +"where clientName=#{clientName},clientPassword = #{clientPassword}";
	String findList = select + "${queryString}";
	
	@Sql(value="select * from user")
	public List<Map<String,Object>> delete1(Integer id);
	
	@Sql(field="login")
	public User doLogin(String clientName,String clientPassword);
	
}