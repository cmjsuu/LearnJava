<%@page contentType="text/html;charset=UTF-8"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>E-Shop - 聊天室</title>
		<link type="text/css" rel="stylesheet" href="css/style.css" />
		<script type="text/javascript">
			function setReceiver(obj) {
				document.getElementById("reply-to").innerHTML = "接收人：" + obj.innerHTML;
			}
		</script>
	</head>
	<body>
		<div id="chat-room">
			<div class="lefter">
				<h2>
					<br>
				</h2>
				<h2>
					<br>
				</h2>
				<h2>
					在线用户列表
				</h2>
				<ul>
					<li>
						<a href="#" onclick="setReceiver(this);">张三</a>
					</li>
				</ul>
			</div>
			<div class="righter">
				<div id="history">
					<p>
						aaa
					</p>
					<p>
						aaa
					</p>
				</div>
				<div class="spacer"></div>
				<div id="reply-to">
					接收人：所有
				</div>
				<div class="reply">
					<input type="text" class="text" name="content" />
					<label class="ui-blue">
						<input type="button" value="发送" />
					</label>
				</div>
			</div>
		</div>
	</body>
</html>
