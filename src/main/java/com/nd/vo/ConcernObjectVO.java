package com.nd.vo;

import java.util.Date;

/**
 * 关注类通用的属性,定义关注类需要继承此类
 * @author hww
 */
public class ConcernObjectVO {
	private Integer id;
	private Integer userId;
	private Date concernDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getConcernDate() {
		return concernDate;
	}
	public void setConcernDate(Date concernDate) {
		this.concernDate = concernDate;
	}
	
}
