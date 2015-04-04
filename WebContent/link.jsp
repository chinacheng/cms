<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<title>文章列表</title>

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
		var name = $("#name").val();
		var url = $("#url").val();

		var param = "name=" + name + "&url=" + url + "&type=1";
		if (name == "" || url == "") {
			alert("名称/链接不能为空，请填写");
			return;
		}
		if(!checkUrl(url)){
			alert("链接不合法，请填写完整链接");
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
	
	function checkUrl(url){
      var strRegex = "^(https|http|ftp|rtsp|mms):\/\/(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))+(\\?\\S*)?$";

      var re=new RegExp(strRegex);

      if (re.exec(url)){

          return true;

      }else{

          return false;

      }

  }
</script>
</head>

<body style="margin: 20px">
	<form>
		<input type="text" id="id" style="visibility: hidden;"></input>
		<p>名称：</p>
		<input type="text" id="name"></input> <br />
		<p>链接：</p>
		<textarea id="url" name="url" style="width: 400px; height: 50px"
			rows="2" cols=""></textarea>
		<br /> <input type="button" value="提交" onclick="upload_data();" /> <input
			type="button" id="cancel" value="取消"
			onclick="window.open('links.jsp','_self');" />
	</form>
</body>
</html>
