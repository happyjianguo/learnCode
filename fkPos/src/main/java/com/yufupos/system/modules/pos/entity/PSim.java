package com.yufupos.system.modules.pos.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.yufupos.system.common.persistence.DataEntity;
import com.yufupos.system.common.utils.excel.annotation.ExcelField;
import com.yufupos.system.modules.sys.entity.User;

/**
 * SIM卡信息Entity
 * @author llg
 * @version 2017-04-05
 */
public class PSim extends DataEntity<PSim> {
	
	private static final long serialVersionUID = 1L;
	private String simCommunication;		// 运营商
	private String phoneNumber;		// 手机号
	private String serialNumber;		// SIM卡串号
	private String simStatus;		// SIM卡状态
	private String posSn;		// SN码
	private String purchaseDate;		// 采购日期
	private String purchaseBy;		// 采购者
	private String instockDate;		// 入库时间
	private String instockBy;		// 入库者
	private String instockBatch;		// 入库批次号
	private String outstockDate;		// 出库时间
	private String outstockBy;		// 出库者
	private String installDate;		// 安装时间
	private String installBy;		// 安装者
	private String repairDate;		// 报修时间
	private String repairBy;		// 报修者
	private String scrappedDate;		// 报废时间
	private String scrappedBy;		// 报废者
	private String repairBeforeStatus;		// 报修之前状态
	private String repairRemarks;		// 报修原因
	private String scrappedRemarks;		// 报废原因
	private BigDecimal simDeposit;      //押金(元)
	
	private List<User> queryPurchaseByName;
	
	public PSim() {
		super();
	}

	public PSim(String id){
		super(id);
	}

	@Length(min=0, max=20, message="运营商长度必须介于 0 和 20 之间")
	@ExcelField(title="运营商", align=2, sort=20,dictType="SIM_COMMUNICATION")	
	public String getSimCommunication() {
		return simCommunication;
	}

	public void setSimCommunication(String simCommunication) {
		this.simCommunication = simCommunication;
	}
	
	@Length(min=0, max=16, message="手机号长度必须介于 0 和 16 之间")
	@ExcelField(title="手机号", align=2, sort=30)	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Length(min=0, max=100, message="SIM卡串号长度必须介于 0 和 100 之间")
	@ExcelField(title="SIM卡串号", align=2, sort=40)	
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	@Length(min=0, max=10, message="SIM卡状态长度必须介于 0 和 10 之间")
	@ExcelField(title="SIM卡状态", align=2, sort=50,dictType="SIM_STATUS")	
	public String getSimStatus() {
		return simStatus;
	}

	public void setSimStatus(String simStatus) {
		this.simStatus = simStatus;
	}
	
	@Length(min=0, max=50, message="SN码长度必须介于 0 和 50 之间")
	@ExcelField(title="SN码", align=2, sort=60)	
	public String getPosSn() {
		return posSn;
	}

	public void setPosSn(String posSn) {
		this.posSn = posSn;
	}
	
	@Length(min=0, max=8, message="采购日期长度必须介于 0 和 8 之间")
	@ExcelField(title="采购日期", align=2, sort=70)	
	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	@Length(min=0, max=64, message="采购者长度必须介于 0 和 64 之间")
	@ExcelField(title="采购者", align=2, sort=80)	
	public String getPurchaseBy() {
		return purchaseBy;
	}

	public void setPurchaseBy(String purchaseBy) {
		this.purchaseBy = purchaseBy == null ? null : purchaseBy.trim();
	}
	
	@Length(min=0, max=8, message="入库时间长度必须介于 0 和 8 之间")
	@ExcelField(title="入库时间", align=2, sort=90)	
	public String getInstockDate() {
		return instockDate;
	}

	public void setInstockDate(String instockDate) {
		this.instockDate = instockDate;
	}
	
	@Length(min=0, max=64, message="入库者长度必须介于 0 和 64 之间")
	@ExcelField(title="入库者", align=2, sort=100)	
	public String getInstockBy() {
		return instockBy;
	}

	public void setInstockBy(String instockBy) {
		this.instockBy = instockBy;
	}
	
	@Length(min=0, max=20, message="入库批次号长度必须介于 0 和 20 之间")
	@ExcelField(title="入库批次号", align=2, sort=110)	
	public String getInstockBatch() {
		return instockBatch;
	}

	public void setInstockBatch(String instockBatch) {
		this.instockBatch = instockBatch;
	}
	
	@Length(min=0, max=8, message="出库时间长度必须介于 0 和 8 之间")
	@ExcelField(title="出库时间", align=2, sort=120)	
	public String getOutstockDate() {
		return outstockDate;
	}

	public void setOutstockDate(String outstockDate) {
		this.outstockDate = outstockDate;
	}
	
	@Length(min=0, max=64, message="出库者长度必须介于 0 和 64 之间")
	@ExcelField(title="出库者", align=2, sort=130)	
	public String getOutstockBy() {
		return outstockBy;
	}

	public void setOutstockBy(String outstockBy) {
		this.outstockBy = outstockBy;
	}
	
	@Length(min=0, max=8, message="安装时间长度必须介于 0 和 8 之间")
	@ExcelField(title="安装时间", align=2, sort=140)	
	public String getInstallDate() {
		return installDate;
	}

	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}
	
	@Length(min=0, max=64, message="安装者长度必须介于 0 和 64 之间")
	@ExcelField(title="安装者", align=2, sort=150)	
	public String getInstallBy() {
		return installBy;
	}

	public void setInstallBy(String installBy) {
		this.installBy = installBy;
	}
	
	@Length(min=0, max=8, message="报修时间长度必须介于 0 和 8 之间")
	@ExcelField(title="报修时间", align=2, sort=160)	
	public String getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(String repairDate) {
		this.repairDate = repairDate;
	}
	
	@Length(min=0, max=64, message="报修者长度必须介于 0 和 64 之间")
	@ExcelField(title="报修者", align=2, sort=170)	
	public String getRepairBy() {
		return repairBy;
	}

	public void setRepairBy(String repairBy) {
		this.repairBy = repairBy;
	}
	
	@Length(min=0, max=8, message="报废时间长度必须介于 0 和 8 之间")
	@ExcelField(title="报废时间", align=2, sort=180)	
	public String getScrappedDate() {
		return scrappedDate;
	}

	public void setScrappedDate(String scrappedDate) {
		this.scrappedDate = scrappedDate;
	}
	
	@Length(min=0, max=64, message="报废者长度必须介于 0 和 64 之间")
	@ExcelField(title="报废者", align=2, sort=190)	
	public String getScrappedBy() {
		return scrappedBy;
	}

	public void setScrappedBy(String scrappedBy) {
		this.scrappedBy = scrappedBy;
	}
	
	@ExcelField(title="创建时间", type=0, align=1, sort=999)
	public Date getCreateDate() {
		return createDate;
	}

	public String getRepairBeforeStatus() {
		return repairBeforeStatus;
	}

	public void setRepairBeforeStatus(String repairBeforeStatus) {
		this.repairBeforeStatus = repairBeforeStatus;
	}

	public String getRepairRemarks() {
		return repairRemarks;
	}

	public void setRepairRemarks(String repairRemarks) {
		this.repairRemarks = repairRemarks;
	}

	public String getScrappedRemarks() {
		return scrappedRemarks;
	}

	public void setScrappedRemarks(String scrappedRemarks) {
		this.scrappedRemarks = scrappedRemarks;
	}

	public List<User> getQueryPurchaseByName() {
		return queryPurchaseByName;
	}

	public void setQueryPurchaseByName(List<User> queryPurchaseByName) {
		this.queryPurchaseByName = queryPurchaseByName;
	}
	
	@ExcelField(title="SIM卡押金(元)", align=16, sort=200)	
	public BigDecimal getSimDeposit() {
		return simDeposit;
	}

	public void setSimDeposit(BigDecimal simDeposit) {
		this.simDeposit = simDeposit;
	}
	
}