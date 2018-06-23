package sc.eshop.vo;
/**
 * 实物商品类型表的实体类，GoodsTypeID为主键
 * @author yxf
 *
 */
public class TypeVO implements VO {

	// 二级商品类型编号 secondary type id
	private String GoodsTypeID;
	
	// 二级商品类型名称 secondary type name
	private String GoodsTypeName;

	public String getGoodsTypeID() {
		return GoodsTypeID;
	}

	public void setGoodsTypeID(String goodsTypeID) {
		GoodsTypeID = goodsTypeID;
	}

	public String getGoodsTypeName() {
		return GoodsTypeName;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		GoodsTypeName = goodsTypeName;
	}
	
}
