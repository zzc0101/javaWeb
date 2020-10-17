<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%String basePath="login.html";%>

<c:if test="${!empty role}">
	<%basePath="main.jsp";%>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="10;url=<%=basePath%>">
<title>错误页面</title>
<link type="text/css" href="css/error.css" rel="stylesheet"/>
</head>
<body>
	<p hidden id="url_info"><%=basePath%></p>
	<div class="main">
		<img class="main_left" src="images/error_bg.jpg" />
		<div class="main_right">
			<h1 class="error_info">${error}</h1>
			<h2><span id="secs_Now">10</span>秒后自动返回到登录页面</h2>
			<h2>不能跳转，请<a href="javascript:void(0)" id="jump" >点击这儿</a></h2>
		</div>
	</div>
	
<script type="text/javascript" src="js/error.js"></script>
</body>
</html>