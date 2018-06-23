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
<div id="changepwd" class="wrap" align="center"">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>歡迎加入E-Shop</h1>
			<ul class="steps clearfix">
				<li class="finished"><em></em>修改密碼</li>
				<li class="last-current"><em></em>修改成功</li>
			</ul>
			<div class="msg">
				<p>恭喜：修改成功！</p>
				<p>正在返回個人主頁...</p>
				<script type="text/javascript">
					setTimeout("location.href='center.userInfo'", 2000);
				</script>
			</div>
		</div>
	</div>
</div>
<jsp:include page="include/bottom.jsp"></jsp:include>
</body>
</html>
