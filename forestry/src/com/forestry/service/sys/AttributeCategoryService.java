package com.forestry.service.sys;

import java.util.List;

import com.forestry.model.sys.AttributeCategory;

import core.service.Service;

public interface AttributeCategoryService extends Service<AttributeCategory> {

	List<AttributeCategory> getAttributeCategoryList(List<AttributeCategory> resultList);

}
