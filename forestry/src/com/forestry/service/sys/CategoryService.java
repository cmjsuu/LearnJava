package com.forestry.service.sys;

import java.util.List;

import com.forestry.model.sys.Category;

import core.service.Service;

public interface CategoryService extends Service<Category> {

	List<Category> getCategoryList(List<Category> resultList);
	
	boolean BrozeCategoryState(Long[] categoryID);
	
	boolean updateCategoryInfo(Category category);
}