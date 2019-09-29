package cn.yufu.system.modules.cortexs.entity;

import cn.yufu.system.common.persistence.DataEntity;

public class MSettleBillRecord extends DataEntity<MSettleBillRecord>{
	private static final long serialVersionUID = 1L;
	
	private String mrchno; //商户号

	private String mrchName;  //商户名称
	
	private String modifyModule;  //修改模块
	
	private String originalRecord; //原纪录
	
	private String modifyRecord; //修改记录
	
	private String remoteAddr;  //IP地址
	
	private String remoteMac; //MAC
	
	private String userAgent; //浏览器标识
	
	private String createTime; //修改时间
	
	private String beginCreateTime;
	
	private String endCreateTime;

    public String getMrchno() {
        return mrchno;
    }

    public void setMrchno(String mrchno) {
        this.mrchno = mrchno == null ? null : mrchno.trim();
    }

    public String getMrchName() {
        return mrchName;
    }

    public void setMrchName(String mrchName) {
        this.mrchName = mrchName == null ? null : mrchName.trim();
    }

    public String getModifyModule() {
        return modifyModule;
    }

    public void setModifyModule(String modifyModule) {
        this.modifyModule = modifyModule == null ? null : modifyModule.trim();
    }
    
    public String getOriginalRecord() {
        return originalRecord;
    }

    public void setOriginalRecord(String originalRecord) {
        this.originalRecord = originalRecord == null ? null : originalRecord.trim();
    }

    public String getModifyRecord() {
        return modifyRecord;
    }

    public void setModifyRecord(String modifyRecord) {
        this.modifyRecord = modifyRecord == null ? null : modifyRecord.trim();
    }
    
    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr == null ? null : remoteAddr.trim();
    }

    public String getRemoteMac() {
        return remoteMac;
    }

    public void setRemoteMac(String remoteMac) {
        this.remoteMac = remoteMac == null ? null : remoteMac.trim();
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getBeginCreateTime() {
		return beginCreateTime;
	}

	public void setBeginCreateTime(String beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}

	public String getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}
	
}
