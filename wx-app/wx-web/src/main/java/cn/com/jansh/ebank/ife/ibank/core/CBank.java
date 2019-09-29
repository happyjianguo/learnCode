/**
 * CBank.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司
 * 创建:YangFan 2015-7-16
 */
package cn.com.jansh.ebank.ife.ibank.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.com.jansh.ebank.ife.ibank.common.IBankAssembleService;
import cn.com.jansh.ebank.ife.ibank.common.IBankException;
import cn.com.jansh.ebank.ife.ibank.common.TradeSet;


/**
 * 功能: IBank服务, IBank对于应用上层提供交易接口<br>
 * @author YangFan
 * @version 1.0
 */
public class CBank extends SocketComm{
	private static final Logger logger = LogManager.getLogger(CBank.class);
	public TradeSet execTrade(TradeSet tradeSet, String ...info) throws IBankException {
		byte[] dataDown = null;
		//查找相关交易模型
		String tradeCode = "";
		StringBuffer tradeMessage = new StringBuffer();
		IBankAssembleService ibas = new IBankAssembleService();
		try {
			tradeCode = tradeSet.getTradeId();
			logger.info("trade id(交易模板ID): " + tradeCode);
			//根据交易标识查找到配置模板信息
			String msgBody = ibas.getMessageBody(Integer.parseInt(tradeCode), tradeSet);
			String msgHead = ibas.getMessageHead(msgBody.length(), tradeCode);
			tradeMessage.append(msgHead);
			tradeMessage.append(msgBody);
		} catch (Exception e) {
			logger.error("查找相关交易模型.异常："+e.getMessage());
		}

		//查找通讯服务
		try {
			logger.info("上传报文: " + tradeMessage.toString());
			//通讯得到下传报文
			dataDown = execComm(tradeSet, tradeMessage, tradeCode);
			logger.info("下传报文: " + new String(dataDown));
		} catch (Exception e) {
			logger.error("查找通讯服务.异常："+e.getMessage());
		}
		
		//解析下传报文
		try {
			//下传字节流
			ibas.setPackageData(tradeSet, dataDown);
			logger.info("CBank服务处理完成");
		} catch (Exception e) {
			logger.error("解析下传报文.异常："+e.getMessage());
		}
		return tradeSet;
	}
}
