package com.pay.batch.bflowlog.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.StringUtils;

public class CrdformatMapBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String iid;
	private String iid_desc;
	private String iid_map;
	private String iid_map_desc;
	private String func_flag;
	private String isuse;
	private String iidAndFuncFlag;
	private String needDt;
	
	private String queryIid;
	private String queryFuncFlag;
	private String queryIsuse;
	private String queryNeedDt;
	
	public String getQueryNeedDt() {
		return queryNeedDt;
	}

	public void setQueryNeedDt(String queryNeedDt) {
		this.queryNeedDt = queryNeedDt;
	}

	public CrdformatMapBean() {

	}

	public CrdformatMapBean(HashMap record) throws Exception {
		if (record.get("iid") == null) {
			this.setIid("");
		} else {
			this.setIid(StringUtils.innerToOuter((String) record.get("iid"))
					.trim());
		}
		if (record.get("iid_desc") == null) {
			this.setIid_desc("");
		} else {
			this.setIid_desc(StringUtils.innerToOuter(
					(String) record.get("iid_desc")).trim());
		}
		if (record.get("iid_map") == null) {
			this.setIid_map("");
		} else {
			this.setIid_map(StringUtils.innerToOuter(
					(String) record.get("iid_map")).trim());
		}
		if (record.get("iid_map_desc") == null) {
			this.setIid_map_desc("");
		} else {
			this.setIid_map_desc(StringUtils.innerToOuter(
					(String) record.get("iid_map_desc")).trim());
		}
		if (record.get("func_flag") == null) {
			this.setFunc_flag("");
		} else {
			this.setFunc_flag(StringUtils.innerToOuter(
					(String) record.get("func_flag")).trim());
		}
		if (record.get("isuse") == null) {
			this.setIsuse("");
		} else {
			this.setIsuse(StringUtils
					.innerToOuter((String) record.get("isuse")).trim());
		}
		if (record.get("iid_func_flag") == null) {
			this.setIidAndFuncFlag("");
		} else {
			this.setIidAndFuncFlag(StringUtils.innerToOuter((String) record.get("iid_func_flag"))
					.trim());
		}
		if (record.get("need_dt") == null) {
			this.setNeedDt("0");
		} else {
			this.setNeedDt(StringUtils.innerToOuter((String) record.get("need_dt"))
					.trim());
		}		
	}

	public String getNeedDt() {
		return needDt;
	}

	public void setNeedDt(String needDt) {
		this.needDt = needDt;
	}

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public String getIid_map() {
		return iid_map;
	}

	public void setIid_map(String iidMap) {
		iid_map = iidMap;
	}

	public String getFunc_flag() {
		return func_flag;
	}

	public void setFunc_flag(String funcFlag) {
		func_flag = funcFlag;
	}

	public String getIsuse() {
		return isuse;
	}

	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIid_desc() {
		return iid_desc;
	}

	public void setIid_desc(String iidDesc) {
		iid_desc = iidDesc;
	}

	public String getIid_map_desc() {
		return iid_map_desc;
	}

	public void setIid_map_desc(String iidMapDesc) {
		iid_map_desc = iidMapDesc;
	}

	public String getIidAndFuncFlag() {
		return iidAndFuncFlag;
	}

	public void setIidAndFuncFlag(String iidAndFuncFlag) {
		this.iidAndFuncFlag = iidAndFuncFlag;
	}

	public String getQueryIid() {
		return queryIid;
	}

	public void setQueryIid(String queryIid) {
		this.queryIid = queryIid;
	}

	public String getQueryFuncFlag() {
		return queryFuncFlag;
	}

	public void setQueryFuncFlag(String queryFuncFlag) {
		this.queryFuncFlag = queryFuncFlag;
	}

	public String getQueryIsuse() {
		return queryIsuse;
	}

	public void setQueryIsuse(String queryIsuse) {
		this.queryIsuse = queryIsuse;
	}

}
