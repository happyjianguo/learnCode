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
import cn.yufu.posp.terminalmanager.domain.model.EdcSwitch;

public class EdcSwitchDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements EdcSwitchDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("edcSwitch");

	/**
	 * @roseuid 44BAF72B0232
	 */
	public EdcSwitchDaoHibernateHQLImpl() {
	}

	public void createItem(EdcSwitch edcSwitch, UserData ud) throws OAException {
		try {
			log.info("EdcSwitchDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ���ն�ת����Ϣ��");

			save(edcSwitch, ud);

			log.info("EdcSwitchDaoHibernateHQLImpl.createItem()�������ã�����һ���ն�ת����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcSwitchDaoHibernateHQLImpl.createItem()����һ���ն�ת����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcSwitchDaoHibernateHQLImpl.deleteItem()��ʼ���ã�ɾ���ն�ת����Ϣ��");

			for (int i = 0; i < keys.size(); i++) {

				delete(findItemByKey((EdcSwitch) keys.get(i), ud), ud);
			}

			log.info("EdcSwitchDaoHibernateHQLImpl.deleteItem()�������ã�ɾ���ն�ת����Ϣ��");
		} catch (Exception e) {
			log.info("EdcSwitchDaoHibernateHQLImpl.deleteItem()ɾ���ն�ת����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public EdcSwitch findItemByKey(EdcSwitch edcSwitch, UserData ud) throws OAException {
		EdcSwitch model = null;
		try {
			log.info("EdcSwitchDaoHibernateHQLImpl.findItemByKey()��ʼ���ã���ʾһ����¼��");

			StringBuffer bufferHql = new StringBuffer(" from EdcSwitch  t where 1=1");

			if (edcSwitch.getId() != null) {
				// ���̻���Ų�ѯ
				// if (edcSwitch.getId().getMerchantId()!= null&&
				// !edcSwitch.getId().getMerchantId().equals(""))
				bufferHql.append(" and t.id.merchantId = '" + edcSwitch.getId().getMerchantId() + "'");
				bufferHql.append(" and t.id.bankType = '" + edcSwitch.getId().getBankType() + "'");
				bufferHql.append(" and t.id.terminalId = '" + edcSwitch.getId().getTerminalId() + "'");
				bufferHql.append(" and t.othTerminalId = '" + edcSwitch.getOthTerminalId() + "'");
			}

			String hql = bufferHql.toString();
			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				model = (EdcSwitch) list.get(0);

			log.info("��ѯ���==" + hql);
			log.info("���������ļ�¼��==" + list.size());
			log.info("EdcSwitchDaoHibernateHQLImpl.findItemByKey()��ʼ���ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcSwitchDaoHibernateHQLImpl.findItemByKey()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public List queryAllItem(EdcSwitch edcSwitch, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List<EdcSwitch> list = new ArrayList<EdcSwitch>();
		List<EdcSwitch> ecSwitchList = new ArrayList<EdcSwitch>();
		try {
			log.info("EdcSwitchDaoHibernateHQLImpl.queryAllItem()��ʼ���ã���ѯ����������������");

			StringBuffer bufferHql = new StringBuffer(" from EdcSwitch  t where 1=1");

			if (edcSwitch.getId() != null) {
				// ���̻���Ų�ѯ
				if (edcSwitch.getId().getMerchantId() != null && !edcSwitch.getId().getMerchantId().equals(""))
					bufferHql.append(" and t.id.merchantId = '" + edcSwitch.getId().getMerchantId() + "'");

				// ���������Ͳ�ѯ
				if (edcSwitch.getId().getBankType() != null && !edcSwitch.getId().getBankType().equals(""))
					bufferHql.append(" and t.id.bankType = '" + edcSwitch.getId().getBankType() + "'");
			}

			// ���������Ϣ
			if (sortfield != null && sortType != null) {
				bufferHql.append(" order by t." + sortfield + " " + sortType + " ");
			} else {
				bufferHql.append(Order.desc(" order by id.merchantId "));
			}

			String hql = bufferHql.toString();
			list = findByHQL(hql, ud);
			ecSwitchList = findByHQL(hql, ud);
			int endIndex = startIndex + maxresults;
			if (endIndex > ecSwitchList.size())
				endIndex = ecSwitchList.size();

			list = ecSwitchList.subList(startIndex, endIndex);

			log.info("EdcSwitchDaoHibernateHQLImpl.queryAllItem()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcSwitchDaoHibernateHQLImpl.queryAllItem()��ѯ���������������������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int queryCount(EdcSwitch edcSwitch, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("EdcSwitchDaoHibernateHQLImpl.queryCount()��ʼ���ã���ѯ����������������");

			StringBuffer bufferHql = new StringBuffer("select count(*) from EdcSwitch  t where 1=1");

			if (edcSwitch.getId() != null) {
				// ���̻���Ų�ѯ
				if (edcSwitch.getId().getMerchantId() != null && !edcSwitch.getId().getMerchantId().equals(""))
					bufferHql.append(" and t.id.merchantId = '" + edcSwitch.getId().getMerchantId() + "'");

				// ���������Ͳ�ѯ
				if (edcSwitch.getId().getBankType() != null && !edcSwitch.getId().getBankType().equals(""))
					bufferHql.append(" and t.id.bankType = '" + edcSwitch.getId().getBankType() + "'");
			}

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());

			log.info("EdcSwitchDaoHibernateHQLImpl.queryCount()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcSwitchDaoHibernateHQLImpl.queryCount()��ѯ���������������������쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	public void saveItem(EdcSwitch edcSwitch, UserData ud) throws OAException {
		try {
			log.info("EdcSwitchDaoHibernateHQLImpl.saveItem()��ʼ���ã����� ���޸��ն�ת����");

			update(edcSwitch, ud);

			log.info("EdcSwitchDaoHibernateHQLImpl.saveItem()�������ã����� ���޸��ն�ת����");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcSwitchDaoHibernateHQLImpl.saveItem()���� ���޸��ն�ת���������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

}
