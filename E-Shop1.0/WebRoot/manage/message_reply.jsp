<%@page contentType="text/html;charset=UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 </title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/function-manage.js"></script>
</head>
<body>
<jsp:include page="include/head-left.jsp"></jsp:include>
	<div class="main">
		<h2>回复留言</h2>
		<div class="manage">
			<form action="messageReplyDo.nmm">
				<table class="form">
					<tr>
						<td><input type="hidden"  name="mbId" value="${message.messageID}" /></td>
						<td><input type="hidden"  name="userId" value="${message.userID}" /></td>
					</tr>
					<tr>
						<td class="field">留言姓名：</td>
						<td>
						<textarea name="nickname" readonly="readonly">${message.accounts}</textarea>
						</td>
					</tr>
					<tr>
						<td class="field">留言内容：</td>
						<td>
						<textarea name="mbcontent" readonly="readonly">${message.messageContent}</textarea>
						</td>
					</tr>
					<tr>
						<td class="field">回复内容：</td>
						<td><textarea name="reply">${message.messageReply}</textarea></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="回复" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>

<jsp:include page="include/bottom.jsp"></jsp:include>
</body>
</html>
