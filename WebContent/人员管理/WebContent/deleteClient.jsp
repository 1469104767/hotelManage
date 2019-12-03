<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="DeleteServlet" method="post">
<table>
<tr>
<th>删除信息</th>
</tr>
<tr>
<td><a href="lookClient.jsp">查询</a></td>
<td><a href="addClient.jsp">添加</a></td>
<td><a href="deleteClient.jsp">删除</a></td>
</tr>
<tr>
<td>用户名</td>
<td><input type="text" name="clientName">输入要删除的用户名</td>
</tr>
<tr>
<td><input type="submit" name="sure" value="确认"></td>
</tr>
</table>
</form>
</body>
</html>