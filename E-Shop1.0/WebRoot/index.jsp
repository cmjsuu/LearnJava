<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="sc.eshop.*"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://mytag.sf.net" prefix="pageTag"%>  
<%@taglib uri="WEB-INF/myTagLib.tld" prefix="myTag"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<title>E-Shop网 - 首页</title>
		<link type="text/css" rel="stylesheet" href="css/style.css" />
		<script type="text/javascript" src="scripts/function.js"></script>
	</head>
	<body>
		<jsp:include page="/include/head.jsp"></jsp:include>
		<div id="position" class="wrap">
			<form action="fuzzySearch.search">
				<table>
					<tr>
						<td>
							您现在的位置：
							<a href="index.jsp">E-Shop</a> &gt; 首页
						</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>
							<input type="text" name="keywords" value="" maxLength="10" />
						</td>
						<td>&nbsp;</td>
						<td>
							<input type="submit" value="搜索" />
						</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>
							<a href="search_by_price.jsp">按价格查找</a>
						</td>
					</tr>
				</table>
			</form>

		</div>
		<div id="main" class="wrap">
			<jsp:include page="/include/left.jsp"></jsp:include>
			<div class="main">
				<div class="price-off">
					<h2>
						今日特价
					</h2>
					<ul class="product clearfix">
					<c:forEach items="${goodsList}" var="goods">
							<li>
								<dl>
									<dt>
									
										<a href="goodsDetailShow.show?gId=${goods.gId}"
											target="_blank"> <img src="images/product/${goods.gUrl}"
												height="100" width="80" /> </a>
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
					<myTag:pageTag url="goodsDetailShow.show" />
				</div>
				<div class="side" align="right">
					<jsp:include page="include/right.jsp"></jsp:include>
				</div>
				<div class="spacer clear"></div>
				
				<div class="hot">
					<h2>
						热卖推荐
					</h2>
					<ul class="product clearfix">
						<c:forEach items="${goodsHot}" var="hot">
							<li>
								<dl>
									<dt>

										<a href="goodsDetailShow.show?gId=${hot.gId}" target="_blank">
											<img src="images/product/${hot.gUrl}" height="60" width="40" />
										</a>
									</dt>
									<dd class="title">
										<a href="goodsDetailShow.show?gId=${hot.gId}" target="_blank">${hot.gName}</a>
									</dd>
									<dd class="price">
										￥${hot.gPrice}
									</dd>
								</dl>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="clear"></div>
			
		</div>
		<jsp:include page="include/bottom.jsp"></jsp:include>
	</body>
</html>
