<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>个人会员中心 - E-shop商城</title>
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<link rel="shortcut icon" href="/rencai/favicon.ico" />
		<meta name="author" content="源代碼" />
		<meta name="copyright" content="ykn.cc" />
		<link href="/rencai/templates/default/css/user.css" rel="stylesheet"
			type="text/css" />
		<script src="/rencai/templates/default/js/jquery.js"
			type='text/javascript' language="javascript"></script>
		<link type="text/css" rel="stylesheet" href="css/style.css" />
		<script type="text/javascript" src="scripts/function.js"></script>
		<script src="/rencai/templates/default/js/jquery.vtip-min.js"
			type='text/javascript' language="javascript"></script>
		<script src="/rencai/templates/default/js/jquery.idTabs.min.js"
			type='text/javascript' language="javascript"></script>
		<script type="text/javascript">
	
</script>
	</head>
	<jsp:include page="include/head.jsp"></jsp:include>
	<div class="head_top_box">
		<div class="head_top_box_left link_lan">
			<span id="top_loginform"></span>
		</div>
		<div class="head_top_box_right link_bk">
		</div>
		<div class="clear"></div>
	</div>
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
	</div>
	<table width="960" border="0" align="center" cellpadding="0"
		cellspacing="0" style="margin-top: 8px;">
		<tr>
			<td width="173" valign="top" class="link_bk">
				<div class="left_menu_box">
					<div class="left_menu_bg">
						<div class="left_menu_tit"
							style="font-size: 14px; color: #006699; padding-top: 5px; padding-left: 10px;">
							<strong>商品管理</strong>
						</div>
						<div class="left_menu_img">
							<img src="/rencai/templates/default/images/07.gif" border="0" />
						</div>
						<div class="clear"></div>
					</div>
					<div class="left_menu_btop">
						<ul>
							<li>
								<a href="allOrderDetailView">账单明细</a>
							</li>
							<li>
								<a href="">帐单管理</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="left_menu_box">
					<div class="left_menu_bg">
						<div class="left_menu_tit"
							style="font-size: 14px; color: #006699; padding-top: 5px; padding-left: 10px;">
							<strong>购物车管理</strong>
						</div>
						<div class="left_menu_img">
							<img src="/rencai/templates/default/images/07.gif" border="0" />
						</div>
						<div class="clear"></div>
					</div>
					<div class="left_menu_btop">
						<ul>
							<li>
								<a href="shopping_cart.jsp" target="_blank">查看购物车</a>
							</li>
							<li>
								<a href="personal_apply.php?act=down">购物车管理</a>
							</li>
							<li>
								<a href="personal_apply.php?act=favorites">商品收藏夹</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="left_menu_box">
					<div class="left_menu_bg">
						<div class="left_menu_tit"
							style="font-size: 14px; color: #006699; padding-top: 5px; padding-left: 10px;">
							<strong>账户管理</strong>
						</div>
						<div class="left_menu_img">
							<img src="/rencai/templates/default/images/07.gif" border="0" />
						</div>
						<div class="clear"></div>
					</div>
					<div class="left_menu_btop">
						<ul>
							<li>
								<a href="individualInfo">查看个人资料</a>
							</li>
							<li>
								<a href="individualInfo">修改个人资料</a>
							</li>

							<li>
								<a href="personal_user.php?act=user_status">帐号状态</a>
							</li>
							<li>
								<a href="changepassword.jsp">密码修改</a>
							</li>
							<!--<li><a  href="personal_user.php?act=feedback">意见建议</a></li>-->
							<li>
								<a href="/rencai/user/login.php?act=logout">安全退出</a>
							</li>
						</ul>
					</div>
				</div>
			</td>
			<td valign="top" class="link_lan">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					bgcolor="#D6E2ED">
					<tr>
						<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="5"
								bgcolor="#F9FCFF">
								<tr>
									<td>
										<div
											style="font-size: 14px; color: #006699; padding-top: 5px; padding-left: 10px;">
											<strong>〉〉帐单明细</strong>
										</div>
										<table width="100%" border="0" cellpadding="0" cellspacing="5"
											bgcolor="#F9FCFF">
											<tr>
												<td width="120">
													<table border="0" cellpadding="0" cellspacing="1"
														bgcolor="#CDE6F3" style="margin: 3px; padding: 3px;">

													</table>
												</td>

											</tr>
										</table>
									</td>
									<td width="230">

										<table width="100%" border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<div class="resume_add idlink"
														id="personal_resume.php?act=make1"></div>
												</td>
												<td>

													<div class="resume_list idlink"
														id="personal_resume.php?act=resume_list"></div>
												</td>
											</tr>
										</table>

									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					style="margin-top: 8px;">


					<tr>
						<td bgcolor="#FFFFFF">

							<table width="100%" border="1" align="center" cellpadding="1"
								cellspacing="1" class="link_lan">
								<tr>
									<th>
										订单编号
									</th>
									<th>
										商品名称
									</th>
									<th>
										商品数量
									</th>
									<th>
										商品单价
									</th>
									<th>
										商品小计
									</th>
								</tr>
								<c:forEach items="${orderList}" var="orderById">
									<c:forEach items="${orderById}" var="detail">
										<tr align="center">
											<td class="first w4 c">
												${detail.oId}
											</td>
											<td class="w1 c">
												${detail.gName}
											</td>
											<td class="w1 c">
												${detail.gNumber}
											</td>
											<td class="w1 c">
												${detail.gPrice}
											</td>
											<td class="w1 c">
												${detail.gSubtotal}
											</td>
										</tr>
									</c:forEach>
								</c:forEach>
							</table>
						</td>
					</tr>
				</table>
			</td>
			<td width="200" valign="top">
			</td>
		</tr>
	</table>
	<div class="footer" align="center">
		<jsp:include page="include/bottom.jsp"></jsp:include>
	</div>
	</body>
</html>