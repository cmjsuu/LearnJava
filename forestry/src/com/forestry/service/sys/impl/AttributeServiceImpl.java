package com.forestry.service.sys.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forestry.dao.sys.AttributeCategoryDao;
import com.forestry.dao.sys.AttributeDao;
import com.forestry.dao.sys.ForestryTypeDao;
import com.forestry.model.sys.Attribute;
import com.forestry.service.sys.AttributeService;

import core.service.BaseService;
import core.util.HtmlUtils;
@Service
public class AttributeServiceImpl extends BaseService<Attribute> implements AttributeService {

	private AttributeDao attributeDao;

	@Resource
	private AttributeCategoryDao attributeCategoryDao;
	@Resource
	public void setAttributeDao(AttributeDao attributeDao) {
		this.attributeDao = attributeDao;
		this.dao = attributeDao;
	}

	@Override
	public List<Attribute> getAttributeList(List<Attribute> resultList) {
		List<Attribute> attributeList = new ArrayList<Attribute>();
		for (Attribute entity : resultList) {
			Attribute attribute = new Attribute();
			attribute.setId(entity.getId());
			attribute.setName(entity.getName());
			attribute.setAttribute_category_name(attributeCategoryDao.get(entity.getAttribute_category_id()).getName());
			attribute.setInput_type(entity.getInput_type());
			attribute.setSort_order(entity.getSort_order());
			attributeList.add(attribute);
		}
		return attributeList;
	}

}