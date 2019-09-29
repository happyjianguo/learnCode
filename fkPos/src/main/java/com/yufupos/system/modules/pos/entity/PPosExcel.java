package com.yufupos.system.modules.pos.entity;

import org.hibernate.validator.constraints.Length;

import com.yufupos.system.common.utils.excel.annotation.ExcelField;

/**
 * POS机信息Entity
 * @author llg
 * @version 2017-04-05
 */
public class PPosExcel {
	
	private static final long serialVersionUID = 1L;
	private String posSn;		// SN码
	private String factoryName;		// 厂商名称
	private String modelId;		// 型号
	private String posType;		// 设备类型
	private String posScanFlag;		// 是否支持扫描
	private String layOutFlag;		// 布放类型
	private String purchaseDate;		// 采购日期
	private String purchaseBy;		// 采购者
	private String warrantyTimeLimit;		// 保修时间
	private String instockBatch;		// 入库批次号
	private String remarks;	// 备注
	
	public PPosExcel() {
		super();
	}

	@Length(min=1, max=50, message="SN码长度必须介于 1 和 50 之间")
	@ExcelField(title="SN码", align=2, sort=10)	
	public String getPosSn() {
		return posSn;
	}

	public void setPosSn(String posSn) {
		this.posSn = posSn;
	}
	
	@Length(min=0, max=200, message="厂商长度必须介于 0 和 200 之间")
	@ExcelField(title="厂商", align=2, sort=30)	
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
	
	@Length(min=0, max=50, message="设备类型长度必须介于 0 和 50 之间")
	@ExcelField(title="设备类型", align=2, sort=50)	
	public String getPosType() {
		return posType;
	}

	public void setPosType(String posType) {
		this.posType = posType;
	}
	
	@Length(min=0, max=20, message="是否支持扫描长度必须介于 0 和20之间")
	@ExcelField(title="是否支持扫描", align=2, sort=70)	
	public String getPosScanFlag() {
		return posScanFlag;
	}

	public void setPosScanFlag(String posScanFlag) {
		this.posScanFlag = posScanFlag;
	}
	
	@Length(min=0, max=20, message="布放类型长度必须介于 0 和 20 之间")
	@ExcelField(title="布放类型", align=2, sort=80)	
	public String getLayOutFlag() {
		return layOutFlag;
	}

	public void setLayOutFlag(String layOutFlag) {
		this.layOutFlag = layOutFlag;
	}

	
	@Length(min=0, max=8, message="采购日期长度必须为8,例如：20170101 (单元格格式请设置成常规,不能是数字)")
	@ExcelField(title="采购日期", align=2, sort=100)	
	public String getPurchaseDate() {
		return purchaseDate = purchaseDate == null ? null : purchaseDate.trim();
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	@Length(min=0, max=64, message="采购者不存在")
	@ExcelField(title="采购者", align=2, sort=110)	
	public String getPurchaseBy() {
		return purchaseBy;
	}

	public void setPurchaseBy(String purchaseBy) {
		this.purchaseBy = purchaseBy;
	}
	
	@Length(min=0, max=8, message="保修时间长度必须介于 0 和 8 之间,例如：1 (单元格格式请设置成常规,不能是数字)")
	@ExcelField(title="保修时间", align=2, sort=120)	
	public String getWarrantyTimeLimit() {
		return warrantyTimeLimit;
	}

	public void setWarrantyTimeLimit(String warrantyTimeLimit) {
		this.warrantyTimeLimit = warrantyTimeLimit;
	}
	
	@Length(min=0, max=20, message="入库批次号长度必须介于 0 和 20 之间,例如：POS-00000000001 (单元格格式请设置成常规,不能是数字)")
	@ExcelField(title="入库批次号", align=2, sort=150)	
	public String getInstockBatch() {
		return instockBatch;
	}

	public void setInstockBatch(String instockBatch) {
		this.instockBatch = instockBatch;
	}	
	
	@Length(min=0, max=100, message="备注长度必须介于 0 和 100 之间,例如：望京20170407新增POS (单元格格式请设置成常规,不能是数字)")
	@ExcelField(title="备注", align=2, sort=330)	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}