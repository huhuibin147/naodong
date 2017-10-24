package com.nd.utils;

import java.util.Properties;

import org.apache.tomcat.util.buf.HexUtils;

import com.alibaba.druid.util.DruidPasswordCallback;
import com.alibaba.druid.util.StringUtils;

public class DBPasswordConfig extends DruidPasswordCallback {
	
	private static final String KEY = HexUtil.strToHex("naodong");
	
	public void setProperties(Properties properties){
		super.setProperties(properties);
		String password = properties.getProperty("jdbc.password");
		if(!StringUtils.isEmpty(password)){
			String result = HexUtil.hexToStr(AESUtil.ecbDecode(password, KEY));
			result = result.replaceAll("^(0+)", "");
			setPassword(result.toCharArray());
		}
	}
	
	/**
	 * 测试用例
	 * @param args
	 */
	public static void main(String args[]){
		String str = HexUtil.strToHex("201324133124");
		String d = "8f19abcc3f0b94e16510a227a7f27cd4";
		System.out.println(AESUtil.ecbEncode(str, KEY));
		System.out.println(AESUtil.ecbDecode(d, KEY));
		
	}
	
	
}
