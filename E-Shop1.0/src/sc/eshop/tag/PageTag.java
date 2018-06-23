package sc.eshop.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
/**
 * 分页显示标签类
 * @author 舒适老师
 *
 */
public class PageTag implements Tag{

	private PageContext pageContext = null;
	// 标签调用者提供的路径
	private String url = null;
	// 总页数
	int countPage = 0;
	// 当前页数 
	int pageNumber = 0;
	
	public void setPageContext(PageContext arg0) {
		this.pageContext = arg0;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	@SuppressWarnings("static-access")
	public int doStartTag() throws JspException {
		// 获得JSP的输出流对象
		JspWriter out = pageContext.getOut();
		try {
			out.println("<table width=\"600\" align=\"center\">");
			out.println("<tr><td align=\"right\">");
			this.writePageHref(out);
			this.writePageSelect(out);
			this.writeSelectJavaScript(out);
			out.println("</td></tr></table>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.SKIP_BODY;
	}
	
	/**
	 * 首页、上一页、下一页、尾页的超链接
	 * @param out JSP输出流
	 */
	private void writePageHref(JspWriter out) throws IOException{
		
		countPage = (Integer)pageContext.getAttribute("countPage",PageContext.REQUEST_SCOPE);
		pageNumber = (Integer)pageContext.getAttribute("pageNumber", PageContext.REQUEST_SCOPE);
		out.println("<div class=\"table_bottom\"><div class=\"div_clear\"><div class=\"left_bottom\"></div><div class=\"center_bottom\">");	
		out.println(" <span>共有"+countPage+"页，当前为第"+pageNumber+"页&nbsp;&nbsp;&nbsp;&nbsp; </span>");
		// 根据URL是否已经带参数来追加分页的请求参数
		String uri = null;
		if (url.indexOf("?") > 0) {
			uri = url + "&page";
		} else {
			uri = url + "?page";
		}
		if (pageNumber > 1) {
			out.println("<div style=\"float:right;padding-right:30px\">");			
			out.println("<a href=\"" + uri + "=1\">首页</a>&nbsp;&nbsp;");
			out.println("<a href=\"" + uri + "=" + (pageNumber-1) + "\">上一页</a>&nbsp;&nbsp;");
			//out.println("</div>");
		}else if (pageNumber <= 1) {
			out.println("<div style=\"float:right;padding-right:30px\">");
			out.println("首页&nbsp;&nbsp;上一页&nbsp;&nbsp;");
			//out.println("</div>");
		}
		if (pageNumber < countPage) {
			out.println("<div style=\"float:right;padding-right:30px\">");
			out.println("<a href=\"" + uri + "=" + (pageNumber+1) + "\">下一页</a>&nbsp;&nbsp;");
			out.println("<a href=\"" + uri + "=" + countPage + "\">尾页</a>&nbsp;&nbsp;");
			//out.println("</div>");
		} else if (pageNumber == countPage) {
			out.println("<div style=\"float:right;padding-right:30px\">");
			out.println("下一页&nbsp;&nbsp;尾页&nbsp;&nbsp;");
			//out.println("</div>");
		}
		//out.println("</div>");
	}
	
	/**
	 * 生成跳转下拉框
	 * @param out JSP输出流
	 */
	private void writePageSelect(JspWriter out) throws IOException{
		countPage = (Integer)pageContext.getAttribute("countPage",PageContext.REQUEST_SCOPE);
		pageNumber = (Integer)pageContext.getAttribute("pageNumber", PageContext.REQUEST_SCOPE);
		out.println(" <span>跳转到</span><select name=\"page\" onchange=\"forwardPage(this)\">");
		for (int i = 1; i <= countPage; i++) {
			out.println("<option value='"+i+"'");
			if (i == pageNumber) {
				out.println(" selected ");
			}
			out.println(">第" + i + "页</option>");
		}
		out.println("</select></div></div></div><div class=\"right_bottom\"></div></div>");
	}
	
	/**
	 * 生成跳转的JavaScript代码
	 * @param out JSP输出流
	 */
	private void writeSelectJavaScript(JspWriter out) throws IOException{
		out.println("<script type=\"text/javascript\">function forwardPage(selectObj){var page = selectObj.value;");
		// 根据URL是否已经带参数来追加分页的请求参数page
		String uri = null;
		if (url.indexOf("?") > 0) {
			uri = url + "&page=";
		}else {
			uri = url + "?page=";
		}
		out.println("window.location.href=\""+uri+"\"+page;");
		out.println("}</script>");
	}
	
	@SuppressWarnings("static-access")
	public int doEndTag() throws JspException {
		return this.EVAL_PAGE;
	}

	public Tag getParent() {
		return null;
	}

	public void release() {
		
	}

	public void setParent(Tag arg0) {
		
	}

}
