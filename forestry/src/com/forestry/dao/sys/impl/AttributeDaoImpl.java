package com.forestry.dao.sys.impl;

import org.springframework.stereotype.Repository;

import com.forestry.dao.sys.AttributeDao;
import com.forestry.model.sys.Attribute;
import com.forestry.model.sys.AttributeCategory;

import core.dao.BaseDao;
@Repository
public class AttributeDaoImpl extends BaseDao<Attribute> implements AttributeDao {
	
	public AttributeDaoImpl() {
		super(Attribute.class);
	}
}
