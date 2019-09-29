package cn.yufu.posp.terminalmanager.domain.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCardDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.terminalmanager.domain.model.Cardtype;
import cn.yufu.posp.terminalmanager.domain.model.EdcCard;

public class EdcCardLogic extends BaseLogic implements EdcCardLogicInterface {

	private static final Log log = LogFactory.getLog("edcCard");

	/**
	 * @roseuid 44BAF7190128
	 */
	public EdcCardLogic() {

	}

	public void createItem(EdcCard edcCard, UserData ud) throws OAException {
		try {
			log.info("EdcCardLogic.createItem()��ʼ���ã��½��ն���������Ϣ��");

			EdcCardDaoHibernateHQL dao = (EdcCardDaoHibernateHQL) getBean("EdcCardDao");
			dao.createItem(edcCard, ud);

			log.info("EdcCardLogic.createItem()�������ã��½��ն���������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardLogic.createItem()�½��ն���������Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcCardLogic.deleteItem()��ʼ���ã�ɾ���ն���������Ϣ��");

			EdcCardDaoHibernateHQL dao = (EdcCardDaoHibernateHQL) getBean("EdcCardDao");
			dao.deleteItem(keys, ud);

			log.info("EdcCardLogic.deleteItem()�������ã�ɾ���ն���������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardLogic.deleteItem()ɾ���ն���������Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public HashMap findItemByKey(EdcCard key, UserData ud) throws OAException {
		EdcCard newModel = new EdcCard();
		HashMap map = new HashMap();
		try {
			log.info("EdcCardLogic.findItemByKey()��ʼ���ã�����һ���ն���������Ϣ��");

			EdcCardDaoHibernateHQL dao = (EdcCardDaoHibernateHQL) getBean("EdcCardDao");
			newModel = dao.findItemByKey(key, ud);

			if (newModel != null) {
				EdcCommonDaoHibernateHQL commondao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");

				List<Banktype> banktypeList = commondao.queryAllBanktypeItem(null, ud);
				for (Iterator iterator = banktypeList.iterator(); iterator.hasNext();) {
					Banktype banktype = (Banktype) iterator.next();
					if (banktype.getBankType().trim().equals(newModel.getId().getBankType().trim()))
						newModel.getId().setBankTypeName(banktype.getTypeName().trim());
				}

				List<Cardtype> cardtypeList = commondao.queryAllCardtypeItem(null, ud);
				for (Iterator iterator = cardtypeList.iterator(); iterator.hasNext();) {
					Cardtype cardtype = (Cardtype) iterator.next();
					if (cardtype.getId().getCardType().trim().equals(newModel.getId().getCardType()))
						newModel.getId().setCardTypeName(cardtype.getId().getTypeName());
				}
				newModel.getId().setMerchantName(commondao.findMerchantNameByKey(newModel.getId().getMerchantId(), ud));
			}
			map.put("Infolist", newModel);

			log.info("EdcCardLogic.findItemByKey()�������ã�����һ���ն���������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardLogic.findItemByKey()����һ���ն���������Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public PageInfoModel queryAllItem(EdcCard edcCard, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("EdcCardLogic.queryAllItem()��ʼ���ã���ҳ��ѯ��");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			EdcCardDaoHibernateHQL dao = (EdcCardDaoHibernateHQL) getBean("EdcCardDao");

			// �õ���¼��������
			totalCount = dao.queryCount(edcCard, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(edcCard, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			EdcCommonDaoHibernateHQL commondao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			List<EdcCard> edcCardList = new ArrayList<EdcCard>();

			List<Banktype> banktypeList = commondao.queryAllBanktypeItem(null, ud);
			List<Cardtype> cardtypeList = commondao.queryAllCardtypeItem(null, ud);
			for (int i = 0; i < list.size(); i++) {
				EdcCard newModel = (EdcCard) list.get(i);

				for (int k = 0; k < banktypeList.size(); k++) {
					Banktype banktype = (Banktype) banktypeList.get(k);
					if (banktype.getBankType().trim().equals(newModel.getId().getBankType().trim()))
						newModel.getId().setBankTypeName(banktype.getTypeName().trim());
				}

				for (int j = 0; j < cardtypeList.size(); j++) {
					Cardtype cardtype = (Cardtype) cardtypeList.get(j);
					if (cardtype.getId().getCardType().trim().equals(newModel.getId().getCardType()))
						newModel.getId().setCardTypeName(cardtype.getId().getTypeName());
				}

				edcCardList.add(newModel);
			}

			page.getResultItems().addAll(edcCardList);
			page.setTotalCount(totalCount);

			log.info("�ն������� ���ؽ��������==" + list.size() + " ��ʼλ����==" + startIndex + " ҳ����==" + (startIndex + pageSize) / pageSize + " �ܼ�¼��==" + totalCount);
			log.info("EdcCardLogic.queryAllItem()�������ã���ҳ��ѯ��=");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardLogic.queryAllItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	public void saveItem(EdcCard edcCard, UserData ud) throws OAException {
		try {
			log.info("EdcCardLogic.saveItem()��ʼ���ã����� ���޸� �ն���������Ϣ��");

			EdcCardDaoHibernateHQL dao = (EdcCardDaoHibernateHQL) getBean("EdcCardDao");
			dao.saveItem(edcCard, ud);

			log.info("EdcCardLogic.saveItem()�������ã����� ���޸� �ն���������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardLogic.saveItem()���� ���޸� ����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

}
