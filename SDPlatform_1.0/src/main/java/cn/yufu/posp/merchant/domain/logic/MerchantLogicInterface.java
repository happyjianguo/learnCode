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

	// 查找所有记录
	public PageInfoModel queryAll(MerchantBaseModel newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// 删除一条记录
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// 显示一条记录
	public HashMap findItem(String newKey, UserData ud) throws OAException;
	
	public MerchantBaseBo findBaseInfoById(String merchantId, UserData ud) throws OAException ;

	// 修改一条记录
	public void saveItem(MerchantBaseModel model, UserData ud) throws OAException;

	// 创建一条记录
	public void createItem(MerchantBaseModel newModel, UserData ud) throws OAException;

	// ajax 查询签约行号和主机号
	public List<MerchantBaseModel> findSignBankInfo(String bankId, UserData ud);

	// ajax 查询商户类型
	public List<MerchantBaseModel> findMccInfo(String mcc, UserData ud);
	// 查询机构
	// public List findAllJGs(UserData ud);

	public void saveBaseInfo(MerchantBaseBo model, UserData ud) throws OAException;

	public List<AreaCodeInfo> findArea(AreaCodeInfo area, UserData ud);
}
