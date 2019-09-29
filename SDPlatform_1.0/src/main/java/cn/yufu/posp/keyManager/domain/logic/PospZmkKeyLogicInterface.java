package cn.yufu.posp.keyManager.domain.logic;

import java.util.HashMap;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.keyManager.domain.model.PospZmkKey;

public interface PospZmkKeyLogicInterface {
	/** �������м�¼ */
	public PageInfoModel queryAllItem(PospZmkKey queryModel, PageInfoModel pageInfo, UserData ud) throws OAException;

	// ��ʾһ����¼
	public HashMap findItemByKey(PospZmkKey btsKey, UserData ud) throws OAException ;

	// �޸�һ����¼
	public void saveItem(PospZmkKey btsKey, UserData ud) throws OAException ;

	// ����һ����¼
	public void createItem(PospZmkKey btsKey, UserData ud) throws OAException;

}
