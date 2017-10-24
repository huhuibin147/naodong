package com.nd.vo;

import java.util.Date;

/**
 * 用于业务返回参数，没有的参数可自行添加
 * @author hww
 *
 */
public class ThemeVO extends ConcernObjectVO{
	private Integer themeId;
	private String themeName;
	private String owner;
	private String intro;
	private String imgSrc;
	
	public ThemeVO() {
		super();
	}
	
	public Integer getThemeId() {
		return themeId;
	}

	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
	}

	public String getThemeName() {
		return themeName;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	
}
