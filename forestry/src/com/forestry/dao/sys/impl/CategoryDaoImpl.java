package com.forestry.dao.sys.impl;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.forestry.dao.sys.CategoryDao;
import com.forestry.model.sys.Category;
import com.sun.org.apache.xpath.internal.operations.Bool;

import core.dao.BaseDao;
@Repository
public class CategoryDaoImpl extends BaseDao<Category> implements CategoryDao {

	public CategoryDaoImpl() {
		super(Category.class);
	}
	
	public boolean BrozeCategoryState(Long[] categoryID)
	{
		int i = 0;
		for(int a=0;a<categoryID.length;a++)
		{
			Query query = this.getSession().createSQLQuery("update nideshop_category set is_show=0  where id=?");
			query.setParameter(0, categoryID[a]);
			i+=query.executeUpdate();
		}
		if(i==categoryID.length)
			return true;
		else
			return false;
	}

	public boolean updateCategoryInfo(Category category)
	{
		Query query = this.getSession().createSQLQuery("update nideshop_category set name=?,parent_id=?,front_desc=?,banner_url=?,img_url=?,wap_banner_url=? where id=?");  
		query.setParameter(0, category.getName());
	    query.setParameter(1, category.getParent_id());
	    query.setParameter(2, category.getFront_desc());
	    query.setParameter(3, category.getBanner_url());
	    query.setParameter(4, category.getImg_url());
	    query.setParameter(5, category.getWap_banner_url());
	    query.setParameter(6, category.getId());
	    int flag = query.executeUpdate();
	    if(flag!=0)
	    	return true;
	    else 
	    	return false;
	}
}
