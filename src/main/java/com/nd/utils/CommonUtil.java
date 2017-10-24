package com.nd.utils;

public class CommonUtil {
	public static boolean isNull(Object object){
		if(object == null||object.equals(null)){
			return true;
		}
		return false;
	}
}
