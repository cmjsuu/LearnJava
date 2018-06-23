package com.forestry.dao.sys.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.forestry.dao.sys.GoodsDao;
import com.forestry.model.sys.Forestry;
import com.forestry.model.sys.Goods;

import core.dao.BaseDao;
import oracle.net.aso.i;
@Repository
public class GoodsDaoImpl extends BaseDao<Goods> implements GoodsDao {
	
	public GoodsDaoImpl() {
		super(Goods.class);
	}
	int flag = 0;
	@Override
	public List<Object[]> queryExportedGoods(Long[] ids) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ids.length; i++) {
			sb.append(ids[i] + ",");
		}
		Query query = getSession().createSQLQuery(
				"select *from goodsdetailinfo");
		return query.list();
	}
	
	public boolean addGoodsInfo(Goods goodentity){
//	Session session =this.getSession();
//	Transaction trans = session.beginTransaction();  
	try {
	    Query query = this.getSession().createSQLQuery("insert into nideshop_goods (id,category_id,goods_sn,name,goods_number,goods_brief,goods_desc,is_on_sale,add_time,retail_price,app_exclusive_price,unit_price,extra_price,counter_price,is_new,is_app_exclusive,is_limited) values (?,?,?,?,?,?,?,?,?,?,?,0,0,0,1,0,0);");  
	    query.setParameter(0, goodentity.getId());
	    query.setParameter(1, goodentity.getCategory_id());
	    query.setParameter(2, goodentity.getGoods_sn());
	    query.setParameter(3, goodentity.getName());
	    query.setParameter(4, goodentity.getGoods_number());
	    query.setParameter(5, goodentity.getGoods_brief());
	    query.setParameter(6, goodentity.getGoods_desc());
	    query.setParameter(7, goodentity.getIs_on_sale());
	    query.setParameter(8, goodentity.getAdd_time());
	    query.setParameter(9, goodentity.getRetail_price());
	    query.setParameter(10, goodentity.getApp_exclusive_price());
	    query.executeUpdate();
	    
	    Query query_2 = this.getSession().createSQLQuery("insert into nideshop_goods_attribute(goods_id,attribute_id,value) values(?,?,?)");  
	    query_2.setParameter(0, goodentity.getId());
	    setGoodsAttribute(query_2,102,goodentity.getAttribute_publishHouse_value());
	    setGoodsAttribute(query_2,103,goodentity.getAttribute_author_value());
	    setGoodsAttribute(query_2,104,goodentity.getAttribute_volumename_value());
	    setGoodsAttribute(query_2,105,goodentity.getAttribute_ISBN_value());
	    setGoodsAttribute(query_2,106,goodentity.getAttribute_subtitle_value());
	    setGoodsAttribute(query_2,107,goodentity.getAttribute_volumenumber_value());
	    setGoodsAttribute(query_2,108,goodentity.getAttribute_publishplace_value());
	    setGoodsAttribute(query_2,109,goodentity.getAttribute_publishtime_value());
	    setGoodsAttribute(query_2,110,goodentity.getAttribute_authorbrief_value());
	    setGoodsAttribute(query_2,111,goodentity.getAttribute_pagenum_value());
	    //session.getTransaction().commit();//事务提交  
		return true;  
	} catch (Exception e) {  
	    e.printStackTrace();  
	    //trans.rollback();//操作不成功，事务回滚  
	    return false;
	}  
	  
	
	}
	
	public void setGoodsAttribute(Query qu,int atrCaID,String str)
	{
		qu.setParameter(1, atrCaID);
		qu.setParameter(2, str);
		qu.executeUpdate();
	}
	
	public boolean BrozeGoodsState(Long[] goodsID)
	{
		int i = 0;
		for(int a=0;a<goodsID.length;a++)
		{
			Query query = this.getSession().createSQLQuery("update nideshop_goods set is_on_sale=0  where id=?");
			query.setParameter(0, goodsID[a]);
			i+=query.executeUpdate();
		}
		if(i==goodsID.length)
			return true;
		else
			return false;
	}
	public boolean updateGoodsInfo(Goods goodentity){
		try {
			flag=0;
		    Query query = this.getSession().createSQLQuery("update nideshop_goods set category_id=?,name=?,goods_number=?,goods_brief=?,goods_desc=? where id=?");  
		    query.setParameter(0, goodentity.getCategory_id());
		    query.setParameter(1, goodentity.getName());
		    query.setParameter(2, goodentity.getGoods_number());
		    query.setParameter(3, goodentity.getGoods_brief());
		    query.setParameter(4, goodentity.getGoods_desc());
		    query.setParameter(5, goodentity.getId());
		    flag+=query.executeUpdate();
		    
		    Query query_2 = this.getSession().createSQLQuery("update nideshop_goods_attribute set value=? where goods_id=? and attribute_id=?");  
		    query_2.setParameter(1, goodentity.getId());
		    updateGoodsAttribute(query_2,102,goodentity.getAttribute_publishHouse_value());
		    updateGoodsAttribute(query_2,103,goodentity.getAttribute_author_value());
		    updateGoodsAttribute(query_2,104,goodentity.getAttribute_volumename_value());
		    updateGoodsAttribute(query_2,105,goodentity.getAttribute_ISBN_value());
		    updateGoodsAttribute(query_2,106,goodentity.getAttribute_subtitle_value());
		    updateGoodsAttribute(query_2,107,goodentity.getAttribute_volumenumber_value());
		    updateGoodsAttribute(query_2,108,goodentity.getAttribute_publishplace_value());
		    updateGoodsAttribute(query_2,109,goodentity.getAttribute_publishtime_value());
		    updateGoodsAttribute(query_2,110,goodentity.getAttribute_authorbrief_value());
		    updateGoodsAttribute(query_2,111,goodentity.getAttribute_pagenum_value());
		    //session.getTransaction().commit();//事务提交  
			if(flag==21)
		    return true;
			else
				return false;
		} catch (Exception e) {  
		    e.printStackTrace();  
		    //trans.rollback();//操作不成功，事务回滚  
		    return false;
		}  
		  
	}
	public void updateGoodsAttribute(Query qu,int atrCaID,String str)
	{
		qu.setParameter(2, atrCaID);
		qu.setParameter(0, str);
		flag+=qu.executeUpdate();
	}
}
