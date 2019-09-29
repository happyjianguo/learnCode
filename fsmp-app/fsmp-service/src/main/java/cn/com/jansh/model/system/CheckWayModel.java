/**
 * CheckWayModel.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年11月3日
 */
package cn.com.jansh.model.system;

/**
 * 发送验证码途径
 * @author Mr.wong
 * @version 1.0
 */
public class CheckWayModel {

	//校验手机号标志  1 校验手机号存不存在 0 校验手机号有没有被使用 
	private int checkFlag;
	// 手机号
	private String phoneNo;
	//邮箱
	private String mailName;
	// 验证码
	private String msgcode;
	
	private String msgModel;
	
	public CheckWayModel() {
		
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getMailName() {
		return mailName;
	}
	public void setMailName(String mailName) {
		this.mailName = mailName;
	}

	public String getMsgcode() {
		return msgcode;
	}
	public void setMsgcode(String msgcode) {
		this.msgcode = msgcode;
	}
	public String getMsgModel() {
		return msgModel;
	}

	public void setMsgModel(String msgModel) {
		this.msgModel = msgModel;
	}

	public int getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(int checkFlag) {
		this.checkFlag = checkFlag;
	}
	
}
