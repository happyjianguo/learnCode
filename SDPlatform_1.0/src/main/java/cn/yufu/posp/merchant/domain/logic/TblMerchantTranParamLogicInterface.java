package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.TblMerchantTranParamModel;

public interface TblMerchantTranParamLogicInterface {

	// 查找所有记录
	public PageInfoModel queryAll(TblMerchantTranParamModel newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// 显示一条记录
	public HashMap findItem(String newKey, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(TblMerchantTranParamModel newModel, UserData ud) throws OAException;

	// 创建一条记录
	public void createItem(TblMerchantTranParamModel newModel, UserData ud) throws OAException;

	public String checkMerchantId(String merchantId, UserData ud) throws OAException;

}
