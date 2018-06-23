<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/myTagLib.tld" prefix="myTag"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 </title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/function-manage.js"></script>
</head>
<body>
<jsp:include page="include/head-left.jsp"></jsp:include>
	<div class="main">
		<h2>用户管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>ID</th>
					<th>账号</th>
					<th>性别</th>
					<!-- <th>密码</th> -->
					<th>状态</th>
					<th>操作</th>
					
				</tr>
				<c:forEach items="${userList}" var = "user">
				<tr>
					<td class="w1 c">${user.userID}</td>
					<td class="w1 c">${user.accounts}</td>
					
					
					
		
					
					<td class="w2 c">${user.userSex}</td>
					<%-- <td class="w2 c">${user.userPassword}</td> --%>
					<td class="w4 c">
						<c:choose>
							<c:when test="${user.userStatus==0}">
								非活跃用户
							</c:when>
							<c:when test="${user.userStatus==1}">
								活跃用户
							</c:when>
						</c:choose>
					</td>
					<td class="w5 c"><a href="userModifyView.user?userId=${user.userID}">修改         </a> 
					<a href="userDeleteDo.user?userId=${user.userID}">禁用            </a>
					<a href="userLookView.user?userId=${user.userID}">查看角色信息</a></td>
				</tr>
			  </c:forEach>
			  <myTag:pageTag url="userManageView.user"/>
			</table>
			
		</div>
	</div>
	<div class="clear"></div>

<jsp:include page="include/bottom.jsp"></jsp:include>
</body>
</html>
