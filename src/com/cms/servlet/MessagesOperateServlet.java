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

import com.cms.bean.MessageBean;
import com.cms.dao.MessagesDao;
import com.cms.json.ErrorJson;
import com.cms.json.JsonHelper;
import com.cms.json.SuccessJson;
import com.cms.utils.DBConnection;

/**
 * 增、删、改
 */
public class MessagesOperateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doPost(request, response);
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
        
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String desc = request.getParameter("desc");


		try {
			conn = DBConnection.openConnection();
			// 连接成功
			if (conn != null) {
				MessagesDao dao = new MessagesDao(conn);
				MessageBean bean = new MessageBean();
				bean.setName(name);
				bean.setEmail(email);
				bean.setDesc(desc);
				bean.setPublished(System.currentTimeMillis());

				// 增加
				if (type.equals("1")) {
					bean.setPublished(bean.getPublished());
					if (dao.saveMessageBean(bean)){}
						//out.write(SuccessJson.getSuccessJson(callback));
					else{}
						//out.write(ErrorJson.getErrorJsonObject(300, callback));
					// 修改
				} else if (type.equals("2")) {
					bean.setId(Integer.parseInt(id));
					bean.setPublished(System.currentTimeMillis());
					dao.updateMessageBean(bean);
					// out.write(SuccessJson.getSuccessJson(callback));
					// 删除
				} else if (type.equals("3")) {
					dao.deleteMessageBean(Integer.parseInt(id));
					// out.write(SuccessJson.getSuccessJson(callback));
					// 根据Id查询
				} else if (type.equals(4 + "")) {
					// List<MessageBean> list = new ArrayList<MessageBean>();
					// list = dao.findMessageById(Integer.parseInt(id));
					// out.write(JsonHelper.getMessagesJson(list, callback));
				}
				conn.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// 连接数据库出错
			try {
				conn.close();
			} catch (SQLException e1) {

			}
			// out.write(ErrorJson.getErrorJsonObject(400, callback).toString());
		}
	}
}
