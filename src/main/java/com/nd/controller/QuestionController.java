package com.nd.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.nd.dao.QuestionDao;
import com.nd.dao.ThemeDao;
import com.nd.dao.UserDao;
import com.nd.entity.Question;
import com.nd.entity.Theme;
import com.nd.entity.User;
import com.nd.utils.PageBean;

@Controller
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
   	private QuestionDao questionDao;
	@Autowired
   	private UserDao userDao;
	@Autowired
   	private ThemeDao themeDao;
	
	//首页问题
	@RequestMapping("/qlist.do")
	@ResponseBody
	public String qlist(int page,HttpSession session,String search){
		
		PageBean pb = new PageBean();
		if(search.equals("")){
			pb = questionDao.QuestionList(page);
		}else{
			pb = questionDao.QuestionList(page,search);
		}
		List<Question> list = pb.getList();
		int cpage = pb.getCurrentPage();
		if(cpage>pb.getTotalPage()){
			cpage-=1;
		}
		List<String> imgs=new ArrayList<String>();
		List<String> names=new ArrayList<String>();
		List<String> themes=new ArrayList<String>();
		String username = (String) session.getAttribute("realname");
		String userimg = (String) session.getAttribute("img");
		for (Question q : list) {
			User u = userDao.getUserByUid(q.getUid());
			imgs.add(u.getImg());
			names.add(u.getName());
			themes.add(themeDao.getThemeById(q.getTid()));
		}
		Map<String,Object> map = new HashMap<>();
		map.put("page",cpage );
		map.put("userimg", userimg);
		map.put("username", username);
		map.put("list", list);
		map.put("imgs", imgs);
		map.put("names", names);
		map.put("themes", themes);
		return JSON.toJSONString(map, SerializerFeature.WriteDateUseDateFormat);
//		return JSON.toJSONString(map);
	}
	
	//单个问题
	@RequestMapping("/Ques.do")
	@ResponseBody
	public String getQues(int id,HttpSession session){
		Question ques = questionDao.get(id);
		String username = (String) session.getAttribute("realname");
		String userimg = (String) session.getAttribute("img");
		Map<String,Object> map = new HashMap<>();
		String theme = themeDao.getThemeById(ques.getTid());
		map.put("ques", ques);
		map.put("theme", theme);
		map.put("userimg", userimg);
		map.put("username", username);
		return JSON.toJSONString(map); 
	}
	
	//提交问题
	@RequestMapping("/pushQues.do")
	@ResponseBody		
	public String pushQues(String title,String topic,String content,HttpServletRequest request){
		Question ques = new Question();
		String eamil = (String) request.getSession().getAttribute("username");
		int uid = userDao.getUserId(eamil);
		int tid = themeDao.getThemeId(topic);
		System.out.println("zhutiid:"+tid);
		if(tid == -1){
			tid = themeDao.add(new Theme(topic));
		}
		System.out.println("zhutiid:"+tid);
		ques.setUid(uid);
		ques.setContent(content);
		ques.setTid(tid);
		ques.setTitle(title);
		ques.setZan(0);
		ques.setAnum(0);
		ques.setTime(new Date());
		questionDao.add(ques);
		return JSON.toJSONString("success");
	}
	//圆桌
		@RequestMapping("/yz.do")
		@ResponseBody
		public String yz(HttpSession session){
			String username = (String) session.getAttribute("realname");
			String userimg = (String) session.getAttribute("img");
			Map<String,Object> map = new HashMap<>();
			map.put("userimg", userimg);
			map.put("username", username);
			return JSON.toJSONString(map); 
		}
}
