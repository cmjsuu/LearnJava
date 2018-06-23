<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="WEB-INF/myTagLib.tld" prefix="myTag"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>E-Shop</title>
		<link type="text/css" rel="stylesheet" href="css/style.css" />
		<script type="text/javascript" src="scripts/function.js"></script>
	</head>
	<body>
		&nbsp;
		<jsp:include page="include/head.jsp"></jsp:include>
		<div id="position" class="wrap">
			您现在的位置：
			<a href="index">易买网</a> &gt;
			<a href="product-list.html">图书音像</a> &gt; 图书
		</div>
		<div id="main" class="wrap">
			<jsp:include page="include/left.jsp"></jsp:include>
			<div id="product" class="main">
				<h1 align="center">
					${goods.gName}
				</h1>
				<div class="infos">
					<br />
					<br />
					<div class="thumb" align="center">
						<img src="images/product/${goods.gUrl}" width="200" height="250" />
					</div>
					<div class="buy">
						<h3>
							商城价：
							<span class="price">￥${goods.gPrice}</span>
						</h3>
						<h3>
							商品编号：${goods.gId}
						</h3>
						<h3>
							品 牌：${goods.gBrand}
						</h3>
						<h3>
							库 存：${goods.gStock}
						</h3>
						<h3>
							上市时间：${goods.gSellTime}
						</h3>
						<h3>
							销 量：${goods.gSales}
						</h3>
						<div class="button">
							<a href="addToCart.order?gId=${goods.gId}"> <input
									type="button" name="button" value="" onclick="" /> 
							</a>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="introduce">
					<h2>
						<strong>商品详情</strong>
					</h2>
					<div class="text">
						${goods.gDescribe }
						<br />
					</div>
				</div>
				<div class="commentary">
					<h2>
						<strong>查看评论</strong>
					</h2>
					<div class="text">
						<c:forEach items="${commentList}" var="comment">
			    			<div align="left">${comment.cComment}</div>
							<div align="right">
								${comment.nickname}&nbsp;&nbsp;${comment.cTime}
							</div>
							<hr>
						</c:forEach>
					</div>
					<myTag:pageTag url="goodsDetailShow.show?gId=${goods.gId}" />
					<div class="clear"></div>
				</div>

				<h2>
					<strong>发表评论</strong>
				</h2>
				<div class="text">
					<form action="addGoodsComment.show" method="POST">
						<table>
							<tr>
								<td>
									评论内容：
									<input type="hidden" name="gId" value="${goods.gId}"/>
								</td>
								<td>
									<textarea name="content" rows="5" cols="60"></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<input type="submit" value="发表">
								</td>
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
