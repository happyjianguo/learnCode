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
import cn.yufu.posp.keyManager.dao.hibernate.hql.TMKMasterKEYDaoHibernateHQL;
import cn.yufu.posp.keyManager.domain.model.BtsKey;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;

public class TMKMasterKEYLogic extends BaseLogic implements TMKMasterKEYLogicInterface {

	private static final Log log = LogFactory.getLog("key");

	public PageInfoModel queryAllItem(BtsKey queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("TMKMasterKEYLogic.queryAllItem()��ʼ���ã���ҳ��ѯ��");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			TMKMasterKEYDaoHibernateHQL dao = (TMKMasterKEYDaoHibernateHQL) getBean("TMKMasterKEYDao");

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

			log.info("TMKMasterKEYLogic.queryAllItem()�������ã���ҳ��ѯ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TMKMasterKEYLogic.ueryAllItem()��ҳ��ѯ������ʱ�����쳣");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	public HashMap findItemByKey(BtsKey key, UserData ud) throws OAException {
		BtsKey newModel = new BtsKey();
		HashMap map = new HashMap();
		try {
			log.info("TMKMasterKEYLogic.findItemByKey()��ʼ���ã�����һ����Ϣ��");

			TMKMasterKEYDaoHibernateHQL dao = (TMKMasterKEYDaoHibernateHQL) getBean("TMKMasterKEYDao");
			newModel = dao.findItemByKey(key, ud);

			if (newModel != null) {
				EdcCommonDaoHibernateHQL commondao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
				newModel.setMerchantName(commondao.findMerchantNameByKey(newModel.getMerchantId(), ud));
			}
			map.put("Infolist", newModel);

			log.info("TMKMasterKEYLogic.findItemByKey()�������ã�����һ����Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TMKMasterKEYLogic.findItemByKey()����һ����Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	}

	public void saveItem(BtsKey btsKey, UserData ud) throws OAException {
		try {
			log.info("TMKMasterKEYLogic.saveItem()��ʼ���ã����� ���޸� ��Ϣ��");

			TMKMasterKEYDaoHibernateHQL dao = (TMKMasterKEYDaoHibernateHQL) getBean("TMKMasterKEYDao");

			dao.saveItem(btsKey, ud);

			log.info("TMKMasterKEYLogic.saveItem()�������ã����� ���޸� ��Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TMKMasterKEYLogic.saveItem()���� ���޸� ����ʱ�����쳣");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	public void createItem(BtsKey btsKey, UserData ud) throws OAException {
		try {
			log.info("TMKMasterKEYLogic.createItem()��ʼ���ã��½�һ����¼��" + ud.getUserId());

			TMKMasterKEYDaoHibernateHQL dao = (TMKMasterKEYDaoHibernateHQL) getBean("TMKMasterKEYDao");
			dao.createItem(btsKey, ud);

			log.info("TMKMasterKEYLogic.createItem()�������ã��½�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TMKMasterKEYLogic.createItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}
	//����Excel��TxT�ļ��Ĺ�������
	public List queryExport(BtsKey queryModel, UserData ud) throws OAException {
		TMKMasterKEYDaoHibernateHQL dao = (TMKMasterKEYDaoHibernateHQL) getBean("TMKMasterKEYDao");
		return dao.queryExport(queryModel, ud);
	}
	
}
