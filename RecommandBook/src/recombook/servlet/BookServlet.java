package recombook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recombook.dao.impldao.ImplBookDAO;
import recombook.vo.BookVO;

public class BookServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	private ImplBookDAO implBook = null;

	private List<BookVO> bookList = null;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String urisplit[]=uri.split("/");
		String prefix = urisplit[3];
		if (prefix.equals("morePage")) {
			try {
				this.morePage(response, request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		}
	public String morePage(HttpServletResponse response,HttpServletRequest request) throws Exception {
	
		implBook = new ImplBookDAO();
		bookList = implBook.inquireBookList(10);
		request.setAttribute("bookList", bookList);
		return "book/morePage";
	}
	

}
