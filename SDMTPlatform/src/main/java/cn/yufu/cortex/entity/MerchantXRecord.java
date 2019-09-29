/**
 *包名:cn.yufu.cortex.entity
 *描述:package cn.yufu.cortex.entity;
 */
package cn.yufu.cortex.entity;

/**
 * MerchantXRecord.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年8月7日
 * 描述:商户修改日志实体类
 */
public class MerchantXRecord {

	private String mrchno; //商户号

	private String mrchName;  //商户名称
	
	private String modifyModule;  //修改模块
	
	private String originalRecord; //原纪录
	
	private String modifyRecord; //修改记录
	
	private String remoteAddr;  //IP地址
	
	private String remoteMac; //MAC
	
	private String userAgent; //浏览器标识
	
	private String createTime; //修改时间
	
	private String createBy;	// 创建者

	public String getMrchno() {
		return mrchno;
	}

	public void setMrchno(String mrchno) {
		this.mrchno = mrchno;
	}

	public String getMrchName() {
		return mrchName;
	}

	public void setMrchName(String mrchName) {
		this.mrchName = mrchName;
	}

	public String getModifyModule() {
		return modifyModule;
	}

	public void setModifyModule(String modifyModule) {
		this.modifyModule = modifyModule;
	}

	public String getOriginalRecord() {
		return originalRecord;
	}

	public void setOriginalRecord(String originalRecord) {
		this.originalRecord = originalRecord;
	}

	public String getModifyRecord() {
		return modifyRecord;
	}

	public void setModifyRecord(String modifyRecord) {
		this.modifyRecord = modifyRecord;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getRemoteMac() {
		return remoteMac;
	}

	public void setRemoteMac(String remoteMac) {
		this.remoteMac = remoteMac;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Override
	public String toString() {
		return "MerchantXRecord [mrchno=" + mrchno + ", mrchName=" + mrchName + ", modifyModule=" + modifyModule
				+ ", originalRecord=" + originalRecord + ", modifyRecord=" + modifyRecord + ", remoteAddr=" + remoteAddr
				+ ", remoteMac=" + remoteMac + ", userAgent=" + userAgent + ", createTime=" + createTime + ", createBy="
				+ createBy + "]";
	}
	
}
