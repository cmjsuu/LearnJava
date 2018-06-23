package com.forestry.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.forestry.model.sys.param.AttributeCategoryParameter;

@Entity
@Table(name = "nideshop_attribute_category")
@Cache(region = "all", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AttributeCategory extends AttributeCategoryParameter {
	private static final long serialVersionUID = -1659957832585685551L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id; // ID
	@Column(name = "name")
	private String name;
	@Column(name = "enabled")
	private int enabled;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	} 
	
}
