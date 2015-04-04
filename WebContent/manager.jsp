<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>内容管理系统CMS后台管理</title>
<link href="css/manager.css" media="all" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<div class='back_main'>
		<div class='header'>
			<span class='logo'>内容管理系统CMS后台管理</span>
			
			<span style="padding-left: 500px;color: #FFF;font-size:12px">
			当前用户：管理员
			<a href='/CMS/' style="padding-left: 20px;color: #FFF;text-decoration:none;">返回首页</a>
			</span>
		</div>

		<div class='main_content'>
			<div class="left">
				<div class="des">功能列表</div>
				<ul class='functions'>
					<li class='function'><a href="articles.jsp" target="mainFrame">文章管理</a></li>
					<li class='function'><a href="images.jsp" target="mainFrame">图片管理</a></li>
					<li class='function'><a href="links.jsp" target="mainFrame">友情链接</a></li>
					<li class='function'><a href="admin_messages.jsp" target="mainFrame">留言管理</a></li>
				</ul>
			</div>

			<div class="right">
				<iframe src="articles.jsp" name="mainFrame" frameborder="0"
					marginheight="0" marginwidth="0" height="700" width="100%">
				</iframe>
			</div>

			<div class='clear'></div>
		</div>

	</div>
</body>
</html>