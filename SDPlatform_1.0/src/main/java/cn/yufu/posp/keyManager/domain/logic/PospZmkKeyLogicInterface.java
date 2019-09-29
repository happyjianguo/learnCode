package cn.yufu.posp.keyManager.domain.logic;

import java.util.HashMap;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.keyManager.domain.model.PospZmkKey;

public interface PospZmkKeyLogicInterface {
	/** 查找所有记录 */
	public PageInfoModel queryAllItem(PospZmkKey queryModel, PageInfoModel pageInfo, UserData ud) throws OAException;

	// 显示一条记录
	public HashMap findItemByKey(PospZmkKey btsKey, UserData ud) throws OAException ;

	// 修改一条记录
	public void saveItem(PospZmkKey btsKey, UserData ud) throws OAException ;

	// 创建一条记录
	public void createItem(PospZmkKey btsKey, UserData ud) throws OAException;

}
