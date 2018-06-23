package com.forestry.service.sys.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder.Case;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.forestry.dao.sys.GoodsAttributeDao;
import com.forestry.dao.sys.GoodsDao;
import com.forestry.model.sys.Attachment;
import com.forestry.model.sys.Forestry;
import com.forestry.model.sys.Goods;
import com.forestry.model.sys.GoodsAttribute;
import com.forestry.service.sys.CategoryService;
import com.forestry.service.sys.GoodsAttributeService;
import com.forestry.service.sys.GoodsService;

import core.service.BaseService;
@Service
public class GoodsServiceImpl extends BaseService<Goods> implements GoodsService {
	
	private GoodsDao goodsDao;

	@Resource
	private GoodsAttributeService goodsAttributeService;
	
	@Resource
	private CategoryService categoryService;
	@Resource
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
		this.dao = goodsDao;
	}

	@Override
	public List<Goods> getGoodsList(List<Goods> resultList) {
		List<Goods> goodsList = new ArrayList<Goods>();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		for (Goods entity : resultList) {
			Goods goods = new Goods();
			goods.setId(entity.getId());
			goods.setCategory_id(entity.getCategory_id());
			goods.setGoods_sn(entity.getGoods_sn());
			goods.setName(entity.getName());
			goods.setBrand_id(entity.getBrand_id());
			goods.setGoods_number(entity.getGoods_number());
			goods.setKeywords(entity.getKeywords());
			goods.setGoods_brief(entity.getGoods_brief());
			goods.setGoods_desc(entity.getGoods_desc());
			goods.setIs_on_sale(entity.getIs_on_sale());
			goods.setAdd_time(entity.getAdd_time());
			goods.setSort_order(entity.getSort_order());
			goods.setIs_delete(entity.getIs_delete());
			goods.setAttribute_category(entity.getAttribute_category());
			goods.setCounter_price(entity.getCounter_price());
			goods.setExtra_price(entity.getExtra_price());
			goods.setIs_new(entity.getIs_new());
			goods.setGoods_unit(entity.getGoods_unit());
			goods.setPrimary_pic_url(entity.getPrimary_pic_url());
			goods.setList_pic_url(entity.getList_pic_url());
			goods.setRetail_price(entity.getRetail_price());
			goods.setSell_volume(entity.getSell_volume());
			goods.setPrimary_product_id(entity.getPrimary_product_id());
			goods.setUnit_price(entity.getUnit_price());
			goods.setPromotion_desc(entity.getPromotion_desc());
			goods.setPromotion_tag(entity.getPromotion_tag());
			goods.setApp_exclusive_price(entity.getApp_exclusive_price());
			goods.setIs_app_exclusive(entity.getIs_app_exclusive());
			goods.setIs_limited(entity.getIs_limited());
			goods.setIs_hot(entity.getIs_hot());
			goods.setUrl(entity.getUrl());
			List<Object[]> goodsAttributetList = goodsAttributeService.queryGoodsAttributeList(entity.getId());
			for (int i = 0; i < goodsAttributetList.size(); i++) 
			{
				
				
				int attr=(int) goodsAttributetList.get(i)[0];
				String attrname=String.valueOf(goodsAttributetList.get(i)[2]);
				String attrvalue=String.valueOf(goodsAttributetList.get(i)[3]);
				
				switch (attr) {
				case 102:
					goods.setAttribute_publishHouse(attrname);
					goods.setAttribute_publishHouse_value(attrvalue);
					break;
				case 103:
					goods.setAttribute_author(attrname);
					goods.setAttribute_author_value(attrvalue);
					break;
				case 104:
					goods.setAttribute_volumename(attrname);
					goods.setAttribute_volumename_value(attrvalue);
					break;
				case 105:
					goods.setAttribute_ISBN(attrname);
					StringBuilder ISBN = new StringBuilder();
					
					String[] splitISBN=attrvalue.split("-"); 
					for(int a=0;a<splitISBN.length;a++)
					{
						ISBN.append(splitISBN[a]);
					}
					goods.setAttribute_ISBN_value(ISBN.toString());
					break;
				case 106:
					goods.setAttribute_subtitle(attrname);
					goods.setAttribute_subtitle_value(attrvalue);
					break;
				case 107:
					goods.setAttribute_volumenumber(attrname);
					goods.setAttribute_volumenumber_value(attrvalue);
					break;
				case 108:
					goods.setAttribute_publishplace(attrname);
					goods.setAttribute_publishplace_value(attrvalue);
					break;
				case 109:
					//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
					goods.setAttribute_publishtime(attrname);
					//try {
						goods.setAttribute_publishtime_value(attrvalue);
					//} catch (ParseException e) {
					//	// TODO Auto-generated catch block
					//	e.printStackTrace();
					//}
					break;
				case 110:
					goods.setAttribute_authorbrief(attrname);
					goods.setAttribute_authorbrief_value(attrvalue);
					break;
				case 111:
					goods.setAttribute_pagenum(attrname);
					goods.setAttribute_pagenum_value(attrvalue);
					break;
				default:
					break;
				}
			}
			goodsList.add(goods);
		}
		return goodsList;
	}
	
	@Override
	public List<Object[]> queryExportedGoods(Long[] ids) {
		return goodsDao.queryExportedGoods(ids);
	}
	
	@Override
	public boolean addGoodsInfo(Goods goodentity){		
	return goodsDao.addGoodsInfo(goodentity);
	}
	@Override
	public boolean BrozeGoodsState(Long[] goodsID){		
		return goodsDao.BrozeGoodsState(goodsID);
		}
	@Override
	public boolean updateGoodsInfo(Goods goodentity){		
		return goodsDao.updateGoodsInfo(goodentity);
		}
}
