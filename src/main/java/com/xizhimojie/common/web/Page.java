package com.xizhimojie.common.web;

import java.util.List;

public class Page<T> {
	private int pageNumber;
	private int pageSize;
	private int count;
	private List<T> list;
	
	
	
	public Page(int pageNumber, int pageSize, int count, List<T> list) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.count = count;
		this.list = list;
	}
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
