package com.pay.merInfoStatistics.struts.form;

import org.apache.struts.action.ActionForm;

public class TerminalInfoForm extends ActionForm {
	
	private static final long serialVersionUID = -7304898177023160832L;
	
	private String id;  //终端编号
	private String merchantid;  //所属商户编号
	private String address;  //商户装机地址
	private String detailaddress;  //店铺及款台地址
	private String model;  //POS型号
	private String type;  //POS类型
	private String mobilenumber;  //无线POS手机号
	private String snnumber;  //POS机S/N号
	private String installdate;  //安装日期
	private String disabledate;  //停用日期
	private String updatedate;  //升级日期
	private String name;  //联系人
	private String phonenumber;  //门店电话
	private String status;  //POS机状态
	private String deposite;  //POS押金（元）
	
	//———————————— query ——————————
	
	private String qid;
	private String qmerchantid; 
	private String qmerchantname;  //所属商户名称
	private String qaddress;  
	private String qdetailaddress; 
	private String qmodel;  
	private String qtype; 
	private String qmobilenumber; 
	private String qsnnumber;  
	private String qinstalldate;
	private String qinstalldate_startdate;  //安装时间-开始时间
	private String qinstalldate_enddate;  //安装时间-结束时间
	private String qdisabledate; 
	private String qupdatedate;
	private String qname;  
	private String qphonenumber;  
	private String qstatus;  
	private String qdeposite; 
	
	//———————————— add or update ——————————
	
	private String qmerchantidname;  //新增或修改终端信息时，根据所属商户id或name查询所属商户list
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMerchantid() {
		return merchantid;
	}
	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetailaddress() {
		return detailaddress;
	}
	public void setDetailaddress(String detailaddress) {
		this.detailaddress = detailaddress;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getSnnumber() {
		return snnumber;
	}
	public void setSnnumber(String snnumber) {
		this.snnumber = snnumber;
	}
	public String getInstalldate() {
		return installdate;
	}
	public void setInstalldate(String installdate) {
		this.installdate = installdate;
	}
	public String getDisabledate() {
		return disabledate;
	}
	public void setDisabledate(String disabledate) {
		this.disabledate = disabledate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeposite() {
		return deposite;
	}
	public void setDeposite(String deposite) {
		this.deposite = deposite;
	}
	
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getQmerchantid() {
		return qmerchantid;
	}
	public void setQmerchantid(String qmerchantid) {
		this.qmerchantid = qmerchantid;
	}
	public String getQaddress() {
		return qaddress;
	}
	public void setQaddress(String qaddress) {
		this.qaddress = qaddress;
	}
	public String getQdetailaddress() {
		return qdetailaddress;
	}
	public void setQdetailaddress(String qdetailaddress) {
		this.qdetailaddress = qdetailaddress;
	}
	public String getQmodel() {
		return qmodel;
	}
	public void setQmodel(String qmodel) {
		this.qmodel = qmodel;
	}
	public String getQtype() {
		return qtype;
	}
	public void setQtype(String qtype) {
		this.qtype = qtype;
	}
	public String getQmobilenumber() {
		return qmobilenumber;
	}
	public void setQmobilenumber(String qmobilenumber) {
		this.qmobilenumber = qmobilenumber;
	}
	public String getQsnnumber() {
		return qsnnumber;
	}
	public void setQsnnumber(String qsnnumber) {
		this.qsnnumber = qsnnumber;
	}
	public String getQinstalldate() {
		return qinstalldate;
	}
	public void setQinstalldate(String qinstalldate) {
		this.qinstalldate = qinstalldate;
	}
	public String getQdisabledate() {
		return qdisabledate;
	}
	public void setQdisabledate(String qdisabledate) {
		this.qdisabledate = qdisabledate;
	}
	public String getQupdatedate() {
		return qupdatedate;
	}
	public void setQupdatedate(String qupdatedate) {
		this.qupdatedate = qupdatedate;
	}
	public String getQname() {
		return qname;
	}
	public void setQname(String qname) {
		this.qname = qname;
	}
	public String getQphonenumber() {
		return qphonenumber;
	}
	public void setQphonenumber(String qphonenumber) {
		this.qphonenumber = qphonenumber;
	}
	public String getQstatus() {
		return qstatus;
	}
	public void setQstatus(String qstatus) {
		this.qstatus = qstatus;
	}
	public String getQdeposite() {
		return qdeposite;
	}
	public void setQdeposite(String qdeposite) {
		this.qdeposite = qdeposite;
	}
	public String getQmerchantname() {
		return qmerchantname;
	}
	public void setQmerchantname(String qmerchantname) {
		this.qmerchantname = qmerchantname;
	}
	public String getQinstalldate_startdate() {
		return qinstalldate_startdate;
	}
	public void setQinstalldate_startdate(String qinstalldate_startdate) {
		this.qinstalldate_startdate = qinstalldate_startdate;
	}
	public String getQinstalldate_enddate() {
		return qinstalldate_enddate;
	}
	public void setQinstalldate_enddate(String qinstalldate_enddate) {
		this.qinstalldate_enddate = qinstalldate_enddate;
	}
	public String getQmerchantidname() {
		return qmerchantidname;
	}
	public void setQmerchantidname(String qmerchantidname) {
		this.qmerchantidname = qmerchantidname;
	}
	
}
