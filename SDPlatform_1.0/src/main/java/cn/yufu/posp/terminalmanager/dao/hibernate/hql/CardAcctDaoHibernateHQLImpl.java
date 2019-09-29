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
import cn.yufu.posp.terminalmanager.domain.model.CardAcct;

public class CardAcctDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements CardAcctDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("cardAcct");

	/**
	 * @roseuid 44BAF72B0232
	 */
	public CardAcctDaoHibernateHQLImpl() {
	}

	public void createItem(CardAcct cardAcct, UserData ud) throws OAException {
		try {
			log.info("CardAcctDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ�����˻�������Ϣ��");

			save(cardAcct, ud);

			log.info("CardAcctDaoHibernateHQLImpl.createItem()�������ã�����һ�����˻�������Ϣ��");
		} catch (Exception e) {

			log.info("CardAcctDaoHibernateHQLImpl.createItem()����һ�����˻�������Ϣ�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("CardAcctDaoHibernateHQLImpl.deleteItem()��ʼ���ã�ɾ�����˻�������Ϣ��");

			for (int i = 0; i < keys.size(); i++) {

				delete(findItemByKey((String) keys.get(i), ud), ud);
			}

			log.info("CardAcctDaoHibernateHQLImpl.deleteItem()�������ã�ɾ�����˻�������Ϣ��");
		} catch (Exception e) {

			log.info("CardAcctDaoHibernateHQLImpl.deleteItem()ɾ�����˻�������Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public CardAcct findItemByKey(String key, UserData ud) throws OAException {
		CardAcct model = null;
		try {
			log.info("CardAcctDaoHibernateHQLImpl.findItemByKey()��ʼ���ã���ʾһ�����˻���¼��");

			StringBuffer bufferHql = new StringBuffer(" from CardAcct t where 1=1");

			// �����Ų�ѯ
			if (key != null && !key.equals(""))
				bufferHql.append(" and t.cardNo = '" + key.trim() + "'");

			String hql = bufferHql.toString();
			List<CardAcct> cardAcctList = new ArrayList<CardAcct>();
			cardAcctList = findByHQL(hql, ud);
			if (cardAcctList.size() > 0)
				model = (CardAcct) cardAcctList.get(0);

			log.info("CardAcctDaoHibernateHQLImpl.findItemByKey()�������ã���ʾһ�����˻���¼��");
		} catch (Exception e) {

			log.info("CardAcctDaoHibernateHQLImpl.findItemByKey()��ʾһ�����˻���¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public List queryAllItem(CardAcct cardAcct, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List<CardAcct> list = new ArrayList<CardAcct>();
		try {
			log.info("CardAcctDaoHibernateHQLImpl.queryAllItem()��ʼ���ã���ѯ���������Ŀ��˻���¼��");

			StringBuffer bufferHql = new StringBuffer(" from CardAcct t where 1=1");

			// �����Ų�ѯ
			if (cardAcct.getCardNo() != null && !cardAcct.getCardNo().equals(""))
				bufferHql.append(" and t.cardNo = '" + cardAcct.getCardNo() + "'");

			// ������״̬��ѯ
			if (cardAcct.getCardStat() != null && !cardAcct.getCardStat().equals(""))
				bufferHql.append(" and t.cardStat = '" + cardAcct.getCardStat() + "'");

			// ���������Ϣ
			if (sortfield != null && sortType != null) {
				bufferHql.append(" order by t." + sortfield + " " + sortType + " ");
			} else {
				bufferHql.append(Order.desc(" order by t.cardNo desc "));
			}

			String hql = bufferHql.toString();
			List<CardAcct> cardAcctList = new ArrayList<CardAcct>();
			cardAcctList = findByHQL(hql, ud);
			int endIndex = startIndex + maxresults;
			if (endIndex > cardAcctList.size())
				endIndex = cardAcctList.size();

			list = cardAcctList.subList(startIndex, endIndex);

			log.info("��ѯ��䣺hql" + hql);
			log.info("CardAcctDaoHibernateHQLImpl.queryAllItem()�������ã���ѯ���������Ŀ��˻���¼��");
		} catch (Exception e) {

			log.info("CardAcctDaoHibernateHQLImpl.queryAllItem()��ѯ���������Ŀ��˻���¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int queryCount(CardAcct cardAcct, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("��CardAcctDaoHibernateHQLImpl.queryCount()��ʼ���ã���ѯ���������Ŀ��˻���¼����");

			StringBuffer bufferHql = new StringBuffer("select count(*) from CardAcct t where 1=1");

			// ������ID��ѯ
			if (cardAcct.getCardNo() != null && !cardAcct.getCardNo().equals(""))
				bufferHql.append(" and t.cardNo = '" + cardAcct.getCardNo() + "'");

			// ���������Ͳ�ѯ
			if (cardAcct.getCardStat() != null && !cardAcct.getCardStat().equals(""))
				bufferHql.append(" and t.cardStat = '" + cardAcct.getCardStat() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("����ѯ��䣺hql" + hql);
			log.info("��CardAcctDaoHibernateHQLImpl.queryCount()�������ã���ѯ���������Ŀ��˻���¼����");
		} catch (Exception e) {

			log.info("��CardAcctDaoHibernateHQLImpl.queryCount()��ѯ���������Ŀ��˻���¼���������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	public void saveItem(CardAcct cardAcct, UserData ud) throws OAException {
		try {
			log.info("CardAcctDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸Ŀ��˻���Ϣ��");

			update(cardAcct, ud);

			log.info("CardAcctDaoHibernateHQLImpl.saveItem()�������ã��޸Ŀ��˻���Ϣ��");
		} catch (Exception e) {

			log.info("CardAcctDaoHibernateHQLImpl.saveItem()�޸Ŀ��˻���Ϣ�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

}
