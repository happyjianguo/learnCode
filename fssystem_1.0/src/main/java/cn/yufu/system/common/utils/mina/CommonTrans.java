package cn.yufu.system.common.utils.mina;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.yufu.system.modules.sys.utils.DictUtils;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * 所有交易类的父类
 * 
 */
public class CommonTrans {
	private static final Logger logger = LoggerFactory.getLogger(CommonTrans.class);

	/**
	 * 对数据封装的业务逻辑,封装成支付平台所需要的信息,然后发送请求.返回交易结果状态码
	 * 
	 * @return
	 */
	private String host;
	private int port;

	public CommonTrans(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	/**
	 * 实例化的时候直接从数据字典获取IP和端口
	 */
	public CommonTrans() {
		this.host = DictUtils.getDictValue("与卡核心系统交互IP", "CARD_SYS_IP", "192.168.10.251");
		this.port = Integer.valueOf(DictUtils.getDictValue("与卡核心系统交互PORT", "CARD_SYS_PORT", "8920"));
		logger.info("IP：host--{}, 端口：port--{}", host, port);
	}
	
	/**
	 * 发送到卡系统
	 * 
	 * @param message
	 * @return @throws
	 */
	public String sendMessage(String message) {
		IoConnector connector = new NioSocketConnector();
		String msg = "";
		try {
			connector.setConnectTimeoutMillis(30000);
			connector.getFilterChain().addLast("codec", new CommonMinaFactory());
			connector.getFilterChain().addLast("logger", new LoggingFilter());
			connector.setHandler(new ClientCodeHandler());
			connector.setDefaultRemoteAddress(new InetSocketAddress(host, port));
			connector.getSessionConfig().setReadBufferSize(2048);
			connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
			ConnectFuture future = connector.connect();
			future.awaitUninterruptibly();
			IoSession session = future.getSession();
			session.write(message);
			future.getSession().getCloseFuture().awaitUninterruptibly();
			future.awaitUninterruptibly();
			msg = (String) session.getAttribute("message");
			logger.info("---返回内容---" + msg);
		} catch (Exception e) {
			logger.error("与卡系统网络连接异常:" + e);
		} finally {
			connector.dispose();
		}
		return msg;
	}

}
