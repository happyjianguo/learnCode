package cn.com.jansh.dao.entity;

public class WxdNewsMaterial {

	private String materialId;
	private String materialName;
	private String mediaId;
	private String mediaidStatus;
	private String updateTime;
	private String createTime;
	private String appid;
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getMediaidStatus() {
		return mediaidStatus;
	}
	public void setMediaidStatus(String mediaidStatus) {
		this.mediaidStatus = mediaidStatus;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	@Override
	public String toString() {
		return "WxdNewsMaterial [materialId=" + materialId + ", materialName=" + materialName + ", mediaId=" + mediaId
				+ ", mediaidStatus=" + mediaidStatus + ", updateTime=" + updateTime + ", createTime=" + createTime
				+ ", appid=" + appid + "]";
	}
}
