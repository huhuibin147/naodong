package com.nd.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "con_user")
public class ConcernUser {
	private Integer id;
	private Integer userId;
	private Integer con_userId;
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
	public Integer getCon_userId() {
		return con_userId;
	}
	public void setCon_userId(Integer con_userId) {
		this.con_userId = con_userId;
	}

	
}
