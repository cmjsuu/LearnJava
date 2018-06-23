<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>E-Shop - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
<jsp:include page="include/head.jsp"></jsp:include>
<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>欢迎注册E-Shop</h1>
			<ul class="steps clearfix">
				<li class="finished"><em></em>回答密保问题</li>
				<li class="last-current"><em></em>验证成功</li>
			</ul>
			<div class="msg">
				<p>恭喜：密码成功找回！</p>
				<p>您的密码是<font size="4" color="red">${user.uPassword}</font>，请妥善保管！</p>
				<div ><a href="login.jsp">立刻登录〉〉</a></div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="include/bottom.jsp"></jsp:include>
</body>
</html>
