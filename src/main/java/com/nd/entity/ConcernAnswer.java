package com.nd.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "con_answer")
public class ConcernAnswer {
	private Integer id;
	private Integer userId;
	private Integer answerId;
	private Date concernDate;
	
	public Date getConcernDate() {
		return concernDate;
	}
	public void setConcernDate(Date concernDate) {
		this.concernDate = concernDate;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
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
	public Integer getAnswerId() {
		return answerId;
	}
	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}
	
	
}
