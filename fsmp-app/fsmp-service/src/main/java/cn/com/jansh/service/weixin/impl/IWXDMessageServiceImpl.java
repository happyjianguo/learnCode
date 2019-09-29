package cn.com.jansh.service.weixin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.com.jansh.component.common.GlobalProperties;
import cn.com.jansh.entity.weixin.NewsMaterial;
import cn.com.jansh.entity.weixin.NewsMaterialDetai;
import cn.com.jansh.entity.weixin.WXDMessage;
import cn.com.jansh.mapper.weixin.INewsMaterialDetailMapper;
import cn.com.jansh.mapper.weixin.INewsMaterialMapper;
import cn.com.jansh.mapper.weixin.IWXDMenuMapper;
import cn.com.jansh.mapper.weixin.IWXDMessageMapper;
import cn.com.jansh.mapper.weixin.IWXDResMessageMapper;
import cn.com.jansh.service.wechat.WxAuthService;
import cn.com.jansh.service.weixin.IWXDMessageService;
import cn.com.jansh.utils.DateUtil;
import cn.com.jansh.utils.HttpClientUtil;
import cn.com.jansh.utils.IDUtils;
import cn.com.jansh.vo.JsonVO;
/**
 * 微信消息业务处理接口Impl
 * @author gll
 * @version 1.0
 */
@Service
public class IWXDMessageServiceImpl implements IWXDMessageService {

	private static final Logger logger = LogManager.getLogger(IWXDMessageServiceImpl.class);
	@Autowired
	private IWXDMessageMapper iwxdmessagemapper;
	@Autowired
	private INewsMaterialMapper inewsmaterialmapper;
	@Autowired
	private INewsMaterialDetailMapper inewsmaterialdetailmapper;
	@Autowired
	private IWXDResMessageMapper iWXDResMessageMapper;
	@Autowired
	private IWXDMenuMapper iWXDMenuMapper;
	@Autowired
	private GlobalProperties globalProperties;
	@Autowired
	private WxAuthService wxAuthService;

	/**
	 * 根据消息名称或类型查询
	 */
	@Override
	public List<WXDMessage> dataGrid(WXDMessage message) {
		Map<String, String> params = new HashMap<String, String>();
		String msgName = message.getMsgName();
		String msgType = message.getMsgType();
		String appid = message.getAppid();
		if (StringUtils.isNotEmpty(appid)) {
			params.put("appid", appid);
		}
		if (StringUtils.isNotEmpty(msgName)) {
			params.put("msgName", msgName);
		}
		if (StringUtils.isNotEmpty(msgType)) {
			params.put("msgType", msgType);
		}
		List<WXDMessage> pager = iwxdmessagemapper.queryPageMessageByParams(params);
		return pager;
	}
	/**
	 * 新增文本类型消息
	 */
	@Override
	public boolean addMsg(WXDMessage msg) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("appid", msg.getAppid());
		map.put("msgName", msg.getMsgName());
		map.put("msgType", msg.getMsgType());
		WXDMessage wXDMessage = iwxdmessagemapper.selectMessage(map);
		if(null == wXDMessage){
			String id = IDUtils.getMsgId();
			msg.setMsgId(id);
			msg.setUpdateTime(DateUtil.getDate());
			msg.setCreateTime(DateUtil.getDate());
			iwxdmessagemapper.insertMessage(msg);
			return true;
		}
		return false;
	}
	/**
	 * 新增消息
	 */
	@Override
	public boolean addMediaMsg(String name, List<Map<String, String>> details) {
		boolean flag = false;
		if (StringUtils.isNotEmpty(name)) {
			WXDMessage msg = null;
			boolean addFlag = false;
			try {
				String id = IDUtils.getMsgId();
				String time = DateUtil.getDate();
				msg = new WXDMessage();
				msg.setMediaId(id);
				msg.setMsgId(id);
				msg.setMsgName(name);
				msg.setMsgType("4");
				msg.setCreateTime(time);
				iwxdmessagemapper.insertMessage(msg);
				addFlag = true;
				if (addFlag) {
					NewsMaterial newsMaterial = new NewsMaterial();
					newsMaterial.setMaterialId(IDUtils.getMsgId());
					newsMaterial.setMaterialName(name);
					newsMaterial.setMediaId(msg.getMediaId());
					newsMaterial.setMediaidStatus("1");
					newsMaterial.setCreateTime(time);
					newsMaterial.setUpdateTime(time);
					inewsmaterialmapper.saveNewsMaterial(newsMaterial);
					for (Map<String, String> detial : details) {
						NewsMaterialDetai newsMaterialDetai = new NewsMaterialDetai();
						newsMaterialDetai.setMaterialTitle(detial.get("title"));
						newsMaterialDetai.setMaterialId(newsMaterial.getMaterialId());
						newsMaterialDetai.setDetailId(IDUtils.getMsgId());
						newsMaterialDetai.setShowCoverPic("1");
						newsMaterialDetai.setThumbMediaUrl(detial.get("url"));
						newsMaterialDetai.setThumbMediaId(detial.get("mediaId"));
						newsMaterialDetai.setCreateTime(time);
						newsMaterialDetai.setUpdateTime(time);
						inewsmaterialdetailmapper.addNewsMaterialDetail(newsMaterialDetai);
					}
				}
				flag = true;
			} catch (Exception e) {
				throw new RuntimeException("插入数据失败:", e);
			}
		}
		return flag;
	}
	/**
	 * 按分组群发消息
	 * 
	 * @author Mingsong
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public JsonVO groupSendMsg(String appid, String msgType, String msgContents, String sendGroupId) {
		JsonVO jsvo = new JsonVO();
		Map<String, Object> groupFilter = new HashMap<String, Object>();
		Map<String, String> conMap = new HashMap<String, String>();
		Map<String, Object> sendMapData = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		String groupSendMsgUrl = "";
		String sendNewsStr = "";
		Map<String, String> maps = null;
		try {
			// 获取AccessToken
			String accessToken = wxAuthService.getAuthAccessToken(appid);
			if (StringUtils.isNotEmpty(sendGroupId)) {
				if ("0".equals(msgType)) {
					conMap.put("content", msgContents);
					sendMapData.put("text", conMap);
					sendMapData.put("msgtype", "text");
				} else if ("4".equals(msgType)) {
					NewsMaterial newsMaterial = inewsmaterialmapper.queryNewsMaterialByMessageId(msgContents);
					conMap.put("media_id", newsMaterial.getMediaId());
//					conMap.put("media_id", msgContents);
					sendMapData.put("mpnews", conMap);
					sendMapData.put("msgtype", "mpnews");
				}
				if ("allUser".equals(sendGroupId)) {
					groupFilter.put("is_to_all", true);
					groupFilter.put("group_id", "");
				} else {
					groupFilter.put("is_to_all", false);
					groupFilter.put("group_id", sendGroupId);
				}
				sendMapData.put("filter", groupFilter);
			}
			sendNewsStr = mapper.writeValueAsString(sendMapData);
			groupSendMsgUrl = globalProperties.getApiWeixinURL() + "message/mass/sendall?access_token=" + accessToken;
			logger.info("群发消息内容：" + sendMapData);
			logger.info("群发消息URL：" + groupSendMsgUrl);
			// 向微信发送post请求
			String res = HttpClientUtil.httpPost(groupSendMsgUrl, sendNewsStr);
			maps = new ObjectMapper().readValue(res, Map.class);
			if (!"0".equals(String.valueOf(maps.get("errcode")))) {
				jsvo.setSuccess(false);
				jsvo.setMsg("群发消息失败!");
				logger.error("群发消息失败!" + String.valueOf(maps.get("errcode")));
				return jsvo;
			} else {
				jsvo.setSuccess(true);
				jsvo.setMsg("群发消息成功!!!");
				logger.error("群发消息成功!");
			}
		} catch (Exception e) {
			logger.error("群发消息失败!" + e);
		}
		return jsvo;
	}
	/**
	 * 根据类型获取文本消息内容
	 */
	@Override
	public List<WXDMessage> getMsgByType(String type) {
		return iwxdmessagemapper.getMsgByType(type);
	}
	/**
	 * 根据id获取文本消息内容
	 */
	@Override
	public WXDMessage getTextMsgContentById(String mediaId) {
		List<WXDMessage> msgContents = iwxdmessagemapper.findByMediaId(mediaId);
		if (msgContents != null) {
			return msgContents.get(0);
		}
		return null;
	}
	/**
	 * 根据消息Id删除消息以及内容 
	 * 
	 * @param msgId
	 * @return 0：删除失败，1：删除成功，2：msgid被占用
	 */
	@Override
	public int deleteMsg(String msgId) {
		int i = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msgId", msgId);
		if (0 < iWXDResMessageMapper.queryPageResMessageByParams(map).size()
				|| 0 < iWXDMenuMapper.queryMenuParams(map).size()) {
			i = 2;
			return i;
		}
		try {
			i = iwxdmessagemapper.delete(msgId);
		} catch (RuntimeException e) {
			throw new RuntimeException("删除失败", e);
		}
		return i;
	}
	/**
	 * 根据消息ID获取消息内容
	 * 
	 * @param materialId
	 * @return
	 */
	@Override
	public List<WXDMessage> getMsgContentById(String materialId) {
		List<WXDMessage> msgContents = iwxdmessagemapper.findByMediaId(materialId);
		return msgContents;
	}
	@Override
	public void save(WXDMessage message) {
		iwxdmessagemapper.insertMessage(message);
	}
	@Override
	public WXDMessage findByMsgId(Integer msgId) {
		WXDMessage wxdMessage = iwxdmessagemapper.findByMsgId(msgId);
		return wxdMessage;
	}
	@Override
	public int update(WXDMessage msgContent) {
		msgContent.setUpdateTime(DateUtil.getDate());
		return iwxdmessagemapper.update(msgContent);
	}
}
