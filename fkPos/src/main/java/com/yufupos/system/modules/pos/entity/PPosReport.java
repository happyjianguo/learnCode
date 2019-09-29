package com.yufupos.system.modules.pos.entity;

import org.hibernate.validator.constraints.Length;

import com.google.common.base.Strings;
import com.yufupos.system.common.utils.excel.annotation.ExcelField;

/**
 * POS机各状态数量统计实体
 * 
 * @author llg
 * @version 2017-04-05
 */
public class PPosReport {

	private static final long serialVersionUID = 1L;
	private String factoryId; // 厂商ID
	private String factoryName; // 厂商名称
	private String modelId; // 型号
	private String idleNum; // 空闲数量
	private String useNum; // 空闲数量
	private String malfuncNum; // 空闲数量
	private String scrapNum; // 空闲数量
	private String totleNum; // 空闲数量

	public PPosReport() {
		super();
	}

	@Length(min = 0, max = 200, message = "厂商长度必须介于 0 和 200 之间")
	@ExcelField(title = "厂商名称", align = 2, sort = 20)
	public String getFactoryName() {
		return Strings.isNullOrEmpty(factoryName)?"":factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = Strings.isNullOrEmpty(factoryName)?"":factoryName;
	}

	@Length(min = 0, max = 64, message = "型号长度必须介于 0 和 64 之间")
	@ExcelField(title = "型号", align = 2, sort = 30)
	public String getModelId() {
		return Strings.isNullOrEmpty(modelId)?"":modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = Strings.isNullOrEmpty(modelId)?"":modelId;
	}
	
	@ExcelField(title = "厂商ID", align = 2, sort = 10)
	public String getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}
	
	@ExcelField(title = "空闲总数量", align = 2, sort = 40)
	public String getIdleNum() {
		return Strings.isNullOrEmpty(idleNum)?"0":idleNum;
	}

	public void setIdleNum(String idleNum) {
		this.idleNum = Strings.isNullOrEmpty(idleNum)?"0":idleNum;
	}
	@ExcelField(title = "在用总数量", align = 2, sort = 50)
	public String getUseNum() {
		return Strings.isNullOrEmpty(useNum)?"0":useNum;
	}

	public void setUseNum(String useNum) {
		this.useNum = Strings.isNullOrEmpty(useNum)?"0":useNum;
	}
	@ExcelField(title = "故障总数量", align = 2, sort = 60)
	public String getMalfuncNum() {
		return Strings.isNullOrEmpty(malfuncNum)?"0":malfuncNum;
	}

	public void setMalfuncNum(String malfuncNum) {
		this.malfuncNum = Strings.isNullOrEmpty(malfuncNum)?"0":malfuncNum;
	}
	@ExcelField(title = "作废总数量", align = 2, sort = 70)
	public String getScrapNum() {
		return Strings.isNullOrEmpty(scrapNum)?"0":scrapNum;
	}

	public void setScrapNum(String scrapNum) {
		this.scrapNum = Strings.isNullOrEmpty(scrapNum)?"0":scrapNum;
	}
	
	@ExcelField(title = "总数量", align = 2, sort = 80)
	public String getTotleNum() {
		return Strings.isNullOrEmpty(totleNum)?"0":totleNum;
	}

	public void setTotleNum(String totleNum) {
		this.totleNum = Strings.isNullOrEmpty(totleNum)?"0":totleNum;
	}

}