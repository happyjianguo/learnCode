//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.pay.system.dept.struts.form;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 9-16-2009
 * 
 * XDoclet definition:
 * @struts.form name="deptForm"
 */
public class DeptForm extends ActionForm {
    private static final long serialVersionUID = 1L;
    private String deptno = "";
    private String deptname = "";
    private String deptdesc = "";
    private String dept_upperno = "";
	private String dept_level = "";
	private String prov_id = "";
	private String prov_name = "";
	private String area_code = "";
	private String area_no = "";//所属城市银行内部ID
	private String area_name = "";
	private String transacct="";
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

    public String getDeptdesc() {
        return deptdesc;
    }

    public void setDeptdesc(String deptdesc) {
        this.deptdesc = deptdesc.trim();
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname.trim();
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno.trim();
    }

	public String getDept_level() {
		return dept_level;
	}

	public void setDept_level(String dept_level) {
		this.dept_level = dept_level.trim();
	}

	public String getDept_upperno() {
		return dept_upperno;
	}

	public void setDept_upperno(String dept_upperno) {
		this.dept_upperno = dept_upperno.trim();
	}

	public String getProv_id() {
		return prov_id;
	}

	public void setProv_id(String prov_id) {
		this.prov_id = prov_id.trim();
	}

	public String getProv_name() {
		return prov_name;
	}

	public void setProv_name(String prov_name) {
		this.prov_name = prov_name.trim();
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code.trim();
	}

	public String getArea_no() {
		return area_no;
	}

	public void setArea_no(String area_no) {
		this.area_no = area_no.trim();
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name.trim();
	}

	public String getTransacct() {
		return transacct;
	}

	public void setTransacct(String transacct) {
		this.transacct = transacct.trim();
	}
	
}
