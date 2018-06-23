<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
		<h2>修改分类</h2>
		<div class="manage">
			<form action="typeModifyDo.type">
				<table class="form">
					<tr>
						<td class="field">分类名称：</td>
						<td><input type="text" class="text" name="mainType" value="${type.goodsTypeName}"/></td>
						<td><input type="hidden" name="stId" value="${type.goodsTypeID}" /></td>
					</tr>
					
					<tr>
						<td><label class="ui-blue"><input type="submit" name="submit" value="更新" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<jsp:include page="include/bottom.jsp"></jsp:include>
</body>
</html>
