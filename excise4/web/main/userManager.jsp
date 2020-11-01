<%--
  Created by IntelliJ IDEA.
  User: zzc
  Date: 2020/10/6
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户管理</title>
    <link type="text/css" href="${pageContext.request.contextPath}/css/userManager.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="header">
    <div class="header_left">
        <img src="${pageContext.request.contextPath}/images/excise.png"/>
    </div>

    <div class="header_right">
        <div class="user_info">
            <p>欢迎您：<span id="user_name">${main.chrName}</span>
                <a href="Logout.do" id="exit_btn">【安全退出】</a>
            </p>
        </div>
        <div>
            <ul>
                <li><a href="main.jsp">首页</a></li>
                <li><a href="GetDownloadList.do">资源下载</a></li>
                <li>用户管理</li>
                <li><a href="resourceManager.do">资源管理</a></li>
                <li><a href="personalCenter.do">个人中心</a></li>
            </ul>
        </div>
    </div>
</div>

<div id="pageBody">
    <div id="search">
        <form id="searchForm">
            <input type="text" name="userName" placeholder="输入用户名">
            <input type="text" name="chrName" placeholder="输入姓名">
            <input type="text" name="emailAddress" placeholder="输入邮箱地址">
            <input type="text" name="provinceName" placeholder="输入省份">
        </form>
        <div id="bt">
            <a href="#" id="btSearch" class="fa fa-search" aria-hidden="true">查找</a>
            <a href="#" id="btClear" class="fa fa-times" aria-hidden="true">清空</a>
            <a href="#" id="btAdd" class="fa fa-plus" aria-hidden="true" onclick="ShowDiv('MyDiv','fade')">增加</a>
            <a href="#" id="btDelete" class="fa fa-trash" aria-hidden="true">删除</a>
            <a href="#" id="btUpdate" class="fa fa-pencil-square-o" aria-hidden="true">修改</a>
        </div>
    </div>
    <table>
        <thead>
        <tr>
            <th width="40"><input type="checkbox" id="ckAll" title="选择" /></th>
            <th class="bg" id="sortByUserName" data="userName" >用户名</th>
            <th width="110">中文名</th>
            <th>邮箱</th>
            <th class="sortByProvinceName" data="provinceName">省份</th>
            <th width="70">城市</th>
            <th width="120">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>

    <div class="paging">
        <div class="pageSize"> 每页
            <select id="pageSize">
                <option>5</option>
                <option selected>10</option>
                <option>20</option>
            </select>，共
            <span id="total"></span>条数据，
            <span id="pageNumber">1</span>页/<span id="pageCount"></span>页
        </div>
        <div class="pageNav">
            <a id="first" href="#">首页</a>
            <a id="back" href="#">上一页</a>
            <a id="next" href="#">下一页</a>
            <a id="last" href="#">尾页</a>
        </div>
    </div>
</div>

<div id="fade" class="black_overlay" onclick="CloseDiv('MyDiv','fade')"></div>
<div id="MyDiv" class="white_content">
    <div style="text-align: right;height: 20px;">
        <span style="font-size: 24px;cursor:pointer;" title="点击关闭" onclick="CloseDiv('MyDiv','fade')">x</span>
    </div>
    <div>
        <h2>用户修改</h2>
        <input id="action" name="action" type="text" hidden="hidden">
        <label class="label_UserName">
            <input type="text" id="userName_Attribute" name="userName" placeholder="用户名">
            <span id="userNameError"></span>
        </label>

        <label class="label_ChrName">
            <input type="text" id="userChrName_Attribute" name="chrName" placeholder="真实姓名">
            <span id="chrNameError"></span>
        </label>

        <label class="label_Email">
            <input type="email" id="emailAddress_Attribute" name="emailAddress" placeholder="邮箱">
            <span id="emailError"></span>
        </label>

        <label class="label_Province">
            <select id="province" name="provinceCode">
                <option value="">请选择省份</option>
            </select>
            <span id="provinceError"></span>
        </label>

        <label class="label_City">
            <select id="city" name="cityCode">
                <option value="">请选择城市</option>
            </select>
            <span id="cityError"></span>
        </label>

        <label class="label_Password">
            <input type="password" id="userPassword_Attribute" name="userPassword" placeholder="密码">
            <span id="passwordError"></span>
        </label>

        <label class="label_Confirm">
            <input type="password" id="confirmPassword_Attribute" name="confirmPassword" placeholder="确认密码">
            <span id="confirmError"></span>
        </label>

        <label>
            <a href="#" id="btn_summit">确定</a>
        </label>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/userManager.js" type="text/javascript"></script>
</body>
</html>