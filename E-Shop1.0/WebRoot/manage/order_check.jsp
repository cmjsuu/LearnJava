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
		for ( var int = 2; int <= 6; int++) {
			if (int == status) {
				document.myForm.status.options[int-2].selected=true;
			}		
		}
	}
</script>
</head>
<body onload="selStatus()">
<jsp:include page="include/head-left.jsp"></jsp:include>
	<div class="main">
		<h2>订单审核</h2>
		<div class="manage">
			<form action="orderCheckDo.om" method="POST">
				<table class="form">
					<tr>
					<td><input type="hidden" name="oId" value="${orderVO.orderID}"/></td>
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
						<td>${orderVO.orderTotalPrice}</td>
					</tr>
					
					<tr>
						<td class="field">订单状态：</td>
						<td>
							<select name="status">
								<option value="2">未审核</option>
								<option value="3">无法交易</option>
								<option value="4">未发货</option>
								<option value="5">已发货</option>
								<option value="6">完成交易</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="field">收货地址：</td>
						<td>${orderVO.orderAddress}</td>
					</tr>
					<tr>
						<td class="field">收货人姓名：</td>
						<td>${orderVO.orderReceiveName}</td>
					</tr>
					<tr>
						<td class="field">收货人电话：</td>
						<td>${orderVO.orderTelephone}</td>
					</tr>
					<tr>
						
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
