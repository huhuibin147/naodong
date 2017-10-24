package com.nd.service;

import com.nd.entity.User;

public interface UserService {
	
	/**
	 * 检查邮箱是否已被注册，被占用返回true，否则返回false
	 * @param email
	 * @return
	 */
	public boolean checkUserExist(String email);
	
	/**
	 * 判断验证码是否错误，验证码错误返回true,验证码正确返回false
	 * @param identifyCode
	 * @param email
	 * @return
	 */
	public boolean checkIdentifyCode(String identifyCode,String email);
	
	/**
	 * 用户注册，校对验证码,保存用户信息
	 * @param user 
	 * @param identifyCode
	 * @return	注册结果
	 */
	public String registUser(User user,String identifyCode);
}
