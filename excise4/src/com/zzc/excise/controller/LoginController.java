package com.zzc.excise.controller;

import com.google.gson.Gson;
import com.zzc.excise.dao.UserDao;
import com.zzc.excise.tools.MD5Util;
import com.zzc.excise.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * Author: zzc
 * function: 效验用户登录信息处理
 */
@WebServlet("/Login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserDao userDao = new UserDao(); 
	User user;
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
		boolean radio_check = Boolean.parseBoolean(request.getParameter("radio_check"));

		String Verify = request.getParameter("Verify_input");

		Map<String,Object> objectMap = new HashMap<String, Object>();

		HttpSession session = request.getSession();
		String verityCode = (String) session.getAttribute("verityCode");

		//判断验证码是否错误
        if(!verityCode.equalsIgnoreCase(Verify)) {
            System.err.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+":\t验证码错误！");
            objectMap.put("code",0);
            objectMap.put("info","抱歉，您输入的验证码不正确!!");
        } else {
            //判断用户是否存在
            user = userDao.getUser(userName);
            if(user.getUserName()==null) {
                System.err.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+":\t该用户不存在！");
                objectMap.put("code",0);
                objectMap.put("info","抱歉，您输入的用户名不存在!!");
            } else {
                //判断用户密码是否正确
                if(userPassword.equals(user.getPassword())) {
                    if (radio_check) {
                        Cookie name_cookie = new Cookie("userName",userName);
                        Cookie pwd_cookie = new Cookie("userPassword",userPassword);
                        name_cookie.setMaxAge(60*60*24*7);
                        pwd_cookie.setMaxAge(60*60*24*7);
                        response.addCookie(name_cookie);
                        response.addCookie(pwd_cookie);
                    }
                    session.setAttribute("main", user);
                    objectMap.put("code",1);
                    objectMap.put("info","登录成功！！");
                } else {
                    System.err.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+":\t用户密码不正确");
                    objectMap.put("code",0);
                    objectMap.put("info","抱歉，您输入的密码不正确!!");
                }
            }
        }

		Gson gson = new Gson();
		String gsonObject = gson.toJson(objectMap);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(gsonObject);
		out.flush();
		out.close();
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userName = request.getParameter("userName");
	    String userEmail = request.getParameter("userEmail");
	    String jsonStr = "";

	    if (userName!=null) {
            User user = userDao.getUser(userName);
            if(user.getUserName()==null) {
                jsonStr = "false";
            } else {
                jsonStr = "true";
            }
        } else if(userEmail!=null) {
            User user = userDao.getUserByEmail(userEmail);
            if(user.getEmailAddress()==null) {
                jsonStr = "false";
            } else {
                jsonStr = "true";
            }
        }

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }
}
