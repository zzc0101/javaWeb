var xmlHttp;
window.onload = function() {
	changeImage();		// 点击修改验证码
	style_PShow();		// 鼠标滑过显示提示信息
	inputBlur();		// 添加 blur 事件
	loginRequest();		// 登录 AJAX 请求
}

// 登录 AJAX 请求
function loginRequest() {
	const btn_login = document.getElementById("btn_login");
	btn_login.onclick = function () {
		const userName = document.getElementById("nameAndPassword_name").value;
		const userPassword = document.getElementById("nameAndPassword_pwd").value;
		const Verify_input = document.getElementById("Verify_input").value;
		const radio_check = document.getElementById("radio_check").checked;

		createXMLHTTP();    // 创建一个 AJAX 对象
		xmlHttp.open("post","Login.do",true);
		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		// const data = "userName="+userName+"&userPassword="+userPassword+"&Verify_input="+Verify_input+"&radio_check="+radio_check;
		xmlHttp.send("userName="+userName+"&userPassword="+userPassword+"&Verify_input="+Verify_input+"&radio_check="+radio_check);
		xmlHttp.onreadystatechange = function(){
			if(xmlHttp.readyState === 4 && xmlHttp.status ===200) {
				const response = xmlHttp.responseText;
				console.log(response);
				const json = JSON.parse(response);
				if(json.code === 1) {
					window.location.href='main.jsp';
				} else {
					let error_info = document.getElementById("error_info");
					error_info.innerHTML = json.info;
					error_info.style.display = 'block';
				}
			}
		}
	}
}

// 创建一个 AJAX 对象
function createXMLHTTP() {
	if(window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
}

// 添加 blur 事件
function inputBlur() {
	const nameAndPassword_name = document.getElementById("nameAndPassword_name");
	const nameAndPassword_pwd = document.getElementById("nameAndPassword_pwd");
	const Verify_input = document.getElementById("Verify_input");

	nameAndPassword_name.onblur = function () {
		let userName = nameAndPassword_name.value;
		const error_name = document.getElementById("error_name");
		if(userName === "") {
			error_name.style.display = 'block';
		} else {
			error_name.style.display = 'none';
		}
	}

	nameAndPassword_pwd.onblur = function () {
		let userPwd = nameAndPassword_pwd.value;
		const error_pwd = document.getElementById("error_pwd");
		if(userPwd === "") {
			error_pwd.style.display = 'block';
		} else {
			error_pwd.style.display = 'none';
		}
	}

	Verify_input.onblur = function () {
		let verify = Verify_input.value;
		const error_verify = document.getElementById("error_verify");
		if(verify === "") {
			error_verify.style.display = 'block';
		} else {
			error_verify.style.display = 'none';
		}
	}

}

// 点击修改验证码
function changeImage() {
	const img_change = document.getElementById("img_btn");
	img_change.onclick = function() {
		img_change.src='createVerifyImage.do?i='+Math.random();
	}
}

// 鼠标滑过显示提示信息
function style_PShow() {
	const show = document.getElementById("img_btn");
	const p_show = document.getElementById("p_show");
	show.onmousemove = function() {
		p_show.style.display='block';
	}
	show.onmouseout = function() {
		p_show.style.display='none';
	}
}