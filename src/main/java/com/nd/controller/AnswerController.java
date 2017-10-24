package com.nd.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.nd.dao.AnswerDao;
import com.nd.dao.QuestionDao;
import com.nd.dao.UserDao;
import com.nd.entity.Answer;
import com.nd.entity.Question;
import com.nd.entity.User;
import com.nd.utils.PageBean;

@Controller
@RequestMapping("/answer")
public class AnswerController {
	
	@Autowired
	private AnswerDao answerDao;
	@Autowired
   	private UserDao userDao;
	@Autowired
	private QuestionDao questionDao;
	
	//问题的回答
		@RequestMapping("/alist.do")
		@ResponseBody
		public String alist(int page,int qid){
			PageBean pb = new PageBean();
			pb = answerDao.AnswerList(page,qid);
			List<Answer> list = pb.getList();
			int cpage = pb.getCurrentPage();
			if(cpage>pb.getTotalPage()){
				cpage-=1;
			}
			Map<String,Object> map = new HashMap<>();
			List<String> imgs=new ArrayList<String>();
			List<String> names=new ArrayList<String>();
			int count = answerDao.getCount(qid);
			for (Answer a : list) {
				User u = userDao.getUserByUid(a.getUid());
				imgs.add(u.getImg());
				names.add(u.getName());
			}
			map.put("page",cpage );
			map.put("list", list);
			map.put("imgs", imgs);
			map.put("names", names);
			map.put("count", count);
			return JSON.toJSONString(map, SerializerFeature.WriteDateUseDateFormat);
		}
	
	//提交回答
	@RequestMapping("/push.do")
	@ResponseBody		
	public String pushQues(String content,int qid,HttpServletRequest request){
		String eamil = (String) request.getSession().getAttribute("username");
		int uid = userDao.getUserId(eamil);
		Question q = questionDao.get(qid);
		q.setAnum(q.getAnum()+1);
		questionDao.update(q);
		Answer an = new Answer();
		an.setHit(0);
		an.setQid(qid);
		an.setContent(content);
		an.setUid(uid);
		an.setTime(new Date());
		answerDao.add(an);
		return JSON.toJSONString("s");
	}
	
	
			
}
