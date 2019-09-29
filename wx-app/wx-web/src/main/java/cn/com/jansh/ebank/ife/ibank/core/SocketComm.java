/**
 * SocketComm.java
 * 版权所有(C) 2011 北京坚石诚信科技有限公司
 * 创建:YangFan 2011-3-31
 */
package cn.com.jansh.ebank.ife.ibank.core;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import cn.com.jansh.ebank.ife.ibank.Simulationhost;
import cn.com.jansh.ebank.ife.ibank.common.IBankAssembleUpMessage;
import cn.com.jansh.ebank.ife.ibank.common.ITradeComm;
import cn.com.jansh.ebank.ife.ibank.common.ITradeCommException;
import cn.com.jansh.ebank.ife.ibank.common.TradeSet;
import cn.com.jansh.ebank.ife.standard.tallyservice.common.TradeConstDefine.TradeHostParam;


/**
 * 功能: 通讯组件SOCKET实现<br>
 * @author YangFan
 * @version 1.0.0
 */
public class SocketComm implements ITradeComm {
	private static final Logger logger = LogManager.getLogger(SocketComm.class);
	
	public byte[] execComm(TradeSet tradeSet, StringBuffer tradeMessage, String tradeCode) throws ITradeCommException {
		
		byte[] realDataUp;//上传报文
		byte[] realDataDown = null;//下传报文
		int inputString;//接收下传报文长度
		byte[] dataDown = new byte[8];//报文长度
		
		OutputStream outputStream = null;
		InputStream inputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		
		
		Socket socket = null;
		BufferedInputStream bufferedInputStream = null;
		if(tradeSet.get("DEBUG")!=null && tradeSet.get("DEBUG").equals("true")){
			logger.info("Debug开启，进入模拟主机交易模式，返回结果");
			return getPackageData(tradeSet);
			
		}
		
		String hostIp = TradeHostParam.hostip;
		logger.info("trade IP: " + hostIp);
		int hostPort = TradeHostParam.hostport;
		logger.info("trade PORT: " + hostPort);
		int timeOut = TradeHostParam.hosttimeout;
		logger.info("trade TIMEOUT: " + timeOut);
		
		try {
			socket = new Socket(hostIp, hostPort);
			socket.setSoTimeout(timeOut);

			outputStream = socket.getOutputStream();
				//////////////////////////////////////////////////////////////////////////////
				//前八位为请求报文总长度, 武汉农商行特有（请求模式：报文总长度 + 报文头 + 报文体）
				String tempMessage = tradeMessage.toString();//上传报文
				realDataUp = tempMessage.getBytes();
				logger.info("up message data(Local上传报文): " + tempMessage);
				System.out.println("上传报文："+new String(realDataUp));
				outputStream.write(realDataUp);
				outputStream.flush();
	
				inputStream = socket.getInputStream();
				bufferedInputStream = new BufferedInputStream(inputStream);
				
				inputString = bufferedInputStream.read(dataDown);
				byteArrayOutputStream = new ByteArrayOutputStream();
				byteArrayOutputStream.write(dataDown, 0, inputString);
				
				//前8位为响应报文体长度, 武汉农商行特有（回应模式：报文头 + 报文体）
				String dataStr = "";//下传报文长度
				dataStr = new String(dataDown).substring(0, 8);
				logger.info("host return message length(主机返回报文长度): " + dataStr);
				realDataDown = new byte[Integer.parseInt(dataStr)];//接收下传报文长度
				//////////////////////////////////////////////////////////////////////////////
				
				inputString = bufferedInputStream.read(realDataDown);//下传报文
				byteArrayOutputStream = new ByteArrayOutputStream();
				byteArrayOutputStream.write(realDataDown, 0, inputString);
	
				System.out.println("下传报文："+dataStr + new String(realDataDown));
				logger.info("down message data(Socket主机返回报文): " +dataStr + new String(realDataDown));
				
		} catch (UnknownHostException e) {
			logger.error("unknown host exception" + hostIp + ":" + hostPort + e.getMessage());
			throw new ITradeCommException("unknown host exception" + hostIp + ":" + hostPort,e);
		} catch (SocketException e) {
			logger.error("socket exception" + e.getMessage());
			throw new ITradeCommException("socket exception", e);
		} catch (IOException e) {
			logger.error("I/O operate exception:" + e.getMessage());
			throw new ITradeCommException("I/O operate exception", e);
		} finally {
			try {
				if (null != byteArrayOutputStream) {
					byteArrayOutputStream.close();
				}
				if (null != inputStream) {
					inputStream.close();
				}
				if (null != outputStream) {
					outputStream.close();
				}
				if (null != socket) {
					socket.close();
				}
			} catch (IOException e) {
				logger.error("clean resource exception:" + e.getMessage());
				throw new ITradeCommException("clean resource exception", e);
			}
		}
		return realDataDown;
	}
	
	
	/**
	 * 模拟主机交易返回结果<br>
	 * 按交易模板下传字段拼装返回字节流<br>
	 * 
	 * @param trade 交易模板实例
	 * @return 模拟返回字节流
	 */
	private byte[] getPackageData(TradeSet tradeSet) {
		StringBuffer sbDataDown = new StringBuffer();
		String tradecode = tradeSet.getTradeId();
		String downHead = "";
		String dwonBody = "";
		switch(Integer.parseInt(tradecode)){
		case 1110:
			dwonBody = Simulationhost.getVmwareData1110(tradeSet);
			break;
		case 1111:
			dwonBody = Simulationhost.getVmwareData1111(tradeSet);
			break;
		case 2110:
			dwonBody = Simulationhost.getVmwareData2110(tradeSet);
			break;
		case 2111:
			dwonBody = Simulationhost.getVmwareData2111(tradeSet);
			break;
		case 3110:
			dwonBody = Simulationhost.getVmwareData3110(tradeSet);
			break;
		case 3111:
			dwonBody = Simulationhost.getVmwareData3111(tradeSet);
			break;
		}
		
		downHead = IBankAssembleUpMessage.getLengthString(dwonBody.length());
		sbDataDown.append(downHead);
		sbDataDown.append(dwonBody);
		
		return sbDataDown.toString().getBytes(); 
	}
}
