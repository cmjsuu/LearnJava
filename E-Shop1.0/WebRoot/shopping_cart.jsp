<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/myTagLib.tld" prefix="myTag"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>E-Shop - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
<script type="text/javascript">
	function getNumber(obj){
		try {
			xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e1) {
			try {
				xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e2) {
				xmlHttpRequest = false;
			}
		}
		if (!xmlHttpRequest && typeof XMLHttpRequest != "undefined") {
			xmlHttpRequest = new XMLHttpRequest();
		}
		xmlHttpRequest.open("POST", "modifyNumber.order", true);
		xmlHttpRequest.setRequestHeader("content-type",
				"application/x-www-form-urlencoded");
		xmlHttpRequest.onreadystatechange = function() {
			if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
				var result1 = xmlHttpRequest.responseText;
				var result2 = result1.split("_", 3);
				document.getElementById(result2[0] + "_subtotal").innerHTML=result2[1];
				document.getElementById("totalMoney").innerHTML=result2[2];
			}
		};
		xmlHttpRequest.send("number="+obj.name + obj.value);
	}
</script>
</head>
<body>
	<jsp:include page="include/head.jsp"></jsp:include>
	<div id="position" class="wrap">
		您现在的位置： <a href="index">E-Shop</a> &gt; 购物车
	</div>
	<div class="wrap">
		<div id="shopping">
			<form action="payTheCart.order" method="post">
				<table>
					<tr>
						<th>商品名称</th>
						<th>商品价格</th>
						<th>购买数量</th>
						<th>小计</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${sessionScope.cartList}" var="orderDetail">
						<tr id="product_id_1">
							<td class="name" align="center"><a id="a${orderDetail.gId}"
								href="goodsDetailShow.show?gId=${orderDetail.gId}">${orderDetail.gName}</a>
							</td>
							<td class="price" id="price_id_1"><span>${orderDetail.gPrice}</span>
							</td>
							<td class="number" align="center" id="price_id_1">
								<input type="text" name="${orderDetail.gId}_number_" id="${orderDetail.gId}_number" value="${orderDetail.gNumber}" 
								size="6" maxLength="6" onblur="getNumber(this);"/></td>
							<td class="money" align="center" id="price_id_1">
								<span id="${orderDetail.gId}_subtotal">${orderDetail.gSubtotal}</span>
							</td>
							<td class="delete"><a href="#?gId=${orderDetail.gId}">删除</a>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<TD class="bold right" colSpan=5 align="right">
							<span id="totalMoney">总计:${sessionScope.totalMoney}</span></TD>
					</tr>
				</table>
				<br />
				<div class="button" align="right">
					<input type="submit" value="" />
				</div>
			</form>
		</div>
		<div class="button" align="right">
			<font size="4" color="green"><a href="index">继续购买</a>
			</font>
		</div>
		<br />
		<script type="text/javascript">
			//document.write("Cookie中记录的购物车商品ID：" + getCookie("product")
			//+ "，可以在动态页面中进行读取");
		</script>
	</div>
	<jsp:include page="include/bottom.jsp"></jsp:include>
</body>
</html>
