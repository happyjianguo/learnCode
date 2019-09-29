/**
 * ConstDefine.java
 * 版权所有(C) 2012 北京坚石诚信科技有限公司 
 * 创建:YangFan 2012-4-16
 */
package cn.com.jansh.config;

/**
 * 静态常量定义
 * @author YangFan
 * @version 1.0.0
 */
public class ConstDefine {
	
	public class ConfigMode {
		/** 国家地区语言版本**/
		public static final String USERINFO_LANG = "zh_CN"; 
		/** 获取用户基本信息（包括UnionID机制）**/
		public static final String USERINFO_HTTP_URL = "https://api.weixin.qq.com/cgi-bin/user/info"; 
		/** 模板信息（交易提醒通知）**/
		public static final String TEMPLATE_URL_MSG286 = "https://api.weixin.qq.com/cgi-bin/message/template/send"; 
	}
	/**
	 * 银行卡类型
	 * (1，借记卡；2，信用卡；3，存折)
	 */
	public class CardMode {
		public static final String CARD = "1";
		public static final String CREDIT = "2";
		public static final String BANKBOOK = "3";
	}
	
	/**
	 * 验证码类型
	 * @author duanmuyn
	 *
	 */
	public class CodeType {
		public static final String IMAGECODE = "IMAGECODE";
		public static final String MSGCODE = "MSGCODE";
	}

	/**
	 * 报文编号
	 */
	public class MsgCdMode {
		
		/**
		 * 借记卡账户积分
		 */
		public static final String WX15601002 ="WX.156.010.02";
		
		/**
		 * 借记卡账户余额查询
		 */
		public static final String WX15601001 ="WX.156.010.01";
		
		/**
		 * 信用卡账单查询
		 */
		public static final String WX15601003 ="WX.156.010.03";
		
		/**
		 * 信用卡积分查询
		 */
		public static final String WX15601004 ="WX.156.010.04";
		
		/**
		 * 获取用户基本信息
		 */
		public static final String WX27600101 = "WX.276.001.01";
		
		/**
		 * 借记卡绑定
		 */
		public static final String WX30200201 = "WX.302.002.01";
		
		/**
		 * 信用卡绑定
		 */
		public static final String WX30800301 = "WX.308.003.01";
		
		/**
		 * 借记卡账户明细查询
		 */
		public static final String WX30400301 = "WX.304.003.01";
		
		/**
		 *信用卡消费查询
		 */
		public static final String WX31000301 = "WX.310.003.01";
		
		/**
		 *模板消息(交易提醒通知)
		 */
		public static final String WX28600101 = "WX.286.001.01";
		
		/**
		 * 关键字
		 */
		public static final String WX00000001 ="WX.000.000.01";
		
	}
	
	/**
	 * 模板占位符变量
	 */
	public class PlaceholderMode {
		
		/**
		 * 卡号后四位
		 */
		public static final String CARDNO = "[[$money_card_n$]]"; 
		/**
		 * 借记卡账户余额
		 */
		public static final String MONEYD = "[[$money_acc_d$]]"; 
		
		/**
		 * 借记卡账户绑定跳转超链接
		 */
		public static final String URLDBIND = "[[#url_bind_d#]]";
		
		/**
		 * 贷记卡账户绑定跳转超链接
		 */
		public static final String URLCBIND = "[[#url_bind_c#]]";
		
		/**
		 * 文本消息跳转超链接
		 */
		public static final String URLTBIND = "[[#url_bind_t#]]";
		
		/**
		 * 贷记卡积分
		 */
		public static final String INTEGRALC = "[[&integral_c&]]";
		
		/**
		 * 借记卡积分
		 */
		public static final String INTEGRALD = "[[&integral_d&]]";

		/**
		 * 信用卡消费查询
		 */
		public static final String CONSUMEDC = "[[#url_consume_c#]]";
		
		/**
		 * 借记卡账户明细查询
		 */
		public static final String ACCDETQUERYD = "[[#url_accdetquery_d#]]";
		
		/**
		 * 模板消息（交易提醒通知）
		 * 户名
		 */
		public static final String TEMPLATE_USERNAME = "[[&template_user&]]";
		/**
		 * 模板消息（交易提醒通知）
		 * 卡号后4位
		 */
		public static final String TEMPLATE_CARD = "[[&template_card&]]";
		/**
		 * 模板消息（交易提醒通知）
		 * 交易金额
		 */
		public static final String TEMPLATE_MONEY = "[[$template_money$]]";
		
		/**
		 * 信用卡账单起始日期
		 */
		public static final String CBILLSTARTDATE = "[[&c_bill_startdate&]]";
		
		/**
		 * 信用卡账单终止日期
		 */
		public static final String CBILLENDDATE = "[[&c_bill_enddate&]]";
		
		/**
		 * 信用卡账单货币符号
		 */
		public static final String CBILLCURRCODE = "[[&c_bill_currcode&]]";
		
		/**
		 * 信用卡账单消费金额
		 */
		public static final String CBILLSONMONEY = "[[&c_bill_sonmoney&]]";
		
		/**
		 * 信用卡账单利息
		 */
		public static final String CBILLINTEREST = "[[&c_bill_interest&]]";
		
		/**
		 * 信用卡每月还款额
		 */
		public static final String CBILLALSOMONEY = "[[&c_bill_alsomoney&]]";
	}
	
	/**
	 * 字典表中获取对应跳转的URL参数
	 */
	public class PubUrlMode {
		
		/**
		 * 类型标识
		 */
		public static final String NODETAG = "URL";
		
		/**
		 * 节点定义--借记卡绑定
		 */
		public static final String NODETYPE_DBIND = "url_bind_d";
		
		/**
		 * 节点定义--贷记卡绑定
		 */
		public static final String NODETYPE_CBIND = "url_bind_c";
		
		/**
		 * 节点定义--借记卡账户明细查询
		 */
		public static final String NODETYPE_DACCQUERY = "url_accdetquery_d";
		
		/**
		 * 节点定义--信用卡消费查询
		 */
		public static final String NODETYPE_CCONSUME = "url_consume_c";
	}

}
