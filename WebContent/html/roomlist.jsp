<%@page import="java.sql.*" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>房间管理</title>
    <link rel="stylesheet" href="../css/base.css">
    <style>
        .rightbox .rightbox-container .addRoomBtn{
            padding:5px 20px;
            margin: 20px;
            border-radius:5px; 
            background: #108ee9;
            border: 1px solid #108ee9;
            color: #fff;
            
        }
        .rightbox .rightbox-container .title{
            line-height: 36px;
            font-size: 24px
        }
        .rightbox .rightbox-container .roomList{
            padding: 20px;
            background: #fff;
            display: flex;
            flex-wrap:wrap;
        }
        .rightbox .rightbox-container .roomList li{
            display: flex;
            border: 1px solid #ccc;
            padding: 20px;
            margin: 20px;
        }
        
        .rightbox .rightbox-container .roomList li .roomImg img{
            width: 100px;
            height: 100px;
        }
        .rightbox .rightbox-container .roomList li .roomMsg{
            padding: 0 10px;
        }
        .rightbox .rightbox-container .roomList li .roomMsg button{
            background: #ccc;
            border: 1px solid #ccc;
            text-align: center;
            padding: 0 30px;
            color: #fff;
        }
    </style>
</head>

<body>
    <div class="leftbox">
        <div class="logo">
            <img src="../imgs/logo.png" alt="">
        </div>
        <ul class="menu">
                <li class="current"><a href="./roomlist.jsp">房间管理</a></li>
                <li><a href="./orderlist.jsp">订单管理</a></li>
                <li><a href="./userlist.jsp">会员管理</a></li>
        </ul>
    </div>
    <div class="rightbox">
        <div class="rightbox-header">
            <p class="logo-title">酒店管理平台<p>
            <p id="userNameShow">admin<button id="quitBtn">退出</button></p>
        </div>
        <div class="rightbox-container">
                <div class="title">房间管理</div>
                <button class="addRoomBtn">新建房间</button>
                <ul class="roomList" id="roomList">
                <%
					Class.forName("com.mysql.jdbc.Driver");
					String url="jdbc:mysql://localhost:3306/hotelmanage?useUnicode=true&characterEncoding=utf-8";
					Connection con=DriverManager.getConnection(url,"root","root");
					Statement stmt=con.createStatement();
					String sql="select * from room";
					ResultSet rs=stmt.executeQuery(sql);
					while(rs.next()){
				%>
                    <li>
                        <div class="roomImg">
                            <img src="../imgs/lalala.jpg" alt="">
                        </div>
                        <div class="roomMsg">
                            <p>房间编号：<%=rs.getInt("roomNum") %></p>
                            <p>房间类型：<%if(rs.getInt("roomType")==1){ %>大床房
                            <%}else if(rs.getInt("roomType")==2){ %>标准套房
                            <%}else if(rs.getInt("roomType")==3){ %>总统套房
                            <%} %>
                            </p>
                            <p>房间价格: <%=rs.getDouble("roomPrice") %></p>
                            <p>房间状态：<%if(rs.getInt("roomStatus")==0){ %>空闲
                            <%}else if(rs.getInt("roomStatus")==1){ %>已入住
                            <%}else if(rs.getInt("roomStatus")==2){ %>未上架
                            <%} %></p>
                            <p><button data-id="<%=rs.getInt("id") %>" class="gotoDetailBtn">查看详情</button></p>
                        </div>
                    </li>
                    <% } %>
                </ul>
            </div>
    </div>
    <script src="../js/jquery-1.7.2.min.js"></script>
    <script src="../js/base.js"></script>
    <script>
    $(".gotoDetailBtn").on("click",function(e){
        console.log(e.target);
        let $target = $(e.target);
        let roomId = $target.data("id");
        window.location.href="./roomdetail.html?roomId="+roomId;
    })
    $(".addRoomBtn").on('click',function(){
    	 window.location.href="./addroom.jsp"
    });
    </script>
</body>
</html>