package com.forestry.service.sys;

import java.util.List;

import com.forestry.model.sys.GoodsAttribute;

import core.service.Service;

public interface GoodsAttributeService extends Service<GoodsAttribute> {

	List<GoodsAttribute> getGoodsAttributeList(List<GoodsAttribute> resultList);

	List<Object[]> queryGoodsAttributeList(long l);
}
