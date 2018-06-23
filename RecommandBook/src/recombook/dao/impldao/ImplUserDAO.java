package recombook.dao.impldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import recombook.common.db.DBConnect;
import recombook.dao.UserDAO;
import recombook.vo.UserVO;

public class ImplUserDAO implements UserDAO{

	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	
	public UserVO inquireUserByAccount(String userAccount)
	{
		UserVO userVO = new UserVO();
		String sql = "SELECT id,user_account,user_passw,user_passw_salt,user_name,user_status,authority_id,rec_succeedNum,recommanding_Num,rec_totalNum,rec_yearSucceedNum from user where user_account=?";
		try {
			conn = DBConnect.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userAccount);
			rs = pstm.executeQuery();
			if (rs.next()) {
				userVO.setId(rs.getInt("id"));
				userVO.setUser_account(rs.getString("user_account"));
				userVO.setUser_passw(rs.getString("user_passw"));
				userVO.setUser_passw_salt(rs.getString("user_passw_salt"));
				userVO.setUser_name(rs.getString("user_name"));
				userVO.setUser_status(rs.getInt("user_status"));
				userVO.setAuthority_id(rs.getInt("authority_id"));
				userVO.setRec_succeedNum(rs.getInt("rec_succeedNum"));
				userVO.setRecommanding_Num(rs.getInt("recommanding_Num"));
				userVO.setRec_totalNum(rs.getInt("rec_totalNum"));
				userVO.setRec_yearSucceedNum(rs.getInt("rec_yearSucceedNum"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBConnect.closeConnection(conn, pstm, rs);
		}
		return userVO;
	}
}
