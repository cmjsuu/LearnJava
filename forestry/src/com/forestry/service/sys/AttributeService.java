package com.forestry.service.sys;

import java.util.List;

import com.forestry.model.sys.Attribute;

import core.service.Service;

public interface AttributeService extends Service<Attribute> {

	List<Attribute> getAttributeList(List<Attribute> resultList);

}