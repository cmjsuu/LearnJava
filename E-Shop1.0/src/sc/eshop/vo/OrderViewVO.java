package sc.eshop.vo;

import java.sql.Date;

/**
 * title 后台订单显示用VO类
 * 
 * @author 李建超 2012-11-8 上午9:40:38
 */
public class OrderViewVO implements VO {

	// 订单编号
		private String OrderID;
		
		// 订单所属用户编号
		private String UserID;
		
		// 下单时间
		private Date OrderTime;
		
		// 订单总价
		private float OrderTotalPrice;
		
		// 订单状态
		private int OrderStatus;
		
		// 送货地址
		private String OrderAddress;
		
		// 收货人电话
		private String OrderTelephone;
		
		//收货人姓名
		private String OrderReceiveName;

		private String OrderSendAccount;
		public String getOrderID() {
			return OrderID;
		}

		public void setOrderID(String orderID) {
			OrderID = orderID;
		}

		public String getUserID() {
			return UserID;
		}

		public void setUserID(String userID) {
			UserID = userID;
		}

		public Date getOrderTime() {
			return OrderTime;
		}

		public void setOrderTime(Date orderTime) {
			OrderTime = orderTime;
		}

		public float getOrderTotalPrice() {
			return OrderTotalPrice;
		}

		public void setOrderTotalPrice(float orderTotalPrice) {
			OrderTotalPrice = orderTotalPrice;
		}

		public int getOrderStatus() {
			return OrderStatus;
		}

		public void setOrderStatus(int orderStatus) {
			OrderStatus = orderStatus;
		}

		public String getOrderAddress() {
			return OrderAddress;
		}

		public void setOrderAddress(String orderAddress) {
			OrderAddress = orderAddress;
		}

		public String getOrderReceiveName() {
			return OrderReceiveName;
		}

		public void setOrderReceiveName(String orderReceiveName) {
			OrderReceiveName = orderReceiveName;
		}

		public String getOrderTelephone() {
			return OrderTelephone;
		}

		public void setOrderTelephone(String orderTelephone) {
			OrderTelephone = orderTelephone;
		}

		public String getOrderSendAccount() {
			return OrderSendAccount;
		}

		public void setOrderSendAccount(String orderSendAccount) {
			OrderSendAccount = orderSendAccount;
		}



}
