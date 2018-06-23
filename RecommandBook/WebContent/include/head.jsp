<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="shortcut icon" href="http://www.yzipi.com/wp-content/themes/yzipi/images/favicon.ico">
<link rel="stylesheet" id="sytle-css" href="../css/style.css" type="text/css" media="all">
<link rel="stylesheet" id="yzipi-pc-css" href="../css/yzipi-pc.css" type="text/css" media="all">
<link rel="stylesheet" id="yzipi-phone-css" href="../css/yzipi-phone.css" type="text/css" media="all">
<script type="text/javascript" src="../js/selectivizr-min.js"></script>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/smohan.fixednav.js"></script>
<link rel="stylesheet prefetch" href="http://puertokhalid.com/up/demos/puerto-Mega_Menu/css/normalize.css">
<link rel="stylesheet" href="../css/menu.css" media="screen" type="text/css">

<header id="header-web">`
    <div class="header-main">
        <nav class="header-nav ">
            <div class="menu-%e9%a1%b6%e9%83%a8%e8%8f%9c%e5%8d%95-container">
                <ul id="menu-%e9%a1%b6%e9%83%a8%e8%8f%9c%e5%8d%95" class="menu">
                    <li id="menu-item-1" class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-home menu-item-4"><a href="index.jsp">首页</a></li>
                    <li id="menu-item-2" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-6"><a href="读者自荐.html">自主荐购</a></li>
                    <li id="menu-item-3" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-6"><a href="学院历史荐购.html">历史荐购</a></li>
                    <li id="menu-item-4" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-127"><a href="购买历史.html">历史购书</a></li>
                </ul>
            </div>
        </nav>
        <form method="get" class="search" action="#">
            <input class="text" type="text" name="s" placeholder="Search" value="">
            <input class="go_btn" type="button" onclick="searchEvent();">
        </form>
        
        <font color='#d6623d'>
			<c:if test="${sessionScope.nickname != null}">
				用户 ${sessionScope.nickname} 您好
			</c:if>
		</font>
        
    </div>
    </header>