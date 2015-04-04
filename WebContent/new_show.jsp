<%@ page import="com.cms.dao.ArticlesDao"%>
<%@ page import="com.cms.bean.ArticleBean"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/cms.css" media="all" rel="stylesheet" type="text/css" />
<title>新闻</title>
</head>
<body>
	<%@ page language="java" import="com.cms.utils.DBConnection"%>
	<%@ page language="java" import="java.sql.Connection"%>
	<%
	    Connection conn = DBConnection.openConnection();
	    ArticlesDao dao = new ArticlesDao(conn);
	    ArticleBean article = new ArticleBean();
	    article = dao.getArticle(request.getParameter("id"));
	    conn.close();
	%>

	<%@ include file="header.jsp"%>

	<div class="content">

		<%@ include file="nav.jsp"%>

		<div class="container mt20">
			<div id="details">
				<h1><%=article.getTitle()%></h1>
				<div class="time">
					更新时间  <%=article.getPublishedStringLong()%> 
                                                      作者 <%=article.getAuthor()%>
					</span></span>
				</div>
				
				<div style="padding: 15px;border-bottom: 1px solid #CCC;">
				<b>摘要：</b>
				<%=article.getIntroduction()%>
				</div>

				<div class="content">
					<div class="out_content">
						<%=article.getContent()%>
					</div>
				</div>

			</div>
			
		</div>

	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>