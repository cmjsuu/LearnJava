package com.forestry.dao.sys.impl;

import org.springframework.stereotype.Repository;

import com.forestry.dao.sys.AttributeCategoryDao;
import com.forestry.model.sys.AttributeCategory;


import core.dao.BaseDao;
@Repository
public class AttributeCategoryDaoImpl extends BaseDao<AttributeCategory> implements AttributeCategoryDao {
	public AttributeCategoryDaoImpl() {
		super(AttributeCategory.class);
	}
}
