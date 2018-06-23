package sc.eshop.dao.impldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sc.eshop.common.db.DBConnect;
import sc.eshop.dao.TypeDAO;
import sc.eshop.vo.TypeVO;

/**
 * @title 商品类型DAO的实现类
 * @author 李建超 @ 2012-11-2 下午9:37:24
 */
public class ImplTypeDAO implements TypeDAO {
	
	/**
	 * 查询所有商品类型，返回List<TypeVO>
	 */
	public List<TypeVO> inquireAllType() {
		String sql = "select GoodsTypeID,GoodsTypeName from AccessoriesStoreType";

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		TypeVO typeVO = null;
		List<TypeVO> typeList = new ArrayList<TypeVO>();

		try {
			conn = DBConnect.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				typeVO = new TypeVO();
				typeVO.setGoodsTypeID(rs.getString("GoodsTypeID"));
				typeVO.setGoodsTypeName(rs.getString("GoodsTypeName"));
				typeList.add(typeVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			typeList = null;
			return typeList;
		}
		return typeList;
	}
	
	/**
	 * 查询所有主商品类型，返回List<String>
	 */
	public List<String> inquireAllMainType(){
		
		String sql = "select GoodsTypeName from AccessoriesStoreType";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String mtName = null;
		List<String> mtList = null;
		try {
			conn = DBConnect.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			mtList = new ArrayList<String>();
			while (rs.next()) {
				mtName = rs.getString("GoodsTypeName");
				mtList.add(mtName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mtList = null;
		} finally {
			DBConnect.closeConnection(conn, st, rs);
		}
		return mtList;
	}
	
	/**
	 * 根据主商品类型查询二级商品类型，返回List<TypeVO>
	 */
/*	public List<TypeVO> inquireByMainType(String mtName){
		String sql = "select stid,stname from AccessoriesStoreType where mtname=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TypeVO typeVO = null;
		List<TypeVO> typeList = new ArrayList<TypeVO>();

		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mtName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				typeVO = new TypeVO();
				typeVO.setStId(rs.getString("stid"));
				typeVO.setStName(rs.getString("stname"));
				typeVO.setMtName(rs.getString(mtName));
				typeList.add(typeVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			typeList = null;
			return typeList;
		}
		return typeList;
	}*/

	/**
	 * 增加一项商品类型，返回boolean
	 */
	public boolean addType(TypeVO type) {
		String sql = "insert into AccessoriesStoreType (GoodsTypeID,GoodsTypeName) SELECT COUNT(*)+1,? FROM AccessoriesStoreType";
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag = false;

		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type.getGoodsTypeName());
			pstmt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 按类型编号删除一项商品类型，返回boolean
	 */
	public boolean deleteType(String stId) {
		String sql = "delete from AccessoriesStoreType where GoodsTypeID=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stId);
			pstmt.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	/**
	 * 修改一项商品类型，返回boolean
	 */
	public boolean updateType(TypeVO type) {
		String sql = "update AccessoriesStoreType set GoodsTypeName=? where GoodsTypeID=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type.getGoodsTypeName());
			pstmt.setString(2, type.getGoodsTypeID());
			pstmt.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	/**
	 * 查询商品的记录总数
	 */
	public int getCount() {
		String sql = "select count(1) as counter from AccessoriesStoreType";

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
			return -1;
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
	public List<TypeVO> inquirePaging(int startRow, int endRow) {
		String sql = "select GoodsTypeID,GoodsTypeName from AccessoriesStoreType";
		sql = DBConnect.PAGE_SELECT_SQL.replaceAll("#SELECTSQL", sql);
		String s="GoodsTypeID";
		sql = sql.replaceAll("#Column", s);
		sql = sql.replaceAll("#pageSize", String.valueOf(endRow));
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TypeVO type = null;
		List<TypeVO> list = new ArrayList<TypeVO>();

		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, endRow);
			pstmt.setInt(1, startRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				type = new TypeVO();
				type.setGoodsTypeID(rs.getString("GoodsTypeID"));
				type.setGoodsTypeName(rs.getString("GoodsTypeName"));
				list.add(type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
			return list;
		} finally {
			DBConnect.closeConnection(conn, pstmt, rs);
		}
		return list;
	}

	/**
	 * 根据二级类型编号查询某项商品类型的信息，返回TypeVO
	 */
	public TypeVO inquireById(String stid) {
		String sql = "select GoodsTypeID,GoodsTypeName from AccessoriesStoreType where GoodsTypeID=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TypeVO typeVO = null;

		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				typeVO = new TypeVO();
				typeVO.setGoodsTypeID(rs.getString("GoodsTypeID"));
				typeVO.setGoodsTypeName(rs.getString("GoodsTypeName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			typeVO = null;
			return typeVO;
		}
		return typeVO;
	}

}
