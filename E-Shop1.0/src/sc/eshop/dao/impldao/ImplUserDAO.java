package sc.eshop.dao.impldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sc.eshop.common.db.DBConnect;
import sc.eshop.dao.UserDAO;
import sc.eshop.vo.GamepeopleVO;
import sc.eshop.vo.ToolVO;
import sc.eshop.vo.UserVO;

/**
 * 實現用戶操作接口的實現類
 * @author leox
 */
public class ImplUserDAO implements UserDAO {

	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;

	/**
	 * 查询所有用户信息
	 */
	public List<UserVO> inquireAllUser() {

		List<UserVO> userList = new ArrayList<UserVO>();
		UserVO userVO = null;
		String sql = "SELECT UserID,Accounts,UserPassword ,UserSex ,UserBirthday, UserAddress ,UserTelephone ,UserEmail ,convert(datetime,RegisterDate,102) RegisterTime,UserStatus from UserInfo";
		try {
			conn = DBConnect.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				userVO = new UserVO();
				userVO.setUserID(rs.getString("UserID"));
				userVO.setAccounts(rs.getString("Accounts"));
				userVO.setUserPassword(rs.getString("UserPassword"));
				//userVO.setuNickname(rs.getString("unickname"));
				userVO.setUserSex(rs.getString("UserSex"));
				userVO.setUserBirthday(rs.getDate("UserBirthday"));
				userVO.setUserAddress(rs.getString("UserAddress"));
				userVO.setUserTelephone(rs.getString("UserTelephone"));
				userVO.setUserEmail(rs.getString("UserEmail"));
				userVO.setRegisterDate(rs.getString("RegisterTime"));
				userVO.setUserStatus(rs.getInt("UserStatus"));
				userList.add(userVO);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			userList = null;
			return userList;
		} finally {
			DBConnect.closeConnection(conn, stmt, rs);
		}
		return userList;
	}
	
	//通过用户ID查询所有角色
	public List<GamepeopleVO> inquireAllGamepeople(String userid) {

		List<GamepeopleVO> GamepeopleList = new ArrayList<GamepeopleVO>();
		
		String sql="select gameid,nickname,jobid,GameLevel,PackID,LoveLiness,Experience from GameInfo where gameid=?";
		
		String sql1 = "SELECT  GameID_First, GameID_Second,GameID_Third from UserInfo where UserID="+userid;
		
		String[] gid=new String[3];	
		
		Connection conn2=null;
		PreparedStatement stmt2=null;
		ResultSet rs2=null;
		try {
			
			conn = DBConnect.getConnection();
			stmt = conn.createStatement();	
			rs = stmt.executeQuery(sql1);

			while (rs.next()) {
				gid[0]=rs.getString("GameID_First");
				gid[1]=rs.getString("GameID_Second");
				gid[2]=rs.getString("GameID_Third");
				
				for(int i=0;i<3;i++){
					if(gid[i]!=null){
						conn2= DBConnect.getConnection();
						stmt2 = conn2.prepareStatement(sql);
						stmt2.setString(1, gid[i]);
						rs2=stmt2.executeQuery();
						while (rs2.next()) {
							GamepeopleVO gamepeopleVO = new GamepeopleVO();
							gamepeopleVO.setGameID(rs2.getString("GameID"));
							gamepeopleVO.setNickName(rs2.getString("NickName"));
							gamepeopleVO.setJobID(rs2.getString("JobID"));
							gamepeopleVO.setJob(getJobNameByID(gamepeopleVO.getJobID()));
							gamepeopleVO.setPackID(rs2.getString("PackID"));
							gamepeopleVO.setExperience(rs2.getString("Experience"));
							gamepeopleVO.setGameLevel(rs2.getInt("GameLevel"));
							gamepeopleVO.setLoveLiness(rs2.getInt("LoveLiness"));
							GamepeopleList.add(gamepeopleVO);
						}
						DBConnect.closeConnection(conn2, stmt2, rs2);
					
					}
				}

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		//	GamepeopleList = null;
			return GamepeopleList;
		} finally {
			DBConnect.closeConnection(conn, stmt, rs);

		}
		return GamepeopleList;
	}

	/**
	 * 按用户名和密码查询某位用户信息
	 */
	public UserVO inquireOneUser(String userName, String userPwd) {
		UserVO userVO = null;
		String sql = "SELECT UserID,Accounts,UserPassword ,UserSex ,UserBirthday, UserAddress ,UserTelephone ,UserEmail ,convert(datetime,RegisterDate,102) RegisterTime,UserStatus from UserInfo where Accounts=? and UserPassword=?";
		try {
			conn = DBConnect.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userName);
			pstm.setString(2, userPwd);
			rs = pstm.executeQuery();
			if (rs.next()) {
				userVO = new UserVO();
				userVO.setUserID(rs.getString("UserID"));
				userVO.setAccounts(rs.getString("Accounts"));
				userVO.setUserPassword(rs.getString("UserPassword"));
				//userVO.setuNickname(rs.getString("unickname"));
				userVO.setUserSex(rs.getString("UserSex"));
				userVO.setUserBirthday(rs.getDate("UserBirthday"));
				userVO.setUserAddress(rs.getString("UserAddress"));
				userVO.setUserTelephone(rs.getString("UserTelephone"));
				userVO.setUserEmail(rs.getString("UserEmail"));
				userVO.setRegisterDate(rs.getString("RegisterTime"));
				userVO.setUserStatus(rs.getInt("UserStatus"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			userVO = null;
			return userVO;
		} finally {
			DBConnect.closeConnection(conn, stmt, rs);
		}
		return userVO;
	}

	/**
	 * 增加一名用户
	 */
	public boolean addUser(UserVO user) {
		String sql = "insert into UserInfo select COUNT(*)+1,null,null,null,?,?,?,?,?,?,?,getdate(),1,null,null,getdate(),1,getdate(),null,null,null,null,null,null,null,null FROM UserInfo ";
		boolean flag = false;
		try {
			conn = DBConnect.getConnection();
			pstm = conn.prepareStatement(sql);
			//pstm.setString(1, user.getUserID());
			pstm.setString(1, user.getAccounts());
			pstm.setString(2, user.getUserPassword());
			//pstm.setString(3, user.getNickname());
			pstm.setString(3, user.getUserSex());
			pstm.setDate(4, user.getUserBirthday());
			pstm.setString(5, user.getUserAddress());
			pstm.setString(6, user.getUserTelephone());
			pstm.setString(7, user.getUserEmail());
			pstm.executeUpdate();
			flag = true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			flag = false;
		} finally {
			DBConnect.closeConnection(conn, pstm, null);
		}
		return flag;
	}

	/**
	 * 按用户编号删除一名用户（将用户状态为可登陆修改为不可登陆）
	 */
	public boolean deleteUser(String userId) {
		// 将用户状态为可登陆修改为不可登陆的语句
		String sql = "update UserInfo set UserStatus=0 where UserID=?";
		boolean flag = false;
		try {
			conn = DBConnect.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			pstm.executeUpdate();
			flag = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			flag = false;
		} finally {
			DBConnect.closeConnection(conn, pstm, null);
		}
		return flag;
	}

	/**
	 * 修改用户信息
	 */
	public boolean updateUser(UserVO user) {

		String sql = "update UserInfo set Accounts=?,UserBirthday=?,UserAddress=?,UserTelephone=?, UserEmail=?,UserStatus=?,UserPassword=? where UserID=?";
		boolean flag = false;
		try {
			conn = DBConnect.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getAccounts());
			pstm.setDate(2, user.getUserBirthday());
			pstm.setString(3, user.getUserAddress());
			pstm.setString(4, user.getUserTelephone());
			pstm.setString(5, user.getUserEmail());
			pstm.setInt(6, user.getUserStatus());
			pstm.setString(7, user.getUserPassword());
			pstm.setString(8, user.getUserID());
			pstm.executeUpdate();
			flag = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			flag = false;
		} finally {
			DBConnect.closeConnection(conn, pstm, null);
		}
		return flag;
	}
	
	

	/**
	 * 获得总用户数
	 */
	public int getCount() {
		String sql = "select count(1) as counter from UserInfo";
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
	 * 分页查询用户
	 * @param startRow  起始行
	 * @param endRow  终止行
	 * @return 返回一个集合List<UserVO>
	 */
	public List<UserVO> inquirePaging(int startRow, int endRow) {
		// 查询所有用户
		String sql = "SELECT UserID,Accounts,UserPassword ,UserSex ,UserBirthday,"
				+ " UserAddress ,UserTelephone ,UserEmail ,convert(datetime,RegisterDate,102) RegisterTime,"
				+ "UserStatus from UserInfo ";
		// 分页查询的语句
		sql = DBConnect.PAGE_SELECT_SQL.replaceAll("#SELECTSQL", sql);
		String s="UserID";
		sql = sql.replaceAll("#Column", s);
		sql = sql.replaceAll("#pageSize", String.valueOf(endRow));
		UserVO userVO = null;
		List<UserVO> list = new ArrayList<UserVO>();

		try {
			conn = DBConnect.getConnection();
			
			pstm = conn.prepareStatement(sql);
			//pstm.setInt(1, endRow);
			pstm.setInt(1, startRow);
			rs = pstm.executeQuery();
			while (rs.next()) {
				userVO = new UserVO();
				userVO.setUserID(rs.getString("UserID"));
				userVO.setAccounts(rs.getString("Accounts"));
				userVO.setUserPassword(rs.getString("UserPassword"));
				//userVO.setuNickname(rs.getString("unickname"));
				userVO.setUserSex(rs.getString("UserSex"));
				userVO.setUserBirthday(rs.getDate("UserBirthday"));
				userVO.setUserAddress(rs.getString("UserAddress"));
				userVO.setUserTelephone(rs.getString("UserTelephone"));
				userVO.setUserEmail(rs.getString("UserEmail"));
				userVO.setRegisterDate(rs.getString("RegisterTime"));
				userVO.setUserStatus(rs.getInt("UserStatus"));		
				list.add(userVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		} finally {
			DBConnect.closeConnection(conn, pstm, rs);
		}
		return list;
	}

	/**
	 * 按用户编号查询某一位用户的详细信息
	 */
	public UserVO inquireUserById(String userId) {
		UserVO userVO = new UserVO();
		String sql = "SELECT UserID,Accounts,UserPassword ,UserSex ,UserBirthday, UserAddress ,UserTelephone ,UserEmail ,convert(datetime,RegisterDate,102) RegisterTime,UserStatus from UserInfo where UserID=?";
		try {
			conn = DBConnect.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			rs = pstm.executeQuery();
			if (rs.next()) {
				userVO.setUserID(userId);
				userVO.setAccounts(rs.getString("Accounts"));
				userVO.setUserPassword(rs.getString("UserPassword"));
				//userVO.setuNickname(rs.getString("unickname"));
				userVO.setUserSex(rs.getString("UserSex"));
				userVO.setUserBirthday(rs.getDate("UserBirthday"));
				userVO.setUserAddress(rs.getString("UserAddress"));
				userVO.setUserTelephone(rs.getString("UserTelephone"));
				userVO.setUserEmail(rs.getString("UserEmail"));
				userVO.setRegisterDate(rs.getString("RegisterTime"));
				userVO.setUserStatus(rs.getInt("UserStatus"));	
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBConnect.closeConnection(conn, pstm, rs);
		}
		return userVO;
	}
	
	/**
	 * 按用户账号查询某一位用户的详细信息
	 */
	public UserVO inquireUserByuName(String uName) {
		UserVO userVO = new UserVO();
		String sql = "SELECT UserID,Accounts,UserPassword ,UserSex ,UserBirthday, UserAddress ,UserTelephone ,UserEmail ,convert(datetime,RegisterDate,102) RegisterTime,UserStatus from UserInfo where Accounts=?";
		try {
			conn = DBConnect.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, uName);
			rs = pstm.executeQuery();
			if (rs.next()) {
				userVO.setUserID(rs.getString("UserID"));
				userVO.setAccounts(rs.getString("Accounts"));
				userVO.setUserPassword(rs.getString("UserPassword"));
				//userVO.setuNickname(rs.getString("unickname"));
				userVO.setUserSex(rs.getString("UserSex"));
				userVO.setUserBirthday(rs.getDate("UserBirthday"));
				userVO.setUserAddress(rs.getString("UserAddress"));
				userVO.setUserTelephone(rs.getString("UserTelephone"));
				userVO.setUserEmail(rs.getString("UserEmail"));
				userVO.setRegisterDate(rs.getString("RegisterTime"));
				userVO.setUserStatus(rs.getInt("UserStatus"));	
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			userVO = null;
		} finally {
			DBConnect.closeConnection(conn, pstm, rs);
		}
		return userVO;
	}

    public String getJobNameByID(String JobID){
		String sql = "select JobName from JobInfo where JobID="+JobID;
    	conn= DBConnect.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println(rs);
			while(rs.next()){
				return rs.getString("JobName");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnect.closeConnection(conn, stmt, rs);
		}
		
		return null;
    }
    
    public String GetToolTypeByID(String ToolTypeID){

		String sql = "select ToolTypeName from GameStoreType where ToolTypeID="+ToolTypeID;
		conn= DBConnect.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				return rs.getString("ToolTypeName");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnect.closeConnection(conn, stmt, rs);
		}
		return null;
    	
    }
	public List<ToolVO> inquireAllTools(String packId) {
		List<ToolVO> ToolList = new ArrayList<ToolVO>();
		ToolVO toolVO = null;
		Connection conn2=null;
		PreparedStatement stmt2=null;
		ResultSet rs2=null;
		String sql = "select ToolID from PackInfo where PackID="+packId;
		String sql2 = "select ToolID,ToolName,ToolCash,ToolGold,JobID,RegulationsInfo,ToolTypeID,ToolUrl from ToolInfo where ToolID=?";

		
		List <String> toolIds=new ArrayList<String>();
		
		try {
			conn = DBConnect.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				
				toolIds.add(rs.getString("ToolID"));

			}
			
			for(int i=0;i<toolIds.size();i++){
				
				conn2= DBConnect.getConnection();
				stmt2 = conn2.prepareStatement(sql2);
				stmt2.setString(1, toolIds.get(i));
				rs2=stmt2.executeQuery();
				
				while (rs2.next()) {
					toolVO=new ToolVO();
					toolVO.setJobID(rs2.getString("JobID"));
					toolVO.setJob(getJobNameByID(toolVO.getJobID()));	
					toolVO.setRegulationsInfo(rs2.getString("RegulationsInfo"));   
					toolVO.setToolCash(rs2.getString("ToolCash"));
					toolVO.setToolGold(rs2.getString("ToolGold"));
					toolVO.setToolID(rs2.getString("ToolID"));
					toolVO.setToolName(rs2.getString("ToolName"));
					toolVO.setToolTypeID(rs2.getString("ToolTypeID"));
					toolVO.setTooltype(GetToolTypeByID(toolVO.getToolTypeID()));
					toolVO.setToolUrl(rs2.getString("ToolUrl"));
					ToolList.add(toolVO);
					
				}
	
			}
			
			
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			ToolList = null;
			return ToolList;
		} finally {
			DBConnect.closeConnection(conn, stmt, rs);
			DBConnect.closeConnection(conn2, stmt2, rs2);
		}
		return ToolList;
	
	}

}
