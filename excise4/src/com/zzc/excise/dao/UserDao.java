package com.zzc.excise.dao;

import com.zzc.excise.tools.JdbcUtils;
import com.zzc.excise.vo.Page;
import com.zzc.excise.vo.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: UserDao
 * @Author: zzc
 * @CreateTime: 2020/10/17 14:11
 * @Description: 用于查询、添加、删除、修改用户在数据库中的相关信息
 */
public class UserDao {

	// 根据用户名查询用户（查询用户名是否存在）
	public User getUser(String name) {
		User user = new User();
		ResultSet resultSet;
		try {
			resultSet = JdbcUtils.query("select * from t_user where userName=?", name);
			while(resultSet.next()) {
				user.setUserName(resultSet.getString(1));
				user.setPassword(resultSet.getString(2));
				user.setChrName(resultSet.getString(3));
				user.setEmailAddress(resultSet.getString(4));
				user.setP_id(resultSet.getInt(5));
				user.setC_id(resultSet.getInt(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("查询用户失败！");
			e.printStackTrace();
		} finally {
			try {
				JdbcUtils.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		return user;
	}

	// 通过邮箱查询 用户(用于效验邮箱是否存在)
	public User getUserByEmail(String email) {
		User user = new User();
		ResultSet resultSet;
		try {
			resultSet = JdbcUtils.query("select * from t_user where emailAddress=?", email);
			while(resultSet.next()) {
				user.setUserName(resultSet.getString(1));
				user.setPassword(resultSet.getString(2));
				user.setChrName(resultSet.getString(3));
				user.setEmailAddress(resultSet.getString(4));
				user.setP_id(resultSet.getInt(5));
				user.setC_id(resultSet.getInt(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("查询用户失败！");
			e.printStackTrace();
		} finally {
			try {
				JdbcUtils.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		return user;
	}

	// 查询所有用户
	public List<User> queryByAll(User user, Page page) {
		List<User> userList = new ArrayList<User>();
		ResultSet resultSet;
		StringBuffer condition = new StringBuffer(""); // 查询条件

		if(user.getUserName() != null && !"".equals(user.getUserName())) {
			condition.append("userName like '%").append(user.getUserName()).append("%'");
		}

		if(user.getChrName() != null && !"".equals(user.getChrName())) {
			if(!condition.toString().equals("")) condition.append(" and ");
			condition.append("chrName like '%").append(user.getChrName()).append("%'");
		}

		if(user.getEmailAddress() != null && !"".equals(user.getEmailAddress())) {
			if(!condition.toString().equals("")) condition.append(" and ");
			condition.append("emailAddress like '%").append(user.getEmailAddress()).append("%'");
		}

		if(user.getProvinceName() != null && !"".equals(user.getProvinceName())) {
			if(!condition.toString().equals("")) condition.append(" and ");
			condition.append("province like '%").append(user.getProvinceName()).append("%'");
		}

		if(user.getCityName() != null && !"".equals(user.getCityName())) {
			if(!condition.toString().equals("")) condition.append(" and ");
			condition.append("city like '%").append(user.getCityName()).append("%'");
		}
		int begin = page.getPageSize() * (page.getPageNumber() - 1);
		String order = page.getSort() +" "+ page.getOrder();
		try {
			// 通过判断 condition 中是否有查询条件进行填充相应的sql。
			if(condition.toString().equals("")) resultSet = JdbcUtils.query("select userName,password,chrName,emailAddress,tp.p_id,province,tc.c_id,city from t_user\n" +
					"    left join t_province tp on tp.p_id = t_user.p_id\n" +
					"    left join t_city tc on tc.c_id = t_user.c_id order by ? limit ?,?",order,begin,page.getPageSize());
			else {
				String sql = "select userName,password,chrName,emailAddress,tp.p_id,province,tc.c_id,city from t_user" +
						"    left join t_province tp on tp.p_id = t_user.p_id" +
						"    left join t_city tc on tc.c_id = t_user.c_id where "+condition.toString()+" order by ? limit ?,?";
				resultSet = JdbcUtils.query(sql,order,begin,page.getPageSize());
			}
			while (resultSet.next()) {
				User userResult = new User();
				userResult.setUserName(resultSet.getString(1));
				userResult.setPassword(resultSet.getString(2));
				userResult.setChrName(resultSet.getString(3));
				userResult.setEmailAddress(resultSet.getString(4));
				userResult.setP_id(resultSet.getInt(5));
				userResult.setProvinceName(resultSet.getString(6));
				userResult.setC_id(resultSet.getInt(7));
				userResult.setCityName(resultSet.getString(8));
				userList.add(userResult);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			System.err.println("查询所有用户信息失败！");
		} finally {
			try {
				JdbcUtils.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		return userList;
	}

	// 查询记录总数
	public int count(User user, Page page) {
		int count = 0;
		ResultSet resultSet;
		StringBuffer condition = new StringBuffer(""); // 查询条件

		if(user.getUserName() != null && !"".equals(user.getUserName())) {
			condition.append("userName like '%").append(user.getUserName()).append("%'");
		}

		if(user.getChrName() != null && !"".equals(user.getChrName())) {
			if(!condition.toString().equals("")) condition.append(" and ");
			condition.append("chrName like '%").append(user.getChrName()).append("%'");
		}

		if(user.getEmailAddress() != null && !"".equals(user.getEmailAddress())) {
			if(!condition.toString().equals("")) condition.append(" and ");
			condition.append("emailAddress like '%").append(user.getEmailAddress()).append("%'");
		}

		if(user.getProvinceName() != null && !"".equals(user.getProvinceName())) {
			if(!condition.toString().equals("")) condition.append(" and ");
			condition.append("province like '%").append(user.getProvinceName()).append("%'");
		}

		if(user.getCityName() != null && !"".equals(user.getCityName())) {
			if(!condition.toString().equals("")) condition.append(" and ");
			condition.append("city like '%").append(user.getCityName()).append("%'");
		}

		String order = page.getSort() +" "+ page.getOrder();

		try {
			// 通过判断 condition 中是否有查询条件进行填充相应的sql。
			if (condition.toString().equals(""))
				resultSet = JdbcUtils.query("select COUNT(userName) from t_user left join t_province tp on tp.p_id = t_user.p_id order by ?", order);
			else {
				String sql = "select COUNT(userName) from t_user left join t_province tp on tp.p_id = t_user.p_id where "+condition.toString()+"order by ?";
				resultSet = JdbcUtils.query(sql,order);
			}
			if(resultSet.next()) count = resultSet.getInt(1);
		}catch (SQLException throwables) {
			throwables.printStackTrace();
			System.err.println("查询总页数失败！");
		} finally {
			try {
				JdbcUtils.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		return count;
	}

	// 删除用户
	public boolean deleteUser(String userName) {
		boolean flag = false;
		try {
			int i = JdbcUtils.dataUpdate("DELETE FROM t_user WHERE userName = ?",userName);
			if(i > 0) flag = true;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			System.err.println("用户删除失败！");
		}
		return flag;
	}

	// 修改用户
	public boolean update(User user,String oldName) {
		boolean flag = false;
		try {
			int update = JdbcUtils.dataUpdate("UPDATE t_user SET userName=?,password=?,chrName=?, emailAddress=?, p_id=?, c_id=? WHERE userName=?",user.getUserName(),user.getPassword(),user.getChrName(),user.getEmailAddress(),user.getP_id(),user.getC_id(),oldName);
			if(update != 0) flag = true;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			System.err.println("用户修改失败！！");
		}
		return  flag;
	}

	// 添加用户
	public boolean insert(User user) {
		boolean flag = false;
		try {
			// 添加用户
			int update = JdbcUtils.dataUpdate("INSERT INTO t_user VALUES (?,?,?,?,?,?)",user.getUserName(),user.getPassword(),user.getChrName(),user.getEmailAddress(),user.getP_id(),user.getC_id());
			// 给用户添加权限
			if(update != 0) {
				update = JdbcUtils.dataUpdate("INSERT INTO t_user_role (roleId,userName) VALUES (?,?)",2,user.getUserName());
				if(update != 0) flag = true;
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			System.err.println("用户添加失败！！");
		}
		return flag;
	}

}
