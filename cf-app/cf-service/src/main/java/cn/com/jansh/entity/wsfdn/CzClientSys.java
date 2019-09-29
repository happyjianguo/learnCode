package cn.com.jansh.entity.wsfdn;

public class CzClientSys {
	
	
	private String sysid;
	private String syskey;
	private String remark;
	private String status;
	
	public String getSysid() {
		return sysid;
	}
	public void setSysid(String sysid) {
		this.sysid = sysid;
	}
	public String getSyskey() {
		return syskey;
	}
	public void setSyskey(String syskey) {
		this.syskey = syskey;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "CzClientSys [sysid=" + sysid + ", syskey=" + syskey + ", remark=" + remark + ", status=" + status + "]";
	}
}
