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
	 * 查找信息
	 */
	public PageInfoModel queryCardType(CardType queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("CardTypeLogic.queryCardType()开始调用：分页查询。");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			CardTypeDaoHibernateHQL jgDao = (CardTypeDaoHibernateHQL) getBean("cardTypeDao");

			// 得到记录的总条数
			totalCount = jgDao.queryCardTypeCount(queryModel, ud);
			// 防止最后一页没有东西
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

			log.info("CardTypeLogic.queryCardType()结束调用：分页查询。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeLogic.queryCardType()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	/**
	 * 删除信息
	 */
	public void deleteCardType(CardType newKeys, UserData ud) throws OAException {
		try {
			log.info("CardTypeLogic.deleteJg()开始调用：删除信息。");

			CardTypeDaoHibernateHQL jgDao = (CardTypeDaoHibernateHQL) getBean("cardTypeDao");

			jgDao.deleteCardType(newKeys, ud);
			if ("YES".equals(CommonDomain.CONST_CHECK_SYNCDATA)) {
				ParamService service = (ParamService) getBean("syncParam");
				List<CardType> list = new ArrayList<CardType>();
				list.add(newKeys);
				String result = service.syncCardType(CardTypeUtil.cardTypeToJson(list, 2));
				JSONObject ret = JSONObject.fromObject(result);
				if ("0".equals(ret.get("code"))) {
					// 同步成功
					log.info("MerchantLogic.saveItem()同步修改商户信息数据成功。" + ud.getUserId());
				} else {
					throw new OAException(ret.getString("msg"));
				}
			}
			log.info("CardTypeLogic.deleteJg()结束调用：删除信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeLogic.deleteJg()删除信息,调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * 新建信息
	 */
	public void createCardType(CardType newModel, UserData ud) throws OAException {
		try {
			log.info("CardTypeLogic.createCardType()开始调用：新建信息。");

			CardTypeDaoHibernateHQL jgDao = (CardTypeDaoHibernateHQL) getBean("cardTypeDao");
			jgDao.createCardType(newModel, ud);
			if ("YES".equals(CommonDomain.CONST_CHECK_SYNCDATA)) {
				ParamService service = (ParamService) getBean("syncParam");
				List<CardType> list = new ArrayList<CardType>();
				list.add(newModel);
				String result = service.syncCardType(CardTypeUtil.cardTypeToJson(list, 1));
				JSONObject ret = JSONObject.fromObject(result);
				if ("0".equals(ret.get("code"))) {
					// 同步成功
					log.info("MerchantLogic.saveItem()同步修改商户信息数据成功。" + ud.getUserId());
				} else {
					throw new OAException(ret.getString("msg"));
				}
			}
			log.info("CardTypeLogic.createCardType()结束调用：新建信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeLogic.createCardType()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * 按key查信息
	 */
	public HashMap queryCardTypeByKey(String newKey, UserData ud) throws OAException {
		CardType newModel = new CardType();

		HashMap parameteMap = new HashMap();
		try {
			log.info("CardTypeLogic.queryCardTypeByKey()开始调用：查信息。");

			CardTypeDaoHibernateHQL jgDao = (CardTypeDaoHibernateHQL) getBean("cardTypeDao");
			newModel = jgDao.queryCardTypeByKey(newKey, ud);

			// 传model
			parameteMap.put("Infolist", newModel);
			log.info("CardTypeLogic.queryCardTypeByKey()结束调用：查信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeLogic.queryCardTypeByKey()查信息，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return parameteMap;
	}

	/**
	 * 保存信息
	 */
	public void saveCardType(CardType newModel, UserData ud) throws OAException {
		try {
			log.info("CardTypeLogic.saveCardType()开始调用：保存信息。");
			CardTypeDaoHibernateHQL jgDao = (CardTypeDaoHibernateHQL) getBean("cardTypeDao");
			jgDao.saveCardType(newModel, ud);
			if ("YES".equals(CommonDomain.CONST_CHECK_SYNCDATA)) {
				ParamService service = (ParamService) getBean("syncParam");
				List<CardType> list = new ArrayList<CardType>();
				list.add(newModel);
				String result = service.syncCardType(CardTypeUtil.cardTypeToJson(list, 3));
				JSONObject ret = JSONObject.fromObject(result);
				if ("0".equals(ret.get("code"))) {
					// 同步成功
					log.info("MerchantLogic.saveItem()同步修改商户信息数据成功。" + ud.getUserId());
				} else {
					throw new OAException(ret.getString("msg"));
				}
			}
			log.info("CardTypeLogic.saveCardType()结束调用：保存信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeLogic.saveCardType()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

}
