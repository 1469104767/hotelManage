<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		request.setCharacterEncoding("utf-8");
	%>
	<jsp:useBean id="client" class="example.bean.client.client"></jsp:useBean>
	<jsp:setProperty property="*" name="client" />
	<%
		try {
			// 加载数据库驱动，注册到驱动管理器
			Class.forName("com.mysql.jdbc.Driver");
			// 数据库连接字符串
			String url = "jdbc:mysql://localhost:3306/eims?useUnicode=true&characterEncoding=utf-8";
			// 数据库用户名
			String username = "root";
			// 数据库密码
			String password = "root";
			// 创建Connection连接
			Connection conn = DriverManager.getConnection(url, username,
					password);
			// 添加信息的SQL语句
			String sql = "insert into client(clientName,clientTelephone,clientAddress,clientEmail) values(?,?,?,?)";
			// 获取PreparedStatement
			PreparedStatement ps = conn.prepareStatement(sql);
			// 对SQL语句中的第1个参数赋值
			ps.setString(1, client.getClientName() );
			// 对SQL语句中的第2个参数赋值
			ps.setString(2, client.getClientTelephone());
			// 对SQL语句中的第3个参数赋值
			ps.setString(3, client.getClientAddress());
			// 对SQL语句中的第4个参数赋值
			ps.setString(4, client.getClientEmail());
			// 执行更新操作，返回所影响的行数
			int row = ps.executeUpdate();
			// 判断是否更新成功
			if (row > 0) {
				// 更新成输出信息
				out.print("成功添加了 " + row + "条数据！");
			}
			// 关闭PreparedStatement，释放资源
			ps.close();
			// 关闭Connection，释放资源
			conn.close();
		} catch (Exception e) {
			out.print("信息添加失败！");
			e.printStackTrace();
		}
	%>
<br>
	<a href="lookClient.jsp">返回</a>
</body>
</html>