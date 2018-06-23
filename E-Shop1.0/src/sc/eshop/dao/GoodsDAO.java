package sc.eshop.dao;

import java.util.List;

import sc.eshop.vo.GoodsVO;

/**
 * 操作商品表的DAO
 * @author yxf
 *
 */
public interface GoodsDAO extends DAO {

	/**
	 * 查询所有商品，返回List<GoodsVO>
	 */
	public List<GoodsVO> inquireAllGoods();
	
	/**
	 * 查询商品的记录总数
	 */
	public int getCount();
	
	/**
	 * 分页查询商品，返回List<GoodsVO>
	 */
	public List<GoodsVO> inquirePaging(int startRow, int endRow);
	
	/**
	 * 分类查询商品的记录总数
	 */
	public int getCountByType(String stId);
	
	/**
	 * 分页查询某类商品，返回List<GoodsVO>
	 */
	public List<GoodsVO> inquirePagingByType(int startRow, int endRow, String stId);
	
	/**
	 * 查询某类商品，返回List<GoodsVO>
	 */
	public List<GoodsVO> inquireByType(String gType);
	
	/**
	 * 查询某件商品，返回GoodsVO
	 */
	public GoodsVO inquireByGId(String gId);
	
	/**
	 * 增加某件商品，返回boolean
	 */
	public boolean addGoods(GoodsVO goods);
	
	/**
	 * 按商品编号删除某件商品，返回boolean
	 */
	public boolean deleteGoods(String gId);
	
	/**
	 * 修改某件商品，返回boolean
	 */
	public boolean updateGoods(GoodsVO goods);
	
}
