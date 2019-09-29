package cn.yufu.posp.client.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.client.domain.model.OaClientComputerInfoModel;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public interface OaClientComputerInfoLogicInterface {

	// �������м�¼
	public PageInfoModel queryAll(OaClientComputerInfoModel newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// ����һ����¼
	public void createItem(OaClientComputerInfoModel newModel, UserData ud) throws OAException;

	// ��ʾһ����¼
	public HashMap findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(OaClientComputerInfoModel newModel, UserData ud) throws OAException;

}
