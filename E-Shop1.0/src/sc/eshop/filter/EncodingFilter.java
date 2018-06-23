package sc.eshop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 设置字符编码集的过滤器类
 * @author yxf
 */
public class EncodingFilter implements Filter{

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		filterChain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

	public void destroy() {
		
	}
	
}
