package cn.yufu.posp.bookManager.domain.logic;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.bookManager.dao.hibernate.hql.AnalyzeCupcheckOkHibernateHQL;
import cn.yufu.posp.bookManager.domain.model.AnalyzeCupcheckOk;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public class AnalyzeCupcheckOkLogic extends BaseLogic implements AnalyzeCupcheckOkLogicInterface {
	private static final Log log = LogFactory.getLog("book");

	public PageInfoModel queryAll(AnalyzeCupcheckOk queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {

		PageInfoModel page = pageInfo;
		try {
			log.info("AnalyzeCupcheckOkLogic.queryAll()开始调用：分页查询。" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			AnalyzeCupcheckOkHibernateHQL dao = (AnalyzeCupcheckOkHibernateHQL) getBean("AnalyzeCupcheckOkDao");

			// 得到记录的总条数
			totalCount = dao.queryCount(queryModel, ud);

			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("AnalyzeCupcheckOkLogic.queryAll()结束调用：分页查询。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("AnalyzeCupcheckOkLogic.queryAll()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return page;
	}

	public AnalyzeCupcheckOk queryDetail(String id, UserData ud) throws OAException {
		AnalyzeCupcheckOk newModel = new AnalyzeCupcheckOk();
		try {
			log.info("AnalyzeCupcheckOkLogic.queryDetail()开始调用：显示一条记录。" + ud.getUserId());

			AnalyzeCupcheckOkHibernateHQL dao = (AnalyzeCupcheckOkHibernateHQL) getBean("AnalyzeCupcheckOkDao");
			newModel = dao.queryDetail(id, ud);
			if (newModel == null) {
				throw new OAException("您要查找的信息不存在！");
			}

			log.info("AnalyzeCupcheckOkLogic.queryDetail()结束调用：显示一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("MerchantLogic.queryDetail()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return newModel;
	}

}
