package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cms.bean.RoleBean;

/**
 * 角色的操作层
 * 
 * @author 
 * 
 */
public class RolesDao {
	private Connection conn = null;
	// 表名
	private final String TABLE_NAME = "roles";

	public RolesDao(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 获取角色列表，不分页
	 * @return
	 */
	public List<RoleBean> getRoleList() {

		List<RoleBean> list = new ArrayList<RoleBean>();
		RoleBean bean = null;
		String sql = "select * from " + TABLE_NAME;
		PreparedStatement pps = null;
		ResultSet res = null;
		try {
			pps = conn.prepareStatement(sql);
			res = pps.executeQuery();
			while (res.next()) {
				bean = new RoleBean();
				bean.setId(res.getInt("id"));
				bean.setName(res.getString("name"));
				bean.setDescription(res.getString("description"));
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
     * 获取一个角色
     * @return
     */
    public RoleBean getRole(String id) {

        RoleBean bean = null;
        String sql = "select * from " + TABLE_NAME + " where id = " + id;
        sql += ";";
        PreparedStatement pps = null;
        ResultSet res = null;
        try {
            pps = conn.prepareStatement(sql);
            res = pps.executeQuery();
            while (res.next()) {
                bean = new RoleBean();
                bean.setId(res.getInt("id"));
                bean.setName(res.getString("name"));
                bean.setDescription(res.getString("description"));
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
        return bean;
    }

	/**
	 * 保存角色信息
	 * 
	 * @param bean
	 * @return
	 */
	public boolean saveRole(RoleBean bean) {
		String sql = "insert into "
				+ TABLE_NAME
				+ " (name,description) values(?,?)";
		PreparedStatement pps = null;
		try {
			pps = conn.prepareStatement(sql);
			pps.setString(1, bean.getName());
			pps.setString(2, bean.getDescription());
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
     * 修改角色信息
     * 
     * @param bean
     * @return
     */
    public boolean updateRole(RoleBean bean) {
        String sql = "update "
                + TABLE_NAME
                + " set name = ?, description =? where id = ?";
        PreparedStatement pps = null;
        try {
            pps = conn.prepareStatement(sql);
            pps.setString(1, bean.getName());
            pps.setString(2, bean.getDescription());
            pps.setInt(3, bean.getId());
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
	 * 删除角色
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteRole(int id) {
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
