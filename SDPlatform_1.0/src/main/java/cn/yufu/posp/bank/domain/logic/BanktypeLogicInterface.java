package cn.yufu.posp.bank.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public interface BanktypeLogicInterface {

	// �������м�¼
	public PageInfoModel queryAll(Banktype newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// ��ʾһ����¼
	public HashMap findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(Banktype newModel, UserData ud) throws OAException;

	// ����һ����¼
	public void createItem(Banktype newModel, UserData ud) throws OAException;

}
