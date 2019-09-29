package cn.yufu.posp.queryManager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.queryManager.domain.model.CurTranLsWarn;

public interface CurTranLsWarnHibernateHQL {
	// 查找流水总数
	public int queryCount(CurTranLsWarn newQueryModel, UserData ud) throws OAException;

	// 查找流水
	public List query(CurTranLsWarn newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	public List queryExport(CurTranLsWarn queryModel, UserData ud) throws OAException;

}
