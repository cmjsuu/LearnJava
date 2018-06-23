package sc.eshop.dao;

import java.util.List;

import sc.eshop.vo.NewsVO;

/**
 * 新闻表的DAO
 * @author yxf
 *
 */
public interface NewsDAO extends DAO {

	/**
	 * 查询所有新闻，返回List<NewsVO>
	 */
	public List<NewsVO> inquireAllNews();
	
	/**
	 * 查询单条新闻详细信息
	 */
	public NewsVO inquireById(String nId);
	
	/**
	 * 查询新闻的记录总数
	 */
	public int getCount();
	
	/**
	 * 分页查询新闻信息，返回List<NewsVO>
	 */
	public List<NewsVO> inquirePaging(int startRow, int endRow);
	
	/**
	 * 增加一条新闻，返回boolean
	 */
	public boolean addNews(NewsVO news);
	
	/**
	 * 按新闻编号删除一条新闻，返回boolean
	 */
	public boolean deleteNews(String nId);
	
	/**
	 * 修改一条新闻，返回boolean
	 */
	public boolean updateNews(NewsVO news);
	
	
}
