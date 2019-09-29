package cn.com.jansh.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.TextUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.jansh.config.DynamicDefine;
import cn.com.jansh.dao.entity.WxdMessage;
import cn.com.jansh.dao.entity.WxdNewsMaterial;
import cn.com.jansh.dao.entity.WxdNewsMaterialDetail;
import cn.com.jansh.dao.mapper.WxdErrMsgMapper;
import cn.com.jansh.dao.mapper.WxdNewsMaterialDetailMapper;
import cn.com.jansh.dao.mapper.WxdNewsMaterialMapper;
import cn.com.jansh.util.DateUtil;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage.Item;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;
/***
 * Handler的通用类
 * @author duanmuyn
 * @version 2.0
 */
public class BaseHandler {
	
	private static final Logger logger = LogManager.getLogger(BaseHandler.class);
	
	@Autowired
	private WxdNewsMaterialMapper materialMapper;
	
	@Autowired
	private WxdNewsMaterialDetailMapper detailMapper;
	
	@Autowired
	private WxdErrMsgMapper errMsgMapper;
	

	/**
	 * 消息回复通用代码
	 * @param wxMessage
	 * @param wxdMessage
	 * @return
	 */
	public WxMpXmlOutMessage getMessage(WxMpXmlMessage wxMessage,WxdMessage wxdMessage){
		WxMpXmlOutMessage m =null;
		try{
			String msgtype = wxdMessage.getMsgtype();
			logger.info("应答类型:"+msgtype);
			//微信消息内容表具体查询内容值
			if(StringUtils.isNotBlank(msgtype)){
				if("0".equals(msgtype)){
					m = getTextMessage(wxMessage,wxdMessage);
					logger.info("返回的消息内容：文本{}"+m.toXml());
				}else if("4".equals(msgtype)){
				    m = getImageMessage(wxMessage,wxdMessage);
				    logger.info("返回的消息内容：图文{}",m.toXml());
				}else{
					m = getErrorMessage(wxMessage);
					logger.info("返回类型失败：文本{}",m.toXml());
				}
			}else{
				m = getErrorMessage(wxMessage);
				logger.info("返回类型失败：文本{}",m.toXml());
			}
		}catch(Exception e){
			m = getErrorMessage(wxMessage);
			logger.info("Exception返回类型失败：文本{}",m.toXml());
		}
		return m;
	}
	/**
	 * 拼装文本素材
	 * @param wxMessage
	 * @return
	 */
	private WxMpXmlOutTextMessage getTextMessage(WxMpXmlMessage wxMessage,WxdMessage wxdMessage){
	    WxMpXmlOutTextMessage m = new WxMpXmlOutTextMessage();
	    if(StringUtils.isNotBlank(wxdMessage.getTrans())){
	    }else{
	    	m.setContent(wxdMessage.getContent());
	    }
	    m.setCreateTime(DateUtil.getLongDate());
	    m.setFromUserName(wxMessage.getToUserName());
	    m.setToUserName(wxMessage.getFromUserName());
	    return m;
	}
	/**
	 * 拼装图文素材
	 * @param mediaid
	 * @param wxdMessage
	 * @param wxMessage
	 * @return
	 */
	private WxMpXmlOutNewsMessage getImageMessage(WxMpXmlMessage wxMessage,WxdMessage wxdMessage){
		
	    List<WxMpXmlOutNewsMessage.Item> itemli = new ArrayList<WxMpXmlOutNewsMessage.Item>();
	    
		WxdNewsMaterial material = materialMapper.getWNMById(wxdMessage.getMediaid());
		List<WxdNewsMaterialDetail> details = detailMapper.getWNMDByMId(material.getMaterialId());
		if (details == null) {
			//return 0;
		}
		for (WxdNewsMaterialDetail detail:details) {
			String wUrl = detail.getContentSourceUrl();
			if(!TextUtils.isEmpty(wUrl)&&wUrl.indexOf("?openid=")!=-1){
				wUrl=wUrl+wxMessage.getFromUserName();
			}
			WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
			if(StringUtils.isNotBlank(wUrl)){
				item.setUrl(wUrl);
			}
			
			item.setPicUrl(detail.getThumbMediaUrl());
		    item.setTitle(detail.getMaterialTitle());
		    item.setDescription(detail.getDiGest());
		    
			if(StringUtils.isNotBlank(wxdMessage.getTrans())){
				wxdMessage.setContent(detail.getDiGest());
			}
		    itemli.add(item);
		}
		
		WxMpXmlOutNewsMessage m1 = new WxMpXmlOutNewsMessage();
	    m1.setFromUserName(wxMessage.getToUserName());
	    m1.setCreateTime(DateUtil.getLongDate());
	    m1.setToUserName(wxMessage.getFromUserName());
	    for(Item ite : itemli){
	    	m1.addArticle(ite);
	    }
	    return m1;
	}
	
	/**
	 * 拼装错误信息
	 * @param wxMessage
	 * @return
	 */
	public WxMpXmlOutTextMessage getErrorMessage(WxMpXmlMessage wxMessage){
		WxMpXmlOutTextMessage m = new WxMpXmlOutTextMessage();
		m.setCreateTime(DateUtil.getLongDate());
		m.setFromUserName(wxMessage.getToUserName());
		m.setToUserName(wxMessage.getFromUserName());
		m.setContent(errMsgMapper.selectByErrorCode(DynamicDefine.getErrorCode(wxMessage.getMsgType(), wxMessage.getEvent())).getErrorDes());
		return m;
	}
}
