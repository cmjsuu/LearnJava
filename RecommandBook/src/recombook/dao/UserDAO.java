package recombook.dao;

import recombook.vo.UserVO;

public interface UserDAO extends DAO {

	
	/**
	 * ���û��˺Ų�ѯĳ�û�����ϸ��Ϣ������userVO
	 */
	public UserVO inquireUserByAccount(String userAccount);
}
