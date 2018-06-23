package sc.eshop.dao.impldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sc.eshop.common.db.DBConnect;
import sc.eshop.dao.QuestionDAO;
import sc.eshop.vo.QuestionVO;

/**
 * 通过用户编号查询密保信息的实现
 * 
 * @author leox
 * 
 */
public class ImplQuestionDAO implements QuestionDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	QuestionVO questionVO = null;

	public QuestionVO getQuestionByUserId(String uName) {
		String sql = "select uname,question,answer from es_protect_pwd where uname=?";
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				questionVO = new QuestionVO();
				questionVO.setuName(uName);
				questionVO.setQuestion(rs.getString("question"));
				questionVO.setAnswer(rs.getString("answer"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			questionVO = null;
		} finally {
			DBConnect.closeConnection(conn, pstmt, rs);
		}
		return questionVO;
	}

	/**
	 * 插入一条记录
	 */
	public boolean addQusetionVO(QuestionVO question) {
		String sql = "insert into es_protect_pwd values(?,?,?)";
		boolean flag = false;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, question.getuName());
			pstmt.setString(2, question.getQuestion());
			pstmt.setString(3, question.getAnswer());
			pstmt.executeQuery();
			flag = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			flag = false;
		} finally {
			DBConnect.closeConnection(conn, pstmt, null);
		}
		return flag;
	}

}
