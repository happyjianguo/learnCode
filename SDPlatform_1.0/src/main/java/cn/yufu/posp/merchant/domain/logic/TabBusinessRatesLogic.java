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
	 * ��ѯ���м�¼
	 */
	public PageInfoModel queryAll(TabBusinessRatesModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("TabBusinessRatesLogic.queryAll()��ʼ���ã���ҳ��ѯ��" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			TabBusinessRatesDaoHibernateHQL dao = (TabBusinessRatesDaoHibernateHQL) getBean("tabBusinessRatesDao");

			// �õ���¼��������
			totalCount = dao.querySum(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("TabBusinessRatesLogic.queryAll()�������ã���ҳ��ѯ��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesLogic.queryAll()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}
	
	/**
	 * ��ʾһ����¼
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		TabBusinessRatesModel newModel = new TabBusinessRatesModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("TabBusinessRatesLogic.findItem()��ʼ���ã���ʾһ����¼��" + ud.getUserId());

			TabBusinessRatesDaoHibernateHQL dao = (TabBusinessRatesDaoHibernateHQL) getBean("tabBusinessRatesDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("��Ҫ���ҵ���Ϣ�����ڣ�");
			}

			// ��model
			parameteMap.put("Infolist", newModel);

			log.info("TabBusinessRatesLogic.findItem()�������ã���ʾһ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesLogic.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * �޸�һ����¼
	 */
	public void saveItem(TabBusinessRatesModel newModel, UserData ud) throws OAException {
		try {
			log.info("TabBusinessRatesLogic.saveItem()��ʼ���ã��޸�һ����¼��" + ud.getUserId());
			TabBusinessRatesDaoHibernateHQL dao = (TabBusinessRatesDaoHibernateHQL) getBean("tabBusinessRatesDao");
			dao.saveItem(newModel, ud);
			log.info("TabBusinessRatesLogic.saveItem()�������ã��޸�һ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesLogic.saveItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * �½�һ����¼
	 */
	public void createItem(TabBusinessRatesModel newModel, UserData ud) throws OAException {
		try {
			log.info("TabBusinessRatesLogic.createItem()��ʼ���ã��½�һ����¼��" + ud.getUserId());
			TabBusinessRatesDaoHibernateHQL dao = (TabBusinessRatesDaoHibernateHQL) getBean("tabBusinessRatesDao");
			
			dao.createItem(newModel, ud);

			log.info("TabBusinessRatesLogic.createItem()�������ã��½�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesLogic.createItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}

	/**
	 * ��ѯҵ���¼
	 */
	public List<TabBusinessRatesModel> queryAllTabBusinessRates() throws OAException {
		try {
			log.info("TabBusinessRatesLogic.queryAllTabBusinessRates()��ʼ���ã���ѯҵ���¼��");
			TabBusinessRatesDaoHibernateHQL dao = (TabBusinessRatesDaoHibernateHQL) getBean("tabBusinessRatesDao");
			
			return dao.queryAllTabBusinessRates();

		} catch (Exception e) {
			log.error("�쳣", e);
		}
		
		return null;
	}
}
