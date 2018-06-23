package com.forestry.controller.sys;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.forestry.core.ForestryBaseController;
import com.forestry.model.sys.Category;
import com.forestry.model.sys.Forestry;
import com.forestry.model.sys.Goods;
import com.forestry.service.sys.CategoryService;
import com.forestry.service.sys.GoodsService;
import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion.Setting;

import core.extjs.ExtJSBaseParameter;
import core.extjs.ListView;
import core.support.QueryResult;

@Controller
@RequestMapping("/sys/goods")
public class GoodsController extends ForestryBaseController<Goods>{

	private static final Logger log = Logger.getLogger(ForestryController.class);
	private static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");

	@Resource
	private GoodsService goodsService;
	@Resource
	private CategoryService categoryService;
	int goods_sn = 1177001;

	@RequestMapping(value = "/getGoods", method = { RequestMethod.POST, RequestMethod.GET })
	public void getForestry(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer firstResult = Integer.valueOf(request.getParameter("start"));
		Integer maxResults = Integer.valueOf(request.getParameter("limit"));
		String sortedObject = null;
		String sortedValue = null;
		request.setCharacterEncoding("utf-8");
		List<LinkedHashMap<String, Object>> sortedList = mapper.readValue(request.getParameter("sort"), List.class);
		for (int i = 0; i < sortedList.size(); i++) {
			Map<String, Object> map = sortedList.get(i);
			sortedObject = (String) map.get("property");
			sortedValue = (String) map.get("direction");
		}
		Goods goods = new Goods();
		String goods_sn = request.getParameter("goods_sn");
		if (StringUtils.isNotBlank(goods_sn)) {
			goods.set$like_goods_sn(goods_sn);
		}
		
		String name = request.getParameter("name");
		if (StringUtils.isNotBlank(name)) {
			goods.set$like_goods_name(name);
		}

		goods.setFirstResult(firstResult);
		goods.setMaxResults(maxResults);
		Map<String, String> sortedCondition = new HashMap<String, String>();
		sortedCondition.put(sortedObject, sortedValue);
		goods.setSortedConditions(sortedCondition);
		QueryResult<Goods> queryResult = goodsService.doPaginationQuery(goods);
		List<Goods> goodsList = goodsService.getGoodsList(queryResult.getResultList());
		
		
		Category category = new Category();		
		category.setFirstResult(firstResult);
		category.setMaxResults(maxResults);
		Map<String, String> sortedCondition_2 = new HashMap<String, String>();
		sortedCondition_2.put(sortedObject, sortedValue);
		goods.setSortedConditions(sortedCondition_2);
		QueryResult<Category> queryResult_2 = categoryService.doPaginationQuery(category);
		List<Category> categoryList = categoryService.getCategoryList(queryResult_2.getResultList());
		
		
		for(int i=0;i<goodsList.size();i++)
		{	
			for(int j=0;j<categoryList.size();j++)
			{
					goodsList.get(i).setCategory_name(categoryService.get((long)goodsList.get(i).getCategory_id()).getName());
			}
		}
		
		ListView<Goods> goodsListView = new ListView<Goods>();
		goodsListView.setData(goodsList);
		goodsListView.setTotalRecord(queryResult.getTotalCount());
		writeJSON(response, goodsListView);
	}
	
	@Override
	@RequestMapping(value = "/saveGoods", method = { RequestMethod.POST, RequestMethod.GET })
	public void doSave(Goods entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ExtJSBaseParameter parameter = ((ExtJSBaseParameter) entity);
		if (CMD_EDIT.equals(parameter.getCmd())) {
			goodsService.updateGoodsInfo(entity);
		} else if (CMD_NEW.equals(parameter.getCmd())) {
					
			List<Goods> goodsList = goodsService.doQueryAll();
			Collections.sort(goodsList);			
			int size=goodsList.size();
			long getid = goodsList.get(size-1).getId()+1;
			entity.setId(getid);
			
			entity.setGoods_sn(String.valueOf(getid));
			entity.setIs_on_sale(1);
			
			Calendar c = Calendar.getInstance();//可以对每个时间域单独修改		
			int year = c.get(Calendar.YEAR); 
			int month = c.get(Calendar.MONTH)+1; 
			int date = c.get(Calendar.DATE);
			int time = date*1+month*100+year*10000;
			entity.setAdd_time(time);//由于数据库表将时间都设置为int类型
			entity.setApp_exclusive_price(0);
			goodsService.addGoodsInfo(entity);
		}
		parameter.setCmd(CMD_EDIT);
		parameter.setSuccess(true);
		writeJSON(response, parameter);
	}
	
	@RequestMapping("/deleteGoods")
	public void deleteGoods(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Long[] ids) throws IOException {
		boolean flag = goodsService.BrozeGoodsState(ids);
		if (flag) {
			writeJSON(response, "{success:true}");
		} else {
			writeJSON(response, "{success:false}");
		}
	}
}
