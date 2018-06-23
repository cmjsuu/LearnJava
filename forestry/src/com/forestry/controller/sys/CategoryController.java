package com.forestry.controller.sys;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryImmediateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContext;

import com.forestry.core.ForestryBaseController;
import com.forestry.model.sys.Attachment;
import com.forestry.model.sys.Category;
import com.forestry.model.sys.ForestryType;
import com.forestry.service.sys.AttachmentService;
import com.forestry.service.sys.CategoryService;

import core.extjs.ExtJSBaseParameter;
import core.extjs.ListView;
import core.support.QueryResult;
import core.util.ForestryUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/sys/category")
public class CategoryController extends ForestryBaseController<Category> {

	@Resource
	private CategoryService categoryService;
	@Resource
	private AttachmentService attachmentService;
	
	long CategoryId = 1037000;

	@RequestMapping("/getCategory")
	public void getCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer firstResult = Integer.valueOf(request.getParameter("start"));
		Integer maxResults = Integer.valueOf(request.getParameter("limit"));
		String sortedObject = null;
		String sortedValue = null;
		List<LinkedHashMap<String, Object>> sortedList = mapper.readValue(request.getParameter("sort"), List.class);
		for (int i = 0; i < sortedList.size(); i++) {
			Map<String, Object> map = sortedList.get(i);
			sortedObject = (String) map.get("property");
			sortedValue = (String) map.get("direction");
		}
		Category category = new Category();
		category.setFirstResult(firstResult);
		category.setMaxResults(maxResults);
		Map<String, String> sortedCondition = new HashMap<String, String>();
		sortedCondition.put(sortedObject, sortedValue);
		category.setSortedConditions(sortedCondition);
		QueryResult<Category> queryResult = categoryService.doPaginationQuery(category);
		List<Category> categoryList = categoryService.getCategoryList(queryResult.getResultList());
		for(int i=0;i<categoryList.size();i++)
		{
			if(categoryList.get(i).getParent_id()==0)
			{
				categoryList.get(i).setParentCategoryName("无");
			}
			else{
				categoryList.get(i).setParentCategoryName(categoryService.get((long)categoryList.get(i).getParent_id()).getName());
		
			}
		}
		ListView<Category> categoryListView = new ListView<Category>();
		categoryListView.setData(categoryList);
		categoryListView.setTotalRecord(queryResult.getTotalCount());
		writeJSON(response, categoryListView);
	}

	@RequestMapping("/deleteCategory")
	public void deleteCategory(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Long[] ids) throws IOException {
		boolean flag = categoryService.BrozeCategoryState(ids);
		if (flag) {
			writeJSON(response, "{success:true}");
		} else {
			writeJSON(response, "{success:false}");
		}
	}

	@Override
	@RequestMapping(value = "/saveCategory", method = { RequestMethod.POST, RequestMethod.GET })
	public void doSave(Category entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ExtJSBaseParameter parameter = ((ExtJSBaseParameter) entity);
		Category category = new Category();
		Long flag = null;
		entity.setIs_show(1);///可显示
		if (CMD_EDIT.equals(parameter.getCmd())) {
			categoryService.updateCategoryInfo(entity);
		} else if (CMD_NEW.equals(parameter.getCmd())) {		
			if(entity.getParent_id()!=0)
			{				
				category = categoryService.get((long)entity.getParent_id());
				flag=category.getFlag();
				int unitnum = entity.getParent_id()%10;//获取id后一位
				int decade = entity.getParent_id()/10%10;//获取id倒数第二位				
				long categoryIDflag = 1000000+unitnum*10000+decade*100000+flag+1;
				
				entity.setId(categoryIDflag);
			}
			else if (entity.getParent_id()==0)
			{
				List<Category> categoryParentList = new ArrayList<>() ;				
				List<Category> categoryList = categoryService.doQueryAll();
				for (int i = 0; i < categoryList.size(); i++) {		
					if(categoryList.get(i).getParent_id()==0)
					{
						categoryParentList.add(categoryList.get(i));
					}
				}
				Collections.sort(categoryParentList);
				int size=categoryParentList.size();
				long getid = categoryParentList.get(size-1).getId()+1;
				entity.setId(getid);
			}
			categoryService.persist(entity);
			//确认插入后才执行以下更新（新增二级菜单数）
			if(entity.getParent_id()!=0){
				category.setFlag(++flag);
				categoryService.update(category);
			}
		}
		parameter.setCmd(CMD_EDIT);
		parameter.setSuccess(true);
		writeJSON(response, parameter);
	}

	@RequestMapping("/getCategoryById")
	public void getCategoryById(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Long id) throws Exception {
		Category category = categoryService.get(id);
		writeJSON(response, category);
	}

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	@RequestMapping(value = "/uploadAttachement", method = RequestMethod.POST)
	public void uploadAttachement(@RequestParam(value = "uploadAttachment", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RequestContext requestContext = new RequestContext(request);
		JSONObject json = new JSONObject();
		if (!file.isEmpty()) {
			if (file.getSize() > 2097152) {
				json.put("msg", requestContext.getMessage("g_fileTooLarge"));
			} else {
				try {
					String originalFilename = file.getOriginalFilename();
					String fileName = sdf.format(new Date()) + ForestryUtils.getRandomString(3) + originalFilename.substring(originalFilename.lastIndexOf("."));
					File filePath = new File(getClass().getClassLoader().getResource("/").getPath().replace("/WEB-INF/classes/", "/static/img/upload/" + DateFormatUtils.format(new Date(), "yyyyMM")));
					if (!filePath.exists()) {
						filePath.mkdirs();
					}
					file.transferTo(new File(filePath.getAbsolutePath() + "\\" + fileName));
					json.put("success", true);
					json.put("data", DateFormatUtils.format(new Date(), "yyyyMM") + "/" + fileName);
					json.put("msg", requestContext.getMessage("g_uploadSuccess"));
				} catch (Exception e) {
					e.printStackTrace();
					json.put("msg", requestContext.getMessage("g_uploadFailure"));
				}
			}
		} else {
			json.put("msg", requestContext.getMessage("g_uploadNotExists"));
		}
		writeJSON(response, json.toString());
	}
	
	@RequestMapping("/getFatherCategoryName")
	public void getFatherCategoryName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Category> categoryList = categoryService.doQueryAll();
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < categoryList.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			if(categoryList.get(i).getParent_id()==0)
			{
				jsonObject.element("ItemText", categoryList.get(i).getName());
				jsonObject.element("ItemValue", categoryList.get(i).getId());
				jsonArray.add(jsonObject);
			}
		}
		JSONObject resultJSONObject = new JSONObject();
		resultJSONObject.element("list", jsonArray);
		writeJSON(response, resultJSONObject);
	}
	@RequestMapping("/getCategoryName")
	public void getCategoryName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Category> categoryList = categoryService.doQueryAll();
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < categoryList.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.element("ItemText", categoryList.get(i).getName());
			jsonObject.element("ItemValue", categoryList.get(i).getId());
			jsonArray.add(jsonObject);
		}
		JSONObject resultJSONObject = new JSONObject();
		resultJSONObject.element("list", jsonArray);
		writeJSON(response, resultJSONObject);
	}
}

