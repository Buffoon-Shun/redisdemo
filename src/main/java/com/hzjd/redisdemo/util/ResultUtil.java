package com.hzjd.redisdemo.util;

import net.sf.json.JSONObject;

/**
 * 
 * @author cys
 *
 */
public class ResultUtil {

	public static JSONObject success(Object object) {
		JSONObject result = new JSONObject();
		result.put("code", 0);
		result.put("message", "成功");
		result.put("data", object);
		return result;
	}

	public static JSONObject success() {
		JSONObject result = new JSONObject();
		result.put("code", 0);
		result.put("message", "成功");
		return result;
	}

	public static JSONObject error(String msg) {
		JSONObject result = new JSONObject();
		result.put("code", 1);
		result.put("message", msg);
		return result;
	}
}