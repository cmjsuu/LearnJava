package recombook.dao.impldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import recombook.common.db.DBConnect;
import recombook.dao.CategoryDAO;
import recombook.vo.CategoryVO;

public class ImplCategoryDAO implements CategoryDAO{
	

	public List<CategoryVO> inquireParentCategoryList()
	{
		String sql = "select id,category_key,category_value,category_brief,parent_id" +
	    " from book_category where parent_id = 0";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		CategoryVO categoryVO = null;
		List<CategoryVO> list = new ArrayList<CategoryVO>();
		try {
			conn = DBConnect.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				categoryVO = new CategoryVO();
				categoryVO.setId(rs.getInt("id"));
				categoryVO.setCategory_key(rs.getString("category_key"));
				categoryVO.setCategory_value(rs.getString("category_value"));
				categoryVO.setCategory_brief(rs.getString("category_brief"));
				categoryVO.setParent_id(rs.getInt("parent_id"));
				list.add(categoryVO);
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

	public List<CategoryVO> inquireChildrenCategoryList()
	{
		String sql = "select id,category_key,category_value,category_brief,parent_id" +
	    " from book_category where parent_id != 0";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		CategoryVO categoryVO = null;
		List<CategoryVO> list = new ArrayList<CategoryVO>();
		try {
			conn = DBConnect.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				categoryVO = new CategoryVO();
				categoryVO.setId(rs.getInt("id"));
				categoryVO.setCategory_key(rs.getString("category_key"));
				categoryVO.setCategory_value(rs.getString("category_value"));
				categoryVO.setCategory_brief(rs.getString("category_brief"));
				categoryVO.setParent_id(rs.getInt("parent_id"));
				list.add(categoryVO);
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

}
