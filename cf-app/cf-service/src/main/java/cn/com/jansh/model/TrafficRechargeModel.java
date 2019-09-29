package cn.com.jansh.model;

import java.util.List;

import cn.com.jansh.entity.wsfdn.CfAccessclientEntity;
import cn.com.jansh.entity.wsfdn.CfAccesspriceEntity;
import cn.com.jansh.entity.wsfdn.CfCustomerEntity;
import cn.com.jansh.entity.wsfdn.CfProvinceEntity;

public class TrafficRechargeModel {

	/** 充值id */
	private String id;
	
	/** 运营商类型 */
	private String ispno;
	
	/** 套餐类型  　*/
	private String isptype;
	
	/** 面值    */
	private String facevalue;
	
	/** 手机号 */
	private String phone;
	
	/** 流水号 */
	private String bizid;
	
	/** 发送状态 */
	private String status;
	
	/** 省份  */
	private String province;
	
	/** 客户  */
	private String cid;
	
	/** 客户名称 */
	private String cname;
	
	/** 接入者 */
	private String acid;
	
	/** 接入者名称 */
	private String acname;

	/** 客户列表 */
	private List<CfCustomerEntity> customerlist;
	
	/** 接入者列表 */
	private List<CfAccessclientEntity> accesslist;
	
	/** 省份列表  */
	private List<CfProvinceEntity> provincelist;
	
	/** 接入者报价列表 */
	private List<CfAccesspriceEntity> accesspricelist;
	
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getAcname() {
		return acname;
	}

	public void setAcname(String acname) {
		this.acname = acname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIspno() {
		return ispno;
	}

	public void setIspno(String ispno) {
		this.ispno = ispno;
	}

	public String getIsptype() {
		return isptype;
	}

	public void setIsptype(String isptype) {
		this.isptype = isptype;
	}

	public String getFacevalue() {
		return facevalue;
	}

	public void setFacevalue(String facevalue) {
		this.facevalue = facevalue;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBizid() {
		return bizid;
	}

	public void setBizid(String bizid) {
		this.bizid = bizid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getAcid() {
		return acid;
	}

	public void setAcid(String acid) {
		this.acid = acid;
	}

	public List<CfCustomerEntity> getCustomerlist() {
		return customerlist;
	}

	public void setCustomerlist(List<CfCustomerEntity> customerlist) {
		this.customerlist = customerlist;
	}

	public List<CfAccessclientEntity> getAccesslist() {
		return accesslist;
	}

	public void setAccesslist(List<CfAccessclientEntity> accesslist) {
		this.accesslist = accesslist;
	}

	public List<CfProvinceEntity> getProvincelist() {
		return provincelist;
	}

	public void setProvincelist(List<CfProvinceEntity> provincelist) {
		this.provincelist = provincelist;
	}

	public List<CfAccesspriceEntity> getAccesspricelist() {
		return accesspricelist;
	}

	public void setAccesspricelist(List<CfAccesspriceEntity> accesspricelist) {
		this.accesspricelist = accesspricelist;
	}
	
	@Override
	public String toString() {
		return "TrafficRechargeModel [id=" + id + ", ispno=" + ispno + ", isptype=" + isptype + ", facevalue="
				+ facevalue + ", phone=" + phone + ", bizid=" + bizid + ", status=" + status + ", province=" + province
				+ ", cid=" + cid + ", cname=" + cname + ", acid=" + acid + ", acname=" + acname + ", customerlist="
				+ customerlist + ", accesslist=" + accesslist + ", provincelist=" + provincelist + ", accesspricelist="
				+ accesspricelist + "]";
	}
}
