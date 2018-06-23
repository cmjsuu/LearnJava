package sc.eshop.dao.impldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sc.eshop.common.db.DBConnect;
import sc.eshop.dao.GoodsDAO;
import sc.eshop.vo.GoodsVO;

/**
 * @title 商品信息DAO的实现类
 * @author 李建超 @ 2012-11-2 下午9:36:49
 */
public class ImplGoodsDAO implements GoodsDAO {

	/**
	 * 查询所有商品，返回List<GoodsVO>
	 */
	public List<GoodsVO> inquireAllGoods() {
		String sql = "SELECT GoodsID,GoodsTypeID,GoodsName,GoodsDesigner,GoodsPrice,GoodsStock,"
				+ " GoodsDescribe,GoodsSales,GoodsUrl FROM GoodsInfo";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		GoodsVO goodsVO = null;
		List<GoodsVO> goodsList = new ArrayList<GoodsVO>();
		try {
			conn = DBConnect.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				goodsVO = new GoodsVO();
				goodsVO.setGoodsID(rs.getString("GoodsID"));
				goodsVO.setGoodsTypeID(rs.getString("GoodsTypeID"));
				goodsVO.setGoodsName(rs.getString("GoodsName"));
				goodsVO.setGoodsDesigner(rs.getString("GoodsDesigner"));
				goodsVO.setGoodsPrice(rs.getFloat("GoodsPrice"));
				goodsVO.setGoodsStock(rs.getInt("GoodsStock"));
				goodsVO.setGoodsDescribe(rs.getString("GoodsDescribe"));
				//goodsVO.setgSellTime(rs.getDate("gsell_time"));
				goodsVO.setGoodsSales(rs.getInt("GoodsSales"));
				goodsVO.setGoodsUrl(rs.getString("GoodsUrl"));	
				goodsList.add(goodsVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection(conn, st, rs);
		}
		return goodsList;
	}

	/**
	 * 查询某类商品，返回List<GoodsVO>
	 */
	public List<GoodsVO> inquireByType(String stid) {
		String sql = "SELECT GoodsID,GoodsName,T.GoodsTypeName,GoodsDesigner,GoodsPrice,GoodsStock,GoodsDescribe,GoodsSales,GoodsUrl"
				+ " FROM GoodsInfo G ,TypeInfo T WHERE G.STID = T.STID AND G.STID=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GoodsVO goodsVO = null;
		List<GoodsVO> goodsList = new ArrayList<GoodsVO>();
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				goodsVO = new GoodsVO();
				goodsVO.setGoodsID(rs.getString("GoodsID"));
				goodsVO.setGoodsTypeID(stid);
				goodsVO.setGoodsName(rs.getString("GoodsName"));
				goodsVO.setGoodsDesigner(rs.getString("GoodsDesigner"));
				goodsVO.setGoodsPrice(rs.getFloat("GoodsPrice"));
				goodsVO.setGoodsStock(rs.getInt("GoodsStock"));
				goodsVO.setGoodsDescribe(rs.getString("GoodsDescribe"));
				//goodsVO.setgSellTime(rs.getDate("gsell_time"));
				goodsVO.setGoodsSales(rs.getInt("GoodsSales"));
				goodsVO.setGoodsUrl(rs.getString("GoodsUrl"));
				goodsList.add(goodsVO);
			}
		} catch (SQLException e) {
			goodsList = null;
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection(conn, pstmt, rs);
		}
		return goodsList;
	}

	/**
	 * 查询某件商品，返回GoodsVO
	 */
	public GoodsVO inquireByGId(String gId) {
		String sql = "SELECT GoodsID,GoodsTypeID,GoodsName,GoodsDesigner,GoodsPrice,GoodsStock,"
				+ " GoodsDescribe,GoodsSales,GoodsUrl FROM GoodsInfo WHERE GoodsID =?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GoodsVO goods = null;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				goods = new GoodsVO();
				goods.setGoodsID(gId);
				goods.setGoodsTypeID(rs.getString("GoodsTypeID"));
				goods.setGoodsName(rs.getString("GoodsName"));
				goods.setGoodsDesigner(rs.getString("GoodsDesigner"));
				goods.setGoodsPrice(rs.getFloat("GoodsPrice"));
				goods.setGoodsStock(rs.getInt("GoodsStock"));
				goods.setGoodsDescribe(rs.getString("GoodsDescribe"));
				//goodsVO.setgSellTime(rs.getDate("gsell_time"));
				goods.setGoodsSales(rs.getInt("GoodsSales"));
				goods.setGoodsUrl(rs.getString("GoodsUrl"));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goods;
	}

	/**
	 * 增加某件商品，返回boolean
	 */
	public boolean addGoods(GoodsVO goods) {
		String sql = "insert into GoodsInfo select count(*)+1,?,?,?,?,?,?,0,? from GoodsInfo";
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, goods.getGoodsName());
			pstmt.setString(2, goods.getGoodsTypeID());
			pstmt.setString(3, goods.getGoodsDesigner());
			pstmt.setFloat(4, goods.getGoodsPrice());
			pstmt.setInt(5, goods.getGoodsStock());
			pstmt.setString(6, goods.getGoodsDescribe());
			pstmt.setString(7, goods.getGoodsUrl());
			pstmt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
			return flag;
		}
		return flag;
	}

	/**
	 * 删除某件商品 ，返回boolean
	 */
	public boolean deleteGoods(String gId) {
		String sql = "delete from GoodsInfo where GoodsID=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag = false;

		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gId);
			pstmt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 修改某件商品 ，返回boolean
	 */
	public boolean updateGoods(GoodsVO goods) {
		String sql = "UPDATE GoodsInfo SET GoodsTypeID=?,GoodsName=?,GoodsDesigner=?,GoodsPrice=?,GoodsStock=?,GoodsDescribe=?,"
				+ "GoodsUrl=? WHERE GoodsID=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, goods.getGoodsTypeID());
			pstmt.setString(2, goods.getGoodsName());
			pstmt.setString(3, goods.getGoodsDesigner());
			pstmt.setFloat(4, goods.getGoodsPrice());
			pstmt.setInt(5, goods.getGoodsStock());
			pstmt.setString(6, goods.getGoodsDescribe());
			pstmt.setString(7, goods.getGoodsUrl());
			pstmt.setString(8, goods.getGoodsID());
			pstmt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
			return flag;
		}
		return flag;
	}

	/**
	 * 查询商品的记录总数
	 */
	public int getCount() {
		String sql = "select count(1) as counter from GoodsInfo";

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
	 * 分页查询所有商品
	 * 
	 * @param startRow
	 *            起始行
	 * @param endRow
	 *            终止行
	 * @return List<GoodsVO>
	 * 				分页查询得到的商品集合
	 */
	public List<GoodsVO> inquirePaging(int startRow, int endRow) {
		// 查询所有商品的语句
		String sql = "SELECT GoodsID,GoodsTypeID,GoodsName,GoodsDesigner,GoodsPrice,GoodsStock,"
				+ " GoodsDescribe,GoodsSales,GoodsUrl FROM GoodsInfo";
		// 分页查询的语句
		sql = DBConnect.PAGE_SELECT_SQL.replaceAll("#SELECTSQL", sql);
		String s="GoodsID";
		sql = sql.replaceAll("#Column", s);
		sql = sql.replaceAll("#pageSize", String.valueOf(endRow));
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GoodsVO goodsVO = null;
		List<GoodsVO> list = new ArrayList<GoodsVO>();

		try {
			conn = DBConnect.getConnection();
			//rs=((Statement) conn).executeQuery(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			//pstmt.setInt(2, startRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				goodsVO = new GoodsVO();
				goodsVO.setGoodsID(rs.getString("GoodsID"));
				goodsVO.setGoodsTypeID(rs.getString("GoodsTypeID"));
				goodsVO.setGoodsName(rs.getString("GoodsName"));
				goodsVO.setGoodsDesigner(rs.getString("GoodsDesigner"));
				goodsVO.setGoodsPrice(rs.getFloat("GoodsPrice"));
				goodsVO.setGoodsStock(rs.getInt("GoodsStock"));
				goodsVO.setGoodsDescribe(rs.getString("GoodsDescribe"));
				//goodsVO.setgSellTime(rs.getDate("gsell_time"));
				goodsVO.setGoodsSales(rs.getInt("GoodsSales"));
				goodsVO.setGoodsUrl(rs.getString("GoodsUrl"));
				list.add(goodsVO);

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
	 * 分类查询商品的记录总数
	 */
	public int getCountByType(String stId) {
		
		String sql = "select count(1) as counter from GoodsInfo where GoodsTypeID=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("counter");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection(conn, pstmt, rs);
		}
		return count;
	}

	/**
	 * 分页查询某类商品，返回List<GoodsVO>
	 */
	public List<GoodsVO> inquirePagingByType(int startRow, int endRow, String stId) {
		// 查询所有商品的语句
		String sql = "SELECT GoodsID,GoodsTypeID,GoodsName,GoodsDesigner,GoodsPrice,GoodsStock,"
				+ " GoodsDescribe,GoodsSales,GoodsUrl FROM GoodsInfo where GoodsTypeID=?";
		// 分页查询的语句
		sql = DBConnect.PAGE_SELECT_SQL.replaceAll("#SELECTSQL", sql);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GoodsVO goodsVO = null;
		List<GoodsVO> list = new ArrayList<GoodsVO>();

		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stId);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, startRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				goodsVO = new GoodsVO();
				goodsVO.setGoodsID(rs.getString("GoodsID"));
				goodsVO.setGoodsTypeID(rs.getString("GoodsTypeID"));
				goodsVO.setGoodsName(rs.getString("GoodsName"));
				goodsVO.setGoodsDesigner(rs.getString("GoodsDesigner"));
				goodsVO.setGoodsPrice(rs.getFloat("GoodsPrice"));
				goodsVO.setGoodsStock(rs.getInt("GoodsStock"));
				goodsVO.setGoodsDescribe(rs.getString("GoodsDescribe"));
				//goodsVO.setgSellTime(rs.getDate("gsell_time"));
				goodsVO.setGoodsSales(rs.getInt("GoodsSales"));
				goodsVO.setGoodsUrl(rs.getString("GoodsUrl"));
				list.add(goodsVO);
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
	 * 模糊查询 商品名称 与 品牌 两个关键字段获得的记录数
	 */
	public int getFuzzySearchCount(String keywords){
		String sql = "select count(1) as counter from GoodsInfo where GoodsName like ? or GoodsDesigner like ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keywords+"%");
			pstmt.setString(2, "%"+keywords+"%");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("counter");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection(conn, pstmt, rs);
		}
		return count;
	}

	/**
	 * 模糊查询 商品名称 与 品牌 两个关键字段，并分页显示
	 */
	public List<GoodsVO> fuzzySearchByNameAndBrand(int startRow, int endRow, String keywords){
		
		// 按关键字查询商品的语句
		String sql = "SELECT GoodsID,GoodsTypeID,GoodsName,GoodsDesigner,GoodsPrice,GoodsStock,"
				+ "GoodsDescribe,GoodsSales,GoodsUrl FROM GoodsInfo where GoodsName like ? or GoodsDesigner like ?";
		// 分页查询的语句
		sql = DBConnect.PAGE_SELECT_SQL.replaceAll("#SELECTSQL", sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GoodsVO goodsVO = null;
		List<GoodsVO> goodsList = null;
		try {
			goodsList = new ArrayList<GoodsVO>();
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keywords+"%");
			pstmt.setString(2, "%"+keywords+"%");
			pstmt.setInt(3, endRow);
			pstmt.setInt(4, startRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				goodsVO = new GoodsVO();
				goodsVO.setGoodsID(rs.getString("GoodsID"));
				goodsVO.setGoodsTypeID(rs.getString("GoodsTypeID"));
				goodsVO.setGoodsName(rs.getString("GoodsName"));
				goodsVO.setGoodsDesigner(rs.getString("GoodsDesigner"));
				goodsVO.setGoodsPrice(rs.getFloat("GoodsPrice"));
				goodsVO.setGoodsStock(rs.getInt("GoodsStock"));
				goodsVO.setGoodsDescribe(rs.getString("GoodsDescribe"));
				//goodsVO.setgSellTime(rs.getDate("gsell_time"));
				goodsVO.setGoodsSales(rs.getInt("GoodsSales"));
				goodsVO.setGoodsUrl(rs.getString("GoodsUrl"));
				goodsList.add(goodsVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			goodsList = null;
		} finally {
			DBConnect.closeConnection(conn, pstmt, rs);
		}
		return goodsList;
	}
	
	/**
	 * 按价格查询商品获得的记录数
	 */
	public int getCountByPrice(int priceRange){
		String sql = null;
		if (priceRange == 1) {
			sql = "select count(1) as counter from GoodsInfo where GoodsPrice between 1 and 100";
		}else if (priceRange == 2) {
			sql = "select count(1) as counter from GoodsInfo where GoodsPrice between 101 and 500";
		}else if (priceRange == 3) {
			sql = "select count(1) as counter from GoodsInfo where GoodsPrice between 501 and 1000";
		}else if (priceRange == 4) {
			sql = "select count(1) as counter from GoodsInfo where GoodsPrice between 1001 and 5000";
		}else if (priceRange == 5) {
			sql = "select count(1) as counter from GoodsInfo where GoodsPrice > 5000";
		}else {
			return 0;
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DBConnect.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt("counter");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection(conn, stmt, rs);
		}
		return count;
	}

	/**
	 * 按价格查询商品，并分页显示
	 */
	public List<GoodsVO> searchByPrice(int startRow, int endRow, int priceRange){
		String sql = null;
		if (priceRange == 1) {
			sql = "SELECT GoodsID,GoodsTypeID,GoodsName,GoodsDesigner,GoodsPrice,GoodsStock,"
				+ "GoodsDescribe,GoodsSales,GoodsUrl FROM GoodsInfo where GoodsPrice between 1 and 100";
		}else if (priceRange == 2) {
			sql = "SELECT GoodsID,GoodsTypeID,GoodsName,GoodsDesigner,GoodsPrice,GoodsStock,"
					+ "GoodsDescribe,GoodsSales,GoodsUrl FROM GoodsInfo where GoodsPrice between 101 and 500";
		}else if (priceRange == 3) {
			sql = "SELECT GoodsID,GoodsTypeID,GoodsName,GoodsDesigner,GoodsPrice,GoodsStock,"
					+ "GoodsDescribe,GoodsSales,GoodsUrl FROM GoodsInfo where GoodsPrice between 501 and 1000";
		}else if (priceRange == 4) {
			sql = "SELECT GoodsID,GoodsTypeID,GoodsName,GoodsDesigner,GoodsPrice,GoodsStock,"
					+ "GoodsDescribe,GoodsSales,GoodsUrl FROM GoodsInfo where GoodsPrice between 1001 and 5000";
		}else if (priceRange == 5) {
			sql = "SELECT GoodsID,GoodsTypeID,GoodsName,GoodsDesigner,GoodsPrice,GoodsStock,"
					+ "GoodsDescribe,GoodsSales,GoodsUrl FROM GoodsInfo where GoodsPrice > 5000";
		}else {
			return null;
		}
		// 分页查询的语句
		sql = DBConnect.PAGE_SELECT_SQL.replaceAll("#SELECTSQL", sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GoodsVO goodsVO = null;
		List<GoodsVO> goodsList = null;
		try {
			goodsList = new ArrayList<GoodsVO>();
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				goodsVO = new GoodsVO();
				goodsVO.setGoodsID(rs.getString("GoodsID"));
				goodsVO.setGoodsTypeID(rs.getString("GoodsTypeID"));
				goodsVO.setGoodsName(rs.getString("GoodsName"));
				goodsVO.setGoodsDesigner(rs.getString("GoodsDesigner"));
				goodsVO.setGoodsPrice(rs.getFloat("GoodsPrice"));
				goodsVO.setGoodsStock(rs.getInt("GoodsStock"));
				goodsVO.setGoodsDescribe(rs.getString("GoodsDescribe"));
				//goodsVO.setgSellTime(rs.getDate("gsell_time"));
				goodsVO.setGoodsSales(rs.getInt("GoodsSales"));
				goodsVO.setGoodsUrl(rs.getString("GoodsUrl"));
				goodsList.add(goodsVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			goodsList = null;
		} finally {
			DBConnect.closeConnection(conn, pstmt, rs);
		}
		return goodsList;
	}
	
}
