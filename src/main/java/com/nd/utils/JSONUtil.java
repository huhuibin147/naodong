package com.nd.utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class JSONUtil {
	
	public static String returnError(){
		Map<String,String> m = new HashMap<>();
		m.put("state", "error");
		return JSON.toJSONString(m);
	}
	
	public static String returnSuccess(){
		Map<String,String> m = new HashMap<>();
		m.put("state", "success");
		return JSON.toJSONString(m);
	}
}
