package cn.com.jansh.mapper.weixin;

import java.util.List;
import java.util.Map;

import cn.com.jansh.entity.weixin.WXDMessage;
import cn.com.jansh.entity.weixin.WXDResMessage;

/**
 * 智能回复配置表实体
 * @author gll
 * @version 1.0
 */
public interface IWXDResMessageMapper {
	/**
	 * 分页查询智能回复信息
	 * @param params
	 * @return
	 */
	public List<WXDResMessage> queryPageResMessageByParams(Map<String, Object> params);
	
	/**
	 * ]初始化查詢自动回复列表
	 * @param resMsg
	 * @return
	 */
	public List<WXDResMessage> queryPageResMessage(WXDResMessage resMsg);

	public List<WXDMessage> getMsgByType(String msgType);

	public void save(WXDResMessage resMsg);

	public void update(WXDResMessage resMsg);

	/**
	 * 通过id删除微信消息
	 * @param idList
	 */
	public void deleteByIdS(List<String> idList);
	
	/**
	 * 根据智能回复名称查询
	 * @param resMsg
	 * @return
	 */
	public List<WXDResMessage> searchByResMsgname(WXDResMessage resMsg);

	/**
	 *  根据智能回复id查询智能回复信息
	 * @param resmsgid
	 * @return
	 */
	public WXDResMessage selectResMByResmsgid(String resmsgid);
}
