package cn.yufu.posp.keyManager.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.keyManager.domain.model.BtsKey;
import cn.yufu.posp.merchant.domain.model.MerchantBlackModel;

public interface TMKMasterKEYLogicInterface {
	/** 查找所有记录 */
	public PageInfoModel queryAllItem(BtsKey queryModel, PageInfoModel pageInfo, UserData ud) throws OAException;

	// 显示一条记录
	public HashMap findItemByKey(BtsKey btsKey, UserData ud) throws OAException ;

	// 修改一条记录
	public void saveItem(BtsKey btsKey, UserData ud) throws OAException ;

	// 创建一条记录
	public void createItem(BtsKey btsKey, UserData ud) throws OAException;
	//导出Excel和TxT文件的公共函数
	public List queryExport(BtsKey queryModel, UserData ud) throws OAException;
}
