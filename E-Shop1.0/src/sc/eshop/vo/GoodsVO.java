package sc.eshop.vo;

import java.sql.Date;

/**
 * 商品表的实体类，gId为主键
 * stId外键约束于类型表的stId
 * @author yxf
 *
 */
public class GoodsVO implements VO {

	private String GoodsID;

	private String GoodsTypeID;
	
	private String GoodsName;
	
	private String GoodsDesigner;
	
	private float GoodsPrice;
	
	private int GoodsStock;
	
	private String GoodsDescribe;
	
	private int GoodsSales;
	
	private String GoodsUrl;

	public String getGoodsID() {
		return GoodsID;
	}

	public void setGoodsID(String goodsID) {
		GoodsID = goodsID;
	}

	public String getGoodsTypeID() {
		return GoodsTypeID;
	}

	public void setGoodsTypeID(String goodsTypeID) {
		GoodsTypeID = goodsTypeID;
	}

	public String getGoodsName() {
		return GoodsName;
	}

	public void setGoodsName(String goodsName) {
		GoodsName = goodsName;
	}

	public String getGoodsDesigner() {
		return GoodsDesigner;
	}

	public void setGoodsDesigner(String goodsDesigner) {
		GoodsDesigner = goodsDesigner;
	}

	public float getGoodsPrice() {
		return GoodsPrice;
	}

	public void setGoodsPrice(float goodsPrice) {
		GoodsPrice = goodsPrice;
	}

	public int getGoodsStock() {
		return GoodsStock;
	}

	public void setGoodsStock(int goodsStock) {
		GoodsStock = goodsStock;
	}

	public String getGoodsDescribe() {
		return GoodsDescribe;
	}

	public void setGoodsDescribe(String goodsDescribe) {
		GoodsDescribe = goodsDescribe;
	}

	public int getGoodsSales() {
		return GoodsSales;
	}

	public void setGoodsSales(int goodsSales) {
		GoodsSales = goodsSales;
	}

	public String getGoodsUrl() {
		return GoodsUrl;
	}

	public void setGoodsUrl(String goodsUrl) {
		GoodsUrl = goodsUrl;
	}


}
