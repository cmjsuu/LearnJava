package recombook.dao.impldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import recombook.common.IC;
import recombook.common.db.DBConnect;
import recombook.dao.BookDAO;
import recombook.vo.BookVO;
import recombook.vo.CategoryVO;

public class ImplBookDAO implements BookDAO{

	public List<BookVO> inquireBookList(int startRow)
	{
		String sql = "SELECT *FROM bookdetail ORDER BY recommand_num_tercher DESC,recommand_times DESC LIMIT ?, ?";

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		BookVO bookVO = null;
		List<BookVO> list = new ArrayList<BookVO>();
		try {
			conn = DBConnect.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, startRow);
			st.setInt(2, IC.TYPE_PAGE_SIZE);
			rs = st.executeQuery();
			while (rs.next()) {
				bookVO = new BookVO();
				bookVO.setId(rs.getInt("bkid"));
				bookVO.setCategory_id(rs.getInt("category_id"));
				bookVO.setBook_sn(rs.getString("book_sn"));					
				bookVO.setBook_ISBN(rs.getString("book_ISBN"));
				bookVO.setBook_name(rs.getString("book_name"));
				bookVO.setBook_subheading(rs.getString("book_subheading"));					
				bookVO.setBook_partNo(rs.getString("book_partNo"));
				bookVO.setBook_version(rs.getString("book_version"));			
				bookVO.setPublish_place(rs.getString("publish_place"));					
				bookVO.setPublish_time(rs.getString("publish_time"));
				bookVO.setBook_author(rs.getString("book_author"));					
				bookVO.setBook_pagination(rs.getInt("book_pagination"));					
				bookVO.setBook_brief(rs.getString("book_brief"));
				bookVO.setUnit_price(rs.getDouble("unit_price"));												
				bookVO.setAdd_time(rs.getDate("add_time"));					
				bookVO.setRecommand_times(rs.getInt("recommand_times"));
				bookVO.setIs_new(rs.getInt("is_new"));					
				bookVO.setBook_status(rs.getInt("book_status"));					
				bookVO.setImg_url(rs.getString("img_url"));
				bookVO.setBookseller_id(rs.getInt("bookseller_id"));				
				bookVO.setRecommand_num_tercher(rs.getInt("recommand_num_tercher"));
				
				//∑÷¿‡ Ù–‘
				bookVO.setParent_id(rs.getInt("parent_id"));					
				bookVO.setCategory_key(rs.getString("category_key"));					
				bookVO.setCategory_value(rs.getString("category_value"));
				bookVO.setCategory_brief(rs.getString("category_brief"));
				list.add(bookVO);
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
