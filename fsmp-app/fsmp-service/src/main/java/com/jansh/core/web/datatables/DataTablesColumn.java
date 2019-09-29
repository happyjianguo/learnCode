package com.jansh.core.web.datatables;

import java.util.Map;

/**
 * DataTablesColumn
 * 
 * @author nie
 *
 */
public class DataTablesColumn {
	/**
	 * 请求序号
	 */
	private String data;
	private String name;
	private String searchable;
	private String orderable;
	private Map<Search, String> search;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSearchable() {
		return searchable;
	}

	public void setSearchable(String searchable) {
		this.searchable = searchable;
	}

	public String getOrderable() {
		return orderable;
	}

	public void setOrderable(String orderable) {
		this.orderable = orderable;
	}

	public Map<Search, String> getSearch() {
		return search;
	}

	public void setSearch(Map<Search, String> search) {
		this.search = search;
	}

	@Override
	public String toString() {
		return "DataTablesColumn [data=" + data + ", name=" + name + ", searchable=" + searchable + ", orderable="
				+ orderable + ", search=" + search + "]";
	}

	public enum Search {
		value, regex
	}

}