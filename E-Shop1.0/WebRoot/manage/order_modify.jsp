<%@page contentType="text/html;charset=UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 </title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/function-manage.js"></script>
<script type="text/javascript">
	// 用于显示用户状态的下拉框
	function selStatus(){
		var status = "${orderVO.orderStatus}";
		//alert(status);
		for ( var int1 = 2; int1 <= 6; int1++) {
			if (int1 == status) {
				document.myForm.status.options[int-2].selected=true;
			}		
		}
	}
</script>
</head>
<body onload="selStatus()">
<jsp:include page="include/head-left.jsp"></jsp:include>
	<div class="main">
		<h2>修改订单</h2>
		<div class="manage">
			<form action="orderModifyDo.om" method="POST" name="myForm">
				<table class="form">
					<tr>
						<td class="field">订单编号：</td>
						<td>${orderVO.orderID}</td>
					</tr>
					<tr>
						<td class="field">用户账号：</td>
						<td>${orderVO.orderSendAccount}</td>
					</tr>
					<tr>
						<td class="field">下单时间：</td>
						<td>${orderVO.orderTime}</td>
					</tr>
					<tr>
						<td class="field">订单总价：</td>
						<td><input type="text" class="text" name="totalprice" value="${orderVO.orderTotalPrice}" /></td>
					</tr>
					
					<tr>
						<td class="field">订单状态：</td>
						<td>${orderVO.orderStatus}</td>
					</tr>
					<tr>
						<td class="field">收货地址：</td>
						<td><input type="text" class="text" name="address" value="${orderVO.orderAddress}" /></td>
					</tr>
					<tr>
						<td class="field">收货人姓名：</td>
						<td><input type="text" class="text" name="receivename" value="${orderVO.orderReceiveName}"/></td>
					</tr>
					<tr>
						<td class="field">收货人电话：</td>
						<td><input type="text" class="text" name="telephone" value="${orderVO.orderTelephone}"/></td>
					</tr>
					<tr>
						<td><input type="hidden" name="oId" value="${orderVO.orderID}"/></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="更新" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>

<jsp:include page="include/bottom.jsp"></jsp:include>
</body>
</html>
