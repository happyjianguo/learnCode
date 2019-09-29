//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.pay.batch.bflowlog.struts.form;

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
public class SixBFlowLogForm extends ActionForm {
    private static final long serialVersionUID = 1L;
    private String id;
	 private String batchflag;
	 private String batchname;
	 private String execdate;
	 private String issucess;
	 private String execinfo;
	 private String addtime;
	 private String srcstartflag;
	 private String srcendflag;
	 private String descstartflag;
	 private String descendflag;
	 private String srcstartflagname;
	 private String srcendflagname;
	 private String descstartflagname;
	 private String descendflagname;
	 private String dept_no;
	 private String execflag;
	 private String editdate;
	 
	 private String execdateday;  //执行日期
	 private String execdatemin;  //执行时间
	 private String editdateday;  //手动执行日期
	 
	 
	 private String batchfile;
	 private String batchflagfile;	 
	 private String demo;
	 private String backuppath;
	 private String logfilepath;
	 private String socketflag;
	 private String datasource;
	 private String panflagno;
	 private String flagnum;   //是否包含4
	 private String passnum;   //是否添加密码处理
	 private String yeardate; //卡有效期
	 private int countFlagEqZero;//未制卡卡数量
	 
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

	public String getExecdate() {
		return execdate;
	}

	public void setExecdate(String execdate) {
		this.execdate = execdate;
	}

	public String getIssucess() {
		return issucess;
	}

	public void setIssucess(String issucess) {
		this.issucess = issucess;
	}

	public String getExecinfo() {
		return execinfo;
	}

	public void setExecinfo(String execinfo) {
		this.execinfo = execinfo;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getSrcstartflag() {
		return srcstartflag;
	}

	public void setSrcstartflag(String srcstartflag) {
		this.srcstartflag = srcstartflag;
	}

	public String getSrcendflag() {
		return srcendflag;
	}

	public void setSrcendflag(String srcendflag) {
		this.srcendflag = srcendflag;
	}

	public String getDescstartflag() {
		return descstartflag;
	}

	public void setDescstartflag(String descstartflag) {
		this.descstartflag = descstartflag;
	}

	public String getDescendflag() {
		return descendflag;
	}

	public void setDescendflag(String descendflag) {
		this.descendflag = descendflag;
	}

	public String getDept_no() {
		return dept_no;
	}

	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}

	public String getBatchname() {
		return batchname;
	}

	public void setBatchname(String batchname) {
		this.batchname = batchname;
	}

	public String getSrcstartflagname() {
		return srcstartflagname;
	}

	public void setSrcstartflagname(String srcstartflagname) {
		this.srcstartflagname = srcstartflagname;
	}

	public String getSrcendflagname() {
		return srcendflagname;
	}

	public void setSrcendflagname(String srcendflagname) {
		this.srcendflagname = srcendflagname;
	}

	public String getDescstartflagname() {
		return descstartflagname;
	}

	public void setDescstartflagname(String descstartflagname) {
		this.descstartflagname = descstartflagname;
	}

	public String getDescendflagname() {
		return descendflagname;
	}

	public void setDescendflagname(String descendflagname) {
		this.descendflagname = descendflagname;
	}

	public String getExecflag() {
		return execflag;
	}

	public void setExecflag(String execflag) {
		this.execflag = execflag;
	}

	public String getEditdate() {
		return editdate;
	}

	public void setEditdate(String editdate) {
		this.editdate = editdate;
	}

	public String getExecdateday() {
		return execdateday;
	}

	public void setExecdateday(String execdateday) {
		this.execdateday = execdateday;
	}

	public String getExecdatemin() {
		return execdatemin;
	}

	public void setExecdatemin(String execdatemin) {
		this.execdatemin = execdatemin;
	}

	public String getEditdateday() {
		return editdateday;
	}

	public void setEditdateday(String editdateday) {
		this.editdateday = editdateday;
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

	public String getSocketflag() {
		return socketflag;
	}

	public void setSocketflag(String socketflag) {
		this.socketflag = socketflag;
	}

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	public String getPanflagno() {
		return panflagno;
	}

	public void setPanflagno(String panflagno) {
		this.panflagno = panflagno;
	}

	public String getFlagnum() {
		return flagnum;
	}

	public void setFlagnum(String flagnum) {
		this.flagnum = flagnum;
	}

	public String getYeardate() {
		return yeardate;
	}

	public void setYeardate(String yeardate) {
		this.yeardate = yeardate;
	}

	public String getPassnum() {
		return passnum;
	}

	public void setPassnum(String passnum) {
		this.passnum = passnum;
	}

	public int getCountFlagEqZero() {
		return countFlagEqZero;
	}

	public void setCountFlagEqZero(int countFlagEqZero) {
		this.countFlagEqZero = countFlagEqZero;
	}

	
    
}
