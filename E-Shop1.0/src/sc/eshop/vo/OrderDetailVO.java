package sc.eshop.vo;
/**
 * 订单明细表的实体类，oId和gId联合组成主键
 * oId外键约束于订单表的oId
 * gId外键约束与商品表的gId
 * @author yxf
 *
 */
public class OrderDetailVO implements VO,java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// 所属订单编号
	private int OrderID;
	
	// 商品编号
	private String GoodsID;
	
	// 商品名称
	private String GoodsName;
	
	// 所购商品数量
	private int GoodsNumber;
	
	// 商品价格
	private float GoodPrice;
	
	// 单项商品价格小计
	private float GoodsSubTotal;

	public int getOrderID() {
		return OrderID;
	}

	public void setOrderID(int orderID) {
		OrderID = orderID;
	}

	public String getGoodsID() {
		return GoodsID;
	}

	public void setGoodsID(String goodsID) {
		GoodsID = goodsID;
	}

	public String getGoodsName() {
		return GoodsName;
	}

	public void setGoodsName(String goodsName) {
		GoodsName = goodsName;
	}

	public int getGoodsNumber() {
		return GoodsNumber;
	}

	public void setGoodsNumber(int goodsNumber) {
		GoodsNumber = goodsNumber;
	}

	public float getGoodPrice() {
		return GoodPrice;
	}

	public void setGoodPrice(float goodPrice) {
		GoodPrice = goodPrice;
	}

	public float getGoodsSubTotal() {
		return GoodsSubTotal;
	}

	public void setGoodsSubTotal(float goodsSubTotal) {
		GoodsSubTotal = goodsSubTotal;
	}



}
