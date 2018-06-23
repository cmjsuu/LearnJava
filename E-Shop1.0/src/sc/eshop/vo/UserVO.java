package sc.eshop.vo;

import java.sql.Date;

/**
 * 用户表的实体类，userId为主键
 * @author yxf
 *
 */
public class UserVO implements VO{

	private String UserID;
	
	private String GameID_First;

	private String GameID_Second;

	private String GameID_Third;
	
	private String Accounts;
	
	private String UserPassword;
	
	private String UserSex;

	private Date UserBirthday;

	private String UserAddress;

	private String UserTelephone;

	private String UserEmail;

	private String RegisterDate;

	private int UserStatus;
	
	private String RegisterIP;

	private String LastLoginIP;

	private String LastLoginDate;
	
	private int QianDaoCount;
	
	private Date QianDaoDate;
	
	private String Question1;
	
	private String Response1;

	private String Question2;

	private String Response2;
	
	private String Question3;
	
	private String Response3;

	private String Compellation;

	private String PassPort;

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getGameID_First() {
		return GameID_First;
	}

	public void setGameID_First(String gameID_First) {
		GameID_First = gameID_First;
	}

	public String getGameID_Second() {
		return GameID_Second;
	}

	public void setGameID_Second(String gameID_Second) {
		GameID_Second = gameID_Second;
	}

	public String getGameID_Third() {
		return GameID_Third;
	}

	public void setGameID_Third(String gameID_Third) {
		GameID_Third = gameID_Third;
	}

	public String getAccounts() {
		return Accounts;
	}

	public void setAccounts(String accounts) {
		Accounts = accounts;
	}


	
	public String getRegisterDate() {
		return RegisterDate;
	}

	public void setRegisterDate(String registerDate) {
		RegisterDate = registerDate;
	}

	public String getRegisterIP() {
		return RegisterIP;
	}

	public void setRegisterIP(String registerIP) {
		RegisterIP = registerIP;
	}

	public String getLastLoginIP() {
		return LastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		LastLoginIP = lastLoginIP;
	}

	public String getLastLoginDate() {
		return LastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		LastLoginDate = lastLoginDate;
	}

	public int getQianDaoCount() {
		return QianDaoCount;
	}

	public void setQianDaoCount(int qianDaoCount) {
		QianDaoCount = qianDaoCount;
	}

	public Date getQianDaoDate() {
		return QianDaoDate;
	}

	public void setQianDaoDate(Date qianDaoDate) {
		QianDaoDate = qianDaoDate;
	}

	public String getQuestion1() {
		return Question1;
	}

	public void setQuestion1(String question1) {
		Question1 = question1;
	}

	public String getResponse1() {
		return Response1;
	}

	public void setResponse1(String response1) {
		Response1 = response1;
	}

	public String getQuestion2() {
		return Question2;
	}

	public void setQuestion2(String question2) {
		Question2 = question2;
	}

	public String getResponse2() {
		return Response2;
	}

	public void setResponse2(String response2) {
		Response2 = response2;
	}

	public String getQuestion3() {
		return Question3;
	}

	public void setQuestion3(String question3) {
		Question3 = question3;
	}

	public String getResponse3() {
		return Response3;
	}

	public void setResponse3(String response3) {
		Response3 = response3;
	}

	public String getCompellation() {
		return Compellation;
	}

	public void setCompellation(String compellation) {
		Compellation = compellation;
	}

	public String getPassPort() {
		return PassPort;
	}

	public void setPassPort(String passPort) {
		PassPort = passPort;
	}

	public String getUserPassword() {
		return UserPassword;
	}

	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}

	public String getUserSex() {
		return UserSex;
	}

	public void setUserSex(String userSex) {
		UserSex = userSex;
	}

	public Date getUserBirthday() {
		return UserBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		UserBirthday = userBirthday;
	}

	public String getUserAddress() {
		return UserAddress;
	}

	public void setUserAddress(String userAddress) {
		UserAddress = userAddress;
	}

	public String getUserTelephone() {
		return UserTelephone;
	}

	public void setUserTelephone(String userTelephone) {
		UserTelephone = userTelephone;
	}

	public String getUserEmail() {
		return UserEmail;
	}

	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}

	public int getUserStatus() {
		return UserStatus;
	}

	public void setUserStatus(int userStatus) {
		UserStatus = userStatus;
	}
	
}
