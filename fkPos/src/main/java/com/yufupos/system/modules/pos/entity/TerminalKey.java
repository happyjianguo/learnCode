package com.yufupos.system.modules.pos.entity;

import com.yufupos.system.common.persistence.DataEntity;
import com.yufupos.system.common.utils.excel.annotation.ExcelField;

/**
 * 终端主密钥Controller
 * @author ZQK
 * @version 2018-06-12
 */
public class TerminalKey extends DataEntity<TerminalKey> {
	private static final long serialVersionUID = 1L;
	private String merchantId;		//商户号
	private String merchantCname;		//商户名称
	private String terminalId;		//终端号
	private String accno;		//商户结算账号
	private String setAddr;			//店铺及款台位置
	private String masterKey;		//明文字段
	private String tmkMasterKey;	//密文字段
	private String posSn;			//POS_SN
	
	public TerminalKey() {
		super();
	}

	public TerminalKey(String id){
		super(id);
	}

	@ExcelField(title="商户号", align=2, sort=10)	
	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
	@ExcelField(title="商户名称", align=2, sort=20)	
	public String getMerchantCname() {
		return merchantCname;
	}

	public void setMerchantCname(String merchantCname) {
		this.merchantCname = merchantCname;
	}

	@ExcelField(title="终端号", align=2, sort=30)	
	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	@ExcelField(title="结算账号", align=2, sort=40)	
	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	@ExcelField(title="店铺及款台位置", align=2, sort=50)	
	public String getSetAddr() {
		return setAddr;
	}

	public void setSetAddr(String setAddr) {
		this.setAddr = setAddr;
	}

	//@ExcelField(title="明文", align=2, sort=50)	
	public String getMasterKey() {
		return masterKey;
	}

	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
	}

	@ExcelField(title="密文", align=2, sort=60)	
	public String getTmkMasterKey() {
		return tmkMasterKey;
	}

	public void setTmkMasterKey(String tmkMasterKey) {
		this.tmkMasterKey = tmkMasterKey;
	}

	@ExcelField(title="POS-SN", align=2, sort=100)	
	public String getPosSn() {
		return posSn;
	}

	public void setPosSn(String posSn) {
		this.posSn = posSn;
	}

}