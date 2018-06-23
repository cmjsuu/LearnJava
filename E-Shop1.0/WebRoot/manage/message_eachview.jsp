<%@page contentType="text/html;charset=UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 游戏</title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/function-manage.js"></script>
</head>
<body>
<jsp:include page="include/head-left.jsp"></jsp:include>
	<div class="main">
		<h2>查看留言</h2>
		<div class="manage">
			<form action="messageDatailedView.nmm">
				<input type="hidden"  name="userId" value="${message.userID}" />
				<h3 class="field">用户账号：${message.accounts}</h3>
				
				<h3 class="field">留言内容：</h3>
				<p class="mbcontent">${message.messageContent}</p>
				<h3 class="field">留言时间：${message.messagePubilshTime}</h3>	
				<h3 class="field">回复内容：</h3>
				<p class="reply">${message.messageReply}</p>

			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<jsp:include page="include/bottom.jsp"></jsp:include>
</body>
</html>
