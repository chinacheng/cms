package com.cms.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SuccessJson {
	
	public static String getSuccessJson(String callback) {
		JSONObject successJson = new JSONObject();
		successJson.put("result", 200);
		successJson.put("code", 200);
		return callback + "(" + successJson.toString() + ")";
	}
	
	public static JSONObject getSuccessJson() {
		JSONObject successJson = new JSONObject();
		successJson.put("result", "success");
		successJson.put("code", 200);
		return successJson;
	}
	public static JSONArray getSuccessJsonArray() {
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(getSuccessJson());
		return jsonArray;
	}
}
