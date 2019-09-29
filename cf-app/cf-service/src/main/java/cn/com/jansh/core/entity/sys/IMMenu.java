package cn.com.jansh.core.entity.sys;

import java.io.Serializable;

public class IMMenu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1781860097247731238L;

	private String menuid;

	private String enname;

	private String cnname;

	private String menuurl;

	private String parentid;

	private String menuorder;

	private String menulevel;
	
	private String icon;

	private String menustatus;

//	private List<Menu> child;
//
//	private Collection<ConfigAttribute> auths;


	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getMenustatus() {
		return menustatus;
	}

	public void setMenustatus(String menustatus) {
		this.menustatus = menustatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "IMMenu [menuid=" + menuid + ", enname=" + enname + ", cnname=" + cnname + ", menuurl=" + menuurl
				+ ", parentid=" + parentid + ", menuorder=" + menuorder + ", menulevel=" + menulevel + ", icon=" + icon
				+ ", menustatus=" + menustatus + "]";
	}

}
