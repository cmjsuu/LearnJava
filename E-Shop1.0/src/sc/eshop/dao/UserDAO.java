package sc.eshop.dao;

import java.util.List;

import sc.eshop.vo.GamepeopleVO;
import sc.eshop.vo.ToolVO;
import sc.eshop.vo.UserVO;

/**
 * 操作用户表的DAO
 * @author yxf
 *
 */
public interface UserDAO extends DAO {

	/**
	 * 查询所有用户信息，返回List<UserVO>
	 */
	public List<UserVO> inquireAllUser();
	
	/**
	 * 查询用户的记录总数
	 */
	public int getCount();
	
	/**
	 * 分页查询用户信息，返回List<UserVO>
	 */
	public List<UserVO> inquirePaging(int startRow, int endRow);
	
	/**
	 * 按用户名和密码查询某位用户信息，返回userVO
	 */
	public UserVO inquireOneUser(String userName, String userPwd);
	
	/**
	 * 按用户编号查询某用户的详细信息，返回userVO
	 */
	public UserVO inquireUserById(String userId);
	
	/**
	 * 增加一名用户，返回boolean值
	 */
	public boolean addUser(UserVO user);
	
	/**
	 * 按用户编号删除一名用户，返回boolean值
	 */
	public boolean deleteUser(String userId);
	
	/**
	 * 修改用户信息，返回boolean值
	 */
	public boolean updateUser(UserVO user);
	
	public List<GamepeopleVO> inquireAllGamepeople(String userid);
	
	public List<ToolVO> inquireAllTools(String packId);
}
