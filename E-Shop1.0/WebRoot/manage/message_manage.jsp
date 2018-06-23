<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="../WEB-INF/myTagLib.tld" prefix="myTag" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>留言管理 - 游戏</title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/function-manage.js"></script>
</head>
<body>
<jsp:include page="include/head-left.jsp"></jsp:include>
	<div class="main">
		<h2>留言管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>留言编号</th>
					<th>用户昵称</th>
					<th>留言标题</th>
					<th>留言内容</th>
					<th>留言时间</th>
					<th>是否回复</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${messageList}" var="message">
					<tr>
						<td class="w1 c">${message.messageID}</td>
						<td class="w1 c">${message.accounts}</td>
						<td class="w1 c">${message.messageTitle}</td>
						<td>${message.messageContent}</td>
						<td class="w1 c">${message.messagePubilshTime}</td>
						<td class="w1 c">
						<c:choose>
    					<c:when test="${message.messageState==1}">已回复
    					</c:when>
    					<c:when test="${message.messageState==0}">未回复
    					</c:when>
    					</c:choose>

						</td>
   						
						<td class="w4 c">
							<a href="messageDatailedView.nmm?mbId=${message.messageID}">查看</a>
							<a href="messageReplyView.nmm?mbId=${message.messageID}">回复</a> 
							<a href="messageDeleteDo.nmm?mbId=${message.messageID}">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<myTag:pageTag url="messageManageView.nmm"/>
		</div>
	</div>
	<div class="clear"></div>
</div>
<jsp:include page="include/bottom.jsp"></jsp:include>
</body>
</html>
