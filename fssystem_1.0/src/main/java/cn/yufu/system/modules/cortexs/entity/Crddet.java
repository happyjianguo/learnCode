package cn.yufu.system.modules.cortexs.entity;

import cn.yufu.system.common.persistence.DataEntity;

/**
 * CarStatus交易类型Entity
 * @author LLG
 * @version 2016-08-24
 */
public class Crddet extends DataEntity<Crddet> {
	
	private static final long serialVersionUID = 1L;
	private String statCode;		// 状态码
	private String pan;				// 卡号

	public Crddet() {
		super();
	}

	public Crddet(String id){
		super(id);
	}
	
	public String getStatCode() {
		return statCode;
	}

	public void setStatCode(String statCode) {
		this.statCode = statCode;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}
	
}