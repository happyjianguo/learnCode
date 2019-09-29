package cn.yufu.posp.jgmanager.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.jgmanager.domain.model.JgModel;

public interface JgLogicInterface {

	// 类别
	// 查找机构
	public PageInfoModel queryJg(JgModel newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// 删除机构
	public void deleteJg(List newKeys, UserData ud) throws OAException;

	// 创建新的机构
	public void createJg(JgModel newModel, UserData ud) throws OAException;

	// 按key查机构
	public HashMap queryJgByKey(String newKey, UserData ud) throws OAException;

	// 保存机构
	public void saveJg(JgModel newModel, UserData ud) throws OAException;

}
