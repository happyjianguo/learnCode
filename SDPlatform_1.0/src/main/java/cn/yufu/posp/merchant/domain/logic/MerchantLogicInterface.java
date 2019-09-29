package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBaseBo;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.sysparam.domain.model.AreaCodeInfo;

public interface MerchantLogicInterface {

	// �������м�¼
	public PageInfoModel queryAll(MerchantBaseModel newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// ��ʾһ����¼
	public HashMap findItem(String newKey, UserData ud) throws OAException;
	
	public MerchantBaseBo findBaseInfoById(String merchantId, UserData ud) throws OAException ;

	// �޸�һ����¼
	public void saveItem(MerchantBaseModel model, UserData ud) throws OAException;

	// ����һ����¼
	public void createItem(MerchantBaseModel newModel, UserData ud) throws OAException;

	// ajax ��ѯǩԼ�кź�������
	public List<MerchantBaseModel> findSignBankInfo(String bankId, UserData ud);

	// ajax ��ѯ�̻�����
	public List<MerchantBaseModel> findMccInfo(String mcc, UserData ud);
	// ��ѯ����
	// public List findAllJGs(UserData ud);

	public void saveBaseInfo(MerchantBaseBo model, UserData ud) throws OAException;

	public List<AreaCodeInfo> findArea(AreaCodeInfo area, UserData ud);
}
