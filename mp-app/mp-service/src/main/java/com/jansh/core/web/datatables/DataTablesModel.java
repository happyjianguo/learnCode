package com.jansh.core.web.datatables;

import java.util.List;
import java.util.Map;

import com.jansh.core.web.datatables.DataTablesColumn;

/**
 * DataTablesModel
 * 
 * @author nie
 *
 */
public class DataTablesModel {
	/**
	 * 请求序号
	 */
	private int draw;
	private int start;
	private int length;
	private Map<Search, String> search;

	private List<DataTablesColumn> columns;

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Map<Search, String> getSearch() {
		return search;
	}

	public void setSearch(Map<Search, String> search) {
		this.search = search;
	}

	public List<DataTablesColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<DataTablesColumn> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		return "DataTablesModel [draw=" + draw + ", start=" + start + ", length=" + length + ", search=" + search
				+ ", columns=" + columns + "]";
	}

	public enum Search {
		value, regex
	}
}