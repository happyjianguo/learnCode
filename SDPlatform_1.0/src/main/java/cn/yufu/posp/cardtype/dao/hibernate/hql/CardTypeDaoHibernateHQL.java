package cn.yufu.posp.cardtype.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.cardtype.domain.model.CardType;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

public interface CardTypeDaoHibernateHQL

{
	// 类别
	// 查找机构总数
	public int queryCardTypeCount(CardType newQueryModel, UserData ud) throws OAException;

	// 查找机构
	public List queryCardType(CardType newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// 删除机构
	public void deleteCardType(CardType newKeys, UserData ud) throws OAException;

	// 新建机构
	public void createCardType(CardType newModel, UserData ud) throws OAException;

	// 按KEY查找机构
	public CardType queryCardTypeByKey(String newKey, UserData ud) throws OAException;

	// 保存机构
	public void saveCardType(CardType newModel, UserData ud) throws OAException;

}
