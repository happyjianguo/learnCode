package cn.com.jansh.constant;

public enum CallBackCode {

	/*定时任务---单笔充值*/
	/**
	 * 网宿流量充值-受理成功
	 */
	WS_STRAIGHT_OK("10000"),
	/**
	 * 欧飞话费直充-受理成功
	 */
	OF_STRAIGHT_OK("1"),
	
	/*定时任务---刷新充值状态*/
	
	/**
	 * 充值成功（网宿）
	 */
	WS_REFRESH_OK("10100"),
	/**
	 * 接受请求查询成功（网宿）
	 */
	WS_REFRESH_REQUEST_OK("10000"),
	/**
	 * 处理中（网宿）
	 */
	WS_REFRESH_HANDLE("20407"),
	/**
	 * 订单不存在（网宿）
	 */
	WS_REFRESH_NOORDER("20408"),
	
	/**
	 * 充值成功（欧飞）
	 */
	OF_REFRESH_OK("1"),
	/**
	 * 处理中（欧飞）
	 */
	OF_REFRESH_HANDLE("0"),
	/**
	 * 充值失败
	 */
	OF_REFRESH_ERROR("9"),
	/**
	 * 无订单
	 */
	OF_REFRESH_NOORDER("-1")
	;
	
	/* ======================================= */
	private final String value;

	private CallBackCode(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
