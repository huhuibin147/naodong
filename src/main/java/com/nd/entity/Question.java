package com.nd.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="question")
public class Question {
	private Integer id;
	private String title;
	private String content;
	private Integer uid;
	private Date time;
	private Integer zan;
	private Integer tid;
	private Integer anonymity;
	private Integer anum;
	private Date modifyDate;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	public Integer getAnonymity() {
		return anonymity;
	}
	public void setAnonymity(Integer anonymity) {
		this.anonymity = anonymity;
	}
	public Integer getAnum() {
		return anum;
	}
	public void setAnum(Integer anum) {
		this.anum = anum;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getZan() {
		return zan;
	}
	public void setZan(Integer zan) {
		this.zan = zan;
	}
	
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", title=" + title + ", content=" + content + ", uid=" + uid + ", time=" + time
				+ ", zan=" + zan + ", tid=" + tid + ", anonymity=" + anonymity + ", anum=" + anum + ", modifyDate="
				+ modifyDate + "]";
	}
	public Question(Integer id, String title, String content, Integer uid, Date time, Integer zan, Integer tid,
			Integer anonymity, Integer anum, Date modifyDate) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.uid = uid;
		this.time = time;
		this.zan = zan;
		this.tid = tid;
		this.anonymity = anonymity;
		this.anum = anum;
		this.modifyDate = modifyDate;
	}
	public Question() {
		super();
	}
	
	
	
	
	
}
