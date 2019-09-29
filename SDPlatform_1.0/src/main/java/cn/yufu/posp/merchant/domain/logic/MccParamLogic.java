package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.dao.hibernate.hql.MccParamDaoHibernateHQL;
import cn.yufu.posp.merchant.domain.model.MccParamModel;

public class MccParamLogic extends BaseLogic implements MccParamLogicInterface {

	private static final Log log = LogFactory.getLog("merchant");

	public MccParamLogic() {

	}

	/**
	 * ��ѯ���м�¼
	 */
	public PageInfoModel queryAll(MccParamModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("MccParamLogic.queryAll()��ʼ���ã���ҳ��ѯ��" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			MccParamDaoHibernateHQL dao = (MccParamDaoHibernateHQL) getBean("mccDao");

			// �õ���¼��������
			totalCount = dao.querySum(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("MccParamLogic.queryAll()�������ã���ҳ��ѯ��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamLogic.queryAll()����ʱ�����쳣��");
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
			log.info("MccParamLogic.deleteItem(List newKeys, UserData ud)��ʼ���ã�ɾ��һ����¼��" + ud.getUserId());

			MccParamDaoHibernateHQL dao = (MccParamDaoHibernateHQL) getBean("mccDao");

			dao.deleteItem(newKeys, ud);

			log.info("MccParamLogic.deleteItem(List newKeys, UserData ud)�������ã�ɾ��һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamLogic.deleteItem(List newKeys, UserData ud)ɾ��һ����¼,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * ��ʾһ����¼
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		MccParamModel newModel = new MccParamModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("MccParamLogic.findItem(long key, UserData ud)��ʼ���ã���ʾһ����¼��" + ud.getUserId());

			MccParamDaoHibernateHQL dao = (MccParamDaoHibernateHQL) getBean("mccDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				//2015.11.11 zhoulong update 
				//throw new OAException("��Ҫ���ҵ���Ϣ�����ڣ�");
				newModel = new MccParamModel("", "", "");  //�̻����Ͳ�ѯ���������̻�������ʾ���ַ�����
			}

			// ��model
			parameteMap.put("Infolist", newModel);

			log.info("MccParamLogic.findItem(long key, UserData ud)�������ã���ʾһ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamLogic.findItem(long key, UserData ud)��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * �޸�һ����¼
	 */
	public void saveItem(MccParamModel newModel, UserData ud) throws OAException {
		try {
			log.info("MccParamLogic.saveItem(MccParamModel newModel, UserData ud)��ʼ���ã��޸�һ����¼��" + ud.getUserId());
			MccParamDaoHibernateHQL dao = (MccParamDaoHibernateHQL) getBean("mccDao");
			dao.saveItem(newModel, ud);
			log.info("MccParamLogic.saveItem(MccParamModel newModel, UserData ud)�������ã��޸�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamLogic.saveItem(MccParamModel newModel, UserData ud)����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * �½�һ����¼
	 */
	public void createItem(MccParamModel newModel, UserData ud) throws OAException {
		try {
			log.info("MccParamLogic.createItem(MccParamModel newModel, UserData ud)��ʼ���ã��½�һ����¼��" + ud.getUserId());

			MccParamDaoHibernateHQL dao = (MccParamDaoHibernateHQL) getBean("mccDao");
			dao.createItem(newModel, ud);

			log.info("MccParamLogic.createItem(MccParamModel newModel, UserData ud)�������ã��½�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamLogic.createItem(MccParamModel newModel, UserData ud)����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

}
