package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cms.bean.ArticleBean;

/**
 * 关于文章的操作层
 * 
 * @author 302
 * 
 */
public class ArticlesDao {
	private Connection conn = null;
	// 表名
	private final String TABLE_NAME = "articles";

	public ArticlesDao(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 分页获取文章列表
	 * 
	 * @param startPage
	 * @param PageSize
	 * @param isValid
	 *            文章是否有效，默认为null
	 * @return
	 */
	public List<ArticleBean> getArticleList(int startPage, int pageSize,
			String isValid) {

		List<ArticleBean> list = new ArrayList<ArticleBean>();
		ArticleBean bean = null;
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
				bean = new ArticleBean();
				bean.setId(res.getInt("id"));
				bean.setTitle(res.getString("title"));
				bean.setIntroduction(res.getString("introduction"));
				bean.setAuthor(res.getString("author"));
				bean.setContent(res.getString("content"));
				bean.setPublished(res.getLong("published_time"));
				bean.setIsValid(res.getInt("isvalid"));
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
     * 根据ID获取文章
     * @param id
     * @return
     */	
	public ArticleBean getArticle(String id) {

        ArticleBean bean = null;
        String sql = "select * from " + TABLE_NAME + " where id = " + id;
        sql += ";";
        PreparedStatement pps = null;
        ResultSet res = null;
        try {
            pps = conn.prepareStatement(sql);
            res = pps.executeQuery();
            while (res.next()) {
                bean = new ArticleBean();
                bean.setId(res.getInt("id"));
                bean.setTitle(res.getString("title"));
                bean.setIntroduction(res.getString("introduction"));
                bean.setAuthor(res.getString("author"));
                bean.setContent(res.getString("content"));
                bean.setPublished(res.getLong("published_time"));
                bean.setIsValid(res.getInt("isvalid"));
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
	 * 保存文章内容
	 * 
	 * @param bean
	 * @return
	 */
	public boolean saveArticle(ArticleBean bean) {
		String sql = "insert into "
				+ TABLE_NAME
				+ " (title,introduction,author,content,published_time,updated_time,isvalid) values(?,?,?,?,?,?,?)";
		PreparedStatement pps = null;
		System.out.println(bean.getId() + ","+ bean.getTitle() + "," + bean.getIntroduction() + "," 
				+ bean.getAuthor() + "," + bean.getContent() + "," + bean.getIsValid() + ","
				+ bean.getPublished());
		try {
			pps = conn.prepareStatement(sql);
			pps.setString(1, bean.getTitle());
			pps.setString(2, bean.getIntroduction());
			pps.setString(3, bean.getAuthor());
			pps.setString(4, bean.getContent());
			pps.setLong(5, bean.getPublished());
			pps.setLong(6, bean.getPublished());
			pps.setInt(7, bean.getIsValid());

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
	 * 更新文章内容
	 * 
	 * @param bean
	 * @return
	 */
	public boolean updateArticle(ArticleBean bean) {
		String sql = "update "
				+ TABLE_NAME
				+ " set title=? , introduction = ? , author = ? , content = ? , updated_time = ? , isvalid = ? where id = ?";
		PreparedStatement pps = null;
//		System.out.println(bean.getId() + ","+ bean.getTitle() + "," + bean.getIntroduction() + "," + bean.getAuthor() + "," + bean.getContent() + "," + bean.getIsValid());

		try {
			pps = conn.prepareStatement(sql);
			pps.setString(1, bean.getTitle());
			pps.setString(2, bean.getIntroduction());
			pps.setString(3, bean.getAuthor());
			pps.setString(4, bean.getContent());
			pps.setLong(5, bean.getPublished());
			pps.setInt(6, bean.getIsValid());
			pps.setInt(7, bean.getId());

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
	 * 删除文章
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteArticle(int id) {
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
	 * 更新文章内容
	 * 
	 * @param bean
	 * @return
	 */
	public List<ArticleBean> findArticleById(int id) {
		List<ArticleBean> list = new ArrayList<ArticleBean>();
		ArticleBean bean = null;
		String sql = "select * from " + TABLE_NAME + " where id = ?";
		PreparedStatement pps = null;
		ResultSet res = null;
		try {
			pps = conn.prepareStatement(sql);
			pps.setInt(1, id);
			res = pps.executeQuery();
			while (res.next()) {
				bean = new ArticleBean();
				bean.setId(res.getInt("id"));
				bean.setTitle(res.getString("title"));
				bean.setIntroduction(res.getString("introduction"));
				bean.setContent(res.getString("content"));
				bean.setAuthor(res.getString("author"));
				bean.setPublished(res.getLong("published_time"));
				bean.setIsValid(res.getInt("isvalid"));
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
