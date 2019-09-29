package cn.yufu.posp.cardtype.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.cardtype.dao.hibernate.hql.Card1TypeDaoHibernateHQL;
import cn.yufu.posp.cardtype.domain.model.Cardtype1;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public class Card1TypeLogic extends BaseLogic implements Card1TypeLogicInterface {

	private static final Log log = LogFactory.getLog("cardType");

	/**
	 * @roseuid 44BAF7190128
	 */
	public Card1TypeLogic() {

	}

	/**
	 * ���ҿ�������Ϣ
	 */
	public PageInfoModel queryCardType(Cardtype1 queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("Card1TypeLogic.queryCardType()��ʼ���ã���ҳ��ѯ��");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			Card1TypeDaoHibernateHQL jgDao = (Card1TypeDaoHibernateHQL) getBean("card1TypeDao");

			// �õ���¼��������
			totalCount = jgDao.queryCardTypeCount(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = jgDao.queryCardType(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			// System.out.println(list.size() +
			// "================rtertwetert=============" + totalCount);
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("Card1TypeLogic.queryCardType()�������ã���ҳ��ѯ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("Card1TypeLogic.queryCardType()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return page;
	}

	/**
	 * ɾ����������Ϣ
	 */
	public void deleteCardType(Cardtype1 newKeys, UserData ud) throws OAException {
		try {
			log.info("Card1TypeLogic.deleteCardType()��ʼ���ã�ɾ��������Ϣ��");

			Card1TypeDaoHibernateHQL jgDao = (Card1TypeDaoHibernateHQL) getBean("card1TypeDao");

			jgDao.deleteCardType(newKeys, ud);

			log.info("Card1TypeLogic.deleteCardType()�������ã�ɾ��������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeLogic.deleteCardType()ɾ��������Ϣ,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * �½���������Ϣ
	 */
	public void createCardType(Cardtype1 newModel, UserData ud) throws OAException {
		try {
			log.info("Card1TypeLogic.createCardType()��ʼ���ã��½�������Ϣ��");

			Card1TypeDaoHibernateHQL jgDao = (Card1TypeDaoHibernateHQL) getBean("card1TypeDao");
			jgDao.createCardType(newModel, ud);

			log.info("Card1TypeLogic.createCardType()�������ã��½�������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeLogic.createCardType()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * ��key�鿨������Ϣ
	 */
	public HashMap queryCardTypeByKey(String newKey, UserData ud) throws OAException {
		Cardtype1 newModel = new Cardtype1();

		HashMap parameteMap = new HashMap();
		try {
			log.info("Card1TypeLogic.queryCardTypeByKey()��ʼ���ã��������Ϣ��");

			Card1TypeDaoHibernateHQL jgDao = (Card1TypeDaoHibernateHQL) getBean("card1TypeDao");
			newModel = jgDao.queryCardTypeByKey(newKey, ud);

			// ��model
			parameteMap.put("Infolist", newModel);
			log.info("Card1TypeLogic.queryCardTypeByKey()�������ã��������Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeLogic.queryCardTypeByKey()�������Ϣ�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * �޸Ŀ�������Ϣ
	 */
	public void saveCardType(Cardtype1 newModel, UserData ud) throws OAException {
		try {
			log.info("Card1TypeLogic.saveCardType()��ʼ���ã����濨����Ϣ��");
			Card1TypeDaoHibernateHQL jgDao = (Card1TypeDaoHibernateHQL) getBean("card1TypeDao");
			jgDao.saveCardType(newModel, ud);
			log.info("Card1TypeLogic.saveCardType()�������ã����濨����Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeLogic.saveCardType()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

}
