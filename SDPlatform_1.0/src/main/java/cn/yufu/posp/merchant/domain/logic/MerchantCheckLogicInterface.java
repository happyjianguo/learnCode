package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBaseBo;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;

public interface MerchantCheckLogicInterface {

	// �������м�¼
	public PageInfoModel queryAll(MerchantBaseBo queryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// ��ʾһ����¼
	public HashMap findItem(String newKey, UserData ud) throws OAException;
	
	// �޸�һ����¼
	public void saveItem(MerchantBaseBo model, UserData ud) throws OAException;

}
