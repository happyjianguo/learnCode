package cn.yufu.posp.cardtype.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.cardtype.domain.model.CardType;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

public interface CardTypeDaoHibernateHQL

{
	// ���
	// ���һ�������
	public int queryCardTypeCount(CardType newQueryModel, UserData ud) throws OAException;

	// ���һ���
	public List queryCardType(CardType newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// ɾ������
	public void deleteCardType(CardType newKeys, UserData ud) throws OAException;

	// �½�����
	public void createCardType(CardType newModel, UserData ud) throws OAException;

	// ��KEY���һ���
	public CardType queryCardTypeByKey(String newKey, UserData ud) throws OAException;

	// �������
	public void saveCardType(CardType newModel, UserData ud) throws OAException;

}
