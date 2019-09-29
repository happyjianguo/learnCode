package cn.yufu.posp.terminalmanager.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.SysParameter;

public interface SysParameterLogicInterface {

	/** �������м�¼ */
	public PageInfoModel queryAllItem(SysParameter sysParameter, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	/** ����һ����¼ */
	public HashMap findItemByKey(SysParameter key, UserData ud) throws OAException;

	/** ɾ����¼ */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** ���һ����¼ */
	public void createItem(SysParameter sysParameter, UserData ud) throws OAException;

	/** �޸� / ���� ��¼ */
	public void saveItem(SysParameter sysParameter, UserData ud) throws OAException;

	/** ����ö��ֵ */
	public List queryAllItem(SysParameter sysParameter, UserData ud) throws OAException;

	/** ����һ��ö��ֵ */
	public SysParameter findItemByKeyValue(SysParameter key, UserData ud) throws OAException;
}
