package com.forestry.model.sys.param;

import core.extjs.ExtJSBaseParameter;

public class CategoryParameter extends ExtJSBaseParameter {

	private static final long serialVersionUID = -6335587468796360403L;
	
	private String parentCategoryName;

	public String getParentCategoryName() {
		return parentCategoryName;
	}

	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}
}
