<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>


<%@ page language="java" import="com.cms.utils.DBConnection"%>
<%@ page language="java" import="java.sql.Connection"%>
<%@ page language="java" import="com.cms.dao.ArticlesDao"%>
<%@ page language="java" import="com.cms.bean.ArticleBean"%>

<%
    request.setCharacterEncoding("UTF-8");
			String htmlData = request.getParameter("content1") != null
					? request.getParameter("content1")
					: "";
%>

<%
    String id = request.getParameter("id");
			Connection uconn = DBConnection.openConnection();
			ArticlesDao udao = new ArticlesDao(uconn);
			ArticleBean bean = udao.getArticle(id);
			uconn.close();
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

<script type="text/javascript" src="kindeditor/kindeditor-all.js"></script>
<script type="text/javascript" src="kindeditor/lang/zh_CN.js"></script>
<link rel="stylesheet" type="text/css"
	href="kindeditor/themes/default/default.css">

<script type="text/javascript">
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="content"]', {
			allowFileManager : true,
			afterBlur : function() {
				this.sync();
			}
		});
		K('input[name=getHtml]').click(function(e) {
			alert(editor.html());
		});
		K('input[name=isEmpty]').click(function(e) {
			alert(editor.isEmpty());
		});
		K('input[name=getText]').click(function(e) {
			alert(editor.text());
		});
		K('input[name=selectedHtml]').click(function(e) {
			alert(editor.selectedHtml());
		});
		K('input[name=setHtml]').click(function(e) {
			editor.html('<h3>Hello KindEditor</h3>');
		});
		K('input[name=setText]').click(function(e) {
			editor.text('<h3>Hello KindEditor</h3>');
		});
		K('input[name=insertHtml]').click(function(e) {
			editor.insertHtml('<strong>插入HTML</strong>');
		});
		K('input[name=appendHtml]').click(function(e) {
			editor.appendHtml('<strong>添加HTML</strong>');
		});
		K('input[name=clear]').click(function(e) {
			editor.html('');
		});
	});
</script>


<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	var type = 0;
	var id =
<%=request.getParameter("id")%>
	;
	console.log("id:" + id);
	$(document).ready(
			$(function() {
				if (id == null) {
					type = 1;
					return;
				} else {
					type = 4;
				}
				$.ajax({
					url : "/CMS/article",
					type : 'post',
					dataType : 'jsonp',
					data : "id=" + id + "&type=" + type,
					jsonp : 'callback',
					success : function(result) {
						if (result.result == 200) {
							// 返回正确结果
							$("#title").val(result.data[0].title);
							$("#id").val(result.data[0].id);
							$("#intro").val(result.data[0].intro);
							$("#content").val(result.data[0].content);
							editor.html(result.data[0].content);
							$("#author").val(result.data[0].author);
							$("#title").val(result.data[0].title);
							$("#time").val(
									getSmpFormatDateByLong(
											result.data[0].published, true));
							$("#isvalid").val(result.data[0].isvalid);
						} else {
							alert("请求数据出错，请稍后重试");
						}
					}
				});
			}));
	// 转换long类型为日期类型
	function getSmpFormatDateByLong(l, isFull) {
		return getSmpFormatDate(new Date(l), isFull);
	}
	function getSmpFormatDate(date, isFull) {
		var pattern = "";
		if (isFull == true || isFull == undefined) {
			pattern = "yyyy-MM-dd hh:mm:ss";
		} else {
			pattern = "yyyy-MM-dd";
		}
		return getFormatDate(date, pattern);
	}
	function getFormatDate(date, pattern) {
		if (date == undefined) {
			date = new Date();
		}
		if (pattern == undefined) {
			pattern = "yyyy-MM-dd hh:mm:ss";
		}
		return date.format(pattern);
	}

	//扩展Date的format方法 
	Date.prototype.format = function(format) {
		var o = {
			"M+" : this.getMonth() + 1,
			"d+" : this.getDate(),
			"h+" : this.getHours(),
			"m+" : this.getMinutes(),
			"s+" : this.getSeconds(),
			"q+" : Math.floor((this.getMonth() + 3) / 3),
			"S" : this.getMilliseconds()
		};
		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}
		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
						: ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return format;
	};

	function upload_data() {
		var id = $("#id").val();
		var title = $("#title").val();
		var intro = $("#intro").val();
		var content = $("#content").val();
		var content = $("#content").val();
		var author = $("#author").val();
		var isvalid = $("#isvalid").val();

		if (type == 4) {
			type = 2;
		}
		var param = "id=" + id + "&title=" + title + "&intro=" + intro + "&content="
				+ content + "&author=" + author + "&isvalid=" + isvalid
				+ "&id=" + id + "&type=" + type;
		//if(type != 1){
		//	param += "&id=" + id;
		//}

		if (title == "" || content == "" || author == "") {
			alert("标题/内容/作者不能为空，请填写");
			return;
		}

		$.ajax({
			url : "/CMS/article",
			type : 'post',
			dataType : 'jsonp',
			data : param,
			jsonp : 'callback',
			success : function(result) {
				if (result.result == 200) {
					// 返回正确结果
					alert("修改成功");
					window.open("articles.jsp", "_self");
				} else {
					alert("更新出错，请稍后重试");
				}
			}
		});
	}
</script>
</head>

<body style="margin: 20px">
	<form>
		<table class="common">

			<tbody>
				<tr>
					<th class='bg'>标题：</th>
					<td><input type="hidden" id="id" value='<%=bean.getId()%>'></input>
						<input type="text" id="title" value='<%=bean.getTitle()%>'
						style="width: 500px;"></input></td>
				</tr>

				<tr>
					<th class='bg'>作者：</th>
					<td><input type="text" id="author"
						value='<%=bean.getAuthor()%>' value='管理员'></input></td>
				</tr>
				<tr>
					<th class='bg'>是否发布：</th>
					<td><select id='isvalid'
						style="width: 75px; height: 25px; margin-right: 15px">
							<%
							    if (bean.getIsValid() == 1) {
							%>
							<option value="1" selected='selected'>是</option>
							<option value="0">否</option>
							<%
							    } else {
							%>
                            <option value="1">是</option>
                            <option value="0" selected='selected'>否</option>
							<%
							    }
							%>
					</select></td>
				</tr>
				<tr>
					<th class='bg'>发布时间：</th>
					<td><input type="text" id="time" value='<%=bean.getPublishedString()%>'></input></td>
				</tr>
				<tr>
					<th class='bg'>简介：</th>
					<td><textarea id="intro" name="intro" 
							style="width: 700px; height: 50px" rows="2" cols=""><%=bean.getIntroduction()%></textarea></td>
				</tr>
				<tr>
					<th class='bg'>内容：</th>
					<td><textarea id="content" name="content"
							style="width: 700px; height: 350px" rows="10" cols="" ><%=bean.getContent() %></textarea></td>
				</tr>
			</tbody>
		</table>
		<input type="button" value="提交" onclick="upload_data();" /> <input
			type="button" id="cancel" value="取消"
			onclick="window.open('articles.jsp','_self');" />
	</form>
</body>
</html>
