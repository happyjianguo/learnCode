/**
 * ContextCode.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年10月24日
 */
package cn.com.jansh.constant;

/**
 * 系统参数变量
 * @author xieliangliang
 * @version 1.0
 */
public enum ContextCode {
	/**
	 * 活动渠道--微信
	 */
	ACTIONCHANNEL_0("0"),
	/**
	 * 活动渠道--APP
	 */
	ACTIONCHANNEL_1("1"),
	/**
	 * 活动状态--未上线
	 */
	ACTIVITY_STATUS_0("0"),
	/**
	 * 活动状态--上线
	 */
	ACTIVITY_STATUS_1("1"),
	/**
	 * 活动状态--下线
	 */
	ACTIVITY_STATUS_2("2"),
	/**
	 * 活动状态--推荐
	 */
	ACTIVITY_STATUS_3("3"),
	/**
	 * 最高級机构id
	 */
	ORGINATION_PARENT_ORGID("00000"),
	/**
	 * 机构删除标志-未删除
	 */
	ORGINATION_DELFLAG_1("1"),
	/**
	 * 机构删除标志-已删除
	 */
	ORGINATION_DELFLAG_0("0"),
	/**
	 * 机构状态-未生效
	 */
	ORGINATION_STATUS_GUOQI("0"),
	/**
	 * 机构状态-生效
	 */
	ORGINATION_STATUS_SHENGXIAO("1"),
	/**
	 * 黑名单状态-过期
	 */
	BLACKLIST_STATUS_GUOQI("0"),
	/**
	 * 黑名单状态-生效
	 */
	BLACKLIST_STATUS_SHENGXIAO("1"),
	/**
	 * 公告状态-过期
	 */
	NOTICE_STATUS_GUOQI("2"),
	/**
	 * 公告状态-已发布
	 */
	NOTICE_STATUS_YIFABU("1"),
	/**
	 * 公告状态-未发布
	 */
	NOTICE_STATUS_WEIFABU("0"),
	/**
	 * 人工充值审核状态-待审核
	 */
	RECHARGE_RSTATUS_AUDIT("2"),
	/**
	 * 人工充值审核状态-审核失败
	 */
	RECHARGE_RSTATUS_FAILE("1"),
	/**
	 * 人工充值审核状态-审核通过
	 */
	RECHARGE_RSTATUS_SUCCES("0"),
	/**
	 * 用户状态-正常
	 */
	USER_STATUS_OK("1"),
	/**
	 * 会员默认机构的系统参数ID
	 */
	USER_ROLE_ID("user_role_id"),
	/**
	 * 充值流水状态：成功
	 */
	RECHARG_ERECORD_SUCCES("1"),
	/**
	 * 充值流水支付方式:0-线下
	 */
	RECHARG_PAYTYPE_SUCCES("op"),
	/**
	 * 记录充值流水的USERID
	 */
	RECHARG_USERID_NAME("系统充值"),
	/**
	 * 活动订单未审核状态
	 */
	PRELIMINARY_STATUS_WAIT("0"),
	/**
	 * 活动模板使用次数
	 */
	GAMETEMPLATE_USETIMES("0"),
	/**
	 * 活动模板状态-未生效
	 */
	GAMETEMPLATE_TEMPSTATUS("0"),
	/**
	 * 活动参数状态-已发布
	 */
	GAMEPARAM_STATUS("1"),
	/**
	 * 公众号状态-生效
	 */
	APPID_STATUS_1("1")
	;
	
	private final String value;

	ContextCode(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

}
