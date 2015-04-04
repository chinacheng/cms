package com.cms.bean;

/**
 * 文章属性
 * @author 
 *
 */
public class LinkBean {
	
	// id
	private int id;
	// 链接名
	private String name;
	// 链接地址
	private String url;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
