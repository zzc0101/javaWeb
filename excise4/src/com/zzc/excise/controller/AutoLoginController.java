package com.zzc.excise.controller;

import com.zzc.excise.dao.UserDao;
import com.zzc.excise.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Author: zzc
 * function: 用于效验自动登录时 cookie 保存的账号密码是否正确
 */
@WebServlet("/AutoLogin.do")
public class AutoLoginController extends HttpServlet {
    
    private static UserDao userDao = new UserDao();
    User user;
    private static final String ERROR = "error";
    private static final String MAIN = "main";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");
        String userPassword = (String) session.getAttribute("userPassword");
        String path = "";

        //判断用户是否存在
        user = userDao.getUser(userName);
        if(user.getUserName()==null) {
            System.err.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+":\t该用户不存在！");
            request.setAttribute(ERROR, "抱歉，您输入的用户名不存在!!");
            path = "/error.jsp";
        } else {
            //判断用户密码是否正确
            if (user.getPassword().equals(userPassword)) {
                session.setAttribute(MAIN, user);
                session.removeAttribute("userName");
                session.removeAttribute("userPassword");
                path = "/main.jsp";
            } else {
                System.err.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ":\t用户密码不正确");
                request.setAttribute(ERROR, "抱歉，您输入的密码不正确!!");
                path = "/error.jsp";
            }
        }
        request.getRequestDispatcher(path).forward(request,response);
    }
}
