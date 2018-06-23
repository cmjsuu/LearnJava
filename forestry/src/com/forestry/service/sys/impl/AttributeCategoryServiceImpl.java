package com.forestry.service.sys.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forestry.dao.sys.AttributeCategoryDao;
import com.forestry.model.sys.AttributeCategory;
import com.forestry.service.sys.AttributeCategoryService;

import core.service.BaseService;
@Service
public class AttributeCategoryServiceImpl extends BaseService<AttributeCategory> implements AttributeCategoryService {

	private AttributeCategoryDao attributeCategoryDao;

	@Resource
	public void setAttributeCategoryDao(AttributeCategoryDao attributeCategoryDao) {
		this.attributeCategoryDao = attributeCategoryDao;
		this.dao = attributeCategoryDao;
	}

	@Override
	public List<AttributeCategory> getAttributeCategoryList(List<AttributeCategory> resultList) {
		List<AttributeCategory> attributeCategoryList = new ArrayList<AttributeCategory>();
		for (AttributeCategory entity : resultList) {
			AttributeCategory attributeCategory = new AttributeCategory();
			attributeCategory.setId(entity.getId());
			attributeCategory.setName(entity.getName());
			attributeCategory.setEnabled(entity.getEnabled());
			attributeCategoryList.add(attributeCategory);
		}
		return attributeCategoryList;
	}

}
