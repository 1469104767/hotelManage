<%@page import="java.sql.*" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>用户列表</title>
    <link rel="stylesheet" href="../css/base.css">
    <style>
        .rightbox .rightbox-container .addPeopleBtn,
        .rightbox .rightbox-container .removePeopleBtn {
            padding: 5px 20px;
            margin: 20px;
            border-radius: 5px;
            background: #108ee9;
            border: 1px solid #108ee9;
            color: #fff;

        }

        .rightbox .rightbox-container .title {
            line-height: 36px;
            font-size: 24px
        }
        .rightbox .rightbox-container .userTable{
            margin: 50px 0;
            width: 100%;
            border: 1px solid #ccc;
            border-collapse:collapse;
        }
        .rightbox .rightbox-container .userTable th,.rightbox .rightbox-container .userTable td{
            text-align: center;
            border: 1px solid #ccc;
            line-height: 24px;
        }
        .rightbox .rightbox-container .userTable td a{
            margin: 0 10px;
        }
    </style>
</head>

<body>
    <div class="leftbox">
        <div class="logo">
            <img src="../imgs/logo.png" alt="">
        </div>
        <ul class="menu">
            <li><a href="./roomlist.jsp">房间管理</a></li>
            <li class="current"><a href="./orderlist.jsp">订单管理</a></li>
            <li><a href="./userlist.jsp">会员管理</a></li>
        </ul>
    </div>
    <div class="rightbox">
        <div class="rightbox-header">
            <p class="logo-title">酒店管理平台<p>
                    <p>admin</p>
        </div>
        <div class="rightbox-container">
            <div class="title">用户列表</div>
            <button class="addPeopleBtn">添加用户</button>
        <table class="userTable" id="userTable">
            <thead>
            
                <tr>
                    <th>用户名</th>
                    <th>密码</th>
                    <th>电话</th>
                    <th>职位</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                  <%
					Class.forName("com.mysql.jdbc.Driver");
					String url="jdbc:mysql://localhost:3306/hotelmanage?useUnicode=true&characterEncoding=utf-8";
					Connection con=DriverManager.getConnection(url,"root","root");
					Statement stmt=con.createStatement();
					String sql="select * from user";
					ResultSet rs=stmt.executeQuery(sql);
					while(rs.next()){
				%>
                <tr>
                
                    <td><%=rs.getString("clientName") %></td>
                    <td><%=rs.getString("clientPassword") %></td>
                    <td><%=rs.getString("clientTel") %></td>
                    <td><%if(rs.getInt("clientType")==0){ %>管理员
                    <%}else if(rs.getInt("clientType")==1){ %>普通用户
                    <%} %></td>
                    <td><a href="/hotelManage/DelectUser?id=<%=rs.getInt("id") %>">删除</a><a href="">设为普通用户</a></td>
                </tr>
                <% }%>
            </tbody>
        </table>
    </div>
    <script src="../js/jquery-1.7.2.min.js"></script>
    <script>
        $('.addPeopleBtn').on('click',function(){
            window.location.href = "adduser.jsp";
        })
    </script>
</body>
</html>