package cn.com.jansh.service.weixin;

import java.util.List;
import java.util.Map;

import cn.com.jansh.entity.weixin.WXDMessage;
import cn.com.jansh.vo.JsonVO;

/**
 * 微信消息业务处理接口
 * @author gll
 * @version 1.0
 */
public interface IWXDMessageService {
	
	public List<WXDMessage> dataGrid(WXDMessage message);
	/**
	 * 新增文本消息
	 * @param name
	 * @param centent
	 */
	public boolean addMsg(WXDMessage msg);
	/**
	 * 新增图文消息
	 * @param name
	 * @param details
	 * @return
	 */
	public boolean addMediaMsg(String name, List<Map<String, String>> details);
	/**
	 * 根据消息类型获取消息
	 * @param type
	 * @return
	 */
	List<WXDMessage> getMsgByType(String type);
	/**
	 * 获取文本消息
	 * @param materialId
	 * @return
	 */
	public WXDMessage getTextMsgContentById(String materialId);
	/**
	 * 根据消息Id删除消息以及内容
	 * @param 0：删除失败，1：删除成功，2：msgid被占用
	 * @return 
	 */
	public int deleteMsg(String msgId);
	/**
	 * 群发消息模块，微信群发消息
	 * @param appid
	 * @param msgType
	 * @param msgContents
	 * @param groupId
	 * @return
	 */
	public JsonVO groupSendMsg(String appid, String msgType, String msgContents, String groupId);
	/**
	 * 根据消息ID获取消息内容
	 * @param materialId
	 * @return
	 */
	public List<WXDMessage> getMsgContentById(String materialId);

	public void save(WXDMessage message);

	public WXDMessage findByMsgId(Integer msgId);

	/**
	 * 修改微信消息
	 * @param msgContent
	 * @return
	 */
	public int update(WXDMessage msgContent);
}
