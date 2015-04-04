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

import com.cms.bean.LinkBean;
import com.cms.dao.LinksDao;
import com.cms.json.ErrorJson;
import com.cms.json.JsonHelper;
import com.cms.utils.DBConnection;

/**
 * 友情链接服务端
 */
public class LinksServlet extends HttpServlet {
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
		String callback = request.getParameter("callback");

		try {
			conn = DBConnection.openConnection();
			// 连接成功
			if (conn != null) {
				LinksDao dao = new LinksDao(conn);
				List<LinkBean> list = new ArrayList<LinkBean>();
				list = dao.getLinkList();
				out.write(JsonHelper.getLinksJson(list, callback));
				conn.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// 连接数据库出错
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {

			}
			out.write(ErrorJson.getErrorJsonObject(400, callback));
		}
	}
}
