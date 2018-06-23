package recombook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recombook.dao.impldao.ImplBookDAO;
import recombook.dao.impldao.ImplCategoryDAO;
import recombook.vo.BookVO;
import recombook.vo.CategoryVO;



public class IndexServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;

	private ImplCategoryDAO implCategory = null;
	
	private ImplBookDAO implBook = null;
	
	private List<CategoryVO> categoryList = null;
	
	private List<CategoryVO> categoryChildrenList = null;
	
	private List<BookVO> bookList = null;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// -----------------读取菜单信息----------------
		implCategory = new ImplCategoryDAO();		
		categoryList = implCategory.inquireParentCategoryList();		
		categoryChildrenList = implCategory.inquireChildrenCategoryList();		
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("categoryChildrenList", categoryChildrenList);
		
		implBook = new ImplBookDAO();
		bookList = implBook.inquireBookList(1);
		request.setAttribute("bookList", bookList);
		// -------------------------------转发至首页----------------------------
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/index.jsp");
		rd.forward(request, response);
	}


}
