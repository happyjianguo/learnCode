
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
	//**********************状态*********************
	/**
	 * 状态开通
	 */
	STATUS_OK("kt"),
	/**
	 * 状态关闭
	 */
	STATUS_NO("gb"),
	//*********************审批不同意****************
	/**
	 * 等待审批
	 */
	AUDIT_WAIT("1"),
	/**
	 * 审批同意
	 */
	AUDIT_OK("66"),
	/**
	 * 审批不同意
	 */
	AUDIT_ERROR("44"),
	//*********************充值来源*******************
	/**
	 * 不需要回调
	 */
	CALLBACK_NOT("0"),
	/**
	 * 回调待待发送
	 */
	CALLBACK_INIT("1"),
	/**
	 * 回调已发送
	 */
	CALLBACK_OK("2"),
	
	
	
	/**
	 * 接口充值
	 */
	SOURCE_INTERFACE("if"),
	/**
	 * 平台充值
	 */
	SOURCE_PLATFORM("pf"),
	
	//*********************充值接口*******************
	
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
	//**********************充值（单笔）********************
	/**
	 * 进入充值流水表
	 */
	RECHARGE_30("30"),
	/**
	 * 审批通过
	 */
	RECHARGE_31("31"),
	/**
	 * 审批失败
	 */
	RECHARGE_32("32"),
	/**
	 * 定时任务抓取标记
	 */
	RECHARGE_33("33"),
/*	*//**
	 * 受理成功
	 *//*
	RECHARGE_34("34"),
	*//**
	 * 受理失败
	 *//*
	RECHARGE_35("35"),*/
	/**
	 * 网络异常
	 */
/*	RECHARGE_36("36"),
	*//**
	 * 无接入者报价异常
	 *//*
	RECHARGE_37("37"),
	*//**
	 * 无供应商报价异常
	 *//*
	RECHARGE_38("38"),
	*//**
	 * 接入者欠费
	 *//*
	RECHARGE_39("39"),
	*//**
	 * 接入者报价状态为关闭
	 *//*
	RECHARGE_40("40"),	
	*//**
	 * 供应商报价状态为关闭
	 *//*
	RECHARGE_41("41"),
	*//**
	 * 供应商报价状态及接入者报价状态均为关闭
	 *//*
	RECHARGE_42("42"),
	*//**
	 * 接入者状态为关闭
	 *//*
	RECHARGE_43("43"),*/
	//**********************充值（批量）********************
	/**
	 * 上传充值表
	 */
	RECHARGE_20("20"),
	
	/**
	 * 待审批
	 */
	RECHARGE_21("21"),
	
	/**
	 * 审批通过
	 */
	RECHARGE_22("22"),
	/**
	 * 审批失败
	 */
	RECHARGE_23("23"),
	
	//**********************充值类型****************
	/**
	 * 充值类型-话费
	 */
	IPSTYPE_BILL("hf"),
	/**
	 * 充值类型-流量
	 */
	IPSTYPE_Flow("ll");
	
	private final String value;


    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
	AppCommonsCode(String value) {
        this.value = value;
    }
    
    public String value() {
        return value;
    }
}
