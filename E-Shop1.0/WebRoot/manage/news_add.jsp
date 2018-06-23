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
		<h2>添加新闻</h2>
		<div class="manage">
			<form action="newsAddDo.nmm">
				<table class="form">
					<tr>
						<td class="field">新闻标题：</td>
						<td><input type="text" class="text" name="nTitle" value="${news.newsTitle}" /></td>
					</tr>
					<tr>
						<td class="field">新闻内容：</td>
						<td><textarea name="nContent">${news.newsContent}</textarea></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="添加" /></label></td>
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
