package sc.eshop.dao;

import java.util.ArrayList;
import java.util.List;

import sc.eshop.vo.OrderDetailVO;

/**
 * 订单明细表的DAO
 * @author yxf
 *
 */
public interface OrderDetailDAO extends DAO {


	/**
	 * 查询所有订单明细，返回List<OrderDetailVO>
	 */
	public List<OrderDetailVO> inquireAllOrderDetail();
	
	/**
	 * 查询订单明细表的记录总数
	 */
	public int getCount();
	
	/**
	 * 分页查询订单明细信息，返回List<OrderDetailVO>
	 */
	public List<OrderDetailVO> inquirePaging(int startRow, int endRow);
	
	/**
	 * 根据订单编号获得订单明细的记录总数
	 */
	public int getCountByoId(int oId);
	
	/**
	 * 按订单编号，分页查询该订单的明细
	 */
	public ArrayList<OrderDetailVO> inquirePagingByoId(String startRow, int endRow, String oId);
	
	
	/**
	 * 增加一条订单明细，返回boolean
	 */
	public boolean addOrderDetail(OrderDetailVO orderDetail);
	
	/**
	 * 按订单编号与商品编号删除一条订单明细，返回boolean
	 */
	public boolean deleteOrderDetail(int oId, String gId);
	
	/**
	 * 修改一条订单明细，返回boolean
	 */
	public boolean updateOrderDetail(OrderDetailVO orderDetail);
	
}
