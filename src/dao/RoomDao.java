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
	
	
	public int addRoom(Room room) {
		// 加载数据库驱动，注册到驱动管理器
		int flag = 0;
		try {
					Class.forName("com.mysql.jdbc.Driver");
					// 创建Connection连接
					Connection conn = DriverManager.getConnection(url, username,
							password);
					// 添加信息的SQL语句
					String sql = "insert into room(roomNum,roomName,roomType,roomPrice) values(?,?,?,?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, room.getRoomNum() );
					ps.setString(2,room.getRoomName());
					ps.setInt(3,room.getRoomType());
					ps.setDouble(4,room.getRoomPrice());
					int row = ps.executeUpdate();
					// 判断是否更新成功
					if (row > 0) {
						// 更新成输出信息
						System.out.print("成功添加了 " + row + "条数据！");
					}
					ps.close();
					conn.close();
					flag = 0;
				} catch (Exception e) {
					e.printStackTrace();
					flag = 1;
				}
		return flag;
	}
	
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
	public int UpdateRoom(Room room) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 创建Connection连接
			Connection conn = DriverManager.getConnection(url,username,password);
			// 添加信息的SQL语句
			String sql = "update room set roomStatus=1, innerPeople = ?,innerTel = ?,idCard=?,startTime=?, endTime = ?,roomUrl=? where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,room.getInnerPeople());
			ps.setString(2,room.getInnerTel());
			ps.setString(3,room.getIdCard());
			ps.setDate(4,room.getStartTime());
			ps.setDate(5,room.getEndTime());
			ps.setString(6,room.getRoomUrl());
			ps.setInt(7, room.getId());
			int row = ps.executeUpdate();
			// 判断是否更新成功
			if (row > 0) {
				// 更新成输出信息
				System.out.print("成功更新了 " + row + "条数据！");
			}
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int OutRoom(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 创建Connection连接
			Connection conn = DriverManager.getConnection(url,username,password);
			// 添加信息的SQL语句
			String sql = "update room set roomStatus=0, innerPeople = null,innerTel = null,idCard=null,startTime=null, endTime = null where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int row = ps.executeUpdate();
			// 判断是否更新成功
			if (row > 0) {
				// 更新成输出信息
				System.out.print("成功更新了 " + row + "条数据！");
			}
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
