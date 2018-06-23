<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/myTagLib.tld" prefix="myTag"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>E-Shop</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
<jsp:include page="include/head.jsp"></jsp:include>
<div id="position" class="wrap"> 
	您现在的位置：<a href="index.jsp">E-Shop</a> &gt; 条件查询  &gt; 按价格查询
</div>
<div id="main" class="wrap">
	<div class="main">
		<div class="product-list">
			<h2>查询条件</h2>
			<form action="searchByPrice.search">
				<table width=60%" >
					<tr>
						<td colspan="2">1元~100元</td>
						<td><input type="radio" name="priceRange" value="1"/></td>
					</tr>
					<tr>
						<td colspan="2">101元~500元</td>
						<td><input type="radio" name="priceRange" value="2"/></td>
					</tr>
					<tr>
						<td colspan="2">500元~1000元</td>
						<td><input type="radio" name="priceRange" value="3"/></td>
					</tr>
					<tr>
						<td colspan="2">1001元~5000元</td>
						<td><input type="radio" name="priceRange" value="4"/></td>
					</tr>
					<tr>
						<td colspan="2">5000元以上</td>
						<td><input type="radio" name="priceRange" value="5"/></td>
					</tr>
					<tr>
						<td colspan="2"></td>
						<td><input type="submit" value="查询"/></td>
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
