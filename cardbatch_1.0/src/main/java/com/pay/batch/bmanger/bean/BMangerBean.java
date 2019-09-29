package com.pay.batch.bmanger.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.StringUtils;

public class BMangerBean implements Serializable{
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
	 private String socketflag;
	 public BMangerBean(){
			
		}
		
	    public BMangerBean(HashMap record){
	       if(record.get("id") == null){
	            this.setId("");
	        }
	        else{
	            this.setId(StringUtils.innerToOuter((String)record.get("id")).trim());
	        }
	       if(record.get("batchflag") == null){
	            this.setBatchflag("");
	        }
	        else{
	            this.setBatchflag(StringUtils.innerToOuter((String)record.get("batchflag")).trim());
	        }
	       if(record.get("batchname") == null){
	            this.setBatchname("");
	        }
	        else{
	            this.setBatchname(StringUtils.innerToOuter((String)record.get("batchname")).trim());
	        }
	       if(record.get("batchfile") == null){
	            this.setBatchfile("");
	        }
	        else{
	            this.setBatchfile(StringUtils.innerToOuter((String)record.get("batchfile")).trim());
	        }
	       if(record.get("batchflagfile") == null){
	            this.setBatchflagfile("");
	        }
	        else{
	            this.setBatchflagfile(StringUtils.innerToOuter((String)record.get("batchflagfile")).trim());
	        }
	       if(record.get("addtime") == null){
	            this.setAddtime("");
	        }
	        else{
	            this.setAddtime(StringUtils.innerToOuter((String)record.get("addtime")).trim());
	        }
	       if(record.get("demo") == null){
	            this.setDemo("");
	        }
	        else{
	            this.setDemo(StringUtils.innerToOuter((String)record.get("demo")).trim());
	        }
	       if(record.get("backuppath") == null){
	            this.setBackuppath("");
	        }
	        else{
	            this.setBackuppath(StringUtils.innerToOuter((String)record.get("backuppath")).trim());
	        }
	       if(record.get("logfilepath") == null){
	            this.setLogfilepath("");
	        }
	        else{
	            this.setLogfilepath(StringUtils.innerToOuter((String)record.get("logfilepath")).trim());
	        }       
	       if(record.get("dept_no") == null){
	            this.setDept_no("");
	        }
	        else{
	            this.setDept_no(StringUtils.innerToOuter((String)record.get("dept_no")).trim());
	        }
	       if(record.get("socketflag") == null){
	            this.setSocketflag("");
	        }
	        else{
	            this.setSocketflag(StringUtils.innerToOuter((String)record.get("socketflag")).trim());
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

	public String getSocketflag() {
		return socketflag;
	}

	public void setSocketflag(String socketflag) {
		this.socketflag = socketflag;
	}
	 
	 
	 
}
