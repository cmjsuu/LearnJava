<%@page contentType="text/html;charset=UTF-8"%>

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
		<h2>修改用户</h2>
		<div class="manage">
			<form action="userModifyDo.user">
				<table class="form">
					<tr>
						<td class="field">用户账户：</td>
						<td><input type="text" class="text" name="accounts" value="${userVO.accounts}" readonly="readonly" />
						<input type="hidden" class="text" name="userId" value="${userVO.userID}" /></td>
					</tr>
					<tr>
						<td class="field">性别：</td>
						<td><input type="radio" name="sex" value="男" checked="checked" />男 
						       <input type="radio" name="sex" value="女" />女</td>
					</tr>
					<tr>
						<td class="field">出生日期：</td>
						<td>
							<input type="text" name="year" size="4" maxLength="4" value="${year}">年
							<input type="text" name="month" size="2" maxLength="2" value="${month}">月
							<input type="text" name="day" size="2" maxLength="2" value="${day}">日
						</td>
					</tr>
					<tr>
						<td class="field">电话号码：</td>
						<td><input type="text" class="text" name="telephone" value="${userVO.userTelephone}" /></td>
					</tr>
					<tr>
						<td class="field">送货地址：</td>
						<td><input type="text" class="text" name="address" value="${userVO.userAddress}" /></td>
					</tr>
					<tr>
						<td class="field">用户邮箱：</td>
						<td><input type="text" class="text" name="email" value="${userVO.userEmail}" /></td>
					</tr>
					<tr>
						<td class="field">用户密码：</td>
						<td><input type="text" class="text" name="password" value="${userVO.userPassword}" /></td>
					</tr>
					<tr>
						<td class="field">用户状态：</td>
						<td><input type="text" class="text" name="status" value="${userVO.userStatus}" /></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="更新" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<jsp:include page="include/bottom.jsp"></jsp:include>
</body>
</html>
