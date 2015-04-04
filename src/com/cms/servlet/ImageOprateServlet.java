package com.cms.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.cms.bean.ImageBean;
import com.cms.dao.ImagesDao;
import com.cms.json.ErrorJson;
import com.cms.json.SuccessJson;
import com.cms.utils.DBConnection;

/**
 * Servlet implementation class UploadPhotoServlet
 */
@WebServlet("/UploadPhotoServlet")
public class ImageOprateServlet extends HttpServlet {
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
		String title = request.getParameter("title");
		String photodesc = request.getParameter("desc");
		String type = request.getParameter("type");
		String callback = request.getParameter("callback");
		System.out.println(title + "," + photodesc);
		Connection conn = DBConnection.openConnection();
		if (conn != null) {
			ImagesDao photoDao = new ImagesDao(conn);
			try {
				if (type.equals("1")) {
					String savePath = "/cms/";
					String absolutePath = "../webapps/root" + savePath;

					final long MAX_SIZE = 3 * 1024 * 1024 * 10;
					final String[] allowedExt = new String[] { "jpg", "jpeg",
							"gif", "png", "bmp", "JPG", "JPEG", "GIF", "PNG",
							"BMP" };
					DiskFileItemFactory dfif = new DiskFileItemFactory();
					dfif.setSizeThreshold(4096);
					File filename = new File(absolutePath);
					if (!filename.exists()) {
						filename.mkdirs();
					}
					dfif.setRepository(filename);
					ServletFileUpload sfu = new ServletFileUpload(dfif);
					sfu.setSizeMax(MAX_SIZE);
					List<?> fileList = null;
					try {
						fileList = sfu.parseRequest(request);
					} catch (FileUploadException e) {
						// Log4j.text2.error(Log4j.getTrace(e));
						if (e instanceof SizeLimitExceededException) {
							// 图片太大
							out.println(ErrorJson.getErrorJsonObject(101,
									callback));
						}
					}
					if (fileList == null || fileList.size() == 0) {
						// 图片为空
						out.write(ErrorJson.getErrorJsonObject(102, callback)
								.toString());
					} else {
						Iterator<?> fileItr = fileList.iterator();
						while (fileItr.hasNext()) {
							FileItem fileItem = null;
							String path = null;
							long size = 0;
							fileItem = (FileItem) fileItr.next();
							if (fileItem == null || fileItem.isFormField()) {
								continue;
							}
							path = fileItem.getName();
							size = fileItem.getSize();
							if ("".equals(path) || size == 0) {
								return;
							}

							String t_name = path.substring(path
									.lastIndexOf("\\") + 1);
							String t_ext = t_name.substring(t_name
									.lastIndexOf(".") + 1);
							int allowFlag = 0;
							int allowedExtCount = allowedExt.length;
							for (; allowFlag < allowedExtCount; allowFlag++) {
								if (allowedExt[allowFlag].equals(t_ext))
									break;
							}
							if (allowFlag == allowedExtCount) {
								return;
							}
							// 获取当前时间
							long now = System.currentTimeMillis();
							String time = String.valueOf(now);
							String u_name = absolutePath + time + "." + t_ext;
							// System.out.println(url + "," + u_name);
							File newFile = new File(u_name);
							fileItem.write(newFile);
							// 保存图片信息
							ImageBean photo = new ImageBean();
							photo.setTitle(title);
							photo.setPath(savePath + time + "." + t_ext);
							photo.setDesc(photodesc);

							if (photoDao.saveImage(photo)) {
								out.write(SuccessJson.getSuccessJsonArray()
										.toString());
							} else {
								out.write(ErrorJson.getErrorJsonArray(301).toString());
							}
						}
					}
					// 删除
				} else if (type.equals("3")) {
					if (photoDao.deleteLink(Integer.parseInt(request
							.getParameter("id")))) {
						out.write(SuccessJson.getSuccessJson(callback)
								.toString());
					} else {
						out.write(ErrorJson.getErrorJsonObject(301, callback)
								.toString());
					}

				}
			} catch (Exception e) {
				// 插入数据库出错
				out.write(ErrorJson.getErrorJsonObject(301, callback)
						.toString());
			} finally {
				DBConnection.closeConnection(conn);
			}

		} else {
			// 链接数据库出错
			out.write(ErrorJson.getErrorJsonObject(300, callback));
		}
	}

}
