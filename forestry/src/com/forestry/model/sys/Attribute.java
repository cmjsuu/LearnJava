package com.forestry.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.forestry.model.sys.param.AttributeParameter;

@Entity
@Table(name = "nideshop_attribute")
@Cache(region = "all", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Attribute extends AttributeParameter {
	
	private static final long serialVersionUID = -1659957832585685551L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id; // ID
	@Column(name = "attribute_category_id")
	private int attribute_category_id; 
	@Column(name = "name")
	private String name;
	@Column(name = "input_type")
	private int input_type; 
	@Column(name = "values")
	private String values;
	@Column(name = "sort_order")
	private int sort_order;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAttribute_category_id() {
		return attribute_category_id;
	}
	public void setAttribute_category_id(int attribute_category_id) {
		this.attribute_category_id = attribute_category_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getInput_type() {
		return input_type;
	}
	public void setInput_type(int input_type) {
		this.input_type = input_type;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	public int getSort_order() {
		return sort_order;
	}
	public void setSort_order(int sort_order) {
		this.sort_order = sort_order;
	} 
}
