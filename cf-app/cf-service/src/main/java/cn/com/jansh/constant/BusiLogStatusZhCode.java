package cn.com.jansh.constant;

public enum BusiLogStatusZhCode {
	/**
	 * 本地数据库中没有此条数据-0
	 */
	UNKNOWZH("240000"),
	/**
	 * 平账-1
	 */
	SUCCEEDZH("240001"),
	/**
	 * 订单金额小于供应商报价-2
	 */
	LESSZH("240002"),
	/**
	 * 订单金额大于供应商报价-3
	 */
	 MOREZH("240003")
	;

	private final String value;

	BusiLogStatusZhCode(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
