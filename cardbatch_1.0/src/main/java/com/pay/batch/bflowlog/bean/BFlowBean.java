package com.pay.batch.bflowlog.bean;

import java.io.Serializable;
import java.util.HashMap;
import com.pay.util.StringUtils;

public class BFlowBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String panflagno;
	private String batchflag;
	private String sendbuf;
	
	public BFlowBean(){
		
	}
	public BFlowBean(HashMap record) throws Exception {
		if (record.get("panflagno") == null) {
			this.setPanflagno("");
		} else {
			this.setPanflagno(StringUtils.innerToOuter((String) record.get("panflagno"))
					.trim());
		}
		if (record.get("batchflag") == null) {
			this.setBatchflag("");
		} else {
			this.setBatchflag(StringUtils.innerToOuter((String) record.get("batchflag"))
					.trim());
		}		
		if (record.get("sendbuf") == null) {
			this.setSendbuf("");
		} else {
			this.setSendbuf(StringUtils.innerToOuter((String) record.get("sendbuf"))
					.trim());
		}		
		
	}
	
	public String getPanflagno() {
		return panflagno;
	}
	public void setPanflagno(String panflagno) {
		this.panflagno = panflagno;
	}
	public String getBatchflag() {
		return batchflag;
	}
	public void setBatchflag(String batchflag) {
		this.batchflag = batchflag;
	}
	public String getSendbuf() {
		return sendbuf;
	}
	public void setSendbuf(String sendbuf) {
		this.sendbuf = sendbuf;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
