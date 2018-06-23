package sc.eshop.dao.impldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sc.eshop.common.db.DBConnect;
import sc.eshop.dao.OrderDAO;
import sc.eshop.vo.OrderVO;
import sc.eshop.vo.OrderViewVO;

/**
 * @title 订单表 DAO的实现类
 * @author 李建超 @ 2012-11-3 上午10:33:39
 */
public class ImplOrderDAO implements OrderDAO {
	/**
	 * 查询所有订单，返回List<OrderVO>
	 */
	public List<OrderVO> inquireAllOrder() {
		String sql = "select OrderID,UserID,OrderTime,OrderTotalPrice,OrderStatus,"
				+ " OrderAddress,OrderTelephone,OrderReceiveName from OrderBase";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		OrderVO orderVO = null;
		List<OrderVO> list = new ArrayList<OrderVO>();
		try {
			conn = DBConnect.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrderID(rs.getString("OrderID"));
				orderVO.setUserID(rs.getString("UserID"));
				orderVO.setOrderTime(rs.getDate("OrderTime"));
				orderVO.setOrderTotalPrice(rs.getFloat("OrderTotalPrice"));
				orderVO.setOrderStatus(rs.getInt("OrderStatus"));
				orderVO.setOrderAddress(rs.getString("OrderAddress"));
				orderVO.setOrderTelephone(rs.getString("OrderTelephone"));
				orderVO.setOrderReceiveName(rs.getString("OrderReceiveName"));
				list.add(orderVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
			return list;
		} finally {
			DBConnect.closeConnection(conn, st, rs);
		}
		return list;
	}
	
	/**
	 * 增加一条订单，返回boolean
	 */
	public boolean addOrder(OrderVO order) {
		String sql = "INSERT INTO OrderBase(OrderID,UserID,OrderTime,OrderTotalPrice,"
				+ "OrderStatus,OrderAddress,OrderTelephone,OrderReceiveName) " +
				" VALUES(?,?,getdate(),?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order.getOrderID());
			pstmt.setString(2, order.getOrderID());
			pstmt.setFloat(3, order.getOrderTotalPrice());
			pstmt.setInt(4, order.getOrderStatus());
			pstmt.setString(5, order.getOrderAddress());
			pstmt.setString(6, order.getOrderTelephone());
			pstmt.setString(7, order.getOrderReceiveName());
			pstmt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			DBConnect.closeConnection(conn, pstmt, null);
		}
		return flag;
	}

	/**
	 * 按订单编号删除一条订单，返回boolean
	 */
	public boolean deleteOrder(int oId) {
		// 删除与其具有外键关系的子记录的语句
		String sql1 = "delete from OrderDetail where OrderID=?";
		String sql2 = "delete from OrderBase where OrderID=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag = false;

		try {
			conn = DBConnect.getConnection();
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, oId);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, oId);
			pstmt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 修改一条订单，返回boolean
	 */
	public boolean updateOrder(OrderVO order) {
		String sql = "update OrderBase set OrderTotalPrice=?,OrderAddress=?,OrderTelephone=? ,OrderReceiveName=? where OrderID=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, order.getOrderTotalPrice());
			pstmt.setString(2, order.getOrderAddress());
			pstmt.setString(3, order.getOrderTelephone());
			pstmt.setString(4, order.getOrderReceiveName());
			pstmt.setString(5, order.getOrderID());
			pstmt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	/**
	 * 修改一条订单状态，返回boolean
	 */
	public boolean updateOrderStatus(OrderVO order) {
		String sql = "update OrderBase set OrderStatus=? where OrderID=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order.getOrderStatus());
			pstmt.setString(2, order.getOrderID());
			pstmt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	/**
	 * 查询订单的记录总数
	 */
	public int getCount() {
		String sql = "select count(1) as counter from OrderBase";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DBConnect.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt("counter");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection(conn, st, rs);
		}
		return count;
	}

	/**
	 * 分页查询
	 * 
	 * @param startRow
	 *            起始行
	 * @param endRow
	 *            终止行
	 * @return 返回一个集合List<GoodsVO>
	 */
	public List<OrderVO> inquirePaging(int startRow, int endRow) {
		String sql = "SELECT OrderID,UserID,OrderTime,OrderTotalPrice,OrderStatus,"
				+ " OrderAddress,OrderTelephone,OrderReceiveName FROM OrderBase";
		sql = DBConnect.PAGE_SELECT_SQL.replaceAll("#SELECTSQL", sql);
		String s="OrderTime";
		sql = sql.replaceAll("#Column", s);
		sql = sql.replaceAll("#pageSize", String.valueOf(endRow));
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO orderVO = null;
		List<OrderVO> orderList = new ArrayList<OrderVO>();
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, endRow);
			pstmt.setInt(1, startRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrderID(rs.getString("OrderID"));
				orderVO.setUserID(rs.getString("UserID"));
				orderVO.setOrderTime(rs.getDate("OrderTime"));
				orderVO.setOrderTotalPrice(rs.getFloat("OrderTotalPrice"));
				orderVO.setOrderStatus(rs.getInt("OrderStatus"));
				orderVO.setOrderAddress(rs.getString("OrderAddress"));
				orderVO.setOrderTelephone(rs.getString("OrderTelephone"));
				orderVO.setOrderReceiveName(rs.getString("OrderReceiveName"));
				orderList.add(orderVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection(conn, pstmt, rs);
		}
		return orderList;
	}
	
	/**
	 * 根据订单编号查询订单详细信息
	 */
	public OrderVO inquireOrderById(int oId) {
	
		String sql ="select o.OrderID,u.Accounts,o.OrderTime,o.OrderTotalPrice,o.OrderStatus,o.OrderAddress,o.OrderTelephone ,o.OrderReceiveName from OrderBase o,UserInfo u where o.OrderID=? AND o.UserID = u.UserID";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO orderVO = null;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrderID(rs.getString("OrderID"));
				//orderVO.setUserID(rs.getString("UserID"));
				orderVO.setOrderTime(rs.getDate("OrderTime"));
				orderVO.setOrderTotalPrice(rs.getFloat("OrderTotalPrice"));
				orderVO.setOrderStatus(rs.getInt("OrderStatus"));
				orderVO.setOrderAddress(rs.getString("OrderAddress"));
				orderVO.setOrderTelephone(rs.getString("OrderTelephone"));
				orderVO.setOrderReceiveName(rs.getString("OrderReceiveName"));
				orderVO.setOrderSendAccount(rs.getString("Accounts"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			orderVO = null;
		} finally {
			DBConnect.closeConnection(conn, pstmt, rs);
		}
		return orderVO;
	}
	
	/**
	 * 根据订单编号和发货人姓名查询OrderView信息
	 * 分页显示
	 */
	public List<OrderViewVO> inquireByIdAndName(int oId,String userName,int startRow,int endRow){
		
		String sql = "select o.OrderID,u.Accounts,o.OrderStatus,o.OrderAddress,o.OrderTelephone " +
			"from OrderBase o,UserInfo u where o.OrderID=? and u.Accounts=? and o.UserID = u.UserID";
		sql = DBConnect.PAGE_SELECT_SQL.replaceAll("#SELECTSQL", sql);
		String s="oid";
		sql = sql.replaceAll("#Column", s);
		sql = sql.replaceAll("#pageSize", String.valueOf(endRow));
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderViewVO orderView = null;
		List<OrderViewVO> orderViews = null;
		
		try {
			orderViews = new ArrayList<OrderViewVO>();
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oId);
			pstmt.setString(2, userName);
			//pstmt.setInt(3, endRow);
			pstmt.setInt(3, startRow);
			rs = pstmt.executeQuery();
			if(rs.next()){
				orderView = new OrderViewVO();
				orderView.setOrderSendAccount(rs.getString("Accounts"));
				orderView.setOrderID(rs.getString("OrderID"));
				orderView.setOrderStatus(rs.getInt("OrderStatus"));
				orderView.setOrderAddress(rs.getString("OrderAddress"));
				orderView.setOrderTelephone(rs.getString("OrderTelephone"));
				orderViews.add(orderView);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			orderViews = null;
		}
		return orderViews;
	}
	
	/**
	 * 连表查询用户信息和该用户的订单信息
	 * 分页查询
	 * @param startRow 起始行
	 * @param endRow 终止行
	 * @return  返回一个OrderViewVO的集合
	 */
	public List<OrderViewVO> inquireOrderByUser(int startRow, int endRow)
	{
		String sql = "SELECT o.OrderID,u.Accounts,o.OrderAddress,o.OrderStatus,o.OrderTelephone,o.OrderReceiveName " +
				"FROM UserInfo u,OrderBase o WHERE u.UserID = o.UserID ";
		
		sql = DBConnect.PAGE_SELECT_SQL.replaceAll("#SELECTSQL",sql);
		String s="OrderID";
		sql = sql.replaceAll("#Column", s);
		sql = sql.replaceAll("#pageSize", String.valueOf(endRow));
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderViewVO orderView = null;
		List<OrderViewVO> orderViewList = new ArrayList<OrderViewVO>();	
		
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, endRow);
			pstmt.setInt(1, startRow);
			rs = pstmt.executeQuery();
			while(rs.next()){
				orderView = new OrderViewVO();
				
				orderView.setOrderSendAccount(rs.getString("Accounts"));
				orderView.setOrderID(rs.getString("OrderID"));
				//orderView.setUserID(rs.getString("UserID"));
				//orderView.setOrderTime(rs.getDate("OrderTime"));
				//orderView.setOrderTotalPrice(rs.getDouble("OrderTotalPrice"));
				orderView.setOrderStatus(rs.getInt("OrderStatus"));
				orderView.setOrderAddress(rs.getString("OrderAddress"));
				orderView.setOrderTelephone(rs.getString("OrderTelephone"));
				orderView.setOrderReceiveName(rs.getString("OrderReceiveName"));
				orderViewList.add(orderView);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderViewList;
	}

	/**
	 * 找到最新的订单编号
	 */
	public int getLatestId(){
		int result = -1;
		String sql = "select max(OrderID) from OrderBase";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnect.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally {
			DBConnect.closeConnection(conn, stmt, rs);
		}
		return result;
	}
	
	/**
	 * 根据用户编号查询单个用户的订单信息
	 */
	public List<OrderViewVO> inquireOrderByUserId(String userId)
	{
		String sql = "SELECT o.OrderID,u.Accounts,o.OrderAddress,o.OrderStatus,o.OrderTelephone,o.OrderReceiveName " +
				"FROM UserInfo u,OrderBase o WHERE u.UserID = o.UserID and u.UserID=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderViewVO orderView = null;
		List<OrderViewVO> orderViewList = new ArrayList<OrderViewVO>();	
		
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				orderView = new OrderViewVO();
				orderView.setOrderSendAccount(rs.getString("Accounts"));
				orderView.setOrderID(rs.getString("OrderID"));
				orderView.setOrderStatus(rs.getInt("OrderStatus"));
				orderView.setOrderAddress(rs.getString("OrderAddress"));
				orderView.setOrderTelephone(rs.getString("OrderTelephone"));
				orderView.setOrderReceiveName(rs.getString("OrderReceiveName"));
				orderViewList.add(orderView);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			orderViewList = null;
		}
		return orderViewList;
	}
	
	/**
	 * 按用户编号查询订单
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List ininquireOrderIds(String userId){
		String sql = "select OrderID from OrderBase where UserID=?";
		List orderIds = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int oId = -1;
		try{
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				oId = rs.getInt("OrderID");
				orderIds.add(oId);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
			orderIds = null;
		}finally{
			DBConnect.closeConnection(conn, pstmt, rs);
		}
		return orderIds;
	}
	
}













