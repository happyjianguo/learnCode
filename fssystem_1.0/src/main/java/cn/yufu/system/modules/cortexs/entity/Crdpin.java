package cn.yufu.system.modules.cortexs.entity;

import cn.yufu.system.common.persistence.DataEntity;

/**
 * TLOG交易类型Entity
 * @author LLG
 * @version 2016-08-24
 */
public class Crdpin extends DataEntity<Crdpin> {
	
	private static final long serialVersionUID = 1L;
	private Integer pintries;		// 交易码
	private Integer pincount;	    //功能码
	private String pan;	
	private String crddetId;
	
	public String getCrddetId() {
		return crddetId;
	}
	public void setCrddetId(String crddetId) {
		this.crddetId = crddetId;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public Integer getPintries() {
		return pintries;
	}
	public void setPintries(Integer pintries) {
		this.pintries = pintries;
	}
	public Integer getPincount() {
		return pincount;
	}
	public void setPincount(Integer pincount) {
		this.pincount = pincount;
	}
	
}