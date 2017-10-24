package com.nd.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
/** 
 * MD5加密工具
 */
public class MD5Util {
    
    public static String getMD5String(String str){
    	try{
    		MessageDigest md5 = MessageDigest.getInstance("MD5");
    		md5.update(str.getBytes());
    		return new BigInteger(1,md5.digest()).toString(16);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return str;
    }
    
    /**
     * 测试主函数
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        String str = new String("abc");
        str = Base64Util.getBase64(str);
        
        System.out.println("原始：" + str);
        System.out.println(getMD5String(str));
    }
}
