package cn.yufu.posp.cardtype.domain.logic;

import java.util.HashMap;

import cn.yufu.posp.cardtype.domain.model.Cardtype1;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public interface Card1TypeLogicInterface {

	// 类别
	// 查找卡类型
	public PageInfoModel queryCardType(Cardtype1 newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// 删除卡类型
	public void deleteCardType(Cardtype1 newKeys, UserData ud) throws OAException;

	// 创建新的卡类型
	public void createCardType(Cardtype1 newModel, UserData ud) throws OAException;

	// 按key查卡类型
	public HashMap queryCardTypeByKey(String newKey, UserData ud) throws OAException;

	// 修改卡类型
	public void saveCardType(Cardtype1 newModel, UserData ud) throws OAException;

}
