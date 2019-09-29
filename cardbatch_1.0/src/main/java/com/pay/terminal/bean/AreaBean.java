package com.pay.terminal.bean;

import java.io.Serializable;

public class AreaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String province_city;
	private String enprovince_city;
	private int fid;
	private String parentpath;
	private int  depth;
	private int  orderid;
	private int child;
	private String isuse;
	private String adddate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProvince_city() {
		return province_city;
	}
	public void setProvince_city(String province_city) {
		this.province_city = province_city;
	}
	public String getEnprovince_city() {
		return enprovince_city;
	}
	public void setEnprovince_city(String enprovince_city) {
		this.enprovince_city = enprovince_city;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getParentpath() {
		return parentpath;
	}
	public void setParentpath(String parentpath) {
		this.parentpath = parentpath;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getChild() {
		return child;
	}
	public void setChild(int child) {
		this.child = child;
	}
	public String getIsuse() {
		return isuse;
	}
	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}
	public String getAdddate() {
		return adddate;
	}
	public void setAdddate(String adddate) {
		this.adddate = adddate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}///////date
}