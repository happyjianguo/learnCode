package cn.com.jansh.entity.weixin;

/**
 * 
 * @author panc 微信媒体素材表
 * @param <MultipartFile>
 */
public class MediaMaterial<MultipartFile> {
	// 素材编号
	private String materialId;
	// 素材名称
	private String materialName;
	// 微信返回的MEDIAID
	private String mediaId;
	// 文件名称
	private String fileName;
	// 文件大小
	private String fileLength;
	// 文件路径
	private String filePath;
	// 文件类型
	private String type;
	// 素材标题
	private String title;
	// 素材描述
	private String introduction;
	// 素材URL
	private String mediaUrl;
	// 任务状态
	private String taskStatus;
	// 时间
	private String createTime;
	//更新时间
	private String updateTime;
	// 上传的文件
	private MultipartFile picSelect;
	//公众号ID
	private String appid;
	
	private String resflag;
	
	private String resMsg;

	
	
	@Override
	public String toString() {
		return "MediaMaterial [materialId=" + materialId + ", materialName=" + materialName + ", mediaId=" + mediaId
				+ ", fileName=" + fileName + ", fileLength=" + fileLength + ", filePath=" + filePath + ", type=" + type
				+ ", title=" + title + ", introduction=" + introduction + ", mediaUrl=" + mediaUrl + ", taskStatus="
				+ taskStatus + ", createTime=" + createTime + ", updateTime=" + updateTime + ", picSelect=" + picSelect
				+ ", appid=" + appid + ", resflag=" + resflag + ", resMsg=" + resMsg + "]";
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public String getResflag() {
		return resflag;
	}

	public void setResflag(String resflag) {
		this.resflag = resflag;
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
	
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileLength() {
		return fileLength;
	}

	public void setFileLength(String fileLength) {
		this.fileLength = fileLength;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public MultipartFile getPicSelect() {
		return picSelect;
	}

	public void setPicSelect(MultipartFile picSelect) {
		this.picSelect = picSelect;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}
	
}
