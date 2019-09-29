package cn.com.jansh.entity.weixin;
/**
 * 素材明细
 * @author panc
 *
 */
public class NewsMaterialDetai {
	@Override
	public String toString() {
		return "NewsMaterialDetailModel [detailId=" + detailId + ", materialId=" + materialId + ", wxd_materialId="
				+ wxd_materialId + ", materialTitle=" + materialTitle + ", thumbMediaId=" + thumbMediaId
				+ ", thumbMediaUrl=" + thumbMediaUrl + ", detailSort=" + detailSort + ", author=" + author + ", digest="
				+ digest + ", showCoverPic=" + showCoverPic + ", content=" + content + ", contentSourceUrl="
				+ contentSourceUrl + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

	private String detailId; // 明细编号
	private String materialId; // 素材编号
	private String wxd_materialId;
	private String materialTitle; // 图文标题
	private String thumbMediaId; // 封面图片MEDIAID
	private String thumbMediaUrl;
	private String detailSort;
	private String author; // 作者
	private String digest; // 图文消息的摘要
	private String showCoverPic; // 是否显示封面
	private String content; // 图文消息的具体内容
	private String contentSourceUrl; // 图文消息的原文地址
	private String createTime; // 创建时间
	private String updateTime;	//更新时间
	
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getShowCoverPic() {
		return showCoverPic;
	}

	public void setShowCoverPic(String showCoverPic) {
		this.showCoverPic = showCoverPic;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public String getWxd_materialId() {
		return wxd_materialId;
	}

	public void setWxd_materialId(String wxd_materialId) {
		this.wxd_materialId = wxd_materialId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
