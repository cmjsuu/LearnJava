package com.forestry.model.sys.param;

import java.util.Date;
import java.util.List;

import com.forestry.model.sys.GoodsAttribute;

import core.extjs.ExtJSBaseParameter;
import javafx.scene.text.Text;

/**
 * @author MigaChen
 * @time 2018/1/7
 */
public class GoodsParameter extends ExtJSBaseParameter {
	
	private static final long serialVersionUID = 4462121985297150686L;
	
	
		
	private String $like_goods_sn; 
	private String $like_goods_name; 
	
	private List<GoodsAttribute> goodsAttributes ;
		
	private String category_name; 
	
	//一下是书本的各种属性，为了方便查询建立
	private String attribute_publishHouse; 
	
	private String attribute_publishHouse_value; 
	
	private String attribute_author; 
	
	private String attribute_author_value; 
	
	private String attribute_volumename; 
	
	private String attribute_volumename_value; 
	
	private String attribute_ISBN; 
	
	private String attribute_ISBN_value;
	
	private String attribute_subtitle; 
	
	private String attribute_subtitle_value; 
	
	private String attribute_volumenumber; 
	
	private String attribute_volumenumber_value; 
	
	private String attribute_publishplace; 
	
	private String attribute_publishplace_value; 
	
	private String attribute_publishtime; 
	
	private String attribute_publishtime_value; 
	
	private String attribute_authorbrief; 
	
	private String attribute_authorbrief_value; 
	
	private String attribute_pagenum; 
	
	private String attribute_pagenum_value; 
	
	public String getCategory_name() {
		return category_name;
	}
	
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	

	
	public String get$like_goods_sn() {
		return $like_goods_sn;
	}
	
	public void set$like_goods_sn(String $like_goods_sn) {
		this.$like_goods_sn = $like_goods_sn;
	}
	
	public String get$like_goods_name() {
		return $like_goods_name;
	}
	
	public void set$like_goods_name(String $like_goods_name) {
		this.$like_goods_name = $like_goods_name;
	}
	


	public List<GoodsAttribute> getGoodsAttributes() {
		return goodsAttributes;
	}

	public void setGoodsAttributes(List<GoodsAttribute> goodsAttributes) {
		this.goodsAttributes = goodsAttributes;
	}

	public String getAttribute_publishHouse() {
		return attribute_publishHouse;
	}

	public void setAttribute_publishHouse(String attribute_publishHouse) {
		this.attribute_publishHouse = attribute_publishHouse;
	}

	public String getAttribute_publishHouse_value() {
		return attribute_publishHouse_value;
	}

	public void setAttribute_publishHouse_value(String attribute_publishHouse_value) {
		this.attribute_publishHouse_value = attribute_publishHouse_value;
	}

	public String getAttribute_author() {
		return attribute_author;
	}

	public void setAttribute_author(String attribute_author) {
		this.attribute_author = attribute_author;
	}

	public String getAttribute_author_value() {
		return attribute_author_value;
	}

	public void setAttribute_author_value(String attribute_author_value) {
		this.attribute_author_value = attribute_author_value;
	}

	public String getAttribute_volumename() {
		return attribute_volumename;
	}

	public void setAttribute_volumename(String attribute_volumename) {
		this.attribute_volumename = attribute_volumename;
	}

	public String getAttribute_volumename_value() {
		return attribute_volumename_value;
	}

	public void setAttribute_volumename_value(String attribute_volumename_value) {
		this.attribute_volumename_value = attribute_volumename_value;
	}

	public String getAttribute_ISBN() {
		return attribute_ISBN;
	}

	public void setAttribute_ISBN(String attribute_ISBN) {
		this.attribute_ISBN = attribute_ISBN;
	}

	public String getAttribute_ISBN_value() {
		return attribute_ISBN_value;
	}

	public void setAttribute_ISBN_value(String attribute_ISBN_value) {
		this.attribute_ISBN_value = attribute_ISBN_value;
	}

	public String getAttribute_subtitle() {
		return attribute_subtitle;
	}

	public void setAttribute_subtitle(String attribute_subtitle) {
		this.attribute_subtitle = attribute_subtitle;
	}

	public String getAttribute_subtitle_value() {
		return attribute_subtitle_value;
	}

	public void setAttribute_subtitle_value(String attribute_subtitle_value) {
		this.attribute_subtitle_value = attribute_subtitle_value;
	}

	public String getAttribute_volumenumber() {
		return attribute_volumenumber;
	}

	public void setAttribute_volumenumber(String attribute_volumenumber) {
		this.attribute_volumenumber = attribute_volumenumber;
	}

	public String getAttribute_volumenumber_value() {
		return attribute_volumenumber_value;
	}

	public void setAttribute_volumenumber_value(String attribute_volumenumber_value) {
		this.attribute_volumenumber_value = attribute_volumenumber_value;
	}

	public String getAttribute_publishplace() {
		return attribute_publishplace;
	}

	public void setAttribute_publishplace(String attribute_publishplace) {
		this.attribute_publishplace = attribute_publishplace;
	}

	public String getAttribute_publishplace_value() {
		return attribute_publishplace_value;
	}

	public void setAttribute_publishplace_value(String attribute_publishplace_value) {
		this.attribute_publishplace_value = attribute_publishplace_value;
	}

	public String getAttribute_publishtime() {
		return attribute_publishtime;
	}

	public void setAttribute_publishtime(String attribute_publishtime) {
		this.attribute_publishtime = attribute_publishtime;
	}

	public String getAttribute_publishtime_value() {
		return attribute_publishtime_value;
	}

	public void setAttribute_publishtime_value(String attrvalue) {
		this.attribute_publishtime_value = attrvalue;
	}

	public String getAttribute_authorbrief() {
		return attribute_authorbrief;
	}

	public void setAttribute_authorbrief(String attribute_authorbrief) {
		this.attribute_authorbrief = attribute_authorbrief;
	}

	public String getAttribute_authorbrief_value() {
		return attribute_authorbrief_value;
	}

	public void setAttribute_authorbrief_value(String attribute_authorbrief_value) {
		this.attribute_authorbrief_value = attribute_authorbrief_value;
	}

	public String getAttribute_pagenum() {
		return attribute_pagenum;
	}

	public void setAttribute_pagenum(String attribute_pagenum) {
		this.attribute_pagenum = attribute_pagenum;
	}

	public String getAttribute_pagenum_value() {
		return attribute_pagenum_value;
	}

	public void setAttribute_pagenum_value(String attribute_pagenum_value) {
		this.attribute_pagenum_value = attribute_pagenum_value;
	}


	

}
