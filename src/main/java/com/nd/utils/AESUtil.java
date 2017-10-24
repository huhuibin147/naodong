package com.nd.utils;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
public class AESUtil {
	/**
	 * AES/ECB模式 加密/解密工具
	 * @param hexStr 16进制字符串
	 * @param hexKey 16进制密钥
	 * @param mode	加密/解密
	 * @return 返回加密/解密结果
	 * @throws Exception
	 */
	private static String ecbBaseTool(String hexStr,String hexKey,int mode) throws Exception{
		String result = null;
		byte[] strBytes = HexUtil.hexToBytes(hexStr);
		byte[] keyBytes = HexUtil.hexToBytes(hexKey);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
		cipher.init(mode, keySpec);
		byte[] encrypted = cipher.doFinal(strBytes);
		result = HexUtil.bytesToHex(encrypted);
		return result;
	}
	/**
	 * 获取加密后字符串
	 * @param hexStr 16进制字符串
	 * @param hexKey 16进制密钥
	 * @return	加密结果
	 */
	public static String ecbEncode(String hexStr,String hexKey){
		String result = null;
		try{
			result = ecbBaseTool(hexStr,hexKey,Cipher.ENCRYPT_MODE);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("加密失败");
		}
		return result;
	}
	
	/**
	 * 获取解密后字符串
	 * @param hexStr 16进制字符串
	 * @param hexKey 16进制密钥
	 * @return	解密结果
	 */
	public static String ecbDecode(String hexStr,String hexKey){
		String result = null;
		try{
			result = ecbBaseTool(hexStr,hexKey,Cipher.DECRYPT_MODE);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("解密失败");
		}
		return result;
	}
	
}
