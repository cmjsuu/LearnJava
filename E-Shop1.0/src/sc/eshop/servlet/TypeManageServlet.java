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
import sc.eshop.dao.impldao.ImplTypeDAO;
import sc.eshop.util.PagingUtil;
import sc.eshop.vo.TypeVO;

/**
 * 后台商品类型管理
 * @author yxf
 * 
 */
public class TypeManageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private HttpSession session = null;

	private TypeVO type = null;
	private ImplTypeDAO implType = null;// 商品类型的DAO类
	private List<TypeVO> typeList = null;// 所有商品类型集合
	private List<String> mainTypeList = null;// 所有商品的大类集合

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		type = new TypeVO();
		implType = new ImplTypeDAO();
		typeList = new ArrayList<TypeVO>();
		implType = new ImplTypeDAO();

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
			if (prefix.equals("typeManageView")) {
				this.typeManageView(request, response);
			} else if (prefix.equals("typeModifyView")) {
				 this.typeModifyView(request, response);
			} else if (prefix.equals("typeModifyDo")) {
				 this.typeModifyDo(request, response);
			} else if (prefix.equals("typeAddView")) {
				 this.typeAddView(request, response);
			} else if (prefix.equals("typeAddDo")) {
				 this.typeAddDo(request, response);
			} else if (prefix.equals("typeDeleteDo")) {
				this.typeDeleteDo(request, response);
			}
		}

	}

	/**
	 * 后台商品类型管理页面，分页显示商品类型的方法
	 */
	private void typeManageView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int countRow = implType.getCount(); // 商品类型的总记录数
		// 获取分页显示的起始行和结束行
		int[] rows = PagingUtil.paging(countRow, IC.TYPE_MANAGE_PAGE_SIZE,
				request);
		// 通过分页查询获得的商品类型的集合
		typeList = implType.inquirePaging(rows[0], rows[1]);
		request.setAttribute("typeList", typeList);

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/manage/productType_manage.jsp");
		rd.forward(request, response);
	}

	/**
	 * 后台商品类型修改页面的显示
	 */
	private void typeModifyView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String stId = request.getParameter("stId");
		type = implType.inquireById(stId);
		request.setAttribute("type", type);
		
		mainTypeList = implType.inquireAllMainType();
		request.setAttribute("mainTypeList", mainTypeList);
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/manage/productType_modify.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * 后台商品类型修改页面的操作
	 */
	private void typeModifyDo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String stId = request.getParameter("stId");
		String mtName = request.getParameter("mainType");
		
		type.setGoodsTypeID(stId);
		type.setGoodsTypeName(mtName);
		boolean flag = implType.updateType(type);
		if (flag) {
			response.sendRedirect("operator_success.jsp");
		} else {
			response.sendRedirect("operator_fail.jsp");
		}
	}
	
	/**
	 * 后台商品类型增加页面的显示
	 */
	private void typeAddView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		mainTypeList = implType.inquireAllMainType();
		request.setAttribute("mainTypeList", mainTypeList);
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/manage/productType_add.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * 后台商品类型增加页面的操作
	 */
	private void typeAddDo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String mtName = request.getParameter("mainType");
		//String stName = request.getParameter("secondaryType");
		
		// 数据库是否存在该商品类型的标示
		boolean exist = false;
		typeList = implType.inquireAllType();
		// 判断数据库是否存在该商品类型
		for (int i = 0; i < typeList.size(); i++) {
			if (mtName.equals(typeList.get(i).getGoodsTypeName())) {
				exist = true;
				break;
			}
		}
		if (exist) {
			response.sendRedirect("operator_fail.jsp");
		} else {
			type.setGoodsTypeName(mtName);
			//type.setMtName(mtName);
			boolean flag = implType.addType(type);
			if (flag) {
				response.sendRedirect("operator_success.jsp");
			} else {
				response.sendRedirect("operator_fail.jsp");
			}
		}
	}
	
	/**
	 * 后台商品类型删除操作
	 */
	private void typeDeleteDo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String stId = request.getParameter("stId");
		boolean flag = implType.deleteType(stId);
		if (flag) {
			response.sendRedirect("operator_success.jsp");
		} else {
			response.sendRedirect("operator_fail.jsp");
		}
	}
	
}






















