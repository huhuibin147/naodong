package com.nd.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 包含homepage页面所有的关注信息
 * @author hww
 *
 */
public class ConcernAll {
	private List<ThemeVO> concernThemes;
//	private List<UserVO> concernUsers;
	private List<AnswerVO> concernAnswers;
	private List<QuestionVO> concernQuestions;
	private List<ArticleVO> concernArticles;
	
	public List<QuestionVO> getConcernQuestions() {
		return concernQuestions;
	}

	public void setConcernQuestions(List<QuestionVO> concernQuestions) {
		this.concernQuestions = concernQuestions;
	}

	public List<ThemeVO> getConcernThemes() {
		return concernThemes;
	}

	public void setConcernThemes(List<ThemeVO> concernThemes) {
		this.concernThemes = concernThemes;
	}

	public List<AnswerVO> getConcernAnswers() {
		return concernAnswers;
	}

	public void setConcernAnswers(List<AnswerVO> concernAnswers) {
		this.concernAnswers = concernAnswers;
	}

	public List<ArticleVO> getConcernArticles() {
		return concernArticles;
	}

	public void setConcernArticles(List<ArticleVO> concernArticles) {
		this.concernArticles = concernArticles;
	}
	
	
//	public List<UserVO> getConcernUsers() {
//		return concernUsers;
//	}
//
//	public void setConcernUsers(List<UserVO> concernUsers) {
//		this.concernUsers = concernUsers;
//	}
//	
	
}
