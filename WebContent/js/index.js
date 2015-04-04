// JavaScript Document

$(document)
		.ready(
				$(function() {
					// 请求左边栏新闻列表
					$.ajax({
						url : "/CMS/articles",
						type : 'get',
						dataType : 'jsonp',
						data : "startpage=" + 0 + "&pagesize=10",
						jsonp : 'callback',
						success : function(result) {
							// console.log(result.data.length);
							if (result.result == 200) {
								// 返回正确结果
								for ( var a = 0; a < result.data.length; a++) {
									var li = "<li><span>" + (a + 1)
											+ "</span><a style='margin-left: 5px;' href='/CMS/new_show.jsp?id="
											+ result.data[a].id
											+ "' target='_blank' title=''>"
											+ result.data[a].title
											+ "</a></li>";
									$("#left_news_ul").append(li);
								}
							} else {
								alert("请求数据出错，请稍后重试");
							}
						}
					});
					// 请求友情链接
					$.ajax({
						url : "/CMS/links",
						type : 'get',
						dataType : 'jsonp',
						data : "",
						jsonp : 'callback',
						success : function(result) {
							if (result.result == 200) {
								// 返回正确结果
								for ( var a = 0; a < result.data.length; a++) {
									var span = "<span> <a href='"
											+ result.data[a].url + "'>"
											+ result.data[a].name
											+ "</a> </span>";
									$("#links").append(span);
								}
							} else {
								alert("请求数据出错，请稍后重试");
							}
						}
					});

					$
							.ajax({
								url : "/CMS/images",
								type : 'get',
								dataType : 'jsonp',
								data : "",
								jsonp : 'callback',
								success : function(result) {
									if (result.result == 200) {
										// 返回正确结果
										// console.log(result.data.length);
										for ( var a = 0; a < result.data.length; a++) {

//											var li = "<li><a href='#' target='_blank'><img src='"
//													+ result.data[a].path
//													+ "' alt='"
//													+ result.data[a].title
//													+ "' /></a></li>";
//											$("#rotaion_list").append(li);
										}
									} else {
										alert("请求数据出错，请稍后重试");
									}
								}
							});
				}));

