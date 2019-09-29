
package cn.com.jansh.constant;

public enum AppCommonsCode {
	//**********************操作*******************
	/**
	 * 新增
	 */
	CREATE("create"),
	/**
	 * 修改
	 */
	UPDATE("update"),
	/**
	 * 手机号正则表达式
	 */
	MOBIL_PATTEN("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"),
	
	//**********************套餐状态*******************
	
	/**
	 * 充值套餐状态_开通
	 */
	RECHARGE_PACKAGE_STATUS_kt("kt"),
	/**
	 * 充值套餐状态_关闭
	 */
	RECHARGE_PACKAGE_STATUS_GB("gb"),
	
	//*********************充值状态*******************
	/**
	 * 未发送
	 */
	RECHARGEINTER_STATUS_0("0"),
	/**
	 * 充值成功
	 */
	RECHARGEINTER_STATUS_1("1"),
	/**
	 * 充值异常
	 */
	RECHARGEINTER_STATUS_2("2"),
	/**
	 * 无此订单
	 */
	RECHARGEINTER_STATUS_3("3"),
	/**
	 * 已受理
	 */
	RECHARGEINTER_STATUS_4("4"),
	/**
	 * 套餐不存在
	 */
	RECHARGEINTER_STATUS_5("5"),
	/**
	 * 超出游戏预算
	 */
	RECHARGEINTER_STATUS_6("6"),
	/**
	 * 已发送
	 */
	RECHARGEINTER_STATUS_7("7"),
	/**
	 * 无需发送（实物商品）
	 */
	RECHARGEINTER_STATUS_8("8"),
	/**
	 * 平台币不足
	 */
	RECHARGEINTER_STATUS_9("9"),
	
	/**
	 * 非法订单
	 */
	RECHARGEINTER_STATUS_10("10"),
	/**
	 * 充值接口，请求成功
	 */
	RECHARGEINTER_OK("000000"),
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
	//**********************订单审批状态********************
	/**
	 * 待审核
	 */
	RECHARGE_0("0"),
	/**
	 * 初审通过
	 */
	RECHARGE_1("1"),
	/**
	 * 初审失败
	 */
	RECHARGE_2("2"),
	/**
	 * 复审成功
	 *//*
	RECHARGE_3("3"),
	*//**
	 * 复审失败
	 *//*
	RECHARGE_4("4"),*/
	
	//**********************充值类型****************
	/**
	 * 商品类型-话费
	 */
	IPSTYPE_BILL("hf"),
	/**
	 * 商品类型-流量
	 */
	IPSTYPE_Flow("ll"),
	
	/***
	 * 奖品类型-虚拟奖品
	 */
	IPSTYPE_Fictitious("xn"),
	/**
	 * 奖品类型-实物奖品
	 */
	IPSTYPE_Material("sw"),
	/**
	 * 奖品类型-实物奖品Id
	 */
	IPSTYPE_Material_defaultId("000000"),
	/**
	 * 用户正常状态码
	 */
	UserRightStatus("1"),
	/**
	 * 注册用户默认状态码
	 */
	UserDefaultStatus("3"),
	//**********************营销 活动****************
	/**
	 * 活动默认时间显示
	 */
	GameDefaultTime("-------------------"),
	/**
	 * 活动微信渠道
	 */
	GameChannelWechat("0"),
	/**
	 * 活动app渠道
	 */
	GameChannelApp("1"),
	/**
	 * 游戏模板有效
	 */
	TempValidate("1"),
	/**
	 * 活动默认状态
	 */
	GameDefaultStatus("0"),
	/**
	 * 活动删除状态
	 */
	GameStatus_invalid("-1"),
	/**
	 * 活动发布成功
	 */
	GameStatus_publish("000000"),
	/**
	 * 活动未上线状态
	 */
	GameStatus_0("0"),
	/**
	 * 活动上线状态
	 */
	GameStatus_1("1"),
	/**
	 * 活动下线状态
	 */
	GameStatus_2("2"),
	/**
	 * 非用户默认公众号
	 */
	isdefault_0("0"),
	/**
	 * 用户默认公众号
	 */
	isdefault_1("1"),
	/**
	 * 活动参数状态-已发布
	 */
	GAMEPARAM_STATUS("1")
	;
	private final String value;


    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
	AppCommonsCode(String value) {
        this.value = value;
    }
    
    public String value() {
        return value;
    }
}
