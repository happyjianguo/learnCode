package cn.yufu.posp.sysModule.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.sysModule.domain.model.SysModuleModel;

public interface SysModuleOtherLogicInterface {

	// �������м�¼
	public PageInfoModel queryAll(SysModuleModel newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// ����һ����¼
	public void createItem(SysModuleModel newModel, UserData ud) throws OAException;

	// ��ʾһ����¼
	public HashMap findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(SysModuleModel newModel, UserData ud) throws OAException;

}
