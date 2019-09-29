/**
 *����:com.pay.merchant.bean
 *����:package com.pay.merchant.bean;
 */
package com.pay.merchant.bean;
/**
 * TCardMerInfoBean.java
 * ��Ȩ����(C) 2018 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2018��6��25��
 */
public class TCardMerInfoBean {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private String cardNo;
	private String startcardNo;
	private String endcardNo;
    private String merNo;
    private String addUser;
    private String timeStamp;

    public TCardMerInfoBean(){
		
	}
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
