package com.forestry.dao.sys;

import java.util.List;

import com.forestry.model.sys.GoodsAttribute;

import core.dao.Dao;

public interface GoodsAttributeDao extends Dao<GoodsAttribute> {

	List<Object[]> queryGoodsAttributeList(long goods_id);
}
