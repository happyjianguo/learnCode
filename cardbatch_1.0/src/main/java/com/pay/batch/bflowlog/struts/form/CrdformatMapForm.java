package com.pay.batch.bflowlog.struts.form;

import org.apache.struts.action.ActionForm;

public class CrdformatMapForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String iid;
	private String iid_desc;
	private String iid_map;
	private String iid_map_desc;
	private String func_flag;
	private String isuse;
	private String iidAndFuncFlag;
	private String needDt;

	//��ѯ����
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

	public String getIidAndFuncFlag() {
		return iidAndFuncFlag;
	}

	public void setIidAndFuncFlag(String iidAndFuncFlag) {
		this.iidAndFuncFlag = iidAndFuncFlag;
	}

	public String getNeedDt() {
		return needDt;
	}

	public void setNeedDt(String needDt) {
		this.needDt = needDt;
	}
	
}
