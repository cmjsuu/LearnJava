<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<link href="./css/login.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main-login">
	<div class="login-content">	
	<h2>用户登录</h2>
	
    <form action="userLoginDo.user" method="post" id="loginForm" name="login-form">
    <div class="login-info">
	<span class="user">&nbsp;</span>
	<input name="userName" id="username" type="text"  class="login-input">
	</div>
    <div class="login-info">
	<span class="pwd">&nbsp;</span>
	<input name="passWord" id="password" type="password"  class="login-input">
	</div>
    <div class="login-oper">
	<input style="margin:1px 10px 0px 2px; float:left;" name="" type="checkbox" value="" checked="checked"><span>记住密码</span>
	</div>
    <div class="login-oper">
	<input name="submit" type="submit" value="登 录" class="login-btn">
	<input name="submit" type="submit" value="重 置" class="login-reset">
	</div>
    </form>
    </div>
   
</div>   
</body>
</html>