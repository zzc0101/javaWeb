package com.zzc.excise.dao;

import com.zzc.excise.tools.JdbcUtils;
import com.zzc.excise.vo.Download;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DownloadDao
 * @Author: zzc
 * @CreateTime: 2020/10/17 14:11
 * @Description: 获取数据库中可下载的资源信息
 */
public class DownloadDao {

	// 获取到所有可以下载的所有信息
	public List<Download> getDownload() {
		List<Download> list = new ArrayList<Download>();
		ResultSet resultSet;
		try {
			resultSet = JdbcUtils.query("select * from t_downloadlist");
			while(resultSet.next()) {
				Download download = new Download();
				download.setId(resultSet.getInt(1));
				download.setName(resultSet.getString(2));
				download.setPath(resultSet.getString(3));
				download.setDescription(resultSet.getString(4));
				download.setSize(resultSet.getLong(5));
				download.setStar(resultSet.getInt(6));
				download.setImage(resultSet.getString(7));
				list.add(download);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("查询下载列表失败！");
			e.printStackTrace();
		}
		return list;
	}
	
}