package cn.yufu.posp.logManager.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.logManager.domain.model.OperateLog;

public interface LogLogicInterface {

	// �������м�¼
	public PageInfoModel queryAll(OperateLog newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// ��ʾһ����¼
	public HashMap findItem(String newKey, UserData ud) throws OAException;

	// ����һ����¼
	public void createItem(OperateLog newModel, UserData ud) throws OAException;

}
