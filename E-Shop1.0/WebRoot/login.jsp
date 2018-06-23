<%@page contentType="text/html;charset=UTF-8"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>游戏管理后台 - 首页</title>
		<link type="text/css" rel="stylesheet" href="css/style.css" />
		<script type="text/javascript" src="js/verifyCode.js"></script>
	</head>
	<body>
		<div id="register" class="wrap">
			<div class="shadow">
				<em class="corner lb"></em>
				<em class="corner rt"></em>
				<div class="box">
					<h1>
						欢迎回到游戏管理后台 
					</h1>
					<form id="loginForm" method="post"
						action="userLoginDo.userForeground"
						onsubmit="verifyResult">
						<table>
							<tr>
								<td class="field">
									用户名：
								</td>
								<td>
									<input class="text" type="text" name="userName" />
								</td>
							</tr>
							<tr>
								<td class="field">
									登录密码：
								</td>
								<td>
									<input class="text" type="password" id="passWord" name="passWord" />
									<a href="reget_password.jsp">忘记密码了？</a>
								</td>
							</tr>
							<tr>
								<td class="field">
									验证码：
								</td>
								<td>
									<input class="veryCode" type="text" name="veryCode" maxlength="4" size="4" 
									onfocus="VerifyOnItem(this)" onblur="VerifyOutItem(this)" />
									<img src="login.makeCertPic"/><span></span>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<label class="ui-green">
										<input type="submit" name="submit" value="立即登录" />
									</label>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<jsp:include page="/include/bottom.jsp"></jsp:include>
	</body>
</html>
