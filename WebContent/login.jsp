<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
body,p,h1,h2,h3,h4,h5,h6,dd,dl,dt,th,td,ul,li,ol,form,input,select,textarea,button,pre,legend
	{
	margin: 0;
	padding: 0
}

button,input,select,textarea {
	font: 12px Arial, Helvetica, sans-serif;
	vertical-align: middle;
	outline: none
}

h1,h2,h3,h4,h5,h6 {
	font-size: 100%
}

table {
	border-collapse: collapse;
	border-spacing: 0
}

img,fieldset {
	border: 0
}

address,cite,dfn,em,var,i {
	font-style: normal
}

ul,ol,li {
	list-style-type: none
}

sup {
	vertical-align: text-top
}

sub {
	vertical-align: text-bottom
}

.jianju_x {
	clear: both;
	height: 10px
}

.mt6 {
	margin-top: 6px
}

.mt8 {
	margin-top: 8px
}

.mt10 {
	margin-top: 10px
}

.mt12 {
	margin-top: 12px
}

.mt20 {
	margin-top: 20px
}

.mb8 {
	margin-bottom: 8px
}

.mb10 {
	margin-bottom: 10px
}

.ml8 {
	margin-left: 8px;
	display: inline
}

.mr8 {
	margin-left: 8px;
	display: inline
}

.pt10 {
	padding-top: 10px
}

.pt15 {
	padding-top: 15px
}

.pb10 {
	padding-bottom: 10px
}

.pl10 {
	padding-left: 10px
}

.pr10 {
	padding-right: 10px
}

.f14 {
	font-size: 14px
}

.f16 {
	font-size: 16px
}

.f18 {
	font-size: 18px
}

.b {
	font-weight: bold
}

.pink {
	color: #FF007E !important
}

.gary {
	color: #666 !important
}

.fl {
	float: left
}

.fr {
	float: right
}

.clear {
	clear: both
}

.clearfix:after {
	clear: both;
	content: '';
	display: block;
	font-size: 0;
	line-height: 0;
	visibility: hidden;
	width: 0;
	height: 0
}

.h32 {
	height: 32px
}

.h60 {
	height: 60px
}

.lh60 {
	line-height: 60px
}

.lh80 {
	line-height: 80px
}

.claretred {
	color: #e01800
}

.w158 {
	width: 158px
}

.ml50 {
	margin-left: 130px
}

.goodsordermain .blueness {
	color: #005EA7
}

.w80 {
	width: 80px
}

.w86 {
	width: 86px
}

html {
	-webkit-text-size-adjust: none
}

body {
	font-size: 12px;
	font-family: "Microsoft YaHei";
	color: #666666;
	background: #f3f3f3
}

a:link {
	color: #666666;
	text-decoration: none
}

a:visited {
	color: #666666;
	text-decoration: none
}

a:hover {
	color: #006f31;
	text-decoration: underline
}

.login_main {
	width: 1000px;
	height: 370px;
	margin: 0 auto;
	border: 1px solid #e7e7e7;
	background: #FFF;
	padding-top: 35px
}

.login_main h1 {
	font-size: 24px;
	font-weight: normal;
	font-family: "微软雅黑";
	margin-bottom: 20px;
	color: #333333;
	padding-left: 15px;
	margin-left: 20px
}

.item {
	clear: both;
	padding: 10px 0 15px 0;
	font-size: 14px;
	height: 30px
}

.item_w {
	padding: 0;
	line-height: 30px
}

.wrong {
	margin-left: 120px;
	height: 20px;
	line-height: 20px;
	width: 260px;
	font-size: 12px;
	color: #ff0003;
	border: 1px solid #ffddcf;
	padding-left: 10px;
	border-radius: 2px 2px 2px 2px;
	background: #ffefe9 url(icon.png) 250px -298px no-repeat
}

.labe {
	float: left;
	line-height: 32px;
	text-align: right;
	display: block
}

.labe span {
	color: #F00
}

.w80 {
	width: 90px
}

.w100 {
	width: 100px
}

.w120 {
	width: 120px
}

.w240 {
	width: 240px
}

.pd {
	padding-top: 5px;
	padding-bottom: 5px
}

.username {
	background: url(icon_03.png) no-repeat 5px 6px
}

.email {
	background: url(icon_10.png) no-repeat 5px 6px
}

.password {
	background: url(icon_13.png) no-repeat 5px 6px
}

.sure_password {
	background: url(icon_13.png) no-repeat 5px 6px
}

.login-input {
	border: 1px solid #e1e1e1;
	border-radius: 3px 3px 3px 3px;
	height: 30px;
	line-height: 30px;
	vertical-align: middle;
	width: 225px;
	color: #777777;
	padding-left: 45px;
	display: block;
	float: left
}

.basic-input {
	border: 1px solid #e1e1e1;
	border-radius: 3px 3px 3px 3px;
	font-size: 14px;
	height: 30px;
	line-height: 30px;
	padding-left: 5px;
	padding-right: 5px;
	vertical-align: middle;
	display: block;
	float: left
}

.agreementx {
	margin-left: 120px;
	font-size: 12px
}

.agreement {
	float: left;
	margin-right: 5px;
	margin-top: 9px;
	*margin-top: 5px
}

.agreementx span {
	color: #d31b1d
}

.btn_submit {
	margin-left: 120px;
	width: 270px;
	height: 35px;
	background-color: #8b8989;
	border: none;
	color: #FFF;
	font-weight: bold;
	cursor: pointer;
	font-size: 14px;
	border-radius: 4px 4px 4px 4px
}

.btn_submit:hover {
	background-color: #8b8989;
}
</style>
<title>管理员登录</title>
</head>
<body>
	<div class="login_main">
 <% String message = request.getParameter("message"); %>
<% if(message != null && message.equals("error")){ %>
    <div class="h50 red" style="color: red; font-size: 16px; padding-left: 25px;">密码输入错误</div>
<% }%>
		<h1>登录</h1>

		<form accept-charset="UTF-8" action="login" class="new_user"
			id="new_user" method="post">
			<div class="item">
				<div class="labe w120">
					<span>*</span> 登录账号：
				</div>
				<input autofocus="autofocus" class="login-input username"
					id="user_email" name="name" size="30" type="email"  />
			</div>
			<div class="item">
				<div class="labe w120">
					<span>*</span> 登录密码：
				</div>
				<input class="login-input password" id="user_password"
					name="password" size="30" type="password" />
			</div>
			<div class="item">
				<input class="btn_submit" name="commit" type="submit" value="马上登录" />
			</div>

		</form>
	</div>
</body>
</html>