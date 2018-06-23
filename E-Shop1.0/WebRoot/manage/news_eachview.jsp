<%@page contentType="text/html;charset=UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="../WEB-INF/myTagLib.tld" prefix="myTag" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 </title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/function-manage.js"></script>
</head>
<body>
<jsp:include page="include/head-left.jsp"></jsp:include>
	<div class="main">
		<h2>查看新闻</h2>
		<div class="manage">
		<form action="newDetailedView.nmm">
				<h1 class="field">新闻标题：${news.newsTitle}</h1>
				
				<h3 class="field">新闻内容：</h3>
				<p class="nContent">${news.newsContent}</p>
		</form>
		</div>
	<div class="clear"></div>
</div>
<jsp:include page="include/bottom.jsp"></jsp:include>
</body>
</html>
