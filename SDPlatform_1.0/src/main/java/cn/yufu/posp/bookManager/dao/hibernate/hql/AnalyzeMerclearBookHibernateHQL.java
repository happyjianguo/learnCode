package cn.yufu.posp.bookManager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.bookManager.domain.model.AnalyzeMerclearBook;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

/**
 * 商户清分单
 * 
 * @author King
 * 
 */
public interface AnalyzeMerclearBookHibernateHQL {

	// 查找所有记录总数
	public int queryCount(AnalyzeMerclearBook queryModel, UserData ud) throws OAException;

	// 查找记录
	public List queryAll(AnalyzeMerclearBook queryModel, int startIndex, int pageSize, String orderField, String orderType, UserData ud) throws OAException;

	// 查询明细
	public AnalyzeMerclearBook queryDetail(String id, UserData ud) throws OAException;

}
