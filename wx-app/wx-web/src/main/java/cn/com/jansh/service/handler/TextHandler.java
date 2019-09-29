package cn.com.jansh.service.handler;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.dao.entity.WxdMessage;
import cn.com.jansh.dao.entity.WxdResMessage;
import cn.com.jansh.dao.mapper.WxdMessageMapper;
import cn.com.jansh.dao.mapper.WxdResMessageMapper;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.common.util.StringUtils;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

/**
 * 文本消息处理
 * @author duanmuyn
 * @version 2.0
 */
@Service("textHandler")
public class TextHandler extends BaseHandler implements WxMpMessageHandler {
	
	private static final Logger logger = LogManager.getLogger(TextHandler.class);
	
	@Autowired
	private WxdResMessageMapper resMessageMapper;
	@Autowired
	private WxdMessageMapper messageMapper;
	
	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager,String appid) throws WxErrorException {
		WxMpXmlOutMessage m = null;
		logger.info("文本消息处理:{}",wxMessage.toString());
		
		//根据公众号id查询关注返回的消息
		WxdResMessage message = new WxdResMessage();
		message.setResType("2");//1为关注回复，2为智能回复
		message.setResMsgKey(wxMessage.getContent());
		message.setAppid(appid);
		message = resMessageMapper.getResContent(message);
		
		//根据id返回消息
		if(message != null){
			if(StringUtils.isNotBlank(message.getMsgid())){
				//确定应答类型及应答素材
				WxdMessage wxdMessage = messageMapper.getWxdMessageById(message.getMsgid());
				m = getMessage(wxMessage,wxdMessage);
			}
		}	
		
		//当没有返回值时默认返回错误提示
		if(m==null){
			m = getErrorMessage(wxMessage);
			logger.info("返回类型失败：文本{}",m.toXml());
		}
		return m;
	}

}
