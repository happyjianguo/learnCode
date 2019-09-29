package cn.yufu.posp.logManager.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.logManager.domain.model.OperateLog;

public interface LogLogicInterface {

	// 查找所有记录
	public PageInfoModel queryAll(OperateLog newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// 显示一条记录
	public HashMap findItem(String newKey, UserData ud) throws OAException;

	// 创建一条记录
	public void createItem(OperateLog newModel, UserData ud) throws OAException;

}
