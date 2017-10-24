package com.nd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.nd.dao.UserDao;
import com.nd.entity.User;
import com.nd.service.HomePageService;
import com.nd.service.UserService;
import com.nd.utils.JSONUtil;
import com.nd.utils.MD5Util;
import com.nd.utils.MailUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserService userService;

	@Autowired
	private MailUtil mailUtil;

	@Autowired
	private HomePageService homepageService;

	@RequestMapping("/login.do")
	@ResponseBody
	public String login(HttpSession session, String email, String psw) {
		// TODO 最终使用时传入在页面用JS进行MD5加密后的密码，不在后台对传入密码加密
//		psw = MD5Util.getMD5String(psw);
		String login = userDao.login(email, psw);
		System.out.println(email + "----" + psw);
		if (login.equals("密码正确")) {
			User user = userDao.getUserByUid(email);
			session.setAttribute("username", user.getEmail());
			session.setAttribute("realname", user.getName());
			session.setAttribute("img", user.getImg());
			session.setAttribute("userId", user.getId());
			session.setAttribute("sex", user.getSex());
			session.setAttribute("intro", user.getIntroduce());
		}
		Map<String, String> map = new HashMap<>();
		map.put("msg", login);
		return JSON.toJSONString(map);
	}

	@RequestMapping("/sendIdentifyCode.do")
	public void sendIdentifyCode(String email) {
		try {
			mailUtil.sendMail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/checkEmail.do")
	public String checkEmail(String email) {
		return userService.checkUserExist(email) ? "yes" : "no";
	}

	@RequestMapping("/regist.do")
	@ResponseBody
	public String regist(String name, String password, String email, String identifyCode) {
		User user = new User(name, password, email);
		String resultMsg = userService.registUser(user, identifyCode);
		Map<String, String> map = new HashMap<>();
		map.put("msg", resultMsg);
		String msg = resultMsg;
		System.out.println(JSON.toJSONString(msg));
		return JSON.toJSONString(map);
	}

	@RequestMapping("/nav.do")
	@ResponseBody
	public String nav(HttpSession session) {
		String email = (String) session.getAttribute("username");
		User user = userDao.getUserByUid(email);
		Map<String, Object> map = new HashMap<>();
		map.put("userimg", user.getImg());
		map.put("username", email);
		map.put("sex", user.getSex());
		map.put("img", user.getImg());
		map.put("intro", user.getIntroduce());
		map.put("realname", user.getName());
		map.put("birthday", user.getBirthday());
		map.put("interest", user.getInterest());
		return JSON.toJSONStringWithDateFormat(map, "yyyy-MM-dd");
	}

	@RequestMapping("/test.do")
	public void testSession(HttpSession session) {
		HttpSession s = session;
		System.out.println(s.getAttribute("user"));
	}

	@RequestMapping("/userDetailInfo")
	@ResponseBody
	public String getHomePageInfo(HttpSession session, Integer answerPage, Integer questionPage, Integer articlePage) {
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			return null;
		}
		List list = null;
		try {
			list = homepageService.getAllHomePageInfo(String.valueOf(userId), questionPage, answerPage, articlePage, 5);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd");
	}

	@RequestMapping("/logOut.do")
	@ResponseBody
	public void logOut(HttpSession session) {
		session = null;
	}

	@RequestMapping("/chgPwd.do")
	@ResponseBody
	public String changePassword(HttpSession session, String pwd, String newPwd) {
		Integer errorTimes = (Integer) session.getAttribute("errorTimes");
		Map<String, String> map = new HashMap<>();
		String email = (String) session.getAttribute("username");
		if (email == null) {
			map.put("state", "error");
			return JSONUtil.returnError();
		}
		if (userDao.changePassword(email, pwd, newPwd) == true) {
			return JSONUtil.returnSuccess();
		} else {
			if (errorTimes == null) {// 初始化计数器
				session.setAttribute("errorTimes", 0);
			} else if (errorTimes > 4) {// 超出修改请求许可次数
				session = null;
				map.put("state", "Excess license");
				return JSON.toJSONString(map);
			} else {// 请求次数+1
				session.setAttribute("errorTimes", errorTimes + 1);
			}
			return JSONUtil.returnError();
		}
		
	}
	
	@RequestMapping("/updateUserInfo.do")
	@ResponseBody
	public String updateUserInfo(HttpSession session,String username,String sex,String birthday,String interest,String intro){
		String email = (String) session.getAttribute("username");
		if (email == null) {
			return JSONUtil.returnError();
		}
		if(userDao.updateUserInfo(email, username, sex, birthday, interest, intro)==true){
			return JSONUtil.returnSuccess();
		}else{
			return JSONUtil.returnError();
		}
	}
}
