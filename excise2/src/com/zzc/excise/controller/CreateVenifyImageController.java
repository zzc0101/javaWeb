package com.zzc.excise.controller;

import com.zzc.excise.tools.CreateVerificationCodeImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Servlet implementation class CreateVenifyImageController
 */
@WebServlet("/createVerifyImage.do")
public class CreateVenifyImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateVenifyImageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 把验证图片生成的过程封装在 tools 包下的 CreateVerificationCodeImage 类中
		CreateVerificationCodeImage createVCodeImage = new CreateVerificationCodeImage();
		//产生四位随机字符串
		String vCode = createVCodeImage.createCode();
		// 获取httpSession 对象
		HttpSession session = request.getSession();
		//将产生的四位随机字符串存放于session中（存放在 Session中的数据在一个会话范围内，多个程序全局共享），以便验证。
		session.setAttribute("cerityCode", vCode);
		// 设置返回值
		response.setContentType("img/jpeg");
		// 浏览器不缓存响应内容 -- 验证码图片，点一次就要刷新一次，所以不能出现缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		// 设置验证码失效时间
		response.setDateHeader("Expires", 0);
		// 以字节流发过去，交给img的src属性去解析即可
		BufferedImage image = createVCodeImage.CreateImage(vCode);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image,"JPEG",out);
		out.flush();
		out.close();
	}
}
