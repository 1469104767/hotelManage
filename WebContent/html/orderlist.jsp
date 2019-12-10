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
                <p id="userNameShow">admin<button id="quitBtn">退出</button></p>
         		
        </div>
        <div class="rightbox-container">
            <div class="title">订单列表</div>
        <table class="userTable" id="userTable">
            <thead>
            
                <tr>
                    <th>订单编号</th>
                    <th>房客姓名</th>
                    <th>房客电话</th>
                    <th>房客身份证</th>
                    <th>房间编号</th>
                    <th>房间类型</th>
                    <th>房间价格</th>
                    <th>入住时间</th>
                    <th>结束时间</th>
                    <th>操作人员</th>
                </tr>
            </thead>
            <tbody>
                  <%
					Class.forName("com.mysql.jdbc.Driver");
					String url="jdbc:mysql://localhost:3306/hotelmanage?useUnicode=true&characterEncoding=utf-8";
					Connection con=DriverManager.getConnection(url,"root","root");
					Statement stmt=con.createStatement();
					String sql="select * from orderlist";
					ResultSet rs=stmt.executeQuery(sql);
					while(rs.next()){
				%>
                <tr>
                
                    <td><%=rs.getInt("id") %></td>
                    <td><%=rs.getString("innerPeople") %></td>
                    <td><%=rs.getString("innerTel") %></td>
                    <td><%=rs.getString("idCard") %></td>
                    <td><%=rs.getInt("roomNum") %></td>
                    <td><%if(rs.getInt("roomType")==1){ %>大床房
                          <%}else if(rs.getInt("roomType")==2){ %>标准套房
                          <%}else if(rs.getInt("roomType")==3){ %>总统套房
                          <%} %></td>
                    <td><%=rs.getBigDecimal("roomPrice") %></td>
            		<td><%=rs.getTimestamp("startTime") %></td>
            		<td><%=rs.getTimestamp("endTime") %></td>
            		<td><%=rs.getString("operator") %></td>
                </tr>
                <% }%>
            </tbody>
        </table>
    </div>
    <script src="../js/jquery-1.7.2.min.js"></script>
    <script>
    </script>
</body>
</html>