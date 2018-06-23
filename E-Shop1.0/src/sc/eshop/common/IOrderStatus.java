package sc.eshop.common;

/**
 * 确认订单状态的接口
 * @author yxf
 *
 */
public interface IOrderStatus {

	/**
	 *  未结算未保存状态
	 */
	int UNSUBMIT_UNSAVED = 0;
	
	/**
	 *  未结算已保存状态
	 */
	int UNSUBMIT_SAVED = 1;
	
	/**
	 *  已结算但管理员尚未审核状态
	 */
	int SUBMIT_UNCHECK = 2;
	
	/**
	 *  已结算已审核，但因为各种原因交易无法完成
	 */
	int CAN_NOT_TRADE = 3;
	
	/**
	 *  已结算已审核尚未发货状态
	 */
	int UNDELIVER = 4;
	
	/**
	 *  已结算已审核已发货状态
	 */
	int DELIVER = 5;
	
	/**
	 *  客户收货，交易完成状态
	 */
	int TRADE_ACCOMPLISH = 6;
	
	
}
