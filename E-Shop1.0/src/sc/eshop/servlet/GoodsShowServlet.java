package sc.eshop.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sc.eshop.common.IC;
import sc.eshop.dao.impldao.ImplGoodsCommentDAO;
import sc.eshop.dao.impldao.ImplGoodsDAO;
import sc.eshop.dao.impldao.ImplTypeDAO;
import sc.eshop.util.PagingUtil;
import sc.eshop.vo.GoodsCommentVO;
import sc.eshop.vo.GoodsVO;
import sc.eshop.vo.TypeVO;

/**
 * 前台商品展示
 * 
 * @author yxf
 */
public class GoodsShowServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private TypeVO type = null;
	private ImplTypeDAO implType = null;
	private ImplGoodsCommentDAO implComment = null;

	private ImplGoodsDAO implGoods = null;
	private List<GoodsVO> goodsList = null;

	private String mgc = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		implType = new ImplTypeDAO();
		implComment = new ImplGoodsCommentDAO();
		type = new TypeVO();
		implGoods = new ImplGoodsDAO();
		goodsList = new ArrayList<GoodsVO>();

		String uri = request.getRequestURI();
		int startInt = uri.lastIndexOf("/") + 1;
		int endInt = uri.lastIndexOf(".");
		String prefix = uri.substring(startInt, endInt);
		if (prefix.equals("goodsTypeShow")) {
			this.goodsTypeShow(request, response);
		} else if (prefix.equals("goodsDetailShow")) {
			this.goodsDetailShow(request, response);
		} else if (prefix.equals("addGoodsComment")) {
			this.addGoodsComment(request, response);
		}

	}

	/**
	 * 初始化敏感词
	 */
	public void init() throws ServletException {
		ServletContext sc = this.getServletContext();
		mgc = (String) sc.getAttribute("mgc");
	}

	/**
	 * 前台商品分类展示，分页显示商品
	 */
	private void goodsTypeShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 获取请求参数
		String stid = request.getParameter("stid");
		// 获得类型信息
		type = implType.inquireById(stid);
		request.setAttribute("type", type);
		// ---------------------------分页显示某类商品信息--------------------------
		int countRow = implGoods.getCountByType(stid); // 某类商品的总记录数
		// 获取分页显示的起始行和结束行
		int[] rows = PagingUtil.paging(countRow, IC.TYPE_PAGE_SIZE, request);
		// 通过分页查询获得的某类商品的集合
		goodsList = implGoods.inquirePagingByType(rows[0], rows[1], stid);
		request.setAttribute("goodsList", goodsList);

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/product_type.jsp");
		rd.forward(request, response);

	}

	/**
	 * 前台商品详细信息及该商品的所有评论显示
	 */
	private void goodsDetailShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// -----------------------显示商品详细信息和商品评论---------------------------------
		// 获取商品编号
		String gId = request.getParameter("gId");
		// 通过编号获取该商品的详细信息并设置入request
		GoodsVO goods = implGoods.inquireByGId(gId);
		request.setAttribute("goods", goods);
		// 获取该商品的评论记录数
		int countRow = implComment.getCountByGoodsId(gId);
		// 分页查询该商品评论
		int[] row = PagingUtil.paging(countRow, IC.TYPE_PAGE_SIZE, request);
		List<GoodsCommentVO> commentList = implComment.inquirePagingByGoodsId(
				row[0], row[1], gId);
		request.setAttribute("commentList", commentList);
		// ------------------------将浏览的商品放入coockie中---------------------------
		this.setCookie(request, response, gId);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/product_detail.jsp");
		rd.forward(request, response);
	}

	/**
	 * 将商品放入cookie中
	 */
	private void setCookie(HttpServletRequest request,
			HttpServletResponse response, String gId) {
		// 获得头信息中cookie的信息
		String cookInfo = request.getHeader("cookie");
		String[] str = cookInfo.split(";");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < str.length; i++) {
			String gids = str[i].substring(1, 4);
			if (gids.trim().equals("gId")) {
				list.add(gids);
			}
		}
		// 创建cookie
		Cookie[] cookies = request.getCookies();
		Cookie cookieInfo = null;
		Cookie cookie = null;
		for (int i = 0; i < cookies.length; i++) {
			cookieInfo = cookies[i];
			if (cookieInfo.getName().equals("gId")) {
				if (list.size() < 3) {
					cookie = new Cookie("gId1", gId);
				} else {
					cookie = new Cookie("gId", gId);
				}
			} else if (cookieInfo.getName().equals("gId1")) {
				if (list.size() < 3) {
					cookie = new Cookie("gId2", gId);

				} else {
					cookie = new Cookie("gId1", gId);
				}
			} else {
				if (list.size() < 3) {
					cookie = new Cookie("gId", gId);
				} else {
					cookie = new Cookie("gId", gId);
				}
			}
		}
		cookie.setMaxAge(24 * 3600);
		response.addCookie(cookie);
	}

	/**
	 * 发表评论 并把评论增加到数据库中,显示到商品详细页面中
	 */
	private void addGoodsComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userId") == null) {
			response.sendRedirect("login.jsp");
		}else{
			String nickname = (String) session.getAttribute("nickname");
			String gId = request.getParameter("gId");
			String goodsContent = request.getParameter("content");
			goodsContent = goodsContent.replaceAll(mgc, "XXX");
			// 得到参数内容 把参数封装到goodsComment中
			GoodsCommentVO goodsComment = new GoodsCommentVO();
			goodsComment.setcComment(goodsContent);
			goodsComment.setgId(gId);
			goodsComment.setNickname(nickname);
			// 把评论对象增加到数据库中
			boolean flag = implComment.addComment(goodsComment);
			if (flag) {
				// 调用商品详情显示方法
				this.goodsDetailShow(request, response);
			} else {
				System.out.println("商品评论添加失败！");
			}
		}
	}

}
