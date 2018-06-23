package com.forestry.model.sys.param;

import core.extjs.ExtJSBaseParameter;

public class GoodsAttributeParameter extends ExtJSBaseParameter{

	private static final long serialVersionUID = 4462121985297150686L;
	
	private String attribute_name; 
	private String attribute_category_name; 
	private int $eq_goods_id;
	private int attribute_id; 	
	private String $like_value;
	public String getAttribute_name() {
		return attribute_name;
	}
	public void setAttribute_name(String attribute_name) {
		this.attribute_name = attribute_name;
	}
	public int get$eq_goods_id() {
		return $eq_goods_id;
	}
	public void set$eq_goods_id(int $eq_goods_id) {
		this.$eq_goods_id = $eq_goods_id;
	}
	public int getAttribute_id() {
		return attribute_id;
	}
	public void setAttribute_id(int attribute_id) {
		this.attribute_id = attribute_id;
	}
	public String get$like_value() {
		return $like_value;
	}
	public void set$like_value(String $like_value) {
		this.$like_value = $like_value;
	}
	public String getAttribute_category_name() {
		return attribute_category_name;
	}
	public void setAttribute_category_name(String attribute_category_name) {
		this.attribute_category_name = attribute_category_name;
	} 
}
