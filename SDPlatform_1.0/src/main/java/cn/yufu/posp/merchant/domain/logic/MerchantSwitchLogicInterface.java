package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantSwitchModel;

public interface MerchantSwitchLogicInterface {

	// �������м�¼
	public PageInfoModel queryAll(MerchantSwitchModel newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// ��ʾһ����¼
	public HashMap findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(MerchantSwitchModel newModel, UserData ud) throws OAException;

	// ����һ����¼
	public void createItem(MerchantSwitchModel newModel, UserData ud) throws OAException;

	// ��ʾ�̻�����
	public String findMerchantName(String merchantId, UserData ud);

}
