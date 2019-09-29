package cn.yufu.posp.logManager.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.logManager.dao.hibernate.hql.LogDaoHibernateHQL;
import cn.yufu.posp.logManager.domain.model.OperateLog;

public class LogLogic extends BaseLogic implements LogLogicInterface {

	private static final Log log = LogFactory.getLog("merchant");

	public LogLogic() {

	}

	/**
	 * 查询所有记录
	 */
	public PageInfoModel queryAll(OperateLog queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("LogLogic.queryAll()开始调用：分页查询。" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			LogDaoHibernateHQL dao = (LogDaoHibernateHQL) getBean("logDao");

			// 得到记录的总条数
			totalCount = dao.querySum(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("LogLogic.queryAll()结束调用：分页查询。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("LogLogic.queryAll()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	/**
	 * 显示一条记录
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		OperateLog newModel = new OperateLog();

		HashMap parameteMap = new HashMap();
		try {
			log.info("LogLogic.findItem()开始调用：显示一条记录。" + ud.getUserId());

			LogDaoHibernateHQL dao = (LogDaoHibernateHQL) getBean("logDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("您要查找的信息不存在！");
			}

			// 传model
			parameteMap.put("Infolist", newModel);

			log.info("LogLogic.findItem()结束调用：显示一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("LogLogic.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * 新建一条记录
	 */
	public void createItem(OperateLog newModel, UserData ud) throws OAException {
		try {
			log.info("LogLogic.createItem()开始调用：新建一条记录。" + ud.getUserId());
			LogDaoHibernateHQL dao = (LogDaoHibernateHQL) getBean("logDao");
			
			dao.createItem(newModel, ud);
			
			log.info("LogLogic.createItem()结束调用：新建一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("LogLogic.createItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}

}
