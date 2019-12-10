<%@page import="java.sql.*" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登录</title>
    <style>
        .loginbox{
            margin: 200px auto;
            width: 400px;
            border: 1px solid #ccc;
            border-radius:5px; 
            padding: 20px;
        }
        .loginbox .title{
            text-align: center;
            font-size: 36px;
            color: #108eec;
        }
        .loginbox span{
            display: inline-block;
            width: 100px;
            text-align: right;
        }
        .loginbox input{
            width: 200px;
            line-height: 24px;
            border: 1px solid #ccc;
            border-radius:5px; 
            outline: none;
            box-shadow: none;
        }
        .loginbox .btnbox{
            text-align: center;
        }
        .loginbox .btnbox #loginBtn{
            padding: 0 20px;
            line-height: 36px;
            background: #fff;
            border: 1px solid #ccc;
            border-radius:5px; 
        }
    </style>
</head>
<body>
    <div class="loginbox">
        <p class="title">酒店管理平台</p>
        <p><span>用户名：</span><input type="text" name="clientName" id="clientName"/></p>
        <p><span>密码：</span><input type="text" name="clientPassword" id="clientPassword" /></p>
        <div class="btnbox">
            <button id="loginBtn">登录</button>
        </div>
    </div>
    <script src="../js/jquery-1.7.2.min.js"></script>
    <script>
        $("#loginBtn").on("click",function(){
        	let params = {};
        	params.clientName = $("#clientName").val();
        	params.clientPassword = $("#clientPassword").val();
            $.ajax({
                url:"/hotelManage/DoLogin",
                data:params,
                type:"post",
                success:function(result){
                	if(result == 0){
                		alert("账号或者密码错误");
                	}else{
                		let userObj = JSON.parse(result);
                		sessionStorage.setItem("user",userObj.clientName);
             			window.location.href="../html/userlist.jsp";
                		console.log(userObj);
                	}
                }
            })
        })
    </script>
</body>
</html>