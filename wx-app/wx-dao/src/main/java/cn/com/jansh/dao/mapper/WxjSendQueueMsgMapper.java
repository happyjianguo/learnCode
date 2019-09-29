package cn.com.jansh.dao.mapper;

import java.util.Map;

import cn.com.jansh.dao.entity.WxjSendQueueMsg;

public interface WxjSendQueueMsgMapper {
	/**
	 * 插入任务表获取用户信息的任务
	 * @param queueMsg
	 * @return
	 */
	public int insertWxjSendQueryMsg(WxjSendQueueMsg queueMsg);
	/**
	 * 取出processFlag = 0所对应的WxjSendQueueMsg实体类
	 * @param proFlag
	 * @return
	 */
	public WxjSendQueueMsg selectByProFlag(String proFlag);
	/**
	 * 更新processFlag的状态
	 * @param map:newProcessFlag新值，oldProcessFlag旧值，autoId
	 * @return
	 */
	public int updateByWSQM(Map<String, String> map);
}
