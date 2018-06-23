<%@page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	System.out.println("rootpath:" + basePath);
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>管理员注册- E-Shop</title>
		<link type="text/css" rel="stylesheet" href="../css/style.css" />

		<script type="text/javascript" src="../js/ajax.js"></script>
		<%
			System.out.println("##########=" + basePath + "js/ajax.js");
		%>
		<script type="text/javascript" src="../js/function.js"></script>
	</head>
	<body>
		<jsp:include page="include/head-left.jsp"></jsp:include>
		<div class="main">
			<h2>
				管理员注册
			</h2>
			<div id="register">
				<div class="shadow">
					<em class="corner lb"></em>
					<em class="corner rt"></em>
					<div class="box">
						<ul class="steps clearfix">
							<li class="currentManager">
								<em></em>填写注册信息
							</li>
							<li class="last">
								<em></em>注册成功
							</li>
						</ul>
						<form id="loginForm" method="post" action="addManager"
							onsubmit="checkForm2(this)">
							<table>
								<tr>
									<td class="field">
										用户名：
									</td>
									<td>
										<input class="text" type="text" id="mName" name="mName"
											onfocus="FocusItem2(this)" onblur="CheckItem2(this);" />
										<span></span>
									</td>
								</tr>
								<tr>
									<td class="field">
										登录密码：
									</td>
									<td>
										<input class="text" type="password" id="passWord"
											name="passWord" onfocus="FocusItem2(this)"
											onblur="CheckItem2(this);" />
										<span></span>
									</td>
								</tr>
								<tr>
									<td class="field">
										确认密码：
									</td>
									<td>
										<input class="text" type="password" name="rePassWord"
											onfocus="FocusItem2(this)" onblur="CheckItem2(this);" />
										<span></span>
									</td>
								</tr>

								<tr>
									<td class="field">
										验证码：
									</td>
									<td>
										<input class="text" type="text" name="certCode"
											onfocus="FocusItem2(this)" onblur="CheckItem2(this);" />
										<img src="register.makeCertPic">
										<span></span>
									</td>
								</tr>
								<tr>

									<td></td>
									<td>
										<label class="ui-green">
											<input type="submit" name="submit" value="提交注册" />
										</label>
									</td>
								</tr>
							</table>
						</form>

					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<jsp:include page="include/bottom.jsp"></jsp:include>
	</body>
</html>
