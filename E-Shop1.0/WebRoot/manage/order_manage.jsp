<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/myTagLib.tld" prefix="myTag"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理</title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/function-manage.js"></script>
</head>
<body>
<jsp:include page="include/head-left.jsp"></jsp:include>
	<div class="main">
		<h2>订单管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>订单编号</th>
                    <th>用户账号</th>
                    <th>发货地址</th>
                    <th>联系电话</th>
                    <th>订单明细</th>
                    <th>订单状态</th>
					<th>操作</th>
	
				</tr>
				<c:forEach items="${orderViews}" var="orderView">
                        <tr>
                            <td>
                            ${orderView.orderID}
                            </td>
                            <td>
                                ${orderView.orderSendAccount}
                            </td>
                            <td>
                                ${orderView.orderAddress}
                            </td>
                            <td>
                                ${orderView.orderTelephone}
                            </td>
                            <td>
                                <a href="orderDetailView.om?oId=${orderView.orderID}">查看</a>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${orderView.orderStatus == 2}">
                                        	未审核
                                    </c:when>
                                    <c:when test="${orderView.orderStatus == 3}">
                                        	无法交易
                                    </c:when>
                                    <c:when test="${orderView.orderStatus == 4}">
                                      		  未发货
                                    </c:when>
                                    <c:when test="${orderView.orderStatus == 5}">
                                        	已发货
                                    </c:when>
                                    <c:when test="${orderView.orderStatus == 6}">
                                        		完成交易
                                    </c:when>
                                    <c:otherwise>
                                       		状态不明
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                
                                    <a href="orderCheckView.om?oId=${orderView.orderID}">审核</a>
                                    
                                    <a href="orderModifyView.om?oId=${orderView.orderID}">修改</a>
                                    
                                    <a href="orderDeleteDo.om?oId=${orderView.orderID}">删除</a>
                          
                            </td>
                        </tr>
                    </c:forEach>
			</table>
		</div>
	</div>
	<myTag:pageTag url="typeManageView.type"/>
	<div class="clear"></div>

<jsp:include page="include/bottom.jsp"></jsp:include>
</body>
</html>
