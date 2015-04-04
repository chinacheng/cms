package com.cms.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文章属性
 * 
 * @author 
 * 
 */
public class ArticleBean {

    // id
    private int id;
    // 文章标题
    private String title;
    // 文章简介
    private String introduction;
    // 文章作者
    private String author;
    // 内容
    private String content;
    // 发布时间
    private long published;
    // 修改时间
    private long updated;
    // 是否有效,默认为0，无效
    private int isValid = 0;

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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getPublished() {
        return published;
    }

    public void setPublished(long published) {
        this.published = published;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public String getPublishedStringLong() {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date(published));
    }
    
    public String getPublishedString() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date(published));
    }

}
