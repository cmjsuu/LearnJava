// 获得验证后提示信息的显示框
var useruserMsgBox;
// 注册信息的验证结果
var regResult = false;

 var verifyUser = false; // 验证用户
 var verifyPwd = false; // 验证密码
 var verifyPwdAgain = false; // 验证确认密码
 var verifyNickname = false; // 验证昵称
 var verifyBirthday = false; // 验证出生日期
 var verifyAddress = false; // 验证用户地址
 var verifyPhone = false; // 验证联系方式
 var verifyMail = false; // 验证电子邮箱

/**
 * 聚焦到当前组件
 */
function FocusItem(obj) {
	alert("进入事件！！");
	obj.parentNode.parentNode.className = "current";
	// 获得当前提示框
	useruserMsgBox = obj.parentNode.getElementsByTagName("span")[0];
	// 写在提示框的内容引用
	useruserMsgBox.innerHTML = "";
	// 提示框的样式引用
	useruserMsgBox.className = "";
}
/**
 * 注册信息验证
 * 
 * @param obj
 * @returns {Boolean}
 */
function CheckItem(obj) {
	alert(name + "|" + value);
	var name = obj.name;
	var value = obj.value;

	// 得到XmlHttpRequest对象
	var xmlHttpRequest;
	if (window.XMLHttpRequest) {
		xmlHttpRequest = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if (!xmlHttpRequest && typeof XMLHttpRequest != "undefined") {
		xmlHttpRequest = new XMLHttpRequest();
	}
	
	switch (name) {
	case "userName":// ---------------验证用户名-----------------
		if (value == "") {
			useruserMsgBox.innerHTML = "用户名不能为空";
			useruserMsgBox.className = "error";
			verifyUser = false;
		}
		xmlHttpRequest.onreadystatechange = function() {
			if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
				var msg = xmlHttpRequest.responseText;
				if (msg == "legal") {
					useruserMsgBox.innerHTML = "<font color='green'>*该用戶名可用*</font>";
					useruserMsgBox.className = "error";
					verifyUser = true;
				} else if (msg == "illegal") {
					useruserMsgBox.innerHTML = "<font color='red'>*用戶名必須是大於6小於15的數字或字母*</font>";
					useruserMsgBox.className = "error";
					verifyUser = false;
				} else if (msg == "exist") {
					useruserMsgBox.innerHTML = "<font color='red'>*該用戶名重複了，請重新輸入...*</font>";
					useruserMsgBox.className = "error";
					verifyUser = false;
				} else {
					useruserMsgBox.innerHTML = "<font color='yellow'>*系統繁忙，請稍後再試...*</font>";
					useruserMsgBox.className = "error";
					verifyUser = false;
				}
			}
		};
		xmlHttpRequest.open("POST", "userCheck.userForeground", true);
		xmlHttpRequest.setRequestHeader("content-type",
				"application/x-www-form-urlencoded");
		xmlHttpRequest.send("userName=" + value);
		alert("userName:" + verifyUser);
		break;
	case "passWord":// 验证密码
		if (value == "") {
			useruserMsgBox.innerHTML = "密码不能为空";
			useruserMsgBox.className = "error";
			verifyPwd = false;
		} else if (value.length < 6) {
			useruserMsgBox.innerHTML = "密码长度不能小于六";
			useruserMsgBox.className = "error";
			verifyPwd = false;
		} else {
			userMsgBox.innerHTML = "<font color='green'>*密码正确*</font>";
			userMsgBox.className = "error";
			verifyPwd = true;
		}
		alert("passWord:" + verifyPwd);
		break;
	case "rePassWord":// 验证确认密码
		if (value != document.getElementById("passWord").value) {
			userMsgBox.innerHTML = "两次输入的密码不相同";
			userMsgBox.className = "error";
			verifyPwdAgain = false;
		} else {
			userMsgBox.innerHTML = "<font color='green'>*确认密码正确*</font>";
			userMsgBox.className = "error";
			verifyPwdAgain = true;
		}
		alert("rePassWord:" + verifyPwdAgain);
		break;
	case "nickname":// -----------------验证用户昵称-------------------
		if (value == "") {
			userMsgBox.innerHTML = "用户昵称不能为空";
			userMsgBox.className = "error";
			verifyNickname = false;
		}
		xmlHttpRequest.onreadystatechange = function() {
			if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
				var msg = xmlHttpRequest.responseText;
				if (msg == "legal") {
					userMsgBox.innerHTML = "<font color='green'>*用户昵称可用*</font>";
					userMsgBox.className = "error";
					verifyNickname = true;
				} else if (msg == "exist") {
					userMsgBox.innerHTML = "<font color='red'>*該用戶昵称已存在，請重新輸入...*</font>";
					userMsgBox.className = "error";
					verifyNickname = false;
				} else {
					userMsgBox.innerHTML = "<font color='yellow'>*系統繁忙，請稍後再試...*</font>";
					userMsgBox.className = "error";
					verifyNickname = false;
				}
			}
		};
		xmlHttpRequest.open("POST", "nicknameCheck.userForeground", true);
		xmlHttpRequest.setRequestHeader("content-type",
				"application/x-www-form-urlencoded");
		xmlHttpRequest.send("nickname=" + value);
		alert("nickname:" + verifyNickname);
		break;
	case "birthday": // 验证生日
		if (value == "") {
			userMsgBox.innerHTML = "出生日期不能为空";
			userMsgBox.className = "error";
			verifyBirthday = false;
		} else {
			verifyBirthday = true;
		}
		alert("birthday:" + verifyBirthday);
		break;
	case "address": // 验证用户地址
		if (value == "") {
			userMsgBox.innerHTML = "用户地址不能为空";
			userMsgBox.className = "error";
			verifyAddress = false;
			return verifyAddress;
		} else {
			userMsgBox.innerHTML = "<font color='green'>*用户地址可用*</font>";
			userMsgBox.className = "error";
			verifyAddress = true;
			return verifyAddress;
		}
		alert("address:" + verifyAddress);
		break;
	case "connection": // 验证用户电话号码
		if (value == "") {
			userMsgBox.innerHTML = "电话号码不能为空";
			userMsgBox.className = "error";
			verifyPhone = false;
		} else if (value.lenght > 13 || value.length < 7) {
			userMsgBox.innerHTML = "电话号码长度不合法";
			userMsgBox.className = "error";
			verifyPhone = false;
		} else if (isNaN(value)) {
			userMsgBox.innerHTML = "联系方式不能为非数字";
			userMsgBox.className = "error";
			verifyPhone = false;
		} else {
			userMsgBox.innerHTML = "<font color='green'>*用户电话号码可用*</font>";
			userMsgBox.className = "error";
			verifyPhone = true;
		}
		alert("connection:" + verifyPhone);
		break;
	case "email": // -----------------验证用户电邮地址-------------------
		if (value == "") {
			userMsgBox.innerHTML = "电子邮箱不能为空";
			userMsgBox.className = "error";
			verifyMail = false;
		} 
		xmlHttpRequest.onreadystatechange = function() {
			if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
				var msg = xmlHttpRequest.responseText;
				if (msg == "legal") {
					userMsgBox.innerHTML = "<font color='green'>*电子邮箱可用*</font>";
					userMsgBox.className = "error";
					verifyMail = true;
				} else if (msg == "illegal") {
					userMsgBox.innerHTML = "<font color='red'>*该邮箱地址不可用，請重新輸入...*</font>";
					userMsgBox.className = "error";
					verifyMail = false;
				} else {
					userMsgBox.innerHTML = "<font color='yellow'>*系統繁忙，請稍後再試...*</font>";
					userMsgBox.className = "error";
					verifyMail = false;
				}
			}
		};
		xmlHttpRequest.open("POST", "mailCheck.userForeground", true);
		xmlHttpRequest.setRequestHeader("content-type",
				"application/x-www-form-urlencoded");
		xmlHttpRequest.send("mail=" + value);
		alert("email:" + verifyMail);
		break;
	}
	// 当上述验证全部成功
	if (verifyUser == true && verifyPwd == true && 
			verifyPwdAgain == true && verifyNickname == true &&
			verifyBirthday == true && verifyAddress == true && 
			verifyPhone == true && verifyMail == true) {
		regResult = true;
		return true;
	}
	alert("注册信息验证结果：" + regResult);
	return false;
}








