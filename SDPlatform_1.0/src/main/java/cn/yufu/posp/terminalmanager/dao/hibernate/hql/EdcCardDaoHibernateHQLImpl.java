package cn.yufu.posp.terminalmanager.dao.hibernate.hql;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Order;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcCard;

public class EdcCardDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements EdcCardDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("edcCard");

	/**
	 * @roseuid 44BAF72B0232
	 */
	public EdcCardDaoHibernateHQLImpl() {
	}

	public void createItem(EdcCard edcCard, UserData ud) throws OAException {
		try {
			log.info("EdcCardDaoHibernateHQLImpl.createItem()开始调用：新增一条受理卡类信息。");

			save(edcCard, ud);

			log.info("EdcCardDaoHibernateHQLImpl.createItem()结束调用：新增一条受理卡类信息。");
		} catch (Exception e) {

			log.info("EdcCardDaoHibernateHQLImpl.createItem()新增一条受理卡类信息，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcCardDaoHibernateHQLImpl.deleteItem()开始调用：删除终端受理卡类信息。");

			for (int i = 0; i < keys.size(); i++) {

				delete(findItemByKey((EdcCard) keys.get(i), ud), ud);
			}

			log.info("EdcCardDaoHibernateHQLImpl.deleteItem()结束调用：删除终端受理卡类信息。");
		} catch (Exception e) {

			log.info("EdcCardDaoHibernateHQLImpl.deleteItem()删除终端受理卡类信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public EdcCard findItemByKey(EdcCard edcCard, UserData ud) throws OAException {
		List<EdcCard> edcCardList = new ArrayList<EdcCard>();
		EdcCard model = null;
		try {
			log.info("EdcCardDaoHibernateHQLImpl.findItemByKey()开始调用：显示一条终端受理卡类记录。");

			StringBuffer bufferHql = new StringBuffer("from EdcCard  t where 1=1");
			if (edcCard.getId() != null) {
				// 按商户编号查询
				// if (edcCard.getId().getMerchantId()!= null&&
				// !edcCard.getId().getMerchantId().equals(""))
				bufferHql.append(" and t.id.merchantId = '" + edcCard.getId().getMerchantId() + "'");
				bufferHql.append(" and t.id.bankType = '" + edcCard.getId().getBankType() + "'");
				bufferHql.append(" and t.id.terminalId = '" + edcCard.getId().getTerminalId() + "'");
				bufferHql.append(" and t.cardStat = '" + edcCard.getCardStat() + "'");
				bufferHql.append(" and t.id.cardType = '" + edcCard.getId().getCardType() + "'");
				bufferHql.append(" and t.updateOper = '" + edcCard.getUpdateOper() + "'");
				bufferHql.append(" and t.updateDate = '" + edcCard.getUpdateDate() + "'");
				bufferHql.append(" and t.updateTime = '" + edcCard.getUpdateTime() + "'");
			}

			String hql = bufferHql.toString();
			edcCardList = findByHQL(hql, ud);
			if (edcCardList.size() > 0)
				model = (EdcCard) edcCardList.get(0);

			log.info("查询的记录数==" + edcCardList.size());
			log.info("查询语句：hql==" + hql);
			log.info("EdcCardDaoHibernateHQLImpl.findItemByKey()结束调用：显示一条终端受理卡类记录。");
		} catch (Exception e) {

			log.info("EdcCardDaoHibernateHQLImpl.findItemByKey()显示一条终端受理卡类记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public List queryAllItem(EdcCard edcCard, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("EdcCardDaoHibernateHQLImpl.queryAllItem()开始调用：根据条件查询终端受理卡类。");

			StringBuffer bufferHql = new StringBuffer("from EdcCard  t where 1=1");

			if (edcCard.getId() != null) {
				// 按商户编号查询
				if (edcCard.getId().getMerchantId() != null && !edcCard.getId().getMerchantId().equals(""))
					bufferHql.append(" and t.id.merchantId = '" + edcCard.getId().getMerchantId() + "'");

				// 按银行类型查询
				if (edcCard.getId().getBankType() != null && !edcCard.getId().getBankType().equals(""))
					bufferHql.append(" and t.id.bankType = '" + edcCard.getId().getBankType() + "'");
			}
			// 添加排序信息
			if (sortfield != null && sortType != null) {
				bufferHql.append(" order by t." + sortfield + " " + sortType + " ");
			} else {
				bufferHql.append(Order.desc(" order by t.id.merchantId desc "));
			}

			String hql = bufferHql.toString();

			List<EdcCard> edcCardList = new ArrayList<EdcCard>();
			edcCardList = findByHQL(hql, ud);
			int endIndex = startIndex + maxresults;
			if (endIndex > edcCardList.size())
				endIndex = edcCardList.size();

			list = edcCardList.subList(startIndex, endIndex);

			log.info("EdcCardDaoHibernateHQLImpl.queryAllItem()结束调用：根据条件查询终端受理卡类。");
		} catch (Exception e) {
			log.info("EdcCardDaoHibernateHQLImpl.queryAllItem()根据条件查询终端受理卡类，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int queryCount(EdcCard edcCard, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("EdcCardDaoHibernateHQLImpl.queryCount()开始调用：查询符合条件的数量。");

			StringBuffer bufferHql = new StringBuffer("select count(*) from EdcCard  t where 1=1");

			if (edcCard.getId() != null) {
				// 按商户编号查询
				if (edcCard.getId().getMerchantId() != null && !edcCard.getId().getMerchantId().equals(""))
					bufferHql.append(" and t.id.merchantId = '" + edcCard.getId().getMerchantId() + "'");

				// 按银行类型查询
				if (edcCard.getId().getBankType() != null && !edcCard.getId().getBankType().equals(""))
					bufferHql.append(" and t.id.bankType = '" + edcCard.getId().getBankType() + "'");
			}
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());

			log.info("EdcCardDaoHibernateHQLImpl.queryCount()结束调用：查询符合条件的数量。");
		} catch (Exception e) {

			log.info("EdcCardDaoHibernateHQLImpl.queryCount()查询符合条件的数量，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	public void saveItem(EdcCard edcCard, UserData ud) throws OAException {
		try {
			log.info("EdcCardDaoHibernateHQLImpl.saveItem()开始调用：保存 、修改终端受理卡类。");

			update(edcCard, ud);

			log.info("EdcCardDaoHibernateHQLImpl.saveItem()结束调用：保存 、修改终端受理卡类.");
		} catch (Exception e) {

			log.info("EdcCardDaoHibernateHQLImpl.saveItem()保存 、修改终端受理卡类,出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

}
