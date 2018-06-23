package sc.eshop.vo;
/**
 * 管理员表的实体类，mId为主键
 * @author yxf
 *
 */
public class ManagerVO implements VO {

	// 管理员编号
	private String ManagerID;
	
	// 管理员账号名
	private String ManagerName;
	
	// 管理员密码
	private String ManagerPassword;
	private int ManagerAuthority;

	public String getManagerID() {
		return ManagerID;
	}

	public void setManagerID(String managerID) {
		ManagerID = managerID;
	}

	public String getManagerName() {
		return ManagerName;
	}

	public void setManagerName(String managerName) {
		ManagerName = managerName;
	}

	public String getManagerPassword() {
		return ManagerPassword;
	}

	public void setManagerPassword(String managerPassword) {
		ManagerPassword = managerPassword;
	}

	public int getManagerAuthority() {
		return ManagerAuthority;
	}

	public void setManagerAuthority(int managerAuthority) {
		ManagerAuthority = managerAuthority;
	}

	
}
