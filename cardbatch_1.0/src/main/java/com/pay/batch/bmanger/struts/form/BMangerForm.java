//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.pay.batch.bmanger.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
 
/** 
 * MyEclipse Struts
 * Creation date: 12-21-2007
 * 
 * XDoclet definition:
 * @struts.form name="roleForm"
 */
public class BMangerForm extends ActionForm {
    private static final long serialVersionUID = 1L;
    private String id;
	 private String batchflag;
	 private String batchname;
	 private String batchfile;
	 private String batchflagfile;
	 private String addtime;
	 private String demo;
	 private String backuppath;
	 private String logfilepath;
	 private String dept_no;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBatchflag() {
		return batchflag;
	}

	public void setBatchflag(String batchflag) {
		this.batchflag = batchflag;
	}

	public String getBatchname() {
		return batchname;
	}

	public void setBatchname(String batchname) {
		this.batchname = batchname;
	}

	public String getBatchfile() {
		return batchfile;
	}

	public void setBatchfile(String batchfile) {
		this.batchfile = batchfile;
	}

	public String getBatchflagfile() {
		return batchflagfile;
	}

	public void setBatchflagfile(String batchflagfile) {
		this.batchflagfile = batchflagfile;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}

	public String getBackuppath() {
		return backuppath;
	}

	public void setBackuppath(String backuppath) {
		this.backuppath = backuppath;
	}

	public String getLogfilepath() {
		return logfilepath;
	}

	public void setLogfilepath(String logfilepath) {
		this.logfilepath = logfilepath;
	}

	public String getDept_no() {
		return dept_no;
	}

	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}

    
}
