package sc.eshop.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sc.eshop.common.IC;
import sc.eshop.dao.impldao.ImplGoodsDAO;
import sc.eshop.util.PagingUtil;
import sc.eshop.vo.GoodsVO;

public class GoodsSearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private ImplGoodsDAO implGoods = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		implGoods = new ImplGoodsDAO();
		
		String uri = request.getRequestURI();
		int startInt = uri.lastIndexOf("/") + 1;
		int endInt = uri.lastIndexOf(".");
		String prefix = uri.substring(startInt, endInt);
		if (prefix.equals("fuzzySearch")) {
			this.fuzzySearch(request, response);
		} else if (prefix.equals("searchByPrice")) {
			 this.searchByPrice(request, response);
		}
		
	}
	
	/**
	 * 模糊查询
	 */
	private void fuzzySearch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获得要查询的关键字
		String keywords = request.getParameter("keywords");
		// 如果关键字为空，则默认为查询所有商品
		if (keywords == null) {
			keywords = "";
		}
		// 进行查询
		int countRow = implGoods.getFuzzySearchCount(keywords);
		int[] rows = PagingUtil.paging(countRow, IC.TYPE_PAGE_SIZE, request);
		List<GoodsVO> goodsList = implGoods.fuzzySearchByNameAndBrand(rows[0], rows[1], keywords);
		// 将结果设入request并转发
		request.setAttribute("goodsList", goodsList);
		request.setAttribute("keywords", keywords);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/fuzzy_search_result.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * 按价格查询
	 */
	private void searchByPrice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int priceRange = Integer.parseInt(request.getParameter("priceRange"));
		
		int countRow = implGoods.getCountByPrice(priceRange);
		int[] rows = PagingUtil.paging(countRow, IC.TYPE_PAGE_SIZE, request);
		List<GoodsVO> goodsList = implGoods.searchByPrice(rows[0], rows[1], priceRange);
		// 将结果设入request并转发
		request.setAttribute("priceRange", priceRange);
		request.setAttribute("goodsList", goodsList);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/fuzzy_search_result.jsp");
		rd.forward(request, response);
	}

}








