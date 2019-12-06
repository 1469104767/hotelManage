package basics.mapper;

import java.util.List;
import java.util.Map;

import basics.entity.Room;
import core.annotation.Sql;
import core.base.BaseMapper;
import core.enumx.SqlType;

public interface RoomMapper extends BaseMapper<Room>{
	
	String insert = "insert into room(roomNum,roomName,roomPrice,roomStatus,innerPeople,innerTel,idCard,startTime,endTime) values(#{roomNum},#{roomName},#{roomPrice},#{roomStatus},#{innerPeople},#{innerTel},#{idCard},#{startTime},#{endTime})";
	String delete = "delete from room where id = #{id}"; 
	String update = "update room set roomNum=#{roomNum},roomName=#{roomName},roomPrice=#{roomPrice},roomStatus=#{roomStatus},innerPeople=#{innerPeople},innerTel=#{innerTel},idCard=#{idCard},startTime=#{startTime},endTime=#{endTime} where id = #{id}";
	String select = "select * from room";
	String findById = select + "where id = #{id}";
	String findList = select + "${queryString}";
	
	@Sql(value="select * from room")
	public List<Map<String,Object>> delete1(Integer id);
	
}