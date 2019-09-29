package cn.com.jansh.service.handler;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;

/**
 * 获取菜单拉取事件VIEW的处理类
 * @author XieLiangliang
 * @version 1.0
 */
@Service("viewHandler")
public class ViewHandler implements WxMpMessageHandler{
	
	private static final Logger logger = LogManager.getLogger(ViewHandler.class);

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
		WxSessionManager sessionManager,String appid) throws WxErrorException {
		logger.info("获取菜单拉取事件VIEW的处理类:{}",wxMessage.toString());
		WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().build();
		return m;
	}
}
