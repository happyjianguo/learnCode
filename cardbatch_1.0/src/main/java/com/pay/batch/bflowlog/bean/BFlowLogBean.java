package com.pay.batch.bflowlog.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.StringUtils;

public class BFlowLogBean implements Serializable {
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

	private String execdateday; // 执行日期
	private String execdatemin; // 执行时间
	private String editdateday; // 手动执行日期

	private String batchfile;
	private String batchflagfile;
	private String demo;
	private String backuppath;
	private String logfilepath;
	private String socketflag;
	private String datasource;
	private String panflagno;

	public BFlowLogBean() {

	}

	public String getExecdateday() {
		if (execdate != null && execdate.length() >= 8) {
			execdateday = execdate.substring(0, 8);
		}
		return execdateday;
	}

	public void setExecdateday(String execdateday) {
		this.execdateday = execdateday;
	}

	public String getExecdatemin() {
		if (execdate != null && execdate.length() >= 12) {
			execdatemin = execdate.substring(8, 12);
		}
		return execdatemin;
	}

	public void setExecdatemin(String execdatemin) {
		this.execdatemin = execdatemin;
	}

	public String getEditdateday() {
		if (editdate != null && editdate.length() >= 8) {
			editdateday = editdate.substring(0, 8);
		}
		return editdateday;
	}

	public void setEditdateday(String editdateday) {
		this.editdateday = editdateday;
	}

	public BFlowLogBean(HashMap record) {
		if (record.get("id") == null) {
			this.setId("");
		} else {
			this.setId(StringUtils.innerToOuter((String) record.get("id"))
					.trim());
		}
		if (record.get("batchflag") == null) {
			this.setBatchflag("");
		} else {
			this.setBatchflag(StringUtils.innerToOuter(
					(String) record.get("batchflag")).trim());
		}
		if (record.get("batchname") == null) {
			this.setBatchname("");
		} else {
			this.setBatchname(StringUtils.innerToOuter(
					(String) record.get("batchname")).trim());
		}
		if (record.get("execdate") == null) {
			this.setExecdate("");
		} else {
			this.setExecdate(StringUtils.innerToOuter(
					(String) record.get("execdate")).trim());
		}
		if (record.get("issucess") == null) {
			this.setIssucess("");
		} else {
			this.setIssucess(StringUtils.innerToOuter(
					(String) record.get("issucess")).trim());
		}
		if (record.get("execinfo") == null) {
			this.setExecinfo("");
		} else {
			this.setExecinfo(StringUtils.innerToOuter(
					(String) record.get("execinfo")).trim());
		}
		if (record.get("addtime") == null) {
			this.setAddtime("");
		} else {
			this.setAddtime(StringUtils.innerToOuter(
					(String) record.get("addtime")).trim());
		}
		if (record.get("srcstartflag") == null) {
			this.setSrcstartflag("");
		} else {
			this.setSrcstartflag(StringUtils.innerToOuter(
					(String) record.get("srcstartflag")).trim());
		}
		if (record.get("srcendflag") == null) {
			this.setSrcendflag("");
		} else {
			this.setSrcendflag(StringUtils.innerToOuter(
					(String) record.get("srcendflag")).trim());
		}

		if (record.get("descstartflag") == null) {
			this.setDescstartflag("");
		} else {
			this.setDescstartflag(StringUtils.innerToOuter(
					(String) record.get("descstartflag")).trim());
		}
		if (record.get("descendflag") == null) {
			this.setDescendflag("");
		} else {
			this.setDescendflag(StringUtils.innerToOuter(
					(String) record.get("descendflag")).trim());
		}
		if (record.get("srcstartflagname") == null) {
			this.setSrcstartflagname("");
		} else {
			this.setSrcstartflagname(StringUtils.innerToOuter(
					(String) record.get("srcstartflagname")).trim());
		}
		if (record.get("srcendflagname") == null) {
			this.setSrcendflagname("");
		} else {
			this.setSrcendflagname(StringUtils.innerToOuter(
					(String) record.get("srcendflagname")).trim());
		}

		if (record.get("descstartflagname") == null) {
			this.setDescstartflagname("");
		} else {
			this.setDescstartflagname(StringUtils.innerToOuter(
					(String) record.get("descstartflagname")).trim());
		}
		if (record.get("descendflagname") == null) {
			this.setDescendflagname("");
		} else {
			this.setDescendflagname(StringUtils.innerToOuter(
					(String) record.get("descendflagname")).trim());
		}
		if (record.get("dept_no") == null) {
			this.setDept_no("");
		} else {
			this.setDept_no(StringUtils.innerToOuter(
					(String) record.get("dept_no")).trim());
		}
		if (record.get("execflag") == null) {
			this.setExecflag("");
		} else {
			this.setExecflag(StringUtils.innerToOuter(
					(String) record.get("execflag")).trim());
		}
		if (record.get("editdate") == null) {
			this.setEditdate("");
		} else {
			this.setEditdate(StringUtils.innerToOuter(
					(String) record.get("editdate")).trim());
		}
		if (record.get("batchfile") == null) {
			this.setBatchfile("");
		} else {
			this.setBatchfile(StringUtils.innerToOuter(
					(String) record.get("batchfile")).trim());
		}
		if (record.get("batchflagfile") == null) {
			this.setBatchflagfile("");
		} else {
			this.setBatchflagfile(StringUtils.innerToOuter(
					(String) record.get("batchflagfile")).trim());
		}

		if (record.get("demo") == null) {
			this.setDemo("");
		} else {
			this.setDemo(StringUtils.innerToOuter((String) record.get("demo"))
					.trim());
		}
		if (record.get("backuppath") == null) {
			this.setBackuppath("");
		} else {
			this.setBackuppath(StringUtils.innerToOuter(
					(String) record.get("backuppath")).trim());
		}
		if (record.get("logfilepath") == null) {
			this.setLogfilepath("");
		} else {
			this.setLogfilepath(StringUtils.innerToOuter(
					(String) record.get("logfilepath")).trim());
		}
		if (record.get("socketflag") == null) {
			this.setSocketflag("");
		} else {
			this.setSocketflag(StringUtils.innerToOuter(
					(String) record.get("socketflag")).trim());
		}
		if (record.get("datasource") == null) {
			this.setDatasource("");
		} else {
			this.setDatasource(StringUtils.innerToOuter(
					(String) record.get("datasource")).trim());
		}
		if (record.get("panflagno") == null) {
			this.setPanflagno("");
		} else {
			this.setPanflagno(StringUtils.innerToOuter(
					(String) record.get("panflagno")).trim());
		}
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

	public String getBatchfile() {
		return batchfile;
	}

	public void setBatchfile(String batchfile) {
		this.batchfile = batchfile;
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

}
