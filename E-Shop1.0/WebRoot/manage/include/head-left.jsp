<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="header" class="wrap">
	<div id="logo">
		<img src="../images/logo.jpg" />
	</div>
	<div class="help">
		<a href="../userExitDo.userForeground">陈美佳 林佳倩</a>
	</div>
	<c:choose>
    <c:when test="${sessionScope.managerAuthority == 0}">
    <div class="navbar">
		<ul class="clearfix">
			<li class="current">
				<a href="index.jsp">首页</a>
			</li>
			<li>
				<a href="userManageView.user">用户</a>
			</li>
			<li>
				<a href="goodsManageView.goods">商品</a>
			</li>
			<li>
				<a href="orderManageView.om">订单</a>
			</li>
			<li>
				<a href="messageManageView.nmm">留言</a>
			</li>
			<li>
				<a href="newsManageView.nmm">新闻</a>
			</li>
		</ul>
	</div>
    </c:when>
    <c:when test="${sessionScope.managerAuthority == 1}">
        <div class="navbar">
		<ul class="clearfix">
			<li class="current">
				<a href="index.jsp">首页</a>
			</li>
			<li>
				<a href="messageManageView.nmm">留言</a>
			</li>
			<li>
				<a href="newsManageView.nmm">新闻</a>
			</li>
		</ul>
	</div>
    </c:when>
    </c:choose>
	
</div>
<div id="childNav">
	<div class="welcome wrap">
		<c:if test="${sessionScope.mName != null}">
				管理员 ${sessionScope.mName} 您好，欢迎回到管理后台。
		</c:if>
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：
	<a href="../index">某个游戏</a> &gt; 管理后台
</div>
<div id="main" class="wrap">
	<div id="menu-mng" class="lefter">
	<c:choose>
    <c:when test="${sessionScope.managerAuthority == 0}">
        <div class="box">
			<dl>
				<dt>
					用户管理
				</dt>
				<dd>
					<em><a href="user_add.jsp">新增</a>
					</em><a href="userManageView.user">用户管理</a>
				</dd>
				<dt>
					商品信息
				</dt>
				<dd>
					<em><a href="typeAddView.type">新增</a>
					</em><a href="typeManageView.type">分类管理</a>
				</dd>
				<dd>
					<em><a href="goodsAddView.goods">新增</a>
					</em><a href="goodsManageView.goods">商品管理</a>
				</dd>
				<dt>
					订单管理
				</dt>
				<dd>
					<a href="orderManageView.om">订单管理</a>
				</dd>
				<dt>
					留言管理
				</dt>
				<dd>
					<a href="messageManageView.nmm">留言管理</a>
				</dd>
				<dt>
					新闻管理
				</dt>
				<dd>
					<em><a href="news_add.jsp">新增</a>
					</em><a href="newsManageView.nmm">新闻管理</a>
				</dd>
			</dl>
		</div>  
    </c:when>
    <c:when test="${sessionScope.managerAuthority == 1}">
            <div class="box">
			<dl>
				<dt>
					留言管理
				</dt>
				<dd>
					<a href="messageManageView.nmm">留言管理</a>
				</dd>
				<dt>
					新闻管理
				</dt>
				<dd>
					<em><a href="news_add.jsp">新增</a>
					</em><a href="newsManageView.nmm">新闻管理</a>
				</dd>
			</dl>
		</div>  
    </c:when>
    </c:choose>

	</div>