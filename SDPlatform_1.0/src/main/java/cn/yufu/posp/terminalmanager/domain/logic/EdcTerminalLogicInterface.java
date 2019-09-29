package cn.yufu.posp.terminalmanager.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;

public interface EdcTerminalLogicInterface {

	/** �������м�¼ */
	public PageInfoModel queryAllItem(EdcTerminal edcTerminal, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	/** ����һ����¼ */
	public HashMap findItemByKey(EdcTerminal edcTerminal, UserData ud) throws OAException;

	/** ɾ����¼ */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** ���һ����¼ */
	public void createItem(EdcTerminal edcTerminal, UserData ud) throws OAException;

	/** �޸� / ���� ��¼ */
	public void saveItem(EdcTerminal edcTerminal, UserData ud) throws OAException;

	public void saveUploadItem(List<EdcTerminal> edcTerminalList, UserData ud) throws OAException;
	public List<EdcTerminal> queryTerminalListByMerchantId(String merchantId,String cardBin, UserData ud) throws OAException;
	/**
	 * �ж����е��ն��Ƿ��Ǹ��̻���
	 * @param merchantId
	 * @param terminals
	 * @param ud
	 * @return	true���в��Ǹ��̻����նˣ�false�����Ǹ��̻����ն�
	 * @throws OAException
	 */
	public boolean checkTerminalIsNotMechants(String merchantId, String terminals,
			UserData ud) throws OAException;
}
