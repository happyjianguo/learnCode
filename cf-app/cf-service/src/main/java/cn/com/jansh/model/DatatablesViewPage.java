package cn.com.jansh.model;

import java.util.List;

public class DatatablesViewPage<T> {

	private List<T> aaData; //aaData 与datatales 加载的“dataSrc"对应
	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private int length;
	public DatatablesViewPage() {
		
	}
	public List<T> getAaData() {
		return aaData;
	}
	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	@Override
	public String toString() {
		return "DatatablesViewPage [aaData=" + aaData + ", iTotalDisplayRecords=" + iTotalDisplayRecords
				+ ", iTotalRecords=" + iTotalRecords + "]";
	}
	
}