package cn.com.jansh.dao.entity;

public class PathPosition {

	private String currentLocalx;
	private String currentLocaly;
	private String destLocalx;
	private String destLocaly;
	private WXDNetStation netStation;
	
	public WXDNetStation getNetStation() {
		return netStation;
	}
	public void setNetStation(WXDNetStation netStation) {
		this.netStation = netStation;
	}
	public String getCurrentLocalx() {
		return currentLocalx;
	}
	public void setCurrentLocalx(String currentLocalx) {
		this.currentLocalx = currentLocalx;
	}
	public String getCurrentLocaly() {
		return currentLocaly;
	}
	public void setCurrentLocaly(String currentLocaly) {
		this.currentLocaly = currentLocaly;
	}
	public String getDestLocalx() {
		return destLocalx;
	}
	public void setDestLocalx(String destLocalx) {
		this.destLocalx = destLocalx;
	}
	public String getDestLocaly() {
		return destLocaly;
	}
	public void setDestLocaly(String destLocaly) {
		this.destLocaly = destLocaly;
	}
	
}
