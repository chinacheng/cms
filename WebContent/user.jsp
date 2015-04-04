<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<%@ page import="com.cms.dao.RolesDao"%>
<%@ page import="com.cms.bean.RoleBean"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page language="java" import="com.cms.utils.DBConnection"%>
<%@ page language="java" import="java.sql.Connection"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>角色列表</title>

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
		var email = $("#email").val();
		var pwd = $("#pwd").val();
		var sex = $("#sex").find("option:selected").val();
		var mobile = $("#mobile").val();
		var address = $("#address").val();
		var role_id = $("#role_id").find("option:selected").val();

		var param = "name=" + name + "&email=" + email + "&mobile=" + mobile
				+ "&pwd=" + pwd + "&sex=" + sex + "&address=" + address
				+ "&role_id=" + role_id + "&type=1";
		if (name == "" || email == "") {
			alert("名称/email不能为空，请填写");
			return;
		}
		$.ajax({
			url : "/CMS/user",
			type : 'post',
			dataType : 'jsonp',
			data : param,
			jsonp : 'callback',
			success : function(result) {
				if (result.result == 200) {
					// 返回正确结果
					alert("修改成功");
					window.open("users.jsp", "_self");
				} else {
					alert("更新出错，请稍后重试");
				}
			}
		});
	}
</script>
</head>

<body style="margin-left: 20px">
	<form>
		<input type="hidden" name='type' value="1"></input> <br /> <input
			type="text" id="id" style="visibility: hidden;"></input>
		<p>登录名：</p>
		<input type="text" id="name"></input> <br />
		<p>密码：</p>
		<input type="password" id="pwd"></input> <br />
		<p>Email：</p>
		<input type="text" id="email"></input> <br />
		<p>性别：</p>
		<select id='sex'>
			<option value="男">男</option>
			<option value="女">女</option>
		</select> <br />
		<p>电话：</p>
		<input type="text" id="mobile"></input> <br />
		<p>地址：</p>
		<input type="text" id="address"></input> <br />
		<p>角色：</p>
		<%
		    Connection conn = DBConnection.openConnection();
		    RolesDao dao = new RolesDao(conn);
		    List<RoleBean> list = new ArrayList<RoleBean>();
		    list = dao.getRoleList();
		    conn.close();
		%>

		<select id='role_id'>
			<%
			    for (int i = 0; i < list.size(); i++) {
			%>
			<%
			    RoleBean mes = (RoleBean) list.get(i);
			%>
			<option value="<%=mes.getId()%>"><%=mes.getName()%></option>

			<%
			    }
			%>
		</select> <br /><br /> <input type="button" value="提交" onclick="upload_data();" />
		<input type="button" id="cancel" value="取消"
			onclick="window.open('users.jsp','_self');" />
	</form>
</body>
</html>
