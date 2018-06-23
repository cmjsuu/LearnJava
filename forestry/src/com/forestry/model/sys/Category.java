package com.forestry.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.forestry.model.sys.param.CategoryParameter;

@Entity
@Table(name = "nideshop_category")
@Cache(region = "all", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIgnoreProperties(value = { "maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "success", "message", "sortColumnsString", "forestrys",
"attachments" })
public class Category extends CategoryParameter implements Comparable<Category>{

	private static final long serialVersionUID = -1659957832585685551L;
	@Id
	@Column(name = "id")
	private long id; // ID
	@Column(name = "name", length = 90)
	private String name; // 名称
	@Column(name = "keywords", length = 255)
	private String keywords;
	@Column(name = "front_desc", length = 255)
	private String front_desc; 
	@Column(name = "parent_id")
	private int parent_id; 
	@Column(name = "sort_order")
	private int sort_order; 
	@Column(name = "show_index")
	private int show_index; 
	@Column(name = "is_show")
	private int is_show; 
	@Column(name = "banner_url", length = 255)
	private String banner_url;
	@Column(name = "icon_url", length = 255)
	private String icon_url; 
	@Column(name = "img_url", length = 255)
	private String img_url;
	@Column(name = "wap_banner_url", length = 255)
	private String wap_banner_url; 
	@Column(name = "level", length = 255)
	private String level;
	@Column(name = "type")
	private int type; 
	@Column(name = "front_name", length = 255)
	private String front_name;
	@Column(name = "flag")
	private Long flag; // ID
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getFront_desc() {
		return front_desc;
	}
	public void setFront_desc(String front_desc) {
		this.front_desc = front_desc;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public int getSort_order() {
		return sort_order;
	}
	public void setSort_order(int sort_order) {
		this.sort_order = sort_order;
	}
	public int getShow_index() {
		return show_index;
	}
	public void setShow_index(int show_index) {
		this.show_index = show_index;
	}
	public int getIs_show() {
		return is_show;
	}
	public void setIs_show(int is_show) {
		this.is_show = is_show;
	}
	public String getBanner_url() {
		return banner_url;
	}
	public void setBanner_url(String banner_url) {
		this.banner_url = banner_url;
	}
	public String getIcon_url() {
		return icon_url;
	}
	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getWap_banner_url() {
		return wap_banner_url;
	}
	public void setWap_banner_url(String wap_banner_url) {
		this.wap_banner_url = wap_banner_url;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getFront_name() {
		return front_name;
	}
	public void setFront_name(String front_name) {
		this.front_name = front_name;
	}
	public Long getFlag() {
		return flag;
	}
	public void setFlag(Long flag) {
		this.flag = flag;
	} 
	public int compareTo(Category c) {
	   //自定义比较方法，如果认为此实体本身大则返回1，否则返回-1
	   if(this.id >= c.getId()){
	      return 1;
	  }
	   return -1;
	 }
}
