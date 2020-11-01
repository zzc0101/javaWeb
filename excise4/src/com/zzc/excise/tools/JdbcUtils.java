
package com.zzc.excise.tools;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
/**
 * @ClassName: JdbcUtils
 * @Author: zzc
 * @CreateTime: 2020/10/14 13:42
 * @Description: 数据库连接工具类
 */
public class JdbcUtils {
	private static String DRIVER;
	private static String URL;
	private static String NAME;
	private static String PWD;
	
	private static Connection connection;
	private static PreparedStatement ps;
	private static ResultSet rs;
	
	static {
		try {
			Loading_Properties();
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("驱动程序加载失败！！");
			e.printStackTrace();
		}
	}

	// 加载配置文件
	private static void Loading_Properties() {
		try {
			Properties properties = new Properties();
			InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("config/jdbc.properties");
			properties.load(in);
			DRIVER = properties.getProperty("jdbc.drive");
			URL = properties.getProperty("jdbc.url");
			NAME = properties.getProperty("jdbc.name");
			PWD = properties.getProperty("jdbc.pwd");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("加载配置文件！！");
		}
	}

	//数据库连接
	public static Connection getConnection() {
		try {
			connection = DriverManager.getConnection(URL,NAME,PWD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("数据库连接失败！！！");
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void execute(String sql , Object...obj) throws SQLException {
		ps = getConnection().prepareStatement(sql);
		for(int i=0;i<obj.length;i++) {
			ps.setObject(i+1, obj[i]);
		}
	}
	
	//查询当前用户
	public static ResultSet query(String sql, Object...obj) throws SQLException {
		execute(sql, obj);
		rs = ps.executeQuery();
		return rs;
	}

	// 数据更新
	public static int dataUpdate(String sql, Object...obj) throws SQLException {
		execute(sql,obj);
		int i = ps.executeUpdate();
		close();
		return i;
	}
	
	//数据库关闭
	public static void close() throws SQLException {
		if(rs!=null) {
			rs.close();
		}
		if(ps!=null) {
			ps.close();
		}
		if(connection!=null) {
			connection.close();
		}
	}
}
