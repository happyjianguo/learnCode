package com.pay.system.user.bean;

import java.io.Serializable;


import java.util.HashMap;

import com.pay.util.StringUtils;

public class UserBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String usercode = "";
    private String username = "";
    private String passwd = "";
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
    private String validdays = "";
    private String isactive = "";
    private String roleno = "";
    private String rolename = "";
    private String deptno = "";
    private String deptname = "";
    private String dept_level = "";
    private String mac="";//0代表绑定，1代表不绑定;


	//用编码与所属用户组编号
    private String usercodeAndRoleno = "";

    public UserBean() {

    }

    public UserBean(HashMap record) {
    	if (record.get("user_mac") == null) {
            this.setMac("");
        } else {
            this.setMac(StringUtils.innerToOuter((String) record.get("user_mac")).trim());
        }
        if (record.get("user_code") == null) {
            this.setUsercode("");
        } else {
            this.setUsercode(StringUtils.innerToOuter((String) record.get("user_code")).trim());
        }
        if (record.get("user_name") == null) {
            this.setUsername("");
        } else {
            this.setUsername(StringUtils.innerToOuter((String) record.get("user_name")).trim());
        }
        if (record.get("user_passwd") == null) {
            this.setPasswd("");
        } else {
            this.setPasswd(StringUtils.innerToOuter((String) record.get("user_passwd")).trim());
        }
        if (record.get("teamno") == null) {
            this.setTeamno("");
        } else {
            this.setTeamno(StringUtils.innerToOuter((String) record.get("teamno")).trim());
        }
        if (record.get("teamname") == null) {
            this.setTeamname("");
        } else {
            this.setTeamname(StringUtils.innerToOuter((String) record.get("teamname")).trim());
        }
        if (record.get("user_address") == null) {
            this.setAddress("");
        } else {
            this.setAddress(StringUtils.innerToOuter((String) record.get("user_address")).trim());
        }
        if (record.get("user_postalcode") == null) {
            this.setPostalcode("");
        } else {
            this.setPostalcode(StringUtils.innerToOuter((String) record.get("user_postalcode")).trim());
        }
        if (record.get("user_mail") == null) {
            this.setMail("");
        } else {
            this.setMail(StringUtils.innerToOuter((String) record.get("user_mail")).trim());
        }
        if (record.get("user_fax") == null) {
            this.setFax("");
        } else {
            this.setFax(StringUtils.innerToOuter((String) record.get("user_fax")).trim());
        }
        if (record.get("user_phone") == null) {
            this.setPhone("");
        } else {
            this.setPhone(StringUtils.innerToOuter((String) record.get("user_phone")).trim());
        }
        if (record.get("user_loginflag") == null) {
            this.setLoginflag("");
        } else {
            this.setLoginflag(StringUtils.innerToOuter((String) record.get("user_loginflag")).trim());
        }
        if (record.get("user_lastintime") == null) {
            this.setLastintime("");
        } else {
            this.setLastintime(StringUtils.innerToOuter((String) record.get("user_lastintime")).trim());
        }
        if (record.get("user_pwdinvldday") == null) {
            this.setPasswddays("");
        } else {
            this.setPasswddays(StringUtils.innerToOuter((String) record.get("user_pwdinvldday")).trim());
        }
        if (record.get("user_validdays") == null) {
            this.setValiddays("");
        } else {
            this.setValiddays(StringUtils.innerToOuter((String) record.get("user_validdays")).trim());
        }
        if (record.get("user_isactive") == null) {
            this.setIsactive("");
        } else {
            this.setIsactive(StringUtils.innerToOuter((String) record.get("user_isactive")).trim());
        }
        if (record.get("role_no") == null) {
            this.setRoleno("");
        } else {
            this.setRoleno(StringUtils.innerToOuter((String) record.get("role_no")).trim());
        }
        if (record.get("role_name") == null) {
            this.setRolename("");
        } else {
            this.setRolename(StringUtils.innerToOuter((String) record.get("role_name")).trim());
        }
        if (record.get("dept_no") == null) {
            this.setDeptno("");
        } else {
            this.setDeptno(StringUtils.innerToOuter((String) record.get("dept_no")).trim());
        }
        if (record.get("dept_name") == null) {
            this.setDeptname("");
        } else {
            this.setDeptname(StringUtils.innerToOuter((String) record.get("dept_name")).trim());
        }
        if (record.get("dept_level") == null) {
        	this.setDept_level(dept_level);
        } else {
        	this.setDept_level(StringUtils.innerToOuter((String) record.get("dept_level")).trim());
        }
        this.setUsercodeAndRoleno(this.getUsercode() + "|" + this.getRoleno());
    }

	public String getUsercodeAndRoleno() {
		return usercodeAndRoleno;
	}

	public void setUsercodeAndRoleno(String usercodeAndRoleno) {
		this.usercodeAndRoleno = usercodeAndRoleno.trim();
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive.trim();
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

    public String getValiddays() {
        return validdays;
    }

    public void setValiddays(String validdays) {
        this.validdays = validdays.trim();
    }

    public String getRoleno() {
		return roleno;
	}

	public void setRoleno(String roleno) {
		this.roleno = roleno.trim();
	}
	
    public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename.trim();
	}

    public String getDeptno() {
		return deptno;
	}

	public void setDeptno(String deptno) {
		this.deptno = deptno.trim();
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname.trim();
	}

	public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append(StringUtils.outerToInner(usercode));
        sb.append(StringUtils.outerToInner(username));
        sb.append(StringUtils.outerToInner(passwd));
        sb.append(StringUtils.outerToInner(teamno));
        sb.append(StringUtils.outerToInner(teamname));
        sb.append(StringUtils.outerToInner(address));
        sb.append(StringUtils.outerToInner(postalcode));
        sb.append(StringUtils.outerToInner(mail));
        sb.append(StringUtils.outerToInner(fax));
        sb.append(StringUtils.outerToInner(phone));
        sb.append(StringUtils.outerToInner(loginflag));
        sb.append(StringUtils.outerToInner(lastintime));
        sb.append(StringUtils.outerToInner(passwddays));
        sb.append(StringUtils.outerToInner(validdays));
        sb.append(StringUtils.outerToInner(roleno));
        sb.append(StringUtils.outerToInner(rolename));
        sb.append(StringUtils.outerToInner(isactive));
        sb.append(StringUtils.outerToInner(deptno));
        sb.append(StringUtils.outerToInner(deptname));
        sb.append(StringUtils.outerToInner(dept_level));

        return new String(sb);
    }

	public String getDept_level() {
		return dept_level;
	}

	public void setDept_level(String dept_level) {
		this.dept_level = dept_level.trim();
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac.trim();
	}

}
