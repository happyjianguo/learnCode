package cn.com.jansh.constant;

public enum BusiLogStatus {
	/*****************************status*********************************
	/**
	 * 录入-0
	 */
	INPUT("0"),
	/**
	 * 成功-1
	 */
	SUCCEED("1"),
	/**
	 * 失败-2
	 */
	FAIL("2"),
	/**
	 * 未知，通讯超时-3
	 */
	UNKNOW("3"),
	/**
	 * 已受理-4
	 */
	ACCEPT("4"),
	
	/*********************************response返回码前缀*******************************/
	/**
	 * 网宿平台返回码前缀
	 */
	PLATFORM_WS("WS"),
	/**
	 * 欧飞平台返回码前缀
	 */
	PLATFORM_OF("OF"),
	/**
	 * 话费流量充值平台返回码前缀
	 */
	PLATFORM_CF("CF"),
	
	/*********************************话费流量充值平台返回码*******************************/
	/**
	 * 无接入者报价
	 */
	NO_ACCESSPRICE("270005"),
	/**
	 * 无供应商报价
	 */
	NO_SUPPLIERPRICE("270006"),
	/**
	 * 无接入者及供应商报价
	 */
	NO_ACCESS_SUPPLIER_PRICE("270007"),
	/**
	 * 欠费
	 */
	NO_ARREARS("270008"),
	/**
	 * 接入者报价状态为关闭
	 */
	STATUS_ACCESSPRICE_NO("270009"),
	/**
	 * 接入者状态为关闭
	 */
	STATUS_ACCESS_NO("270010"),
	/**
	 * 供应商报价状态为关闭
	 */
	STATUS_SUPPLIER_NO("270011"),
	
	/**
	 * 手机号错误，无归属地
	 */
	STATUS_PROVINCE_NO("270012"),
	
	/**
	 * 平台校验正常
	 */
	STATUS_OK("000000");
	
	
	;

	private final String value;

	BusiLogStatus(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
