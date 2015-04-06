<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<%@ page import="com.cms.dao.LinksDao"%>
<%@ page import="com.cms.bean.LinkBean"%>

<%@ page language="java" import="com.cms.utils.DBConnection"%>
<%@ page language="java" import="java.sql.Connection"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function upload_data() {
		var id = $("#id").val();
		var name = $("#name").val();
		var url = $("#url").val();

		var param = "id=" + id + "&name=" + name + "&url=" + url + "&type=2";
		if (name == "" || url == "") {
			alert("名称/链接不能为空，请填写");
			return;
		}
		$.ajax({
			url : "/CMS/link",
			type : 'post',
			dataType : 'jsonp',
			data : param,
			jsonp : 'callback',
			success : function(result) {
				if (result.result == 200) {
					// 返回正确结果
					alert("修改成功");
					window.open("links.jsp", "_self");
				} else {
					alert("更新出错，请稍后重试");
				}
			}
		});
	}
</script>
</head>
<%@ page import="com.cms.dao.LinksDao"%>
<%@ page import="com.cms.bean.LinkBean"%>
<%
    String id = request.getParameter("id");
    Connection uconn = DBConnection.openConnection();
    LinksDao udao = new LinksDao(uconn);
    LinkBean bean = udao.getLink(id);
    uconn.close();
%>


<body style="margin-left: 20px">
	<form>
		<input type="hidden" name='type' value="2"></input> <br /> 
		<input type="text" id="id" style="visibility: hidden;" value="<%=bean.getId()%>"></input>
		<p>名称：</p>
		<input type="text" id="name" value="<%=bean.getName()%>"></input> <br />
		<p>描述：</p>
		<input type="text" id="url" value="<%=bean.getUrl()%>"></input>
         <br /> <br /> <input type="button" value="提交"
			onclick="upload_data();" /> <input type="button" id="cancel"
			value="取消" onclick="window.open('links.jsp','_self');" />
	</form>
</body>
</html>
