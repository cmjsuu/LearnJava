package sc.eshop.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {
	
	/**
	 * 销毁方法
	 */
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("我被销毁了......");
	}
	/**
	 * 初始化方法
	 */
	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		String mgc = initMinGanCi(sc);
		mgc = mgc.replaceAll(";","|");
		//将敏感词存入Application级会话中
		sc.setAttribute("mgc", mgc);
	}
	
	/**
	 * 获取敏感词
	 * @param sc
	 * @return
	 */
	public String initMinGanCi(ServletContext sc){
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		String mgc = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					sc.getResourceAsStream("/WEB-INF/MinGangCi.txt"),"UTF-8"));
			sb.append(reader.readLine());
		} 
		catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//将从文件中收集到的所有敏感词存入mgc属性中为每次请求服务
		mgc = sb.toString();
		return mgc;
	}

}
