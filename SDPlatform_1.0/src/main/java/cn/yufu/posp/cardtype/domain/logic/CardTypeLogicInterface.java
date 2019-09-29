package cn.yufu.posp.cardtype.domain.logic;

import java.util.HashMap;

import cn.yufu.posp.cardtype.domain.model.CardType;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public interface CardTypeLogicInterface {

	// 类别
	// 查找机构
	public PageInfoModel queryCardType(CardType newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// 删除机构
	public void deleteCardType(CardType newKeys, UserData ud) throws OAException;

	// 创建新的机构
	public void createCardType(CardType newModel, UserData ud) throws OAException;

	// 按key查机构
	public HashMap queryCardTypeByKey(String newKey, UserData ud) throws OAException;

	// 保存机构
	public void saveCardType(CardType newModel, UserData ud) throws OAException;

}
