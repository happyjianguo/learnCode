package cn.yufu.posp.client.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.client.dao.hibernate.hql.OaClientComputerInfoDaoHibernateHQL;
import cn.yufu.posp.client.domain.model.OaClientComputerInfoModel;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public class OaClientComputerInfoLogic extends BaseLogic implements OaClientComputerInfoLogicInterface {

	private static final Log log = LogFactory.getLog("errctl");

	public OaClientComputerInfoLogic() {

	}

	/**
	 * �������м�¼
	 */
	public PageInfoModel queryAll(OaClientComputerInfoModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("OaClientComputerInfoLogic.queryAll()��ʼ���ã���ҳ��ѯ��" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			OaClientComputerInfoDaoHibernateHQL dao = (OaClientComputerInfoDaoHibernateHQL) getBean("OaClientComputerInfoDao");

			// �õ���¼��������
			totalCount = dao.querySum(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("OaClientComputerInfoLogic.queryAll()�������ã���ҳ��ѯ��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoLogic.queryAll()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	/**
	 * ɾ��һ����¼
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("OaClientComputerInfoLogic.deleteItem(List newKeys, UserData ud)��ʼ���ã�ɾ��һ����¼��" + ud.getUserId());

			OaClientComputerInfoDaoHibernateHQL dao = (OaClientComputerInfoDaoHibernateHQL) getBean("OaClientComputerInfoDao");

			dao.deleteItem(newKeys, ud);

			log.info("OaClientComputerInfoLogic.deleteItem(List newKeys, UserData ud)�������ã�ɾ��һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoLogic.deleteItem(List newKeys, UserData ud)ɾ��һ����¼,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	/**
	 * �½�һ����¼
	 */
	public void createItem(OaClientComputerInfoModel newModel, UserData ud) throws OAException {
		try {
			log.info("OaClientComputerInfoLogic.createItem(OaClientComputerInfoModel newModel, UserData ud)��ʼ���ã��½�һ����¼��" + ud.getUserId());

			OaClientComputerInfoDaoHibernateHQL dao = (OaClientComputerInfoDaoHibernateHQL) getBean("OaClientComputerInfoDao");
			dao.createItem(newModel, ud);

			log.info("OaClientComputerInfoLogic.createItem(OaClientComputerInfoModel newModel, UserData ud)�������ã��½�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoLogic.createItem(OaClientComputerInfoModel newModel, UserData ud)����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	/**
	 * ��ʾһ����¼
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		OaClientComputerInfoModel newModel = new OaClientComputerInfoModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("OaClientComputerInfoLogic.findItem(long key, UserData ud)��ʼ���ã���ʾһ����¼��" + ud.getUserId());

			OaClientComputerInfoDaoHibernateHQL dao = (OaClientComputerInfoDaoHibernateHQL) getBean("OaClientComputerInfoDao");
			newModel = dao.findItem(newKey, ud);

			// ��model
			parameteMap.put("Infolist", newModel);

			log.info("OaClientComputerInfoLogic.findItem(long key, UserData ud)�������ã���ʾһ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoLogic.findItem(long key, UserData ud)��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return parameteMap;
	}

	/**
	 * �޸�һ����¼
	 */
	public void saveItem(OaClientComputerInfoModel newModel, UserData ud) throws OAException {
		try {
			log.info("OaClientComputerInfoLogic.saveItem(OaClientComputerInfoModel newModel, UserData ud)��ʼ���ã��޸�һ����¼��" + ud.getUserId());
			OaClientComputerInfoDaoHibernateHQL dao = (OaClientComputerInfoDaoHibernateHQL) getBean("OaClientComputerInfoDao");
			dao.saveItem(newModel, ud);
			log.info("OaClientComputerInfoLogic.saveItem(OaClientComputerInfoModel newModel, UserData ud)�������ã��޸�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoLogic.saveItem(OaClientComputerInfoModel newModel, UserData ud)����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

}
