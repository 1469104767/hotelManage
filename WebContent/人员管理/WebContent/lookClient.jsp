<%@page import="java.sql.*" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户查询</title>
</head>
<body>
<table id="tab1">
<tr>
<td><a href="lookClient.jsp">客户查询</a></td>
<td><a href="addClient.jsp">客户添加</a></td>
<td><a href="deleteClient.jsp">客户删除</a></td>
</tr>
</table>
<br>
<table id="tab2">
<tr>
<th>查看用户信息</th>
</tr>
<tr>
<td>用户名</td>
<td>电话</td>
<td>地址</td>
<td>密码</td>
</tr>
<%



Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://localhost:3306/eims?useUnicode=true&characterEncoding=utf-8";
Connection con=DriverManager.getConnection(url,"root","root");
Statement stmt=con.createStatement();
String sql="select * from client";
ResultSet rs=stmt.executeQuery(sql);
while(rs.next()){
%>
<tr>
<td><%=rs.getString("clientName") %></td>
<td><%=rs.getString("clientTelephone") %></td>
<td><%=rs.getString("clientAddress") %></td>
<td><%=rs.getString("clientEmail") %></td>
</tr>
<%
}
%>
</table>

</body>
</html>