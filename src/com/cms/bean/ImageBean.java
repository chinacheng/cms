package com.cms.bean;

/**
 * 图片属性
 * @author 
 *
 */
public class ImageBean {
	
	// id
	private int id;
	// 标题
	private String title;
	// 描述
	private String description;
	// 路径
	private String path;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String desc) {
		this.description = desc;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
