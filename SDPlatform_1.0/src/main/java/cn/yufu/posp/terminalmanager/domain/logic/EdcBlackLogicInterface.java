package cn.yufu.posp.terminalmanager.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcBlack;

public interface EdcBlackLogicInterface {

	/** �������м�¼ */
	public PageInfoModel queryAllItem(EdcBlack edcTerminal, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	/** ����һ����¼ */
	public HashMap findItemByKey(EdcBlack edcTerminal, UserData ud) throws OAException;

	/** ɾ����¼ */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** ���һ����¼ */
	public void createItem(EdcBlack edcTerminal, UserData ud) throws OAException;

	/** �޸� / ���� ��¼ */
	public void saveItem(EdcBlack edcTerminal, UserData ud) throws OAException;

}
