package cn.yufu.system.common.utils.mina;


import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonMinaDecoder implements ProtocolDecoder {
	private static final Logger logger = LoggerFactory.getLogger(CommonMinaDecoder.class);

	public void decode(final IoSession session, final IoBuffer msg,final ProtocolDecoderOutput out) throws Exception {
		logger.info("---------接收卡系统数据---------start");
		try {
			final int chunkSize = msg.remaining();
			logger.info("chunsize---------" + chunkSize);
			if (chunkSize != 0) {
				IoBuffer buffer = (IoBuffer) msg;
				byte[] isoMessage = buffer.array();
				byte[] length = new byte[4];
				System.arraycopy(isoMessage, 0, length, 0, 4);
				int bodyLength = Integer.parseInt(new String(length));
				
				byte[] bodyMesasge = new byte[bodyLength];
				System.arraycopy(isoMessage, 4, bodyMesasge, 0, bodyLength);
				String s_bodyMesasge = new String(bodyMesasge,"UTF-8");
				logger.info("接收到卡系统报文的报文---------" + s_bodyMesasge);
				
				//s_bodyMesasge = this.decoder(s_bodyMesasge);
				//logger.info("解密后的报文---------" + s_bodyMesasge);

				session.setAttribute("message", s_bodyMesasge);
				out.write(s_bodyMesasge);
				session.close(true);
				session.setAttribute("count", "end");
				logger.info("---------接收卡系统数据---------end");
			}
			msg.position(msg.limit());
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 返回报文验签部分
	 * @param message 去掉四位报文长度的报文
	 * @return 解密和验签后的报文
	 */
	public String decoder(String message){
		return message;
	}

	public void finishDecode(final IoSession session,
			final ProtocolDecoderOutput out) throws Exception {
		
	}

	public void dispose(final IoSession session) throws Exception {
		
	}

}
