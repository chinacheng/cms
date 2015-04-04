package com.cms.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ErrorJson {

	public static String getErrorJsonObject(int errorCode,String callback) {
		JSONObject errorJson = new JSONObject();
		errorJson.put("result", "error");
		errorJson.put("code", errorCode);
		return callback + "(" + errorJson.toString() + ")";
	}
	
	public static JSONObject getErrorJsonObject(int errorCode) {
		JSONObject errorJson = new JSONObject();
		errorJson.put("result", "error");
		errorJson.put("code", errorCode);
		return errorJson;
	}

	public static JSONArray getErrorJsonArray(int errorCode) {
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(getErrorJsonObject(errorCode));
		return jsonArray;
	}

}
