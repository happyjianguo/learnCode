package cn.yufu.posp.sysparam.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.sysparam.dao.hibernate.hql.TranModuleDaoHibernateHQLImpl;
import cn.yufu.posp.sysparam.domain.model.TranModuleInf;

public class TranModuleLogic extends BaseLogic implements TranModuleLogicInterface {

	private static final Log log = LogFactory.getLog("sysparam");

	public TranModuleLogic() {

	}

	/**
	 * �������м�¼
	 */
	public PageInfoModel queryAll(TranModuleInf queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("TranModuleLogic.queryAll()��ʼ���ã���ҳ��ѯ��" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			TranModuleDaoHibernateHQLImpl dao = (TranModuleDaoHibernateHQLImpl) getBean("TranModuleDao");

			// �õ���¼��������
			totalCount = dao.querySum(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("TranModuleLogic.queryAll()�������ã���ҳ��ѯ��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleLogic.queryAll()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return page;
	}

	/**
	 * ɾ��һ����¼
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("TranModuleLogic.deleteItem()��ʼ���ã�ɾ��һ����¼��" + ud.getUserId());

			TranModuleDaoHibernateHQLImpl dao = (TranModuleDaoHibernateHQLImpl) getBean("TranModuleDao");

			dao.deleteItem(newKeys, ud);

			log.info("TranModuleLogic.deleteItem()�������ã�ɾ��һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleLogic.deleteItem()ɾ��һ����¼,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * �½�һ����¼
	 */
	public void createItem(TranModuleInf newModel, UserData ud) throws OAException {
		try {
			log.info("TranModuleLogic.createItem()��ʼ���ã��½�һ����¼��" + ud.getUserId());

			TranModuleDaoHibernateHQLImpl dao = (TranModuleDaoHibernateHQLImpl) getBean("TranModuleDao");
			dao.createItem(newModel, ud);

			log.info("TranModuleLogic.createItem()�������ã��½�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleLogic.createItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * ��ʾһ����¼
	 */
	public HashMap findItem(TranModuleInf newKey, UserData ud) throws OAException {
		TranModuleInf newModel = new TranModuleInf();

		HashMap parameteMap = new HashMap();
		try {
			log.info("TranModuleLogic.findItem()��ʼ���ã���ʾһ����¼��" + ud.getUserId());

			TranModuleDaoHibernateHQLImpl dao = (TranModuleDaoHibernateHQLImpl) getBean("TranModuleDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {

				throw new OAException("��Ҫ���ҵ���Ϣ�����ڣ�");
			}
			// ��model
			parameteMap.put("Infolist", newModel);

			log.info("TranModuleLogic.findItem()�������ã���ʾһ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleLogic.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * �޸�һ����¼
	 */
	public void saveItem(TranModuleInf newModel, UserData ud) throws OAException {
		try {
			log.info("TranModuleLogic.saveItem()��ʼ���ã��޸�һ����¼��" + ud.getUserId());
			TranModuleDaoHibernateHQLImpl dao = (TranModuleDaoHibernateHQLImpl) getBean("TranModuleDao");
			dao.saveItem(newModel, ud);
			log.info("TranModuleLogic.saveItem()�������ã��޸�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleLogic.saveItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

}
