package cn.yufu.posp.terminalmanager.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcNewfkterminalOrmDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.EdcNewfkterminalOrm;

public class EdcNewfkterminalOrmLogic extends BaseLogic implements EdcNewfkterminalOrmLogicInterface {

	private static final Log log = LogFactory.getLog("edcTerminal");

	/**
	 * @roseuid 44BAF7190128
	 */
	public EdcNewfkterminalOrmLogic() {

	}

	public void createItem(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcNewfkterminalOrmLogic.createItem()��ʼ���ã��½��ն�������Ϣ��");

			EdcNewfkterminalOrmDaoHibernateHQL dao = (EdcNewfkterminalOrmDaoHibernateHQL) getBean("EdcNewfkterminalOrmDao");
			dao.createItem(edcNewfkterminalOrm, ud);

			log.info("EdcNewfkterminalOrmLogic.createItem()�������ã��½��ն�������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcNewfkterminalOrmLogic.createItem()�½��ն�������Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcNewfkterminalOrmLogic.deleteItem()��ʼ���ã�ɾ���ն�������Ϣ��");

			// ɾ���ն������趨
			EdcNewfkterminalOrmDaoHibernateHQL dao = (EdcNewfkterminalOrmDaoHibernateHQL) getBean("EdcNewfkterminalOrmDao");
			dao.deleteItem(keys, ud);

			log.info("EdcNewfkterminalOrmLogic.deleteItem()�������ã�ɾ���ն�������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcNewfkterminalOrmLogic.deleteItem()ɾ���ն�������Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public HashMap findItemByKey(EdcNewfkterminalOrm key, UserData ud) throws OAException {
		EdcNewfkterminalOrm newModel = new EdcNewfkterminalOrm();
		HashMap map = new HashMap();
		try {
			log.info("EdcNewfkterminalOrmLogic.findItemByKey()��ʼ���ã�����һ����Ϣ��");

			EdcNewfkterminalOrmDaoHibernateHQL dao = (EdcNewfkterminalOrmDaoHibernateHQL) getBean("EdcNewfkterminalOrmDao");
			newModel = dao.findItemByKey(key, ud);

			if (newModel != null) {
				EdcCommonDaoHibernateHQL commondao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
				newModel.setMerchanName(commondao.findMerchantNameByKey(newModel.getMerchantId(), ud));
			}
			map.put("Infolist", newModel);

			log.info("EdcNewfkterminalOrmLogic.findItemByKey()�������ã�����һ����Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcNewfkterminalOrmLogic.findItemByKey()����һ����Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public PageInfoModel queryAllItem(EdcNewfkterminalOrm edcNewfkterminalOrm, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("EdcNewfkterminalOrmLogic.queryAllItem()��ʼ���ã���ҳ��ѯ��");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			EdcNewfkterminalOrmDaoHibernateHQL dao = (EdcNewfkterminalOrmDaoHibernateHQL) getBean("EdcNewfkterminalOrmDao");

			// �õ���¼��������
			totalCount = dao.queryCount(edcNewfkterminalOrm, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(edcNewfkterminalOrm, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("���ؽ��������==" + list.size() + "��ʼλ����==" + startIndex + "ҳ����==" + (startIndex + pageSize) / pageSize + "�ܼ�¼��==" + totalCount);

			log.info("EdcNewfkterminalOrmLogic.queryAllItem()�������ã���ҳ��ѯ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcNewfkterminalOrmLogic.ueryAllItem()��ҳ��ѯ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	public void saveItem(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcNewfkterminalOrmLogic.saveItem()��ʼ���ã����� ���޸� ��Ϣ��");

			EdcNewfkterminalOrmDaoHibernateHQL dao = (EdcNewfkterminalOrmDaoHibernateHQL) getBean("EdcNewfkterminalOrmDao");

			dao.saveItem(edcNewfkterminalOrm, ud);

			log.info("EdcNewfkterminalOrmLogic.EdcNewfkterminalOrmLogic.saveItem()�������ã����� ���޸� ��Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcNewfkterminalOrmLogic.saveItem()���� ���޸� ����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	public void saveUploadItem(List<EdcNewfkterminalOrm> edcNewfkterminalOrmList, UserData ud) throws OAException {
		try {
			EdcNewfkterminalOrmDaoHibernateHQL dao = (EdcNewfkterminalOrmDaoHibernateHQL) getBean("EdcNewfkterminalOrmDao");
			for (int i = 0; i < edcNewfkterminalOrmList.size(); i++) {
				// �����µ���Ϣ
				dao.createItem(edcNewfkterminalOrmList.get(i), ud);
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("EdcNewfkterminalOrmLogic.saveUploadItem()�����ն���Ϣ��ͬ���ն�����Կ��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}

}
