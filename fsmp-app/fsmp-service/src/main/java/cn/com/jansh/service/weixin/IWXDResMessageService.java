package cn.com.jansh.service.weixin;

import java.util.List;
import cn.com.jansh.entity.weixin.WXDResMessage;


/**
 * 智能回复业务处理接口
 * @author gll
 * @version 1.0
 */
public interface IWXDResMessageService {
	/**
	 * easy dataGrid格式数据
	 * 初始化查詢自动回复列表
	 * @param resMsg	
	 * @return
	 */
	public List<WXDResMessage> dataGrid(WXDResMessage resMsg);
	/**
	 * 添加智能回复
	 * @param resMsg
	 */
	public void add(WXDResMessage resMsg);
	/**
	 * 修改智能回复
	 * @param resMsg
	 */
	public void update(WXDResMessage resMsg);
	/**
	 * 批量删除智能回复
	 * @param ids
	 */
	public void batchDel(String ids);
	/**
	 * 根据智能回复名称查询
	 * @param resMsg
	 * @return
	 */
	public List<WXDResMessage> searchByResMsgname(WXDResMessage resMsg);
	/**
	 * 根据智能回复id查询智能回复信息
	 * @param resmsgid
	 * @return
	 */
	public WXDResMessage selectResMByResmsgid(String resmsgid);
}
