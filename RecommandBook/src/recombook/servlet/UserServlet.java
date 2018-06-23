package recombook.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import recombook.common.IType;
import recombook.dao.impldao.ImplUserDAO;
import recombook.util.MakeMD5;
import recombook.vo.UserVO;



public class UserServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private ImplUserDAO userDAO = null;
	
	private UserVO user = null;
	
	// 上个网页的路径
	private String uri = null;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		uri = request.getRequestURI();
		request.setAttribute("uri", uri);
		userDAO = new ImplUserDAO();
		
		int startInt = uri.lastIndexOf("/") + 1;
		int endInt = uri.lastIndexOf(".");
		String prefix = uri.substring(startInt, endInt);
		if (prefix.equals("userLoginDo")) {
			this.userLoginDo(request, response);
		} 
	}
	
	/**
	 * 用户登陆的操作方法
	 */
	private void userLoginDo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = null;
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		// 在普通表上查询该用户是否存在
		user = userDAO.inquireUserByAccount(userName);
		String pas=MakeMD5.getMD5(MakeMD5.getMD5(passWord) + user.getUser_passw_salt());
		if (user != null&&pas.equals(user.getUser_passw())) {
			// 　如果user不为空，则判断用户状态是否为可登陆
			if (user.getUser_status() == IType.YES_LOGIN) {
				// 可登陆则创建session并保存该用户的用户编号与昵称
				session = request.getSession(true);
				session.setAttribute("userId", user.getUser_account());
				session.setAttribute("nickname", user.getUser_name());
				response.sendRedirect("index");
			}
			else
			{
				response.sendRedirect("login_fail.jsp");
			}
		} else {
			response.sendRedirect("login_fail.jsp");
		}
	}

}
