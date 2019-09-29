package cn.com.jansh.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//import cn.com.jansh.dao.entity.WXJTransLog;
import cn.com.jansh.dao.entity.WxbPlatformUser;
//import cn.com.jansh.dao.mapper.IWXJTransLogMapper;
import cn.com.jansh.dao.mapper.WxbPlatformUserMapper;
import cn.com.jansh.service.WeixinService;
//import cn.com.jansh.util.DateUtil;
//import cn.com.jansh.util.IDUtils;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.util.xml.XStreamTransformer;
/**
 * 微信消息请求路由
 * @author duanmuyn
 * @version 2.0
 */
@Service
public class WeixinServiceImpl implements WeixinService{

	private static final Logger logger = LogManager.getLogger(WeixinService.class);
	
	@Autowired
	private WxbPlatformUserMapper platformUserMapper;
	
//	@Autowired
//	private IWXJTransLogMapper iWXJTransLogMapper;

	/**
	 * 验证服务器地址的有效性,并返回微信公众号信息
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param requrl
	 * @return
	 */
	public WxbPlatformUser queryPlatformUser(String signature, String timestamp, String nonce, String requrl) {
		logger.info("##########check signature :"+requrl);
		WxbPlatformUser result = null;
		if(StringUtils.isNotBlank(requrl)){
			result = platformUserMapper.selectPlatformByRequrl(requrl);
			logger.info("##########check signature :"+requrl);
			if(result == null){
				return result;
			}
		}
		if (signature == null || timestamp == null || nonce == null || result.getToken() == null) {
			result = null;
		} else {
			List<String> list = new ArrayList<String>();
			list.add(result.getToken());
			list.add(timestamp);
			list.add(nonce);
			Collections.sort(list);// 排序
			String tmpStr = DigestUtils.sha1Hex(list.get(0) + list.get(1) + list.get(2));
			if (signature.equals(tmpStr)) {
				logger.info("check signature success");
			} else {
				result = null;
			}
		}
		return result;
	}
	
	  @Autowired
	  @Qualifier("textHandler")   
	  private WxMpMessageHandler textHandler;
	  @Autowired
	  @Qualifier("viewHandler")  
	  private WxMpMessageHandler viewHandler;
	  @Autowired
	  @Qualifier("clickHandler") 
	  private WxMpMessageHandler clickHandler;
	  @Autowired
	  @Qualifier("subscribeHandler") 
	  private WxMpMessageHandler subscribeHandler;
	  @Autowired
	  @Qualifier("unsubscribeHandler")  
	  private WxMpMessageHandler unsubscribeHandler;
	  @Autowired
	  @Qualifier("templateSendJobFinishHandler")  
	  private WxMpMessageHandler templateSendJobFinishHandler;
	  
	  protected WxMpService wxMpService;
	  protected WxMpMessageRouter wxMpMessageRouter;

	  /**
	   * 初始化路由过滤规则
	   */
	  @PostConstruct 
	  public void init() {
		wxMpService = new WxMpServiceImpl();
	    wxMpMessageRouter = new WxMpMessageRouter(wxMpService);
	    wxMpMessageRouter
	    	.rule()
		        .async(false)
		        .msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_SUBSCRIBE)
		        .handler(subscribeHandler)
		        .end()
		    .rule()
		        .async(false)
		        .msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_UNSUBSCRIBE)
		        .handler(unsubscribeHandler)
		        .end()
	        .rule()
		        .async(false)
		        .msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_VIEW)
		        .handler(viewHandler)
		        .end()
		    .rule()
		        .async(false)
		        .msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_CLICK)
		        .handler(clickHandler)
		        .end()
		    .rule()
		        .async(false)
		        .msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_TEMPLATESENDJOBFINISH)
		        .handler(templateSendJobFinishHandler)
		        .end()
		    .rule()
		        .async(false)
		        .msgType(WxConsts.XML_MSG_TEXT)
		        .handler(textHandler)
		        .end();
	  } 
	  /**
	   * 消息处理
	   */
	  public String getHandlePost(String xmlMsg,String appid)  {
		  WxMpXmlMessage message = XStreamTransformer.fromXml(WxMpXmlMessage.class, xmlMsg);
		  WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(message,appid);
		  
		  //日志记录
//		  WXJTransLog WXJTransLog = new WXJTransLog();
//		  WXJTransLog.setLogid(IDUtils.getMsgId());
//		  WXJTransLog.setPlatformid(message.getToUserName());
//		  WXJTransLog.setEvent(message.getEvent());
//		  WXJTransLog.setEventkey(message.getEventKey());
//		  WXJTransLog.setFromusername(message.getFromUserName());
//		  WXJTransLog.setMsgtype(message.getMsgType());
//		  WXJTransLog.setCreagetime(DateUtil.getDate());
//		  WXJTransLog.setTextcontext(message.getContent());
//		  if(message.getMsgType().equals(WxConsts.XML_MSG_EVENT)&&message.getEvent().equals(WxConsts.EVT_TEMPLATESENDJOBFINISH)){
//			  
//		  }else{
//			  iWXJTransLogMapper.insertTransLog(WXJTransLog);	
//		  }
		  return outMessage.toXml();
	  }
}
