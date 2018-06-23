package sc.eshop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sc.eshop.common.IUserType;
import sc.eshop.dao.impldao.ImplMangerDAO;
import sc.eshop.dao.impldao.ImplQuestionDAO;
import sc.eshop.dao.impldao.ImplUserDAO;
import sc.eshop.vo.ManagerVO;
import sc.eshop.vo.QuestionVO;
import sc.eshop.vo.UserVO;

/**
 * 前台普通用户综合操作SERVLET，也包括管理员登陆
 * @author yxf、hzh、xcg
 */
public class UserForegroundServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ImplUserDAO userDAO = null;
	private ImplMangerDAO managerDAO = null;
	private UserVO user = null;
	private ManagerVO manager = null;
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
		managerDAO = new ImplMangerDAO();

		int startInt = uri.lastIndexOf("/") + 1;
		int endInt = uri.lastIndexOf(".");
		String prefix = uri.substring(startInt, endInt);
		if (prefix.equals("checkUserForQuestion")) {
			this.checkUserForQuestion(request, response);
		} else if (prefix.equals("getPassword")) {
			this.getPassword(request, response);
		} else if (prefix.equals("userRegisterDo")) {
			this.userRegisterDo(request, response);
		} else if (prefix.equals("userLoginDo")) {
			this.userLoginDo(request, response);
		} else if (prefix.equals("userCheck")) {
			this.userCheck(request, response);
		} else if (prefix.equals("userExitDo")) {
			this.userExitDo(request, response);
		} else if (prefix.equals("certPicCheck")) {
			this.certPicCheck(request, response);
		} else if (prefix.equals("nicknameCheck")) {
			this.nicknameCheck(request, response);
		} else if (prefix.equals("mailCheck")) {
			this.mailCheck(request, response);
		}

	}

	/**
	 * 检查用户及其密保问题是否存在
	 */
	private void checkUserForQuestion(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			//获取用户名
				String userName = request.getParameter("userName");
				ImplQuestionDAO implQuestion = new ImplQuestionDAO();
				//通過用戶名查找密保问题
				QuestionVO questionVO = implQuestion.getQuestionByUserId(userName);
				String result = "";
				if (questionVO != null) {
					result = questionVO.getQuestion();
				}else{
					result = "error";
				}
				PrintWriter out = response.getWriter();
				out.write(result);
				out.close();
	}

	/**
	 * 获得用户密码
	 */
	private void getPassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获得用户名及输入的答案
		String uName = request.getParameter("userName");
		String anwser = request.getParameter("anwser");
		
		ImplQuestionDAO implQuestion = new ImplQuestionDAO();
		QuestionVO question = implQuestion.getQuestionByUserId(uName);
		if (question.getAnswer().equals(anwser)) {
			UserVO user = userDAO.inquireUserByuName(uName);
			request.setAttribute("user", user);
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/anwser_correct.jsp");
			rd.forward(request, response);
		}else {
			response.sendRedirect("reget_password.jsp");
		}
	}

	/**
	 * 用户注册的操作方法
	 */
	private void userRegisterDo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 获取用户信息
		String userName = request.getParameter("userName");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String passWord = request.getParameter("passWord");
		String nickname = request.getParameter("nickname");
		String address = request.getParameter("address");
		String connection = request.getParameter("connection");
		String email = request.getParameter("email");
		String regTime = "sysdate";
		int status = IUserType.YES_LOGIN;
		// 封装用户信息
		user = new UserVO();
		//user.setuName(userName);
		//user.setuNickname(nickname);
		//user.setuSex(sex);
		//user.setuBirthday(Date.valueOf(birthday));
		//user.setuPassword(passWord);
		//user.setuAddress(address);
		//user.setuTelephone(connection);
		//user.setuEmail(email);
		//user.setuTime(regTime);
		//user.setuStatus(status);
		// 添加用户
		boolean result1 = userDAO.addUser(user);
		// 获取用户的密保问题
		String question = request.getParameter("question");
		String answer = request.getParameter("anwser");
		// 封装密保对象
		QuestionVO questionVO = new QuestionVO();
		questionVO.setuName(userName);
		questionVO.setQuestion(question);
		questionVO.setAnswer(answer);
		// 添加用户密保
		boolean result2 = new ImplQuestionDAO().addQusetionVO(questionVO);
		// 注册成功
		if (result1 && result2) {
			response.sendRedirect("reg_result.jsp");
		} else {
			System.out.println("注册失败！");
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
		user = userDAO.inquireOneUser(userName, passWord);
		if (user != null) {
			// 　如果user不为空，则判断用户状态是否为可登陆
			//if (user.getuStatus() == IUserType.YES_LOGIN) {
				// 可登陆则创建session并保存该用户的用户编号与昵称
			//	session = request.getSession(true);
				//session.setAttribute("userId", user.getUserId());
				//session.setAttribute("nickname", user.getuNickname());
				//response.sendRedirect("index");
			//}
		}// else {
			// 如果user为空，则从管理员表上查询该用户是否存在
			manager = managerDAO.inquireOneManager(userName, passWord);
			if (manager != null) {
				// 如果manager不为空，则创建session并保存其管理员编号与管理员账号
				session = request.getSession();
				session.setAttribute("mId", manager.getManagerID());
				session.setAttribute("mName", manager.getManagerName());
				session.setAttribute("managerAuthority", manager.getManagerAuthority());
				System.out.println(manager.getManagerAuthority());
				response.sendRedirect("manage/index.jsp");
			} else {
				response.sendRedirect("login_fail.jsp");
			}
		//}
	}

	/**
	 * 用户注册时验证用户名的操作方法
	 */
	private void userCheck(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("验证用户是否存在！");
		// 獲取需驗證的用戶名
		String userName = request.getParameter("userName");
		String result = ""; // 默認驗證結果
		PrintWriter out = response.getWriter();
		// 正則驗證
		if (!userName.matches("\\w{6,15}")) {
			result = "illegal";
		} else {
			// 通過正則驗證，則依次取得所有用戶信息并比較
			List<UserVO> list = userDAO.inquireAllUser();
			boolean flag = false;// 表示數據庫中是否有該用戶，默認為無
			for (int i = 0; i < list.size(); i++) {
				//if (list.get(i).getuName().equals(userName)) {
				//	flag = true;
				//	break;
				//}
			}
			if (flag) {
				result = "exist";
			} else {
				result = "legal";
			}
		}
		out.write(result);
	}

	/**
	 * 用户注册时验证昵称名的操作方法
	 */
	private void nicknameCheck(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 獲取需驗證的昵称
		String nickname = request.getParameter("nickname");
		String result = ""; // 默認驗證結果
		PrintWriter out = response.getWriter();
		// 依次取得所有用戶信息并比較
		List<UserVO> list = userDAO.inquireAllUser();
		boolean flag = false;// 表示數據庫中是否有該用戶，默認為無
		for (int i = 0; i < list.size(); i++) {
			//if (list.get(i).getuNickname().equals(nickname)) {
			//	flag = true;
			//	break;
			//}
		}
		if (flag) {
			result = "exist";
		} else {
			result = "legal";
		}
		out.write(result);
	}

	/**
	 * 验证用户电邮地址的操作方法
	 */
	private void mailCheck(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String result = "";
		if (mail.matches("\\w+@\\w+\\.\\w+")) {
			result = "legal";
		} else {
			result = "illegal";
		}
		PrintWriter out = response.getWriter();
		out.write(result);
	}

	/**
	 * 用户安全退出的操作方法
	 */
	private void userExitDo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession(false).invalidate();
		response.sendRedirect("index");
	}

	/**
	 * 对验证码进行验证的的操作方法
	 */
	private void certPicCheck(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获得SERVLET的输出流
		PrintWriter out = response.getWriter();
		// 从页面拿到输入验证码的值
		String veryCode = request.getParameter("veryCode");
		if (veryCode != null) {
			// 先得到Session
			HttpSession session = request.getSession(false);
			// 从Seesion中拿到验证码图片内的值
			String certCode = (String) session.getAttribute("certCode");
			String str = "false";
			if (veryCode.equals(certCode)) {
				str = "true";
			}
			out.write(str);
		} else {
			out.write("");
		}
	}

}
