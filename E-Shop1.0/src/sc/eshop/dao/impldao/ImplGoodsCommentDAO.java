package sc.eshop.dao.impldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sc.eshop.common.db.DBConnect;
import sc.eshop.dao.GoodsCommentDAO;
import sc.eshop.vo.GoodsCommentVO;

/**
 * 商品评论DAO的实现类
 * 
 * @author yxf
 * 
 */
public class ImplGoodsCommentDAO implements GoodsCommentDAO {
	/**
	 * 查询所有商品评论，返回List<GoodsCommentVO>
	 */
	public List<GoodsCommentVO> inquireAllComment() {

		List<GoodsCommentVO> result = new ArrayList<GoodsCommentVO>();
		GoodsCommentVO goodsComment = null;

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select nickname,gid,ccomment,ctime from es_goods_comment";

		try {
			conn = DBConnect.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				goodsComment = new GoodsCommentVO();
				goodsComment.setNickname(rs.getString("nickname"));
				goodsComment.setgId(rs.getString("gid"));
				goodsComment.setcComment(rs.getString("ccomment"));
				goodsComment.setcTime(rs.getDate("ctime"));
				result.add(goodsComment);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
			return result;
		} finally {
			DBConnect.closeConnection(conn, st, rs);
		}
		return result;
	}

	/**
	 * 按照用户编号查询某用户的所有商品评论，返回List<GoodsCommentVO>
	 */
	public List<GoodsCommentVO> inquireByUserId(String userId) {

		List<GoodsCommentVO> result = new ArrayList<GoodsCommentVO>();
		GoodsCommentVO goodsComment = null;

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		String sql = "select nickname,gid,ccomment,ctime from es_goods_comment where userid="
				+ userId;
		try {
			conn = DBConnect.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				goodsComment = new GoodsCommentVO();
				goodsComment.setNickname(rs.getString("nickname"));
				goodsComment.setgId(rs.getString("gid"));
				goodsComment.setcComment(rs.getString("ccomment"));
				goodsComment.setcTime(rs.getDate("ctime"));
				result.add(goodsComment);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
			return result;
		} finally {
			DBConnect.closeConnection(conn, st, rs);
		}
		return result;
	}

	/**
	 * 按照商品编号查询某件商品的所有评论，返回List<GoodsCommentVO>
	 */
	public List<GoodsCommentVO> inquireByGoodsId(String gId) {

		List<GoodsCommentVO> result = new ArrayList<GoodsCommentVO>();
		GoodsCommentVO goodsComment = null;

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		String sql = "select nickname,gid,ccomment,ctime from es_goods_comment where gid="
				+ gId;
		try {
			conn = DBConnect.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				goodsComment = new GoodsCommentVO();
				goodsComment.setNickname(rs.getString("nickname"));
				goodsComment.setgId(rs.getString("gid"));
				goodsComment.setcComment(rs.getString("ccomment"));
				goodsComment.setcTime(rs.getDate("ctime"));
				result.add(goodsComment);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
			return result;
		} finally {
			DBConnect.closeConnection(conn, st, rs);
		}
		return result;
	}

	/**
	 * 按照用户编号和商品编号查询某条商品评论的详细内容，返回GoodsCommentVO
	 */
	public GoodsCommentVO inquireOneComment(String userId, String gId) {

		GoodsCommentVO goodsComment = null;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select Nickname,gid,ccomment,ctime from es_goods_comment where userid="
				+ userId + " and gid=" + gId;
		try {
			conn = DBConnect.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			if (rs.next()) {
				goodsComment = new GoodsCommentVO();
				goodsComment.setNickname(rs.getString("Nickname"));
				goodsComment.setgId(rs.getString("gid"));
				goodsComment.setcComment(rs.getString("ccomment"));
				goodsComment.setcTime(rs.getDate("ctime"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			goodsComment = null;
			return goodsComment;
		} finally {
			DBConnect.closeConnection(conn, st, rs);
		}
		return goodsComment;
	}

	/**
	 * 增加一条评论，返回boolean
	 */
	public boolean addComment(GoodsCommentVO comment) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "insert into es_goods_comment values(?,?,?,sysdate)";

		try {
			conn = DBConnect.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, comment.getNickname());
			st.setString(2, comment.getgId());
			st.setString(3, comment.getcComment());
			int g = st.executeUpdate();
			if (g != -1) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection(conn, st, rs);
		}
		return flag;
	}

	/**
	 * 根据用户编号和商品编号删除一条评论，返回boolean
	 */
	public boolean deleteComment(String Nickname, String gId) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete es_goods_comment where Nickname=? and gid=?";
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Nickname);
			pstmt.setString(2, gId);
			int g = pstmt.executeUpdate();
			if (g != -1) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			DBConnect.closeConnection(conn, pstmt, null);
		}
		return flag;
	}

	/**
	 * 查询所有商品评论的记录总数
	 */
	public int getCount() {
		String sql = "select count(1) as counter from es_goods_comment";

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
	 * 所有商品评论的分页查询
	 * 
	 * @param startRow
	 *            起始行
	 * @param endRow
	 *            终止行
	 * @return 返回一个集合List<ManagerVO>
	 */
	public List<GoodsCommentVO> inquirePaging(int startRow, int endRow) {
		// 查询所有商品评论的语句
		String sql = "select Nickname,gid,ccomment,ctime from es_goods_comment";
		// 分页查询的语句
		sql = DBConnect.PAGE_SELECT_SQL.replaceAll("#SELECTSQL", sql);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GoodsCommentVO goodsComment = null;
		List<GoodsCommentVO> result = new ArrayList<GoodsCommentVO>();

		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				goodsComment = new GoodsCommentVO();
				goodsComment.setNickname(rs.getString("Nickname"));
				goodsComment.setgId(rs.getString("gid"));
				goodsComment.setcComment(rs.getString("ccomment"));
				goodsComment.setcTime(rs.getDate("ctime"));
				result.add(goodsComment);
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

	/**
	 * 按商品编号查询商品评论的记录总数
	 */
	public int getCountByGoodsId(String gid) {
		String sql = "select count(1) as counter from es_goods_comment where gid=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gid);
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
	 * 按商品编号分页查询商品评论，返回List<GoodsCommentVO>
	 */
	public List<GoodsCommentVO> inquirePagingByGoodsId(int startRow, int endRow, String gId) {
		// 按商品编号查询所有商品评论的语句
		String sql = "select Nickname,gid,ccomment,ctime from es_goods_comment where gid=? order by ctime desc";
		// 分页查询的语句
		sql = DBConnect.PAGE_SELECT_SQL.replaceAll("#SELECTSQL", sql);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GoodsCommentVO goodsComment = null;
		List<GoodsCommentVO> result = new ArrayList<GoodsCommentVO>();

		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gId);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, startRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				goodsComment = new GoodsCommentVO();
				goodsComment.setNickname(rs.getString("Nickname"));
				goodsComment.setgId(rs.getString("gid"));
				goodsComment.setcComment(rs.getString("ccomment"));
				goodsComment.setcTime(rs.getDate("ctime"));
				result.add(goodsComment);
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

	/**
	 * 按用户编号查询商品评论的记录总数
	 */
	public int getCountByUserId(String userId) {
		String sql = "select count(1) as counter from es_goods_comment where userid=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
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
	 * 按用户编号分页查询商品评论，返回List<GoodsCommentVO>
	 */
	public List<GoodsCommentVO> inquirePagingByUserId(int startRow, int endRow, String userId) {
		// 按用户编号查询所有商品评论的语句
		String sql = "select Nickname,gid,ccomment,ctime from es_goods_comment where userid=?";
		// 分页查询的语句
		sql = DBConnect.PAGE_SELECT_SQL.replaceAll("#SELECTSQL", sql);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GoodsCommentVO goodsComment = null;
		List<GoodsCommentVO> result = new ArrayList<GoodsCommentVO>();

		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, startRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				goodsComment = new GoodsCommentVO();
				goodsComment.setNickname(rs.getString("Nickname"));
				goodsComment.setgId(rs.getString("gid"));
				goodsComment.setcComment(rs.getString("ccomment"));
				goodsComment.setcTime(rs.getDate("ctime"));
				result.add(goodsComment);
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
