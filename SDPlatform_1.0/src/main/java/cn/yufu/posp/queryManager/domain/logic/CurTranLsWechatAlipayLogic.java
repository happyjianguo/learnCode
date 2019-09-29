/**
 *����:cn.yufu.posp.queryManager.domain.logic
 *����:package cn.yufu.posp.queryManager.domain.logic;
 */
package cn.yufu.posp.queryManager.domain.logic;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.queryManager.dao.hibernate.hql.CurTranLsWechatAlipayHibernateHQL;
import cn.yufu.posp.queryManager.domain.model.TblExpCurTranLog;

/**
 * CurTranLsWechatAlipayLogic.java
 * ��Ȩ����(C) 2017 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2017��7��21��
 * ����:΢��֧����Logic
 */
public class CurTranLsWechatAlipayLogic extends BaseLogic implements CurTranLsWechatAlipayLogicInterface {

	private static final Log log = LogFactory.getLog("query");
	
	@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	@Override
	public PageInfoModel queryAll(TblExpCurTranLog queryModel, PageInfoModel pageInfo, UserData sessionUserData, String startDate, String endDate)
			throws OAException {
		PageInfoModel page = pageInfo;
		try{
			log.info("CurTranLsWechatAlipayLogic.queryAll()��ʼ���ã���ҳ��ѯ��");
			
			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;
			
			CurTranLsWechatAlipayHibernateHQL CurTranLsWechatAlipayDao = (CurTranLsWechatAlipayHibernateHQL) getBean("curTranLsWechatAlipayDao");

			// �õ���¼��������
			totalCount = CurTranLsWechatAlipayDao.queryCount(queryModel, sessionUserData, startDate, endDate);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = CurTranLsWechatAlipayDao.query(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), sessionUserData, startDate, endDate);
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("CurTranLsWechatAlipayLogic.queryAll()�������ã���ҳ��ѯ��");
			
		}catch(Exception e){
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("CurTranLsWechatAlipayLogic.queryAll()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return page;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List queryExport(TblExpCurTranLog queryModel, UserData sessionUserData, String startDate, String endDate) throws OAException {
		List list = null;

		try {
			log.info("CurTranLsLogic.queryAll()��ʼ���ã���ҳ��ѯ��");

			CurTranLsWechatAlipayHibernateHQL CurTranLsWechatAlipayDao = (CurTranLsWechatAlipayHibernateHQL) getBean("curTranLsWechatAlipayDao");

			list = CurTranLsWechatAlipayDao.queryExport(queryModel, sessionUserData, startDate, endDate);

			log.info("CurTranLsLogic.queryAll()�������ã���ҳ��ѯ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("CurTranLsLogic.queryAll()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return list;
	}

}
