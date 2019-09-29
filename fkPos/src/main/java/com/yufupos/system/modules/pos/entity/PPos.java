package com.yufupos.system.modules.pos.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.yufupos.system.common.persistence.DataEntity;
import com.yufupos.system.common.utils.excel.annotation.ExcelField;

/**
 * POS机信息Entity
 * @author llg
 * @version 2017-04-05
 */
public class PPos extends DataEntity<PPos> {
	
	private static final long serialVersionUID = 1L;
	private String posSn;		// SN码
	private String factoryId;		// 厂商ID
	private String factoryName;		// 厂商名称
	private String modelId;		// 型号
	private String posType;		// 设备类型
	private String posStatus;		// 设备状态
	private String posScanFlag;		// 是否支持扫描
	private String layOutFlag;		// 布放类型
	private String terminalId;		// 终端号
	private String purchaseDate;		// 采购日期
	private String purchaseBy;		// 采购者
	private String warrantyTimeLimit;		// 保修时间
	private String instockDate;		// 入库时间
	private String instockBy;		// 入库者
	private String instockBatch;		// 入库批次号
	private String outstockDate;		// 出库时间
	private String outstockBy;		// 出库者
	private String installDate;		// 安装时间
	private String installBy;		// 安装者
	private String repairDate;		// 报修时间
	private String repairBy;		// 报修者
	private String repairBeforeStatus;		// 报修之前的状态
	private String repairRemarks;		// 报修原因	
	private String scrappedDate;		// 报废时间
	private String scrappedBy;		// 报废者
	private String scrappedRemarks;		// 报废原因	
	
	public PPos() {
		super();
	}

	public PPos(String id){
		super(id);
	}

	@Length(min=1, max=50, message="SN码长度必须介于 1 和 50 之间")
	@ExcelField(title="SN码", align=2, sort=10)	
	public String getPosSn() {
		return posSn;
	}

	public void setPosSn(String posSn) {
		this.posSn = posSn;
	}
	
	@Length(min=0, max=64, message="厂商ID长度必须介于 0 和 64 之间")
	@ExcelField(title="厂商ID", align=2, sort=20)	
	public String getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}
	
	@Length(min=0, max=200, message="厂商名称长度必须介于 0 和 200 之间")
	@ExcelField(title="厂商名称", align=2, sort=30)	
	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	
	@Length(min=0, max=64, message="型号长度必须介于 0 和 64 之间")
	@ExcelField(title="型号", align=2, sort=40)	
	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	
	@Length(min=0, max=10, message="设备类型长度必须介于 0 和 10 之间")
	@ExcelField(title="设备类型", align=2, sort=50,dictType="POS_TYPE")	
	public String getPosType() {
		return posType;
	}

	public void setPosType(String posType) {
		this.posType = posType;
	}
	
	@Length(min=1, max=10, message="设备状态长度必须介于 1 和 10 之间")
	@ExcelField(title="设备状态", align=2, sort=60,dictType="POS_STATUS")	
	public String getPosStatus() {
		return posStatus;
	}

	public void setPosStatus(String posStatus) {
		this.posStatus = posStatus;
	}
	
	@Length(min=0, max=1, message="是否支持扫描长度必须介于 0 和 1 之间")
	@ExcelField(title="是否支持扫描", align=2, sort=70,dictType="POS_SCAN_FLAG")	
	public String getPosScanFlag() {
		return posScanFlag;
	}

	public void setPosScanFlag(String posScanFlag) {
		this.posScanFlag = posScanFlag;
	}
	
	@Length(min=0, max=10, message="布放类型长度必须介于 0 和 10 之间")
	@ExcelField(title="布放类型", align=2, sort=80,dictType="LAY_OUT_FLAG")	
	public String getLayOutFlag() {
		return layOutFlag;
	}

	public void setLayOutFlag(String layOutFlag) {
		this.layOutFlag = layOutFlag;
	}
	
	@Length(min=0, max=16, message="终端号长度必须介于 0 和 16 之间")
	@ExcelField(title="终端号", align=2, sort=90)	
	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	
	@Length(min=0, max=8, message="采购日期长度必须介于 0 和 8 之间")
	@ExcelField(title="采购日期", align=2, sort=100)	
	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	@Length(min=0, max=64, message="采购者长度必须介于 0 和 64 之间")
	@ExcelField(title="采购者", align=2, sort=110)	
	public String getPurchaseBy() {
		return purchaseBy;
	}

	public void setPurchaseBy(String purchaseBy) {
		this.purchaseBy = purchaseBy == null ? null : purchaseBy.trim();
	}
	
	@Length(min=0, max=8, message="保修时间长度必须介于 0 和 8 之间")
	@ExcelField(title="保修时间", align=2, sort=120)	
	public String getWarrantyTimeLimit() {
		return warrantyTimeLimit;
	}

	public void setWarrantyTimeLimit(String warrantyTimeLimit) {
		this.warrantyTimeLimit = warrantyTimeLimit;
	}
	
	@Length(min=0, max=8, message="入库时间长度必须介于 0 和 8 之间")
	@ExcelField(title="入库时间", align=2, sort=130)	
	public String getInstockDate() {
		return instockDate;
	}

	public void setInstockDate(String instockDate) {
		this.instockDate = instockDate;
	}
	
	@Length(min=0, max=64, message="入库者长度必须介于 0 和 64 之间")
	@ExcelField(title="入库者", align=2, sort=140)	
	public String getInstockBy() {
		return instockBy;
	}

	public void setInstockBy(String instockBy) {
		this.instockBy = instockBy;
	}
	
	@Length(min=0, max=20, message="入库批次号长度必须介于 0 和 20 之间")
	@ExcelField(title="入库批次号", align=2, sort=150)	
	public String getInstockBatch() {
		return instockBatch;
	}

	public void setInstockBatch(String instockBatch) {
		this.instockBatch = instockBatch;
	}
	
	@Length(min=0, max=8, message="出库时间长度必须介于 0 和 8 之间")
	@ExcelField(title="出库时间", align=2, sort=160)	
	public String getOutstockDate() {
		return outstockDate;
	}

	public void setOutstockDate(String outstockDate) {
		this.outstockDate = outstockDate;
	}
	
	@Length(min=0, max=64, message="出库者长度必须介于 0 和 64 之间")
	@ExcelField(title="出库者", align=2, sort=170)	
	public String getOutstockBy() {
		return outstockBy;
	}

	public void setOutstockBy(String outstockBy) {
		this.outstockBy = outstockBy;
	}
	
	@Length(min=0, max=8, message="安装时间长度必须介于 0 和 8 之间")
	@ExcelField(title="安装时间", align=2, sort=180)	
	public String getInstallDate() {
		return installDate;
	}

	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}
	
	@Length(min=0, max=64, message="安装者长度必须介于 0 和 64 之间")
	@ExcelField(title="安装者", align=2, sort=190)	
	public String getInstallBy() {
		return installBy;
	}

	public void setInstallBy(String installBy) {
		this.installBy = installBy;
	}
	
	@Length(min=0, max=8, message="报修时间长度必须介于 0 和 8 之间")
	@ExcelField(title="报修时间", align=2, sort=200)	
	public String getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(String repairDate) {
		this.repairDate = repairDate;
	}
	
	@Length(min=0, max=64, message="报修者长度必须介于 0 和 64 之间")
	@ExcelField(title="报修者", align=2, sort=210)	
	public String getRepairBy() {
		return repairBy;
	}

	public void setRepairBy(String repairBy) {
		this.repairBy = repairBy;
	}
	
	@Length(min=0, max=8, message="报废时间长度必须介于 0 和 8 之间")
	@ExcelField(title="报废时间", align=2, sort=220)	
	public String getScrappedDate() {
		return scrappedDate;
	}

	public void setScrappedDate(String scrappedDate) {
		this.scrappedDate = scrappedDate;
	}
	
	@Length(min=0, max=64, message="报废者长度必须介于 0 和 64 之间")
	@ExcelField(title="报废者", align=2, sort=230)	
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
	
	
	
}