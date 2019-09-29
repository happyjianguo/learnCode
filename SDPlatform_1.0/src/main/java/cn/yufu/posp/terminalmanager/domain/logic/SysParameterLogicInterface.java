package cn.yufu.posp.terminalmanager.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.SysParameter;

public interface SysParameterLogicInterface {

	/** 查找所有记录 */
	public PageInfoModel queryAllItem(SysParameter sysParameter, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	/** 查找一条记录 */
	public HashMap findItemByKey(SysParameter key, UserData ud) throws OAException;

	/** 删除记录 */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** 添加一条记录 */
	public void createItem(SysParameter sysParameter, UserData ud) throws OAException;

	/** 修改 / 保存 记录 */
	public void saveItem(SysParameter sysParameter, UserData ud) throws OAException;

	/** 查找枚举值 */
	public List queryAllItem(SysParameter sysParameter, UserData ud) throws OAException;

	/** 查找一条枚举值 */
	public SysParameter findItemByKeyValue(SysParameter key, UserData ud) throws OAException;
}
