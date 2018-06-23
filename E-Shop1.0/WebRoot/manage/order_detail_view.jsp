<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/myTagLib.tld" prefix="myTag"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>订单明细 </title>
		<link type="text/css" rel="stylesheet" href="../css/style.css" />
		<script type="text/javascript" src="../scripts/function-manage.js"></script>
	</head>
	<body>
		&nbsp;&nbsp;
		<jsp:include page="include/head-left.jsp"></jsp:include>
		<div class="main">
			<h2 align="left">
				订单明细
			</h2>
			<div class="manage">
				<div class="spacer"></div>
				<table class="list">
					<tr>
						<th>
							订单编号
						</th>
						<th>
							商品编号
						</th>
						<th>
							商品名称
						</th>
						<th>
							购买数量
						</th>
						<th>
							商品价格
						</th>
						<th>
							小计
						</th>
					</tr>
					<c:forEach items="${detailList}" var="detail">
						<tr>
							<td class="w1 c">
								${detail.orderID}
							</td>
							<td class="w1 c">
								${detail.goodsID}
							</td>
							<td class="w1 c">
								${detail.goodsName}
							</td>
							<td class="w1 c">
								${detail.goodsNumber}
							</td>
							<td class="w1 c">
								${detail.goodPrice}
							</td>
							<td class="w1 c">
								${detail.goodsSubTotal}
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="clear"></div>
		<myTag:pageTag url="orderDetailView.om" />
		<jsp:include page="include/bottom.jsp"></jsp:include>
	</body>
</html>
