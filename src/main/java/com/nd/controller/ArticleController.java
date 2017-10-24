package com.nd.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.nd.dao.ArticleDao;
import com.nd.dao.UserDao;
import com.nd.entity.Article;
import com.nd.entity.ArticleFind;
import com.nd.entity.User;
import com.nd.utils.PageBean;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleDao articleDao;
	@Autowired
   	private UserDao userDao;
	
	//提交文章
	@RequestMapping("/push.do")
	@ResponseBody
	public String pushArti(String content,String title,HttpSession session) {
		String eamil = (String) session.getAttribute("username");
		int uid = userDao.getUserId(eamil);
		Article ar = new Article();
		ar.setContent(content);
		ar.setTime(new Date());
		ar.setTitle(title);
		ar.setUid(uid);
		ar.setZan(0);
		articleDao.add(ar);
		return JSON.toJSONString("s");
	}
	
	// 文章列表
	@RequestMapping("/artList.do")
	@ResponseBody
	public String getArtList(int page,HttpSession session) {
		PageBean pb = new PageBean();
		pb = articleDao.ArtList(page);
		List<Article> list = pb.getList();
		List<ArticleFind> hotlist = articleDao.hotList();
		int cpage = pb.getCurrentPage();
		if(cpage>pb.getTotalPage()){
			cpage-=1;
		}
		String username = (String) session.getAttribute("realname");
		String userimg = (String) session.getAttribute("img");
		Map<String,Object> map = new HashMap<>();
		map.put("page",cpage );
		map.put("list",list);
		map.put("userimg", userimg);
		map.put("username", username);
		map.put("hotlist", hotlist);
		return JSON.toJSONString(map);
	}
	
	// id获得文章
		@RequestMapping("/art.do")
		@ResponseBody
		public String getArt(HttpSession session,int id) {
			Article art = articleDao.get(id);
			User u = userDao.get(art.getUid());
			String username = (String) session.getAttribute("realname");
			String userimg = (String) session.getAttribute("img");
			Map<String,Object> map = new HashMap<>();
			map.put("art",art);
			map.put("userimg", userimg);
			map.put("username", username);
			map.put("artuser", u);
			return JSON.toJSONString(map);
		}
}
