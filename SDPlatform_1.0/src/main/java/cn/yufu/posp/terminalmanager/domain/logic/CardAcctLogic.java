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
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.CardAcctDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.CardAcct;

public class CardAcctLogic extends BaseLogic implements CardAcctLogicInterface {

	private static final Log log = LogFactory.getLog("cardAcct");

	/**
	 * @roseuid 44BAF7190128
	 */
	public CardAcctLogic() {

	}

	public void createItem(CardAcct cardAcct, UserData ud) throws OAException {
		try {
			log.info("CardAcctLogic.createItem()��ʼ���ã��½�һ�����˻���Ϣ��");

			CardAcctDaoHibernateHQL dao = (CardAcctDaoHibernateHQL) getBean("CardAcctDao");
			dao.createItem(cardAcct, ud);

			log.info("CardAcctLogic.createItem()�������ã��½�һ�����˻���Ϣ��");

		} catch (Exception e) {

			log.info("CardAcctLogic.createItem()�½�һ�����˻���Ϣ�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("CardAcctLogic.deleteItem()��ʼ���ã�ɾ�����˻���Ϣ��");

			CardAcctDaoHibernateHQL dao = (CardAcctDaoHibernateHQL) getBean("CardAcctDao");
			dao.deleteItem(keys, ud);

			log.info("CardAcctLogic.deleteItem()�������ã�ɾ�����˻���Ϣ��");

		} catch (Exception e) {

			log.info("CardAcctLogic.deleteItem()ɾ�����˻���Ϣ�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public HashMap findItemByKey(String key, UserData ud) throws OAException {
		CardAcct newModel = new CardAcct();
		HashMap map = new HashMap();
		try {
			log.info("CardAcctLogic.findItemByKey()��ʼ���ã�����һ�����˻���Ϣ��");

			CardAcctDaoHibernateHQL dao = (CardAcctDaoHibernateHQL) getBean("CardAcctDao");
			newModel = dao.findItemByKey(key, ud);

			map.put("Infolist", newModel);

			log.info("CardAcctLogic.findItemByKey()�������ã�����һ�����˻���Ϣ��");

		} catch (Exception e) {

			log.info("CardAcctLogic.findItemByKey()����һ�����˻���Ϣ�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public PageInfoModel queryAllItem(CardAcct cardAcct, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("CardAcctLogic.queryAllItem()��ʼ���ã���ҳ��ѯ���˻���");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			CardAcctDaoHibernateHQL dao = (CardAcctDaoHibernateHQL) getBean("CardAcctDao");

			// �õ���¼��������
			totalCount = dao.queryCount(cardAcct, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(cardAcct, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("���˻�������Ϣ���ؽ��������==" + list.size() + "��ʼλ����==" + startIndex + "ҳ����==" + (startIndex + pageSize) / pageSize + "�ܼ�¼��==" + totalCount);
			log.info("CardAcctLogic.queryAllItem()�������ã���ҳ��ѯ���˻���");

		} catch (Exception e) {

			log.info("CardAcctLogic.queryAllItem()��ҳ��ѯ���˻��������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	public void saveItem(CardAcct cardAcct, UserData ud) throws OAException {
		try {
			log.info("CardAcctLogic.saveItem()��ʼ���ã��޸�һ�����˻���Ϣ��");

			CardAcctDaoHibernateHQL dao = (CardAcctDaoHibernateHQL) getBean("CardAcctDao");
			dao.saveItem(cardAcct, ud);

			log.info("CardAcctLogic.saveItem()�������ã��޸�һ�����˻���Ϣ��");

		} catch (Exception e) {

			log.info("CardAcctLogic.saveItem()�޸�һ�����˻���Ϣ�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

}
