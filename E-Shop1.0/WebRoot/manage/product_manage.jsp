<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/myTagLib.tld" prefix="myTag"%>

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
		<h2>商品管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>ID</th>
					<th>商品名称</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${goodsList}" var="goods">
					<tr>
						<td class="first w4 c">${goods.goodsID}</td>
						<td class="thumb"><img src="../images/AccessoriesStore/${goods.goodsUrl}" height="60" width="60"/><a href="../goodsDetailShow.show?gId=${goods.goodsID}" target="_blank">${goods.goodsName}</a></td>
						<td class="w4 c"><a href="goodsModifyView.goods?gId=${goods.goodsID}">修改</a> <a href="goodsDeleteDo.goods?gId=${goods.goodsID}">删除</a></td>
					</tr>
				</c:forEach>
				<myTag:pageTag url="goodsManageView.goods"/>
			</table>
		</div>
	</div>
	<div class="clear"></div>
</div>
<jsp:include page="include/bottom.jsp"></jsp:include>
</body>
</html>
