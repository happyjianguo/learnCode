//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.pay.system.user.struts.form;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 12-21-2007
 * 
 * XDoclet definition:
 * @struts.form name="itemForm"
 */
public class UserForm extends ActionForm {
    private static final long serialVersionUID = 1L;
    //查询条件字段
    private String usercodeQ = "";
    private String teamnoQ = "";
    private String rolenoQ = "";
    private String deptnoQ = "";
    
	//基本字段
    private String usercode = "";
    private String username = "";
    private String passwd = "";
	private String passwd2 = "";
    private String teamno = "";
    private String teamname = "";
    private String address = "";
    private String postalcode = "";
    private String mail = "";
    private String fax = "";
    private String phone = "";
    private String loginflag = "";
    private String lastintime = "";
    private String passwddays = "";
    private String validdays = "";//密码有效天数
    private String roleno = "";
    private String rolename = "";
    private String isactive = "";
    private String deptno = "";
    private String deptname = "";
    private String mac="";//Mac码

    // --------------------------------------------------------- Methods

    /** 
     * Method validate
     * @param mapping
     * @param request
     * @return ActionErrors
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

        // TODO Auto-generated method stub
        return null;
    }

    /** 
     * Method reset
     * @param mapping
     * @param request
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {

        // TODO Auto-generated method stub
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax.trim();
    }

    public String getLastintime() {
        return lastintime;
    }

    public void setLastintime(String lastintime) {
        this.lastintime = lastintime.trim();
    }

    public String getLoginflag() {
        return loginflag;
    }

    public void setLoginflag(String loginflag) {
        this.loginflag = loginflag.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone.trim();
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode.trim();
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname.trim();
    }

    public String getTeamno() {
        return teamno;
    }

    public void setTeamno(String teamno) {
        this.teamno = teamno.trim();
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.trim();
    }

    public String getPasswddays() {
        return passwddays;
    }

    public void setPasswddays(String passwddays) {
        this.passwddays = passwddays.trim();
    }

    public String getPasswd2() {
        return passwd2;
    }

    public void setPasswd2(String passwd2) {
        this.passwd2 = passwd2.trim();
    }
    
    public String getValiddays() {
        return validdays;
    }

    public void setValiddays(String validdays) {
        this.validdays = validdays.trim();
    }

    public String getTeamnoQ() {
        return teamnoQ;
    }

    public void setTeamnoQ(String teamnoQ) {
        this.teamnoQ = teamnoQ.trim();
    }

    public String getUsercodeQ() {
        return usercodeQ;
    }

    public String getRoleno() {
		return roleno;
	}

	public void setRoleno(String roleno) {
		this.roleno = roleno;
	}

    public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public void setUsercodeQ(String usercodeQ) {
        this.usercodeQ = usercodeQ;
    }
    
    public String getRolenoQ() {
		return rolenoQ;
	}

	public void setRolenoQ(String rolenoQ) {
		this.rolenoQ = rolenoQ;
	}

	public String getDeptno() {
		return deptno;
	}

	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getDeptnoQ() {
		return deptnoQ;
	}

	public void setDeptnoQ(String deptnoQ) {
		this.deptnoQ = deptnoQ;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}


}
