package cn.yufu.SDMTPlatform.entity;

public class TerminalSDMT extends TerminalSDMTKey {
	//扩张信息
	private String upgradeDate;		// 升级日期
	private String upgradeVersion;		// 升级版本号
	private String storeContacts;		// 门店联系人
	private String storePhone;		// 门店电话
	private String merchantAdvisor;		// 商户顾问
	private String terminalArea;		// 区域
//	private String remarks;		// 区域
//	private String terminalProvince;		// 省份(*)
//	private String terminalCity;		// 城市(*)
//	private String terminalZone;		// 区(*)
	private String terminalPosition;		// 终端装机地址
		
	
    private String edcType;//终端设备型号

	@Override
	public String toString() {
		return "TerminalSDMT [upgradeDate=" + upgradeDate + ", upgradeVersion=" + upgradeVersion + ", storeContacts="
				+ storeContacts + ", storePhone=" + storePhone + ", merchantAdvisor=" + merchantAdvisor
				+ ", terminalArea=" + terminalArea + ", terminalPosition=" + terminalPosition + ", edcType=" + edcType
				+ ", softVer=" + softVer + ", downloadFlag=" + downloadFlag + ", addDate=" + addDate + ", termAddress="
				+ termAddress + ", actDate=" + actDate + ", actTime=" + actTime + ", termTel=" + termTel + ", state="
				+ state + ", province=" + province + ", cityNo=" + cityNo + ", zone=" + zone + ", settleMrchAccId="
				+ settleMrchAccId + ", xTimezone=" + xTimezone + ", sdFlag=" + sdFlag + ", sdError=" + sdError
				+ ", xFlag=" + xFlag + ", xError=" + xError + ", yufuFlag=" + yufuFlag + ", yufuError=" + yufuError
				+ ", xBakFlag=" + xBakFlag + ", xBakError=" + xBakError + ", seqTermposId=" + seqTermposId
				+ ", seqEnckeyId=" + seqEnckeyId + ", consumpCategory=" + consumpCategory + "]";
	}

	public String getTerminalPosition() {
		return terminalPosition;
	}

	public void setTerminalPosition(String terminalPosition) {
		this.terminalPosition = terminalPosition;
	}

	public String getUpgradeDate() {
		return upgradeDate;
	}

	public void setUpgradeDate(String upgradeDate) {
		this.upgradeDate = upgradeDate;
	}

	public String getUpgradeVersion() {
		return upgradeVersion;
	}

	public void setUpgradeVersion(String upgradeVersion) {
		this.upgradeVersion = upgradeVersion;
	}

	public String getStoreContacts() {
		return storeContacts;
	}

	public void setStoreContacts(String storeContacts) {
		this.storeContacts = storeContacts;
	}

	public String getStorePhone() {
		return storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	public String getMerchantAdvisor() {
		return merchantAdvisor;
	}

	public void setMerchantAdvisor(String merchantAdvisor) {
		this.merchantAdvisor = merchantAdvisor;
	}

	public String getTerminalArea() {
		return terminalArea;
	}

	public void setTerminalArea(String terminalArea) {
		this.terminalArea = terminalArea;
	}

	private String softVer;//终端软件版本

    private String downloadFlag;//参数下载标志

    private String addDate;//增加日期

    private String termAddress;//终端所在位置

    private Integer actDate;//密钥生效日期

    private Long actTime;//密钥生效时间

    private String termTel;//终端使用的电话号码

    private Integer state;//裕福原有状态

    private String province;//省份

    private String cityNo;//城市

    private String zone;//区

    private Long settleMrchAccId;

    private String xTimezone;//费率(单位：%)

    private String sdFlag;

    private String sdError;

    private String xFlag;

    private String xError;

    private String yufuFlag;

    private String yufuError;

    private String xBakFlag;

    private String xBakError;

    private Long seqTermposId;

    private Long seqEnckeyId;

    private String consumpCategory;//消费场景

    public String getEdcType() {
        return edcType;
    }

    public void setEdcType(String edcType) {
        this.edcType = edcType == null ? null : edcType.trim();
    }

    public String getSoftVer() {
        return softVer;
    }

    public void setSoftVer(String softVer) {
        this.softVer = softVer == null ? null : softVer.trim();
    }

    public String getDownloadFlag() {
        return downloadFlag;
    }

    public void setDownloadFlag(String downloadFlag) {
        this.downloadFlag = downloadFlag == null ? null : downloadFlag.trim();
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate == null ? null : addDate.trim();
    }

    public String getTermAddress() {
        return termAddress;
    }

    public void setTermAddress(String termAddress) {
        this.termAddress = termAddress == null ? null : termAddress.trim();
    }

    public Integer getActDate() {
        return actDate;
    }

    public void setActDate(Integer actDate) {
        this.actDate = actDate;
    }

    public Long getActTime() {
        return actTime;
    }

    public void setActTime(Long actTime) {
        this.actTime = actTime;
    }

    public String getTermTel() {
        return termTel;
    }

    public void setTermTel(String termTel) {
        this.termTel = termTel == null ? null : termTel.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo == null ? null : cityNo.trim();
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone == null ? null : zone.trim();
    }

    public Long getSettleMrchAccId() {
        return settleMrchAccId;
    }

    public void setSettleMrchAccId(Long settleMrchAccId) {
        this.settleMrchAccId = settleMrchAccId;
    }

    public String getxTimezone() {
        return xTimezone;
    }

    public void setxTimezone(String xTimezone) {
        this.xTimezone = xTimezone == null ? null : xTimezone.trim();
    }

    public String getSdFlag() {
        return sdFlag;
    }

    public void setSdFlag(String sdFlag) {
        this.sdFlag = sdFlag == null ? null : sdFlag.trim();
    }

    public String getSdError() {
        return sdError;
    }

    public void setSdError(String sdError) {
        this.sdError = sdError == null ? null : sdError.trim();
    }

    public String getxFlag() {
        return xFlag;
    }

    public void setxFlag(String xFlag) {
        this.xFlag = xFlag == null ? null : xFlag.trim();
    }

    public String getxError() {
        return xError;
    }

    public void setxError(String xError) {
        this.xError = xError == null ? null : xError.trim();
    }

    public String getYufuFlag() {
        return yufuFlag;
    }

    public void setYufuFlag(String yufuFlag) {
        this.yufuFlag = yufuFlag == null ? null : yufuFlag.trim();
    }

    public String getYufuError() {
        return yufuError;
    }

    public void setYufuError(String yufuError) {
        this.yufuError = yufuError == null ? null : yufuError.trim();
    }

    public String getxBakFlag() {
        return xBakFlag;
    }

    public void setxBakFlag(String xBakFlag) {
        this.xBakFlag = xBakFlag == null ? null : xBakFlag.trim();
    }

    public String getxBakError() {
        return xBakError;
    }

    public void setxBakError(String xBakError) {
        this.xBakError = xBakError == null ? null : xBakError.trim();
    }

    public Long getSeqTermposId() {
        return seqTermposId;
    }

    public void setSeqTermposId(Long seqTermposId) {
        this.seqTermposId = seqTermposId;
    }

    public Long getSeqEnckeyId() {
        return seqEnckeyId;
    }

    public void setSeqEnckeyId(Long seqEnckeyId) {
        this.seqEnckeyId = seqEnckeyId;
    }

    public String getConsumpCategory() {
        return consumpCategory;
    }

    public void setConsumpCategory(String consumpCategory) {
        this.consumpCategory = consumpCategory == null ? null : consumpCategory.trim();
    }
}