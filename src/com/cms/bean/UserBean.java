package com.cms.bean;

import com.cms.utils.Base64;

/**
 * 用户属性
 * 
 * @author 
 * 
 */
public class UserBean {

    private int id;
    private int role_id;
    private String name;
    private String pwd;
    private String sex;
    private String email;
    private String mobile;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return Base64.getFromBase64(pwd);
    }

    public void setPwd(String pwd) {
        this.pwd = Base64.getBase64(pwd);
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

   

}
