package cn.yufu.posp.terminalmanager.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcSwitch;

public interface EdcSwitchLogicInterface {

	/** 查找所有记录 */
	public PageInfoModel queryAllItem(EdcSwitch edcSwitch, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	/** 查找一条记录 */
	public HashMap findItemByKey(EdcSwitch key, UserData ud) throws OAException;

	/** 删除记录 */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** 添加一条记录 */
	public void createItem(EdcSwitch edcSwitch, UserData ud) throws OAException;

	/** 修改 / 保存 记录 */
	public void saveItem(EdcSwitch edcSwitch, UserData ud) throws OAException;

}
