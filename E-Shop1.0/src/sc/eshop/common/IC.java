package sc.eshop.common;
/**
 * 存放不同情况分页显示中，每页显示条数的接口
 * @author yxf
 *
 */
public interface IC {

	/**
	 * 分类浏览商品页面每页显示数
	 */
	int TYPE_PAGE_SIZE = 8;
	
	/**
	 * 商品管理页面每页显示数
	 */
	int GOODS_MANAGER_SIZE = 4;
	
	/**
	 * 首页特价商品每页显示数
	 */
	int INDEX_GOODS = 8;
	
	/**
	 * 后台的类型管理页面中每页显示类型数
	 */
	int TYPE_MANAGE_PAGE_SIZE = 8;
	
	/**
	 * 前台留言板和新闻板每页显示记录数
	 */
	int NEW_MESSAGE_PAGE_SIZE = 3;
	
	/**
	 * 后台用户管理页面每页显示用户数
	 */
	int USER_MANAGE_PAGE_SIZE = 8;
	
	
}
