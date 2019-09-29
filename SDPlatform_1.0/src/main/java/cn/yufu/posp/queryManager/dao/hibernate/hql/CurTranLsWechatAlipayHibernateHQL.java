/**
 *包名:cn.yufu.posp.queryManager.dao.hibernate.hql
 *描述:package cn.yufu.posp.queryManager.dao.hibernate.hql;
 */
package cn.yufu.posp.queryManager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.queryManager.domain.model.TblExpCurTranLog;

/**
 * CurTranLsWechatAlipayHibernateHQL.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年7月21日
 * 描述:微信支付宝hql
 */
public interface CurTranLsWechatAlipayHibernateHQL {

	public int queryCount(TblExpCurTranLog queryModel, UserData sessionUserData, String startDate, String endDate) throws OAException;

	@SuppressWarnings("rawtypes")
	public List query(TblExpCurTranLog queryModel, int startIndex, int pageSize, String orderField, String orderType,
			UserData sessionUserData, String startDate, String endDate) throws OAException;

	@SuppressWarnings("rawtypes")
	public List queryExport(TblExpCurTranLog queryModel, UserData sessionUserData, String startDate, String endDate) throws OAException;

}
