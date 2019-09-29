package cn.yufu.posp.terminalmanager.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalOrm;

public interface EdcTerminalOrmLogicInterface {

	/** �������м�¼ */
	public PageInfoModel queryAllItem(EdcTerminalOrm edcTerminalOrm, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	/** ����һ����¼ */
	public HashMap findItemByKey(EdcTerminalOrm edcTerminalOrm, UserData ud) throws OAException;

	/** ɾ����¼ */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** ���һ����¼ */
	public void createItem(EdcTerminalOrm edcTerminalOrm, UserData ud) throws OAException;

	/** �޸� / ���� ��¼ */
	public void saveItem(EdcTerminalOrm edcTerminalOrm, UserData ud) throws OAException;

	public void saveUploadItem(List<EdcTerminalOrm> edcTerminalOrmList, UserData ud) throws OAException;

	public EdcTerminalOrm queryModualBy(String merchantId, String terminalId) throws OAException;
	
	/**У����������Ψһ��PKEY */
	public String checkEdcTerminalOrmPKEY(String merchantId,String terminalId,String moduleId) throws OAException;
		
	/**У������Ψһ��ORM */
	public String checkEdcTerminalOrmORM(String bankMerchantId,String bankTerminalId,String moduleId) throws OAException;
	
}
