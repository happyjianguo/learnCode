/**
 *包名:com.pay.batch.bflowlog.struts.form
 *描述:package com.pay.batch.bflowlog.struts.form;
 */
package com.pay.batch.bflowlog.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * IidPinChkForm.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:gll 
 * 时间:2018年7月4日
 * 描述:发卡密码管理
 */
public class IidPinChkForm extends ActionForm {
    private static final long serialVersionUID = 1L;
    
    private String queryiid;
    private String iid;

    private String ivrpinfalg;

    private String pospinfalg;

    private String adddatetime;

    private String oper;

    private String reserved1;

    private String reserved2;

    private String reserved3;

    private String reserved4;

    
    public String getQueryiid() {
		return queryiid;
	}

	public void setQueryiid(String queryiid) {
		this.queryiid = queryiid;
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

}
