package basics.mapper;

import java.util.List;
import java.util.Map;

import basics.entity.Test;
import core.annotation.Sql;
import core.base.BaseMapper;
import core.enumx.SqlType;

public interface TestMapper extends BaseMapper<Test>{
	
	String insert = "insert into test(name,time) values(#{name},#{time})";
	String delete = "delete from test where id = #{id}";
	String update = "update test set name = #{name},time = #{time} where id = #{id}";
	String select = "select * from test";
	String findById = select + "where id = #{id}";
	String findList = select + "${queryString}";
	
	@Sql(value="select * from test")
	public List<Map<String,Object>> delete1(Integer id);
}