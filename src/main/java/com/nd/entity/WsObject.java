package com.nd.entity;

public class WsObject {
	private String name;
	private String img;
	private String msg;
	private Integer status;//0-离线 1-在线 2-发信息
	
	public WsObject(){
		
	}
	

	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
