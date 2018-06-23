package sc.eshop.dao;

import java.util.List;

import sc.eshop.vo.MessageBoardVO;

/**
 * 操作留言板的DAO
 * @author yxf
 *
 */
public interface MessageBoardDAO extends DAO {

	/**
	 * 查询所有留言信息，返回List<MessageBoardVO>
	 */
	public List<MessageBoardVO> inquireAllMessage();
	
	/**
	 * 根据留言编号查询单条留言
	 */
	public MessageBoardVO inquireById(String mbId);
	
	/**
	 * 查询留言的记录总数
	 */
	public int getCount();
	
	/**
	 * 分页查询留言信息，返回List<MessageBoardVO>
	 */
	public List<MessageBoardVO> inquirePaging(int startRow, int endRow);
	
	/**
	 * 增加一项留言信息，返回boolean
	 */
	public boolean addMessage(MessageBoardVO message);
	
	/**
	 * 按留言编号删除一项留言信息，返回boolean
	 */
	public boolean deleteMessage(String mbId);
	
	/**
	 * 修改一项留言信息，返回boolean
	 */
	public boolean updateMessage(MessageBoardVO message);
	
}
