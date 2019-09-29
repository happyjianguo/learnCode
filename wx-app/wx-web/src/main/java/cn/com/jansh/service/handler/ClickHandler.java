package cn.com.jansh.service.handler;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.dao.entity.WxdMenu;
import cn.com.jansh.dao.entity.WxdMessage;
import cn.com.jansh.dao.mapper.WxdMenuMapper;
import cn.com.jansh.dao.mapper.WxdMessageMapper;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.common.util.StringUtils;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
/**
 * CLICK点击事件处理
 * @author duanmuyn
 * @version 2.0
 */
@Service("clickHandler")
public class ClickHandler extends BaseHandler implements WxMpMessageHandler{

	private static final Logger logger = LogManager.getLogger(ClickHandler.class);
	
	@Autowired
	private WxdMessageMapper messageMapper;
	
	@Autowired
	private WxdMenuMapper wxdMenuMapper;
	
	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager,String appid) throws WxErrorException {
		WxMpXmlOutMessage m = null;
		logger.info("CLICK点击事件处理:{}",wxMessage.toString());
		
		WxdMenu wxdMenu = new WxdMenu();
		wxdMenu.setMenutype(wxMessage.getEvent().toLowerCase());
		wxdMenu.setMenukey(wxMessage.getEventKey());
		wxdMenu.setAppid(appid);
		wxdMenu = wxdMenuMapper.getwxdmenuData(wxdMenu);
		
		//根据id返回消息
		if(wxdMenu != null){
			if(StringUtils.isNotBlank(wxdMenu.getMsgid())){
				//确定应答类型及应答素材
				WxdMessage wxdMessage = messageMapper.getWxdMessageById(wxdMenu.getMsgid());
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
