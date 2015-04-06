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

<title>用户列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="css/manager.css" media="all" rel="stylesheet"
	type="text/css" />

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					$(function() {

						$
								.ajax({
									url : "/CMS/users",
									type : 'get',
									dataType : 'jsonp',
									data : "",
									jsonp : 'callback',
									success : function(result) {
										if (result.result == 200) {
											// 返回正确结果
											for ( var a = 0; a < result.data.length; a++) {
												var tr = "<tr><td>"
														+ (a + 1)
														+ "</td><td>"
														+ result.data[a].name
														+ "</td><td>"
														+ result.data[a].pwd
														+ "</td><td>"
														+ result.data[a].sex
														+ "</td><td>"
														+ result.data[a].email
														+ "</td><td>"
														+ result.data[a].mobile
														+ "</td><td>"
														+ result.data[a].address
														+ "</td><td>"
														+ result.data[a].role_id
														+ "</td><td><input type='button' value='修改' onclick='editUser("
														+ result.data[a].id
														+ ",3);'/><input type='button' value='删除' onclick='deleteUser("
														+ result.data[a].id
														+ ",3);'/></td></tr>";
												$("#articles").append(tr);
											}
										} else {
											alert("请求数据出错，请稍后重试");
										}
									}
								});
					}));
	// 删除
	function deleteUser(id, type) {
		$.ajax({
			url : "/CMS/user",
			type : 'get',
			dataType : 'jsonp',
			data : "type=" + type + "&id=" + id,
			jsonp : 'callback',
			success : function(result) {
				if (result.result == 200) {
					// 返回正确结果
					alert("删除成功。");
					window.open('users.jsp', '_self');
				} else {
					alert("请求数据出错，请稍后重试");
				}
			}
		});
	}

	function editUser(id, type) {
		window.location.href = "/CMS/user_edit.jsp?id=" + id;
		// window.location.href();
	}
</script>
</head>

<body>
	<div class='operates'>
		<input type="button" value="新建"
			onclick="window.open('user.jsp','_self');" />
	</div>

	<table id="articles">
		<tr>
			<th>序号</th>
			<th>姓名</th>
			<th>密码</th>
			<th>性别</th>
			<th>Email</th>
			<th>电话</th>
			<th>地址</th>
			<th>角色</th>
			<th>操作</th>
		</tr>
	</table>



</body>
</html>
