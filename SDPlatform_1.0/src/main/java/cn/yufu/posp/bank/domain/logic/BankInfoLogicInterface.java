package cn.yufu.posp.bank.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.bank.domain.model.BankInfoId;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public interface BankInfoLogicInterface {

	// �������м�¼
	public PageInfoModel queryAll(BankInfoId newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// ��ʾһ����¼
	public HashMap findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(BankInfoId newModel, UserData ud) throws OAException;

	// ����һ����¼
	public void createItem(BankInfoId newModel, UserData ud) throws OAException;

}
