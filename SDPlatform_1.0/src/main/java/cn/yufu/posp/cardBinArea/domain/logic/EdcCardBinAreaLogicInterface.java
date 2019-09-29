package cn.yufu.posp.cardBinArea.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.cardBinArea.domain.model.EdcCardBinArea;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public interface EdcCardBinAreaLogicInterface {

	// �������м�¼
	public PageInfoModel queryAll(EdcCardBinArea newQueryModel,
			PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// ��ʾһ����¼
	public HashMap findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(EdcCardBinArea newModel, UserData ud)
			throws OAException;

	// ����һ����¼
	public void createItem(EdcCardBinArea newModel, UserData ud)
			throws OAException;

}
