package cn.yufu.posp.terminalmanager.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcCard;

public interface EdcCardLogicInterface {

	/** �������м�¼ */
	public PageInfoModel queryAllItem(EdcCard edcCard, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	/** ����һ����¼ */
	public HashMap findItemByKey(EdcCard key, UserData ud) throws OAException;

	/** ɾ����¼ */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** ���һ����¼ */
	public void createItem(EdcCard edcCard, UserData ud) throws OAException;

	/** �޸� / ���� ��¼ */
	public void saveItem(EdcCard edcCard, UserData ud) throws OAException;

}
