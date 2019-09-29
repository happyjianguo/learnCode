package cn.yufu.posp.sysModule.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.sysModule.dao.hibernate.hql.SysModuleOtherDaoHibernateHQLImpl;
import cn.yufu.posp.sysModule.domain.model.SysModuleModel;

public class SysModuleOtherLogic extends BaseLogic implements SysModuleOtherLogicInterface {

	private static final Log log = LogFactory.getLog("sys");

	public SysModuleOtherLogic() {

	}

	/**
	 * �������м�¼
	 */
	public PageInfoModel queryAll(SysModuleModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("SysModuleOtherLogic.queryAll()��ʼ���ã���ҳ��ѯ��" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			SysModuleOtherDaoHibernateHQLImpl dao = (SysModuleOtherDaoHibernateHQLImpl) getBean("sysModuleOtherDao");

			// �õ���¼��������
			totalCount = dao.querySum(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("SysModuleOtherLogic.queryAll()�������ã���ҳ��ѯ��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleOtherLogic.queryAll()����ʱ�����쳣��");
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
			log.info("SysModuleOtherLogic.deleteItem()��ʼ���ã�ɾ��һ����¼��" + ud.getUserId());

			SysModuleOtherDaoHibernateHQLImpl dao = (SysModuleOtherDaoHibernateHQLImpl) getBean("sysModuleOtherDao");

			dao.deleteItem(newKeys, ud);

			log.info("SysModuleOtherLogic.deleteItem()�������ã�ɾ��һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleOtherLogic.deleteItem()ɾ��һ����¼,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * �½�һ����¼
	 */
	public void createItem(SysModuleModel newModel, UserData ud) throws OAException {
		try {
			log.info("SysModuleOtherLogic.createItem()��ʼ���ã��½�һ����¼��" + ud.getUserId());

			SysModuleOtherDaoHibernateHQLImpl dao = (SysModuleOtherDaoHibernateHQLImpl) getBean("sysModuleOtherDao");
			dao.createItem(newModel, ud);

			log.info("SysModuleOtherLogic.createItem()�������ã��½�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleOtherLogic.createItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * ��ʾһ����¼
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		SysModuleModel newModel = new SysModuleModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("SysModuleOtherLogic.findItem()��ʼ���ã���ʾһ����¼��" + ud.getUserId());

			SysModuleOtherDaoHibernateHQLImpl dao = (SysModuleOtherDaoHibernateHQLImpl) getBean("sysModuleOtherDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {

				throw new OAException("��Ҫ���ҵ���Ϣ�����ڣ�");
			}
			// ��model
			parameteMap.put("Infolist", newModel);

			log.info("SysModuleOtherLogic.findItem()�������ã���ʾһ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleOtherLogic.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * �޸�һ����¼
	 */
	public void saveItem(SysModuleModel newModel, UserData ud) throws OAException {
		try {
			log.info("SysModuleOtherLogic.saveItem()��ʼ���ã��޸�һ����¼��" + ud.getUserId());
			SysModuleOtherDaoHibernateHQLImpl dao = (SysModuleOtherDaoHibernateHQLImpl) getBean("sysModuleOtherDao");
			dao.saveItem(newModel, ud);
			log.info("SysModuleOtherLogic.saveItem()�������ã��޸�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleOtherLogic.saveItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

}
