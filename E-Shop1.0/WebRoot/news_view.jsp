<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/myTagLib.tld" prefix="myTag"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>E-Shop - 首页</title>
		<link type="text/css" rel="stylesheet" href="css/style.css" />
		<script type="text/javascript" src="js/ajax.js"></script>
		<script type="text/javascript">

var xmlHttpRequest;
// 動態顯示新聞信息
function shownews(nId){
	if (nId != null) {
		try {
		xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
		}
		catch (e1) {
			try {
				xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
			}
			catch (e2) {
				xmlHttpRequest = false;
			}
		}
		if (!xmlHttpRequest && typeof XMLHttpRequest != "undefined") {
			xmlHttpRequest = new XMLHttpRequest();
		}
	
		xmlHttpRequest.open("POST","newsView.view",true);
		xmlHttpRequest.setRequestHeader("content-type","application/x-www-form-urlencoded");		
			
		xmlHttpRequest.onreadystatechange = function(){
			if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200){
			var result = xmlHttpRequest.responseText;
			result = eval(result);
			var inner1 = document.getElementById("news");
			inner1.innerHTML = "";//清空原有的元素内容
			addMsg1(result);
			}
		};
		xmlHttpRequest.send("nId="+nId);
	}
}
	
function addMsg1(msg){
	var inner1 = document.getElementById("news");
	var h1Obj = document.createElement("h3");
	h1Obj.innerHTML = msg[0];
	inner1.appendChild(h1Obj);
	var divObj = document.createElement("div");
	divObj.innerHTML = msg[1];
	inner1.appendChild(divObj);
	
	var divObj = document.createElement("div");
	divObj.innerHTML = "發佈時間："+msg[2];
	inner1.appendChild(divObj);
}

function showmsgs(){
		
	xmlHttpRequest.open("POST","msgServlet",true);
	xmlHttpRequest.setRequestHeader("content-type","application/x-www-form-urlencoded");	
		
	xmlHttpRequest.onreadystatechange = function(){
		if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200){
			var result = xmlHttpRequest.responseText;
			result = eval(result);
			var inner1 = document.getElementById("msgs");
			inner1.innerHTML = "";//清空原有的元素内容
			addMsg(result);
		}
	};
	
	xmlHttpRequest.send("mbId="+mbId);
}

function addMsg(msg){
	var inner1 = document.getElementById("news");
	var h1Obj = document.createElement("h2");
	h1Obj.innerHTML = msg[0];
	inner1.appendChild(h1Obj);
	var divObj = document.createElement("div");
	divObj.innerHTML = msg[1];
	inner1.appendChild(divObj);
	
	var divObj = document.createElement("div");
	divObj.innerHTML = "發佈時間："+msg[2];
	inner1.appendChild(divObj);
}
</script>
	</head>
	<body onload="shownews(${nId})">
		<jsp:include page="include/head.jsp"></jsp:include>
		<div id="position" class="wrap">
			您现在的位置：
			<a href="index">E-Shop</a> &gt; 阅读新闻
		</div>
		<div id="main" class="wrap">
			<div class="left-side">
				<div class="spacer"></div>
				<div class="news-list">
					<h4>
						<a>新闻动态</a>
					</h4>
					<ul>
						<c:forEach items="${allNews}" var="news">
							<li>
								<a onclick="shownews(${news.nId})">${news.nTitle}</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="right-main">
				<div id="news" class="right-main">
					<h1>最新公告</h1>
					<hr>
					<c:forEach items="${newsList}" var="newslist">
						<h4>
							${newslist.nTitle}
						</h4>
						<div>
							${newslist.nContent}
						</div>
						<div>
							發佈時間：${newslist.nTime}
						</div>
						<br/>
					</c:forEach>
				</div>
				<myTag:pageTag url="newsBoardView.view" />
			</div>
			<div class="clear"></div>
		</div>
		<jsp:include page="include/bottom.jsp"></jsp:include>
	</body>
</html>
