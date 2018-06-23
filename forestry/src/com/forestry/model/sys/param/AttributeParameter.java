package com.forestry.model.sys.param;

import core.extjs.ExtJSBaseParameter;
import javafx.scene.text.Text;

public class AttributeParameter extends ExtJSBaseParameter {

	private static final long serialVersionUID = 4462121985297150686L;
	
	private String attribute_category_name; 
	
	private int $eq_attribute_category_id; 	
	private String $like_attribute_name; 
	private int input_type; 
	private int sort_order;
	public String getAttribute_category_name() {
		return attribute_category_name;
	}
	public void setAttribute_category_name(String attribute_category_name) {
		this.attribute_category_name = attribute_category_name;
	}
	public int get$eq_attribute_category_id() {
		return $eq_attribute_category_id;
	}
	public void set$eq_attribute_category_id(int $eq_attribute_category_id) {
		this.$eq_attribute_category_id = $eq_attribute_category_id;
	}
	public String get$like_attribute_name() {
		return $like_attribute_name;
	}
	public void set$like_attribute_name(String $like_attribute_name) {
		this.$like_attribute_name = $like_attribute_name;
	}
	public int getInput_type() {
		return input_type;
	}
	public void setInput_type(int input_type) {
		this.input_type = input_type;
	}
	public int getSort_order() {
		return sort_order;
	}
	public void setSort_order(int sort_order) {
		this.sort_order = sort_order;
	} 

}
