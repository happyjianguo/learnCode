package cn.com.jansh.dao.entity;

public class WxdNewsMaterialDetail {
	private String detailId;
	private String materialId;
	private String wxd_materialId;
	private String materialTitle;
	private String thumbMediaId;
	private String thumbMediaUrl;
	private String detailSort;
	private String author;
	private String diGest;
	private String showCoverpic;
	private String content;
	private String contentSourceUrl;
	private String updateTime;
	private String createTime;
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getWxd_materialId() {
		return wxd_materialId;
	}
	public void setWxd_materialId(String wxd_materialId) {
		this.wxd_materialId = wxd_materialId;
	}
	public String getMaterialTitle() {
		return materialTitle;
	}
	public void setMaterialTitle(String materialTitle) {
		this.materialTitle = materialTitle;
	}
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	public String getThumbMediaUrl() {
		return thumbMediaUrl;
	}
	public void setThumbMediaUrl(String thumbMediaUrl) {
		this.thumbMediaUrl = thumbMediaUrl;
	}
	public String getDetailSort() {
		return detailSort;
	}
	public void setDetailSort(String detailSort) {
		this.detailSort = detailSort;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDiGest() {
		return diGest;
	}
	public void setDiGest(String diGest) {
		this.diGest = diGest;
	}
	public String getShowCoverpic() {
		return showCoverpic;
	}
	public void setShowCoverpic(String showCoverpic) {
		this.showCoverpic = showCoverpic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentSourceUrl() {
		return contentSourceUrl;
	}
	public void setContentSourceUrl(String contentSourceUrl) {
		this.contentSourceUrl = contentSourceUrl;
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
	@Override
	public String toString() {
		return "WxdNewsMaterialDetail [detailId=" + detailId + ", materialId=" + materialId + ", wxd_materialId="
				+ wxd_materialId + ", materialTitle=" + materialTitle + ", thumbMediaId=" + thumbMediaId
				+ ", thumbMediaUrl=" + thumbMediaUrl + ", detailSort=" + detailSort + ", author=" + author + ", diGest="
				+ diGest + ", showCoverpic=" + showCoverpic + ", content=" + content + ", contentSourceUrl="
				+ contentSourceUrl + ", updateTime=" + updateTime + ", createTime=" + createTime + "]";
	}
}
