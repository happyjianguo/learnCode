package cn.yufu.posp.bookManager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.bookManager.domain.model.AnalyzeCupcheckStatics;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

public interface AnalyzeCupcheckStaticsHibernateHQL {

	// 查找所有记录总数
	public int queryCount(AnalyzeCupcheckStatics queryModel, UserData ud) throws OAException;

	// 查找记录
	public List queryAll(AnalyzeCupcheckStatics queryModel, int startIndex, int pageSize, String orderField, String orderType, UserData ud) throws OAException;

	// 查询明细
	public AnalyzeCupcheckStatics queryDetail(String id, UserData ud) throws OAException;

}
