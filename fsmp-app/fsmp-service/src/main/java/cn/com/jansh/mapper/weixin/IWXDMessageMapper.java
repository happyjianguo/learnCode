package cn.com.jansh.mapper.weixin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.jansh.entity.weixin.WXDMessage;

/**
 * 微信消息数据Mapper
 * @author gll
 * @version 1.0
 */
public interface IWXDMessageMapper {
	/**
	 * 分页获取微信消息列表 
	 * @param params
	 * @return
	 */
	public List<WXDMessage> queryPageMessageByParams(Map<String, String> params);

	/**
	 * 添加用户信息
	 * @param msg
	 * @return
	 */
	public int insertMessage(WXDMessage msg);

	/**
	 * 根据消息类型获取消息
	 * 
	 * @param type
	 * @return
	 */
	List<WXDMessage> getMsgByType(String type);

	public int delete(String msgId); 

	public WXDMessage findByMsgId(Integer msgId);

	public List<WXDMessage> findByMediaId(String mediaId);

	public int update(WXDMessage msgContent);

	/**
	 * 根据消息类型，消息名称，公众号id获取消息
	 * @param map
	 * @return
	 */
	public WXDMessage selectMessage(Map<String, String> map);

	/**
	 * 通过消息id查询消息
	 * @param msgContents
	 * @return
	 */
	public WXDMessage findBySMsgId(@Param("msgId")String msgId);
}
