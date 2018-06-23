<%@page contentType="text/html;charset=UTF-8"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>注册结果 - E-Shop</title>
		<link type="text/css" rel="stylesheet" href="../css/style.css" />
		<script type="text/javascript" src="../scripts/function-manage.js"></script>
	</head>
	<body>
		<jsp:include page="include/head-left.jsp"></jsp:include>
		<div class="main">
			<h2>
				管理页
			</h2>
			<div id="welcome" class="manage">
				<div class="shadow">
					<em class="corner lb"></em>
					<em class="corner rt"></em>
					<div class="box">
						<div class="msg">
							<p>
								恭喜：注册成功！
							</p>
							<p>
								正在进入管理员登录页...
							</p>
							<script type="text/javascript">
								setTimeout("location.href='index.jsp'", 3000);
							</script>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="clear"></div>
		</div>
		<jsp:include page="include/bottom.jsp"></jsp:include>
	</body>
</html>
