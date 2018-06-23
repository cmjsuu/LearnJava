<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>个人会员中心 - E-shop商城</title>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<link rel="shortcut icon" href="/rencai/favicon.ico" />
<meta name="author" content="源代碼" />
<meta name="copyright" content="ykn.cc" />
<link href="/rencai/templates/default/css/user.css" rel="stylesheet" type="text/css" />
<script src="/rencai/templates/default/js/jquery.js" type='text/javascript' language="javascript"></script>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
<script src="/rencai/templates/default/js/jquery.vtip-min.js" type='text/javascript' language="javascript"></script>
<script src="/rencai/templates/default/js/jquery.idTabs.min.js" type='text/javascript' language="javascript"></script>
<script type="text/javascript">

</script>
</head>
<body>
<jsp:include page="include/head.jsp" ></jsp:include>
<div class="head_top" />
<div class="head_top_box">
<div class="head_top_box_left link_lan"><span id="top_loginform"></span></div>
<div class="head_top_box_right link_bk">


<div class="lia t_so" id="t_so" />

<script type="text/javascript">


</script></div>
<div class="clear"></div>
</div>

<div class="head" />

<div class="nav_outer">
	<div class="left"></div>
	<div class="center">
		<div class="nav">
		
			<div class="clear"></div>
		</div>
			<div class="nav_bottom">
		  </div>
	
	</div>
	<div class="right"></div>
	<div class="clear"></div>
</div><table width="960" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px;" >
  <tr>
    <td width="173" valign="top" class="link_bk">
	
<script type="text/javascript">

</script>
<div class="left_menu_box">
	<div class="left_menu_bg">
		<div class="left_menu_tit" style="font-size:14px; color: #006699; padding-top:5px; padding-left:10px;">
		<strong>商品管理</strong></div>
		<div class="left_menu_img"><img src="/rencai/templates/default/images/07.gif"  border="0" /></div>
		<div class="clear"></div>
	</div>
	<div class="left_menu_btop">
	<ul>
			<li><a href="allOrderDetailView.userInfo">全部账单明细</a></li>
			<li>帐单管理</li>
			</ul>
	</div>
</div>
<div class="left_menu_box">
	<div class="left_menu_bg">
		<div class="left_menu_tit" style="font-size:14px; color: #006699; padding-top:5px; padding-left:10px;">
		<strong>购物车管理</strong></div>
		<div class="left_menu_img"><img src="/rencai/templates/default/images/07.gif"  border="0" /></div>
		<div class="clear"></div>
	</div>
	<div class="left_menu_btop">
	<ul>
			<li><a href="shopping_cart.jsp" target="_blank">查看购物车</a></li>
			<li><a href="personal_apply.php?act=down">购物车管理</a></li>
			<li><a href="personal_apply.php?act=favorites">商品收藏夹</a></li>
			</ul>
	</div>
</div>
<div class="left_menu_box">
	<div class="left_menu_bg">
		<div class="left_menu_tit" style="font-size:14px; color: #006699; padding-top:5px; padding-left:10px;">
		<strong>账户管理</strong></div>
		<div class="left_menu_img"><img src="/rencai/templates/default/images/07.gif"  border="0" /></div>
		<div class="clear"></div>
	</div>
	<div class="left_menu_btop">
	<ul>
			<li><a href="individualInfo">查看个人资料</a></li>
			<li><a href="individualInfo">修改个人资料</a></li>
			
			<li><a href="personal_user.php?act=user_status">帐号状态</a></li>
			<li><a href="changepassword.jsp">密码修改</a></li>
			<!--<li><a  href="personal_user.php?act=feedback">意见建议</a></li>-->
			<li><a href="/rencai/user/login.php?act=logout">安全退出</a></li>
	</ul>
	</div>
</div>	</td>
    <td valign="top" class="link_lan">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D6E2ED" >
      <tr>
        <td>
		<table width="100%" border="0" cellpadding="0" cellspacing="5" bgcolor="#F9FCFF">
          <tr>
            <td>
			<div style="font-size:14px; color: #006699; padding-top:5px; padding-left:10px;"> <strong>〉〉帐单信息</strong></div>
			<table width="100%" border="0" cellpadding="0" cellspacing="5" bgcolor="#F9FCFF" >
            <tr>
              <td width="120">
			  <table border="0" cellpadding="0" cellspacing="1" bgcolor="#CDE6F3" style="margin:3px; padding:3px;" >
              
              </table>			  </td>
           
              </tr>
      </table>
			</td>
            <td width="230">
			
			<table width="100%" border="0" cellpadding="0" cellspacing="0"  >
              <tr>
                <td>
				<div class="resume_add idlink" id="personal_resume.php?act=make1"></div>
				</td>
                <td>
				
				<div class="resume_list idlink" id="personal_resume.php?act=resume_list"></div>
				</td>
              </tr>
            </table>
			<script type="text/javascript">
					
			</script>
			</td>
          </tr>
        </table></td>
      </tr>
    </table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"  style="margin-top:8px;">
        
  
  <tr>
    <td bgcolor="#FFFFFF">
 
		 <table width="100%" border="1" align="center" cellpadding="1" cellspacing="1"  class="link_lan">
		         <tr>
					<th>ID</th>
					<th>姓名</th>
					<th>发货地址</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<c:if test="${length>0}">
					<c:forEach items="${ordersList}" var="order">
						<tr align="center"">
							<td class="first w4 c">${order.oId}</td>
							<td class="w1 c">${order.userName}</td>
							<td>${order.userAddress}</td>
							<td class="w1 c">
								<c:choose>
									<c:when test="${order.oStatus == 2}">
										未审核
									</c:when>
									<c:when test="${order.oStatus == 3}">
										无法交易
									</c:when>
									<c:when test="${order.oStatus == 4}">
										未发货
									</c:when>
									<c:when test="${order.oStatus == 5}">
										已发货
									</c:when>
									<c:when test="${order.oStatus == 6}">
										完成交易
									</c:when>
									<c:otherwise>
										状态不明
									</c:otherwise>
								</c:choose>
							</td>
							<td class="w1 c"><a href="userOrderDetailView.userInfo?oId=${order.oId}">查看详情</a> </td>
						</tr>
					</c:forEach>
				</c:if>
				
		</table>
						<c:if test="${length==0}"><div class="us_list_no_content">没有匹配的信息</div><br /></c:if>
		
 	</td>
 </tr>
</table>
          </td>
          <td width="200" valign="top">
		  </td>
        </tr>
      </table>
	
<div class="footer" align="center">

<jsp:include page="include/bottom.jsp" ></jsp:include>
<div class="link_bk" style="font-size:15px;"> Powered by <a href="http://www.zhuojing.cn/" target="_blank" style="color:#0066CC">卓京信息有限公司</a></div>
</div>
</body>
</html>