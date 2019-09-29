package cn.yufu.posp.cardtype.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.cardtype.domain.model.Cardtype1;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

public interface Card1TypeDaoHibernateHQL

{
	// 查找总数卡类型
	public int queryCardTypeCount(Cardtype1 newQueryModel, UserData ud) throws OAException;

	// 查找卡类型
	public List queryCardType(Cardtype1 newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// 删除卡类型
	public void deleteCardType(Cardtype1 newKeys, UserData ud) throws OAException;

	// 新建卡类型
	public void createCardType(Cardtype1 newModel, UserData ud) throws OAException;

	// 查找一条卡类型信息
	public Cardtype1 queryCardTypeByKey(String newKey, UserData ud) throws OAException;

	// 修改卡类型
	public void saveCardType(Cardtype1 newModel, UserData ud) throws OAException;

}
