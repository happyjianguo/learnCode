package cn.com.jansh.core.menu;

import java.util.Collection;
import java.util.List;

import org.springframework.security.access.ConfigAttribute;

public class MenuAuths {
	
	private String menuid;

	private String enname;

	private String cnname;

	private String menuurl;

	private String parentid;

	private String menuorder;

	private String menulevel;
	
	private String icon;

	private String menustatus;
	
	private String selected;

	private List<MenuAuths> child;

	private Collection<ConfigAttribute> auths;


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


	public List<MenuAuths> getChild() {
		return child;
	}


	public void setChild(List<MenuAuths> child) {
		this.child = child;
	}


	public Collection<ConfigAttribute> getAuths() {
		return auths;
	}


	public void setAuths(Collection<ConfigAttribute> auths) {
		this.auths = auths;
	}


	public String getSelected() {
		return selected;
	}


	public void setSelected(String selected) {
		this.selected = selected;
	}


	@Override
	public String toString() {
		return "MenuAuths [menuid=" + menuid + ", enname=" + enname + ", cnname=" + cnname + ", menuurl=" + menuurl
				+ ", parentid=" + parentid + ", menuorder=" + menuorder + ", menulevel=" + menulevel + ", icon=" + icon
				+ ", menustatus=" + menustatus + ", selected=" + selected + ", child=" + child + ", auths=" + auths
				+ "]";
	}
	
}
