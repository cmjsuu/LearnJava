package sc.eshop.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 生成一张随即数字验证码
 */
public class MakeCertPic extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	// 生成数字和验证码
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		BufferedImage img = new BufferedImage(68, 22,
				BufferedImage.TYPE_INT_RGB);
		// 得到该图片的对象
		Graphics g = img.getGraphics();
		Random r = new Random();
		Color c = new Color(200, 150, 255);
		g.setColor(c);
		// 填充整个图片
		g.fillRect(0, 0, 68, 22);
		// 向图片输出数字和字母
		StringBuffer sb = new StringBuffer();
		char[] ch = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		int index, len = ch.length;
		for (int i = 0; i < 4; i++) {
			index = r.nextInt(len);
			g.setColor(new Color(r.nextInt(66), r.nextInt(155), r.nextInt(255)));
			// 输出字体和大小
			g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
			// 写什么数字，在在图片的什么位子画
			g.drawString("" + ch[index], (i * 15) + 3, 18);
			sb.append(ch[index]);
		}
		// 把图片内容存入Session中
		request.getSession().setAttribute("certCode", sb.toString());
		// 向页面输出
		ImageIO.write(img, "JPG", response.getOutputStream());
	}
	
}
