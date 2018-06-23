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
import sc.eshop.dao.impldao.ImplGoodsDAO;
import sc.eshop.dao.impldao.ImplTypeDAO;
import sc.eshop.util.PagingUtil;
import sc.eshop.vo.GoodsVO;
import sc.eshop.vo.TypeVO;

/**
 * 
 * 后台商品管理
 * @author yxf
 *
 */
public class GoodsManageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private HttpSession session = null;

	private GoodsVO goods = null;
	private ImplGoodsDAO implGoods = null; // 商品的DAO
	private List<GoodsVO> goodsList = null; // 所有商品的集合
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		implGoods = new ImplGoodsDAO();
		goods = new GoodsVO();
		goodsList = new ArrayList<GoodsVO>();
		
		session = request.getSession(false);
		
		// 判断用户是否登录，即session是否为空或者session中管理员编号是否为空
		// 如果为空，则转到登录页面；如果不为空则可以继续操作
		if (session == null || session.getAttribute("mId") == null) {
			response.sendRedirect("../login.jsp");
		} else {
			String uri = request.getRequestURI();
			int startInt = uri.lastIndexOf("/") + 1;
			int endInt = uri.lastIndexOf(".");
			String prefix = uri.substring(startInt, endInt);
			if (prefix.equals("goodsManageView")) {
				this.goodsManageView(request, response);
			} else if (prefix.equals("goodsModifyView")) {
				 this.goodsModifyView(request, response);
			} else if (prefix.equals("goodsModifyDo")) {
				 this.goodsModifyDo(request, response);
			} else if (prefix.equals("goodsAddView")) {
				 this.goodsAddView(request, response);
			} else if (prefix.equals("goodsAddDo")) {
				 this.goodsAddDo(request, response);
			} else if (prefix.equals("goodsDeleteDo")) {
				this.goodsDeleteDo(request, response);
			}
		}
	}

	/**
	 * 后台商品管理页面，分页显示商品
	 */
	private void goodsManageView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		int countRow = implGoods.getCount(); //商品的总记录数
		int[] rows = PagingUtil.paging(countRow, IC.GOODS_MANAGER_SIZE, request);
		// 通过分页查询获得的某类商品的集合
		goodsList = implGoods.inquirePaging(rows[0], rows[1]);
		request.setAttribute("goodsList", goodsList);
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/manage/product_manage.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * 后台商品修改页面的显示
	 */
	private void goodsModifyView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String gId = request.getParameter("gId");
		goods = implGoods.inquireByGId(gId);
		request.setAttribute("goods", goods);
		ImplTypeDAO implType = new ImplTypeDAO();
		List<TypeVO> typeList = implType.inquireAllType();
		request.setAttribute("typeList", typeList);
		//String stName = new ImplTypeDAO().inquireById(goods.getGoodsID()).getGoodsTypeName();
		//request.setAttribute("stName", stName);
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/manage/product_modify.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * 后台商品修改页面的操作
	 */
	private void goodsModifyDo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// 获得网页上的参数
		String gId = request.getParameter("gId");
		String gName = request.getParameter("gName");
		String stId = request.getParameter("stId");
		float gPrice = Float.parseFloat(request.getParameter("gPrice"));
		String gBrand = request.getParameter("gBrand");
		int gStock = Integer.parseInt(request.getParameter("gStock"));
		String gDescribe = request.getParameter("gDescribe");		
		String gUrl = null;
		if (stId.equals("1")) {
			gUrl = "Clothes/" + request.getParameter("gUrl");
		} 
		else  if(stId.equals("2"))
		{
			gUrl = "Fitting/" + request.getParameter("gUrl");
		}
		else  if(stId.equals("3"))
		{
			gUrl = "Jewel/" + request.getParameter("gUrl");
		}
		else  if(stId.equals("4"))
		{
			gUrl = "Outdoor/" + request.getParameter("gUrl");
		}
		else  if(stId.equals("5"))
		{
			gUrl = "Computer/" + request.getParameter("gUrl");
		}
		else  if(stId.equals("6"))
		{
			gUrl = "toy/" + request.getParameter("gUrl");
		}
		// 封装参数
		goods.setGoodsID(gId);
		goods.setGoodsTypeID(stId);
		goods.setGoodsName(gName);
		goods.setGoodsPrice(gPrice);
		goods.setGoodsDesigner(gBrand);
		goods.setGoodsStock(gStock);
		goods.setGoodsDescribe(gDescribe);
		goods.setGoodsUrl(gUrl);
		
		// 修改数据
		boolean flag = implGoods.updateGoods(goods);
		if (flag) {
			goodsManageView(request, response);
		} else {
			response.sendRedirect("operator_fail.jsp");
		}
	}
	
	/**
	 * 后台商品增加页面的显示
	 */
	private void goodsAddView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ImplTypeDAO implType = new ImplTypeDAO();
		List<TypeVO> typeList = implType.inquireAllType();
		request.setAttribute("typeList", typeList);
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/manage/product_add.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * 后台商品增加页面的操作
	 */
	private void goodsAddDo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// 获得网页上的参数
		String gName = request.getParameter("gName");
		String stId = request.getParameter("stId");
		int gPrice = Integer.parseInt(request.getParameter("gPrice"));
		String gBrand = request.getParameter("gBrand");
		int gStock = Integer.parseInt(request.getParameter("gStock"));
		String gDescribe = request.getParameter("gDescribe");
		String gUrl = null;
		if (stId.equals("1")) {
			gUrl = "Clothes/" + request.getParameter("gUrl");
		} 
		else  if(stId.equals("2"))
		{
			gUrl = "Fitting/" + request.getParameter("gUrl");
		}
		else  if(stId.equals("3"))
		{
			gUrl = "Jewel/" + request.getParameter("gUrl");
		}
		else  if(stId.equals("4"))
		{
			gUrl = "Outdoor/" + request.getParameter("gUrl");
		}
		else  if(stId.equals("5"))
		{
			gUrl = "Computer/" + request.getParameter("gUrl");
		}
		else  if(stId.equals("6"))
		{
			gUrl = "toy/" + request.getParameter("gUrl");
		}
		
		// 封装参数
		goods.setGoodsTypeID(stId);
		goods.setGoodsName(gName);
		goods.setGoodsPrice(gPrice);
		goods.setGoodsDesigner(gBrand);
		goods.setGoodsStock(gStock);
		goods.setGoodsDescribe(gDescribe);
		goods.setGoodsUrl(gUrl);
		
		// 添加数据
		boolean flag = implGoods.addGoods(goods);
		if (flag) {
			goodsManageView(request, response);
		} else {
			response.sendRedirect("operator_fail.jsp");
		}
	}
	
	/**
	 * 后台商品删除的操作
	 */
	private void goodsDeleteDo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String gId = request.getParameter("gId");
		boolean flag = implGoods.deleteGoods(gId);
		if (flag) {
			goodsManageView(request, response);
		} else {
			response.sendRedirect("operator_fail.jsp");
		}
	}
	
	
}




