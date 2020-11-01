package com.zzc.excise.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zzc.excise.dao.UserDao;
import com.zzc.excise.tools.MD5Util;
import com.zzc.excise.vo.Page;
import com.zzc.excise.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Author: zzc
 * function: 用于跳转用户管理界面
 */
@WebServlet("/userManager.do")
public class UserManagerController extends HttpServlet {
    private UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if(state.equals("queryUserByStandard")) {
            // 查询所有用户信息（初始化页面数据）
            queryUserByStandard(request,response);
        } else if(state.equals("deleteUser")) {
            deleteUser(request,response);
        } else if(state.equals("insert")) {
            addUser(request,response);
        } else if(state.equals("update")) {
            updateUser(request,response);
        }
    }

    // 修改用户
    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String queryParams = request.getParameter("queryParams");
        String pwdIsChange = request.getParameter("pwdIsChange");
        Gson gson = new GsonBuilder().serializeNulls().create();
        String oldName = request.getParameter("oldName");
        User user = gson.fromJson(queryParams,User.class);
        if(Boolean.parseBoolean(pwdIsChange)) user.setPassword(MD5Util.md5(user.getPassword()));
        boolean flag = userDao.update(user,oldName);
        Map<String,String> map = new HashMap<String, String>();
        if(flag) {
            map.put("code","0");
            map.put("info","修改成功");
        } else {
            map.put("code","1");
            map.put("info","修改失败");
        }
        String jsonStr = gson.toJson(map);
        //字符串输出
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }

    // 添加用户
    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String queryParams = request.getParameter("queryParams");
        Gson gson = new GsonBuilder().serializeNulls().create();
        User user = gson.fromJson(queryParams,User.class);
        user.setPassword(MD5Util.md5(user.getPassword()));
        boolean flag = userDao.insert(user);
        Map<String,String> map = new HashMap<String, String>();
        if(flag) {
            map.put("code","0");
            map.put("info","添加成功");
        } else {
            map.put("code","1");
            map.put("info","添加失败");
        }
        String jsonStr = gson.toJson(map);
        //字符串输出
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();

    }

    // 删除用户
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String queryParams = request.getParameter("queryParams");
        Gson gson = new GsonBuilder().serializeNulls().create();
        User user = gson.fromJson(queryParams,User.class);
        String[] st = user.getUserName().split(",");
        boolean flag = false;
        System.out.println(Arrays.toString(st));
        for (int i=0;i<st.length;i++) {
            flag = userDao.deleteUser(st[i]);
            if(!flag) break;
        }
        Map<String,String> map = new HashMap<String, String>();
        if(flag) {
            map.put("code","0");
            map.put("info","删除成功");
        } else {
            map.put("code","1");
            map.put("info","删除失败");
        }
        String jsonStr = gson.toJson(map);
        //字符串输出
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }

    // 查询所有用户信息（初始化页面数据）
    private void queryUserByStandard(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String queryParams = request.getParameter("queryParams");
        String pageParams = request.getParameter("pageParams");

        // 将 JSON 字符串参数值转换为 java 对象
        Gson gson = new GsonBuilder().serializeNulls().create();
        Page page = gson.fromJson(pageParams, Page.class);

        User user = new User();
        if(queryParams != null) {
            user = gson.fromJson(queryParams,User.class);
        }
        List<User> rows = userDao.queryByAll(user,page); // 查询
        int total = userDao.count(user,page); // 查询记录总数

        // 转换为 json 数据
        Map<String, Object> mapReturn = new HashMap<String, Object>();
        mapReturn.put("rows",rows);
        mapReturn.put("total",total);
        String jsonStr = gson.toJson(mapReturn);

        //字符串输出
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/main/userManager.jsp").forward(request,response);
    }
}
