//package sc.eshop.servlet;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import sc.eshop.common.IOrderStatus;
//import sc.eshop.dao.impldao.ImplGoodsDAO;
//import sc.eshop.dao.impldao.ImplOrderDAO;
//import sc.eshop.dao.impldao.ImplOrderDetailDAO;
//import sc.eshop.dao.impldao.ImplUserDAO;
//import sc.eshop.vo.GoodsVO;
//import sc.eshop.vo.OrderDetailVO;
//import sc.eshop.vo.OrderVO;
//import sc.eshop.vo.UserVO;
//
///**
// * 购物车、订单等等综合类
// * 
// * @author yxf
// * 
// */
//public class ShoppingOrderServlet extends HttpServlet {
//
//	private static final long serialVersionUID = 1L;
//
//	private HttpSession session = null;
//
//	private OrderDetailVO orderDetail = null;
//	private OrderVO orderVO = null;
//	private ImplOrderDetailDAO implOrderDetail = null;
//	private ImplOrderDAO implOrder = null;
//	private ImplGoodsDAO implGoods = null;
//
//	protected void doGet(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		this.doPost(request, response);
//	}
//
//	protected void doPost(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//
//		session = request.getSession(false);
//
//		// 判断用户是否登录，即session是否为空或者session中用户编号是否为空
//		// 如果为空，则转到登录页面；如果不为空则可以继续购物操作
//		if (session == null || session.getAttribute("userId") == null) {
//			response.sendRedirect("login.jsp");
//		} else {
//			implOrderDetail = new ImplOrderDetailDAO();
//			implGoods = new ImplGoodsDAO();
//
//			String uri = request.getRequestURI();
//			int startInt = uri.lastIndexOf("/") + 1;
//			int endInt = uri.lastIndexOf(".");
//			String prefix = uri.substring(startInt, endInt);
//			if (prefix.equals("addToCart")) {
//				this.addToCart(request, response);
//			} else if (prefix.equals("payTheCart")) {
//				this.payTheCart(request, response);
//			} else if (prefix.equals("modifyNumber")) {
//				this.modifyNumber(request, response);
//			}
//		}
//
//	}
//
//	/**
//	 * 将商品加入购物车的操作
//	 */
//	private void addToCart(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//
//		orderDetail = new OrderDetailVO();
//		String gId = request.getParameter("gId");
//		GoodsVO goods = implGoods.inquireByGId(gId);
//
//		// 从Session级会话中得到一个购物车(预购商品集合)
//		@SuppressWarnings("unchecked")
//		List<OrderDetailVO> cartList = (ArrayList<OrderDetailVO>) session
//				.getAttribute("cartList");
//
//		// 如果cartList为空，则创建一个购物车，向其中加入第一件商品并将其加入session
//		if (cartList == null) {
//			cartList = new ArrayList<OrderDetailVO>();
//			orderDetail.setgId(gId);
//			orderDetail.setgName(goods.getgName());
//			orderDetail.setgNumber(1);
//			orderDetail.setgPrice(goods.getgPrice());
//			orderDetail.setgSubtotal(goods.getgPrice());
//			cartList.add(orderDetail);
//			// 将该项商品小计作为总价，加入到Session级会话中
//			session.setAttribute("totalMoney", orderDetail.getgSubtotal());
//			session.setAttribute("cartList", cartList);
//		} else { // 如果不为空，则向取到的cartList中添加商品
//			boolean flag = false; // 标示购物车中商品是否已更新，默认为否
//			double totalMoney = 0; // 商品总价
//			OrderDetailVO temp = null;
//			for (int i = 0; i < cartList.size(); i++) {
//				temp = cartList.get(i);
//				if (gId.equals(temp.getgId())) {
//					// 如果两个商品编号相同,把商品的数量在原有的基础上加1
//					temp.setgNumber(temp.getgNumber() + 1);
//					// 价格小计在于有基础上加上该商品的单价
//					temp.setgSubtotal(temp.getgPrice() * temp.getgNumber());
//					flag = true;
//				}
//				// 统计商品总价
//				totalMoney = totalMoney + temp.getgSubtotal();
//			}
//			// 如果该商品没有被购买过，则向购物车添加新商品
//			if (!flag) {
//				orderDetail.setgId(gId);
//				orderDetail.setgName(goods.getgName());
//				orderDetail.setgNumber(1);
//				orderDetail.setgPrice(goods.getgPrice());
//				orderDetail.setgSubtotal(goods.getgPrice());
//				cartList.add(orderDetail);
//				// 统计商品总价
//				totalMoney = totalMoney + orderDetail.getgSubtotal();
//			}
//			session.setAttribute("totalMoney", totalMoney);
//		}
//		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
//				"/shopping_cart.jsp");
//		rd.forward(request, response);
//	}
//
//	/**
//	 * 结账的操作
//	 */
//	private void payTheCart(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//
//		UserVO user = null;
//		orderVO = new OrderVO();
//		implOrder = new ImplOrderDAO();
//		ImplUserDAO implUser = new ImplUserDAO();
//
//		HttpSession session = request.getSession(false);
//		// 取得该用户账号，并获取该用户
//		String userId = (String) session.getAttribute("userId");
//		user = implUser.inquireUserById(userId);
//		// 取得购物车的总价
//		double totalMoney = 0;
//		if (session != null) {
//			totalMoney = (Double) session.getAttribute("totalMoney");
//		}
//		// 如果购物车总价为0，即购物车没商品，则返回首页
//		if (totalMoney == 0) {
//			System.out.println("测试");
//			response.sendRedirect("index");
//		} else {// 开始生成订单，首先找到最新的订单编号
//			int orderId = 0;
//			int latestId = implOrder.getLatestId();
//			// 如果查询到的最新的订单编号不为空，则在原来的值+1
//			// 如果没查到，则orderId为0
//			if (latestId != -1) {
//				orderId = latestId + 1;
//			}
//			orderVO.setoId(orderId);
//			orderVO.setUserId(user.getUserId());
//			orderVO.setoTotalPrice(totalMoney);
//			orderVO.setoAddress(user.getuAddress());
//			orderVO.setoStatus(IOrderStatus.SUBMIT_UNCHECK);
//			orderVO.setoTelephone(user.getuTelephone());
//			// 把ordeVO增加到数据库中
//			boolean flag = implOrder.addOrder(orderVO);
//			if (flag) { // 订单添加成功，则将购物车中的订单明细添加至数据库
//				// 循环添加订单明细
//				@SuppressWarnings("unchecked")
//				List<OrderDetailVO> cartList = (List<OrderDetailVO>) session
//						.getAttribute("cartList");
//				// 标示订单明细表是否添加成功
//				boolean flag2 = false;
//				for (int i = 0; i < cartList.size(); i++) {
//					orderDetail = cartList.get(i);
//					orderDetail.setoId(orderVO.getoId());
//					flag2 = implOrderDetail.addOrderDetail(orderDetail);
//					if (!flag2) {
//						System.out.println("糟糕：订单明细添加失败！");
//						break;
//					}
//				}
//				if (flag2) {
//					// 购买成功，清空购物车
//					session.setAttribute("cartList", null);
//					session.setAttribute("totalMoney", 0);
//					response.sendRedirect("shopping_result.jsp");
//				}
//			} else {
//				System.out.println("糟糕：订单生产失败！");
//			}
//		}
//	}
//
//	/**
//	 * 修改购物车上的商品数
//	 */
//	private void modifyNumber(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		// 操作结果
//		String result = null;
//		// 每当购物车中商品数量失去焦点时，获得从购物车发来的信息
//		String number = request.getParameter("number");
//		String[] msg = number.split("_");
//		String gId = msg[0];// 商品编号
//		Integer goodsNumber = Integer.parseInt(msg[2]);// 商品数量
//		// 从Session级会话中得到购物车(预购商品集合)
//		@SuppressWarnings("unchecked")
//		List<OrderDetailVO> cartList = (ArrayList<OrderDetailVO>) session
//				.getAttribute("cartList");
//		double totalMoney = 0; // 商品总价
//		OrderDetailVO detail = null; // 商品明细
//		// 从购物车中找到商品
//		for (int i = 0; i < cartList.size(); i++) {
//			OrderDetailVO temp = cartList.get(i);
//			if (gId.equals(temp.getgId())) {
//				detail = temp;
//				// 如果两个商品编号相同,把商品的数量设为从网页中传过来的数量
//				temp.setgNumber(goodsNumber);
//				// 重新计算商品小计
//				temp.setgSubtotal(temp.getgPrice() * temp.getgNumber());
//			}
//			// 统计商品总价
//			totalMoney = totalMoney + temp.getgSubtotal();
//		}
//		result = gId + "_" + detail.getgSubtotal() + "_" + totalMoney;
//		PrintWriter out = response.getWriter();
//		out.write(result);
//		out.close();
//	}
//
//}
