package sc.eshop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import sc.eshop.common.IC;
import sc.eshop.dao.impldao.ImplMessageBoardDAO;
import sc.eshop.dao.impldao.ImplNewsDAO;
import sc.eshop.util.PagingUtil;
import sc.eshop.vo.MessageBoardVO;
import sc.eshop.vo.NewsVO;

/**
 * 前台新闻和留言显示
 * 
 * @author leox
 * 
 */
public class NewsAndMessageViewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ImplMessageBoardDAO implMessage = null;
	private List<MessageBoardVO> messageList = null;

	private ImplNewsDAO implNews = null;
	private List<NewsVO> newsList = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		implMessage = new ImplMessageBoardDAO();
		implNews = new ImplNewsDAO();

		String uri = request.getRequestURI();
		int startInt = uri.lastIndexOf("/") + 1;
		int endInt = uri.lastIndexOf(".");
		String prefix = uri.substring(startInt, endInt);
		if (prefix.equals("messageBoardView")) {
			this.messageBoardView(request, response);
		} else if (prefix.equals("messageView")) {
			this.messageView(request, response);
		} else if (prefix.equals("messageAddView")) {
			this.messageAddView(request, response);
		} else if (prefix.equals("messageAddDo")) {
			this.messageAddDo(request, response);
		} else if (prefix.equals("newsBoardView")) {
			this.newsBoardView(request, response);
		} else if (prefix.equals("newsView")) {
			this.newsView(request, response);
		}
	}

	/**
	 * 前台留言板界面的显示
	 */
	private void messageBoardView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String mbId = request.getParameter("mbId");
		request.setAttribute("mbId", mbId);
		// 留言总记录数
		int countRow = implMessage.getCount();
		int pages[] = PagingUtil.paging(countRow, IC.NEW_MESSAGE_PAGE_SIZE,
				request);
		// 分页显示留言
		messageList = implMessage.inquirePaging(pages[0], pages[1]);
		request.setAttribute("messageList", messageList);
		// 留言总数
		List<MessageBoardVO> allMessage = implMessage.inquireAllMessage();
		request.setAttribute("allMessage", allMessage);

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/message_view.jsp");
		rd.forward(request, response);
	}

	/**
	 * 前台留言详细信息的显示
	 */
	private void messageView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String mbId = request.getParameter("mbId");
		// 查詢指定留言編號的留言信息，并將留言的信息存儲到list中，再通過JSON對象傳遞出去
		MessageBoardVO message = implMessage.inquireById(mbId);
		if (message != null) {
			List<String> list = new ArrayList<String>();
			list.add(message.getMessageTitle());
			list.add(message.getMessageContent());
			list.add(message.getMessagePubilshTime().toString());

			PrintWriter out = response.getWriter();
			JSONArray jsonArray = JSONArray.fromObject(list);
			out.write(jsonArray.toString());
			out.close();
		}
	}

	/**
	 * 前台留言板，添加留言界面的显示
	 */
	private void messageAddView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String userId = (String) session.getAttribute("userId");
		// 如果用户没登陆，则返回登陆页面
		if (session == null || userId == null) {
			response.sendRedirect("login.jsp");
		} else { // 如果用户已登陆，则进入添加留言界面
			response.sendRedirect("message_add.jsp");
		}
	}

	/**
	 * 前台留言板，添加留言的操作
	 */
	private void messageAddDo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String userId = (String) session.getAttribute("userId");

		String mTitle = request.getParameter("mTitle");
		String mContent = request.getParameter("mContent");

		MessageBoardVO message = new MessageBoardVO();
		message.setUserID(userId);
		message.setMessageContent(mContent);
		message.setMessageTitle(mTitle);
		boolean flag = implMessage.addMessage(message);
		if (flag) {
			response.sendRedirect("messageBoardView.view");
		} else {
			System.out.println("添加失败！");
		}
	}

	/**
	 * 前台新闻动态界面的显示
	 */
	private void newsBoardView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String nId = request.getParameter("nId");
		request.setAttribute("nId", nId);
		// 获取新闻总记录数
		int countRow = implNews.getCount();
		int[] pages = PagingUtil.paging(countRow, IC.NEW_MESSAGE_PAGE_SIZE,
				request);
		// 分页显示新闻的记录
		newsList = implNews.inquirePaging(pages[0], pages[1]);
		request.setAttribute("newsList", newsList);
		// 所有新闻记录
		List<NewsVO> allNews = implNews.inquireAllNews();
		request.setAttribute("allNews", allNews);

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/news_view.jsp");
		rd.forward(request, response);
	}

	/**
	 * 前台新闻详细信息的显示
	 */
	private void newsView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String nId = request.getParameter("nId");
		System.out.println(nId);
		// 根据nId查询新闻详细信息
		NewsVO news = implNews.inquireById(nId);
		if (news != null) {
			// 將新聞的信息存儲到list中，再通過JSON對象傳遞出去
			List<String> list = new ArrayList<String>();
			list.add(news.getNewsTitle());
			list.add(news.getNewsContent());
			list.add(news.getNewsPublishTime().toString());

			PrintWriter out = response.getWriter();
			JSONArray jsonArray = JSONArray.fromObject(list);
			out.write(jsonArray.toString());
			out.close();
		}
	}

}
