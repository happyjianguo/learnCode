/**
 *包名:com.pay.merchant.struts.form
 *描述:package com.pay.merchant.struts.form;
 */
package com.pay.merchant.struts.form;

import org.apache.struts.action.ActionForm;

/**
 * TCardMerInfoForm.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:gll 
 * 时间:2018年6月22日
 * 描述:商户活动卡管理 
 */
public class TCardMerInfoForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String cardNo;
	private String startcardNo;
	private String endcardNo;

    private String merNo;

    private String addUser;

    private String timeStamp;

    public String getStartcardNo() {
		return startcardNo;
	}

	public void setStartcardNo(String startcardNo) {
		this.startcardNo = startcardNo == null ? null : startcardNo.trim();
	}

	public String getEndcardNo() {
		return endcardNo;
	}

	public void setEndcardNo(String endcardNo) {
		this.endcardNo = endcardNo == null ? null : endcardNo.trim();
	}

	public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo == null ? null : merNo.trim();
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser == null ? null : addUser.trim();
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp == null ? null : timeStamp.trim();
    }

	@Override
	public String toString() {
		return "TCardMerInfoForm [cardNo=" + cardNo + ", merNo=" + merNo + ", addUser=" + addUser + ", timeStamp="
				+ timeStamp + "]";
	}
}
