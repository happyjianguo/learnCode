package cn.yufu.posp.cardtype.domain.logic;

import java.util.HashMap;

import cn.yufu.posp.cardtype.domain.model.CardType;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public interface CardTypeLogicInterface {

	// ���
	// ���һ���
	public PageInfoModel queryCardType(CardType newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// ɾ������
	public void deleteCardType(CardType newKeys, UserData ud) throws OAException;

	// �����µĻ���
	public void createCardType(CardType newModel, UserData ud) throws OAException;

	// ��key�����
	public HashMap queryCardTypeByKey(String newKey, UserData ud) throws OAException;

	// �������
	public void saveCardType(CardType newModel, UserData ud) throws OAException;

}
