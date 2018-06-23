<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>个人中心 - E-shop商城</title>
		<link type="text/css" rel="stylesheet" href="css/userCenter.css" />
		<script type="text/javascript" src="scripts/function.js"></script>
	</head>
	<body>
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
							<hr>
							<div class="clear"></div>
						</div>
						<div class="left_menu_btop">
							<ul>
								<li>
									<a href="userOrderView.userInfo">查看账单</a>
								</li>
								<li>
									<a href="orderuser">帐单管理</a>
								</li>
							</ul>
						</div>
					</div>
					<hr>
					<div class="left_menu_box">
						<div class="left_menu_bg">
							<div class="left_menu_tit"
								style="font-size: 14px; color: #006699; padding-top: 5px; padding-left: 10px;">
								<strong>购物车管理</strong>
							</div>
							<hr>
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
					<hr>
					<div class="left_menu_box">
						<div class="left_menu_bg">
							<div class="left_menu_tit"
								style="font-size: 14px; color: #006699; padding-top: 5px; padding-left: 10px;">
								<strong>账户管理</strong>
							</div>
							<hr>
							<div class="clear"></div>
						</div>
						<div class="left_menu_btop">
							<ul>
								<li>
									<a href="userInfoView.userInfo">查看|修改个人资料</a>
								</li>
								<li>
									<a href="changepassword.jsp">密码修改</a>
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
												<strong>帐号信息</strong>
											</div>
											<hr>
											<table width="100%" border="0" cellpadding="0"
												cellspacing="5" bgcolor="#F9FCFF">
												<tr>
													
													<td valign="top">

														<table width="97%" border="0" cellpadding="3"
															cellspacing="0">
															<tr>
																<td height="35">
																	<div style="float: left">
																		<strong style="color: #000000; font-size: 18px;">
																			${userVO.uNickname}</strong> &nbsp;
																		<span style="color: #C3C3C3">用户名:${userVO.uName}</span>
																		&nbsp;&nbsp;
																	</div>
																</td>
															</tr>

															<tr>
																<td style="color: #999999">
																	注册时间：${userVO.uTime} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																</td>
															</tr>
															<tr>
																<td>
																	您的购物车里有
																	<strong style="color: #FF0000">${length}</strong>
																	件商品&nbsp;&nbsp;
																	&nbsp;&nbsp;&nbsp;
																</td>
															</tr>
															<tr>
																<td class="link_lan">
																	<!--收到的面试邀请：<a href="personal_apply.php?act=interview" style="color:#666666">(0)</a>&nbsp;&nbsp;&nbsp;
					已申请职位：<a href="personal_apply.php?act=apply_jobs"  style="color:#666666">(0)</a>&nbsp;&nbsp;&nbsp;-->
																	商品收藏夹：
																	<a href="personal_apply.php?act=favorites"
																		style="color: #666666">(0)</a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
										<td width="230">

											<table width="100%" border="0" cellpadding="0"
												cellspacing="0">
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
											<script type="text/javascript">
	/**$(document).ready(function()
	{
		$(".idlink").click(function(){
		window.location.href=$(this).attr("id");
		});	
		
		$(".resume_add").hover(function()	{$(this).addClass("resume_add_hover")},function(){$(this).removeClass("resume_add_hover")});
		$(".resume_list").hover(function()	{$(this).addClass("resume_list_hover")},function(){$(this).removeClass("resume_list_hover")});
		
	});*/
</script>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>




					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						style="margin-top: 8px;">
						<tr>
							<td valign="top">

								<table width="785" border="0" cellpadding="0" cellspacing="1"
									bgcolor="#CDE6F3">
									<tr>
										<td class="us_index_title_bg">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td class="us_index_title">
														您可能感兴趣的商品
													</td>
													<td align="right">
														<a href="index" target="_blank">更多</a>&nbsp;&nbsp;&nbsp;&nbsp;
													</td>
												</tr>
											</table>

										</td>
									</tr>
									<tr>
										<td bgcolor="#FFFFFF">

											<table width="100%" border="0" align="center" cellpadding="0"
												cellspacing="0" class="link_lan">
												<tr>
													<td height="28" class="us_list_title"
														style="padding-left: 10px;">
														商品名称
													</td>
													<td height="10" class="us_list_title">
														商品价格
													</td>
													<td width="250" class="us_list_title">
														商品描述
													</td>
													<td width="100" class="us_list_title">
														上市日期
													</td>
												</tr>
												<c:if test="${goodsList!=null}">
													<c:forEach items="${goodsList}" var="goodsList">
														<tr>
															<td>
																<img src="images/product/${goodsList.gUrl}" />
															</td>
															<td height="28" class="us_list_title"
																style="padding-left: 10px;">
																${goodsList.gName}
															</td>
															<td height="10" class="us_list_title">
																${goodsList.gPrice}
															</td>
															<td width="250" class="us_list_title">
																${goodsList.gDescribe}
															</td>
															<td width="100" class="us_list_title">
																${goodsList.gSellTime}
															</td>
														</tr>
													</c:forEach>
												</c:if>

											</table>
											<c:if test="${goodsList==null}">
												<div class="us_list_no_content">
													没有匹配的信息
												</div>
												<br />
											</c:if>


										</td>
									</tr>
								</table>



							</td>
							<td width="200" valign="top">







							</td>
						</tr>
					</table>





				</td>
			</tr>
		</table>

		<div class="footer" align="center">

			<div class="link_bk">
				Copyright @ 2012 卓京信息源代码项目小组
			</div>
			<div class="link_bk" style="font-size: 15px;">
				Powered by
				<a href="http://www.zhuojing.cn/" target="_blank"
					style="color: #0066CC">卓京信息有限公司</a>
			</div>
		</div>
	</body>
</html>