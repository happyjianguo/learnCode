package cn.yufu.posp.queryManager.domain.logic;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.queryManager.dao.hibernate.hql.CurTranLsWarnHibernateHQL;
import cn.yufu.posp.queryManager.domain.model.CurTranLsWarn;

public class CurTranLsWarnLogic extends BaseLogic implements CurTranLsWarnLogicInterface {

	private static final Log log = LogFactory.getLog("query");

	@Override
	public PageInfoModel queryAll(CurTranLsWarn queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("CurTranLsLogic.queryAll()开始调用：分页查询。");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			CurTranLsWarnHibernateHQL curTranLsDao = (CurTranLsWarnHibernateHQL) getBean("curTranLsWarnDao");

			// 得到记录的总条数
			totalCount = curTranLsDao.queryCount(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = curTranLsDao.query(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);
			log.info("CurTranLsLogic.queryAll()结束调用：分页查询。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("CurTranLsLogic.queryAll()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	@Override
	public List queryExport(CurTranLsWarn queryModel, UserData ud) throws OAException {
		List list = null;

		try {
			log.info("CurTranLsLogic.queryAll()开始调用：分页查询。");

			CurTranLsWarnHibernateHQL curTranLsDao = (CurTranLsWarnHibernateHQL) getBean("curTranLsWarnDao");

			list = curTranLsDao.queryExport(queryModel, ud);

			log.info("CurTranLsLogic.queryAll()结束调用：分页查询。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("CurTranLsLogic.queryAll()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return list;
	}

}
