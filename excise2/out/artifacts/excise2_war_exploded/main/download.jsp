<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>下载页面</title>
<link href="${pageContext.request.contextPath}/css/download.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" />
</head>
<body>
	<h2>资源下载</h2>
	
	<c:forEach items="${downloadList}" var="list">
		<div class="main">	
			<div class="main_top">
				<img src="fileIcon/${list.getImage()}" class="img_book"/>
				<div class="main_title">
				<p hidden="hidden"><c:out value="${list.getId()}"/></p>
					<h3 class="title"><c:out value="${list.getName()}"/></h3>
					<p class="statement">大小：<fmt:formatNumber type="number" value="${list.getSize()/1024/1024}" pattern="0.0" maxFractionDigits="2"/>MB</p>
					<p class="statement">时间：2020-09-20</p>
					<p class="star" hidden="hidden"><c:out value="${list.getStar()}"/></p>
					<p class="statement_star">星级：</p>
						<ul class="statement_ul">
							<li class="fa fa-star-o show" aria-hidden="true"></li>
							<li class="fa fa-star-o show" aria-hidden="true"></li>
							<li class="fa fa-star-o show" aria-hidden="true"></li>
							<li class="fa fa-star-o show" aria-hidden="true"></li>
							<li class="fa fa-star-o show" aria-hidden="true"></li>
						</ul>
				</div>
				<a href="Download.do?url_id=${list.getId()}" id="btn_download">立即下载</a>
			</div>
			<div class="main_bottom">
				<p><c:out value="${list.getDescription()}"/></p>
			</div>
		</div>
	</c:forEach>
	<a href="${pageContext.request.contextPath}/main.jsp" id="back">返回首页</a>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/download.js"></script>
</body>
</html>