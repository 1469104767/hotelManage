<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
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
<form action="addClientCheck.jsp" method="post">
<table id="tab3">
<tr>
<th>添加客户信息</th>
</tr>
<tr>
<td>用户名</td>
<td><input type="text" name="clientName"/></td>
</tr>
<tr>
<td>电话</td>
<td><input type="text" name="clientTelephone"/></td>
</tr>
<tr>
<td>地址</td>
<td><input type="text" name="clientAddress"/></td>
</tr>
<tr>
<td>密码</td>
<td><input type="text" name="clientEmail"/></td>
</tr>
<tr>
<td><input type="submit" value="确认" onclick="window.location.href='addClientCheck.jsp'">&nbsp;&nbsp;&nbsp;<input type="reset" value="返回" onclick="window.location.href='lookClient.jsp'"></td>
</tr>
</table>
</form>
</body>
</html>