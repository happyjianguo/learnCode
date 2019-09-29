package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.TabBusinessRatesModel;

public interface TabBusinessRatesLogicInterface {

	// �������м�¼
	public PageInfoModel queryAll(TabBusinessRatesModel newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// ��ʾһ����¼
	public HashMap findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(TabBusinessRatesModel model, UserData ud) throws OAException;

	// ����һ����¼
	public void createItem(TabBusinessRatesModel newModel, UserData ud) throws OAException;

	 //��ѯҵ���¼ 
	public List<TabBusinessRatesModel> queryAllTabBusinessRates() throws OAException;
	
}
