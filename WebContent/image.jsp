<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<link rel="stylesheet" type="text/css" href="css/ajaxfileupload.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<!--  script type="text/javascript" src="js/ajaxfileupload.js"></script -->
<script type="text/javascript">
	function ajaxUpload() {
		var title = $("#title").val();
		console.log("param-----" + title);
		var descr = $("#picture_descr").val();
		var file = $("#file_image").val();
		if (file == null || file == "") {
			alert("请选择照片");
			return false;
		} else {
			var param = "title=" + title + "&desc=" + descr + "&type=1";
			$.ajaxFileUpload({
				url : "/CMS/image?" + param,
				type : 'post',
				secureuri : false,
				fileElementId : 'file_image',
				dataType : 'json',
				success : function(data, status) {
					if (status == "success") {
						alert("照片上传成功。");
						window.open("images.jsp","_self");
					} else {
						alert("照片上传失败。");
					}
				}
			});
		}
	}
</script>
</head>

<body style="margin-left: 10px;">
	<textarea id="goods_id" style="display: none"><%= request.getParameter("id") %></textarea>
	<br />
	<br />
	<form id="form_upload" name="form_upload" action="/CMS/image" method="post" enctype="multipart/form-data">  
		<div id="div_upload">
			<!-- label>标题:</label>
			<   input type="text" id="title" name="title" -->
			<input type="hidden" id="type" name="type" value='1' />
			<p>
				<strong>图片路径:</strong> <input type="file" id="file_image"
					name="path" accept="image/*" style="width: 350px" onchange="">
			<p>
				<!-- label>图片描述:</label>
			<p>
				<textarea id="picture_descr" name="description"
					style="width: 400px; height: 50px" rows="6" cols=""></textarea -->
			<p>
				<input type="submit" id="tmp_upload_button" name="tm_upload" value="上传"/>
		</div>
	</form>
</body>
</html>
