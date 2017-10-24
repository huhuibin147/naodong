package com.nd.vo;

import java.util.Date;

public class TopicVO {
	private Integer id;
	private String title;
	private String content;
	private Date time;
	private Integer zan;
	private Integer anum;
	private String name;
	private String img;
	
	
	public TopicVO() {
		super();
	}
	
	public TopicVO(Integer id, String title, String content, Date time, Integer zan, Integer anum, String name,
			String img) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.time = time;
		this.zan = zan;
		this.anum = anum;
		this.name = name;
		this.img = img;
	}
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
	public Integer getZan() {
		return zan;
	}
	public void setZan(Integer zan) {
		this.zan = zan;
	}
	public Integer getAnum() {
		return anum;
	}
	public void setAnum(Integer anum) {
		this.anum = anum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "TopicVO [id=" + id + ", title=" + title + ", content="
				+ content + ", time=" + time + ", zan=" + zan + ", anum="
				+ anum + ", name=" + name + ", img=" + img + "]";
	}
	
}
