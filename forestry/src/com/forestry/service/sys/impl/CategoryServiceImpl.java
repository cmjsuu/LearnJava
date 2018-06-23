package com.forestry.service.sys.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forestry.dao.sys.CategoryDao;
import com.forestry.model.sys.Category;
import com.forestry.service.sys.CategoryService;

import core.service.BaseService;
import core.util.HtmlUtils;
import oracle.net.aso.c;
@Service
public class CategoryServiceImpl extends BaseService<Category> implements CategoryService {

	private CategoryDao categoryDao;

	@Resource
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
		this.dao = categoryDao;
	}

	@Override
	public List<Category> getCategoryList(List<Category> resultList) {
		List<Category> categoryList = new ArrayList<Category>();
		for (Category entity : resultList) {
			Category category = new Category();
			
			category.setId(entity.getId());
			category.setName(entity.getName());
			category.setKeywords(entity.getKeywords());
			category.setFront_desc(entity.getFront_desc());
			category.setParent_id(entity.getParent_id());
			category.setSort_order(entity.getSort_order());
			category.setShow_index(entity.getShow_index());
			category.setIs_show(entity.getIs_show());
			category.setBanner_url(entity.getBanner_url());
			category.setIcon_url(entity.getIcon_url());
			category.setImg_url(entity.getImg_url());
			category.setWap_banner_url(entity.getWap_banner_url());
			category.setLevel(entity.getLevel());
			category.setType(entity.getType());
			category.setFront_name(entity.getFront_name());
	
			categoryList.add(category);
		}
		return categoryList;
	}
	public boolean BrozeCategoryState(Long[] categoryID)
	{
		return categoryDao.BrozeCategoryState(categoryID);
	}
	
	public boolean updateCategoryInfo(Category category)
	{
		return categoryDao.updateCategoryInfo(category);
	}
}

