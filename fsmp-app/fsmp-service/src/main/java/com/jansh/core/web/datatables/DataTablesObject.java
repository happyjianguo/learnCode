package com.jansh.core.web.datatables;

import java.util.List;

/**
 * DataTablesViewObject
 * 
 * @author nie
 *
 * @param <T>
 */
public class DataTablesObject<T> {
	/**
	 * 请求序号
	 */
	private int draw;
	/**
	 * 总记录数
	 */
	private int recordsTotal;
	/**
	 * 过滤后的总记录数
	 */
	private int recordsFiltered;
	/**
	 * aaData 与datatales 加载的“dataSrc"对应
	 */
	private List<T> data;

	private String error;

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "DataTablesObject [draw=" + draw + ", recordsTotal=" + recordsTotal + ", recordsFiltered="
				+ recordsFiltered + ", data=" + data + ", error=" + error + "]";
	}

}