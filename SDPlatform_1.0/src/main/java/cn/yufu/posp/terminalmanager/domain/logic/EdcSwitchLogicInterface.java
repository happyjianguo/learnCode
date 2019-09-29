package cn.yufu.posp.terminalmanager.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcSwitch;

public interface EdcSwitchLogicInterface {

	/** �������м�¼ */
	public PageInfoModel queryAllItem(EdcSwitch edcSwitch, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	/** ����һ����¼ */
	public HashMap findItemByKey(EdcSwitch key, UserData ud) throws OAException;

	/** ɾ����¼ */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** ���һ����¼ */
	public void createItem(EdcSwitch edcSwitch, UserData ud) throws OAException;

	/** �޸� / ���� ��¼ */
	public void saveItem(EdcSwitch edcSwitch, UserData ud) throws OAException;

}
