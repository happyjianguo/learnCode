
package cn.com.jansh.constant;

public enum AppPayOrderCode {
	//**********************操作*******************
	/**
	 * 新增
	 */
	CREATE("create"),
	/**
	 * 修改
	 */
	UPDATE("update"),
	//*********************充值接口返回状态*******************
	
	/**
	 * 充值接口，成功
	 */
	RECHARGEINTER_OK("000000"),
	/**
	 * 充值接口，失败
	 */
	RECHARGEINTER_ERROR("999999"),
	/**
	 * 订单处理中
	 */
	RECHARGEINTER_ERROR_WAIT("900003"),
	/**
	 * 充值接口，解析参数异常
	 */
	RECHARGEINTER_ERROR_PARSE("900002"),
	/**
	 * 充值接口，非法请求
	 */
	RECHARGEINTER_ERROR_REQUEST("900001"),
	
	//*********************运营商********************
	/**
	 * 联通
	 */
	CHINA_UNICOM("lt"),
	/**
	 * 移动
	 */
	CHINA_MOBILE_COMMUNICATIONS_CORPORATION("yd"),	
	/**
	 * 电信
	 */
	CHINA_TELICOM("dx"),
	//********************
	/**
	 * 全国
	 */
	CHINA_PNO("qg"),
	//**********************订单状态********************
	/**
	 * 进入充值流水表（未发送）
	 */
	RECHARGE_0("0"),
	/**
	 * 受理成功
	 */
	RECHARGE_1("1"),
	/**
	 * 受理失败
	 */
	RECHARGE_2("2"),
	/**
	 * 充值成功
	 */
	RECHARGE_3("3"),
	/**
	 * 充值失败
	 */
	RECHARGE_4("4"),
	/**
	 * 套餐不存在
	 */
	RECHARGE_5("5"),
	/**
	 * 审批通过
	 */
	EXAMINE_PASS("1"),
	/**
	 * 审批不通过
	 */
	RECHARGE_BAN("0"),
	//**********************充值类型****************
	/**
	 * 充值类型-话费
	 */
	IPSTYPE_BILL("hf"),
	/**
	 * 充值类型-流量
	 */
	IPSTYPE_Flow("ll"),
	
	//**********************直充********************
		/**
		 * 查询次数-0
		 */
		QUERYNUM_0("0"),
		/**
		 * 查询次数限制 
		 */
		QUERYNUM_6("6"),
		/**
		 * 未支付
		 */
		NOPAYMENT("0"),
		/**
		 * 成功
		 */
		OLSUCCEED("1"),
		/**
		 * 失败
		 */
		OLFAILED("2"),
		/**
		 * 已受理
		 */
		BEENACCEPTED("3"),
		/**
		 * 全国
		 */
		CHINA_NU("qg"),
		/**
		 * 全国
		 */
		LOCAL_TYPE_0("0"),
		/**
		 * 本地
		 */
		LOCAL_TYPE_1("1"),
		/**
		 * 本地
		 */
		CHINA_BD("bd"),
		/**
		 * 支付方式-银联
		 */
		UNIONOPTION("yl"),
		/**
		 * 支付方式-微信
		 */
	
	WXCHATOPTION("wx"),
	//**********************直充消费交易状态码********************
	/**银联或微信-成功 */
	RESSUCCEED("000000"),
	/**银联-处理中 */
	YLRESUNKNOWN("000001"),
	/**银联-系统忙 */
	YLRESBUSY("999999"),
	/**原交易信息不存在 */
	YLRESNOTRADE("900017"),
	/**银联处理失败 */
	YLRESPFAIl("01"),
	/**银联处理成功 */
	YLSUCCESS("00"),
	/**银联有缺陷的成功 */
	YLA6("A6"),
	/**微信交易回调状态 */
	WXCHATSTATUS("SUCCESS"),
	/** 微信-支付中*/
	WXUSERPAYING("USERPAYING"),
	//**********************直充消费退款状态码********************
	/**未退款 */
	NOREFUND("0"),
	/**退款成功 */
	REFUNDSUCCES("1"),
	/**退款失败 */
	REFUNDFAIL("2");
	
	private final String value;

	

    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
	AppPayOrderCode(String value) {
        this.value = value;
    }
    
    public String value() {
        return value;
    }
}
