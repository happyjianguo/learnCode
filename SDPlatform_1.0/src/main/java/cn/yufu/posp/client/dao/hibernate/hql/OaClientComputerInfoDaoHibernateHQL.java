package cn.yufu.posp.client.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.client.domain.model.OaClientComputerInfoModel;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

public interface OaClientComputerInfoDaoHibernateHQL {
	// ���Ҽ�¼����
	public int querySum(OaClientComputerInfoModel queryModel, UserData ud) throws OAException;

	// �������м�¼
	public List queryAll(OaClientComputerInfoModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud)
			throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// �½�һ����¼
	public void createItem(OaClientComputerInfoModel newModel, UserData ud) throws OAException;

	// ��ʾһ����¼
	public OaClientComputerInfoModel findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(OaClientComputerInfoModel newModel, UserData ud) throws OAException;

}
