package com.cms.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 留言
 * 
 * @desc 
 * 
 */
public class MessageBean {

    // id
    private int id;
    // 留言人
    private String name;
    // 留言email
    private String email;

    private String desc;
    // 内容

    private long published;
    // 修改时间

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public long getPublished() {
        return published;
    }

    public void setPublished(long published) {
        this.published = published;
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
