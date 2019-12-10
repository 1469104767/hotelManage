package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import base.Room;

public class RoomDao {
	private String url = "jdbc:mysql://localhost:3306/hotelmanage?useUnicode=true&characterEncoding=utf-8";
	// 数据库用户名
	private String username = "root";
	// 数据库密码
	private String password = "root";
	
	public List<Room> findRoomById(int id) {
		List<Room> roomList = new ArrayList<>();
		// 加载数据库驱动，注册到驱动管理器
		try {
					Class.forName("com.mysql.jdbc.Driver");
					// 创建Connection连接
					Connection conn = DriverManager.getConnection(url,username,password);
					// 添加信息的SQL语句
					String sql = "select * from room where id = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, id);
					
					ResultSet set = ps.executeQuery();
					while(set.next()) {
						Room room2 = new Room();
						room2.setId(set.getInt("id"));
						room2.setInnerPeople(set.getString("innerPeople"));
						room2.setInnerTel(set.getString("innerTel"));
						room2.setIdCard(set.getString("idCard"));
						room2.setRoomNum(set.getInt("roomNum"));
						room2.setRoomStatus(set.getInt("roomStatus"));
						room2.setRoomType(set.getInt("roomType"));
						room2.setRoomPrice(set.getDouble("roomPrice"));
						room2.setStartTime(set.getDate("startTime"));
						room2.setEndTime(set.getDate("endTime"));
						roomList.add(room2);
					}
					ps.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		return roomList;
	}
}
