package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import base.Order;

public class OrderDao {
	private String url = "jdbc:mysql://localhost:3306/hotelmanage?useUnicode=true&characterEncoding=utf-8";
	// 数据库用户名
	private String username = "root";
	// 数据库密码
	private String password = "root";
	
	public int addOrder(Order order) {
		// 加载数据库驱动，注册到驱动管理器
		try {
					Class.forName("com.mysql.jdbc.Driver");
					// 创建Connection连接
					Connection conn = DriverManager.getConnection(url, username,
							password);
					// 添加信息的SQL语句
					String sql = "insert into orderlist(innerPeople,idCard,innerTel,roomNum,roomType,startTime,endTime,roomPrice,operator) values(?,?,?,?,?,?,?,?,?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, order.getInnerPeople() );
					ps.setString(2, order.getIdCard() );
					ps.setString(3, order.getInnerTel() );
					ps.setInt(4, order.getRoomNum() );
					ps.setInt(5, order.getRoomNum() );
					ps.setDate(6, (Date) order.getStartTime());
					ps.setDate(7,(Date) order.getEndTime() );
					ps.setDouble(8, order.getRoomPrice());
					ps.setString(9, order.getOperator());
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
}
