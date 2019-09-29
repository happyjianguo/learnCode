/**
 * ITradeComm.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司
 * 创建:YangFan 2015-7-16
 */
package cn.com.jansh.ebank.ife.ibank.common;


/**
 * 功能: 通讯组件接口<br>
 * @author YangFan
 * @version 1.0
 */
public interface ITradeComm {
	/**
	 * 交易通用接口
	 * 
	 * @param tradeSet 上层交易实例, 用于获取一些通讯参数信息
	 * @param tradeMessage 上传字节流
	 * @param tradeCode 交易码
	 * @return 下传字节流
	 * @throws ITradeCommException异常
	 */
	public byte[] execComm(TradeSet tradeSet, StringBuffer tradeMessage, String tradeCode) throws ITradeCommException;
}
