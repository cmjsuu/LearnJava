<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="../WEB-INF/myTagLib.tld" prefix="myTag" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新闻管理 </title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/function-manage.js"></script>
</head>
<body>
<jsp:include page="include/head-left.jsp"></jsp:include>
	<div class="main">
		<h2>新闻管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>新闻ID</th>
					<th>新闻标题</th>
					<th>发布人</th>
					<th>发布时间</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${newsList}" var="news">
					<tr>
						<td class="w1 c">${news.newsID}</td>
						<td class="w1 c">${news.newsTitle}</td>
						<td class="w1 c">${news.manager}</td>
						<td class="w1 c">${news.newsPublishTime}</td>
						<td class="w5 c">
							<font size="2">
								<a href="newDetailedView.nmm?nId=${news.newsID}">查看</a>
								<a href="newsModifyView.nmm?nId=${news.newsID}">修改</a>
								<a href="newsDeleteDo.nmm?nId=${news.newsID}">删除</a>
								<input type="hidden" name="nId" value="${news.newsID}" />
							</font>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	<div class="clear"><myTag:pageTag url="newsManageView.nmm"/></div>
</div>
<jsp:include page="include/bottom.jsp"></jsp:include>
</body>
</html>
