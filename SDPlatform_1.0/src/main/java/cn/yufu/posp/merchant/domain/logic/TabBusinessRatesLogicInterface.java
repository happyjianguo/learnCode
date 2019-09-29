package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.TabBusinessRatesModel;

public interface TabBusinessRatesLogicInterface {

	// 查找所有记录
	public PageInfoModel queryAll(TabBusinessRatesModel newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// 显示一条记录
	public HashMap findItem(String newKey, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(TabBusinessRatesModel model, UserData ud) throws OAException;

	// 创建一条记录
	public void createItem(TabBusinessRatesModel newModel, UserData ud) throws OAException;

	 //查询业务记录 
	public List<TabBusinessRatesModel> queryAllTabBusinessRates() throws OAException;
	
}
