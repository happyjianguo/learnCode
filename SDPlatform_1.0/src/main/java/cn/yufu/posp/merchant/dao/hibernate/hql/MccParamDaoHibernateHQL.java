package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MccParamModel;

public interface MccParamDaoHibernateHQL {

	// ���Ҽ�¼����
	public int querySum(MccParamModel queryModel, UserData ud) throws OAException;

	// �������м�¼
	public List queryAll(MccParamModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// ɾ��һ����¼
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// ��ʾһ����¼
	public MccParamModel findItem(String newKey, UserData ud) throws OAException;

	// �޸�һ����¼
	public void saveItem(MccParamModel newModel, UserData ud) throws OAException;

	// �½�һ����¼
	public void createItem(MccParamModel newModel, UserData ud) throws OAException;
	// ��ѯ���еļ�¼����������ѯ
	// public List findAllById(MccParamModel newModel, UserData ud) throws
	// OAException;
}
