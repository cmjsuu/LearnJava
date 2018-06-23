<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 </title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/function-manage.js"></script>
</head>
<body>
<jsp:include page="include/head-left.jsp"></jsp:include>
	<div class="main">
		<h2>修改商品</h2>
		<div class="manage">
			<form action="goodsModifyDo.goods">
				<table class="form">
					<tr>
						<td><input type="hidden" name="gId" value="${goods.goodsID}" />
					</td>
					</tr>
					<tr>
						<td class="field">商品名称：</td>
						<td><input type="text" class="text" name="gName" value="${goods.goodsName}" /></td>
					</tr>
					<tr>
						<td class="field">所属分类：</td>
						<td>
							<select name="stId">
								<c:forEach items="${typeList}" var="type">
									<option value="${type.goodsTypeID}">${type.goodsTypeName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="field">商品图片：</td>
						<td><input type="file" class="text" name="gUrl" value="${goods.goodsUrl}"/></td>
					</tr>
					<tr>
						<td class="field">商品价格：</td>
						<td><input type="text" class="text tiny" name="gPrice" value="${goods.goodsPrice}"/> 元</td>
					</tr>
					<tr>
						<td class="field">设计师：</td>
						<td><input type="text" class="text" name="gBrand" value="${goods.goodsDesigner}"/></td>
					</tr>
					<tr>
						<td class="field">库存：</td>
						<td><input type="text" class="text tiny" name="gStock" value="${goods.goodsStock}"/></td>
					</tr>
					<tr>
						<td class="field">商品描述：</td>
						<td><input type="text" class="text" name="gDescribe" value="${goods.goodsDescribe}"/></td>
					</tr>
					<tr>
						<td></td>
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
