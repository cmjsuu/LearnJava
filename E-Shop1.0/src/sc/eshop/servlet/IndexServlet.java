package sc.eshop.servlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sc.eshop.common.IC;
import sc.eshop.dao.impldao.ImplGoodsDAO;
import sc.eshop.dao.impldao.ImplMessageBoardDAO;
import sc.eshop.dao.impldao.ImplNewsDAO;
import sc.eshop.util.PagingUtil;
import sc.eshop.vo.GoodsVO;
import sc.eshop.vo.MessageBoardVO;
import sc.eshop.vo.NewsVO;

/**
 * 首页展示
 * 
 * @author ljc
 */
public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ImplGoodsDAO implGoods = null;
	private List<GoodsVO> goodsList = null;
	private List<GoodsVO> allGoods = null;

	private ImplMessageBoardDAO implMessage = null;
	private List<MessageBoardVO> messageList = null;

	private ImplNewsDAO implNews = null;
	private List<NewsVO> newsList = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// -----------------分页显示商品信息----------------
		implGoods = new ImplGoodsDAO();
		int countRow = implGoods.getCount(); // 商品的总记录数
		int[] rows = PagingUtil.paging(countRow, IC.INDEX_GOODS, request);
		// 通过分页查询获得的某类商品的集合
		goodsList = implGoods.inquirePaging(rows[0], rows[1]);
		request.setAttribute("goodsList", goodsList);

		// 获得所有商品集合
		allGoods = implGoods.inquireAllGoods();
		GoodsVO[] goodsHot = new GoodsVO[12];
		for (int i = 0; i < 12; i++) {
			goodsHot[i] = allGoods.get(i);
		}
		request.setAttribute("goodsHot", goodsHot);

		// ------------------显示最新公告标题----------------
		implMessage = new ImplMessageBoardDAO();
		messageList = implMessage.inquireAllMessage();
		// 如果messageList的容量大于8，则返回该集合的第0-7个元素组成的集合
		if (messageList.size() > 8) {
			messageList = messageList.subList(0, 8);
		}
		request.setAttribute("messageList", messageList);

		// ------------------显示新闻动态标题--------------------
		implNews = new ImplNewsDAO();
		newsList = implNews.inquireAllNews();
		// 如果newsList的容量大于8，返回该集合的第0-7个元素组成的集合
		if (newsList.size() > 8) {
			newsList = newsList.subList(0, 8);
		}
		request.setAttribute("newsList", newsList);

		// -----------------显示最近浏览过的商品-----------------
//		Cookie[] cookies = request.getCookies();
//		if (cookies != null) {
//			LinkedList<GoodsVO> latest = new LinkedList<GoodsVO>();
//			Cookie cookie = null;
//			String goodsId = null;
//			GoodsVO goodsVO = null;
//
//			for (int i = 0; i < cookies.length; i++) {
//				cookie = cookies[i];
//				if (cookie.getName().equals("gId")) {
//					goodsId = cookie.getValue();
//					goodsVO = implGoods.inquireByGId(goodsId);
//					latest.offer(goodsVO);
//				}
//				if (cookie.getName().equals("gId1")) {
//					goodsId = cookie.getValue();
//					goodsVO = implGoods.inquireByGId(goodsId);
//					latest.offer(goodsVO);
//				}
//				if (cookie.getName().equals("gId2")) {
//					cookies[i - 2].setValue("");
//					goodsId = cookie.getValue();
//					goodsVO = implGoods.inquireByGId(goodsId);
//					latest.offer(goodsVO);
//				}
//			}
//			request.setAttribute("latest", latest);
//		}
		// -------------------------------转发至首页----------------------------
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/index.jsp");
		rd.forward(request, response);
	}

}
