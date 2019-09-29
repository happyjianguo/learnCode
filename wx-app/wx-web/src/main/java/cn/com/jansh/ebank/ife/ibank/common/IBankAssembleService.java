/**
 * IBankAssembleService.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司
 * 创建:YangFan 2015-8-4
 */
package cn.com.jansh.ebank.ife.ibank.common;

import java.io.UnsupportedEncodingException;


import cn.com.jansh.ebank.ife.ibank.HostMode1110;
import cn.com.jansh.ebank.ife.ibank.HostMode1111;
import cn.com.jansh.ebank.ife.ibank.HostMode2110;
import cn.com.jansh.ebank.ife.ibank.HostMode2111;
import cn.com.jansh.ebank.ife.ibank.HostMode3110;
import cn.com.jansh.ebank.ife.ibank.HostMode3111;
import cn.com.jansh.ebank.ife.ibank.common.IBankAssembleConfig.MessageHead;

/**
 * IBankAssembleService服务, 用于将报文信息的装配与反装配
 * @author YangFan
 * @version 1.0.0
 */
public class IBankAssembleService {
	
	/**
	 * 
	 * 上传报文头
	 * @param msgBodyLength 报文体长度
	 * @return 返回报文头字符串
	 * 00000216990120RQ0VOST0001################        tp9901203
	 */
	public String getMessageHead(int msgBodyLength, String tradeCode){
		String messageHeadString = "";
		if(msgBodyLength>0 && tradeCode.length()>0){
			messageHeadString += IBankAssembleUpMessage.getLengthString(msgBodyLength);
			messageHeadString += tradeCode;
			messageHeadString += MessageHead.REQUEST + MessageHead.NOFILE + MessageHead.COMMONSTRING;
			messageHeadString += MessageHead.PARTSTRING + tradeCode + MessageHead.SERVERTCP;
		}

		return messageHeadString;
	}
	
	/**
	 * 
	 * 上传报文体
	 * @param tradeCode 交易模板ID
	 * @param tradeSet 交易模板数据
	 * @return 返回报文体字符串
	 */
	public String getMessageBody(int tradeCode, TradeSet tradeSet){
		String messageBodyString = "";
		switch(tradeCode){
		case 1110:
			HostMode1110 msg1110 = new HostMode1110();
			messageBodyString = IBankAssembleUpMessage.getTallyNodeString(msg1110.getTagId(), msg1110.getTagFormat(), msg1110.getTagName(), tradeSet);
			break;
		case 1111:
			HostMode1111 msg1111 = new HostMode1111();
			messageBodyString = IBankAssembleUpMessage.getTallyNodeString(msg1111.getTagId(), msg1111.getTagFormat(), msg1111.getTagName(), tradeSet);
			break;
		case 2110:
			HostMode2110 msg2110 = new HostMode2110();
			messageBodyString = IBankAssembleUpMessage.getTallyNodeString(msg2110.getTagId(), msg2110.getTagFormat(), msg2110.getTagName(), tradeSet);
			break;
		case 2111:
			HostMode2111 msg2111 = new HostMode2111();
			messageBodyString = IBankAssembleUpMessage.getTallyNodeString(msg2111.getTagId(), msg2111.getTagFormat(), msg2111.getTagName(), tradeSet);
			break;
		case 3110:
			HostMode3110 msg3110 = new HostMode3110();
			messageBodyString = IBankAssembleUpMessage.getTallyNodeString(msg3110.getTagId(), msg3110.getTagFormat(), msg3110.getTagName(), tradeSet);
			break;
		case 3111:
			HostMode3111 msg3111 = new HostMode3111();
			messageBodyString = IBankAssembleUpMessage.getTallyNodeString(msg3111.getTagId(), msg3111.getTagFormat(), msg3111.getTagName(), tradeSet);
			break;
		}
		return messageBodyString;
	}
	
	
	/**
	 * 将下传字符串转换为TradeSet实例
	 * 
	 * @param tradeSet
	 *            TradeSet实例
	 * @param byteDataDown
	 *            下传字符串
	 * @throws UnsupportedEncodingException
	 */
	public void setPackageData(TradeSet tradeSet, byte[] byteDataDown) {
		IBankAssembleDownMessage.getMessageDownString(tradeSet, byteDataDown);
	}
	
	
	
	/**
	 * 
	 * 本机向核心金额转换
	 * @param localCreadit 本地金额 单位：分转换为元
	 * @return 转换为单位为元的金额；小数点左移两位
	 */
	public String localToHostCreadit(String localCreadit){
		String hostCreadit = "";
		if(localCreadit.length()>=3){
			hostCreadit = localCreadit.substring(0, localCreadit.length()-2) + "." + localCreadit.substring(localCreadit.length()-2);
		}else if(localCreadit.length()==2){
			hostCreadit = "0." + localCreadit;
		}else if(localCreadit.length()==1){
			hostCreadit = "0.0" + localCreadit;
		}else{
			hostCreadit = "0.00";
		}
		return hostCreadit;
	}
	
	
	/**
	 * 
	 * 核心向本机金额转换（单位：元转换为分）
	 * @param creadit 转换金额 单位：元
	 * @return 转换为分后的金额
	 */
	public String getPayString(String creadit){
		String userBal = "";
		if(creadit.indexOf(".")>-1){//存在
			   String array[] = creadit.split("\\.");
			   userBal += array[0];
			   if(array[1].length()>2){
				   userBal += array[1].substring(0,2);
			   }else if(array[1].length()==2){
				   userBal += array[1];
			   }else if(array[1].length()==1){
				   userBal += array[1]+"0";
			   }else{
				   userBal += "00";
			   }
		}else{//不存在
			userBal += creadit +"00";
		}
		System.out.println("转换为分后的金额="+userBal);
		if(userBal.length()<=3){
			int credit = Integer.parseInt(userBal);
			userBal = Integer.toString(credit);
			System.out.println("转换为分后的金额2="+userBal);
		}
		return userBal;
	}
}
