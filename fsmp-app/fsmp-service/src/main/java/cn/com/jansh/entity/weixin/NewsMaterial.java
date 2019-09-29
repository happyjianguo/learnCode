package cn.com.jansh.entity.weixin;

import java.util.List;

/**
 * 素材
 * 
 * @author panc
 *
 */
public class NewsMaterial {
	private String msgId;//消息编号
	private String msgName;//消息编号
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	private String materialId; // 素材编号
	private String materialName; // 素材名称
	private String mediaId; // 微信返回的MEDIAID
	private String mediaidStatus; // 任务状态
	private String createTime; // 创建时间
	private String updateTime; // 更新时间
	private String appid; // 平台ID
	private List<NewsMaterialDetai> detailModels;
	
	private int detailModelsLength;
	
	
	@Override
	public String toString() {
		return "NewsMaterial [msgId=" + msgId + ", msgName=" + msgName + ", materialId=" + materialId
				+ ", materialName=" + materialName + ", mediaId=" + mediaId + ", mediaidStatus=" + mediaidStatus
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", appid=" + appid + ", detailModels="
				+ detailModels + ",  detailModelsLength=" + detailModelsLength + "]";
	}
	
	public int getDetailModelsLength() {
		return detailModelsLength;
	}
	public void setDetailModelsLength(int detailModelsLength) {
		this.detailModelsLength = detailModelsLength;
	}
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public List<NewsMaterialDetai> getDetailModels() {
		return detailModels;
	}
	public void setDetailModels(List<NewsMaterialDetai> detailModels) {
		this.detailModels = detailModels;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
}
