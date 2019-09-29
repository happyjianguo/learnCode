package cn.yufu.posp.cardtype.domain.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.core.common.util.CommonDomain;
import cn.yufu.posp.cardtype.dao.hibernate.hql.CardTypeDaoHibernateHQL;
import cn.yufu.posp.cardtype.domain.model.CardType;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.sysparam.domain.logic.ParamService;

public class CardTypeLogic extends BaseLogic implements CardTypeLogicInterface {

	private static final Log log = LogFactory.getLog("cardType");

	/**
	 * @roseuid 44BAF7190128
	 */
	public CardTypeLogic() {

	}

	/**
	 * ������Ϣ
	 */
	public PageInfoModel queryCardType(CardType queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("CardTypeLogic.queryCardType()��ʼ���ã���ҳ��ѯ��");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			CardTypeDaoHibernateHQL jgDao = (CardTypeDaoHibernateHQL) getBean("cardTypeDao");

			// �õ���¼��������
			totalCount = jgDao.queryCardTypeCount(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;
			// System.out.println("startIndexstartIndexstartIndex"+startIndex);
			// System.out.println("pageSizepageSizepageSize"+pageSize);
			// System.out.println("totalCounttotalCounttotalCount"+totalCount);
			List list = jgDao.queryCardType(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			// System.out.println("listlistlistlistlistlist"+list.s);
			// System.out.println(list.size() +
			// "================rtertwetert=============" + totalCount);
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("CardTypeLogic.queryCardType()�������ã���ҳ��ѯ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeLogic.queryCardType()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	/**
	 * ɾ����Ϣ
	 */
	public void deleteCardType(CardType newKeys, UserData ud) throws OAException {
		try {
			log.info("CardTypeLogic.deleteJg()��ʼ���ã�ɾ����Ϣ��");

			CardTypeDaoHibernateHQL jgDao = (CardTypeDaoHibernateHQL) getBean("cardTypeDao");

			jgDao.deleteCardType(newKeys, ud);
			if ("YES".equals(CommonDomain.CONST_CHECK_SYNCDATA)) {
				ParamService service = (ParamService) getBean("syncParam");
				List<CardType> list = new ArrayList<CardType>();
				list.add(newKeys);
				String result = service.syncCardType(CardTypeUtil.cardTypeToJson(list, 2));
				JSONObject ret = JSONObject.fromObject(result);
				if ("0".equals(ret.get("code"))) {
					// ͬ���ɹ�
					log.info("MerchantLogic.saveItem()ͬ���޸��̻���Ϣ���ݳɹ���" + ud.getUserId());
				} else {
					throw new OAException(ret.getString("msg"));
				}
			}
			log.info("CardTypeLogic.deleteJg()�������ã�ɾ����Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeLogic.deleteJg()ɾ����Ϣ,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * �½���Ϣ
	 */
	public void createCardType(CardType newModel, UserData ud) throws OAException {
		try {
			log.info("CardTypeLogic.createCardType()��ʼ���ã��½���Ϣ��");

			CardTypeDaoHibernateHQL jgDao = (CardTypeDaoHibernateHQL) getBean("cardTypeDao");
			jgDao.createCardType(newModel, ud);
			if ("YES".equals(CommonDomain.CONST_CHECK_SYNCDATA)) {
				ParamService service = (ParamService) getBean("syncParam");
				List<CardType> list = new ArrayList<CardType>();
				list.add(newModel);
				String result = service.syncCardType(CardTypeUtil.cardTypeToJson(list, 1));
				JSONObject ret = JSONObject.fromObject(result);
				if ("0".equals(ret.get("code"))) {
					// ͬ���ɹ�
					log.info("MerchantLogic.saveItem()ͬ���޸��̻���Ϣ���ݳɹ���" + ud.getUserId());
				} else {
					throw new OAException(ret.getString("msg"));
				}
			}
			log.info("CardTypeLogic.createCardType()�������ã��½���Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeLogic.createCardType()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * ��key����Ϣ
	 */
	public HashMap queryCardTypeByKey(String newKey, UserData ud) throws OAException {
		CardType newModel = new CardType();

		HashMap parameteMap = new HashMap();
		try {
			log.info("CardTypeLogic.queryCardTypeByKey()��ʼ���ã�����Ϣ��");

			CardTypeDaoHibernateHQL jgDao = (CardTypeDaoHibernateHQL) getBean("cardTypeDao");
			newModel = jgDao.queryCardTypeByKey(newKey, ud);

			// ��model
			parameteMap.put("Infolist", newModel);
			log.info("CardTypeLogic.queryCardTypeByKey()�������ã�����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeLogic.queryCardTypeByKey()����Ϣ�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return parameteMap;
	}

	/**
	 * ������Ϣ
	 */
	public void saveCardType(CardType newModel, UserData ud) throws OAException {
		try {
			log.info("CardTypeLogic.saveCardType()��ʼ���ã�������Ϣ��");
			CardTypeDaoHibernateHQL jgDao = (CardTypeDaoHibernateHQL) getBean("cardTypeDao");
			jgDao.saveCardType(newModel, ud);
			if ("YES".equals(CommonDomain.CONST_CHECK_SYNCDATA)) {
				ParamService service = (ParamService) getBean("syncParam");
				List<CardType> list = new ArrayList<CardType>();
				list.add(newModel);
				String result = service.syncCardType(CardTypeUtil.cardTypeToJson(list, 3));
				JSONObject ret = JSONObject.fromObject(result);
				if ("0".equals(ret.get("code"))) {
					// ͬ���ɹ�
					log.info("MerchantLogic.saveItem()ͬ���޸��̻���Ϣ���ݳɹ���" + ud.getUserId());
				} else {
					throw new OAException(ret.getString("msg"));
				}
			}
			log.info("CardTypeLogic.saveCardType()�������ã�������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeLogic.saveCardType()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

}
