package com.nd.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nd.constant.ServiceConstant;
import com.nd.dao.UserDao;
import com.nd.entity.User;
import com.nd.utils.MD5Util;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	/**
	 * 判断邮箱是否已被注册，被占用返回true，否则返回false
	 * @param email
	 * @return
	 */
	public boolean checkUserExist(String email) {
		return userDao.checkUserExist(email);
	}

	/**
	 * 判断验证码是否错误，验证码错误返回true,验证码正确返回false
	 * @param identifyCode
	 * @param email
	 * @return
	 */
	public boolean checkIdentifyCode(String identifyCode,String email){
		return userDao.checkIdentifyCode(identifyCode, email);
	}
	
	/**
	 * 用户注册，保存用户信息，校对验证码
	 * @return 返回注册结果
	 */
	@Transactional
	public String registUser(User user,String identifyCode){
		try{
			user.setPassword(MD5Util.getMD5String(user.getPassword()));
			if(this.checkUserExist(user.getEmail())){
				return ServiceConstant.REGIST_ERROR_EMAIL;
			}
			if(this.checkIdentifyCode(identifyCode, user.getEmail())){
				return ServiceConstant.REGIST_ERROR_IDENTIFYCODE;
			}
			userDao.add(user);
		}catch(Exception e){
			e.printStackTrace();
			return ServiceConstant.SYSTEM_ERROR;
		}	
		return ServiceConstant.REGIST_STATUS_SUCCESS;
	}
	
}
