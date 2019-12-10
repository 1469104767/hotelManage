package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import base.User;

public class UserDao {
	private String url = "jdbc:mysql://localhost:3306/hotelmanage?useUnicode=true&characterEncoding=utf-8";
	// 数据库用户名
	private String username = "root";
	// 数据库密码
	private String password = "root";
	
	public int addUser(User user) {
		// 加载数据库驱动，注册到驱动管理器
		try {
					Class.forName("com.mysql.jdbc.Driver");
					// 创建Connection连接
					Connection conn = DriverManager.getConnection(url, username,
							password);
					// 添加信息的SQL语句
					String sql = "insert into user(clientName,clientPassword,clientType,clientTel) values(?,?,?,?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, user.getClientName() );
					ps.setString(2, user.getClientPassword());
					ps.setInt(3, user.getClientType());
					ps.setString(4, user.getClientTel());
					int row = ps.executeUpdate();
					// 判断是否更新成功
					if (row > 0) {
						// 更新成输出信息
						System.out.print("成功添加了 " + row + "条数据！");
					}
					ps.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		return 0;
	}
	public List<User> dologin(User user) {
		List<User> userList = new ArrayList<>();
		// 加载数据库驱动，注册到驱动管理器
		try {
					Class.forName("com.mysql.jdbc.Driver");
					// 创建Connection连接
					Connection conn = DriverManager.getConnection(url, username,
							password);
					// 添加信息的SQL语句
					String sql = "select * from user where clientName = ? and clientPassword = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, user.getClientName() );
					ps.setString(2, user.getClientPassword());
					
					
					ResultSet set = ps.executeQuery();
					while(set.next()) {
						User user2 = new User();
						user2.setId(set.getInt("id"));
						user2.setClientName(set.getString("clientName"));
						user2.setClientPassword(set.getString("clientPassword"));
						user2.setClientTel(set.getString("clientTel"));
						user2.setClientType(set.getInt("clientType"));
						userList.add(user2);
					}
					ps.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		return userList;
	}
	public int delectUser(int id) {
		// 加载数据库驱动，注册到驱动管理器
		try {
					Class.forName("com.mysql.jdbc.Driver");
					// 创建Connection连接
					Connection conn = DriverManager.getConnection(url, username,
							password);
					// 添加信息的SQL语句
					String sql = "delete from user where id=?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, id);
					int row = ps.executeUpdate();
					// 判断是否更新成功
					if (row > 0) {
						// 更新成输出信息
						System.out.print("成功删除 " + row + "条数据！");
					}
					ps.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		return 0;
	}
	
	public int updateClientType(int id,int type) {
		// 加载数据库驱动，注册到驱动管理器
		try {
					Class.forName("com.mysql.jdbc.Driver");
					// 创建Connection连接
					Connection conn = DriverManager.getConnection(url, username,
							password);
					// 添加信息的SQL语句
					String sql = "";
					if(type == 0) {
						 sql = "update user set clientType = 1 where id = ?";
					}else if(type == 1) {
						sql = "update user set clientType = 0 where id = ?";
					}
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, id);
					int row = ps.executeUpdate();
					// 判断是否更新成功
					if (row > 0) {
						// 更新成输出信息
						System.out.print("成功更新 " + row + "条数据！");
					}
					ps.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		return 0;
	}
}
