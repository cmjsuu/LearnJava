<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>E-Shop网 - 个人中心</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/ajax.js"></script>
</head>
<body>
<jsp:include page="include/head.jsp"></jsp:include>
<div id="register" class="wrap" align="center">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>会员资料</h1>
			<ul class="steps clearfix">
				<li class="current"><em></em>我的信息</li>
			</ul>
			<form id="regForm" method="post" action="renewinfo" onsubmit="return checkForm(this);">
				<table>
					<tr>
            			<td height="40" colspan="2" valign="bottom"><span class="reg">我的个人资料</span></td>
          			</tr>
					<tr>
						<td class="field">用户名：</td>
						<td><input class="text" type="text" name="userName" id ="userName"  value="${userVO.uName} " readonly="readonly"/>*此處不可更改*<span></span></td>
					</tr>
					<tr>
					
						<td class="field">性別：</td>
						<td >
							<c:if test="${userVO.uSex=='男'}">
							<input class="radio" type="radio"  name="sex" value="男" checked/>男&nbsp;&nbsp;&nbsp;&nbsp;
							<input class="radio" type="radio"  name="sex" value="女" />女
							</c:if>
							<c:if test="${userVO.uSex=='女'}">
							<input class="radio" type="radio"  name="sex" value="男" />男
							<input class="radio" type="radio"  name="sex" value="女" checked/>女
							</c:if>
						</td>
					</tr>
					<tr>
						<td class="field">用户昵称：</td>
						<td><input class="text" type="text" name="nickname" onfocus="FocusItem(this)" onblur="CheckItem(this);" value="${userVO.uNickname}" /><span></span></td>
					</tr>
					<tr>
						<td class="field">出生日期：</td>
						<td><input class="text" type="text" name="birthday" onfocus="FocusItem(this)" onblur="CheckItem(this);" value="${userVO.uBirthday}" /><span></span></td>
					</tr>
					<tr>
						<td class="field">用戶地址：</td>
						<td><input class="text" type="text" name="address" onfocus="FocusItem(this)" onblur="CheckItem(this);" value="${userVO.uAddress}" /><span></span></td>
					</tr>
					<tr>
						<td class="field">联系方式：</td>
						<td><input class="text" type="text" name="connection" onfocus="FocusItem(this)" onblur="CheckItem(this);" value="${userVO.uTelephone}" /><span></span></td>
					</tr>
					<tr>
						<td class="field">电子邮箱：</td>
						<td><input class="text" type="text" name="email" onfocus="FocusItem(this)" onblur="CheckItem(this);" value="${userVO.uEmail}" /><span></span></td>
					</tr>
					
					<tr align="center"">
						<td colspan="2"><label class="ui-green"><input type="submit" name="submit" value="更新" /></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label class="ui-green"><input type="reset" name="reset" value="重置" /></label></td>
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
