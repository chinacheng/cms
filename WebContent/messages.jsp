<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/cms.css" media="all" rel="stylesheet" type="text/css" />
<title>留言板</title>
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="content">

		<%@ include file="nav.jsp"%>

		<div class="container pl10">
			<h3>留言板</h3>
			<div>
				<form accept-charset="UTF-8" action="/CMS/messages"
					class="simple_form new_feedback" data-validate="true"
					id="new_feedback" method="post" novalidate="novalidate">
					<div style="margin: 0; padding: 0; display: inline">
						<input name="utf8" type="hidden" value="&#x2713;" /><input
							name="authenticity_token" type="hidden"
							value="T1TXM1Pk7MnCTVGxV4nW7M+pdaeR/X1xHbsf5iu8iCE=" />
							<input
                            name="type" type="hidden" value='1'>
					</div>
					<div class="control-group text required feedback_content">
						<label class="text required control-label" for="feedback_content"><span
							class='required-mark'>*</span> 内容</label>
						<div class="controls">
							<textarea class="text required" cols="100" id="feedback_content"
								name="desc" rows="10" style="width: 560px;">
</textarea>
						</div>
					</div>
					<div class="control-group email required feedback_email">
						<label class="email required control-label" for="feedback_email"><span
							class='required-mark'>*</span> 邮箱</label>
						<div class="controls">
							<input class="string email required" id="feedback_email"
								name="email" size="50" type="email" />
							
						</div>
					</div>
					<div class="control-group string required feedback_name">
						<label class="string required control-label" for="feedback_name"><span
							class='required-mark'>*</span> 姓名</label>
						<div class="controls">
							<input class="string required" id="feedback_name"
								name="name" size="50" type="text" />
						</div>
					</div>


					<div class="form-actions">
						<input class="btn-submit" name="commit" type="submit" value="提交" />
						<a href="/" class="btn-back">返回</a>
					</div>
				</form>
			</div>
		</div>

	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>