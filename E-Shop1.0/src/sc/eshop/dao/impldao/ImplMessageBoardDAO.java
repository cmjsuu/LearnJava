package sc.eshop.dao.impldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sc.eshop.common.db.DBConnect;
import sc.eshop.dao.MessageBoardDAO;
import sc.eshop.vo.MessageBoardVO;
/**
 * 對操作留言板接口的實現類
 * @author leox
 *
 */
public class ImplMessageBoardDAO implements MessageBoardDAO {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	/**
	 * 查询所有留言信息
	 */
	public ArrayList<MessageBoardVO> inquireAllMessage() {
		ArrayList<MessageBoardVO> messageList = new ArrayList<MessageBoardVO>();
		MessageBoardVO messageVO = null;
		String sql = "select UserID ,MessageID ,MessageTitle ,MessageContent, MessagePubilshTime,MessageState,MessageReply,Accounts from Message order by MessagePubilshTime desc";
		try{
			conn = DBConnect.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				messageVO = new MessageBoardVO();
				messageVO.setMessageContent(rs.getString("mbcontent"));
				messageVO.setMessageID(rs.getString("mbid"));
				messageVO.setMessagePubilshTime(rs.getDate("mbtime"));
				messageVO.setMessageTitle(rs.getString("mbtitle"));
				messageVO.setUserID(rs.getString("userid"));
				messageVO.setMessageState(rs.getInt("mState"));
				messageVO.setMessageReply(rs.getString("reply"));
				messageVO.setAccounts(rs.getString("accounts"));
				
				messageList.add(messageVO);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
			messageList = null;
			return messageList;
		}finally{
			DBConnect.closeConnection(conn, stmt, rs);
		}
		return messageList;
	}
	/**
	 * 增加一项留言信息
	 */
	public boolean addMessage(MessageBoardVO message) {
		String sql = "insert into Message select ?,count(*),?,?,getdate(),0,'',? from Message";
		boolean flag = false;
		try{
			//inquireUserById(String userId);
			conn = DBConnect.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, message.getUserID());
			pstm.setString(2, message.getMessageTitle());
			pstm.setString(3, message.getMessageContent());
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
	 * 按留言编号删除一项留言信息
	 */
	public boolean deleteMessage(String mbId) {
		String sql = "delete from Message where MessageID=?";
		boolean flag = false;
		try{
			conn = DBConnect.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, mbId);
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
	 *修改一项留言信息 
	 */
	public boolean updateMessage(MessageBoardVO message) {
		String sql = "update Message  set MessageState=?,MessageReply=? where MessageID=?";
		boolean flag = false;
		try{
			conn = DBConnect.getConnection();
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, message.getMessageState());
			pstm.setString(2, message.getMessageReply());
			//pstm.setDate(3,message.getMbTime());
			pstm.setString(3, message.getMessageID());
			pstm.executeUpdate();
			flag = true;
		}catch(SQLException ex){
			ex.printStackTrace();
			flag = false;
		}finally{
			DBConnect.closeConnection(conn, stmt, null);
		}
		return flag;
	}

	/**
	 * 查询商品的记录总数
	 */
	public int getCount() {
		String sql = "select count(1) as counter from Message";
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
	 * @return 返回一个集合List<MessageBoardVO>
	 */
	public List<MessageBoardVO> inquirePaging(int startRow, int endRow) {
		String sql = "select UserID ,MessageID ,MessageTitle ,MessageContent, MessagePubilshTime,MessageState from Message ";
		sql = DBConnect.PAGE_SELECT_SQL.replaceAll("#SELECTSQL", sql);
		String s="MessagePubilshTime";
		sql = sql.replaceAll("#Column", s);
		sql = sql.replaceAll("#pageSize", String.valueOf(endRow));
		MessageBoardVO messageVO = null;
		List<MessageBoardVO> list = new ArrayList<MessageBoardVO>();

		try {
			conn = DBConnect.getConnection();
			pstm = conn.prepareStatement(sql);
			//pstm.setInt(1, endRow);
			pstm.setInt(1, startRow);
			rs = pstm.executeQuery();
			while (rs.next()) {
				messageVO = new MessageBoardVO();
				messageVO.setMessageContent(rs.getString("MessageContent"));
				messageVO.setMessageID(rs.getString("MessageID"));
				messageVO.setMessagePubilshTime(rs.getDate("MessagePubilshTime"));
				messageVO.setMessageTitle(rs.getString("MessageTitle"));
				messageVO.setUserID(rs.getString("UserID"));
				messageVO.setMessageState(rs.getInt("MessageState"));
				list.add(messageVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
			return list;
		} finally {
			DBConnect.closeConnection(conn, pstm, rs);
		}
		return list;
	}
	
	/**
	 * 根据留言编号查询单条留言
	 */
	public MessageBoardVO inquireById(String mbId) {
		
		String sql = "select UserID ,MessageID ,MessageTitle ,MessageContent, MessagePubilshTime,MessageState,MessageReply,Accounts from Message where MessageID=?";
		MessageBoardVO message = null;
		try {
			conn = DBConnect.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, mbId);
			rs = pstm.executeQuery();
			if (rs.next()) {
				message = new MessageBoardVO();
				message.setUserID(rs.getString("UserID"));
				message.setMessageID(rs.getString("MessageID"));
				message.setMessageTitle(rs.getString("MessageTitle"));
				message.setMessageContent(rs.getString("MessageContent"));
				message.setMessagePubilshTime(rs.getDate("MessagePubilshTime"));
				message.setMessageState(rs.getInt("MessageState"));
				message.setMessageReply(rs.getString("MessageReply"));
				message.setAccounts(rs.getString("Accounts"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			message = null;
		} finally {
			DBConnect.closeConnection(conn, pstm, rs);
		}
		return message;
	}
	
}
