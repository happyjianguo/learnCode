/**
 *����:cn.yufu.posp.queryManager.dao.hibernate.hql
 *����:package cn.yufu.posp.queryManager.dao.hibernate.hql;
 */
package cn.yufu.posp.queryManager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.queryManager.domain.model.TblExpCurTranLog;

/**
 * CurTranLsWechatAlipayHibernateHQL.java
 * ��Ȩ����(C) 2017 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2017��7��21��
 * ����:΢��֧����hql
 */
public interface CurTranLsWechatAlipayHibernateHQL {

	public int queryCount(TblExpCurTranLog queryModel, UserData sessionUserData, String startDate, String endDate) throws OAException;

	@SuppressWarnings("rawtypes")
	public List query(TblExpCurTranLog queryModel, int startIndex, int pageSize, String orderField, String orderType,
			UserData sessionUserData, String startDate, String endDate) throws OAException;

	@SuppressWarnings("rawtypes")
	public List queryExport(TblExpCurTranLog queryModel, UserData sessionUserData, String startDate, String endDate) throws OAException;

}
