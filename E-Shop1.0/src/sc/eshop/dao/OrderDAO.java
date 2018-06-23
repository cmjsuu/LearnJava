package sc.eshop.dao;

import java.util.List;

import sc.eshop.vo.OrderVO;

/**
 * 订单表的DAO
 * @author yxf
 *
 */
public interface OrderDAO extends DAO {

	/**
	 * 查询所有订单，返回List<OrderVO>
	 */
	public List<OrderVO> inquireAllOrder();
	
	/**
	 * 根据订单编号查询订单详细信息
	 */
	public OrderVO inquireOrderById(int oId);
	
	/**
	 * 查询订单的记录总数
	 */
	public int getCount();
	
	/**
	 * 分页查询订单信息，返回List<OrderVO>
	 */
	public List<OrderVO> inquirePaging(int startRow, int endRow);
	
	/**
	 * 增加一条订单，返回boolean
	 */
	public boolean addOrder(OrderVO order);
	
	/**
	 * 按订单编号删除一条订单，返回boolean
	 */
	public boolean deleteOrder(int oId);
	
	/**
	 * 修改一条订单，返回boolean
	 */
	public boolean updateOrder(OrderVO order);
	
}
