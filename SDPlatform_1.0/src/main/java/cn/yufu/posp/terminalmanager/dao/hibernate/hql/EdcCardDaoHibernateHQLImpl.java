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
			log.info("EdcCardDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����������Ϣ��");

			save(edcCard, ud);

			log.info("EdcCardDaoHibernateHQLImpl.createItem()�������ã�����һ����������Ϣ��");
		} catch (Exception e) {

			log.info("EdcCardDaoHibernateHQLImpl.createItem()����һ����������Ϣ�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcCardDaoHibernateHQLImpl.deleteItem()��ʼ���ã�ɾ���ն���������Ϣ��");

			for (int i = 0; i < keys.size(); i++) {

				delete(findItemByKey((EdcCard) keys.get(i), ud), ud);
			}

			log.info("EdcCardDaoHibernateHQLImpl.deleteItem()�������ã�ɾ���ն���������Ϣ��");
		} catch (Exception e) {

			log.info("EdcCardDaoHibernateHQLImpl.deleteItem()ɾ���ն���������Ϣ�������쳣��");
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
			log.info("EdcCardDaoHibernateHQLImpl.findItemByKey()��ʼ���ã���ʾһ���ն��������¼��");

			StringBuffer bufferHql = new StringBuffer("from EdcCard  t where 1=1");
			if (edcCard.getId() != null) {
				// ���̻���Ų�ѯ
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

			log.info("��ѯ�ļ�¼��==" + edcCardList.size());
			log.info("��ѯ��䣺hql==" + hql);
			log.info("EdcCardDaoHibernateHQLImpl.findItemByKey()�������ã���ʾһ���ն��������¼��");
		} catch (Exception e) {

			log.info("EdcCardDaoHibernateHQLImpl.findItemByKey()��ʾһ���ն��������¼�������쳣��");
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
			log.info("EdcCardDaoHibernateHQLImpl.queryAllItem()��ʼ���ã�����������ѯ�ն������ࡣ");

			StringBuffer bufferHql = new StringBuffer("from EdcCard  t where 1=1");

			if (edcCard.getId() != null) {
				// ���̻���Ų�ѯ
				if (edcCard.getId().getMerchantId() != null && !edcCard.getId().getMerchantId().equals(""))
					bufferHql.append(" and t.id.merchantId = '" + edcCard.getId().getMerchantId() + "'");

				// ���������Ͳ�ѯ
				if (edcCard.getId().getBankType() != null && !edcCard.getId().getBankType().equals(""))
					bufferHql.append(" and t.id.bankType = '" + edcCard.getId().getBankType() + "'");
			}
			// ���������Ϣ
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

			log.info("EdcCardDaoHibernateHQLImpl.queryAllItem()�������ã�����������ѯ�ն������ࡣ");
		} catch (Exception e) {
			log.info("EdcCardDaoHibernateHQLImpl.queryAllItem()����������ѯ�ն������࣬�����쳣��");
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
			log.info("EdcCardDaoHibernateHQLImpl.queryCount()��ʼ���ã���ѯ����������������");

			StringBuffer bufferHql = new StringBuffer("select count(*) from EdcCard  t where 1=1");

			if (edcCard.getId() != null) {
				// ���̻���Ų�ѯ
				if (edcCard.getId().getMerchantId() != null && !edcCard.getId().getMerchantId().equals(""))
					bufferHql.append(" and t.id.merchantId = '" + edcCard.getId().getMerchantId() + "'");

				// ���������Ͳ�ѯ
				if (edcCard.getId().getBankType() != null && !edcCard.getId().getBankType().equals(""))
					bufferHql.append(" and t.id.bankType = '" + edcCard.getId().getBankType() + "'");
			}
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());

			log.info("EdcCardDaoHibernateHQLImpl.queryCount()�������ã���ѯ����������������");
		} catch (Exception e) {

			log.info("EdcCardDaoHibernateHQLImpl.queryCount()��ѯ���������������������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	public void saveItem(EdcCard edcCard, UserData ud) throws OAException {
		try {
			log.info("EdcCardDaoHibernateHQLImpl.saveItem()��ʼ���ã����� ���޸��ն������ࡣ");

			update(edcCard, ud);

			log.info("EdcCardDaoHibernateHQLImpl.saveItem()�������ã����� ���޸��ն�������.");
		} catch (Exception e) {

			log.info("EdcCardDaoHibernateHQLImpl.saveItem()���� ���޸��ն�������,�����쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

}
