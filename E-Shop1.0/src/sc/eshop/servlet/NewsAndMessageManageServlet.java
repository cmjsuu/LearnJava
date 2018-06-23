package sc.eshop.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sc.eshop.common.IC;
import sc.eshop.dao.impldao.ImplMessageBoardDAO;
import sc.eshop.dao.impldao.ImplNewsDAO;
import sc.eshop.dao.impldao.ImplUserDAO;
import sc.eshop.util.PagingUtil;
import sc.eshop.vo.MessageBoardVO;
import sc.eshop.vo.NewsVO;
import sc.eshop.vo.UserVO;

/**
 * 后台新闻与留言管理类
 * 
 * @author yxf
 */
public class NewsAndMessageManageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ImplMessageBoardDAO implMessage = null;
	private ImplNewsDAO implNews = null;
	private ImplUserDAO implUser = null;
	private HttpSession session = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		implMessage = new ImplMessageBoardDAO();
		implNews = new ImplNewsDAO();
		implUser=new ImplUserDAO();
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
			if (prefix.equals("newsManageView")) {
				this.newsManageView(request, response);
			} else if (prefix.equals("newsAddDo")) {
				this.newsAddDo(request, response);
			} else if (prefix.equals("newsModifyView")) {
				this.newsModifyView(request, response);
			} else if (prefix.equals("newsModifyDo")) {
				this.newsModifyDo(request, response);
			} else if (prefix.equals("newsDeleteDo")) {
				this.newsDeleteDo(request, response);
			} else if (prefix.equals("messageManageView")) {
				this.messageManageView(request, response);
			} else if (prefix.equals("messageDatailedView")) {
				this.messageDatailedView(request, response);
			} else if (prefix.equals("messageReplyDo")) {
				this.messageReplyDo(request, response);
			} else if (prefix.equals("messageReplyView")) {
				this.messageReplyView(request, response);
			} else if (prefix.equals("messageDeleteDo")) {
				this.messageDeleteDo(request, response);
			}
			else if (prefix.equals("newDetailedView")) {
				this.newDetailedView(request, response);
			}
		}
	}

	/**
	 * 后台新闻管理界面浏览
	 */
	private void newsManageView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 取得新闻总条数
		int countRow = implNews.getCount();
		// 获取分页查询的起始行和结束行
		int[] rows = PagingUtil
				.paging(countRow, IC.GOODS_MANAGER_SIZE, request);
		// 分页查询所有新闻
		List<NewsVO> newsList = implNews.inquirePaging(rows[0], rows[1]);
		request.setAttribute("newsList", newsList);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/manage/news_view.jsp");
		rd.forward(request, response);
	}

	/**
	 * 后台新闻添加界面操作
	 */
	private void newsAddDo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		session = request.getSession(false);
		String mId = (String) session.getAttribute("mId");
		System.out.println(mId);

		String nTitle = request.getParameter("nTitle");
		String nContent = request.getParameter("nContent");
		NewsVO news = new NewsVO();

		news.setManagerID(mId);
		news.setNewsTitle(nTitle);
		news.setNewsContent(nContent);
		boolean flag = implNews.addNews(news);
		if (flag) {
			//response.sendRedirect("operator_success.jsp");
			newsManageView(request, response);
		} else {
			response.sendRedirect("operator_fail.jsp");
		}
	}

	/**
	 * 后台新闻修改界面浏览
	 */
	private void newsModifyView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获得传入的新闻编号
		String nId = request.getParameter("nId");

		NewsVO news = implNews.inquireById(nId);
		request.setAttribute("news", news);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/manage/news_modify.jsp");
		rd.forward(request, response);
	}
	private void newDetailedView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获得传入的新闻编号
		String nId = request.getParameter("nId");
		NewsVO news = implNews.inquireById(nId);
		request.setAttribute("news", news);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/manage/news_eachview.jsp");
		rd.forward(request, response);
	}

	/**
	 * 后台新闻修改界面操作
	 */
	private void newsModifyDo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String nId = request.getParameter("nId");
		String nTitle = request.getParameter("nTitle");
		String nContent = request.getParameter("nContent");
		NewsVO news = new NewsVO();
		news.setNewsID(nId);
		news.setNewsTitle(nTitle);
		news.setNewsContent(nContent);

		boolean flag = implNews.updateNews(news);
		if (flag) {
			response.sendRedirect("operator_success.jsp");
		} else {
			response.sendRedirect("operator_fail.jsp");
		}
	}

	/**
	 * 后台新闻删除操作
	 */
	private void newsDeleteDo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nId = request.getParameter("nId");
		boolean flag = implNews.deleteNews(nId);
		if (flag) {
			newsManageView(request, response);
		} else {
			response.sendRedirect("operator_fail.jsp");
		}
	}

	/**
	 * 后台用户留言管理界面分页浏览
	 */
	private void messageManageView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int countRow = implMessage.getCount();
		int[] rows = PagingUtil
				.paging(countRow, IC.GOODS_MANAGER_SIZE, request);
		List<MessageBoardVO> messageList = implMessage.inquirePaging(rows[0],
				rows[1]);
		///如果表中用户昵称为空，去user表中取
		for(int i=0;i<messageList.size();i++)
		{
			if(messageList.get(i).getAccounts()==null)
			{
				UserVO user = new UserVO();
				user=implUser.inquireUserById(messageList.get(i).getMessageID());
				messageList.get(i).setAccounts(user.getAccounts());
			}

		}
		request.setAttribute("messageList", messageList);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/manage/message_manage.jsp");
		rd.forward(request, response);
	}
	

	/**
	 * 后台用户留言详细界面浏览
	 */
	private void messageDatailedView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获得传入的新闻编号
		String nId = request.getParameter("mbId");
		MessageBoardVO message = implMessage.inquireById(nId);
		request.setAttribute("message", message);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/manage/message_eachview.jsp");
		rd.forward(request, response);
	}
	/**
	 * 后台留言回复界面浏览
	 */
	private void messageReplyView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获得传入的留言编号
		String mbId = request.getParameter("mbId");
		MessageBoardVO message=implMessage.inquireById(mbId);
		request.setAttribute("message", message);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/manage/message_reply.jsp");
		rd.forward(request, response);
	}
	/**
	 * 后台用户留言回复操作
	 */
	private void messageReplyDo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String reply = request.getParameter("reply");
		String mbId = request.getParameter("mbId");
		MessageBoardVO message=new MessageBoardVO();
		message.setMessageReply(reply);
		message.setMessageState(1);
		message.setMessageID(mbId);
		boolean flag = implMessage.updateMessage(message);
		if (flag) {
			messageManageView(request, response);
			//response.sendRedirect("operator_success.jsp");
		} else {
			response.sendRedirect("operator_fail.jsp");
		}
	}

	/**
	 * 后台用户留言删除操作
	 */
	private void messageDeleteDo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String mbId = request.getParameter("mbId");
		boolean flag = implMessage.deleteMessage(mbId);
		if (flag) {
			messageManageView(request, response);
		} else {
			response.sendRedirect("operator_fail.jsp");
		}
	}

}
