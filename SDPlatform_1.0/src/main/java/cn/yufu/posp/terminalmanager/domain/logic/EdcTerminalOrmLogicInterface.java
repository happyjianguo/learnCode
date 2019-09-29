package cn.yufu.posp.terminalmanager.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalOrm;

public interface EdcTerminalOrmLogicInterface {

	/** 查找所有记录 */
	public PageInfoModel queryAllItem(EdcTerminalOrm edcTerminalOrm, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	/** 查找一条记录 */
	public HashMap findItemByKey(EdcTerminalOrm edcTerminalOrm, UserData ud) throws OAException;

	/** 删除记录 */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** 添加一条记录 */
	public void createItem(EdcTerminalOrm edcTerminalOrm, UserData ud) throws OAException;

	/** 修改 / 保存 记录 */
	public void saveItem(EdcTerminalOrm edcTerminalOrm, UserData ud) throws OAException;

	public void saveUploadItem(List<EdcTerminalOrm> edcTerminalOrmList, UserData ud) throws OAException;

	public EdcTerminalOrm queryModualBy(String merchantId, String terminalId) throws OAException;
	
	/**校验联合主键唯一性PKEY */
	public String checkEdcTerminalOrmPKEY(String merchantId,String terminalId,String moduleId) throws OAException;
		
	/**校验索引唯一性ORM */
	public String checkEdcTerminalOrmORM(String bankMerchantId,String bankTerminalId,String moduleId) throws OAException;
	
}
