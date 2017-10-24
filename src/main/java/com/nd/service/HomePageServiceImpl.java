package com.nd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nd.dao.AnswerDao;
import com.nd.dao.ArticleDao;
import com.nd.dao.QuestionDao;
import com.nd.entity.Answer;
import com.nd.entity.Article;
import com.nd.entity.Question;
import com.nd.utils.DBUtil;
import com.nd.vo.AnswerVO;

@Service
public class HomePageServiceImpl implements HomePageService {
	
	@Autowired
	ArticleDao articleDao;
	@Autowired
	AnswerDao answerDao;
	@Autowired
	QuestionDao questionDao;
	@Autowired
	DBUtil db;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<?> getAllHomePageInfo(String userId, Integer questionPage, Integer answerPage, Integer articlePage, Integer num){
		List<Object> list = new ArrayList<>();
		List<Article> articleList = (List<Article>) articleDao.loadByUserId(userId, articlePage, num);
		List<Question> questionList = (List<Question>) questionDao.loadByUserId(userId, questionPage, num);
		List<AnswerVO> answerList = (List<AnswerVO>) answerDao.loadByUserId(userId, answerPage, num);
		list.add(articleList);
		list.add(questionList);
		list.add(answerList);
		return list;
	}
	
	public List<?> getHomePageInfo(String userId, Integer page, Integer num){
		return null;
		
	}
	
}
