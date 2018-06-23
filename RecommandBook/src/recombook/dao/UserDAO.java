package recombook.dao;

import recombook.vo.UserVO;

public interface UserDAO extends DAO {

	
	/**
	 * 按用户账号查询某用户的详细信息，返回userVO
	 */
	public UserVO inquireUserByAccount(String userAccount);
}
