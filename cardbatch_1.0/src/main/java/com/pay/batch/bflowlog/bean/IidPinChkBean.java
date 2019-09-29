/**
 *包名:com.pay.batch.bflowlog.bean
 *描述:package com.pay.batch.bflowlog.bean;
 */
package com.pay.batch.bflowlog.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.StringUtils;

/**
 * IidPinChkBean.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:gll 
 * 时间:2018年7月4日
 * 描述:发卡密码管理
 */
public class IidPinChkBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String iid;

    private String ivrpinfalg;

    private String pospinfalg;

    private String adddatetime;

    private String oper;

    private String reserved1;

    private String reserved2;

    private String reserved3;

    private String reserved4;
    
    @SuppressWarnings("rawtypes")
	public IidPinChkBean(HashMap record) throws Exception {
		if (record.get("iid") == null) {
			this.setIid("");
		} else {
			this.setIid(StringUtils.innerToOuter((String) record.get("iid"))
					.trim());
		}
		if (record.get("ivrpinfalg") == null) {
			this.setIvrpinfalg("");
		} else {
			this.setIvrpinfalg(StringUtils.innerToOuter(
					(String) record.get("ivrpinfalg")).trim());
		}
		if (record.get("pospinfalg") == null) {
			this.setPospinfalg("");
		} else {
			this.setPospinfalg(StringUtils.innerToOuter(
					(String) record.get("pospinfalg")).trim());
		}
		if (record.get("adddatetime") == null) {
			this.setAdddatetime("");
		} else {
			this.setAdddatetime(StringUtils.innerToOuter(
					(String) record.get("adddatetime")).trim());
		}
		if (record.get("oper") == null) {
			this.setOper("");
		} else {
			this.setOper(StringUtils.innerToOuter(
					(String) record.get("oper")).trim());
		}
		if (record.get("reserved1") == null) {
			this.setReserved1("");
		} else {
			this.setReserved1(StringUtils.innerToOuter((String) record.get("reserved1")).trim());
		}
		if (record.get("reserved2") == null) {
			this.setReserved2("");
		} else {
			this.setReserved2(StringUtils.innerToOuter((String) record.get("reserved2")).trim());
		}
		if (record.get("reserved3") == null) {
			this.setReserved3("");
		} else {
			this.setReserved3(StringUtils.innerToOuter((String) record.get("reserved3")).trim());
		}
		if (record.get("reserved4") == null) {
			this.setReserved4("");
		} else {
			this.setReserved4(StringUtils.innerToOuter((String) record.get("reserved4")).trim());
		}
	}

	public IidPinChkBean() {
	}

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public String getIvrpinfalg() {
		return ivrpinfalg;
	}

	public void setIvrpinfalg(String ivrpinfalg) {
		this.ivrpinfalg = ivrpinfalg;
	}

	public String getPospinfalg() {
		return pospinfalg;
	}

	public void setPospinfalg(String pospinfalg) {
		this.pospinfalg = pospinfalg;
	}

	public String getAdddatetime() {
		return adddatetime;
	}

	public void setAdddatetime(String adddatetime) {
		this.adddatetime = adddatetime;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getReserved1() {
		return reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public String getReserved2() {
		return reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	public String getReserved3() {
		return reserved3;
	}

	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}

	public String getReserved4() {
		return reserved4;
	}

	public void setReserved4(String reserved4) {
		this.reserved4 = reserved4;
	}
}
