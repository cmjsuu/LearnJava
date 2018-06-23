<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="news-list">
	<h4>
		最新留言&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="messageBoardView.view">更多</a>
	</h4>
	<ul>
		<c:forEach items="${messageList}" var="message">
			<li>
				<a href="messageBoardView.view?mbId=${message.mbId}" target="_blank">${message.mbTitle}</a>
			</li>
		</c:forEach>
	</ul>
</div>
<div class="spacer"></div>
<div class="news-list">
	<h4>
		新闻动态&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="newsBoardView.view">更多</a>
	</h4>
	<ul>
		<c:forEach items="${newsList}" var="news">
			<li>
				<a href="newsBoardView.view?nId=${news.nId}" target="_blank">${news.nTitle}</a>
			</li>
		</c:forEach>
	</ul>
</div>
