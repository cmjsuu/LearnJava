package sc.eshop.dao;

import java.util.List;

import sc.eshop.vo.GoodsCommentVO;

/**
 * 操作商品评论的DAO，评论无法修改
 * @author yxf
 *
 */
public interface GoodsCommentDAO {

	/**
	 * 查询所有商品评论，返回List<GoodsCommentVO>
	 */
	public List<GoodsCommentVO> inquireAllComment();
	
	/**
	 * 查询商品评论的记录总数
	 */
	public int getCount();
	
	/**
	 * 分页查询商品评论，返回List<GoodsCommentVO>
	 */
	public List<GoodsCommentVO> inquirePaging(int startRow, int endRow);
	
	/**
	 * 按商品编号查询商品评论的记录总数
	 */
	public int getCountByGoodsId(String gId);
	
	/**
	 * 按商品编号分页查询商品评论，返回List<GoodsCommentVO>
	 */
	public List<GoodsCommentVO> inquirePagingByGoodsId(int startRow, int endRow, String gId);
	
	/**
	 * 按用户编号查询商品评论的记录总数
	 */
	public int getCountByUserId(String userId);
	
	/**
	 * 按用户编号分页查询商品评论，返回List<GoodsCommentVO>
	 */
	public List<GoodsCommentVO> inquirePagingByUserId(int startRow, int endRow, String userId);
	
	/**
	 * 按照用户编号查询某用户的所有商品评论，返回List<GoodsCommentVO>
	 */
	public List<GoodsCommentVO> inquireByUserId(String userId);
	
	/**
	 * 按照商品编号查询某件商品的所有评论，返回List<GoodsCommentVO>
	 */
	public List<GoodsCommentVO> inquireByGoodsId(String gId);
	
	/**
	 * 按照用户编号和商品编号查询某条商品评论的详细内容，返回GoodsCommentVO
	 */
	public GoodsCommentVO inquireOneComment(String userId, String gId);
	
	/**
	 * 增加一条评论，返回boolean
	 */
	public boolean addComment(GoodsCommentVO comment);
	
	/**
	 * 根据用户编号和商品编号删除一条评论，返回boolean
	 */
	public boolean deleteComment(String userId, String gId);
	
	
}
