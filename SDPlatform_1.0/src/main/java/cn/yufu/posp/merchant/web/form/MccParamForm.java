package cn.yufu.posp.merchant.web.form;

import cn.yufu.posp.merchant.domain.model.MerchantExtraModel;
import cn.yufu.core.web.form.BaseForm;

public class MccParamForm extends BaseForm{
	// Fields

	private String mcc;//商户类型编号
	private String mccName;//商户类型名称
	private String mccEname;//类型英文名称
	private String mccType;//所属类型
	
	//查询条件
	private String queryMcc;
	private String queryMccName;
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getMccName() {
		return mccName;
	}
	public void setMccName(String mccName) {
		this.mccName = mccName;
	}
	public String getMccEname() {
		return mccEname;
	}
	public void setMccEname(String mccEname) {
		this.mccEname = mccEname;
	}
	public String getMccType() {
		return mccType;
	}
	public void setMccType(String mccType) {
		this.mccType = mccType;
	}
	public String getQueryMcc() {
		return queryMcc;
	}
	public void setQueryMcc(String queryMcc) {
		this.queryMcc = queryMcc;
	}
	public String getQueryMccName() {
		return queryMccName;
	}
	public void setQueryMccName(String queryMccName) {
		this.queryMccName = queryMccName;
	}
	
	
}
