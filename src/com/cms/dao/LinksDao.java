package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cms.bean.LinkBean;

/**
 * 友情链接的操作层
 * 
 * @author 302
 * 
 */
public class LinksDao {
	private Connection conn = null;
	// 表名
	private final String TABLE_NAME = "links";

	public LinksDao(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 获取链接列表，不分页
	 * @return
	 */
	public List<LinkBean> getLinkList() {

		List<LinkBean> list = new ArrayList<LinkBean>();
		LinkBean bean = null;
		String sql = "select * from " + TABLE_NAME;
		PreparedStatement pps = null;
		ResultSet res = null;
		try {
			pps = conn.prepareStatement(sql);
			res = pps.executeQuery();
			while (res.next()) {
				bean = new LinkBean();
				bean.setId(res.getInt("id"));
				bean.setName(res.getString("name"));
				bean.setUrl(res.getString("url"));
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
	public boolean saveLink(LinkBean bean) {
		String sql = "insert into "
				+ TABLE_NAME
				+ " (name,url) values(?,?)";
		PreparedStatement pps = null;
		try {
			pps = conn.prepareStatement(sql);
			pps.setString(1, bean.getName());
			pps.setString(2, bean.getUrl());

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
	public boolean deleteLink(int id) {
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
