package com.nd.utils;

import java.util.List;

public class PageBean {
	private List list;//返回记录
	private int totalPage;//总页数
	private int pageSize;//每页记录数
	private int allRow;//总记录数
	private int currentPage;//当前页
	private boolean isFirstPage;//是否第一页
	private boolean isLastPage;//是否为最后一页
	private boolean hasPreviousPage;//是否有前一页
	private boolean hasNextPage;//是否有下一页
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getAllRow() {
		return allRow;
	}
	public void setAllRow(int allRow) {
		this.allRow = allRow;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
	public boolean isFirstPage() {
		return currentPage==1;
	}
	
	public boolean isLastPage() {
		return currentPage==totalPage;
	}
	
	public boolean isHasPreviousPage() {
		return currentPage!=1;
	}
	
	public boolean isHasNextPage() {
		return currentPage!=totalPage;
	}
	
	/**
	 * 初始化分页信息
	 */
	public void init(){
		this.isFirstPage = isFirstPage();
		this.isLastPage = isLastPage();
	    this.hasPreviousPage = isHasPreviousPage();
	    this.hasNextPage = isHasNextPage();
	}
	/**
	 * 计算总页数
	 * @param pageSize 每页记录数
	 * @param allRow 总记录数
	 * @return
	 */
	public static int countTotalPage(final int pageSize,final int allRow){
		int totalPage = allRow % pageSize==0?allRow/pageSize:allRow/pageSize+1;
		return totalPage;
	}
	/**
	 * 计算当前页开始记录
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public static int countOffset(final int pageSize,final int currentPage){
		int  offset = pageSize*(currentPage-1);
		return offset;
	}
	 /**
	  * 计算当前页,若为0或者请求的URL中没有"?page=",则用1代替
	  * @paramPage 传入的参数(可能为空,即0,则返回1)
	  * @return 当前页
	  */
	public static int countCurrentPage(int page) {
		final int curPage = (page == 0 ? 1 : page);
		return curPage;
	}
}
