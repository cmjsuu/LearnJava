package sc.eshop.dao.impldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sc.eshop.common.db.DBConnect;
import sc.eshop.dao.ManagerDAO;
import sc.eshop.vo.ManagerVO;

/**
 * 管理员DAO的实现类
 * 
 * @author huangzhihan
 * 
 */
public class ImplMangerDAO implements ManagerDAO {

	/**
	 * 查询所有管理员，返回List<ManagerVO>
	 */
	public List<ManagerVO> inquireAllManager() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ManagerVO managerVO = null;
		List<ManagerVO> result = new ArrayList<ManagerVO>();

		String sql = "select ManagerID,ManagerName,ManagerPassword from Manager";

		try {
			conn = DBConnect.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				managerVO = new ManagerVO();
				managerVO.setManagerID(rs.getString("mid"));
				managerVO.setManagerName(rs.getString("mname"));
				managerVO.setManagerPassword(rs.getString("mpassword"));
				result.add(managerVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = null;
			return result;
		} finally {
			DBConnect.closeConnection(conn, st, rs);
		}
		return result;

	}

	/**
	 * 按账号和密码查询管理员，返回一个ManagerVO
	 */
	public ManagerVO inquireOneManager(String mName, String mPassword) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select ManagerID,ManagerName,ManagerPassword,ManagerAuthority from Manager where ManagerName=? and ManagerPassword=?";
		ManagerVO managerVO = null;

		try {
			conn = DBConnect.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, mName);
			st.setString(2, mPassword);
			rs = st.executeQuery();
			if (rs.next()) {
				managerVO = new ManagerVO();
				managerVO.setManagerID(rs.getString("ManagerID"));
				managerVO.setManagerName(rs.getString("ManagerName"));
				managerVO.setManagerPassword(rs.getString("ManagerPassword"));
				managerVO.setManagerAuthority(rs.getInt("ManagerAuthority"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection(conn, st, rs);
		}
		return managerVO;
	}

	/**
	 * 增加一名管理员，返回boolean
	 */
	public boolean addManager(ManagerVO manager) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement st = null;
		String sql = "INSERT INTO ES_MANAGER(ManagerID,ManagerName,ManagerPassword)VALUES(manager_seq.nextval,?,?)";
		try {
			conn = DBConnect.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, manager.getManagerName());
			st.setString(2, manager.getManagerPassword());
			int g = st.executeUpdate();
			if (g != -1) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			DBConnect.closeConnection(conn, st, null);
		}
		return flag;
	}

	/**
	 * 按照编号删除一名管理员，返回boolean
	 */
	public boolean deleteManager(String mId) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement st = null;
		String sql = "delete Manager where ManagerID=?";
		try {
			conn = DBConnect.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, mId);
			int g = st.executeUpdate();
			if (g != -1) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
			return flag;
		} finally {
			DBConnect.closeConnection(conn, st, null);
		}
		return flag;
	}

	/**
	 * 修改管理员密码，返回boolean
	 */
	public boolean updateManager(ManagerVO manager) {
		boolean falg = false;
		Connection conn = null;
		PreparedStatement st = null;
		String sql = "update Manager set ManagerPassword=? where ManagerID=?";
		try {
			conn = DBConnect.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, manager.getManagerPassword());
			st.setString(2, manager.getManagerID());
			int g = st.executeUpdate();
			if (g != -1) {
				falg = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection(conn, st, null);
		}
		return falg;
	}

	/**
	 * 查询管理员的记录总数
	 */
	public int getCount() {
		String sql = "select count(1) as counter from Manager";
		
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
	 * @return 返回一个集合List<ManagerVO>
	 */
	public List<ManagerVO> inquirePaging(int startRow, int endRow) {
		
		// 查询所有商品的语句
		String sql = "select mid,mname,mpassword from es_manager";
		// 分页查询的语句
		sql = DBConnect.PAGE_SELECT_SQL.replaceAll("#SELECTSQL", sql);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ManagerVO manager = null;
		List<ManagerVO> result = new ArrayList<ManagerVO>();

		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				manager = new ManagerVO();
				manager.setManagerID(rs.getString("mid"));
				manager.setManagerName(rs.getString("mname"));
				manager.setManagerPassword(rs.getString("mpassword"));
				result.add(manager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = null;
			return result;
		} finally {
			DBConnect.closeConnection(conn, pstmt, rs);
		}
		return result;
	}
	
}



