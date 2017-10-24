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
import com.nd.dao.QuestionDao;
import com.nd.dao.ThemeDao;
import com.nd.utils.PageBean;

@Controller
@RequestMapping("/topic")
public class TopicController {

	@Autowired
   	private ThemeDao themeDao;
	@Autowired
   	private QuestionDao questionDao;
	
	@RequestMapping("/getlist.do")
	@ResponseBody
	public String getTopic(HttpSession session,int page,String topic){
		String email = (String) session.getAttribute("username");
		List Tlist = themeDao.getThemeByEmail(email);
		
		PageBean pb = new PageBean();
		if(topic.equals("")){
			if(Tlist.size()!=0){
				pb = questionDao.QuestionListByTheme(page, (String)Tlist.get(0));
			}
		}else{
			pb = questionDao.QuestionListByTheme(page, topic);
		}
		
		List list = pb.getList();
		int cpage = pb.getCurrentPage();
		if(cpage>pb.getTotalPage()){
			cpage-=1;
		}
		String username = (String) session.getAttribute("realname");
		String userimg = (String) session.getAttribute("img");
		Map<String,Object> map = new HashMap<>();
		map.put("tlist",Tlist);
		map.put("userimg", userimg);
		map.put("username", username);
		map.put("page",cpage );
		map.put("list", list);
		map.put("size", Tlist.size());
		return JSON.toJSONString(map);
	}
	
	@RequestMapping("/gettopic.do")
	@ResponseBody
	public String getOneTopic(HttpSession session,int page,String topic){
		String email = (String) session.getAttribute("username");

		PageBean pb = questionDao.QuestionListByTheme(page, topic);
		List list = pb.getList();
		int cpage = pb.getCurrentPage();
		if(cpage>pb.getTotalPage()){
			cpage-=1;
		}
		String username = (String) session.getAttribute("realname");
		String userimg = (String) session.getAttribute("img");
		Map<String,Object> map = new HashMap<>();
		map.put("userimg", userimg);
		map.put("username", username);
		map.put("page",cpage );
		map.put("list", list);
		return JSON.toJSONString(map);
	}
	
	@RequestMapping("/getTopicList.do")
	@ResponseBody
	public String getTopicList(HttpSession session,int page,String topic){
		String email = (String) session.getAttribute("username");
		List Tlist = themeDao.getThemeByEmail(email);
		PageBean pb = new PageBean();
		if(topic.equals("")){
			pb = themeDao.ThemeSquareList(page);
		}else{
			pb = themeDao.ThemeSquareList(page, topic);
		}
		
		List list = pb.getList();
		int cpage = pb.getCurrentPage();
		if(cpage>pb.getTotalPage()){
			cpage-=1;
		}
		String username = (String) session.getAttribute("realname");
		String userimg = (String) session.getAttribute("img");
		Map<String,Object> map = new HashMap<>();
		map.put("tlist",Tlist);
		map.put("userimg", userimg);
		map.put("username", username);
		map.put("page",cpage );
		map.put("lists", list);
		map.put("size", Tlist.size());
		return JSON.toJSONString(map);
	}
	
}	
