package org.lq.bean;

import java.util.Map;

public class PageBean {
	Map<String,Book> map ;
	Integer size;
	Integer pageIndex;
	Integer totals;
	
	public Integer getNumberOfPages() {
		return (int) Math.ceil((double)totals/size);
	}
	
	public Map<String, Book> getMap() {
		return map;
	}
	public void setMap(Map<String, Book> map) {
		this.map = map;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getTotals() {
		return totals;
	}
	public void setTotals(Integer totals) {
		this.totals = totals;
	}
}
