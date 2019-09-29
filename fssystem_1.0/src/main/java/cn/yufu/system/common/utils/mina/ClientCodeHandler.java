package cn.yufu.system.common.utils.mina;


import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketSessionConfig;

public class ClientCodeHandler extends IoHandlerAdapter {
	private final Logger logger = Logger.getLogger(ClientCodeHandler.class);

	// 发送数据
	public void sessionOpened(IoSession session) throws Exception {
		SocketSessionConfig cfg = (SocketSessionConfig) session.getConfig();
		cfg.setKeepAlive(true);
	}

	// 接收数据
	public void messageReceived(IoSession session, Object message) throws Exception {
		logger.debug("---接收到的数据---"+message);
		session.setAttribute("message", message);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		session.close(true);
		logger.debug("---关闭一个session----------");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		logger.debug("session 进入idle");
		session.setAttribute("empty", "1");
		super.sessionIdle(session, status);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		logger.debug("session未接收到数据！");
	}
}
