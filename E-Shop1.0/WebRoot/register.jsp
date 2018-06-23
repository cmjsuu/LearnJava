<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>E-Shop网 - 用户注册</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/user_register.js"></script>
<script type="text/javascript" src="js/verifyCode.js"></script>
<script type="text/javascript">
	// 获得验证后提示信息的显示框
	var userMsgBox;
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
		// 获得当前提示框
		userMsgBox = document.getElementById(obj.name+"Span");
	}
	/**
	 * 注册信息验证
	 * 
	 * @param obj
	 * @returns {Boolean}
	 */
	function CheckItem(obj) {
		var name = obj.name;
		var value = obj.value;
		// 得到XmlHttpRequest对象
		var xmlHttpRequest;
		try {
			xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e1) {
			try {
				xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e2) {
				xmlHttpRequest = false;
			}
		}
		if (!xmlHttpRequest && typeof XMLHttpRequest != "undefined") {
			xmlHttpRequest = new XMLHttpRequest();
		}
		switch (name) {
		case "userName":// ---------------验证用户名-----------------
			if (value == "") {
				userMsgBox.innerHTML = "用户名不能为空";
				userMsgBox.className = "error";
				verifyUser = false;
				return false;
			}
			xmlHttpRequest.onreadystatechange = function() {
				if (xmlHttpRequest.readyState == 4
						&& xmlHttpRequest.status == 200) {
					var msg = xmlHttpRequest.responseText;
					if (msg == "legal") {
						userMsgBox.innerHTML = "<font color='green'>*该用戶名可用*</font>";
						userMsgBox.className = "true";
						verifyUser = true;
						alert("userName:" + verifyUser);
					} else if (msg == "illegal") {
						userMsgBox.innerHTML = "<font color='red'>*用戶名必須是大於6小於15的數字或字母*</font>";
						userMsgBox.className = "error";
						verifyUser = false;
						return false;
					} else if (msg == "exist") {
						userMsgBox.innerHTML = "<font color='red'>*該用戶名重複了，請重新輸入...*</font>";
						userMsgBox.className = "error";
						verifyUser = false;
						return false;
					} else {
						userMsgBox.innerHTML = "<font color='yellow'>*系統繁忙，請稍後再試...*</font>";
						userMsgBox.className = "error";
						verifyUser = false;
						return false;
					}
					alert("userName:" + verifyUser);
				}
			};
			xmlHttpRequest.open("POST", "userCheck.userForeground", true);
			xmlHttpRequest.setRequestHeader("content-type",
					"application/x-www-form-urlencoded");
			xmlHttpRequest.send("userName=" + value);
			break;
		case "passWord":// 验证密码
			if (value == "") {
				userMsgBox.innerHTML = "密码不能为空";
				userMsgBox.className = "error";
				verifyPwd = false;
				return false;
			} else if (value.length < 6) {
				userMsgBox.innerHTML = "密码长度不能小于六";
				userMsgBox.className = "error";
				verifyPwd = false;
				return false;
			} else {
				userMsgBox.innerHTML = "<font color='green'>*密码正确*</font>";
				userMsgBox.className = "true";
				verifyPwd = true;
			}
			alert("passWord:" + verifyPwd);
			break;
		case "rePassWord":// 验证确认密码
			if (value != document.getElementById("passWord").value) {
				userMsgBox.innerHTML = "两次输入的密码不相同";
				userMsgBox.className = "error";
				verifyPwdAgain = false;
				return false;
			} else {
				userMsgBox.innerHTML = "<font color='green'>*确认密码正确*</font>";
				userMsgBox.className = "true";
				verifyPwdAgain = true;
			}
			alert("rePassWord:" + verifyPwdAgain);
			break;
		case "nickname":// -----------------验证用户昵称-------------------
			if (value == "") {
				userMsgBox.innerHTML = "用户昵称不能为空";
				userMsgBox.className = "error";
				verifyNickname = false;
				return false;
			}
			xmlHttpRequest.onreadystatechange = function() {
				if (xmlHttpRequest.readyState == 4
						&& xmlHttpRequest.status == 200) {
					var msg = xmlHttpRequest.responseText;
					if (msg == "legal") {
						userMsgBox.innerHTML = "<font color='green'>*用户昵称可用*</font>";
						userMsgBox.className = "true";
						verifyNickname = true;
					} else if (msg == "exist") {
						userMsgBox.innerHTML = "<font color='red'>*該用戶昵称已存在，請重新輸入...*</font>";
						userMsgBox.className = "error";
						verifyNickname = false;
						return false;
					} else {
						userMsgBox.innerHTML = "<font color='yellow'>*系統繁忙，請稍後再試...*</font>";
						userMsgBox.className = "error";
						verifyNickname = false;
						return false;
					}
					alert("nickname:" + verifyNickname);
				}
			};
			xmlHttpRequest.open("POST", "nicknameCheck.userForeground", true);
			xmlHttpRequest.setRequestHeader("content-type",
					"application/x-www-form-urlencoded");
			xmlHttpRequest.send("nickname=" + value);
			break;
		case "address": // 验证用户地址
			if (value == "") {
				userMsgBox.innerHTML = "用户地址不能为空";
				userMsgBox.className = "error";
				verifyAddress = false;
				return false;
			} else {
				userMsgBox.innerHTML = "<font color='green'>*用户地址可用*</font>";
				userMsgBox.className = "true";
				verifyAddress = true;
			}
			alert("address:" + verifyAddress);
			break;
		case "connection": // 验证用户电话号码
			if (value == "") {
				userMsgBox.innerHTML = "电话号码不能为空";
				userMsgBox.className = "error";
				verifyPhone = false;
				return false;
			} else if (value.lenght > 13 || value.length < 7) {
				userMsgBox.innerHTML = "电话号码长度不合法";
				userMsgBox.className = "error";
				verifyPhone = false;
				return false;
			} else if (isNaN(value)) {
				userMsgBox.innerHTML = "联系方式不能为非数字";
				userMsgBox.className = "error";
				verifyPhone = false;
				return false;
			} else {
				userMsgBox.innerHTML = "<font color='green'>*用户电话号码可用*</font>";
				userMsgBox.className = "true";
				verifyPhone = true;
			}
			alert("connection:" + verifyPhone);
			break;
		case "email": // -----------------验证用户电邮地址-------------------
			if (value == "") {
				userMsgBox.innerHTML = "电子邮箱不能为空";
				userMsgBox.className = "error";
				verifyMail = false;
				return false;
			}
			xmlHttpRequest.onreadystatechange = function() {
				if (xmlHttpRequest.readyState == 4
						&& xmlHttpRequest.status == 200) {
					var msg = xmlHttpRequest.responseText;
					if (msg == "legal") {
						userMsgBox.innerHTML = "<font color='green'>*电子邮箱可用*</font>";
						userMsgBox.className = "true";
						verifyMail = true;
					} else if (msg == "illegal") {
						userMsgBox.innerHTML = "<font color='red'>*该邮箱地址不可用，請重新輸入...*</font>";
						userMsgBox.className = "error";
						verifyMail = false;
						return false;
					} else {
						userMsgBox.innerHTML = "<font color='yellow'>*系統繁忙，請稍後再試...*</font>";
						userMsgBox.className = "error";
						verifyMail = false;
						return false;
					}
					alert("email:" + verifyMail);
				}
			};
			xmlHttpRequest.open("POST", "mailCheck.userForeground", true);
			xmlHttpRequest.setRequestHeader("content-type",
					"application/x-www-form-urlencoded");
			xmlHttpRequest.send("mail=" + value);
			break;
		}
		// 当上述验证全部成功
		if (verifyUser == true && verifyPwd == true && verifyPwdAgain == true
				&& verifyNickname == true && verifyAddress == true 
				&& verifyPhone == true && verifyMail == true) {
			regResult = true;
			return true;
		}
		alert("注册信息验证结果：" + regResult);
		return false;
	}

	var result = false;
	// 当用户信息与验证码都通过验证时
	if (regResult == true && verifyResult == true) {
		result = true;
	}
</script>
</head>
<body>
	<jsp:include page="include/head.jsp"></jsp:include>
	<div id="register" class="wrap">
		<div class="shadow">
			<em class="corner lb"></em> <em class="corner rt"></em>
			<div class="box">
				<h1>欢迎注册E-Shop网</h1>
				<ul class="steps clearfix">
					<li class="current"><em></em>填写注册信息</li>
					<li class="last"><em></em>注册成功</li>
				</ul>
				<form id="regForm" method="post"
					action="userRegisterDo.userForeground" onsubmit="true">
					<table>
						<tr>
							<td height="40" colspan="1" valign="bottom">
								<div align="right">
									<h3>基本资料</h3>
								</div>
							</td>
						</tr>
						<tr>
							<td class="field">用户账号：</td>
							<td><input class="text" type="text" name="userName"
								id="userName" onfocus="FocusItem(this)"
								onblur="CheckItem(this);" /> 
								<span id="userNameSpan"></span>
							</td>
						</tr>
						<tr>
							<td class="field">登录密码：</td>
							<td><input class="text" type="password" id="passWord"
								name="passWord" onfocus="FocusItem(this)"
								onblur="CheckItem(this);" />
								<span id="passWordSpan"></span>
							</td>
						</tr>
						<tr>
							<td class="field">确认密码：</td>
							<td><input class="text" type="password" name="rePassWord"
								onfocus="FocusItem(this)" onblur="CheckItem(this);" />
								<span id="rePassWordSpan"></span>
							</td>
						</tr>
						<tr>
							<td class="field">用户昵称：</td>
							<td><input class="text" type="text" name="nickname"
								onfocus="FocusItem(this)" onblur="CheckItem(this);" /> 
								<span id="nicknameSpan"></span>
							</td>
						</tr>
						<tr>
							<td class="field">性別：</td>
							<td>
								<input class="radio" type="radio" name="sex" value="男" checked="checked" />男
								&nbsp;&nbsp;&nbsp;&nbsp; 
								<input class="radio" type="radio" name="sex" value="女" /> 女
							</td>
						</tr>
						<tr>
							<td class="field">出生日期：</td>
							<td><input class="text" type="text" name="birthday"/></td>
						</tr>
						<tr>
							<td class="field">用户地址：</td>
							<td>
								<input class="text" type="text" name="address"
								onfocus="FocusItem(this)" onblur="CheckItem(this);" />
								<span id="addressSpan"></span>
							</td>
						</tr>
						<tr>
							<td class="field">电话号码：</td>
							<td>
								<input class="text" type="text" name="connection"
								onfocus="FocusItem(this)" onblur="CheckItem(this);" />
								<span id="connectionSpan"></span>
							</td>
						</tr>
						<tr>
							<td class="field">电子邮箱：</td>
							<td>
								<input class="text" type="text" name="email"
								onfocus="FocusItem(this)" onblur="CheckItem(this);" />
								<span id="emailSpan"></span>
							</td>
						</tr>
						<tr>
							<td class="field">密保问题设置：</td>
							<td><input class="text" type="text" name="question"
								id="question1" onfocus="FocusItem(this)"
								onblur="CheckItem(this);" /><span></span></td>
						</tr>
						<tr>
							<td class="field">答案：</td>
							<td><input class="text" type="text" name="anwser"
								id="anser1" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">验证码：</td>
							<td><input class="text verycode" type="text" name="veryCode"
								onfocus="VerifyOnItem(this)" onblur="VerifyOutItem(this);" /> <img
								src="login.makeCertPic" /><span></span>
							</td>
						</tr>
						<tr>
							<td></td>
							<td><label class="ui-green"> <input type="submit"
									name="submit" value="提交注册" /> </label> <label class="ui-green">
									<input type="reset" name="reset" value="重置信息" /> </label>
							</td>
							<td></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="include/bottom.jsp"></jsp:include>
</body>
</html>
