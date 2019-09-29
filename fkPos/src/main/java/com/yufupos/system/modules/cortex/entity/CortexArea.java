package com.yufupos.system.modules.cortex.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.yufupos.system.common.persistence.DataEntity;
import com.yufupos.system.common.utils.excel.annotation.ExcelField;

/**
 * 省市区信息Entity
 * @author llg
 * @version 2017-04-19
 */
public class CortexArea extends DataEntity<CortexArea> {
	
	private static final long serialVersionUID = 1L;
	private String provinceCity;		// 名称
	private String enprovinceCity;		// 英文名
	private Integer fid;		// 上一级ID
	private String parentpath;		// 层级关系
	private Integer depth;		// 级别
	private Integer orderid;		// 排序
	private Integer child;		// 下一级ID
	private String isuse;		// 是否有效
	private Date adddate;		// 添加时间
	
	public CortexArea() {
		super();
	}

	public CortexArea(String id){
		super(id);
	}
	
	public CortexArea(Integer fid,Integer depth){
		this.setFid(fid);
		this.setDepth(depth);
		this.setIsuse("1");
	}
	

	@Length(min=1, max=200, message="名称长度必须介于 1 和 200 之间")
	@ExcelField(title="名称", align=2, sort=20)	
	public String getProvinceCity() {
		return provinceCity;
	}

	public void setProvinceCity(String provinceCity) {
		this.provinceCity = provinceCity;
	}
	
	@Length(min=1, max=200, message="英文名长度必须介于 1 和 200 之间")
	@ExcelField(title="英文名", align=2, sort=30)	
	public String getEnprovinceCity() {
		return enprovinceCity;
	}

	public void setEnprovinceCity(String enprovinceCity) {
		this.enprovinceCity = enprovinceCity;
	}
	
	@NotNull(message="上一级ID不能为空")
	@ExcelField(title="上一级ID", align=2, sort=40)	
	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}
	
	@Length(min=1, max=200, message="层级关系长度必须介于 1 和 200 之间")
	@ExcelField(title="层级关系", align=2, sort=50)	
	public String getParentpath() {
		return parentpath;
	}

	public void setParentpath(String parentpath) {
		this.parentpath = parentpath;
	}
	
	@NotNull(message="级别不能为空")
	@ExcelField(title="级别", align=2, sort=60)	
	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	
	@NotNull(message="排序不能为空")
	@ExcelField(title="排序", align=2, sort=70)	
	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	
	@NotNull(message="下一级ID不能为空")
	@ExcelField(title="下一级ID", align=2, sort=80)	
	public Integer getChild() {
		return child;
	}

	public void setChild(Integer child) {
		this.child = child;
	}
	
	@Length(min=1, max=1, message="是否有效长度必须介于 1 和 1 之间")
	@ExcelField(title="是否有效", align=2, sort=90)	
	public String getIsuse() {
		return isuse;
	}

	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="添加时间不能为空")
	@ExcelField(title="添加时间", align=2, sort=100)	
	public Date getAdddate() {
		return adddate;
	}

	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}
	
	@ExcelField(title="创建时间", type=0, align=1, sort=999)
	public Date getCreateDate() {
		return createDate;
	}	
	
}