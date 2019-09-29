package cn.yufu.posp.sysModule.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.sysModule.domain.model.SysModuleModel;

public interface SysModuleOtherLogicInterface {

	// 查找所有记录
	public PageInfoModel queryAll(SysModuleModel newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// 删除一条记录
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// 创建一条记录
	public void createItem(SysModuleModel newModel, UserData ud) throws OAException;

	// 显示一条记录
	public HashMap findItem(String newKey, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(SysModuleModel newModel, UserData ud) throws OAException;

}
