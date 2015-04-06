<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/cms.css" media="all" rel="stylesheet" type="text/css" />
<title>企业风景</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%@ page language="java" import="com.cms.utils.DBConnection"%>
	<%@ page language="java" import="java.sql.Connection"%>
	<%@ page language="java" import="com.cms.dao.ImagesDao"%>
	<%@ page language="java" import="com.cms.bean.ImageBean"%>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="java.util.List"%>

	<%
	    Connection conn = DBConnection.openConnection();
	    ImagesDao dao = new ImagesDao(conn);
	    List<ImageBean> list = new ArrayList<ImageBean>();
	    list = dao.getImageList();
	    conn.close();
	%>
	<div class="content">

		<%@ include file="nav.jsp"%>

		<div class="container pl10">
			<MARQUEE width=990 height=500 onload=start()
				scrollAmount=10 loop=infinite deplay="0">
				<%
				    for (int i = 0; i < list.size(); i++) {
				%>
				<%
				    ImageBean bean = (ImageBean) list.get(i);
				%>
				<img src="/CMS/<%=bean.getPath()%>" height='300px'>
				<%
				    }
				%>

			</MARQUEE>
		</div>

	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>