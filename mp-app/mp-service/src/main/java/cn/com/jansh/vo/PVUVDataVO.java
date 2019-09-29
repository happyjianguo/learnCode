/**
 * PVUVDataVO.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年12月12日
 */
package cn.com.jansh.vo;

import java.math.BigDecimal;

/**
 * pv、uv VO
 * @author gll
 * @version 1.0
 */
public class PVUVDataVO {
	
	private BigDecimal pv;		//pv
	private BigDecimal uv;		//uv
	private EchartData EchartData;	//图表vo
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
	public EchartData getEchartData() {
		return EchartData;
	}
	public void setEchartData(EchartData echartData) {
		EchartData = echartData;
	}
	@Override
	public String toString() {
		return "PVUVDataVO [pv=" + pv + ", uv=" + uv + ", EchartData=" + EchartData + "]";
	}
}
