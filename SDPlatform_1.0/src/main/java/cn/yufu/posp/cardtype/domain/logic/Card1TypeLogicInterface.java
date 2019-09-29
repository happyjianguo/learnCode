package cn.yufu.posp.cardtype.domain.logic;

import java.util.HashMap;

import cn.yufu.posp.cardtype.domain.model.Cardtype1;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public interface Card1TypeLogicInterface {

	// ���
	// ���ҿ�����
	public PageInfoModel queryCardType(Cardtype1 newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// ɾ��������
	public void deleteCardType(Cardtype1 newKeys, UserData ud) throws OAException;

	// �����µĿ�����
	public void createCardType(Cardtype1 newModel, UserData ud) throws OAException;

	// ��key�鿨����
	public HashMap queryCardTypeByKey(String newKey, UserData ud) throws OAException;

	// �޸Ŀ�����
	public void saveCardType(Cardtype1 newModel, UserData ud) throws OAException;

}
