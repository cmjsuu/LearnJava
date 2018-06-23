<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/myTagLib.tld" prefix="myTag"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>角色信息</title>
		<link type="text/css" rel="stylesheet" href="../css/style.css" />
		<script type="text/javascript" src="../scripts/function-manage.js"></script>
	</head>
	<body>
		&nbsp;&nbsp;
		<jsp:include page="include/head-left.jsp"></jsp:include>
		<div class="main">
			<h2 align="left">
				角色信息
			</h2>
			<div class="manage">
				<div class="spacer"></div>
				<table class="list">
					<tr>
						<th>
							ID
						</th>
						<th>
							昵称
						</th>
						<th>
							职业
						</th>
						<th>
							等级
						</th>
						<th>
							查看
						</th>
						
					</tr>
					<c:forEach items="${gamepeopleVOList}" var="List">
						<tr>
							<td class="w1 c">
								${List.gameID}
							</td>
							<td class="w1 c">
								${List.nickName}
							</td>
							<td class="w1 c">

									${List.job}	
										
							</td>
							<td class="w1 c">
								${List.gameLevel}
							</td>
							<td>
								<a href="PackLookView.user?packID=${List.packID}">查看背包</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="clear"></div>
	
		<jsp:include page="include/bottom.jsp"></jsp:include>
	</body>
</html>
