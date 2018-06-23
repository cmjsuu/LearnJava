package recombook.vo;

import java.sql.Date;

public class UserVO implements VO {


	private int id;
	
	//用户账号
	private String user_account;
	
	//用户密码
	private String user_passw;
	
	private String user_passw_salt;
	
	//用户姓名
	private String user_name;
	
	//用户账号状态
	private int user_status;
	
	private int authority_id;
	
	private int rec_succeedNum;
	
	private int recommanding_Num;
	
	private int rec_totalNum;
	
	private int rec_yearSucceedNum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public String getUser_passw() {
		return user_passw;
	}

	public void setUser_passw(String user_passw) {
		this.user_passw = user_passw;
	}

	public String getUser_passw_salt() {
		return user_passw_salt;
	}

	public void setUser_passw_salt(String user_passw_salt) {
		this.user_passw_salt = user_passw_salt;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getUser_status() {
		return user_status;
	}

	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}

	public int getAuthority_id() {
		return authority_id;
	}

	public void setAuthority_id(int authority_id) {
		this.authority_id = authority_id;
	}

	public int getRec_succeedNum() {
		return rec_succeedNum;
	}

	public void setRec_succeedNum(int rec_succeedNum) {
		this.rec_succeedNum = rec_succeedNum;
	}

	public int getRecommanding_Num() {
		return recommanding_Num;
	}

	public void setRecommanding_Num(int recommanding_Num) {
		this.recommanding_Num = recommanding_Num;
	}

	public int getRec_totalNum() {
		return rec_totalNum;
	}

	public void setRec_totalNum(int rec_totalNum) {
		this.rec_totalNum = rec_totalNum;
	}

	public int getRec_yearSucceedNum() {
		return rec_yearSucceedNum;
	}

	public void setRec_yearSucceedNum(int rec_yearSucceedNum) {
		this.rec_yearSucceedNum = rec_yearSucceedNum;
	}
		
}
