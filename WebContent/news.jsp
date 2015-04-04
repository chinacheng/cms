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
<title>行业新闻</title>
</head>
<body>
	<%@ page language="java" import="com.cms.utils.DBConnection"%>
	<%@ page language="java" import="java.sql.Connection"%>
	<%
	    Connection conn = DBConnection.openConnection();
	    ArticlesDao dao = new ArticlesDao(conn);
	    List<ArticleBean> list = new ArrayList<ArticleBean>();
	    list = dao.getArticleList(1, 20, null);
	    conn.close();
	%>

	<%@ include file="header.jsp"%>

	<div class="content">

		<%@ include file="nav.jsp"%>

		<div class="container mt20">
			<h3>新闻列表</h3>
			<div>
				<div id="apellation">
					<div class="names">新闻名称</div>
					<div class="times">发布日期</div>
				</div>
				<ul class="news_list table-striped">
					<%
					    for (int i = 0; i < list.size(); i++) {
					%>
					<%
					    ArticleBean article = (ArticleBean) list.get(i);
					%>
					<li>
						<div class="names">
							<a href="/CMS/new_show.jsp?id=<%= article.getId()%>"><%=article.getTitle()%></a>
						</div>
						<div class="times"><%=article.getPublishedString()%></div>
					</li>
					<%
					    }
					%>
				</ul>
			</div>
		</div>

	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>