package cn.yufu.posp.cardtype.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.cardtype.domain.model.Cardtype1;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

public interface Card1TypeDaoHibernateHQL

{
	// ��������������
	public int queryCardTypeCount(Cardtype1 newQueryModel, UserData ud) throws OAException;

	// ���ҿ�����
	public List queryCardType(Cardtype1 newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// ɾ��������
	public void deleteCardType(Cardtype1 newKeys, UserData ud) throws OAException;

	// �½�������
	public void createCardType(Cardtype1 newModel, UserData ud) throws OAException;

	// ����һ����������Ϣ
	public Cardtype1 queryCardTypeByKey(String newKey, UserData ud) throws OAException;

	// �޸Ŀ�����
	public void saveCardType(Cardtype1 newModel, UserData ud) throws OAException;

}
