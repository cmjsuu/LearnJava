package com.forestry.dao.sys;


import java.util.List;

import com.forestry.model.sys.Category;
import com.forestry.model.sys.Goods;
import com.sun.org.apache.xpath.internal.operations.Bool;

import core.dao.Dao;

public interface CategoryDao extends Dao<Category> {

	boolean BrozeCategoryState(Long[] categoryID);
	
	boolean updateCategoryInfo(Category category);
}