package cn.com.jansh.service.handler;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.dao.entity.WxbUser;
import cn.com.jansh.dao.mapper.WxbUserMapper;
import cn.com.jansh.util.DateUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;

/**
 * 取消关注事件处理
 * @author duanmuyn
 * @version 2.0
 */
@Service("unsubscribeHandler")
public class UnsubscribeHandler implements WxMpMessageHandler{

	private static final Logger logger = LogManager.getLogger(UnsubscribeHandler.class);
	
	@Autowired
	private WxbUserMapper userMapper;
	
	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager,String appid) throws WxErrorException {
		logger.info("取消关注公众号:{}" + wxMessage.toString());
		
		// 取消关注成功后需要将微信用户表WXBUSER 更新不关注
		WxbUser user = new WxbUser();
		user.setOpenid(wxMessage.getFromUserName());
		user.setSubscripttype("2");
		user.setUpdatetime(DateUtil.getDate());
		int updateUCount = userMapper.updateType(user);
		
		if (updateUCount == 0) {
			logger.error("取消关注时更新微信用户表失败!");
		}
		
		WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().build();
		return m;
	}

}
