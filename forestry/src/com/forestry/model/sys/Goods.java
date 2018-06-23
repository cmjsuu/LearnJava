package com.forestry.model.sys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.forestry.model.sys.param.GoodsParameter;

import javafx.scene.text.Text;
/**
 * @author MigaChen
 * @time 2018/1/7
 * @商品信息实体类
 */
@Entity
@Table(name = "nideshop_goods")
@Cache(region = "all", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Goods extends GoodsParameter implements Comparable<Goods>{
	
	private static final long serialVersionUID = -1659957832585685551L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id; // ID
	@Column(name = "category_id")
	private long category_id; 
	@Column(name = "goods_sn", length = 60)
	private String goods_sn; 
	@Column(name = "name", length = 120)
	private String name; // 名称
	@Column(name = "brand_id")
	private int brand_id; 
	@Column(name = "goods_number")
	private int goods_number; 
	@Column(name = "keywords", length = 255)
	private String keywords; 
	@Column(name = "goods_brief", length = 255)
	private String goods_brief; 
	@Column(name = "goods_desc")
	private String goods_desc; 
	@Column(name = "is_on_sale")
	private int is_on_sale; 
	@Column(name = "add_time")
	private int add_time; 
	@Column(name = "sort_order")
	private int sort_order; 
	@Column(name = "is_delete")
	private int is_delete; 
	@Column(name = "attribute_category")
	private int attribute_category; 
	@Column(name = "counter_price")
	private double counter_price; 
	@Column(name = "extra_price")
	private double extra_price; 
	@Column(name = "is_new")
	private int is_new; 
	@Column(name = "goods_unit", length = 45)
	private String goods_unit; 
	@Column(name = "primary_pic_url", length = 255)
	private String primary_pic_url; 
	@Column(name = "list_pic_url", length = 255)
	private String list_pic_url; 
	@Column(name = "retail_price")
	private double retail_price; 
	@Column(name = "sell_volume")
	private int sell_volume; 
	@Column(name = "primary_product_id")
	private int primary_product_id; 
	@Column(name = "unit_price")
	private double unit_price; 
	@Column(name = "promotion_desc", length = 255)
	private String promotion_desc; 
	@Column(name = "promotion_tag", length = 45)
	private String promotion_tag; 
	@Column(name = "app_exclusive_price")
	private double app_exclusive_price; 
	@Column(name = "is_app_exclusive")
	private int is_app_exclusive; 
	@Column(name = "is_limited")
	private int is_limited; 
	@Column(name = "is_hot")
	private int is_hot; 
	@Column(name = "url", length = 255)
	private String url;
	
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getCategory_id() {
		return category_id;
	}
	
	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}
	
	public String getGoods_sn() {
		return goods_sn;
	}
	
	public void setGoods_sn(String goods_sn) {
		this.goods_sn = goods_sn;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getBrand_id() {
		return brand_id;
	}
	
	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}
	
	public int getGoods_number() {
		return goods_number;
	}
	
	public void setGoods_number(int goods_number) {
		this.goods_number = goods_number;
	}
	
	public String getKeywords() {
		return keywords;
	}
	
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	public String getGoods_brief() {
		return goods_brief;
	}
	
	public void setGoods_brief(String goods_brief) {
		this.goods_brief = goods_brief;
	}
	
	public String getGoods_desc() {
		return goods_desc;
	}
	
	public void setGoods_desc(String goods_desc) {
		this.goods_desc = goods_desc;
	}
	
	public int getIs_on_sale() {
		return is_on_sale;
	}
	
	public void setIs_on_sale(int is_on_sale) {
		this.is_on_sale = is_on_sale;
	}
	
	public int getAdd_time() {
		return add_time;
	}
	
	public void setAdd_time(int add_time) {
		this.add_time = add_time;
	}
	
	public int getSort_order() {
		return sort_order;
	}
	
	public void setSort_order(int sort_order) {
		this.sort_order = sort_order;
	}
	
	public int getIs_delete() {
		return is_delete;
	}
	
	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}
	
	public int getAttribute_category() {
		return attribute_category;
	}
	
	public void setAttribute_category(int attribute_category) {
		this.attribute_category = attribute_category;
	}
	
	public double getCounter_price() {
		return counter_price;
	}
	
	public void setCounter_price(double counter_price) {
		this.counter_price = counter_price;
	}
	
	public double getExtra_price() {
		return extra_price;
	}
	
	public void setExtra_price(double extra_price) {
		this.extra_price = extra_price;
	}
	
	public int getIs_new() {
		return is_new;
	}
	
	public void setIs_new(int is_new) {
		this.is_new = is_new;
	}
	
	public String getGoods_unit() {
		return goods_unit;
	}
	
	public void setGoods_unit(String goods_unit) {
		this.goods_unit = goods_unit;
	}
	
	public String getPrimary_pic_url() {
		return primary_pic_url;
	}
	
	public void setPrimary_pic_url(String primary_pic_url) {
		this.primary_pic_url = primary_pic_url;
	}
	
	public String getList_pic_url() {
		return list_pic_url;
	}
	
	public void setList_pic_url(String list_pic_url) {
		this.list_pic_url = list_pic_url;
	}
	
	public double getRetail_price() {
		return retail_price;
	}
	
	public void setRetail_price(double retail_price) {
		this.retail_price = retail_price;
	}
	
	public int getSell_volume() {
		return sell_volume;
	}
	
	public void setSell_volume(int sell_volume) {
		this.sell_volume = sell_volume;
	}
	
	public int getPrimary_product_id() {
		return primary_product_id;
	}
	
	public void setPrimary_product_id(int primary_product_id) {
		this.primary_product_id = primary_product_id;
	}
	
	public double getUnit_price() {
		return unit_price;
	}
	
	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	
	public String getPromotion_desc() {
		return promotion_desc;
	}
	
	public void setPromotion_desc(String promotion_desc) {
		this.promotion_desc = promotion_desc;
	}
	
	public String getPromotion_tag() {
		return promotion_tag;
	}
	
	public void setPromotion_tag(String promotion_tag) {
		this.promotion_tag = promotion_tag;
	}
	
	public double getApp_exclusive_price() {
		return app_exclusive_price;
	}
	
	public void setApp_exclusive_price(double app_exclusive_price) {
		this.app_exclusive_price = app_exclusive_price;
	}
	
	public int getIs_app_exclusive() {
		return is_app_exclusive;
	}
	
	public void setIs_app_exclusive(int is_app_exclusive) {
		this.is_app_exclusive = is_app_exclusive;
	}
	
	public int getIs_limited() {
		return is_limited;
	}
	
	public void setIs_limited(int is_limited) {
		this.is_limited = is_limited;
	}
	
	public int getIs_hot() {
		return is_hot;
	}
	
	public void setIs_hot(int is_hot) {
		this.is_hot = is_hot;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}  
	
	public int compareTo(Goods c) {
   //自定义比较方法，如果认为此实体本身大则返回1，否则返回-1
   if(this.id >= c.getId()){
      return 1;
  }
   return -1;
 }
}
