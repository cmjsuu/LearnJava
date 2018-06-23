package com.forestry.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.forestry.model.sys.param.GoodsAttributeParameter;

@Entity
@Table(name = "nideshop_goods_attribute")
@Cache(region = "all", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GoodsAttribute extends GoodsAttributeParameter {
	
	private static final long serialVersionUID = -1659957832585685551L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id; // ID
	@Column(name = "goods_id")
	private int goods_id; 
	@Column(name = "attribute_id")
	private int attribute_id; 
	@Column(name = "value")
	private String value;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public int getAttribute_id() {
		return attribute_id;
	}
	public void setAttribute_id(int attribute_id) {
		this.attribute_id = attribute_id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	} 
}
