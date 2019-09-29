package cn.com.jansh.entity.weixin;
/**
 * 用户分组
 * @author panc
 *
 */
public class WXBGroup {
	private String groupId; //组ID
	private String groupName; //组名称
	private String count; //组员数量
	private String appid; //微信平台ID
	private String updateTime; //更新时间
	private String CreateTime; //创建时间
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	@Override
	public String toString() {
		return "WXBGroup [groupId=" + groupId + ", groupName=" + groupName + ", count=" + count + ", appid=" + appid
				+ ", updateTime=" + updateTime + ", CreateTime=" + CreateTime + "]";
	}
}
