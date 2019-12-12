<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>添加用户</title>
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
        .rightbox .rightbox-container .formBox{
            background: #fff;
            text-align: center;
        }
        .rightbox .rightbox-container .formBox p{
            line-height: 48px;
        }
        .rightbox .rightbox-container .formBox span{
            display: inline-block;
            width: 100px;
            text-align: right;
            margin-right:20px; 
        }
        .rightbox .rightbox-container .formBox input[type="text"]{
            width: 200px;
            height:24px;
        }
        .rightbox .rightbox-container .formBox input[type="radio"]{
            width: 48px;
        }
        .rightbox .rightbox-container .formBox input[type="submit"],.rightbox .rightbox-container .formBox button{
            width: 120px;
            color: #fff;
            margin-left:10px ;
            background: #108ee9;
            border: 1px solid #108ee9;
            border-radius: 5px;
            line-height: 24px; 
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
            <li><a href="./orderlist.jsp">订单管理</a></li>
            <li class="current"><a href="./userlist.jsp">会员管理</a></li>
        </ul>
    </div>
    <div class="rightbox">
        <div class="rightbox-header">
            <p class="logo-title">酒店管理平台<p>
                  <p id="userNameShow">admin<button id="quitBtn">退出</button></p>
        </div>
        <div class="rightbox-container">
            <div class="title">添加用户</div>
            <div class="formBox">
                    <p><span>请输入用户名</span><input type="text" name="clientName" id="clientName" /></p>
                    <p><span>请输入密码</span><input type="text" name="clientPassword" id="clientPassword"/></p>
                    <p><span>请输入电话</span><input type="text" name="clientTel" id="clientTel"/></p>
                    <p><span>请选择类型</span><input type="radio" name="clientType" value="0"/>管理员<input type="radio" name="clientType" value="1"/>普通用户</p>
                    <p><button type="button" id="submitUser">确定</button><button type="button" class="gotoBack">返回上一页</button></p>
            </div>
    </div>
    <script src="../js/jquery-1.7.2.min.js"></script>
    <script src="../js/base.js"></script>
    <script>
        $('.gotoBack').on('click',function(e){
            window.location.href = "userlist.jsp";
            e.stopPropagation();
           return;
        })
        $("#submitUser").on('click',function(){
        	let user = {};
        	user.clientName = $("#clientName").val();
        	user.clientPassword = $("#clientPassword").val();
        	user.clientTel = $("#clientTel").val();
        	user.clientType = $("input[name=clientType]:checked").val();
        	console.log(user);
        	$.ajax({
        		url:"/hotelManage/AddUser",
        		data:user,
        		type:"post",
        		success:function(result){
						alert("新增成功");
						window.location.href = "userlist.jsp"
        		}
        	})
        })
    </script>
</body>

</html>