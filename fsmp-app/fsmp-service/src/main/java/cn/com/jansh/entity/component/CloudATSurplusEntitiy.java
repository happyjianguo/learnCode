/**
 * CloudATSurplusEntitiy.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月27日
 */
package cn.com.jansh.entity.component;

import java.math.BigDecimal;

/**
 * 账户余额实体
 * @author Mr.wong
 * @version 1.0
 */
public class CloudATSurplusEntitiy {

	private String id ;
	
	private String orgid;
	
	private BigDecimal currentMoney;
	
	private BigDecimal totalMoney;
	
	private String createTime;
	
	private String updateTime;
	
	public CloudATSurplusEntitiy() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	
	public BigDecimal getCurrentMoney() {
		return currentMoney;
	}

	public void setCurrentMoney(BigDecimal currentMoney) {
		this.currentMoney = currentMoney;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "CloudATSurplusEntitiy [id=" + id + ", orgid=" + orgid + ", currentMoney=" + currentMoney
				+ ", totalMoney=" + totalMoney + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	
}
