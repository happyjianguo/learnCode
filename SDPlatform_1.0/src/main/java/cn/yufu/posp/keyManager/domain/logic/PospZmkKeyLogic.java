package cn.yufu.posp.keyManager.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.keyManager.dao.hibernate.hql.PospZmkKeyDaoHibernateHQL;
import cn.yufu.posp.keyManager.domain.model.PospZmkKey;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;

public class PospZmkKeyLogic extends BaseLogic implements PospZmkKeyLogicInterface {

	private static final Log log = LogFactory.getLog("key");

	public PageInfoModel queryAllItem(PospZmkKey queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("PospZmkKeyLogic.queryAllItem()��ʼ���ã���ҳ��ѯ��");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			PospZmkKeyDaoHibernateHQL dao = (PospZmkKeyDaoHibernateHQL) getBean("PospZmkKeyDao");

			// �õ���¼��������
			totalCount = dao.queryCount(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("���ؽ��������==" + list.size() + "��ʼλ����==" + startIndex + "ҳ����==" + (startIndex + pageSize) / pageSize + "�ܼ�¼��==" + totalCount + "");

			log.info("PospZmkKeyLogic.queryAllItem()�������ã���ҳ��ѯ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("PospZmkKeyLogic.ueryAllItem()��ҳ��ѯ������ʱ�����쳣");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	public HashMap findItemByKey(PospZmkKey key, UserData ud) throws OAException {
		PospZmkKey newModel = new PospZmkKey();
		HashMap map = new HashMap();
		try {
			log.info("PospZmkKeyLogic.findItemByKey()��ʼ���ã�����һ����Ϣ��");

			PospZmkKeyDaoHibernateHQL dao = (PospZmkKeyDaoHibernateHQL) getBean("PospZmkKeyDao");
			newModel = dao.findItemByKey(key, ud);
			map.put("Infolist", newModel);
			log.info("PospZmkKeyLogic.findItemByKey()�������ã�����һ����Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("PospZmkKeyLogic.findItemByKey()����һ����Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	}

	public void saveItem(PospZmkKey pospZmkKey, UserData ud) throws OAException {
		try {
			log.info("PospZmkKeyLogic.saveItem()��ʼ���ã����� ���޸� ��Ϣ��");

			PospZmkKeyDaoHibernateHQL dao = (PospZmkKeyDaoHibernateHQL) getBean("PospZmkKeyDao");

			dao.saveItem(pospZmkKey, ud);

			log.info("PospZmkKeyLogic.saveItem()�������ã����� ���޸� ��Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("PospZmkKeyLogic.saveItem()���� ���޸� ����ʱ�����쳣");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	public void createItem(PospZmkKey pospZmkKey, UserData ud) throws OAException {
		try {
			log.info("PospZmkKeyLogic.createItem()��ʼ���ã��½�һ����¼��" + ud.getUserId());

			PospZmkKeyDaoHibernateHQL dao = (PospZmkKeyDaoHibernateHQL) getBean("PospZmkKeyDao");
			dao.createItem(pospZmkKey, ud);

			log.info("PospZmkKeyLogic.createItem()�������ã��½�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("PospZmkKeyLogic.createItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

}
