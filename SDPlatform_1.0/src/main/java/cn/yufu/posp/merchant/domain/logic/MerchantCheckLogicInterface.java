package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBaseBo;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;

public interface MerchantCheckLogicInterface {

	// 查找所有记录
	public PageInfoModel queryAll(MerchantBaseBo queryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// 显示一条记录
	public HashMap findItem(String newKey, UserData ud) throws OAException;
	
	// 修改一条记录
	public void saveItem(MerchantBaseBo model, UserData ud) throws OAException;

}
