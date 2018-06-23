package sc.eshop.dao.impldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sc.eshop.common.db.DBConnect;
import sc.eshop.dao.NewsDAO;
import sc.eshop.vo.NewsVO;
/**
 * 對新聞操作接口的實現類
 * @author leox
 */
public class ImplNewsDAO implements NewsDAO {
	
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
public String GetManagerByID(String ManagerID){
	String sql = "select ManagerName from Manager Where ManagerID="+ManagerID;
	ResultSet rs2=null;
	try{
		conn = DBConnect.getConnection();
		stmt = conn.createStatement();
		rs2 = stmt.executeQuery(sql);
		while(rs2.next()){
			return rs2.getString("ManagerName");
		}
	}catch(SQLException ex){
		ex.printStackTrace();
	}finally{
		DBConnect.closeConnection(conn, stmt, rs2);
	}
	return null;
}
	public ArrayList<NewsVO> inquireAllNews() {
		ArrayList<NewsVO> newsList = new ArrayList<NewsVO>();
		NewsVO newsVO = null;
		String sql = "select ManagerID ,NewsID ,NewsTitle ,NewsContent ,NewsPublishTime from NewsInfo by NewsPublishTime desc";
		try{
			conn = DBConnect.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				newsVO = new NewsVO();
				newsVO.setNewsID(rs.getString("NewsID"));
				newsVO.setManagerID(rs.getString("ManagerID"));
				newsVO.setManager(GetManagerByID(newsVO.getManagerID()));
				newsVO.setNewsContent(rs.getString("NewsContent"));
				newsVO.setNewsPublishTime(rs.getDate("NewsPublishTime"));
				newsVO.setNewsTitle(rs.getString("NewsTitle"));
				newsList.add(newsVO);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
			//newsList = null;
			return newsList;
		}finally{
			DBConnect.closeConnection(conn, stmt, rs);
		}
		return newsList;
	}

	public boolean addNews(NewsVO news) {
		String sql = "insert into NewsInfo (ManagerID,NewsID,NewsTitle,NewsContent,NewsPublishTime) SELECT ?,COUNT(*)+100,?,?,GETDATE() FROM NewsInfo";
		boolean flag = false;
		try{
			conn = DBConnect.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, news.getManagerID());
			pstm.setString(2, news.getNewsTitle());
			pstm.setString(3, news.getNewsContent());
			pstm.executeUpdate();
			flag = true;
		}catch(SQLException ex){
			ex.printStackTrace();
			flag = false;
		}finally{
			DBConnect.closeConnection(conn, pstm, null);
		}
		return flag;
	}

	public boolean deleteNews(String nId) {
		String sql = "delete from NewsInfo where NewsID=?";
		boolean flag = false;
		try{
			conn = DBConnect.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, nId);
			pstm.executeUpdate();
			flag = true;
		}catch(SQLException ex){
			ex.printStackTrace();
			flag = false;
		}finally{
			DBConnect.closeConnection(conn, pstm, null);
		}
		return flag;
	}

	public boolean updateNews(NewsVO news) {
		String sql = "update NewsInfo set NewsTitle =?,NewsContent=? where NewsID=?";
		boolean flag = false;
		try{
			conn = DBConnect.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, news.getNewsTitle());
			pstm.setString(2, news.getNewsContent());
			pstm.setString(3, news.getNewsID());
			pstm.executeUpdate();
			flag = true;
		}catch(SQLException ex){
			ex.printStackTrace();
			flag = false;
		}finally{
			DBConnect.closeConnection(conn, pstm, null);
		}
		return flag;
	}

	/**
	 * 查询新闻的记录总数
	 */
	public int getCount() {
		String sql = "select count(1) as counter from NewsInfo";
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
			return -1;
		} finally {
			DBConnect.closeConnection(conn, stmt, rs);
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
	 * @return 返回一个集合List<NewsVO>
	 */
	public List<NewsVO> inquirePaging(int startRow, int endRow) {
		
		String sql = "select  ManagerID ,NewsID ,NewsTitle ,NewsContent ,NewsPublishTime from NewsInfo ";
		sql = DBConnect.PAGE_SELECT_SQL.replaceAll("#SELECTSQL", sql);
		String s="NewsPublishTime";
		sql = sql.replaceAll("#Column", s);
		sql = sql.replaceAll("#pageSize", String.valueOf(endRow));
		NewsVO newsVO = null;
		List<NewsVO> list = new ArrayList<NewsVO>();

		try {
			conn = DBConnect.getConnection();
			pstm = conn.prepareStatement(sql);
			//pstm.setInt(1, endRow);
			pstm.setInt(1, startRow);
			rs = pstm.executeQuery();
			while (rs.next()) {
				newsVO = new NewsVO();
				newsVO.setNewsID(rs.getString("NewsID"));
				newsVO.setManagerID(rs.getString("ManagerID"));
				newsVO.setManager(GetManagerByID(newsVO.getManagerID()));
				newsVO.setNewsContent(rs.getString("NewsContent"));
				newsVO.setNewsPublishTime(rs.getDate("NewsPublishTime"));
				newsVO.setNewsTitle(rs.getString("NewsTitle"));
				list.add(newsVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//list = null;
			return list;
		} finally {
			DBConnect.closeConnection(conn, pstm, rs);
		}
		return list;
	}

	/**
	 * 查询单条新闻详细信息
	 */
	public NewsVO inquireById(String nId) {
		
		NewsVO newsVO = null;
		String sql = "select ManagerID ,NewsID ,NewsTitle ,NewsContent ,NewsPublishTime from NewsInfo where NewsID=?";
		
		try {
			conn = DBConnect.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, nId);
			rs = pstm.executeQuery();
			if (rs.next()) {
				newsVO = new NewsVO();
				newsVO.setNewsID(rs.getString("NewsID"));
				newsVO.setManagerID(rs.getString("ManagerID"));
				newsVO.setNewsContent(rs.getString("NewsContent"));
				newsVO.setNewsPublishTime(rs.getDate("NewsPublishTime"));
				newsVO.setNewsTitle(rs.getString("NewsTitle"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			newsVO = null;
		} finally {
			DBConnect.closeConnection(conn, pstm, rs);
		}
		return newsVO;
	}

}












