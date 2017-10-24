package com.nd.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="answer")
public class Answer {
	private Integer id;
	private Integer qid;
	private Integer uid;
	private String content;
	private Integer anonymity;
	private Integer hit;
	private Date time;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getAnonymity() {
		return anonymity;
	}
	public void setAnonymity(Integer anonymity) {
		this.anonymity = anonymity;
	}
	public Integer getHit() {
		return hit;
	}
	public void setHit(Integer hit) {
		this.hit = hit;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Answer [id=" + id + ", qid=" + qid + ", uid=" + uid
				+ ", content=" + content + ", anonymity=" + anonymity
				+ ", hit=" + hit + ", time=" + time + "]";
	}
	
	
	
}
