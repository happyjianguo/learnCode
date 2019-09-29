package cn.yufu.posp.bookManager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.bookManager.domain.model.AnalyzeMerfeeBook;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

/**
 * 商户手续费应收单
 * 
 * @author King
 * 
 */
public interface AnalyzeMerfeeBookHibernateHQL {

	// 查找所有记录总数
	public int queryCount(AnalyzeMerfeeBook queryModel, UserData ud) throws OAException;

	// 查找记录
	public List queryAll(AnalyzeMerfeeBook queryModel, int startIndex, int pageSize, String orderField, String orderType, UserData ud) throws OAException;

	// 查询明细
	public AnalyzeMerfeeBook queryDetail(String id, UserData ud) throws OAException;

}
