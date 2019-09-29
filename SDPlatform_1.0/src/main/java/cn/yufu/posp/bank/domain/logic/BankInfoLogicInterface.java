package cn.yufu.posp.bank.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.bank.domain.model.BankInfoId;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public interface BankInfoLogicInterface {

	// 查找所有记录
	public PageInfoModel queryAll(BankInfoId newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// 删除一条记录
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// 显示一条记录
	public HashMap findItem(String newKey, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(BankInfoId newModel, UserData ud) throws OAException;

	// 创建一条记录
	public void createItem(BankInfoId newModel, UserData ud) throws OAException;

}
