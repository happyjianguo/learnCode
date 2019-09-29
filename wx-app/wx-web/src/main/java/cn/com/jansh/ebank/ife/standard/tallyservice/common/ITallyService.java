/**
 * ITallyService.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司
 * 创建:杨帆 2015-7-16
 */
package cn.com.jansh.ebank.ife.standard.tallyservice.common;

import cn.com.jansh.ebank.ife.standard.tallyservice.common.TradeConstDefine.TradeType;

/**
 * 账务交易服务接口
 * @author YangFan
 * @version 1.0
 */
public interface ITallyService {

	/**
	 * 账务交易服务
	 * @param object 上核心需要传递的参数
	 * @param type 交易类型
	 * @return 交易返回值(1成功，0失败)
	 * @throws Exception 
	 */
	public int tallyTrade(Object object,TradeType type) throws Exception;

}
