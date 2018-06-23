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
			您现在的位置：
			<a href="index.jsp">E-Shop</a> &gt; ${type.mtName} &gt;
			${type.stName}
		</div>
		<div id="main" class="wrap">
			<jsp:include page="include/left.jsp"></jsp:include>
			<div class="main">
				<div class="product-list">
					<h2>
						全部商品
					</h2>
					<div class="pager">
						<myTag:pageTag url="goodsTypeShow.show?stid=${type.stId}" />
					</div>
					<div class="clear"></div>
					<ul class="product clearfix">
						<c:forEach items="${goodsList}" var="goods">
							<li>
								<dl>
									<dt>
										<a href="goodsDetailShow.show?gId=${goods.gId}"
											target="_blank"><img src="images/product/${goods.gUrl}" />
										</a>
									</dt>
									<dd class="title">
										<a href="goodsDetailShow.show?gId=${goods.gId}"
											target="_blank">${goods.gName}</a>
									</dd>
									<dd class="price">
										￥${goods.gPrice}
									</dd>
								</dl>
							</li>
						</c:forEach>
					</ul>
					<div class="clear"></div>
					<div class="pager">
						<myTag:pageTag url="goodsTypeShow.show?stid=${type.stId}" />
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<jsp:include page="include/bottom.jsp"></jsp:include>
	</body>
</html>
