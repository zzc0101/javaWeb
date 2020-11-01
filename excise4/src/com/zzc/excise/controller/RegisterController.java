package com.zzc.excise.controller;

import com.google.gson.Gson;
import com.zzc.excise.dao.RegisterDao;
import com.zzc.excise.tools.MD5Util;
import com.zzc.excise.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: zzc
 * @CreateTime: 2020/10/17 19:03
 * @Description: 注册表控制器，将注册信息添加到数据库中
 */

@WebServlet("/Register.do")
public class RegisterController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        User user = new User();
        user.setUserName(request.getParameter("userName"));
        user.setPassword(MD5Util.md5(request.getParameter("userPassword")));
        user.setChrName(request.getParameter("chrName"));
        user.setEmailAddress(request.getParameter("emailAddress"));
        user.setP_id(Integer.parseInt(request.getParameter("provinceCode")));
        user.setC_id(Integer.parseInt(request.getParameter("cityCode")));

        System.out.println(user);

        RegisterDao registerDao = new RegisterDao();
        int update = registerDao.addUser(user);

        String jsonStr = "success";
        if(update == 0) {
            jsonStr = "注册失败！";
        }

        jsonStr = new Gson().toJson(jsonStr);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
