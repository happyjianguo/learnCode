/**
 * SingleActionTotal.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年12月1日
 */
package cn.com.jansh.entity.data;

import java.math.BigDecimal;

/**
 * PV/UV
 * @author gll
 * @version 1.0
 */
public class SingleActionTotal {
	private BigDecimal pv;		//pv
	private BigDecimal uv;		//uv
	
	public BigDecimal getPv() {
		return pv;
	}

	public void setPv(BigDecimal pv) {
		this.pv = pv;
	}

	public BigDecimal getUv() {
		return uv;
	}

	public void setUv(BigDecimal uv) {
		this.uv = uv;
	}

	@Override
	public String toString() {
		return "SingleActionTotal [pv=" + pv + ", uv=" + uv + "]";
	}
}
