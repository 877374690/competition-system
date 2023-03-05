package com.competition.system.domain;

/**
 * @package: com.competition.system.domain
 * @author: luweihong
 * @description:
 * @create: 2022/3/28 20:29
 * @version: 1.0
 */
public class QueryInfo {
	private String nameSearch;

	private int pageIndex;

	private int pageSize;


	public String getNameSearch() {
		return this.nameSearch;
	}

	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}

	public int getPageIndex() {
		return this.pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
