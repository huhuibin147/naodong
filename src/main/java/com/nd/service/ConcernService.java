package com.nd.service;

import java.util.List;

import com.nd.vo.ArticleVO;
import com.nd.vo.ConcernAll;
import com.nd.vo.ConcernNumVO;
import com.nd.vo.ConcernObjectVO;
import com.nd.vo.QuestionVO;
import com.nd.vo.ThemeVO;

public interface ConcernService {
	
	/**
	 * 获取关注主题详细信息
	 * @param userId
	 * @return
	 */
	public List<ThemeVO> getThemeDetail(String userId,Integer page,Integer num);
	
	/**
	 * 获取关注问题详细信息
	 * @param userId
	 * @return
	 */
	public List<QuestionVO> getQuestionDetail(String userId,Integer page,Integer num);
	
	/**
	 * 获取当前用户所有新动态（联系人不做考虑）
	 * @param userId
	 * @return
	 */
	public ConcernAll getAllDetail(String userId,Integer page,Integer num);
	
	
	public List<ConcernObjectVO> getConItems(String userId,Integer page,Integer num);
	
	/**
	 * 根据用户编号统计用户所有关注数量
	 * @param userId
	 * @return
	 */
	public ConcernNumVO getUserAllConcernNum(String userId);

	List<ArticleVO> getArticleDetail(String userId, Integer page, Integer num);
}
