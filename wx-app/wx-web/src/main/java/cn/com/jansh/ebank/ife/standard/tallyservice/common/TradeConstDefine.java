/**
 * TradeConstDefine.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司
 * 创建:YangFan 2015-7-16
 */
package cn.com.jansh.ebank.ife.standard.tallyservice.common;

/**
 * 账务交易常量定义
 * @author YangFan
 * @version 1.0.0
 */
public class TradeConstDefine {
	/**
	 * 交易类型枚举
	 */
	public enum TradeType {
		/**
		 * 账户验证查询(1110)
		 */
		doAccountVerTally(1110),
		/**
		 * 账户积分查询(1111)
		 */
		doAccountIntTally(1111),
		
		/**
		 * 借记卡账户余额查询(2110)
		 */
		doDebitBalTally(2110),
		/**
		 * 借记卡账户明细查询(2111)
		 */
		doDebitDetilTally(2111),
		
		/**
		 * 信用卡账单查询(3110)
		 */
		doCreditBillTally(3110),
		/**
		 * 信用卡消费查询(3111)
		 */
		doCreditConsumTally(3111);
		
		private int value;
		private TradeType(int value){
			this.value = value;
		}
		public int getValue(){
			return value;
		}
	}
	
	/**
	 * 交易结果集
	 */
	public class TradeResult{
		/**
		 * 失败
		 */
		public static final int FAIL = 0;
		/**
		 * 成功
		 */
		public static final int SUCCESS = 1;
		/**
		 *未知类型的返回值(通讯超时) 
		 */
		public static final int OTHERREASON =2;
		/**
		 * 没有找到原交易 (只用于反交易)
		 */
		public static final int NOFORMERLYTRADE = 3;
		/**
		 * 原交易失败 (只用于反交易)
		 */
		public static final int FORMERLYTRADEFAIL = 4;
	}
	
    /**
	 * 请求核心通讯参数集
	 */
	public class TradeHostParam {
		/**
		 * trade host IP
		 */
		public static final String hostip = "192.168.23.110";
		/**
		 * trade host PORT
		 */
		public static final int hostport =1988;
		/**
		 * trade host TIMEOUT
		 */
		public static final int hosttimeout =2000;
	}
	
}
