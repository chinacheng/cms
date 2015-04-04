package com.cms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cms.bean.ArticleBean;
import com.cms.dao.ArticlesDao;
import com.cms.json.ErrorJson;
import com.cms.json.JsonHelper;
import com.cms.json.SuccessJson;
import com.cms.utils.DBConnection;

/**
 * 文章增、删、改
 */
public class ArticlesOprateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();

		Connection conn = null;
		// 类型，增1删改查
		String type = request.getParameter("type");
		// id
		String id = request.getParameter("id");
		// 标题
		String title = request.getParameter("title");
		// 简介
		String introducton = request.getParameter("intro");
		// 作者
		String author = request.getParameter("author");
		// 内容
		String content = request.getParameter("content");
		// 发布时间
		// String published = request.getParameter("published");
		// 是否发布
		String isValid = request.getParameter("isvalid");

		// callback
		String callback = request.getParameter("callback");
		try {
			conn = DBConnection.openConnection();
			// 连接成功
			if (conn != null) {
				ArticlesDao dao = new ArticlesDao(conn);
				ArticleBean bean = new ArticleBean();
				bean.setTitle(title);
				bean.setIntroduction(introducton);
				bean.setAuthor(author);
				bean.setContent(content);
				bean.setUpdated(System.currentTimeMillis());

				// 增加
				if (type.equals("1")) {
					bean.setIsValid(Integer.parseInt(isValid));
					bean.setPublished(bean.getUpdated());
					if (dao.saveArticle(bean))
						out.write(SuccessJson.getSuccessJson(callback));
					else
						out.write(ErrorJson.getErrorJsonObject(300, callback));
					// 修改
				} else if (type.equals("2")) {
					bean.setId(Integer.parseInt(id));
					bean.setPublished(System.currentTimeMillis());
					bean.setIsValid(Integer.parseInt(isValid));
					dao.updateArticle(bean);
					out.write(SuccessJson.getSuccessJson(callback));
					// 删除
				} else if (type.equals("3")) {
					dao.deleteArticle(Integer.parseInt(id));
					out.write(SuccessJson.getSuccessJson(callback));
					// 根据Id查询
				} else if (type.equals(4 + "")) {
					List<ArticleBean> list = new ArrayList<ArticleBean>();
					list = dao.findArticleById(Integer.parseInt(id));
					out.write(JsonHelper.getArticlesJson(list, callback));
				}
				conn.close();
				
				request.getRequestDispatcher("messages.jsp").forward(request,response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// 连接数据库出错
			try {
				conn.close();
			} catch (SQLException e1) {

			}
			out.write(ErrorJson.getErrorJsonObject(400, callback).toString());
		}
	}
}
