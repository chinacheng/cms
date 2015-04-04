package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cms.bean.UserBean;
import com.cms.utils.Base64;

/**
 * 用户操作层
 * 
 * @author 
 * 
 */
public class UsersDao {
	private Connection conn = null;
	// 表名
	private final String TABLE_NAME = "users";

	public UsersDao(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 获取链接列表，不分页
	 * @return
	 */
	public List<UserBean> getUserList() {

		List<UserBean> list = new ArrayList<UserBean>();
		UserBean bean = null;
		String sql = "select * from " + TABLE_NAME;
		PreparedStatement pps = null;
		ResultSet res = null;
		try {
			pps = conn.prepareStatement(sql);
			res = pps.executeQuery();
			while (res.next()) {
				bean = new UserBean();
				bean.setId(res.getInt("id"));
				bean.setName(res.getString("name"));
				bean.setPwd(res.getString("pwd"));
				bean.setSex(res.getString("sex"));
				bean.setEmail(res.getString("email"));
				bean.setMobile(res.getString("mobile"));
				bean.setAddress(res.getString("address"));
				bean.setRole_id(res.getInt("role_id"));
				list.add(bean);
			}
			res.close();
			pps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				res.close();
				pps.close();
				return null;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 保存链接信息
	 * 
	 * @param bean
	 * @return
	 */
	public boolean saveUser(UserBean bean) {
		String sql = "insert into "
				+ TABLE_NAME
				+ " (name, pwd, sex, email, mobile, address, role_id) values(?,?,?,?,?,?,?)";
		PreparedStatement pps = null;
		try {
			pps = conn.prepareStatement(sql);
			pps.setString(1, bean.getName());
			pps.setString(2, Base64.getBase64(bean.getPwd()));
			pps.setString(3, bean.getSex());
			pps.setString(4, bean.getEmail());
			pps.setString(5, bean.getMobile());
			pps.setString(6, bean.getAddress());
			pps.setInt(7, bean.getRole_id());
			pps.executeUpdate();
			pps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				pps.close();
				return false;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return true;
	}


	/**
	 * 删除链接
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteUser(int id) {
		String sql = "delete from " + TABLE_NAME + " where id = ?";
		PreparedStatement pps = null;

		try {
			pps = conn.prepareStatement(sql);
			pps.setInt(1, id);

			pps.executeUpdate();
			pps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				pps.close();
				return false;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return true;
	}
	
}
