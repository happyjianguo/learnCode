package com.pay.batch.bflowlog.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.StringUtils;

public class MakeCardBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String verno_ctx;//
	private String id;//主键id		
	private String txtime;// 订单时间
	private String stan;// 流水号
	private String cust_order;// 订单号
	private String pan;// 卡号
	private String telphone;// 手机号
	private String address; // 联系地址
	private String name;// 姓名
	private String batch;// 批次号
	private String org_batch;// 原批次号
	private String fee;// 手续费
	private String operator;// 操作者
	private String stat;// 状态(00,未制卡，01已制卡，02制卡失败)
	private String acct_period;//
	private String reserved1;// 制卡文件路径
	private String reserved2;
	private String reserved3; // 备注
	private String crd_expdate;// 卡有效期

	//查询条件
	private String queryCustOrder;
	private String queryPan;
	private String queryBatch;
	private String queryStat;	
	
	private MakeCardBean (){
		
	}
	

	public MakeCardBean(HashMap record) throws Exception {
		if (record.get("verno_ctx") == null) {
			this.setVerno_ctx("");
		} else {
			this.setVerno_ctx(StringUtils.innerToOuter((String) record.get("verno_ctx"))
					.trim());
		}
		if (record.get("id") == null) {
			this.setId("");
		} else {
			this.setId(StringUtils.innerToOuter((String) record.get("id"))
					.trim());
		}
		if (record.get("txtime") == null) {
			this.setTxtime("");
		} else {
			this.setTxtime(StringUtils.innerToOuter((String) record.get("txtime"))
					.trim());
		}
		if (record.get("stan") == null) {
			this.setStan("");
		} else {
			this.setStan(StringUtils.innerToOuter((String) record.get("stan"))
					.trim());
		}
		if (record.get("cust_order") == null) {
			this.setCust_order("");
		} else {
			this.setCust_order(StringUtils.innerToOuter((String) record.get("cust_order"))
					.trim());
		}
		if (record.get("pan") == null) {
			this.setPan("");
		} else {
			this.setPan(StringUtils.innerToOuter((String) record.get("pan"))
					.trim());
		}
		if (record.get("telphone") == null) {
			this.setTelphone("");
		} else {
			this.setTelphone(StringUtils.innerToOuter((String) record.get("telphone"))
					.trim());
		}
		if (record.get("address") == null) {
			this.setAddress("");
		} else {
			this.setAddress(StringUtils.innerToOuter((String) record.get("address"))
					.trim());
		}
		if (record.get("name") == null) {
			this.setName("");
		} else {
			this.setName(StringUtils.innerToOuter((String) record.get("name"))
					.trim());
		}
		if (record.get("batch") == null) {
			this.setBatch("");
		} else {
			this.setBatch(StringUtils.innerToOuter((String) record.get("batch"))
					.trim());
		}
		if (record.get("org_batch") == null) {
			this.setOrg_batch("");
		} else {
			this.setOrg_batch(StringUtils.innerToOuter((String) record.get("org_batch"))
					.trim());
		}
		if (record.get("fee") == null) {
			this.setFee("");
		} else {
			this.setFee(StringUtils.innerToOuter((String) record.get("fee"))
					.trim());
		}
		if (record.get("operator") == null) {
			this.setOperator("");
		} else {
			this.setOperator(StringUtils.innerToOuter((String) record.get("operator"))
					.trim());
		}
		if (record.get("stat") == null) {
			this.setStat("");
		} else {
			this.setStat(StringUtils.innerToOuter((String) record.get("stat"))
					.trim());
		}
		if (record.get("acct_period") == null) {
			this.setAcct_period("");
		} else {
			this.setAcct_period(StringUtils.innerToOuter((String) record.get("acct_period"))
					.trim());
		}
		if (record.get("reserved1") == null) {
			this.setReserved1("");
		} else {
			this.setReserved1(StringUtils.innerToOuter((String) record.get("reserved1"))
					.trim());
		}
		if (record.get("reserved2") == null) {
			this.setReserved2("");
		} else {
			this.setReserved2(StringUtils.innerToOuter((String) record.get("reserved2"))
					.trim());
		}
		if (record.get("reserved3") == null) {
			this.setReserved3("");
		} else {
			this.setReserved3(StringUtils.innerToOuter((String) record.get("reserved3"))
					.trim());
		}
		if (record.get("crd_expdate") == null) {
			this.setCrd_expdate("");
		} else {
			this.setCrd_expdate(StringUtils.innerToOuter((String) record.get("crd_expdate"))
					.trim());
		}
	}

	
	public String getVerno_ctx() {
		return verno_ctx;
	}

	public void setVerno_ctx(String vernoCtx) {
		verno_ctx = vernoCtx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTxtime() {
		return txtime;
	}

	public void setTxtime(String txtime) {
		this.txtime = txtime;
	}

	public String getStan() {
		return stan;
	}

	public void setStan(String stan) {
		this.stan = stan;
	}

	public String getCust_order() {
		return cust_order;
	}

	public void setCust_order(String custOrder) {
		cust_order = custOrder;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getOrg_batch() {
		return org_batch;
	}

	public void setOrg_batch(String orgBatch) {
		org_batch = orgBatch;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getAcct_period() {
		return acct_period;
	}

	public void setAcct_period(String acctPeriod) {
		acct_period = acctPeriod;
	}

	public String getReserved1() {
		return reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public String getReserved2() {
		return reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	public String getReserved3() {
		return reserved3;
	}

	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}

	public String getCrd_expdate() {
		return crd_expdate;
	}

	public void setCrd_expdate(String crdExpdate) {
		crd_expdate = crdExpdate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getQueryCustOrder() {
		return queryCustOrder;
	}


	public void setQueryCustOrder(String queryCustOrder) {
		this.queryCustOrder = queryCustOrder;
	}


	public String getQueryPan() {
		return queryPan;
	}


	public void setQueryPan(String queryPan) {
		this.queryPan = queryPan;
	}


	public String getQueryBatch() {
		return queryBatch;
	}


	public void setQueryBatch(String queryBatch) {
		this.queryBatch = queryBatch;
	}


	public String getQueryStat() {
		return queryStat;
	}


	public void setQueryStat(String queryStat) {
		this.queryStat = queryStat;
	}

}
