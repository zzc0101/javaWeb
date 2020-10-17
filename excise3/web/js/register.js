var isName=false;
var isChrName=false;
var isEmail=false;
var isProvince=false;
var isCity=false;
var isPwd=false;
var isConfrimPwd=false;
$(document).ready(function () {
    fillProvince();    // 自动填充省份
    controlsListener();   // 监听控件
    registerSend();      // 提交注册请求
});

// 提交注册请求
function registerSend() {
    $("#btn_summit").click(function () {
        if(isName && isChrName && isEmail && isProvince && isCity && isPwd && isConfrimPwd) {
            const userName = $("#userName_Attribute").val();
            const userPassword = $("#confirmPassword_Attribute").val();
            const chrName = $("#userChrName_Attribute").val();
            const emailAddress = $("#emailAddress_Attribute").val();
            const province = $("#province option:selected").text();
            const city = $("#city option:selected").text();
            $.ajax({
                type: "post",
                url: "Register.do",
                data: {userName:userName,userPassword:userPassword,chrName:chrName,
                        emailAddress:emailAddress,province:province,city:city},
                dataType: "json",
                success : function (response) {
                    console.log(response);
                    if(response === "success") {
                        window.location.href='login.html';
                    } else {
                        alert(response);
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
                        if(response) {
                            error_text.text("该用户已存在");
                            error_text.css("color","#FA8072");
                            isName = false;
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
                        if(response) {
                            error_text.text("该邮箱已存在");
                            error_text.css("color","#FA8072");
                            isEmail = false;
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
                cityElement.add(new Option(response[index].city));
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