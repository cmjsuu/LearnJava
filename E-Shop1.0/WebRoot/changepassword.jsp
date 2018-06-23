<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%
	String uPassword = (String)request.getParameter("oldpwd");
	System.out.println("upwd = "+uPassword);
 %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<script type="text/javascript">

</script>
</head>
<body>


<jsp:include page="/include/head.jsp"></jsp:include>

<div id="register" class="wrap" align="center"">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>密码修改中心</h1>
			<form id="loginForm" method="post" action="changePasswordDo.userInfo" onsubmit="checkForm(this)">
				<table>
					 
					<tr>
						<td class="field">旧密码：</td>
						
						<td><input class="text" type="password" id="oldpassWord" name="oldpassWord" onfocus="FocusItem(this)" onblur="checkPwd(this,<%=uPassword %>);" /><span></span></td>
					</tr>
					<tr>
						<td class="field">新密码：</td>
						<td><input class="text" type="password" id="newpassWord" name="newpassWord" onfocus="FocusItem(this)" onblur="checkPwd(this,uPassword);" /><span></span></td>
					</tr>
					<tr>
						<td class="field">确认密码：</td>
						<td><input class="text" type="password" id="aginpassWord" name="aginpassWord" onfocus="FocusItem(this)" onblur="checkPwd(this,uPassword);" /><span></span></td>
					</tr>
					<tr>
						<td class="field">验证码：</td>
						<td><input class="text verycode" type="text" name="veryCode" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><img id="veryCode" src="" /><span></span></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-green"><input type="submit" name="submit" value="确定" /></label>&nbsp;
						   <label class="ui-green"><input type="button" name="register" value="取消" /></label>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<jsp:include page="/include/bottom.jsp"></jsp:include>
</body>
</html>
