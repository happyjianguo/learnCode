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
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcBlackDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.EdcBlack;

public class EdcBlackLogic extends BaseLogic implements EdcBlackLogicInterface {

	private static final Log log = LogFactory.getLog("edcTerminal");

	/**
	 * @roseuid 44BAF7190128
	 */
	public EdcBlackLogic() {

	}

	public void createItem(EdcBlack edcTerminal, UserData ud) throws OAException {
		try {
			log.info("EdcBlackLogic.createItem()��ʼ���ã��½���Ϣ��");

			EdcBlackDaoHibernateHQL dao = (EdcBlackDaoHibernateHQL) getBean("edcBlackDao");
			dao.createItem(edcTerminal, ud);

			log.info("EdcBlackLogic.createItem()�������ã��½���Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcBlackLogic.createItem()�½���Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcBlackLogic.deleteItem()��ʼ���ã�ɾ����Ϣ��");

			// ɾ���ն������趨
			EdcBlackDaoHibernateHQL dao = (EdcBlackDaoHibernateHQL) getBean("edcBlackDao");
			dao.deleteItem(keys, ud);

			log.info("EdcBlackLogic.deleteItem()�������ã�ɾ����Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcBlackLogic.deleteItem()ɾ����Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public HashMap findItemByKey(EdcBlack key, UserData ud) throws OAException {
		EdcBlack newModel = new EdcBlack();
		HashMap map = new HashMap();
		try {
			log.info("EdcBlackLogic.findItemByKey()��ʼ���ã�����һ����Ϣ��");

			EdcBlackDaoHibernateHQL dao = (EdcBlackDaoHibernateHQL) getBean("edcBlackDao");
			newModel = dao.findItemByKey(key, ud);

			if (newModel != null) {
				EdcCommonDaoHibernateHQL commondao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
				newModel.setMerchantName(commondao.findMerchantNameByKey(newModel.getMerchantId(), ud));
			}
			map.put("Infolist", newModel);

			log.info("EdcBlackLogic.findItemByKey()�������ã�����һ����Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcBlackLogic.findItemByKey()����һ����Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public PageInfoModel queryAllItem(EdcBlack edcTerminal, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("EdcBlackLogic.queryAllItem()��ʼ���ã���ҳ��ѯ��");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			EdcBlackDaoHibernateHQL dao = (EdcBlackDaoHibernateHQL) getBean("edcBlackDao");

			// �õ���¼��������
			totalCount = dao.queryCount(edcTerminal, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(edcTerminal, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("���ؽ��������==" + list.size() + "��ʼλ����==" + startIndex + "ҳ����==" + (startIndex + pageSize) / pageSize + "�ܼ�¼��==" + totalCount + "");

			log.info("EdcBlackLogic.queryAllItem()�������ã���ҳ��ѯ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcBlackLogic.ueryAllItem()��ҳ��ѯ������ʱ�����쳣");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	public void saveItem(EdcBlack edcTerminal, UserData ud) throws OAException {
		try {
			log.info("EdcBlackLogic.saveItem()��ʼ���ã����� ���޸� ��Ϣ��");

			EdcBlackDaoHibernateHQL dao = (EdcBlackDaoHibernateHQL) getBean("edcBlackDao");

			dao.saveItem(edcTerminal, ud);

			log.info("EdcBlackLogic.EdcBlackLogic.saveItem()�������ã����� ���޸� ��Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcBlackLogic.saveItem()���� ���޸� ����ʱ�����쳣");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

}
