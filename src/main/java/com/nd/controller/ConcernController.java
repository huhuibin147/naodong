package com.nd.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;
import com.nd.dao.ConcernQuestionDao;
import com.nd.dao.ConcernThemeDao;
import com.nd.dao.ConcernUserDao;
import com.nd.dao.QuestionDao;
import com.nd.dao.UserDao;
import com.nd.entity.ConcernQuestion;
import com.nd.entity.ConcernTheme;
import com.nd.entity.Question;
import com.nd.service.ConcernService;
import com.nd.utils.CommonUtil;
import com.nd.utils.JSONUtil;
import com.nd.vo.ConcernAll;

@Controller
@RequestMapping("/Concern")
public class ConcernController {
	
	@Autowired
	private ConcernService concernService;
	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private ConcernQuestionDao concernQuestionDao;
	@Autowired
	private ConcernThemeDao concernThemeDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ConcernUserDao concernUserDao;
	
	@RequestMapping(value= "/initCocernInfomation.do",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAllConcernItems(HttpSession session,Integer page,Integer num){
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId == null){
			return null;
		}
		if(CommonUtil.isNull(page)||CommonUtil.isNull(num)){
			page = 1;
			num = 1;
		}
		ConcernAll concernItem = concernService.getAllDetail(String.valueOf(userId),page,num);
		return JSON.toJSONStringWithDateFormat(concernItem, "yyyy-MM-dd");
	}
	
//	@RequestMapping(value= "/init.do",produces = "text/html;charset=UTF-8")
//	@ResponseBody
//	public String getAllConcernItems(HttpSession session,Integer page,Integer num){
//		String userId = (String) session.getAttribute("userId");
//		if(!StringUtils.isNullOrEmpty(userId)){
//			
//		}
////		System.out.println(userId);
//		List list =  concernService.getConItems(userId,page,num);
//		return JSON.toJSONString(list);
//	}
	
	//关注问题
	@RequestMapping(value= "/concernQues.do")
	@ResponseBody
	public String ConcerQues(HttpSession session,int qid){
		int uid = userDao.getUserId((String)session.getAttribute("username"));
		int i = concernQuestionDao.exitConcerQues(qid, uid);
		if (i == 1) {
		ConcernQuestion cq = new ConcernQuestion();
		cq.setUserId(uid);
		cq.setQuestionId(qid);
		cq.setConcernDate(new Date());
		concernQuestionDao.add(cq);
		return JSON.toJSONString("success");
	}
		return JSON.toJSONString("error");
	}

	// 关注话题
	@RequestMapping(value = "/concernTopic.do")
	@ResponseBody
	public String concernTopic(HttpSession session, int tid) {
		int uid = userDao.getUserId((String) session.getAttribute("username"));
		int i = concernThemeDao.exitConcerTopic(tid, uid);
		if (i == 1) {
			ConcernTheme cq = new ConcernTheme();
			cq.setUserId(uid);
			cq.setThemeId(tid);
			cq.setConcernDate(new Date());
			concernThemeDao.add(cq);
			return JSON.toJSONString("success");
		}
		return JSON.toJSONString("error");
	}

	// 关注话题
	@RequestMapping(value = "/concernTopicByS.do")
	@ResponseBody
	public String concernTopic(HttpSession session, String tid) {
		int uid = userDao.getUserId((String) session.getAttribute("username"));
		int id=concernThemeDao.getTidByTopic(tid);
		int i = concernThemeDao.exitConcerTopic(id, uid);
		if (i == 1) {
			ConcernTheme cq = new ConcernTheme();
			cq.setUserId(uid);
			cq.setThemeId(id);
			cq.setConcernDate(new Date());
			concernThemeDao.add(cq);
			return JSON.toJSONString("success");
		}
		return JSON.toJSONString("error");
	}

	// 点赞问题
	@RequestMapping(value = "/agreeQues.do")
	@ResponseBody
	public String agreeQues(int qid, int num) {
		Question q = questionDao.get(qid);
		q.setZan(num + 1);
		questionDao.update(q);
		return JSON.toJSONString("success");
	}
	
	//获取个人主页右侧栏关注数量
	@RequestMapping(value= "/concernNum.do")
	@ResponseBody
	public String getConcernNum(HttpSession session){
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId == null){
			return null;
		}
		return JSON.toJSONString(concernService.getUserAllConcernNum(String.valueOf(userId)));	
	}
	
	//取消关注话题
	@RequestMapping(value= "/deleteConcernTheme.do")
	@ResponseBody
	public String deleteConcernTheme(HttpSession session,String concernId){
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId == null ||StringUtils.isNullOrEmpty(concernId)){
			return null;
		}
		try{
			concernUserDao.deleteConcernTheme(String.valueOf(userId), concernId);
			return JSONUtil.returnSuccess();
		}catch (Exception e) {
			e.printStackTrace();
			return JSONUtil.returnError();
		}
	}
	
	//取消关注问题
	@RequestMapping(value= "/deleteConcernQuestion.do")
	@ResponseBody
	public String deleteConcernQuestion(HttpSession session,String concernId){
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId == null ||StringUtils.isNullOrEmpty(concernId)){
			return null;
		}
		try{
			concernUserDao.deleteConcernQuestion(String.valueOf(userId), concernId);
			return JSONUtil.returnSuccess();
		}catch (Exception e) {
			e.printStackTrace();
			return JSONUtil.returnError();
		}
	}
	
	//取消关注回答
	@RequestMapping(value= "/deleteConcernAnswer.do")
	@ResponseBody
	public String deleteConcernAnswer(HttpSession session,String concernId){
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId == null ||StringUtils.isNullOrEmpty(concernId)){
			return null;
		}
		try{
			concernUserDao.deleteConcernAnswer(String.valueOf(userId), concernId);
			return JSONUtil.returnSuccess();
		}catch (Exception e) {
			return JSONUtil.returnError();
		}
	}
	
	//取消关注文章
	@RequestMapping(value= "/deleteConcernArticle.do")
	@ResponseBody
	public String deleteConcernArticle(HttpSession session,String concernId){
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId == null ||StringUtils.isNullOrEmpty(concernId)){
			return null;
		}
		try{
			concernUserDao.deleteConcernArticle(String.valueOf(userId), concernId);
			return JSONUtil.returnSuccess();
		}catch (Exception e) {
			return JSONUtil.returnError();
		}
	}
	
	//取消关注用户
	@RequestMapping(value= "/deleteConcernUser.do")
	@ResponseBody
	public String deleteConcernUser(HttpSession session,String concernId){
		Integer ownerId = (Integer) session.getAttribute("userId");
		if(ownerId == null ||StringUtils.isNullOrEmpty(concernId)){
			return null;
		}
		try{
			concernUserDao.deleteConcernUser(String.valueOf(ownerId), concernId);
			return JSONUtil.returnSuccess();
		}catch (Exception e) {
			return JSONUtil.returnError();
		}
	}
	
}
