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
			log.info("CardAcctLogic.createItem()开始调用：新建一条卡账户信息。");

			CardAcctDaoHibernateHQL dao = (CardAcctDaoHibernateHQL) getBean("CardAcctDao");
			dao.createItem(cardAcct, ud);

			log.info("CardAcctLogic.createItem()结束调用：新建一条卡账户信息。");

		} catch (Exception e) {

			log.info("CardAcctLogic.createItem()新建一条卡账户信息，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("CardAcctLogic.deleteItem()开始调用：删除卡账户信息。");

			CardAcctDaoHibernateHQL dao = (CardAcctDaoHibernateHQL) getBean("CardAcctDao");
			dao.deleteItem(keys, ud);

			log.info("CardAcctLogic.deleteItem()结束调用：删除卡账户信息。");

		} catch (Exception e) {

			log.info("CardAcctLogic.deleteItem()删除卡账户信息，出现异常。");
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
			log.info("CardAcctLogic.findItemByKey()开始调用：查找一条卡账户信息。");

			CardAcctDaoHibernateHQL dao = (CardAcctDaoHibernateHQL) getBean("CardAcctDao");
			newModel = dao.findItemByKey(key, ud);

			map.put("Infolist", newModel);

			log.info("CardAcctLogic.findItemByKey()结束调用：查找一条卡账户信息。");

		} catch (Exception e) {

			log.info("CardAcctLogic.findItemByKey()查找一条卡账户信息，出现异常。");
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
			log.info("CardAcctLogic.queryAllItem()开始调用：分页查询卡账户。");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			CardAcctDaoHibernateHQL dao = (CardAcctDaoHibernateHQL) getBean("CardAcctDao");

			// 得到记录的总条数
			totalCount = dao.queryCount(cardAcct, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(cardAcct, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("卡账户管理信息返回结果集长度==" + list.size() + "起始位长度==" + startIndex + "页码数==" + (startIndex + pageSize) / pageSize + "总记录数==" + totalCount);
			log.info("CardAcctLogic.queryAllItem()结束调用：分页查询卡账户。");

		} catch (Exception e) {

			log.info("CardAcctLogic.queryAllItem()分页查询卡账户，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	public void saveItem(CardAcct cardAcct, UserData ud) throws OAException {
		try {
			log.info("CardAcctLogic.saveItem()开始调用：修改一条卡账户信息。");

			CardAcctDaoHibernateHQL dao = (CardAcctDaoHibernateHQL) getBean("CardAcctDao");
			dao.saveItem(cardAcct, ud);

			log.info("CardAcctLogic.saveItem()结束调用：修改一条卡账户信息。");

		} catch (Exception e) {

			log.info("CardAcctLogic.saveItem()修改一条卡账户信息，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

}
