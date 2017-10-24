package com.nd.entity;


public class ArticleFind {
	private Integer id;
	private String name;
	private String content;
	private String title;
	private Integer zan;
	public ArticleFind() {
		super();
	}
	public ArticleFind(Integer id, String name, String content, String title,
			Integer zan) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.title = title;
		this.zan = zan;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getZan() {
		return zan;
	}
	public void setZan(Integer zan) {
		this.zan = zan;
	}
	
}
