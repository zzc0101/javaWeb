package com.zzc.excise.controller;

import com.google.gson.Gson;
import com.zzc.excise.dao.ProvinceCiteDao;
import com.zzc.excise.vo.City;
import com.zzc.excise.vo.Province;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ${NAME}
 * @Author: zzc
 * @CreateTime: 2020/10/17 13:46
 * @Description: 查询省份和城市
 */

@WebServlet("/queryProvinceCity.do")
public class QueryProvinceCityController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String provinceCode = request.getParameter("provinceCode");
        String jsonStr = "";
        ProvinceCiteDao provinceCiteDao = new ProvinceCiteDao();
        if(provinceCode == null) {
            List<Province> list = provinceCiteDao.queryProvince();
            jsonStr = new Gson().toJson(list);
        } else {
            List<City> list = provinceCiteDao.queryCity(Integer.parseInt(provinceCode));
            jsonStr = new Gson().toJson(list);
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
