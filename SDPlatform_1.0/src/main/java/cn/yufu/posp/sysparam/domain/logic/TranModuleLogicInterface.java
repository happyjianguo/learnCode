
package cn.yufu.posp.sysparam.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.sysparam.domain.model.TranModuleInf;

public interface TranModuleLogicInterface {

	// 查找所有记录
	public PageInfoModel queryAll(TranModuleInf newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// 删除一条记录
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// 创建一条记录
	public void createItem(TranModuleInf newModel, UserData ud) throws OAException;

	// 显示一条记录
	public HashMap findItem(TranModuleInf newKey, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(TranModuleInf newModel, UserData ud) throws OAException;

}
