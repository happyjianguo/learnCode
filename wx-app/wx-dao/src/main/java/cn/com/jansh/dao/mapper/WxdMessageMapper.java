package cn.com.jansh.dao.mapper;

import cn.com.jansh.dao.entity.WxdMessage;

public interface WxdMessageMapper {
	
	/**
	 * 根据MSGID取出WxdMsgContent对象
	 * @param msgId
	 * @return
	 */
	public WxdMessage getWxdMessageById(String msgId);
}
