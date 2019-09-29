package cn.yufu.system.modules.cortexs.entity;

import java.util.Date;

import cn.yufu.system.common.persistence.DataEntity;

/**
 * TLOG交易类型Entity
 * @author LLG
 * @version 2016-08-24
 */
public class Crdstatchglog extends DataEntity<Crdstatchglog> {
	
	private static final long serialVersionUID = 1L;
	private String tranType;		// 交易类型描述
	private String oriStatus;		// 交易码
	private String curStatus;		// 子处理码
	private Date  updateTime;      //修改时间
	private String content;		// 金额符号
	private String loginName;		// 是否需要显示
	private String crdNo;		// 备注
	private String ip;			//功能码
	
	
	public String getOriStatus() {
		return oriStatus;
	}
	public void setOriStatus(String oriStatus) {
		this.oriStatus = oriStatus;
	}
	public String getTranType() {
		return tranType;
	}
	public void setTranType(String tranType) {
		this.tranType = tranType;
	}
	public String getCurStatus() {
		return curStatus;
	}
	public void setCurStatus(String curStatus) {
		this.curStatus = curStatus;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getCrdNo() {
		return crdNo;
	}
	public void setCrdNo(String crdNo) {
		this.crdNo = crdNo;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}