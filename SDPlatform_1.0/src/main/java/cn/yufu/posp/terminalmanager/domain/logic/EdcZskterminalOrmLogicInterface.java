/**
 *包名:cn.yufu.posp.terminalmanager.domain.logic
 *描述:package cn.yufu.posp.terminalmanager.domain.logic;
 */
package cn.yufu.posp.terminalmanager.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcZskterminalOrm;

/**
 * EdcZskterminalOrmLogicInterface.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:gll 
 * 时间:2018年3月2日
 * 描述:专属卡终端
 */
public interface EdcZskterminalOrmLogicInterface {

	/** 查找所有记录 */
	public PageInfoModel queryAllItem(EdcZskterminalOrm edcZskterminalOrm, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	/** 查找一条记录 */
	public HashMap findItemByKey(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException;

	/** 删除记录 */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** 添加一条记录 */
	public void createItem(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException;

	/** 修改 / 保存 记录 */
	public void saveItem(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException;
	
	public void saveUploadItem(List<EdcZskterminalOrm> edcZskterminalOrmList, UserData ud) throws OAException;
	
}
