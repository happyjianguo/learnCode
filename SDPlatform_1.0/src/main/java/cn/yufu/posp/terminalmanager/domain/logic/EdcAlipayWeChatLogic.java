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
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcAlipayWeChatDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcNewfkterminalOrmDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.EdcAlipayWeChat;
import cn.yufu.posp.terminalmanager.domain.model.EdcNewfkterminalOrm;

public class EdcAlipayWeChatLogic extends BaseLogic implements
		EdcAlipayWeChatLogicInterface {

	private static final Log log = LogFactory.getLog("edcTerminal");

	/**
	 * @roseuid 44BAF7190128
	 */
	public EdcAlipayWeChatLogic() {

	}

	@SuppressWarnings("unchecked")
	public PageInfoModel queryAllItem(EdcAlipayWeChat edcAlipayWeChat,
			PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("EdcAlipayWeChatLogic.queryAllItem()��ʼ���ã���ҳ��ѯ��");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			EdcAlipayWeChatDaoHibernateHQL dao = (EdcAlipayWeChatDaoHibernateHQL) getBean("EdcAlipayWeChatDao");

			// �õ���¼��������
			totalCount = dao.queryCount(edcAlipayWeChat, ud);
			// ��ֹ���һҳû�ж���
			currentPage = pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(edcAlipayWeChat, startIndex, pageSize,
					page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("���ؽ��������==" + list.size() + "��ʼλ����==" + startIndex
					+ "ҳ����==" + (startIndex + pageSize) / pageSize + "�ܼ�¼��=="
					+ totalCount);

			log.info("EdcAlipayWeChatLogic.queryAllItem()�������ã���ҳ��ѯ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcAlipayWeChatLogic.queryAllItem()��ҳ��ѯ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	public void createItem(EdcAlipayWeChat edcAlipayWeChat, UserData ud)
			throws OAException {
		try {
			log.info("EdcAlipayWeChatLogic.createItem()��ʼ���ã��½��ն�������Ϣ��");

			EdcAlipayWeChatDaoHibernateHQL dao = (EdcAlipayWeChatDaoHibernateHQL) getBean("EdcAlipayWeChatDao");
			dao.createItem(edcAlipayWeChat, ud);

			log.info("EdcAlipayWeChatLogic.createItem()�������ã��½��ն�������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcAlipayWeChatLogic.createItem()�½��ն�������Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcAlipayWeChatLogic.deleteItem()��ʼ���ã�ɾ���ն�������Ϣ��");

			// ɾ���ն������趨
			EdcAlipayWeChatDaoHibernateHQL dao = (EdcAlipayWeChatDaoHibernateHQL) getBean("EdcAlipayWeChatDao");
			dao.deleteItem(keys, ud);

			log.info("EdcAlipayWeChatLogic.deleteItem()�������ã�ɾ���ն�������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcAlipayWeChatLogic.deleteItem()ɾ���ն�������Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public HashMap findItemByKey(EdcAlipayWeChat key, UserData ud)
			throws OAException {
		EdcAlipayWeChat newModel = new EdcAlipayWeChat();
		HashMap map = new HashMap();
		try {
			log.info("EdcAlipayWeChatLogic.findItemByKey()��ʼ���ã�����һ����Ϣ��");

			EdcAlipayWeChatDaoHibernateHQL dao = (EdcAlipayWeChatDaoHibernateHQL) getBean("EdcAlipayWeChatDao");
			newModel = dao.findItemByKey(key, ud);

			/*
			 * if (newModel != null) { EdcCommonDaoHibernateHQL commondao =
			 * (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			 * newModel.setMerchanName
			 * (commondao.findMerchantNameByKey(newModel.getMerchantId(), ud));
			 * }
			 */
			map.put("Infolist", newModel);

			log.info("EdcAlipayWeChatLogic.findItemByKey()�������ã�����һ����Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcAlipayWeChatLogic.findItemByKey()����һ����Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	}

	public void saveItem(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud)
			throws OAException {
		try {
			log.info("EdcAlipayWeChatLogic.saveItem()��ʼ���ã����� ���޸� ��Ϣ��");

			EdcNewfkterminalOrmDaoHibernateHQL dao = (EdcNewfkterminalOrmDaoHibernateHQL) getBean("EdcNewfkterminalOrmDao");

			dao.saveItem(edcNewfkterminalOrm, ud);

			log
					.info("EdcAlipayWeChatLogic.EdcNewfkterminalOrmLogic.saveItem()�������ã����� ���޸� ��Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcAlipayWeChatLogic.saveItem()���� ���޸� ����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	public void saveUploadItem(List<EdcAlipayWeChat> edcAlipayWeChatList,
			UserData ud) throws OAException {
		try {
			EdcAlipayWeChatDaoHibernateHQL dao = (EdcAlipayWeChatDaoHibernateHQL) getBean("EdcAlipayWeChatDao");
			for (int i = 0; i < edcAlipayWeChatList.size(); i++) {
				// �����µ���Ϣ
				dao.createItem(edcAlipayWeChatList.get(i), ud);
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("EdcAlipayWeChatLogic.saveUploadItem()�����ն���Ϣ��ͬ���ն�����Կ��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}

}
