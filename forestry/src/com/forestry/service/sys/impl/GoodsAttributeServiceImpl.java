package com.forestry.service.sys.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forestry.dao.sys.AttributeDao;
import com.forestry.dao.sys.GoodsAttributeDao;
import com.forestry.dao.sys.GoodsDao;
import com.forestry.model.sys.GoodsAttribute;
import com.forestry.service.sys.GoodsAttributeService;

import core.service.BaseService;
@Service
public class GoodsAttributeServiceImpl extends BaseService<GoodsAttribute> implements GoodsAttributeService {

	private GoodsAttributeDao goodsAttributeDao;

	@Resource
	private AttributeDao attributeDao;
	
	@Resource
	private GoodsDao goodsDao;
	@Resource
	public void setGoodsAttributeDao(GoodsAttributeDao goodsAttributeDao) {
		this.goodsAttributeDao = goodsAttributeDao;
		this.dao = goodsAttributeDao;
	}

	@Override
	public List<GoodsAttribute> getGoodsAttributeList(List<GoodsAttribute> resultList) {
		List<GoodsAttribute> goodsAttributeList = new ArrayList<GoodsAttribute>();
		for (GoodsAttribute entity : resultList) {
			GoodsAttribute goodsAttribute = new GoodsAttribute();
			goodsAttribute.setId(entity.getId());
			goodsAttribute.setGoods_id(entity.getGoods_id());
			goodsAttribute.setAttribute_category_name(attributeDao.get(entity.getAttribute_id()).getAttribute_category_name());
			goodsAttribute.setAttribute_name(attributeDao.get(entity.getAttribute_id()).getName());
			goodsAttribute.setValue(entity.getValue());
			goodsAttributeList.add(goodsAttribute);
		}
		return goodsAttributeList;
	}
	
	@Override
	public List<Object[]> queryGoodsAttributeList(long goods_id){
		return goodsAttributeDao.queryGoodsAttributeList(goods_id);
	}

}
