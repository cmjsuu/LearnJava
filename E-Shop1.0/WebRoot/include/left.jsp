<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="lefter">
	<div class="box">
		<h2>商品分类</h2>
		<dl>
			<dt>图书音像</dt>
			<dd>
				<a href="goodsTypeShow.show?stid=b001">图书</a>
			</dd>
			<dd>
				<a href="goodsTypeShow.show?stid=b002">音乐</a>
			</dd>
			<dt>百货</dt>
			<dd>
				<a href="goodsTypeShow.show?stid=bh003">运动健康</a>
			</dd>
			<dd>
				<a href="goodsTypeShow.show?stid=bh004">服装</a>
			</dd>
			<dd>
				<a href="goodsTypeShow.show?stid=bh005">家居</a>
			</dd>
			<dd>
				<a href="goodsTypeShow.show?stid=bh006">美妆</a>
			</dd>
			<dd>
				<a href="goodsTypeShow.show?stid=bh007">母婴</a>
			</dd>
			<dd>
				<a href="goodsTypeShow.show?stid=bh008">食品</a>
			</dd>
			<dd>
				<a href="goodsTypeShow.show?stid=bh009">手机数码</a>
			</dd>
			<dd>
				<a href="goodsTypeShow.show?stid=bh010">家具首饰</a>
			</dd>
			<dd>
				<a href="goodsTypeShow.show?stid=bh011">手表饰品</a>
			</dd>
			<dd>
				<a href="goodsTypeShow.show?stid=bh012">鞋包</a>
			</dd>
			<dd>
				<a href="goodsTypeShow.show?stid=bh013">家电</a>
			</dd>
			<dd>
				<a href="goodsTypeShow.show?stid=bh014">电脑办公</a>
			</dd>
			<dd>
				<a href="goodsTypeShow.show?stid=bh015">玩具文具</a>
			</dd>
			<dd>
				<a href="goodsTypeShow.show?stid=bh016">汽车用品</a>
			</dd>
		</dl>
	</div>
	<div class="spacer"></div>
	<div class="last-view">
		<h2>最近浏览</h2>
		<table>
			<c:forEach items="${latest}" var="scan">
				<tr>
					<td>
						<img src="images/product/${scan.gUrl}" width="80" height="80" />
					</td>
					<td>
						<h4><a href="goodsDetailShow.show?gId=${scan.gId}">${scan.gName}</a></h4>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>