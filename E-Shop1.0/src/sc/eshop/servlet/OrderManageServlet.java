package sc.eshop.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sc.eshop.common.IC;
import sc.eshop.dao.impldao.ImplOrderDAO;
import sc.eshop.dao.impldao.ImplOrderDetailDAO;
import sc.eshop.util.PagingUtil;
import sc.eshop.vo.OrderDetailVO;
import sc.eshop.vo.OrderVO;
import sc.eshop.vo.OrderViewVO;

/**
 * title 后台订单管理类
 * 
 * @author 李建超 2012-11-7 下午8:52:06
 */
public class OrderManageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private HttpSession session = null;

	private ImplOrderDAO orderDao = null;
	private List<OrderViewVO> orderViews = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		orderDao = new ImplOrderDAO();

		session = request.getSession(false);

		// 判断用户是否登录，即session是否为空或者session中管理员编号是否为空
		// 如果为空，则转到登录页面；如果不为空则可以继续操作
		if (session == null || session.getAttribute("mId") == null) {
			response.sendRedirect("../login.jsp");
		} else {
			String uri = request.getRequestURI();
			int beginIndex = uri.lastIndexOf("/");
			int endIndex = uri.lastIndexOf(".");
			String prefix = uri.substring(beginIndex, endIndex);
			if (prefix.equals("/orderManageView")) {
				this.orderManageView(request, response);
			} else if (prefix.equals("/orderModifyDo")) {
				orderModifyDo(request, response);
			} else if (prefix.equals("/orderDeleteDo")) {
				orderDeleteDo(request, response);
			} else if (prefix.equals("/orderModifyView")) {
				orderModifyView(request, response);
			} else if (prefix.equals("/orderCheckView")) {
				orderCheckView(request, response);
			}else if (prefix.equals("/orderCheckDo")) {
				orderCheckDo(request, response);
			}else if (prefix.equals("/inquireByoIdOrNickname")) {
				inquireByoIdOrNickname(request, response);
			} else if (prefix.equals("/orderDetailView")) {
				orderDetailView(request, response);
			}
		}
	}

	/**
	 * 根据订单号或订货人姓名查询订单
	 */
	private void inquireByoIdOrNickname(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String oId = request.getParameter("orderId");
		String userName = request.getParameter("userName");

		// 如果两者都为空，则直接返回订单管理界面
		if (oId == null && userName == null) {
			response.sendRedirect("orderManageView.om");
		}

		// 如果只有oId不为空
		if (oId != null && userName == null) {
		}

		// 如果只有userName不为空
		if (oId == null && userName != null) {
		}

		// 如果oId和userName都不为空
		if (oId != null && userName != null) {
			int countRow = orderDao.getCount();
			int[] row = PagingUtil.paging(countRow, IC.TYPE_PAGE_SIZE, request);
			// 同时按照两个条件查询
			orderViews = orderDao.inquireByIdAndName(Integer.parseInt(oId),
					userName, row[0], row[1]);
			request.setAttribute("orderViews", orderViews);
			RequestDispatcher rd = this.getServletContext()
					.getRequestDispatcher("/manage/order_manage.jsp");
			rd.forward(request, response);
		}

	}

	/**
	 * 后台订单管理界面，分页显示
	 */
	private void orderManageView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int countRow = orderDao.getCount(); // 获得记录总行数
		int[] rows = PagingUtil
				.paging(countRow, IC.GOODS_MANAGER_SIZE, request);
		// 通过分页查询获得的订单的集合
		orderViews = orderDao.inquireOrderByUser(rows[0], rows[1]);
		request.setAttribute("orderViews", orderViews);

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/manage/order_manage.jsp");
		rd.forward(request, response);
	}

	/**
	 * 后台订单修改界面的显示
	 */
	private void orderModifyView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String oId = request.getParameter("oId");
		OrderVO orderVO = orderDao.inquireOrderById(Integer.parseInt(oId));
		request.setAttribute("orderVO", orderVO);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/manage/order_modify.jsp");
		rd.forward(request, response);
	}

	/**
	 * 后台订单修改的操作
	 */
	private void orderModifyDo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// 得到参数值
		String oId = request.getParameter("oId");
		String oTotalprice = request.getParameter("totalprice");
		String oAddress = request.getParameter("address");
		String oReceivename = request.getParameter("receivename");
		String oTelephone = request.getParameter("telephone");
		//String oStatus = request.getParameter("status");
		
		
		
		
		// 把得到的值封装到OrderVO中
		OrderVO order = new OrderVO();
		order.setOrderID(oId);
		order.setOrderTotalPrice(Float.valueOf(oTotalprice));
		order.setOrderReceiveName(oReceivename);
		order.setOrderAddress(oAddress);
		//order.setoStatus(Integer.parseInt(oStatus));
		order.setOrderTelephone(oTelephone);

		boolean flag = orderDao.updateOrder(order);
		if (flag) {
			response.sendRedirect("operator_success.jsp");
		} else {
			response.sendRedirect("operator_fail.jsp");
		}
	}
	/**
	 * 后台订单审核界面的显示
	 */
	private void orderCheckView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String oId = request.getParameter("oId");
		OrderVO orderVO = orderDao.inquireOrderById(Integer.parseInt(oId));
		request.setAttribute("orderVO", orderVO);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/manage/order_check.jsp");
		rd.forward(request, response);
	}
	/**
	 * 后台订单审核的操作
	 * @throws ServletException 
	 */
	private void orderCheckDo(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		// 得到参数值
		String oId = request.getParameter("oId");
		String oStatus = request.getParameter("status");
		// 把得到的值封装到OrderVO中
		OrderVO order = new OrderVO();
		order.setOrderID(oId);	
		order.setOrderStatus(Integer.parseInt(oStatus));
		
		boolean flag = orderDao.updateOrderStatus(order);
		if (flag) {
			this.orderManageView(request, response);
			//response.sendRedirect("order_manage.jsp");
			//response.sendRedirect("operator_success.jsp");
		} else {
			response.sendRedirect("operator_fail.jsp");
		}
	}
	/**
	 * 后台订单删除操作
	 */
	private void orderDeleteDo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String oId = request.getParameter("oId");
		boolean flag = orderDao.deleteOrder(Integer.parseInt(oId));
		if (flag) {
			response.sendRedirect("operator_success.jsp");
		} else {
			response.sendRedirect("operator_fail.jsp");
		}
	}

	/**
	 * 查看订单明细表
	 */
	private void orderDetailView(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String oId = request.getParameter("oId");
		// 取得该编号的所有订单明细
		ImplOrderDetailDAO implDetail = new ImplOrderDetailDAO();
		int countRow = implDetail.getCountByoId(Integer.parseInt(oId));
		int[] rows = PagingUtil
				.paging(countRow, IC.GOODS_MANAGER_SIZE, request);
		// 根据订单编号，分页查询获得的订单的集合
		ArrayList<OrderDetailVO> detailList = implDetail.inquirePagingByoId(
				Integer.toString(rows[0]), rows[1], oId);
		request.setAttribute("detailList", detailList);

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/manage/order_detail_view.jsp");
		rd.forward(request, response);

	}

}
