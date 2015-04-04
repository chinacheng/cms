package com.cms.json;

import java.util.List;

import com.cms.bean.ArticleBean;
import com.cms.bean.ImageBean;
import com.cms.bean.LinkBean;
import com.cms.bean.MessageBean;
import com.cms.bean.RoleBean;
import com.cms.bean.UserBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 生成Json形式的数据
 * 
 * @author 302
 * 
 */
public class JsonHelper {

    /**
     * 返回文章信息或列表信息
     * 
     * @param list
     * @return
     */
    public static String getArticlesJson(List<ArticleBean> list, String callback) {
        JSONObject successJson = new JSONObject();
        successJson.put("result", 200);
        JSONArray array = new JSONArray();
        JSONObject json = null;
        for (int i = 0; i < list.size(); i++) {
            json = new JSONObject();
            json.put("id", list.get(i).getId());
            json.put("title", list.get(i).getTitle());
            json.put("intro", list.get(i).getIntroduction());
            json.put("author", list.get(i).getAuthor());
            json.put("content", list.get(i).getContent());
            json.put("published", list.get(i).getPublished());
            json.put("isvalid", list.get(i).getIsValid());
            array.add(json);
        }
        successJson.put("data", array);
        return callback + "(" + successJson.toString() + ")";
    }

    /**
     * 返回链接信息
     * 
     * @param list
     * @return
     */
    public static String getLinksJson(List<LinkBean> list, String callback) {
        JSONObject successJson = new JSONObject();
        successJson.put("result", 200);
        JSONArray array = new JSONArray();
        JSONObject json = null;
        for (int i = 0; i < list.size(); i++) {
            json = new JSONObject();
            json.put("id", list.get(i).getId());
            json.put("name", list.get(i).getName());
            json.put("url", list.get(i).getUrl());
            array.add(json);
        }
        successJson.put("data", array);
        return callback + "(" + successJson.toString() + ")";
    }

    /**
     * 返回角色信息
     * 
     * @param list
     * @return
     */
    public static String getRolesJson(List<RoleBean> list, String callback) {
        JSONObject successJson = new JSONObject();
        successJson.put("result", 200);
        JSONArray array = new JSONArray();
        JSONObject json = null;
        for (int i = 0; i < list.size(); i++) {
            json = new JSONObject();
            json.put("id", list.get(i).getId());
            json.put("name", list.get(i).getName());
            json.put("description", list.get(i).getDescription());
            array.add(json);
        }
        successJson.put("data", array);
        return callback + "(" + successJson.toString() + ")";
    }

    /**
     * 返回用户信息
     * 
     * @param list
     * @return
     */
    public static String getUsersJson(List<UserBean> list, String callback) {
        JSONObject successJson = new JSONObject();
        successJson.put("result", 200);
        JSONArray array = new JSONArray();
        JSONObject json = null;
        for (int i = 0; i < list.size(); i++) {
            json = new JSONObject();
            json.put("id", list.get(i).getId());
            json.put("name", list.get(i).getName());
            json.put("pwd", list.get(i).getPwd());
            json.put("sex", list.get(i).getSex());
            json.put("email", list.get(i).getEmail());
            json.put("mobile", list.get(i).getMobile());
            json.put("address", list.get(i).getAddress());
            json.put("role_id", list.get(i).getAddress());
            array.add(json);
        }
        successJson.put("data", array);
        return callback + "(" + successJson.toString() + ")";
    }

    /**
     * 返回图片信息
     * 
     * @param list
     * @return
     */
    public static String getImagesJson(List<ImageBean> list, String callback) {
        JSONObject successJson = new JSONObject();
        successJson.put("result", 200);
        JSONArray array = new JSONArray();
        JSONObject json = null;
        for (int i = 0; i < list.size(); i++) {
            json = new JSONObject();
            json.put("id", list.get(i).getId());
            json.put("title", list.get(i).getTitle());
            json.put("desc", list.get(i).getDesc());
            json.put("path", list.get(i).getPath());
            array.add(json);
        }
        successJson.put("data", array);
        return callback + "(" + successJson.toString() + ")";
    }

    /**
     * 返回反馈信息
     * 
     * @param list
     * @return
     */
    public static String getMessagesJson(List<MessageBean> list, String callback) {
        JSONObject successJson = new JSONObject();
        successJson.put("result", 200);
        JSONArray array = new JSONArray();
        JSONObject json = null;
        for (int i = 0; i < list.size(); i++) {
            json = new JSONObject();
            json.put("id", list.get(i).getId());
            json.put("name", list.get(i).getName());
            json.put("email", list.get(i).getEmail());
            json.put("content", list.get(i).getContent());
            json.put("published", list.get(i).getPublishedString());
            array.add(json);
        }
        successJson.put("data", array);
        return callback + "(" + successJson.toString() + ")";
    }
}
