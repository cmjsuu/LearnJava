package sc.eshop.dao;

import sc.eshop.vo.QuestionVO;


/**
 * 密保DAO
 * @author leox
 *
 */
public interface QuestionDAO extends DAO{
	
	/**
	 * 通过用户账号查询密保问题 
	 * @param userId 用户编号
	 * @return PwdProtectedVO
	 */
	public QuestionVO getQuestionByUserId(String uName);
	
	/**
	 * 向密保表里面插入一条记录
	 * @param pwdProctectVO
	 * @return
	 */
	public boolean addQusetionVO(QuestionVO question);
	
	/**
	 * 修改密保问题
	 */
	
}
