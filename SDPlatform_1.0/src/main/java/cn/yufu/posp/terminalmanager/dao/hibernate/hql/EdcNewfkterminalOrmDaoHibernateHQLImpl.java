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
import cn.yufu.posp.terminalmanager.domain.model.EdcNewfkterminalOrm;

public class EdcNewfkterminalOrmDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements EdcNewfkterminalOrmDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("edcTerminal");

	/**
	 * @roseuid 44BAF72B0232
	 */
	public EdcNewfkterminalOrmDaoHibernateHQLImpl() {
	}

	public void createItem(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.createItem()��ʼ���ã����һ���ն����ϼ�¼��");

			save(edcNewfkterminalOrm, ud);

			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.createItem()�������ã����һ���ն����ϼ�¼��");
		} catch (Exception e) {

			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.createItem()���һ���ն����ϼ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.deleteItem()��ʼ���ã�ɾ���ն����ϼ�¼��");

			for (int i = 0; i < keys.size(); i++) {
				delete(findItemByKey((EdcNewfkterminalOrm) keys.get(i), ud), ud);
			}

			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.deleteItem()�������ã�ɾ���ն����ϼ�¼��");
		} catch (Exception e) {

			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.deleteItem()ɾ���ն����ϼ�¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public EdcNewfkterminalOrm findItemByKey(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud) throws OAException {
		List<EdcNewfkterminalOrm> edcNewfkterminalOrmList = new ArrayList<EdcNewfkterminalOrm>();
		EdcNewfkterminalOrm model = new EdcNewfkterminalOrm();
		try {
			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.findItemByKey()��ʼ���ã�����һ���ն����ϼ�¼��");

			StringBuffer bufferHql = new StringBuffer(" from EdcNewfkterminalOrm  t where 1=1");

			if (edcNewfkterminalOrm != null) {

				if (edcNewfkterminalOrm.getMerchantId() != null && !edcNewfkterminalOrm.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + edcNewfkterminalOrm.getMerchantId() + "'");

				if (edcNewfkterminalOrm.getTerminalId() != null && !edcNewfkterminalOrm.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + edcNewfkterminalOrm.getTerminalId() + "'");
				
				if (edcNewfkterminalOrm.getModuleId() != null && !edcNewfkterminalOrm.getModuleId().equals(""))
					bufferHql.append(" and t.moduleId = '" + edcNewfkterminalOrm.getModuleId() + "'");

			}

			String hql = bufferHql.toString();
			edcNewfkterminalOrmList = findByHQL(hql, ud);

			if (edcNewfkterminalOrmList.size() > 0)
				model = (EdcNewfkterminalOrm) edcNewfkterminalOrmList.get(0);

			log.info(" ��ѯ���: hql==" + hql);
			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.findItemByKey()�������ã�����һ���ն����ϼ�¼��");
		} catch (Exception e) {

			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.findItemByKey()����һ���ն����ϼ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public List queryAllItem(EdcNewfkterminalOrm edcNewfkterminalOrm, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List<EdcNewfkterminalOrm> list = new ArrayList<EdcNewfkterminalOrm>();
		try {
			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.queryAllItem()��ʼ���á������ҷ��������ļ�¼��");

			StringBuffer bufferHql = new StringBuffer(" from EdcNewfkterminalOrm  t where 1=1");

			if (edcNewfkterminalOrm != null) {
				// ���̻���Ų�ѯ
				if (edcNewfkterminalOrm.getMerchantId() != null && !edcNewfkterminalOrm.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + edcNewfkterminalOrm.getMerchantId() + "'");

				// ���ն˱�Ų�ѯ
				if (edcNewfkterminalOrm.getTerminalId() != null && !edcNewfkterminalOrm.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + edcNewfkterminalOrm.getTerminalId() + "'");

				if (edcNewfkterminalOrm.getLogonStatus() != null && !edcNewfkterminalOrm.getLogonStatus().equals(""))
					bufferHql.append(" and t.logonStatus = '" + edcNewfkterminalOrm.getLogonStatus() + "'");
			}

			// ���������Ϣ
			if (sortfield != null && sortType != null) {
				bufferHql.append(" order by t." + sortfield + " " + sortType + " ");
			} else {
				bufferHql.append(Order.desc(" order by t.id.merchantId desc "));
			}

			String hql = bufferHql.toString();

			List<EdcNewfkterminalOrm> edcNewfkterminalOrmList = new ArrayList<EdcNewfkterminalOrm>();
			edcNewfkterminalOrmList = findByHQL(hql, ud);
			int endIndex = startIndex + maxresults;
			if (endIndex > edcNewfkterminalOrmList.size())
				endIndex = edcNewfkterminalOrmList.size();

			list = edcNewfkterminalOrmList.subList(startIndex, endIndex);

			log.info("��ѯ���==" + hql);

			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.queryAllItem()�������ã����ҷ��������ļ�¼��");
		} catch (Exception e) {
			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.queryAllItem()���ҷ��������ļ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int queryCount(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.queryCount()��ʼ���ã���ѯ�����������ն�����������");

			StringBuffer bufferHql = new StringBuffer("select count(*) from EdcNewfkterminalOrm  t where 1=1");

			if (edcNewfkterminalOrm != null) {
				// ���̻���Ų�ѯ
				if (edcNewfkterminalOrm.getMerchantId() != null && !edcNewfkterminalOrm.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + edcNewfkterminalOrm.getMerchantId() + "'");

				// ���ն˱�Ų�ѯ
				if (edcNewfkterminalOrm.getTerminalId() != null && !edcNewfkterminalOrm.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + edcNewfkterminalOrm.getTerminalId() + "'");
				
				if (edcNewfkterminalOrm.getLogonStatus() != null && !edcNewfkterminalOrm.getLogonStatus().equals(""))
					bufferHql.append(" and t.logonStatus = '" + edcNewfkterminalOrm.getLogonStatus() + "'");
			}
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());

			log.info("��ѯ���==" + hql);
			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.queryCount()�����쳣����ѯ�����������ն�����������");
		} catch (Exception e) {

			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.queryCount()��ѯ�����������ն����������������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	public void saveItem(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.saveItem()��ʼ���ã�����һ���ն����ϼ�¼��");

			update(edcNewfkterminalOrm, ud);

			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.saveItem()�������ã�����һ���ն����ϼ�¼��");
		} catch (Exception e) {

			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.saveItem()����һ���ն����ϼ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

}
