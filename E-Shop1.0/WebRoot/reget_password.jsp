<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>E-Shop网 - 找回密码</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript">
	
	var msgBox; // 组件后的显示条
	// 获得组件后的显示条
	function FocusItem(obj){
		msgBox = document.getElementById(obj.name+"Span");
	}
	/**
	 * 通过用户账号获得其密码保护
	 */
	function getPwdQuestion(obj) {
		var xmlHttpRequest = false;
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
		xmlHttpRequest.open("POST", "checkUserForQuestion.userForeground", true);
		xmlHttpRequest.setRequestHeader("content-type",
				"application/x-www-form-urlencoded");
		xmlHttpRequest.onreadystatechange = function() {
			if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
				var result = xmlHttpRequest.responseText;
				// alert(result);
				if (result == "error") {
					msgBox.innerHTML = "<font color='red'>*用户名不存在！*</font>";
					msgBox.className = "error";
					return false;
				} else if (result == ""){
					msgBox.innerHTML = "<font color='red'>*系统正忙，请稍后与管理员联系……*</font>";
					msgBox.className = "error";
					return false;
				} else {
					msgBox.innerHTML = "<font color='green'>*用户名正确*</font>";
					msgBox.className = "true";
					document.getElementById("question").value=result;
					return true;
				}
			}
		};
		xmlHttpRequest.send("userName="+obj.value);
	}
</script>
</head>
<body>
	<jsp:include page="include/head.jsp"></jsp:include>
	<div id="register" class="wrap" align="center">
		<div class="shadow">
			<em class="corner lb"></em> <em class="corner rt"></em>
			<div class="box">
				<h1>找回密码</h1>
				<ul class="steps clearfix">
					<li class="current"><em></em>回答密保问题</li>
					<li class="last"><em></em>验证成功</li>
				</ul>
				<form id="regForm" method="post" action="getPassword.userForeground">
					<table>
						<tr>
							<td height="40" colspan="2"><span class="reg"></span>
							</td>
						</tr>
						<tr>
							<td class="field">用户名：</td>
							<td><input class="text" type="text" name="userName"
								id="userName" value="" onfocus="FocusItem(this)"
								onblur="getPwdQuestion(this);" /><span id="userNameSpan"></span>
							</td>
						</tr>


						<tr>
							<td class="field">问题：</td>
							<td><input class="text" type="text" name="question"
								id="question" readonly="readonly" /><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">答案：</td>
							<td><input class="text" type="text" name="anwser"
								id="anwser"/><span></span>
							</td>
						</tr>
						<tr align="center">
							<td></td>
							<td align="left"><label class="ui-green"><input
									type="submit" name="submit" value="提交" />
							</label> <label class="ui-green"><input type="reset" name="reset"
									value="重置" /> </label></td>
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
