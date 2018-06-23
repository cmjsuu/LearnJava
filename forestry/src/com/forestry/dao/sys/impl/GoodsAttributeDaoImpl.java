package com.forestry.dao.sys.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.forestry.dao.sys.GoodsAttributeDao;
import com.forestry.model.sys.GoodsAttribute;

import core.dao.BaseDao;
@Repository
public class GoodsAttributeDaoImpl extends BaseDao<GoodsAttribute> implements GoodsAttributeDao {
	
	public GoodsAttributeDaoImpl() {
		super(GoodsAttribute.class);
	}
				
	@Override
	public List<Object[]> queryGoodsAttributeList(long goods_id) {
		Query query = this.getSession().createSQLQuery("select ga.attribute_id,ga.goods_id,a.`name` as attributename,ga.`value`,ac.`name` as atributycategory from nideshop_goods_attribute ga ,nideshop_attribute a ,nideshop_attribute_category ac where ga.goods_id=? and ga.attribute_id=a.id and a.attribute_category_id=ac.id");
		query.setParameter(0, goods_id);
		return query.list();
	}

}
