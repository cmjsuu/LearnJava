<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/myTagLib.tld" prefix="myTag"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理</title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/function-manage.js"></script>
</head>
<body>
<jsp:include page="include/head-left.jsp"></jsp:include>
	<div class="main">
		<h2>分类管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>ID</th>
					<th>分类名称</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${typeList}" var="type">
					<tr>
						<td class="first w4 c">${type.goodsTypeID}</td>
						<td>${type.goodsTypeName}</td>
						<td class="w4 c"><a href="typeModifyView.type?stId=${type.goodsTypeID}">修改</a>
						 <a href="typeDeleteDo.type?stId=${type.goodsTypeID}">删除</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<myTag:pageTag url="typeManageView.type"/>
	<div class="clear"></div>

<jsp:include page="include/bottom.jsp"></jsp:include>
</body>
</html>
