package com.nd.service;

import java.util.List;

import com.nd.utils.PageBean;

public interface HomePageService {
	/**
	 * @param userId 用户编号
	 * @param page 页码数
	 * @param num 记录数
	 * @return
	 */
	List<?> getAllHomePageInfo(String userId, Integer questionPage, Integer answerPage, Integer articlePage,
			Integer num);
}
