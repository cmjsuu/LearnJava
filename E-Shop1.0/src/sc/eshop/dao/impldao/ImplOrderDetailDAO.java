package sc.eshop.dao.impldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sc.eshop.common.db.DBConnect;
import sc.eshop.dao.OrderDetailDAO;
import sc.eshop.vo.OrderDetailVO;

/**
 * @title 订单明细表DAO的实现类
 * @author 李建超 @ 2012-11-3 上午11:24:41
 */
public class ImplOrderDetailDAO implements OrderDetailDAO {
	/**
	 * 查询所有订单明细，返回List<OrderDetailVO>
	 */
	public List<OrderDetailVO> inquireAllOrderDetail() {
		String sql = "select OrderID,GoodsID,GoodsName,GoodsNumber,GoodPrice,GoodsSubTotal from OrderDetail";

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		OrderDetailVO orderDetail = null;
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();

		try {
			conn = DBConnect.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				orderDetail = new OrderDetailVO();
				orderDetail.setGoodsID(rs.getString("GoodsID"));
				orderDetail.setOrderID(rs.getInt("OrderID"));
				orderDetail.setGoodsName(rs.getString("GoodsName"));
				orderDetail.setGoodsNumber(rs.getInt("GoodsNumber"));
				orderDetail.setGoodPrice(rs.getInt("GoodPrice"));
				orderDetail.setGoodsSubTotal(rs.getInt("GoodsSubTotal"));
				list.add(orderDetail);
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
	 * 增加一条订单明细，返回boolean
	 */
	public boolean addOrderDetail(OrderDetailVO orderDetail) {
		String sql = "insert into OrderDetail(OrderID,GoodsID,GoodsName,GoodsNumber,GoodPrice,"
				+ "GoodsSubTotal) values(?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderDetail.getOrderID());
			pstmt.setString(2, orderDetail.getGoodsID());
			pstmt.setString(3, orderDetail.getGoodsName());
			pstmt.setInt(4, orderDetail.getGoodsNumber());
			pstmt.setDouble(5, orderDetail.getGoodPrice());
			pstmt.setDouble(6, orderDetail.getGoodsSubTotal());
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
	 * 按订单编号与商品编号删除一条订单明细，返回boolean
	 */
	public boolean deleteOrderDetail(int oId, String gId) {
		String sql = "delete OrderDetail where OrderID=? and GoodsID=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oId);
			pstmt.setString(2, gId);
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
	 * 修改一条订单明细，返回boolean
	 */
	public boolean updateOrderDetail(OrderDetailVO orderDetail) {
		String sql = "update OrderDetail set GoodsName=?,GoodsNumber=?,GoodPrice=?,GoodsSubTotal=?"
				+ " where OrderID =? and GoodsID =?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderDetail.getGoodsName());
			pstmt.setInt(2, orderDetail.getGoodsNumber());
			pstmt.setDouble(3, orderDetail.getGoodPrice());
			pstmt.setDouble(4, orderDetail.getGoodsSubTotal());
			pstmt.setInt(5, orderDetail.getOrderID());
			pstmt.setString(6, orderDetail.getGoodsID());
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
	 * 查询订单明细表的记录总数
	 * 
	 * @return 返回总记录数
	 */
	public int getCount() {
		String sql = "select count(1) as counter from OrderDetail";

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
	 * @return 返回一个集合List<OrderDetailVO>
	 */
	public List<OrderDetailVO> inquirePaging(int startRow, int endRow) {
		String sql = "SELECT OrderID,GoodsID,GoodsName,GoodsNumber,GoodPrice,"
				+ "GoodsSubTotal FROM OrderDetail";
		sql = DBConnect.PAGE_SELECT_SQL.replaceAll("#SELECTSQL", sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderDetailVO orderDetail = null;
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();

		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				orderDetail = new OrderDetailVO();
				orderDetail.setGoodsID(rs.getString("GoodsID"));
				orderDetail.setOrderID(rs.getInt("OrderID"));
				orderDetail.setGoodsName(rs.getString("GoodsName"));
				orderDetail.setGoodsNumber(rs.getInt("GoodsNumber"));
				orderDetail.setGoodPrice(rs.getInt("GoodPrice"));
				orderDetail.setGoodsSubTotal(rs.getInt("GoodsSubTotal"));
				list.add(orderDetail);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection(conn, pstmt, rs);
		}
		return list;
	}

	/**
	 * 按订单编号，分页查询该订单的明细
	 */
	public ArrayList<OrderDetailVO> inquirePagingByoId(String startRow, int endRow, String oId) {
		String sql = "SELECT OrderID,GoodsID,GoodsName,GoodsNumber,GoodPrice,"
			+ " GoodsSubTotal FROM OrderDetail where OrderID=?";
		sql = DBConnect.PAGE_SELECT_SQL.replaceAll("#SELECTSQL", sql);
		String s="OrderID";
		sql = sql.replaceAll("#Column", s);
		sql = sql.replaceAll("#pageSize", String.valueOf(endRow));
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderDetailVO orderDetail = null;
		ArrayList<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
	
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, oId);
			//pstmt.setInt(2, endRow);
			pstmt.setString(2, startRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				orderDetail = new OrderDetailVO();
				orderDetail.setGoodsID(rs.getString("GoodsID"));
				orderDetail.setOrderID(rs.getInt("OrderID"));
				orderDetail.setGoodsName(rs.getString("GoodsName"));
				orderDetail.setGoodsNumber(rs.getInt("GoodsNumber"));
				orderDetail.setGoodPrice(rs.getInt("GoodPrice"));
				orderDetail.setGoodsSubTotal(rs.getInt("GoodsSubTotal"));
				list.add(orderDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		} finally {
			DBConnect.closeConnection(conn, pstmt, rs);
		}
		return list;
	}

	/**
	 * 根据订单编号获得订单明细的记录总数
	 */
	public int getCountByoId(int oId) {
		String sql = "select count(1) as counter from OrderDetail where OrderID=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("counter");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			count = -1;
		} finally {
			DBConnect.closeConnection(conn, pstmt, rs);
		}
		return count;
	}
	
	 /**
	  * 根据订单编号查询该订单的明细
	  */
	 public List<OrderDetailVO> inquireOrderDetailByUser(int oid) {
			String sql ="select eod.OrderID,eod.GoodsName,eod.GoodsNumber,eod.GoodPrice,eod.GoodsSubTotal from OrderDetail eod where eod.OrderID=?";
			
			Connection conn = null;
			PreparedStatement st = null;
			ResultSet rs =null;
			OrderDetailVO orderDetail = null;
			List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
					
			try {
				conn = DBConnect.getConnection();
				st = conn.prepareStatement(sql);
				st.setInt(1, oid);
				rs = st.executeQuery();
				while(rs.next()){
					orderDetail = new OrderDetailVO();
					orderDetail.setOrderID(rs.getInt("OrderID"));
					orderDetail.setGoodsName(rs.getString("GoodsName"));
					orderDetail.setGoodsNumber(rs.getInt("GoodsNumber"));
					orderDetail.setGoodPrice(rs.getInt("GoodPrice"));
					orderDetail.setGoodsSubTotal(rs.getInt("GoodsSubTotal"));
					list.add(orderDetail);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				list = null;
			}
			return list;
		}

}
