<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link href="css/cms.css" media="all" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/yx_rotaion.css" />

<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/index.js" type="text/javascript"></script>


<title>内容管理系统</title>
</head>
<body>

	<%@ include file="header.jsp"%>

	<div class="content">

		<%@ include file="nav.jsp"%>

		<div class="index_main mt20">
			<div class="first">
				<div class="first_right">
					<h1 class="l_title">
						<span class="bigmore"> <a href="/CMS/news.jsp" target="_blank">更多&gt;&gt;</a>
						</span>行业新闻
					</h1>
					<ul id="left_news_ul">
					</ul>
				</div>
				<div class="first_mid h350">
					<div class="banner">
						<div class="yx-rotaion">
							<ul class="rotaion_list">
								<li><a href="/CMS/"><img src="images/1.jpg"
										alt="美丽景色让人痴迷" width='689px'> </a></li>
								<li><a href="/CMS/"><img src="images/2.jpg"
										alt="岁月如何生活如花" width='689px'> </a></li>
								<li><a href="/CMS/"><img src="images/3.jpg"
										alt='景色优美真的好看' width='689px'> </a></li>
								<li><a href="/CMS/"><img src="images/4.jpg"
										alt="如果那么然后就是" width='689px'> </a></li>
							</ul>
						</div>
					</div>

				</div>


			</div>

			<div class="jianju_x"></div>

			<div class="jianju_x"></div>
			<div class="fouth">
				<h1 class="l_title">友情链接</h1>
				<p id="links"></p>
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>

	<script src="js/jquery.yx_rotaion.js" type="text/javascript"></script>
	<script type="text/javascript">
		//$(document).load(function() {
		$(".yx-rotaion").yx_rotaion({
			auto : true
		});
		//});
	</script>
</body>
</html>