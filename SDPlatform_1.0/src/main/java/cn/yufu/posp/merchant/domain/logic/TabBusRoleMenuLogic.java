package cn.yufu.posp.merchant.domain.logic;

import java.util.ArrayList;
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
import cn.yufu.posp.merchant.dao.hibernate.hql.TabBusRoleMenuDaoHibernateHQL;
import cn.yufu.posp.merchant.domain.model.TabBusRoleMenuModel;
import cn.yufu.posp.merchant.domain.model.MerchantUserRela;
import cn.yufu.posp.merchant.domain.model.TabBusinessRatesModel;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcTerminalDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.EdcNewfkterminalOrm;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalId;

public class TabBusRoleMenuLogic extends BaseLogic implements TabBusRoleMenuLogicInterface {

	private static final Log log = LogFactory.getLog("merchant");

	public TabBusRoleMenuLogic() {

	}

	/**
	 * ��ѯ���м�¼
	 */
	public PageInfoModel queryAll(TabBusRoleMenuModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("TabBusRoleMenuLogic.queryAll()��ʼ���ã���ҳ��ѯ��" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			TabBusRoleMenuDaoHibernateHQL dao = (TabBusRoleMenuDaoHibernateHQL) getBean("tabBusRoleMenuDao");

			// �õ���¼��������
			totalCount = dao.querySum(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);
			
			
			
			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("TabBusRoleMenuLogic.queryAll()�������ã���ҳ��ѯ��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuLogic.queryAll()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	

	/**
	 * ��ʾһ����¼
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		TabBusRoleMenuModel newModel = new TabBusRoleMenuModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("TabBusRoleMenuLogic.findItem()��ʼ���ã���ʾһ����¼��" + ud.getUserId());

			TabBusRoleMenuDaoHibernateHQL dao = (TabBusRoleMenuDaoHibernateHQL) getBean("tabBusRoleMenuDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("��Ҫ���ҵ���Ϣ�����ڣ�");
			}

			// ��model
			parameteMap.put("Infolist", newModel);

			log.info("TabBusRoleMenuLogic.findItem()�������ã���ʾһ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuLogic.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * �޸�һ����¼
	 */
	public void saveItem(TabBusRoleMenuModel newModel, UserData ud) throws OAException {
		try {
			log.info("TabBusRoleMenuLogic.saveItem()��ʼ���ã��޸�һ����¼��" + ud.getUserId());
			TabBusRoleMenuDaoHibernateHQL dao = (TabBusRoleMenuDaoHibernateHQL) getBean("tabBusRoleMenuDao");
			dao.saveItem(newModel, ud);
			log.info("TabBusRoleMenuLogic.saveItem()�������ã��޸�һ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuLogic.saveItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * �½�һ����¼
	 */
	public void createItem(TabBusRoleMenuModel newModel, UserData ud) throws OAException {
		try {
			log.info("TabBusRoleMenuLogic.createItem()��ʼ���ã��½�һ����¼��" + ud.getUserId());
			TabBusRoleMenuDaoHibernateHQL dao = (TabBusRoleMenuDaoHibernateHQL) getBean("tabBusRoleMenuDao");
			
			dao.createItem(newModel, ud);

			log.info("TabBusRoleMenuLogic.createItem()�������ã��½�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuLogic.createItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}

	//��ȡҵ���ɫ�˵��б�
	public List<TabBusRoleMenuModel> findBusRoleList(UserData ud) throws OAException{
		TabBusRoleMenuDaoHibernateHQL dao = (TabBusRoleMenuDaoHibernateHQL) getBean("tabBusRoleMenuDao");
		return dao.findBusRoleList(ud);
	}
	
	//��֤ҵ���ɫ�������Ƶ�Ψһ��
	public List<TabBusRoleMenuModel> findBusRoleNameKey(TabBusRoleMenuModel key, UserData ud) throws OAException {
		List<TabBusRoleMenuModel> list = new ArrayList<TabBusRoleMenuModel>();
		try {

			TabBusRoleMenuDaoHibernateHQL dao = (TabBusRoleMenuDaoHibernateHQL) getBean("tabBusRoleMenuDao");
			
			list = dao.findBusRoleNameKey(key, ud);
		} catch (Exception e) {
			log.info("��֤��֤ҵ���ɫ�������Ƶ�Ψһ��,�����쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return list;
	}	
	

	
	
}
