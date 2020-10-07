<%--
  Created by IntelliJ IDEA.
  User: zzc
  Date: 2020/10/6
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link type="text/css" href="css/login.css" rel="stylesheet"/>
</head>
<body>
<div class="main">
    <h1>欢迎登录</h1>
    <form action="Login.do" method="POST">
        <label>
            <span>输入用户账号：</span>
            <input class="nameAndPassword" required="required" type="text" name="userName" placeholder="请输入您的账号"/>
        </label>

        <label>
            <span>输入用户密码：</span>
            <input class="nameAndPassword" type="password" required="required" name="userPassword"/>
        </label>

        <label>
            <span class="yzm_span">请输入验证码：</span>
            <input type="text" required="required" name="Vcode" class="vcode_input" placeholder="验证码"/>
            <img alt="验证码" src="createVerifyImage.do" id="img_btn" />
            <p id="p_show">看不清？点击刷新</p>
        </label>

        <label class="check_box">
            <input type="checkbox" name="radio_check" class="check_box_input" /><span id="check_text">一周以内免登录</span>
        </label>

        <input type="submit" value="立即登录" class="btn"/>
    </form>
</div>
<script src="js/login.js" type="text/javascript"></script>
</body>
</html>