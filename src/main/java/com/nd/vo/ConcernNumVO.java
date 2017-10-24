package com.nd.vo;

import java.math.BigInteger;

public class ConcernNumVO {
	private BigInteger concernManNum;
	private BigInteger concernMeNum;
	private BigInteger concernThemeNum;
	private BigInteger concernArticleNum;
	private BigInteger concernQuestionNum;
	private BigInteger concernAnswerNum;
	
	public BigInteger getConcernManNum() {
		return concernManNum;
	}
	public void setConcernManNum(BigInteger concernManNum) {
		this.concernManNum = concernManNum;
	}
	public BigInteger getConcernMeNum() {
		return concernMeNum;
	}
	public void setConcernMeNum(BigInteger concernMeNum) {
		this.concernMeNum = concernMeNum;
	}
	public BigInteger getConcernThemeNum() {
		return concernThemeNum;
	}
	public void setConcernThemeNum(BigInteger concernThemeNum) {
		this.concernThemeNum = concernThemeNum;
	}
	public BigInteger getConcernArticleNum() {
		return concernArticleNum;
	}
	public void setConcernArticleNum(BigInteger concernArticleNum) {
		this.concernArticleNum = concernArticleNum;
	}
	public BigInteger getConcernQuestionNum() {
		return concernQuestionNum;
	}
	public void setConcernQuestionNum(BigInteger concernQuestionNum) {
		this.concernQuestionNum = concernQuestionNum;
	}
	public BigInteger getConcernAnswerNum() {
		return concernAnswerNum;
	}
	public void setConcernAnswerNum(BigInteger concernAnswerNum) {
		this.concernAnswerNum = concernAnswerNum;
	}
	
	public ConcernNumVO(){
		this.concernAnswerNum = new BigInteger("0");
		this.concernArticleNum = new BigInteger("0");
		this.concernManNum = new BigInteger("0");
		this.concernMeNum = new BigInteger("0");
		this.concernQuestionNum = new BigInteger("0");
		this.concernThemeNum = new BigInteger("0");
	}
	
}
