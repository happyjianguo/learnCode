package com.pay.system.role.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.StringUtils;

/**
 * 
 * @TODO ÃèÊö½ÇÉ«ÐÅÏ¢
 *
 * @author »Æ±ó
 * @created on 2007-12-20  10:37:04
 * @version 1.0
 */
public class RoleBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String roleno = "";
	private String rolename = "";
	private String roledesc = "";
	
	public RoleBean(){
		
	}
	
    public RoleBean(HashMap record){
        if(record.get("role_no") == null){
            this.setRoleno("");
        }
        else{
            this.setRoleno(StringUtils.innerToOuter((String)record.get("role_no")).trim());
        }
        if(record.get("role_name") == null){
            this.setRolename("");
        }
        else{
            this.setRolename(StringUtils.innerToOuter((String)record.get("role_name")).trim());
        }
        if(record.get("role_desc") == null){
            this.setRoledesc("");
        }
        else{
            this.setRoledesc(StringUtils.innerToOuter((String)record.get("role_desc")).trim());
        }
	}

	public String getRoledesc() {
		return roledesc;
	}

	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc.trim();
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename.trim();
	}

	public String getRoleno() {
		return roleno;
	}

	public void setRoleno(String roleno) {
		this.roleno = roleno.trim();
	}

    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append(roleno);
        sb.append("|");
        sb.append(rolename);
        sb.append("|");
        sb.append(roledesc);
        sb.append("|");

        return new String(sb);
    }
}
