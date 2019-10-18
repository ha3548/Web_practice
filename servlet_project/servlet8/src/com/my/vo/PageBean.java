package com.my.vo;

import java.util.List;

public class PageBean<T> {
	private int currentPage;//현재페이지
	private int cntPerPage;
	private List<T> list;
	private int totalCnt;//row전체의 수
	private int maxPage;
	private String url;
	private int startPage;
	private int endPage;
	
	public PageBean() {
		super();
	}

	public PageBean(int currentPage, int cntPerPage, List<T> list, int totalCnt, int maxPage, String url, int startPage,
			int endPage) {
		super();
		this.currentPage = currentPage;
		this.cntPerPage = cntPerPage;
		this.list = list;
		this.totalCnt = totalCnt;
		this.maxPage = maxPage;
		this.url = url;
		this.startPage = startPage;
		this.endPage = endPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCntPerPage() {
		return cntPerPage;
	}

	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
}

