package com.pay.system.menu.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pay.util.StringUtils;

/**
 * 
 * @TODO 描述用户功能信息 
 *
 * @author 黄斌
 * @created on 2007-12-20  10:35:07
 * @version 1.0
 */
public class MenuBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String menuno = "";
    private String menuname = "";
    private String menudesc = "";
    private String menu_level = "";
    private String menuparent="";
    private String menupath="";    
    private String usercode="";
    private List lst=new ArrayList();
    

    public MenuBean() {

    }

    public MenuBean(HashMap record) {
        if (record.get("menu_no") == null) {
            this.setMenuno("");
        } else {
            this.setMenuno(StringUtils.innerToOuter(((String) record.get("menu_no")).trim()));
        }
        if (record.get("menu_name") == null) {
            this.setMenuname("");
        } else {
            this.setMenuname(StringUtils.innerToOuter(((String) record.get("menu_name")).trim()));
        }
        if (record.get("menu_desc") == null) {
            this.setMenudesc("");
        } else {
            this.setMenudesc(StringUtils.innerToOuter(((String) record.get("menu_desc")).trim()));
        }
        if (record.get("menu_level") == null) {
        	this.setMenu_level("");
        } else {
        	this.setMenu_level(StringUtils.innerToOuter(((String) record.get("menu_level")).trim()));
        }
        if (record.get("menu_parentno") == null) {
        	this.setMenuparent("");
        } else {
        	this.setMenuparent(StringUtils.innerToOuter(((String) record.get("menu_parentno")).trim()));
        }
        if (record.get("menu_path") == null) {
        	this.setMenupath("");
        } else {
        	this.setMenupath(StringUtils.innerToOuter(((String) record.get("menu_path")).trim()));
        }
        if (record.get("user_code") == null) {
        	this.setUsercode("");
        } else {
        	this.setUsercode(StringUtils.innerToOuter(((String) record.get("user_code")).trim()));
        }
    }

    public String getMenuno() {
		return menuno;
	}

	public void setMenuno(String menuno) {
		this.menuno = menuno.trim();
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname.trim();
	}

	public String getMenudesc() {
		return menudesc;
	}

	public void setMenudesc(String menudesc) {
		this.menudesc = menudesc.trim();
	}

	public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append(StringUtils.outerToInner(menuno));
        sb.append("|");
        sb.append(StringUtils.outerToInner(menuname));
        sb.append("|");
        sb.append(StringUtils.outerToInner(menudesc));
        sb.append("|");
        sb.append(StringUtils.outerToInner(menu_level));
        sb.append("|");

        return new String(sb);
    }

	public String getMenu_level() {
		return menu_level;
	}

	public void setMenu_level(String menu_level) {
		this.menu_level = menu_level.trim();
	}

	public String getMenuparent() {
		return menuparent;
	}

	public void setMenuparent(String menuparent) {
		this.menuparent = menuparent.trim();
	}

	public String getMenupath() {
		return menupath;
	}

	public void setMenupath(String menupath) {
		this.menupath = menupath.trim();
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode.trim();
	}

	public List getLst() {
		return lst;
	}

	public void setLst(List list) {
		if(list!=null&&!list.isEmpty()) {
			for(int i=0;i<list.size();i++){
				this.lst.add(list.get(i));
			}		
		}
	}

	
	
}
