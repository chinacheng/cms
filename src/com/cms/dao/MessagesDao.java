package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cms.bean.MessageBean;

/**
 * 留言的操作层
 * 
 * @author 302
 * 
 */
public class MessagesDao {
	private Connection conn = null;
	// 表名
	private final String TABLE_NAME = "messages";

	public MessagesDao(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 分页获取留言列表
	 * 
	 * @param startPage
	 * @param PageSize
	 * @param isValid
	 *            留言是否有效，默认为null
	 * @return
	 */
	public List<MessageBean> getMessageBeanList(int startPage, int pageSize,
			String isValid) {

		List<MessageBean> list = new ArrayList<MessageBean>();
		MessageBean bean = null;
		String sql = "select * from " + TABLE_NAME;
		if (isValid != null) {
			sql += " where isvalid = " + isValid;
		}
		sql += " limit ?,?";
		PreparedStatement pps = null;
		ResultSet res = null;
		try {
			pps = conn.prepareStatement(sql);
			pps.setInt(1, startPage);
			pps.setInt(2, pageSize);
			res = pps.executeQuery();
			while (res.next()) {
				bean = new MessageBean();
				bean.setId(res.getInt("id"));
				bean.setName(res.getString("name"));
				bean.setContent(res.getString("content"));
				bean.setEmail(res.getString("email"));
				bean.setPublished(res.getLong("published"));
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
     * 根据ID获取留言
     * @param id
     * @return
     */	
	public MessageBean getMessageBean(String id) {

        MessageBean bean = null;
        String sql = "select * from " + TABLE_NAME + " where id = " + id;
        sql += ";";
        PreparedStatement pps = null;
        ResultSet res = null;
        try {
            pps = conn.prepareStatement(sql);
            res = pps.executeQuery();
            while (res.next()) {
                bean = new MessageBean();
                bean.setId(res.getInt("id"));
                bean.setName(res.getString("name"));
                bean.setContent(res.getString("content"));
                bean.setEmail(res.getString("email"));
                bean.setPublished(res.getLong("published"));
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
	 * 保存留言内容
	 * 
	 * @param bean
	 * @return
	 */
	public boolean saveMessageBean(MessageBean bean) {
	    System.out.println("-===================================----");
		String sql = "insert into "
				+ TABLE_NAME
				+ " (name,email,content,published) values(?,?,?,?)";
		PreparedStatement pps = null;
		System.out.println(bean.getId() + ","+ bean.getName() + "," + bean.getEmail() + "," 
				+ bean.getContent() + "," + bean.getPublished());
		try {
			pps = conn.prepareStatement(sql);
			System.out.println(sql);
			pps.setString(1, bean.getName());
			pps.setString(2, bean.getEmail());
			pps.setString(3, bean.getContent());
		    pps.setLong(4, bean.getPublished());
		    
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
	 * 更新留言内容
	 * 
	 * @param bean
	 * @return
	 */
	public boolean updateMessageBean(MessageBean bean) {
		String sql = "update "
				+ TABLE_NAME
				+ " set name=? , eamil = ? , content = ? , published = ? ";
		PreparedStatement pps = null;
//		System.out.println(bean.getId() + ","+ bean.getTitle() + "," + bean.getIntroduction() + "," + bean.getAuthor() + "," + bean.getContent() + "," + bean.getIsValid());

		try {
			pps = conn.prepareStatement(sql);
			pps.setString(1, bean.getName());
			pps.setString(2, bean.getEmail());
			pps.setString(3, bean.getContent());
			pps.setLong(4, bean.getPublished());

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
	 * 删除留言
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteMessageBean(int id) {
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
	
	/**
	 * 更新留言列表
	 * 
	 * @param bean
	 * @return
	 */
	public List<MessageBean> findMessageBeanById(int id) {
		List<MessageBean> list = new ArrayList<MessageBean>();
		MessageBean bean = null;
		String sql = "select * from " + TABLE_NAME + " where id = ?";
		PreparedStatement pps = null;
		ResultSet res = null;
		try {
			pps = conn.prepareStatement(sql);
			pps.setInt(1, id);
			res = pps.executeQuery();
			while (res.next()) {
				bean = new MessageBean();
				bean.setId(res.getInt("id"));
				bean.setName(res.getString("title"));
				bean.setEmail(res.getString("introduction"));
				bean.setContent(res.getString("content"));
				bean.setPublished(res.getLong("published"));
				list.add(bean);
			}
			res.close();
			pps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				pps.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return list;
	}
	
}
