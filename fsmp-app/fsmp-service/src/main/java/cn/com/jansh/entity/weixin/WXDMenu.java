package cn.com.jansh.entity.weixin;

import java.util.List;
/**
 * 自定义菜单实体类
 * 
 * @author panc
 *
 */
public class WXDMenu {
	private String menuid; // 菜单编号
	private String menuType; // 菜单的响应动作类型
	private String menuName; // 菜单标题
	private String menuKey; // 菜单KEY值
	private String menuUrl; // 菜单URL
	private String msgId; // 素材ID,
	private String msgType;
	private String parentid; // 上级菜单编号 一级菜单默认0
	private String menuorder; // 菜单顺序
	private String menuLevel; // 菜单级别
	private String menuStatus; // 任务状态
	private String appid;
	private List<WXDMenu> child;

	
	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}


	public String getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMenuStatus() {
		return menuStatus;
	}

	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public List<WXDMenu> getChild() {
		return child;
	}

	public void setChild(List<WXDMenu> child) {
		this.child = child;
	}
	
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getMenuorder() {
		return menuorder;
	}

	public void setMenuorder(String menuorder) {
		this.menuorder = menuorder;
	}

	@Override
	public String toString() {
		return "WXDMenuModel [menuid=" + menuid + ", menuType=" + menuType + ", menuName=" + menuName + ", menuKey="
				+ menuKey + ", menuUrl=" + menuUrl + ", msgId=" + msgId + ", msgType=" + msgType + ", parentid="
				+ parentid + ", menuorder=" + menuorder + ", menuLevel=" + menuLevel + ", menuStatus=" + menuStatus
				+ ", appid=" + appid + ", child=" + child + "]";
	}

/*	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("menuid:");
		sb.append(menuid);
		sb.append(",parentid:");
		sb.append(parentid);
		sb.append(",page:");
		sb.append(menuName);
		sb.append(",menuName:");
		sb.append(menuorder);
		sb.append(",menuorder:");
		sb.append(child == null? "null":child);
		sb.append("}\n");
		return sb.toString();
	}*/
	
	
}
