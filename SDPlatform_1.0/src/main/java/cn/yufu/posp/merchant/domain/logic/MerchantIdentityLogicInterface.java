package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBaseBo;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.merchant.domain.model.MerchantIdentity;

public interface MerchantIdentityLogicInterface {

	// �������м�¼
	public PageInfoModel queryAll(MerchantIdentity newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// ��ʾһ����¼
	public HashMap findItem(String newKey, UserData ud) throws OAException;
	
	public MerchantBaseBo findBaseInfoById(String merchantId, UserData ud) throws OAException ;

	// �޸�һ����¼
	public void saveItem(MerchantIdentity model, UserData ud) throws OAException;


}
