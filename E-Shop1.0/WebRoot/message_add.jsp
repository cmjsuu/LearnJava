<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/myTagLib.tld" prefix="myTag"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>E-Shop - 首页</title>
		<link type="text/css" rel="stylesheet" href="css/style.css" />
		<script type="text/javascript" src="js/ajax.js"></script>

	</head>
	<body>
		<jsp:include page="include/head.jsp"></jsp:include>
		<div id="position" class="wrap">
			您现在的位置：
			<a href="index">E-Shop</a> &gt; 在线留言
		</div>
		<div id="main" class="wrap">
			
			<div class="main">
				<h1>
					最新留言
				</h1>
				<hr>
				<div class="guestbook">
					<div class="clear"></div>
					<div id="reply-box">
						<form action="messageAddDo.view">
							<table>
								<tr>
									<td class="field">
										用户：
									</td>
									<td>
										<input class="text" type="text" name="uNickname"
											value="${sessionScope.nickname}" readonly="readonly"/>
									</td>
								</tr>
								
								<tr>
									<td class="field">
										留言标题：
									</td>
									<td>
										<input class="text" type="text" name="mTitle" value="" />
									</td>
								</tr>
								<tr>
									<td class="field">
										留言内容：
									</td>
									<td>
										<textarea name="mContent"></textarea>
									</td>
								</tr>
								<tr>
									<td></td>
									<td>
										<label class="ui-blue">
											<input type="submit" name="submit" value="提交留言" />
										</label>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<jsp:include page="include/bottom.jsp"></jsp:include>
	</body>
</html>
