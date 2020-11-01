let userParamsByObj;
let pageParamsByObj;
let isName=false;
let isChrName=false;
let isEmail=false;
let isProvince=false;
let isCity=false;
let isPwd=false;
let isConfrimPwd=false;
let oldName = "";
let oldEmail = "";
let oldPwd = "";
$(document).ready(function (){
    fillUserInfo(); // 自动查询填充用户信息
    selectedEachPageByDataShow(); // 监听每页显示条数选择
    selectedShowPage();  // 选择显示的页面

    // 菜单栏控制
    checkboxAll();  // checkbox 事件监听
    selectedUserByWhere();  // 根据动态参数查询用户
    clearSelected();    // 清空条件查询
    deleteCheckedUser();    // 删除选中用户
    addUser();      // 增加用户
    updateCheckedUser();    // 修改选中用户

    // 用户栏的修改和删除操作
    deleteUserByOne();
    updateUserByOne();

    register();
});

// 修改选中用户
function updateCheckedUser() {
    $("#btUpdate").click(function (){
        alert("暂时不支持此功能！！");
    });
}

// 添加用户
function addUser() {
    $("#btAdd").click(function (){
        registerClear();
        $("#action").text("insert");
        $("h2").text("用户增加");
        oldName = "";
        oldEmail = "";
    });
}

// checkbox 事件监听
function checkboxAll() {
    $("#ckAll").change(function (){
        const checkedFlag =  $("#ckAll").is(':checked');
        let checkboxByAll = $("td input[type='checkbox']");
        if(checkedFlag) {
            checkboxByAll.each(function (){
                $(this).prop("checked",true);
            });
        } else {
            checkboxByAll.each(function (){
                $(this).prop("checked",false);
            });
        }
    });
}

// 修改用户信息
function updateUserByOne() {
    $('table').on('click','#btnUpdate',function (){
        const user = $(this).attr('value');
        registerClear();
        $("#action").text("update");
        $("h2").text("用户修改");
        console.log(user);
        let user_info = JSON.parse(user);
        $("#userName_Attribute").val(user_info.userName);
        $("#userChrName_Attribute").val(user_info.chrName);
        $("#emailAddress_Attribute").val(user_info.emailAddress);
        $("#userPassword_Attribute").val(user_info.password);
        $("#confirmPassword_Attribute").val(user_info.password);
        $("#province").val(user_info.p_id);
        oldName = user_info.userName;
        oldEmail = user_info.emailAddress;
        oldPwd = user_info.password;
        $.ajax({
            type: "post",
            url: "queryProvinceCity.do",
            data: {provinceCode:user_info.p_id},
            dataType: "json",
            success : function (response) {
                let cityElement = document.getElementById("city");
                cityElement.options.length = 0;
                cityElement.add(new Option("请选择城市",""));
                for(let index = 0;index < response.length;index++) {
                    cityElement.add(new Option(response[index].city,response[index].c_id));
                }
                $("#city").val(user_info.c_id);
            }
        });
        isName=true;
        isChrName=true;
        isEmail=true;
        isProvince=true;
        isCity=true;
        isPwd=true;
        isConfrimPwd=true;
    });
}

// 删除选中用户
function deleteCheckedUser() {
    $("#btDelete").click(function (){
        let len = $('tbody tr input:checkbox:checked').length;
        if(len === 0) {
            alert("至少需要选择一项！")
            return;
        }
        let vals = [];
        $('tbody tr input:checkbox:checked').each(function (index, item) {
            vals.push($(this).val());
        });
        userParamsByObj['userName'] = vals.join(",");
        $.ajax({
            type: "POST",
            url: "userManager.do",
            data: {state:"deleteUser",queryParams:JSON.stringify(userParamsByObj),pageParams:JSON.stringify(pageParamsByObj)},
            dataType: "json",
            success: function (response) {
                alert(response.info);
                if(response.code === "0") {
                    reload();
                }
            }
        });
    });
}

// 删除单条用户
function deleteUserByOne() {
    $('table').on('click',"#btnDel",function (){
        userParamsByObj['userName'] = $(this).attr('value');
        $.ajax({
            type: "POST",
            url: "userManager.do",
            data: {state:"deleteUser",queryParams:JSON.stringify(userParamsByObj),pageParams:JSON.stringify(pageParamsByObj)},
            dataType: "json",
            success: function (response) {
                alert(response.info);
                if(response.code === "0") {
                    reload();
                }
            }
        });
    });
}

// 弹出隐藏层
function ShowDiv(show_div,bg_div) {
    const show = $("#"+show_div);
    const bg = $("#"+bg_div);
    show.css('display','block');
    bg.css('display','block');

    // 弹出层居中
    let windowHeight = $(window).height();  // 获取当前窗口高度
    let windowWidth = $(window).width();    // 获取当前窗口宽度
    let popupHeight = show.height(); // 获取弹出层高度
    let popupWeight = show.width();  // 获取弹出层宽度
    let posiTop = (windowHeight - popupHeight) / 2 ;
    let posiLeft = (windowWidth - popupWeight) / 2;
    show.css({"left":posiLeft+"px","top":posiTop+"px","display":"block"});
}

// 关闭弹出层
function CloseDiv(show_div, bg_div) {
    const show = $("#"+show_div);
    const bg = $("#"+bg_div);
    show.css('display','none');
    bg.css('display','none');
}

// 清空条件查询
function clearSelected() {
    $("#btClear").click(function (){
        let userName = $("input[name='userName']");
        userName.val("");
        let chrName = $("input[name='chrName']");
        chrName.val("");
        let emailAddress = $("input[name='emailAddress']");
        emailAddress.val("");
        let provinceName = $("input[name='provinceName']");
        provinceName.val("");
        userParamsByObj['userName'] = userName.val();
        userParamsByObj['chrName'] = chrName.val();
        userParamsByObj['emailAddress'] = emailAddress.val();
        userParamsByObj['provinceName'] = provinceName.val();
        pageParamsByObj['pageNumber'] = 1;
        requestForAjaxByPage();
    });
}

// 根据动态参数查询用户
function selectedUserByWhere() {
    $("#btSearch").click(function (){
        const userName = $("input[name='userName']").val();
        const chrName = $("input[name='chrName']").val();
        const emailAddress = $("input[name='emailAddress']").val();
        const provinceName = $("input[name='provinceName']").val();
        // 输入框属性设置到对象中
        userParamsByObj['userName'] = userName;
        userParamsByObj['chrName'] = chrName;
        userParamsByObj['emailAddress'] = emailAddress;
        userParamsByObj['provinceName'] = provinceName;
        pageParamsByObj['pageNumber'] = 1;
        requestForAjaxByPage();
    });
}

// 选择显示的页面
function selectedShowPage() {
    const pageNumberDoc = $("#pageNumber");
    const pageCountDoc = $("#pageCount");

    $("#first").click(function (){
        pageParamsByObj['pageNumber'] = 1;
        requestForAjaxByPage();    // 页面 Ajax 请求
    });
    $("#back").click(function (){
        let pageNumber = eval(pageNumberDoc.text());
        if(pageNumber > 1) pageNumber = pageNumber - 1;
        pageParamsByObj['pageNumber'] = pageNumber;
        requestForAjaxByPage();    // 页面 Ajax 请求
    });
    $("#next").click(function (){
        let pageNumber = eval(pageNumberDoc.text());
        const pageCount = eval(pageCountDoc.text());
        if(pageNumber < pageCount) pageNumber += 1;
        pageParamsByObj['pageNumber'] = pageNumber;
        requestForAjaxByPage();    // 页面 Ajax 请求
    });
    $("#last").click(function (){
        const pageCount = eval(pageCountDoc.text());
        pageParamsByObj['pageNumber'] = pageCount;
        requestForAjaxByPage();    // 页面 Ajax 请求
    });
}

// 监听每页显示条数选择
function selectedEachPageByDataShow() {
    $("#pageSize").change(function (){
        const pageSize = eval($("#pageSize option:selected").text());
        pageParamsByObj['pageSize'] = pageSize;
        requestForAjaxByPage();
    });
}

// 自动查询填充用户信息
function fillUserInfo() {
    const userName = $("input[name='userName']").val();
    const chrName = $("input[name='chrName']").val();
    const emailAddress = $("input[name='emailAddress']").val();
    const provinceName = $("input[name='provinceName']").val();
    // 输入框属性拼装成一个 JSON 对象
    let queryParams = {
        "userName":userName,
        "chrName":chrName,
        "emailAddress":emailAddress,
        "provinceName":provinceName
    };
    userParamsByObj = queryParams;
    // 将页面拼装成一个 JSON 对象
    const pageSize = $("#pageSize option:selected").text();
    const pageNumber =  $("#pageNumber").text();
    let pageParams = {"pageSize":pageSize,
        "pageNumber":pageNumber,
        "sort":"userName",
        "order":"DESC"};
    pageParamsByObj = pageParams;
    requestForAjaxByPage();
}

// 页面 Ajax 请求
function requestForAjaxByPage() {
    $.ajax({
        type: "POST",
        url: "userManager.do",
        data: {state:"queryUserByStandard",queryParams:JSON.stringify(userParamsByObj),pageParams:JSON.stringify(pageParamsByObj)},
        dataType: "json",
        success: function (response) {
            HTMLOfDynamic(response);    // 动态生成列表的封装
            if(response.total === 0) {
                $("#pageNumber").text(0);
            } else {
                $("#pageNumber").text(pageParamsByObj.pageNumber);
            }
        }
    });
}

// 动态生成列表的封装
function HTMLOfDynamic(response) {
    $("tbody").empty();
    const rows = response.rows;
    const total = response.total;
    const pageSize = $("#pageSize option:selected").text();
    const pageCount = Math.ceil(total / pageSize); // 计算总页数
    $("#total").text(total);
    $("#pageCount").text(pageCount);    // 总页数
    $.each(rows,function (index,row){
        let s = JSON.stringify(row);
        let str = "<tr data='"+ s +"'>";
        str = str + '<td><input type="checkbox" value=' + row.userName+ ' /></td>';
        str = str + '<td>' + row.userName + '</td>';
        str = str + '<td>' + row.chrName + '</td>';
        str = str + '<td>' + row.emailAddress + '</td>';
        str = str + '<td>' + row.provinceName + '</td>';
        str = str + '<td>' + row.cityName + '</td>';
        str = str + '<td><a href="#" id="btnDel" value=' + row.userName + '>删除</a>';
        str = str + '<a href="#" value='+s+' id="btnUpdate" onclick="ShowDiv(\'MyDiv\',\'fade\')">修改</a></td>';
        str = str + '</tr>';
        $("tbody").append(str);
    });
    // 通过jQuery控制表格隔行换色，并鼠标悬停变色。
    $('tbody tr:even').addClass('tr_even');
    $('tbody tr:odd').addClass('tr_odd');
    $("tbody a").addClass('tr_a');
    $('tbody a').on("mouseover","tr",function (){
        $(this).addClass('tr_ahover');
    });
    $('tbody a').on("mouseout","tr",function (){
        $(this).removeClass('tr_ahover');
    });
    $("tbody").addClass('tb');
    $("tbody").on("mouseover","tr",function (){
        $(this).addClass('tr_hover');
    });
    $("tbody").on("mouseout","tr",function (){
        $(this).removeClass('tr_hover');
    });
}

function register() {
    fillProvince();    // 自动填充省份
    controlsListener();   // 监听控件
    registerSend();      // 提交注册请求

    // 通过选择的省份，填充响应的城市
    $("#province").change(function (e) {
        $("#city").empty();
        $("#city").append($("<option>").val("").text("请选择城市"));
        if ($(this).val() === "") {
            $("#provinceError").text("必须选择省份！");
            return;
        }
        $("#provinceError").text("");
        const provinceCode = $("#province").val();
        $.ajax({
            type: "post",
            url: "queryProvinceCity.do",
            data: {provinceCode:provinceCode},
            dataType: "json",
            success : function (response) {
                let cityElement = document.getElementById("city");
                cityElement.options.length = 0;
                cityElement.add(new Option("请选择城市",""));
                for(let index = 0;index < response.length;index++) {
                    cityElement.add(new Option(response[index].city,response[index].c_id));
                }
            }
        });
    });

// 未选城市的提示
    $("#city").change(function (e) {
        if ($(this).val() === "") {
            $("#cityError").text("必须选择城市！");
        } else {
            $("#cityError").text("");
        }
    });
}

// 提交请求信息
function registerSend() {
    $("#btn_summit").click(function () {
        if(isName && isChrName && isEmail && isProvince && isCity && isPwd && isConfrimPwd) {
            const userName = $("#userName_Attribute").val();
            const userPassword = $("#confirmPassword_Attribute").val();
            const chrName = $("#userChrName_Attribute").val();
            const emailAddress = $("#emailAddress_Attribute").val();
            const province = $("#province option:selected").val();
            const city = $("#city option:selected").val();
            const action =  $("#action").text();
            if(action === "insert") oldName = "";
            let user = {
                userName:userName,
                password:userPassword,
                chrName:chrName,
                emailAddress:emailAddress,
                p_id:province,
                c_id:city
            }
            let pwdIsChange;
            if(oldPwd === userPassword) pwdIsChange = false;
            else pwdIsChange = true;
            $.ajax({
                type: "POST",
                url: "userManager.do",
                data: {state:action,queryParams:JSON.stringify(user),oldName:oldName,pwdIsChange:pwdIsChange},
                dataType: "json",
                success : function (response) {
                    alert(response.info);
                    console.log(response);
                    if(response.code === "0") {
                        reload();
                        CloseDiv('MyDiv','fade');
                    }
                }
            });
        } else {
            alert("请检查各选项是否有误!");
        }
    });
}

// 监听控件
function controlsListener() {
    // 用户名进行效验
    $("#userName_Attribute").blur(function () {
        const userName = $("#userName_Attribute").val();
        const error_text = $("#userNameError");
        if (userName === "") {
            isName = false;
            error_text.text("用户名不能为空");
            error_text.css("color","#FA8072");
        } else {
            let champter = /^[A-Za-z]+[A-Za-z0-9]{3,14}$/;
            if(champter.test(userName)) {
                $.ajax({
                    type: "get",
                    url: "Login.do",
                    data: {userName:userName},
                    dataType: "json",
                    success : function (response) {
                        if(userName !== oldName) {
                            if(response) {
                                error_text.text("该用户已存在");
                                error_text.css("color","#FA8072");
                                isName = false;
                            } else {
                                error_text.text("输入字符合法");
                                error_text.css("color","#2DFF77");
                                isName = true;
                            }
                        } else {
                            error_text.text("输入字符合法");
                            error_text.css("color","#2DFF77");
                            isName = true;
                        }
                    }
                });
            } else {
                error_text.text("只能输入字母和数字，以字母开头，长度为4到15个字符");
                error_text.css("color","#FA8072");
                isName = false;
            }
        }
    });

    // 用户真实姓名进行效验
    $("#userChrName_Attribute").blur(function () {
        const userChrName = $("#userChrName_Attribute").val();
        const error_text = $("#chrNameError");
        if(userChrName === "") {
            error_text.text("真实姓名不能为空");
            error_text.css("color","#FA8072");
            isChrName = false;
        } else {
            const champter = /^[\u4e00-\u9fa5]{2,4}$/;
            if(champter.test(userChrName)) {
                error_text.text("输入中文字符合法");
                error_text.css("color","#2DFF77");
                isChrName = true;
            } else {
                error_text.text("请输入长度在（2-4）内的中文姓名");
                error_text.css("color","#FA8072");
                isChrName = false;
            }
        }
    });

    // 邮箱进行效验
    $("#emailAddress_Attribute").blur(function () {
        const userEmail = $("#emailAddress_Attribute").val();
        const error_text = $("#emailError");
        if(userEmail === "") {
            error_text.text("邮箱不能为空");
            error_text.css("color","#FA8072");
            isEmail = false;
        } else {
            let champter = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
            if(champter.test(userEmail)) {
                $.ajax({
                    type: "get",
                    url: "Login.do",
                    data: {userEmail:userEmail},
                    dataType: "json",
                    success : function (response) {
                        if(oldEmail !== userEmail){
                            if(response) {
                                error_text.text("该邮箱已存在");
                                error_text.css("color","#FA8072");
                                isEmail = false;
                            } else {
                                error_text.text("输入邮箱地址合法");
                                error_text.css("color","#2DFF77");
                                isEmail = true;
                            }
                        } else {
                            error_text.text("输入邮箱地址合法");
                            error_text.css("color","#2DFF77");
                            isEmail = true;
                        }
                    }
                });
            } else {
                error_text.text("请输入合法的邮箱地址");
                error_text.css("color","#FA8072");
                isEmail = false;
            }
        }
    });

    // 省份选择效验
    $("#province").blur(function () {
        const province = $("#province option:selected").val();
        const error_text = $("#provinceError");
        if(province !== "") {
            error_text.text("合法选择");
            error_text.css("color","#2DFF77");
            isProvince = true;
        } else {
            error_text.text("必须选择省份！");
            error_text.css("color","#FA8072");
            isProvince = false;
        }
    });

    // 城市选择效验
    $("#city").blur(function () {
        const city = $("#city option:selected").val();
        const error_text = $("#cityError");
        if(city !== "") {
            error_text.text("合法选择");
            error_text.css("color","#2DFF77");
            isCity = true;
        } else {
            error_text.text("必须选择城市！");
            error_text.css("color","#FA8072");
            isCity = false;
        }
    });

    // 密码进行效验
    $("#userPassword_Attribute").blur(function () {
        const userPassword = $("#userPassword_Attribute").val();
        const error_text = $("#passwordError");
        if(userPassword === "") {
            error_text.text("密码长度至少为4");
            error_text.css("color","#FA8072");
            isPwd = false;
        } else {
            if(userPassword.length>=4) {
                error_text.text("输入字符合法");
                error_text.css("color","#2DFF77");
                isPwd = true;
            } else {
                error_text.text("密码长度至少为4");
                error_text.css("color","#FA8072");
                isPwd = false;
            }
        }
    });

    // 确认密码进行效验
    $("#confirmPassword_Attribute").blur(function () {
        const  confirmPassword = $("#confirmPassword_Attribute").val();
        const error_text = $("#confirmError");
        if(confirmPassword === "") {
            error_text.text("密码不一致或长度不够");
            error_text.css("color","#FA8072");
            isConfrimPwd = false;
        } else {
            if($("#userPassword_Attribute").val() === confirmPassword) {
                error_text.text("输入字符合法");
                error_text.css("color","#2DFF77");
                isConfrimPwd = true;
            } else {
                error_text.text("密码不一致或长度不够");
                error_text.css("color","#FA8072");
                isConfrimPwd = false;
            }
        }
    });
}

// 自动填充省份
function fillProvince() {
    $.ajax({
        type:"post",
        url: "queryProvinceCity.do",
        data: {},
        dataType: "json",
        success: function (response) {
            let provinceElement = document.getElementById("province");
            provinceElement.options.length = 0;
            provinceElement.add(new Option("请选择省份",""));
            for (let index = 0;index < response.length;index++) {
                provinceElement.add(new Option(response[index].province,response[index].p_id));
            }
        }
    });
}

// 清空弹出框内容信息
function registerClear() {
    isName=false;
    isChrName=false;
    isEmail=false;
    isProvince=false;
    isCity=false;
    isPwd=false;
    isConfrimPwd=false;
    $("#userName_Attribute").val("");
    $("#userNameError").text("");
    $("#userChrName_Attribute").val("");
    $("#chrNameError").text("");
    $("#emailAddress_Attribute").val("");
    $("#emailError").text("");
    $("#userPassword_Attribute").val("");
    $("#passwordError").text("");
    $("#confirmPassword_Attribute").val("");
    $("#confirmError").text("");
    $("#provinceError").text("");
    $("#cityError").text("");
}

// 刷新页面
function reload() {
    let queryParams = {
        "userName":"",
        "chrName":"",
        "emailAddress":"",
        "provinceName":""
    };
    userParamsByObj = queryParams;
    requestForAjaxByPage();
}
