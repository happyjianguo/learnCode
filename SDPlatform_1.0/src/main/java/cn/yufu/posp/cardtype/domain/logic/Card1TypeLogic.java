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
	 * 查找卡类型信息
	 */
	public PageInfoModel queryCardType(Cardtype1 queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("Card1TypeLogic.queryCardType()开始调用：分页查询。");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			Card1TypeDaoHibernateHQL jgDao = (Card1TypeDaoHibernateHQL) getBean("card1TypeDao");

			// 得到记录的总条数
			totalCount = jgDao.queryCardTypeCount(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = jgDao.queryCardType(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			// System.out.println(list.size() +
			// "================rtertwetert=============" + totalCount);
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("Card1TypeLogic.queryCardType()结束调用：分页查询。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("Card1TypeLogic.queryCardType()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return page;
	}

	/**
	 * 删除卡类型信息
	 */
	public void deleteCardType(Cardtype1 newKeys, UserData ud) throws OAException {
		try {
			log.info("Card1TypeLogic.deleteCardType()开始调用：删除机构信息。");

			Card1TypeDaoHibernateHQL jgDao = (Card1TypeDaoHibernateHQL) getBean("card1TypeDao");

			jgDao.deleteCardType(newKeys, ud);

			log.info("Card1TypeLogic.deleteCardType()结束调用：删除机构信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeLogic.deleteCardType()删除机构信息,调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * 新建卡类型信息
	 */
	public void createCardType(Cardtype1 newModel, UserData ud) throws OAException {
		try {
			log.info("Card1TypeLogic.createCardType()开始调用：新建机构信息。");

			Card1TypeDaoHibernateHQL jgDao = (Card1TypeDaoHibernateHQL) getBean("card1TypeDao");
			jgDao.createCardType(newModel, ud);

			log.info("Card1TypeLogic.createCardType()结束调用：新建机构信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeLogic.createCardType()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * 按key查卡类型信息
	 */
	public HashMap queryCardTypeByKey(String newKey, UserData ud) throws OAException {
		Cardtype1 newModel = new Cardtype1();

		HashMap parameteMap = new HashMap();
		try {
			log.info("Card1TypeLogic.queryCardTypeByKey()开始调用：查机构信息。");

			Card1TypeDaoHibernateHQL jgDao = (Card1TypeDaoHibernateHQL) getBean("card1TypeDao");
			newModel = jgDao.queryCardTypeByKey(newKey, ud);

			// 传model
			parameteMap.put("Infolist", newModel);
			log.info("Card1TypeLogic.queryCardTypeByKey()结束调用：查机构信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeLogic.queryCardTypeByKey()查机构信息，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * 修改卡类型信息
	 */
	public void saveCardType(Cardtype1 newModel, UserData ud) throws OAException {
		try {
			log.info("Card1TypeLogic.saveCardType()开始调用：保存卡类信息。");
			Card1TypeDaoHibernateHQL jgDao = (Card1TypeDaoHibernateHQL) getBean("card1TypeDao");
			jgDao.saveCardType(newModel, ud);
			log.info("Card1TypeLogic.saveCardType()结束调用：保存卡类信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeLogic.saveCardType()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

}
