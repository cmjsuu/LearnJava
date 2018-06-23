package com.forestry.service.sys;

import java.util.List;

import com.forestry.model.sys.Goods;

import core.service.Service;

public interface GoodsService extends Service<Goods> {

	List<Goods> getGoodsList(List<Goods> resultList);
	
	List<Object[]> queryExportedGoods(Long[] ids);
	
	boolean addGoodsInfo(Goods goodentity);
	
	boolean BrozeGoodsState(Long[] goodsID);
	
	boolean updateGoodsInfo(Goods goodentity);
}
