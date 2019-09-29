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
import cn.yufu.posp.terminalmanager.domain.model.EdcBlack;

public class EdcBlackDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements EdcBlackDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("edcTerminal");

	/**
	 * @roseuid 44BAF72B0232
	 */
	public EdcBlackDaoHibernateHQLImpl() {
	}

	public void createItem(EdcBlack edcTerminal, UserData ud) throws OAException {
		EdcBlack model = findItemByKey(edcTerminal, ud);
		if (model.getMerchantId() != null) {
			throw new OAException("�̻����:" + model.getMerchantId() + " �ն˺�:" + model.getTerminalId() + "�ļ�¼�����ݿ����Ѵ��ڣ�");
		}
		try {
			log.info("EdcBlackDaoHibernateHQLImpl.createItem()��ʼ���ã��½��ն˺�������");

			save(edcTerminal, ud);

			log.info("EdcBlackDaoHibernateHQLImpl.createItem()�������ã��½��ն˺�������");
		} catch (Exception e) {

			log.info("EdcBlackDaoHibernateHQLImpl.createItem()�½��ն˺������������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcBlackDaoHibernateHQLImpl.deleteItem()��ʼ���ã�ɾ���ն˺�������");

			for (int i = 0; i < keys.size(); i++) {
				delete(findItemByKey((EdcBlack) keys.get(i), ud), ud);
			}

			log.info("EdcBlackDaoHibernateHQLImpl.deleteItem()�������ã�ɾ���ն˺�������");
		} catch (Exception e) {

			log.info("EdcBlackDaoHibernateHQLImpl.deleteItem()ɾ���ն˺������������쳣");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public EdcBlack findItemByKey(EdcBlack edcTerminal, UserData ud) throws OAException {
		List<EdcBlack> edcTerminalList = new ArrayList<EdcBlack>();
		EdcBlack model = new EdcBlack();
		try {
			log.info("EdcBlackDaoHibernateHQLImpl.findItemByKey()��ʼ���ã���һ���ն˺�������");

			StringBuffer bufferHql = new StringBuffer(" from EdcBlack  t where 1=1");

			if (edcTerminal.getMerchantId() != null && !edcTerminal.getMerchantId().equals(""))
				bufferHql.append(" and t.merchantId = '" + edcTerminal.getMerchantId() + "'");

			if (edcTerminal.getTerminalId() != null && !edcTerminal.getTerminalId().equals(""))
				bufferHql.append(" and t.terminalId = '" + edcTerminal.getTerminalId() + "'");

			String hql = bufferHql.toString();
			edcTerminalList = findByHQL(hql, ud);
			log.info("���������ļ�¼��==" + edcTerminalList.size() + "");

			if (edcTerminalList.size() > 0)
				model = (EdcBlack) edcTerminalList.get(0);

			log.info("��ѯ���: hql==" + hql + "");
			log.info("EdcBlackDaoHibernateHQLImpl.findItemByKey()�������ã���һ���ն˺�������");
		} catch (Exception e) {

			log.info("EdcBlackDaoHibernateHQLImpl.findItemByKey()��һ���ն˺������������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public List queryAllItem(EdcBlack edcTerminal, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List<EdcBlack> list = new ArrayList<EdcBlack>();
		try {
			log.info("EdcBlackDaoHibernateHQLImpl.queryAllItem()��ʼ���ã������������ն˺�������");

			StringBuffer bufferHql = new StringBuffer(" from EdcBlack  t where 1=1");

			if (edcTerminal.getMerchantId() != null && !edcTerminal.getMerchantId().equals(""))
				bufferHql.append(" and t.merchantId = '" + edcTerminal.getMerchantId() + "'");

			// ���ն˱�Ų�ѯ
			if (edcTerminal.getTerminalId() != null && !edcTerminal.getTerminalId().equals(""))
				bufferHql.append(" and t.terminalId = '" + edcTerminal.getTerminalId() + "'");

			// ���������Ϣ
			if (sortfield != null && sortType != null) {
				bufferHql.append(" order by t." + sortfield + " " + sortType + " ");
			} else {
				bufferHql.append(Order.desc(" order by t.merchantId desc "));
			}

			String hql = bufferHql.toString();

			List<EdcBlack> edcTerminalList = new ArrayList<EdcBlack>();
			edcTerminalList = findByHQL(hql, ud);
			int endIndex = startIndex + maxresults;
			if (endIndex > edcTerminalList.size())
				endIndex = edcTerminalList.size();

			list = edcTerminalList.subList(startIndex, endIndex);

			log.info("��ѯ���==" + hql + "");

			log.info("EdcBlackDaoHibernateHQLImpl.queryAllItem()�������ã������������ն˺�������");
		} catch (Exception e) {
			log.info("EdcBlackDaoHibernateHQLImpl.queryAllItem()�����������ն˺������������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int queryCount(EdcBlack edcTerminal, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("EdcBlackDaoHibernateHQLImpl.queryCount()��ʼ���ã������������ն˺���������");

			StringBuffer bufferHql = new StringBuffer("select count(*) from EdcBlack  t where 1=1");

			// ���̻���Ų�ѯ
			if (edcTerminal.getMerchantId() != null && !edcTerminal.getMerchantId().equals(""))
				bufferHql.append(" and t.merchantId = '" + edcTerminal.getMerchantId() + "'");

			// ���ն˱�Ų�ѯ
			if (edcTerminal.getTerminalId() != null && !edcTerminal.getTerminalId().equals(""))
				bufferHql.append(" and t.terminalId = '" + edcTerminal.getTerminalId() + "'");
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());

			log.info("��ѯ���==" + hql + "");
			log.info("EdcBlackDaoHibernateHQLImpl.queryCount()�����쳣�������������ն˺���������");
		} catch (Exception e) {

			log.info("EdcBlackDaoHibernateHQLImpl.queryCount()�����������ն˺��������������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	public void saveItem(EdcBlack edcTerminal, UserData ud) throws OAException {
		try {
			log.info("EdcBlackDaoHibernateHQLImpl.saveItem()��ʼ���ã������ն˺���������");

			update(edcTerminal, ud);

			log.info("EdcBlackDaoHibernateHQLImpl.saveItem()�������ã������ն˺���������");
		} catch (Exception e) {

			log.info("EdcBlackDaoHibernateHQLImpl.saveItem()�����ն˺��������������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

}
