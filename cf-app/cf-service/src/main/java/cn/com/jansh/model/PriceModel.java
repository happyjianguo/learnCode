package cn.com.jansh.model;

public class PriceModel {

	@Override
	public String toString() {
		return "PriceModel [allapprice=" + allapprice + ", allspprice=" + allspprice + ", allprofit=" + allprofit + "]";
	}
	private String allapprice;
	private String allspprice;
	private String allprofit;
	public String getAllapprice() {
		return allapprice;
	}
	public void setAllapprice(String allapprice) {
		this.allapprice = allapprice;
	}
	public String getAllspprice() {
		return allspprice;
	}
	public void setAllspprice(String allspprice) {
		this.allspprice = allspprice;
	}
	public String getAllprofit() {
		return allprofit;
	}
	public void setAllprofit(String allprofit) {
		this.allprofit = allprofit;
	}
	
}
