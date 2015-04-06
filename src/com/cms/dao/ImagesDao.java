package com.cms.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cms.bean.ImageBean;

/**
 * 图片的操作层
 * 
 * @author 302
 * 
 */
public class ImagesDao {
	private Connection conn = null;
	// 表名
	private final String TABLE_NAME = "image";

	public ImagesDao(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 获取图片列表，不分页
	 * 
	 * @return
	 */
	public List<ImageBean> getImageList() {

		List<ImageBean> list = new ArrayList<ImageBean>();
		ImageBean bean = null;
		String sql = "select * from " + TABLE_NAME;
		PreparedStatement pps = null;
		ResultSet res = null;
		try {
			pps = conn.prepareStatement(sql);
			res = pps.executeQuery();
			while (res.next()) {
				bean = new ImageBean();
				bean.setId(res.getInt("id"));
				bean.setTitle(res.getString("title"));
				bean.setDescription(res.getString("description"));
				bean.setPath(res.getString("path"));
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
	public boolean saveImage(ImageBean bean) {
		String sql = "insert into " + TABLE_NAME
				+ " (title,description,path) values(?,?,?)";
		PreparedStatement pps = null;
		try {
			pps = conn.prepareStatement(sql);
			pps.setString(1, bean.getTitle());
			pps.setString(2, bean.getDescription());
			pps.setString(3, bean.getPath());
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
	 * 删除图片
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteLink(int id) {
		String sql = "delete from " + TABLE_NAME + " where id = ?";
		PreparedStatement pps = null;
		String file = "select path from " + TABLE_NAME + " where id = ?";
		try {
			pps = conn.prepareStatement(file);
			pps.setInt(1, id);

			ResultSet res = pps.executeQuery();
			if (res.next()) {
				if (new File("../webapps/root" + res.getString("path"))
						.delete()) {
					pps = conn.prepareStatement(sql);
					pps.setInt(1, id);
					pps.executeUpdate();
				}

			}
			res.close();
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
