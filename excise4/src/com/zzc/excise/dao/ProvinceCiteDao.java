package com.zzc.excise.dao;

import com.zzc.excise.tools.JdbcUtils;
import com.zzc.excise.vo.City;
import com.zzc.excise.vo.Province;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ProvinceCiteDao
 * @Author: zzc
 * @CreateTime: 2020/10/17 14:11
 * @Description: 用于查询省和城市
 */

public class ProvinceCiteDao {

    // 用于查询省份集合
    public List<Province> queryProvince() {
        List<Province> list = new ArrayList<Province>();
        ResultSet resultSet;
        try {
            resultSet = JdbcUtils.query("SELECT * FROM t_province");
            while (resultSet.next()) {
                Province province = new Province();
                province.setP_id(resultSet.getInt(1));
                province.setProvince(resultSet.getString(2));
                list.add(province);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("省份查询失败！");
        }
        return list;
    }

    // 通过省份 id 查询相应的所有城市id
    public List<City> queryCity(int p_id) {
        List<City> list = new ArrayList<City>();
        ResultSet resultSet;
        try {
            resultSet = JdbcUtils.query("SELECT * FROM t_city WHERE p_id=?",p_id);
            while (resultSet.next()) {
                City city = new City();
                city.setC_id(resultSet.getInt(1));
                city.setCity(resultSet.getString(2));
                city.setP_id(resultSet.getInt(3));
                list.add(city);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("省份查询失败！");
        }
        return list;
    }
}
