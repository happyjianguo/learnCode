package cn.com.jansh.service.handler;

import java.util.Map;

import org.apache.http.util.TextUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.config.ConstDefine.ConfigMode;
import cn.com.jansh.dao.entity.WxbUser;
import cn.com.jansh.dao.entity.WxdMessage;
import cn.com.jansh.dao.entity.WxdResMessage;
import cn.com.jansh.dao.mapper.WxbUserMapper;
import cn.com.jansh.dao.mapper.WxdMessageMapper;
import cn.com.jansh.dao.mapper.WxdResMessageMapper;
import cn.com.jansh.model.ReceiveJsonModel;
import cn.com.jansh.service.WxAuthService;
import cn.com.jansh.service.impl.tools.DecompositionMsg;
import cn.com.jansh.service.impl.tools.HttpClientUtils;
import cn.com.jansh.util.DateUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.common.util.StringUtils;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
/**
 * 关注公众号
 * @author duanmuyn
 * @version 2.0
 */
@Service("subscribeHandler") 
public class SubscribeHandler extends BaseHandler implements WxMpMessageHandler{

	private static final Logger logger = LogManager.getLogger(SubscribeHandler.class);
	
	@Autowired
	private WxdResMessageMapper resMessageMapper;
	@Autowired
	private WxdMessageMapper messageMapper;
	@Autowired
	private WxbUserMapper userMapper;
	@Autowired
	private WxAuthService wxAuthService;	//获取accesstoken接口
	
	//@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager,String appid) throws WxErrorException {
		WxMpXmlOutMessage m = null;
		logger.info("关注公众号:{}",wxMessage.toString());
		
		//根据公众号id查询关注返回的消息
		WxdResMessage message = new WxdResMessage();
		message.setResType("1");
		message.setAppid(appid);
		message = resMessageMapper.selectByResType(message);
		
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
		//关注成功后更新用户信息
		updateUserTable(wxMessage,appid);
		return m;
	}
	/**
	 * 关注成功后将关注人的详细信息存入数据库 --微信用户表WXBUSER
	 * @param db
	 * @param repmsg
	 */
	private void updateUserTable(WxMpXmlMessage wxMessage,String appid){
		//先去检查表中是否存在该条数据，若存在则更新状态，不存在则插入新数据
		//检查微信用户表中是否存在记录1:关注，2：未关注
		if( TextUtils.isEmpty(wxMessage.getFromUserName()) || TextUtils.isEmpty(wxMessage.getToUserName())){
			return;
		}
		
		WxbUser wxbUser = new WxbUser();
		wxbUser.setOpenid(wxMessage.getFromUserName());
		wxbUser.setSubscripttype("2");
		WxbUser user = userMapper.selectByWxbUser(wxbUser);
		//曾经关注过
		if(user != null) {
			wxbUser.setSubScribeTime(DateUtil.getDate());
			wxbUser.setSubscripttype("1");
			wxbUser.setUpdatetime(DateUtil.getDate());
			int upUserInfo = userMapper.updateType(wxbUser);
			if(upUserInfo>0) {
				logger.info("更新用户表取消关注状态为关注状态成功！");
			}else{
				logger.error("更新用户表取消关注状态为关注状态失败！");
			}
		}else{
			//无记录则向微信用户表中新增一条记录
			wxbUser.setAppid(appid);
			wxbUser.setSubScribeTime(DateUtil.getDate());
			wxbUser.setSubscripttype("1");
			wxbUser.setCreatetime(DateUtil.getDate());
			wxbUser.setUpdatetime(DateUtil.getDate());
			userMapper.insertWxbUser(wxbUser);
		}	
			
		try {
			//获得发送任务信息
			String sendHttps = OrgaBodyAction(wxMessage,appid);
			String sendDate = null;
			String sendResult = "";
			if (sendHttps!=null && sendHttps.length()>0) {//处理发送业务逻辑
				sendResult = HttpClientUtils.sendmsg(sendHttps, sendDate,"GET");
				if("505".equals(sendResult)){
					logger.info("JOB7获取微信公共服务平台,应答数据超时.");
				}
				ReceiveJsonModel recvEntity = DecompositionMsg.DecompositionJson(sendResult);
				WxbUser user2 = new WxbUser(recvEntity.getOpenid(), recvEntity.getNickname(),
						recvEntity.getCountry(), recvEntity.getProvince(), recvEntity.getCity(),
						recvEntity.getSex(), recvEntity.getGroupid(), recvEntity.getRemark(),
						recvEntity.getLanguage(), recvEntity.getHeadimgurl(), recvEntity.getUnionid(),
						recvEntity.getSubscribe());
				if (userMapper.updateWxbUser(user2)>0) {
					logger.info("用户信息更新成功");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 处理消息体
	 * 请求接口传送的url
	 * 查询发送地址
	 */
	private String OrgaBodyAction(WxMpXmlMessage wxMessage,String appid) {
		String Auth_access_token = wxAuthService.getAuthAccessToken(appid);
		if(Auth_access_token == null || Auth_access_token.length() <=0){
			return "";
		}
		
		String OPENID = wxMessage.getFromUserName();//获取openId
		if(OPENID==null){
			return "";
		}
		String action = ConfigMode.USERINFO_HTTP_URL;//获取请求路径
		action += "?access_token="+Auth_access_token;
		action += "&openid="+OPENID;
		action += "&lang="+ConfigMode.USERINFO_LANG;
		logger.info("获取请求路径:"+action);
		return action;
	}
}
