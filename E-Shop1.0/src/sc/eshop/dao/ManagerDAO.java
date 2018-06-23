package sc.eshop.dao;

import java.util.List;

import sc.eshop.vo.ManagerVO;

/**
 * 管理员表的DAO
 * @author yxf
 *
 */
public interface ManagerDAO extends DAO {

	/**
	 * 查询所有管理员，返回List<ManagerVO>
	 */
	public List<ManagerVO> inquireAllManager();
	
	/**
	 * 查询管理员的记录总数
	 */
	public int getCount();
	
	/**
	 * 分页查询管理员，返回List<ManagerVO>
	 */
	public List<ManagerVO> inquirePaging(int startRow, int endRow);
	
	/**
	 * 按账号和密码查询管理员，返回一个ManagerVO
	 */
	public ManagerVO inquireOneManager(String mName, String mPassword);
	
	/**
	 * 增加一名管理员，返回boolean
	 */
	public boolean addManager(ManagerVO manager);
	
	/**
	 * 按照编号删除一名管理员，返回boolean
	 */
	public boolean deleteManager(String mId);
	
	/**
	 * 修改管理员密码，返回boolean
	 */
	public boolean updateManager(ManagerVO manager);
}
