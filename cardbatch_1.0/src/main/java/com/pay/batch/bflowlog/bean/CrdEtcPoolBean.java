package com.pay.batch.bflowlog.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.StringUtils;


public class CrdEtcPoolBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String verno_ctx;
	private String id;
	private String etc_pan;
	private String flag;
	private String crdbatch_batch;
	private String batchtask_ticket_no;
	
	private String insertdate; 
	private String inserttime; 
	
	private String updatedate;
	private String updatetime;

	//²éÑ¯Ìõ¼þ
	private String start_etc_pan;
	private String end_etc_pan;
	
	public CrdEtcPoolBean() {

	}

	public String getVerno_ctx() {
		return verno_ctx;
	}

	public void setVerno_ctx(String verno_ctx) {
		this.verno_ctx = verno_ctx;
	}
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getEtc_pan() {
		return etc_pan;
	}



	public void setEtc_pan(String etc_pan) {
		this.etc_pan = etc_pan;
	}



	public String getInsertdate() {
		return insertdate;
	}



	public void setInsertdate(String insertdate) {
		this.insertdate = insertdate;
	}



	public String getInserttime() {
		return inserttime;
	}



	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}



	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCrdbatch_batch() {
		return crdbatch_batch;
	}

	public void setCrdbatch_batch(String crdbatch_batch) {
		this.crdbatch_batch = crdbatch_batch;
	}

	public String getBatchtask_ticket_no() {
		return batchtask_ticket_no;
	}

	public void setBatchtask_ticket_no(String batchtask_ticket_no) {
		this.batchtask_ticket_no = batchtask_ticket_no;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	
	
	public String getStart_etc_pan() {
		return start_etc_pan;
	}

	public void setStart_etc_pan(String start_etc_pan) {
		this.start_etc_pan = start_etc_pan;
	}

	public String getEnd_etc_pan() {
		return end_etc_pan;
	}

	public void setEnd_etc_pan(String end_etc_pan) {
		this.end_etc_pan = end_etc_pan;
	}

	public CrdEtcPoolBean(HashMap record) {
		if (record.get("verno_ctx") == null) {
			this.setVerno_ctx("");
		} else {
			this.setVerno_ctx(StringUtils.innerToOuter((String) record.get("verno_ctx"))
					.trim());
		}
		if (record.get("id") == null) {
			this.setId("");
		} else {
			this.setId(StringUtils.innerToOuter((String) record.get("id"))
					.trim());
		}
		if (record.get("etc_pan") == null) {
			this.setEtc_pan("");
		} else {
			this.setEtc_pan(StringUtils.innerToOuter((String) record.get("etc_pan"))
					.trim());
		}
		if (record.get("flag") == null) {
			this.setFlag("");
		} else {
			this.setFlag(StringUtils.innerToOuter((String) record.get("flag"))
					.trim());
		}
		if (record.get("crdbatch_batch") == null) {
			this.setCrdbatch_batch("");
		} else {
			this.setCrdbatch_batch(StringUtils.innerToOuter((String) record.get("crdbatch_batch"))
					.trim());
		}
		if (record.get("batchtask_ticket_no") == null) {
			this.setBatchtask_ticket_no("");
		} else {
			this.setBatchtask_ticket_no(StringUtils.innerToOuter((String) record.get("batchtask_ticket_no"))
					.trim());
		}
		if (record.get("insertdate") == null) {
			this.setInsertdate("");
		} else {
			this.setInsertdate(StringUtils.innerToOuter((String) record.get("insertdate"))
					.trim());
		}
		if (record.get("inserttime") == null) {
			this.setInserttime("");
		} else {
			this.setInserttime(StringUtils.innerToOuter((String) record.get("inserttime"))
					.trim());
		}
		if (record.get("updatedate") == null) {
			this.setUpdatedate("");
		} else {
			this.setUpdatedate(StringUtils.innerToOuter((String) record.get("updatedate"))
					.trim());
		}
		if (record.get("updatetime") == null) {
			this.setUpdatetime("");
		} else {
			this.setUpdatetime(StringUtils.innerToOuter((String) record.get("updatetime"))
					.trim());
		}
	}
	
	
	
	
}
