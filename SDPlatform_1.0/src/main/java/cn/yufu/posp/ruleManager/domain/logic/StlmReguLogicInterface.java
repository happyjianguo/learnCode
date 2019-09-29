package cn.yufu.posp.ruleManager.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.ruleManager.domain.model.TblStlmRegu;

public interface StlmReguLogicInterface {

	// �������м�¼
	public PageInfoModel queryAll(TblStlmRegu newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// ��ʾһ����¼
	public HashMap findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(TblStlmRegu newModel, UserData ud) throws OAException;

	// ����һ����¼
	public void createItem(TblStlmRegu newModel, UserData ud) throws OAException;
}
