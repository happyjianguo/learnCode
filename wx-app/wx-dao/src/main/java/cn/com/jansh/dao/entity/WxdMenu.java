package cn.com.jansh.dao.entity;

public class WxdMenu {
    private String menuid;

    private String menutype;

    private String menuname;

    private String menukey;

    private String menuurl;

    private String msgid;

    private String parentid;

    private String menuorder;

    private String menulevel;

    private String msgtype;
    
    private String taskstatus;
    
    private String appid;

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getMenukey() {
		return menukey;
	}

	public void setMenukey(String menukey) {
		this.menukey = menukey;
	}

	public String getMenuurl() {
		return menuurl;
	}

	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl;
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

	public String getMenulevel() {
		return menulevel;
	}

	public void setMenulevel(String menulevel) {
		this.menulevel = menulevel;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public String getTaskstatus() {
		return taskstatus;
	}

	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}

	

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	@Override
	public String toString() {
		return "WxdMenu [menuid=" + menuid + ", menutype=" + menutype + ", menuname=" + menuname + ", menukey="
				+ menukey + ", menuurl=" + menuurl + ", msgid=" + msgid + ", parentid=" + parentid + ", menuorder="
				+ menuorder + ", menulevel=" + menulevel + ", msgtype=" + msgtype + ", taskstatus=" + taskstatus
				+ ", appid=" + appid + "]";
	}

    
    
}