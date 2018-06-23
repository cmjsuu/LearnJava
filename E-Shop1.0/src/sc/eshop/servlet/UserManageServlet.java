package sc.eshop.servlet;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sc.eshop.common.IC;
import sc.eshop.dao.impldao.ImplUserDAO;
import sc.eshop.util.PagingUtil;
import sc.eshop.vo.GamepeopleVO;
import sc.eshop.vo.ToolVO;
import sc.eshop.vo.UserVO;

/**
 * title 后台用户管理的SERVLET类
 * 
 * @author 李建超 2012-11-9 上午9:26:12
 */
public class UserManageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private HttpSession session = null;

	private ImplUserDAO userDao = new ImplUserDAO();
	private List<UserVO> userList = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		session = request.getSession(false);
		
		// 判断用户是否登录，即session是否为空或者session中管理员编号是否为空
		// 如果为空，则转到登录页面；如果不为空则可以继续操作
		if (session == null || session.getAttribute("mId") == null) {
			response.sendRedirect("../login.jsp");
		} else {
			String uri = request.getRequestURI();
			int startIndex = uri.lastIndexOf("/");
			int endIndex = uri.lastIndexOf(".");
			String prefix = uri.substring(startIndex, endIndex);
			if (prefix.equals("/userManageView")) {
				userManageView(request, response);
			} else if (prefix.equals("/userAddDo")) {
				userAddDo(request, response);
			} else if (prefix.equals("/userModifyDo")) {
				userModifyDo(request, response);
			} else if (prefix.equals("/userModifyView")) {
				userModifyView(request, response);
			} else if (prefix.equals("/userDeleteDo")) {
				userDeleteDo(request, response);
			}
			else if (prefix.equals("/userLookView")) {

					userLookView(request, response);
				
			}
			else if (prefix.equals("/PackLookView")) {

				PackLookView(request, response);
			
		}
	
		}
	}

	private void PackLookView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String packId = request.getParameter("packID");
		List<ToolVO> toolVOs = userDao.inquireAllTools(packId);
		
		request.setAttribute("toolList", toolVOs);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/manage/pack_look.jsp");
		rd.forward(request, response);
	}

	private void userLookView(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		// TODO Auto-generated method stub
		
		String userId = request.getParameter("userId");
		List<GamepeopleVO> gamepeopleVOList = userDao.inquireAllGamepeople(userId);
		
		request.setAttribute("gamepeopleVOList", gamepeopleVOList);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/manage/gamepeople_look.jsp");
		rd.forward(request, response);
	}

	/**
	 * 把原有的用户信息转发到修改界面修
	 */
	private void userModifyView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		UserVO userVO = userDao.inquireUserById(userId);
		String OldTime=userVO.getRegisterDate();
		String TimeOutSecond = null;  
		for (int i = 0; i < OldTime.length(); i++)   
		  {   
		   if (OldTime.substring(i, i + 1).equals(" "))   
		   {     
			TimeOutSecond=OldTime.substring(0,i).trim();     
		   }    
		  } 
		String[] timeStr = TimeOutSecond.split("-");
		
		String year = timeStr[0];
		String month = timeStr[1];
		String day = timeStr[2];
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		request.setAttribute("userVO", userVO);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/manage/user_modify.jsp");
		rd.forward(request, response);
	}

	/**
	 * 删除用户操作
	 */
	private void userDeleteDo(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String userid = request.getParameter("userId");
		boolean flag = userDao.deleteUser(userid);
		if (flag) {
			this.userManageView(request, response);
		} else {
			response.sendRedirect("operator_fail.jsp");
		}
	}

	/**
	 * 修改用户操作
	 */
	private void userModifyDo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String userID = request.getParameter("userId");
		String Accounts = request.getParameter("accounts");
		String telephone = request.getParameter("telephone");
		String address = request.getParameter("address");
		String uEmail = request.getParameter("email");
		String uStatus = request.getParameter("status");
		String uPassword = request.getParameter("password");
		// 用户出生日期的年、月、日
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");

		
		UserVO user = new UserVO();
		user.setUserID(userID);
		user.setAccounts(Accounts);
		//user.setuName(userName);
		user.setUserAddress(address);
		user.setUserEmail(uEmail);
		user.setUserStatus(Integer.parseInt(uStatus));
		user.setUserPassword(uPassword);
		user.setUserTelephone(telephone);
		

		if (year != null && month != null && day != null) {			
			String birthday = year + "-" + month + "-" + day;
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");  
	        java.util.Date d = null;  
	        try {  
	            d = format.parse(birthday);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        java.sql.Date date = new java.sql.Date(d.getTime());
	        user.setUserBirthday(date);

		}
		
		boolean flag = userDao.updateUser(user);
		if (flag) {
			userManageView(request, response);
		} else {
			response.sendRedirect("operator_fail.jsp");
		}

	}

	/**
	 * 增加一个用户操作
	 */
	private void userAddDo(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		//String userName = request.getParameter("userName");
		String accounts = request.getParameter("accounts");
		String pwd = request.getParameter("passWord");
		String gender = request.getParameter("sex");
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");
		String uEmail = request.getParameter("email");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");

		String birthday = year + "-" + month + "-" + day;
		UserVO user = new UserVO();
		//user.setuNickname(nickname);
		user.setAccounts(accounts);
		user.setUserAddress(address);
		user.setUserPassword(pwd);
		user.setUserEmail(uEmail);
		user.setUserSex(gender);
		user.setUserTelephone(mobile);
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");  
	        java.util.Date d = null;  
	        try {  
	            d = format.parse(birthday);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        java.sql.Date date = new java.sql.Date(d.getTime());
	        user.setUserBirthday(date);
		boolean flag = userDao.addUser(user);
		if (flag) {
			// System.out.println(user.getuName() + "用户添加成功！");
			userManageView(request, response);
		} else {
			response.sendRedirect("operator_fail.jsp");
		}

	}

	/**
	 * 查找所有用户操作
	 */
	private void userManageView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int countRow = userDao.getCount();
		int[] row = PagingUtil.paging(countRow, IC.USER_MANAGE_PAGE_SIZE,
				request);
		userList = userDao.inquirePaging(row[0], row[1]);
		request.setAttribute("userList", userList);

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/manage/user_manage.jsp");
		rd.forward(request, response);

	}

}
