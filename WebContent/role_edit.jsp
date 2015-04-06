<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<%@ page import="com.cms.dao.RolesDao"%>
<%@ page import="com.cms.bean.RoleBean"%>

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
		var description = $("#description").val();

		var param = "id=" + id + "&name=" + name + "&description=" + description + "&type=2";
		if (name == "" || description == "") {
			alert("名称/描述不能为空，请填写");
			return;
		}
		$.ajax({
			url : "/CMS/role",
			type : 'post',
			dataType : 'jsonp',
			data : param,
			jsonp : 'callback',
			success : function(result) {
				if (result.result == 200) {
					// 返回正确结果
					alert("修改成功");
					window.open("roles.jsp", "_self");
				} else {
					alert("更新出错，请稍后重试");
				}
			}
		});
	}
</script>
</head>
<%@ page import="com.cms.dao.RolesDao"%>
<%@ page import="com.cms.bean.RoleBean"%>
<%
    String id = request.getParameter("id");
    Connection uconn = DBConnection.openConnection();
    RolesDao udao = new RolesDao(uconn);
    RoleBean bean = udao.getRole(id);
    uconn.close();
%>


<body style="margin-left: 20px">
	<form>
		<input type="hidden" name='type' value="2"></input> <br /> 
		<input type="text" id="id" style="visibility: hidden;" value="<%=bean.getId()%>"></input>
		<p>名称：</p>
		<input type="text" id="name" value="<%=bean.getName()%>"></input> <br />
		<p>描述：</p>
		<input type="text" id="description" value="<%=bean.getDescription()%>"></input>
         <br /> <br /> <input type="button" value="提交"
			onclick="upload_data();" /> <input type="button" id="cancel"
			value="取消" onclick="window.open('roles.jsp','_self');" />
	</form>
</body>
</html>
