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
	
	// �ϸ���ҳ��·��
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
	 * �û���½�Ĳ�������
	 */
	private void userLoginDo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = null;
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		// ����ͨ���ϲ�ѯ���û��Ƿ����
		user = userDAO.inquireUserByAccount(userName);
		String pas=MakeMD5.getMD5(MakeMD5.getMD5(passWord) + user.getUser_passw_salt());
		if (user != null&&pas.equals(user.getUser_passw())) {
			// �����user��Ϊ�գ����ж��û�״̬�Ƿ�Ϊ�ɵ�½
			if (user.getUser_status() == IType.YES_LOGIN) {
				// �ɵ�½�򴴽�session��������û����û�������ǳ�
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
