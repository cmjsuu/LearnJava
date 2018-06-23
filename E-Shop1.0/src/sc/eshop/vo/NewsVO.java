package sc.eshop.vo;

import java.sql.Date;

/**
 * 新闻实体类，nId为主键
 * userId外键约束于用户表的userId
 * @author yxf
 *
 */
public class NewsVO implements VO {

	// 发布新闻用户编号
	private String ManagerID;
	private String Manager;


	// 新闻编号
	private String NewsID;
	
	// 新闻标题
	private String NewsTitle;
	
	// 新闻内容
	private String NewsContent;
	
	// 新闻发布时间
	private Date NewsPublishTime;

	public String getManagerID() {
		return ManagerID;
	}

	public void setManagerID(String managerID) {
		ManagerID = managerID;
	}

	public String getNewsID() {
		return NewsID;
	}

	public void setNewsID(String newsID) {
		NewsID = newsID;
	}

	public String getNewsTitle() {
		return NewsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		NewsTitle = newsTitle;
	}

	public String getNewsContent() {
		return NewsContent;
	}

	public void setNewsContent(String newsContent) {
		NewsContent = newsContent;
	}

	public Date getNewsPublishTime() {
		return NewsPublishTime;
	}

	public void setNewsPublishTime(Date newsPublishTime) {
		NewsPublishTime = newsPublishTime;
	}
	
	public String getManager() {
		return Manager;
	}

	public void setManager(String manager) {
		Manager = manager;
	}
	
}
