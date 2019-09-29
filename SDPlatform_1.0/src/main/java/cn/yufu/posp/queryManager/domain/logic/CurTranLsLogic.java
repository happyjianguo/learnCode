package cn.yufu.posp.queryManager.domain.logic;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.queryManager.dao.hibernate.hql.CurTranLsHibernateHQL;
import cn.yufu.posp.queryManager.domain.model.CurTranLs;

public class CurTranLsLogic extends BaseLogic implements CurTranLsLogicInterface {

	private static final Log log = LogFactory.getLog("query");

	@Override
	public PageInfoModel queryAll(CurTranLs queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("CurTranLsLogic.queryAll()��ʼ���ã���ҳ��ѯ��");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			CurTranLsHibernateHQL curTranLsDao = (CurTranLsHibernateHQL) getBean("curTranLsDao");

			// �õ���¼��������
			totalCount = curTranLsDao.queryCount(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = curTranLsDao.query(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("CurTranLsLogic.queryAll()�������ã���ҳ��ѯ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("CurTranLsLogic.queryAll()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	@Override
	public CurTranLs queryDetail(CurTranLs queryModel, UserData ud) throws OAException {
		CurTranLs model = new CurTranLs();
		try {
			log.info("CurTranLsLogic.queryDetail()��ʼ���ã���ϸ��ѯ��");
			CurTranLsHibernateHQL curTranLsDao = (CurTranLsHibernateHQL) getBean("curTranLsDao");
			model = curTranLsDao.queryDetail(queryModel, ud);
			log.info("CurTranLsLogic.queryAll()�������ã���ϸ��ѯ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("CurTranLsLogic.queryAll()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return model;
	}

	@Override
	public List queryExport(CurTranLs queryModel, UserData ud) throws OAException {
		List list = null;

		try {
			log.info("CurTranLsLogic.queryAll()��ʼ���ã���ҳ��ѯ��");

			CurTranLsHibernateHQL curTranLsDao = (CurTranLsHibernateHQL) getBean("curTranLsDao");

			list = curTranLsDao.queryExport(queryModel, ud);

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
