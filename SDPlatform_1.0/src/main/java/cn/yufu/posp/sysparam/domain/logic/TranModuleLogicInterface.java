
package cn.yufu.posp.sysparam.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.sysparam.domain.model.TranModuleInf;

public interface TranModuleLogicInterface {

	// �������м�¼
	public PageInfoModel queryAll(TranModuleInf newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// ����һ����¼
	public void createItem(TranModuleInf newModel, UserData ud) throws OAException;

	// ��ʾһ����¼
	public HashMap findItem(TranModuleInf newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(TranModuleInf newModel, UserData ud) throws OAException;

}
