<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人主页</title>
<link type="text/css" href="css/main.css" rel="stylesheet"/>
</head>
<body>
	<div class="header">
		<div class="header_left">
			<img src="images/excise.png"/>
		</div>
			
		<div class="header_right">
			<div class="user_info">
				<p>欢迎您：<span id="user_name">${main.chrName}</span>
				<a href="Logout.do" id="exit_btn">【安全退出】</a>
				</p>
			</div>
				<div>
					<ul>
						<li>首页</li>
						<li><a href="GetDownloadList.do?">资源下载</a></li>
						<li><a href="userManager.do">用户管理</a></li>
						<li><a href="resourceManager.do">资源管理</a></li>
						<li><a href="personalCenter.do">个人中心</a></li>
					</ul>
				</div>
			</div>
		</div>
	<img src="images/timg.jpg" id="main"/>
	
	<script type="text/javascript" src="js/main.js"></script>
</body>
</html>