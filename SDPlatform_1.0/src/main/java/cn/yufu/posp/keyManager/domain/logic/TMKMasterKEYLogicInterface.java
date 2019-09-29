package cn.yufu.posp.keyManager.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.keyManager.domain.model.BtsKey;
import cn.yufu.posp.merchant.domain.model.MerchantBlackModel;

public interface TMKMasterKEYLogicInterface {
	/** �������м�¼ */
	public PageInfoModel queryAllItem(BtsKey queryModel, PageInfoModel pageInfo, UserData ud) throws OAException;

	// ��ʾһ����¼
	public HashMap findItemByKey(BtsKey btsKey, UserData ud) throws OAException ;

	// �޸�һ����¼
	public void saveItem(BtsKey btsKey, UserData ud) throws OAException ;

	// ����һ����¼
	public void createItem(BtsKey btsKey, UserData ud) throws OAException;
	//����Excel��TxT�ļ��Ĺ�������
	public List queryExport(BtsKey queryModel, UserData ud) throws OAException;
}
