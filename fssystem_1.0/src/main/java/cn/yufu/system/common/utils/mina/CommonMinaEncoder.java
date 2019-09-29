package cn.yufu.system.common.utils.mina;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.yufu.system.common.utils.StringUtils;

public class CommonMinaEncoder implements ProtocolEncoder {
	private static final Logger logger = LoggerFactory.getLogger(CommonMinaEncoder.class);

	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput encoderOut) throws Exception {
		logger.info("---------发送卡系统数据---------start");
		session.getConfig().setBothIdleTime(10);
		logger.info("encode {}", message.getClass().getCanonicalName());
		if (message instanceof ByteBuffer) {
			encoderOut.write(message);
		} else if (message instanceof String) {
			
			String[] split = ((String) message).split("\\|");
			if (split.length > 2) {	
				message = this.encoder(message.toString());	 //加四位长度报文头
			}
			
			logger.info("---------发送卡系统的报文数据---------" + message.toString());
			byte[] body = ((String) message).getBytes();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			out.write(body);
			IoBuffer buffers = IoBuffer.allocate(out.toByteArray().length);
			buffers.put(out.toByteArray());
			buffers.flip();
			encoderOut.write(buffers);
			logger.info("---------发送卡系统数据---------end");
		}
	}

	/**
	 * 请求报文加密部分
	 * @param message 报文源码
	 * @return 加密和加验签后的报文
	 */
	public String encoder(String message){
		if(StringUtils.isNotEmpty(message)){
			message = "0075|" + message + "|" + "FFFFFFFFFFFFFFFF";
			//报文头新增四位报文长度
			byte[] body = message.getBytes();
			message = String.format("%04d", body.length) + message;
		}
		return message;
	}
	
	public void dispose(IoSession session) throws Exception {
		session.close(true);
	}
	
}
