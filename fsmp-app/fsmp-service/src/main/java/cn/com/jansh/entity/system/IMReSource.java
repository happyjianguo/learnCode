package cn.com.jansh.entity.system;
/**
 * @author Mr.wong
 * 
 */
public class IMReSource {
	/**
	 *资源id
	 */
	private String resourceId;
	/**
	 *中文名
	 */
	private String cnName;
	/**
	 *英文名
	 */
	private String enName;
	/**
	 *资源关键词
	 */
	private String resourceKey;
	
	private String parentid;
	/**
	 *标签
	 */
	private String remark;
	/**
	 *状态
	 */
	private String status;
	
	
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getResourceKey() {
		return resourceKey;
	}
	public void setResourceKey(String resourceKey) {
		this.resourceKey = resourceKey;
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
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
}
