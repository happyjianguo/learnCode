package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.dao.hibernate.hql.TabBusRoleMenuDaoHibernateHQL;
import cn.yufu.posp.merchant.dao.hibernate.hql.TabBusinessRatesDaoHibernateHQL;
import cn.yufu.posp.merchant.dao.hibernate.hql.TabBusinessRatesDaoHibernateHQL;
import cn.yufu.posp.merchant.domain.model.TabBusinessRatesModel;
import cn.yufu.posp.merchant.domain.model.MerchantUserRela;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalId;

public class TabBusinessRatesLogic extends BaseLogic implements TabBusinessRatesLogicInterface {

	private static final Log log = LogFactory.getLog("merchant");

	public TabBusinessRatesLogic() {

	}

	/**
	 * 查询所有记录
	 */
	public PageInfoModel queryAll(TabBusinessRatesModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("TabBusinessRatesLogic.queryAll()开始调用：分页查询。" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			TabBusinessRatesDaoHibernateHQL dao = (TabBusinessRatesDaoHibernateHQL) getBean("tabBusinessRatesDao");

			// 得到记录的总条数
			totalCount = dao.querySum(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("TabBusinessRatesLogic.queryAll()结束调用：分页查询。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesLogic.queryAll()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}
	
	/**
	 * 显示一条记录
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		TabBusinessRatesModel newModel = new TabBusinessRatesModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("TabBusinessRatesLogic.findItem()开始调用：显示一条记录。" + ud.getUserId());

			TabBusinessRatesDaoHibernateHQL dao = (TabBusinessRatesDaoHibernateHQL) getBean("tabBusinessRatesDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("您要查找的信息不存在！");
			}

			// 传model
			parameteMap.put("Infolist", newModel);

			log.info("TabBusinessRatesLogic.findItem()结束调用：显示一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesLogic.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * 修改一条记录
	 */
	public void saveItem(TabBusinessRatesModel newModel, UserData ud) throws OAException {
		try {
			log.info("TabBusinessRatesLogic.saveItem()开始调用：修改一条记录。" + ud.getUserId());
			TabBusinessRatesDaoHibernateHQL dao = (TabBusinessRatesDaoHibernateHQL) getBean("tabBusinessRatesDao");
			dao.saveItem(newModel, ud);
			log.info("TabBusinessRatesLogic.saveItem()结束调用：修改一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesLogic.saveItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * 新建一条记录
	 */
	public void createItem(TabBusinessRatesModel newModel, UserData ud) throws OAException {
		try {
			log.info("TabBusinessRatesLogic.createItem()开始调用：新建一条记录。" + ud.getUserId());
			TabBusinessRatesDaoHibernateHQL dao = (TabBusinessRatesDaoHibernateHQL) getBean("tabBusinessRatesDao");
			
			dao.createItem(newModel, ud);

			log.info("TabBusinessRatesLogic.createItem()结束调用：新建一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesLogic.createItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}

	/**
	 * 查询业务记录
	 */
	public List<TabBusinessRatesModel> queryAllTabBusinessRates() throws OAException {
		try {
			log.info("TabBusinessRatesLogic.queryAllTabBusinessRates()开始调用：查询业务记录。");
			TabBusinessRatesDaoHibernateHQL dao = (TabBusinessRatesDaoHibernateHQL) getBean("tabBusinessRatesDao");
			
			return dao.queryAllTabBusinessRates();

		} catch (Exception e) {
			log.error("异常", e);
		}
		
		return null;
	}
}
