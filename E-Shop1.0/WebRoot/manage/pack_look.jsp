<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/myTagLib.tld" prefix="myTag"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>角色背包</title>
		<link type="text/css" rel="stylesheet" href="../css/style.css" />
		<script type="text/javascript" src="../scripts/function-manage.js"></script>
	</head>
	<body>
		&nbsp;&nbsp;
		<jsp:include page="include/head-left.jsp"></jsp:include>
		<div class="main">
			<h2 align="left">
				背包物品
			</h2>
			<div class="manage">
				<div class="spacer"></div>
				<table class="list">
					<tr>
						<th>
							ID
						</th>
						<th>
							名称
						</th>
						<th>
							类别
						</th>
						<th>
							现金
						</th>
						<th>
							金币
						</th>
						<th>
							职业
						</th>
						<th>
							简介
						</th>
					</tr>
					<c:forEach items="${toolList}" var="List">
						<tr>
							<td class="w1 c">
								${List.toolID}
							</td>
							<td class="w1 c">
								${List.toolName}
							</td>
							</td>
							<td class="w1 c">
								${List.tooltype}
							</td>
								
							<td class="w1 c">
								${List.toolCash}
							</td>
							<td class="w1 c">
								${List.toolGold}
							</td>
							
							<td class="w1 c">
								${List.job}
							</td>
							<td class="w1 c">
								${List.regulationsInfo}
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