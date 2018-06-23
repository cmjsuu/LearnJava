package com.forestry.dao.sys;

import java.util.List;

import com.forestry.model.sys.Goods;

import core.dao.Dao;

public interface GoodsDao extends Dao<Goods> {
	
	
	List<Object[]> queryExportedGoods(Long[] ids);
	
	boolean addGoodsInfo(Goods goodentity);
	
	boolean BrozeGoodsState(Long[] goodsID);
	
	boolean updateGoodsInfo(Goods goodentity);
	
}
