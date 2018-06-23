package sc.eshop.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sc.eshop.dao.impldao.ImplGoodsDAO;
import sc.eshop.dao.impldao.ImplOrderDAO;
import sc.eshop.dao.impldao.ImplOrderDetailDAO;
import sc.eshop.dao.impldao.ImplUserDAO;
import sc.eshop.vo.GoodsVO;
import sc.eshop.vo.OrderDetailVO;
import sc.eshop.vo.OrderViewVO;
import sc.eshop.vo.UserVO;
/**
 * 个人信息中心
 * @author xcg
 *
 */
public class UserInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private HttpSession session = null;
	
	ImplUserDAO implUser = null;
	ImplGoodsDAO implGoods = null;
	UserVO userVO = null;
	GoodsVO goodsVO = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		implUser = new ImplUserDAO();
		implGoods = new ImplGoodsDAO(); 
		
		session = request.getSession(false);
		
		// 判断用户是否登录，即session是否为空或者session中用户编号是否为空
		// 如果为空，则转到登录页面；如果不为空则可以继续操作
		if (session == null || session.getAttribute("userId") == null) {
			response.sendRedirect("login.jsp");
		} else {
			String uri = request.getRequestURI();
			int startInt = uri.lastIndexOf("/") + 1;
			int endInt = uri.lastIndexOf(".");
			String prefix = uri.substring(startInt, endInt);
			if (prefix.equals("userInfoView")) {
				this.userInfoView(request, response);
			} else if (prefix.equals("center")) {
				 this.center(request, response);
			} else if (prefix.equals("changePasswordDo")) {
				 this.changePasswordDo(request, response);
			} else if (prefix.equals("userOrderView")) {
				 this.userOrderView(request, response);
			} else if (prefix.equals("userOrderDetailView")) {
				 this.userOrderDetailView(request, response);
			} else if (prefix.equals("allOrderDetailView")) {
				 this.allOrderDetailView(request, response);
			} else if (prefix.equals("goodsDeleteDo")) {
//				this.goodsDeleteDo(request, response);
			}
		}
		
	}
	
	/**
	 * 進入個人中心
	 */
	private void center(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取用户信息
		String userId  = (String)session.getAttribute("userId");
		userVO = implUser.inquireUserById(userId);
		
		// 获取购物车信息
		@SuppressWarnings("unchecked")
		List<OrderDetailVO> cartList = (ArrayList<OrderDetailVO>)request.getSession().getAttribute("cartList");
		// 购物车里的商品数
		int length = 0;
		if (cartList != null) {
			length = cartList.size();
		}
		request.setAttribute("length", length);
		// 获取最近浏览过的商品信息
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		List<GoodsVO> goodsList = new ArrayList<GoodsVO>();
		if (cookies != null) {
			for(int i=0;i<cookies.length;i++){
				cookie = cookies[i];
				if(cookie.getName().equals("gId")||
						cookie.getName().equals("gId1")||
						cookie.getName().equals("gId2")){
					String goodsId = cookie.getValue();
					goodsVO = implGoods.inquireByGId(goodsId);
					goodsList.add(goodsVO);
					request.setAttribute("goodsList", goodsList);
				}
			}
		}
		request.setAttribute("userVO", userVO);
		request.setAttribute("cartList", cartList);
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/individual.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * 用户个人信息展示
	 */
	private void userInfoView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String userId = (String)session.getAttribute("userId");
		
		userVO = implUser.inquireUserById(userId);
		request.setAttribute("userVO", userVO);
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/individualinfo.jsp");
		rd.forward(request, response);
	}
	

	/**
	 * 修改密码的操作
	 */
	private void changePasswordDo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		String newPwd = request.getParameter("newpassWord");
		String userId = (String) session.getAttribute("userId");
		userVO = implUser.inquireUserById(userId);
		userVO.setUserPassword(newPwd);
		
		boolean flag = implUser.updateUser(userVO);
		if (flag) {
			response.sendRedirect("changepwdcorrect.jsp");
		}else{
			System.out.println("修改失败！！！");
		}
	}
	

	/**
	 * 查看个人账单
	 */
	private void userOrderView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		ImplOrderDAO implOrder = new ImplOrderDAO();
		String userId = (String)request.getSession().getAttribute("userId");
		List<OrderViewVO> ordersList = implOrder.inquireOrderByUserId(userId);
		int length = ordersList.size();
		request.setAttribute("ordersList", ordersList);
		request.setAttribute("length", length);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/order_user.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * 根据账单编号查看个人某条账单的明细
	 */
	private void userOrderDetailView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String oId = request.getParameter("oId");
		ImplOrderDetailDAO implDetail = new ImplOrderDetailDAO();
		List<OrderDetailVO> detailList = implDetail.inquireOrderDetailByUser(Integer.parseInt(oId));
		request.setAttribute("detailList", detailList);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/order_detail_view.jsp");
		rd.forward(request, response);
	}
	

	/**
	 * 查看该用户所有账单明细
	 */
	private void allOrderDetailView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// 取得该用户的编号
		String userId = (String)request.getSession().getAttribute("userId");
		ImplOrderDAO implOrder = new ImplOrderDAO();
		//存储单个订单编号下的订单信息
		List<OrderDetailVO> orderById = null;
		//存储所有的订单信息
		List<List<OrderDetailVO>> orderList = new ArrayList<List<OrderDetailVO>>();
		ImplOrderDetailDAO implOrderDetail = new ImplOrderDetailDAO();
		@SuppressWarnings("rawtypes")
		List oidsList = implOrder.ininquireOrderIds(userId);
		/**
		 * 获取每个订单的订单信息
		 */
		for(int i=0;i<oidsList.size();i++){
			int oid = (Integer)oidsList.get(i);
			orderById = implOrderDetail.inquireOrderDetailByUser(oid);
			//得到所有的订单信息
			orderList.add(orderById);
		}
		request.setAttribute("orderList", orderList);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/all_order_detail_view.jsp");
		rd.forward(request, response);
		
		
	}
}








