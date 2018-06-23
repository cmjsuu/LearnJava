package sc.eshop.dao;

import java.util.List;

import sc.eshop.vo.TypeVO;

/**
 * 操作商品类型表的DAO
 * @author yxf
 *
 */
public interface TypeDAO extends DAO {

	/**
	 * 查询所有商品类型，返回List<TypeVO>
	 */
	public List<TypeVO> inquireAllType();
	
	/**
	 * 根据二级类型编号查询某项商品类型的信息，返回TypeVO
	 */
	public TypeVO inquireById(String stid);
	
	/**
	 * 查询商品种类的记录总数
	 */
	public int getCount();
	
	/**
	 * 分页查询商品类型信息，返回List<TypeVO>
	 */
	public List<TypeVO> inquirePaging(int startRow, int endRow);
	
	/**
	 * 增加一项商品类型，返回boolean
	 */
	public boolean addType(TypeVO type);
	
	/**
	 * 按类型编号删除一项商品类型，返回boolean
	 */
	public boolean deleteType(String stId);
	
	/**
	 * 修改一项商品类型，返回boolean
	 */
	public boolean updateType(TypeVO type);
	
	
}
