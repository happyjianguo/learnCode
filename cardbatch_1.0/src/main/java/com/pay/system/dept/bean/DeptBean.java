package com.pay.system.dept.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.StringUtils;

/**
 * 
 * @TODO 描述用户组信息
 *
 * @author zl
 * @created on 2009-9-16
 * @version 1.0
 */
public class DeptBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String deptno = "";
	private String deptname = "";
	private String deptdesc = "";
	private String dept_upperno = "";
	private String dept_level = "";
	private String prov_id = "";
	private String prov_name = "";
	private String area_code = "";//地区编号关联
	private String area_no = "";//所属城市银行内部ID
	
	private String area_name = "";
	private String result = "";
	private String transacct="";
	
	public DeptBean(){
		
	}
	
    public DeptBean(HashMap record){
        if(record.get("dept_no") == null){
            this.setDeptno("");
        }
        else{
            this.setDeptno(StringUtils.innerToOuter((String)record.get("dept_no")).trim());
        }
        if(record.get("dept_name") == null){
            this.setDeptname("");
        }
        else{
            this.setDeptname(StringUtils.innerToOuter((String)record.get("dept_name")).trim());
        }
        if(record.get("dept_desc") == null){
            this.setDeptdesc("");
        }
        else{
            this.setDeptdesc(StringUtils.innerToOuter((String)record.get("dept_desc")).trim());
        }
        if(record.get("dept_upperno") == null){
        	this.setDept_upperno("");
        }
        else{
        	this.setDept_upperno(StringUtils.innerToOuter((String)record.get("dept_upperno")).trim());
        }
        if(record.get("dept_level") == null){
        	this.setDept_level("");
        }
        else{
        	this.setDept_level(StringUtils.innerToOuter((String)record.get("dept_level")).trim());
        }
        if(record.get("prov_id") == null){
        	this.setProv_id("");
        }
        else{
        	this.setProv_id(StringUtils.innerToOuter((String)record.get("prov_id")).trim());
        }
        if(record.get("prov_name") == null){
        	this.setProv_name("");
        }
        else{
        	this.setProv_name(StringUtils.innerToOuter((String)record.get("prov_name")).trim());
        }
        if(record.get("area_code") == null){
        	this.setArea_code("");
        }
        else{
        	this.setArea_code(StringUtils.innerToOuter((String)record.get("area_code")).trim());
        }
		
		if (record.get("area_no") == null) {
			this.setArea_no("");
		} else {
			this.setArea_no(StringUtils.innerToOuter((String) record.get("area_no")).trim());
		}
        if(record.get("area_name") == null){
        	this.setArea_name("");
        }
        else{
        	this.setArea_name(StringUtils.innerToOuter((String)record.get("area_name")).trim());
        }
        if(record.get("trans_acct") == null){
            this.setTransacct("");
        }
        else{
            this.setTransacct(StringUtils.innerToOuter((String)record.get("trans_acct")).trim());
        }
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

	public String getDeptdesc() {
		return deptdesc;
	}

	public void setDeptdesc(String deptdesc) {
		this.deptdesc = deptdesc.trim();
	}

	public String getDept_upperno() {
		return dept_upperno;
	}

	public void setDept_upperno(String dept_upperno) {
		this.dept_upperno = dept_upperno.trim();
	}

	public String getDept_level() {
		return dept_level;
	}

	public void setDept_level(String dept_level) {
		this.dept_level = dept_level.trim();
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result.trim();
	}

	public String getTransacct() {
		return transacct;
	}

	public void setTransacct(String transacct) {
		this.transacct = transacct.trim();
	}

	public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append(deptno);
        sb.append("|");
        sb.append(deptname);
        sb.append("|");
        sb.append(deptdesc);
        sb.append("|");
        sb.append(dept_upperno);
        sb.append("|");
        sb.append(dept_level);
        sb.append("|");
        sb.append(prov_id);
        sb.append("|");
        sb.append(prov_name);
        sb.append("|");
        sb.append(area_code);
        sb.append("|");
        sb.append(area_no);
        sb.append("|");
        sb.append(area_name);
        sb.append("|");

        return new String(sb);
    }
}
