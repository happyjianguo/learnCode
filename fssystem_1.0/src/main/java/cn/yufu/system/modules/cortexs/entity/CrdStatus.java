package cn.yufu.system.modules.cortexs.entity;

import cn.yufu.system.common.persistence.DataEntity;

/**
 * CarStatus交易类型Entity
 * @author LLG
 * @version 2016-08-24
 */
public class CrdStatus extends DataEntity<CrdStatus> {
	
	private static final long serialVersionUID = 1L;
	private String statCode;		// 状态码
	private String descr;			// 状态描述
	private String pan;				// 卡号
	private String currentStatus;
	private String comments;
	
	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public CrdStatus() {
		super();
	}

	public CrdStatus(String id){
		super(id);
	}
	
	public String getStatCode() {
		return statCode;
	}

	public void setStatCode(String statCode) {
		this.statCode = statCode;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}
	
}