package cn.yufu.posp.bookManager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.bookManager.domain.model.AnalyzeMerstlBook;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

/**
 * 商户结算打款单
 * 
 * @author King
 * 
 */
public interface AnalyzeMerstlBookHibernateHQL {

	// 查找所有记录总数
	public int queryCount(AnalyzeMerstlBook queryModel, UserData ud) throws OAException;

	// 查找记录
	public List queryAll(AnalyzeMerstlBook queryModel, int startIndex, int pageSize, String orderField, String orderType, UserData ud) throws OAException;

	// 查询明细
	public AnalyzeMerstlBook queryDetail(String id, UserData ud) throws OAException;

}
