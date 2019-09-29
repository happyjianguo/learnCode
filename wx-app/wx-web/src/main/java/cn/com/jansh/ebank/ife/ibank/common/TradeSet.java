/**
 * TradeSet.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司
 * 创建:YangFan 2015-7-16
 */
package cn.com.jansh.ebank.ife.ibank.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能:IBank交易对象,此对象封装了应用上层与IBank通讯所需传入的参数<br>
 * @author YangFan 
 * @version 1.0
 */
public class TradeSet implements Serializable{

	private static final long serialVersionUID = 2025882508771781576L;
	
	private String 	tradeId;		//本方定义的交易标识
	private long 	businessId;		//相对应的业务标识，可选
	private boolean tradeResult;	//交易结果
	private String 	hostCode; 	    //主机返回代码
	private String 	hostMsg; 		//主机返回信息

	private Map<String,Object> upParams = new HashMap<String,Object>();	//上传MAP

	public TradeSet(String tradeId) {
		this.tradeId = tradeId;
	}

	/**
	 * 获得上行数据中键为Key的对象
	 * @param key 键值
	 * @return 存放在上行数据中的对象或null
	 */
	public Object get(String key) {
		return upParams.get(key);
	}
	
	/**
	 * 上行数据的键值存放
	 * @param key 存放在上行数据中的键
	 * @param obj 存放在上行数据中对应于键的对象
	 */
	public void put(String key, Object obj) {
		upParams.put(key, obj);
	}

	
	
	
	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}

	public String getHostCode() {
		return hostCode;
	}

	public void setHostCode(String hostCode) {
		this.hostCode = hostCode;
	}

	public String getHostMsg() {
		return hostMsg;
	}

	public void setHostMsg(String hostMsg) {
		this.hostMsg = hostMsg;
	}

	public boolean getTradeResult() {
		return tradeResult;
	}

	public void setTradeResult(boolean tradeResult) {
		this.tradeResult = tradeResult;
	}
}
