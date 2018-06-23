package sc.eshop.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 分页的工具类
 * @author yxf
 *
 */
public class PagingUtil {

	/**
	 * 将所有记录分页显示的方法，会自动向request中设置两个属性："countPage"为总页数；"pageNumber"为要访问的页数
	 * @return int[]:只有2个值。第一个为起始行，第二个为结束行
	 * @param countRow:所有记录数
	 * @param pageSize:每页显示记录数
	 * @param request:HttpServletRequest请求的对象
	 */
	public static int[] paging(int countRow, int pageSize, 
			HttpServletRequest request){
		int countPage = countRow / pageSize; //某类商品的总页数
		// 当总行数不能整除页面数时，页面数+1
		if (countRow % pageSize != 0) {
			countPage++;
		}
		String page = request.getParameter("page"); //页数请求参数
		int pageNumber = 1; //当前页数，初始设为1
		if (page != null && !page.equals("1")) {
			pageNumber = Integer.parseInt(page);
		}
		// 当查询页数超过总页数范围时
		if (pageNumber > countPage) {
			pageNumber = countPage;
		} else if (pageNumber < 1) {
			pageNumber = 1;
		}
		// 设置总页数
		request.setAttribute("countPage", countPage);
		// 设置当前页数
		request.setAttribute("pageNumber", pageNumber);
		// 通过当前页数来生成起始行和结束行
		int startRow = (pageNumber - 1) * pageSize + 1;
		int endRow = pageNumber * pageSize;
		
		return new int[]{startRow,endRow};
	}
	
}
