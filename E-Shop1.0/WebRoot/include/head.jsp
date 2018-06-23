<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="header" class="wrap">
	<div id="logo"></div>
	<div class="help">
		<font color='green'>
			<c:if test="${sessionScope.nickname != null}">
				用户 ${sessionScope.nickname} 您好，欢迎光临
			</c:if>
		</font>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="shopping_cart.jsp" class="shopping">购物车</a>
		<a href="login.jsp">登录</a>
		<a href="register.jsp">注册</a>
		<a href="center.userInfo">个人中心</a>
		<a href="userExitDo.userForeground">安全退出</a>
	</div>
	<div class="navbar">
		<ul class="clearfix">
			<li class="current"><a href="index">首页</a></li>
			<li><a href="goodsTypeShow.show?stid=b001">图书</a></li>
			<li><a href="goodsTypeShow.show?stid=b002">音乐</a></li>
			<li><a href="goodsTypeShow.show?stid=bh004">服装</a></li>
			<li><a href="goodsTypeShow.show?stid=bh013">家电</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="wrap">
		<ul class="clearfix">
			<li class="first"><a href="goodsTypeShow.show?stid=b002">音乐</a></li>
			<li><a href="goodsTypeShow.show?stid=bh003">运动健康</a></li>
			<li><a href="goodsTypeShow.show?stid=bh005">家居</a></li>
			<li><a href="goodsTypeShow.show?stid=bh006">美妆</a></li>
			<li><a href="goodsTypeShow.show?stid=bh007">母婴</a></li>
			<li><a href="goodsTypeShow.show?stid=bh008">食品</a></li>
			<li><a href="goodsTypeShow.show?stid=bh009">手机数码</a></li>
			<li><a href="goodsTypeShow.show?stid=bh010">家具首饰</a></li>
			<li><a href="goodsTypeShow.show?stid=bh012">鞋包</a></li>
			<li><a href="goodsTypeShow.show?stid=bh014">电脑办公</a></li>
			<li><a href="goodsTypeShow.show?stid=bh015">玩具文具</a></li>
			<li class="last"><a href="goodsTypeShow.show?stid=bh016">汽车用品</a></li>
		</ul>
	</div>
</div>