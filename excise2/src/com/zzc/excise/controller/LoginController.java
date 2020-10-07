package com.zzc.excise.controller;

import com.zzc.excise.dao.UserDao;
import com.zzc.excise.tools.MD5Util;
import com.zzc.excise.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserDao userDao = new UserDao(); 
	User user;
	private static final String ERROR = "error";
	private static final String MAIN = "main";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		userPassword = MD5Util.md5(userPassword);
		String[] radio_checks = request.getParameterValues("radio_check");

		String VCode = request.getParameter("Vcode");
		HttpSession session = request.getSession();
		String cerityCode = (String) session.getAttribute("cerityCode");
		
		String path = "";

		//用户反复提交时进行的判断
		if(cerityCode==null&&user!=null) {
			session.setAttribute(MAIN, user);
			path = "/main.jsp";
		} else {
			//判断验证码是否错误
			if(!cerityCode.equalsIgnoreCase(VCode)) {
				System.err.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+":\t验证码错误！");
				request.setAttribute(ERROR, "抱歉，您输入的验证码不正确!!");
				path = "/error.jsp";
			} else {
				//判断用户是否存在
				user = userDao.getUser(userName);
				if(user.getUserName()==null) {
					System.err.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+":\t该用户不存在！");
					request.setAttribute(ERROR, "抱歉，您输入的用户名不存在!!");
					path = "/error.jsp";
				} else {
					//判断用户密码是否正确
					if(user.getPassword().equals(userPassword)) {
						if (radio_checks!=null) {
							Cookie name_cookie = new Cookie("userName",userName);
							Cookie pwd_cookie = new Cookie("userPassword",userPassword);
							name_cookie.setMaxAge(60*60*24*7);
							pwd_cookie.setMaxAge(60*60*24*7);
							response.addCookie(name_cookie);
							response.addCookie(pwd_cookie);
						}
						session.setAttribute("main", user);
						path = "/main.jsp";
					} else {
						System.err.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+":\t用户密码不正确");
						request.setAttribute(ERROR, "抱歉，您输入的密码不正确!!");
						path = "/error.jsp";
					}
				}
			}
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

}
