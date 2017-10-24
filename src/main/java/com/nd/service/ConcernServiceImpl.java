package com.nd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nd.constant.ServiceConstant;
import com.nd.dao.ConcernAnswerDao;
import com.nd.dao.ConcernArticleDao;
import com.nd.dao.ConcernQuestionDao;
import com.nd.dao.ConcernThemeDao;
import com.nd.dao.ConcernUserDao;
import com.nd.dao.ThemeDao;
import com.nd.vo.ArticleVO;
import com.nd.vo.ConcernAll;
import com.nd.vo.ConcernNumVO;
import com.nd.vo.ConcernObjectVO;
import com.nd.vo.QuestionVO;
import com.nd.vo.ThemeVO;

@Service
public class ConcernServiceImpl implements ConcernService{
	
	@Autowired
	private ConcernAnswerDao concernAnswerDao;
	@Autowired
	private ConcernArticleDao concernArticleDao;
	@Autowired
	private ConcernQuestionDao concernQuestionDao;
	@Autowired
	private ConcernThemeDao concernThemeDao;
	@Autowired
	private ConcernUserDao concernUserDao;
	@Autowired
	private ThemeDao themeDao;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getByUserId(String userId,Integer type){
		List<Object> list = new ArrayList<>();
		if(type.equals(ServiceConstant.CONCERN_TYPE_ANSWER)){
			list = concernAnswerDao.getListByUserId(userId);
		}else if(type.equals(ServiceConstant.CONCERN_TYPE_ARTICLE)){
			list = concernArticleDao.getListByUserId(userId);
		}else if(type.equals(ServiceConstant.CONCERN_TYPE_QUESTION)){
			list = concernQuestionDao.getListByUserId(userId);
		}else if(type.equals(ServiceConstant.CONCERN_TYPE_THEME)){
			list = concernThemeDao.getListByUserId(userId);
		}else if(type.equals(ServiceConstant.CONCERN_TYPE_USER)){
			list = concernUserDao.getListByUserId(userId);
		}
		return list;
	}
	
	/**
	 * 获取关注主题详细信息
	 * @param userId
	 * @return
	 */
	@Override
	public List<ThemeVO> getThemeDetail(String userId,Integer page,Integer num){
		List<ThemeVO> resultList = concernThemeDao.getThemesDetail(userId,page,num);
		return resultList;
	}
	
	/**
	 * 获取关注问题详细信息
	 * @param userId 用户ID
	 * @param page 页码
	 * @param num 每页数据量
	 * @return
	 */
	@Override
	public List<QuestionVO> getQuestionDetail(String userId,Integer page,Integer num){
		List<QuestionVO> resultList = concernQuestionDao.getQuestionDetail(userId,page,num);
		return resultList;
	}
	
	/**
	 * 获取关注文章详细信息
	 * @param userId 用户ID
	 * @param page 页码
	 * @param num 每页数据量
	 * @return
	 */
	@Override
	public List<ArticleVO> getArticleDetail(String userId,Integer page,Integer num){
		List<ArticleVO> resultList = concernArticleDao.getArticleDetail(userId,page,num);
		return resultList;
	}
	
	/**
	 * 获取当前用户所有新动态（联系人不做考虑）
	 * @param userId
	 * @return
	 */
	@Override
	public ConcernAll getAllDetail(String userId,Integer page,Integer num){
		ConcernAll allDetailVO = new ConcernAll();
		allDetailVO.setConcernQuestions(this.getQuestionDetail(userId,page,num));
		allDetailVO.setConcernThemes(this.getThemeDetail(userId,page,num));
		allDetailVO.setConcernArticles(this.getArticleDetail(userId, page, num));
		return allDetailVO;
	}
	
	/**
	 * 获取关注Object
	 * @param userId
	 * @return
	 */
	public List<ConcernObjectVO> getConItems(String userId,Integer page,Integer num){
		List<ConcernObjectVO> list = new ArrayList<>();
		list.addAll(this.getQuestionDetail(userId,page,num));
		list.addAll(this.getThemeDetail(userId,page,num));
		return list;
		
	}
	
	/**
	 * 根据用户编号统计用户所有关注数量
	 */
	public ConcernNumVO getUserAllConcernNum(String userId){
		ConcernNumVO vo= concernUserDao.getUserAllConcernNum(userId);
		return vo;
	}

	
}
