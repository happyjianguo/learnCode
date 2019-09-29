/**
 *����:cn.yufu.posp.terminalmanager.dao.hibernate.hql
 *����:package cn.yufu.posp.terminalmanager.dao.hibernate.hql;
 */
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
import cn.yufu.posp.terminalmanager.domain.model.EdcZskterminalOrm;

/**
 * EdcZskterminalOrmDaoHibernateHQLImpl.java
 * ��Ȩ����(C) 2018 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2018��3��2��
 * ����:ר�����ն�
 */
public class EdcZskterminalOrmDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl
		implements EdcZskterminalOrmDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("edcTerminal");

	/**
	 * @roseuid 44BAF72B0232
	 */
	public EdcZskterminalOrmDaoHibernateHQLImpl() {
	}
	
	@Override
	public int queryCount(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.queryCount()��ʼ���ã���ѯ�����������ն�����������");

			StringBuffer bufferHql = new StringBuffer("select count(*) from EdcZskterminalOrm  t where 1=1");

			if (edcZskterminalOrm != null) {
				// ���̻���Ų�ѯ
				if (edcZskterminalOrm.getMerchantId() != null && !edcZskterminalOrm.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + edcZskterminalOrm.getMerchantId() + "'");

				// ���ն˱�Ų�ѯ
				if (edcZskterminalOrm.getTerminalId() != null && !edcZskterminalOrm.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + edcZskterminalOrm.getTerminalId() + "'");
				
				if (edcZskterminalOrm.getLogonStatus() != null && !edcZskterminalOrm.getLogonStatus().equals(""))
					bufferHql.append(" and t.logonStatus = '" + edcZskterminalOrm.getLogonStatus() + "'");
			}
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());

			log.info("��ѯ���==" + hql);
			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.queryCount()�����쳣����ѯ�����������ն�����������");
		} catch (Exception e) {

			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.queryCount()��ѯ�����������ն����������������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	@Override
	public List queryAllItem(EdcZskterminalOrm edcZskterminalOrm, int startIndex, int maxresults, String sortfield,
			String sortType, UserData ud) throws OAException {
		
		List<EdcZskterminalOrm> list = new ArrayList<EdcZskterminalOrm>();
		
		try {
			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.queryAllItem()��ʼ���ã����ҷ��������ļ�¼��");

			StringBuffer bufferHql = new StringBuffer(" from EdcZskterminalOrm  t where 1=1");

			if (edcZskterminalOrm != null) {
				// ���̻���Ų�ѯ
				if (edcZskterminalOrm.getMerchantId() != null && !edcZskterminalOrm.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + edcZskterminalOrm.getMerchantId() + "'");

				// ���ն˱�Ų�ѯ
				if (edcZskterminalOrm.getTerminalId() != null && !edcZskterminalOrm.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + edcZskterminalOrm.getTerminalId() + "'");

				if (edcZskterminalOrm.getLogonStatus() != null && !edcZskterminalOrm.getLogonStatus().equals(""))
					bufferHql.append(" and t.logonStatus = '" + edcZskterminalOrm.getLogonStatus() + "'");
			}

			// ���������Ϣ
			if (sortfield != null && sortType != null) {
				bufferHql.append(" order by t." + sortfield + " " + sortType + " ");
			} else {
				bufferHql.append(Order.desc(" order by t.id.merchantId desc "));
			}

			String hql = bufferHql.toString();

			List<EdcZskterminalOrm> edcAlipayWeChatList = new ArrayList<EdcZskterminalOrm>();
			edcAlipayWeChatList = findByHQL(hql, ud);
			int endIndex = startIndex + maxresults;
			if (endIndex > edcAlipayWeChatList.size())
				endIndex = edcAlipayWeChatList.size();

			list = edcAlipayWeChatList.subList(startIndex, endIndex);

			log.info("��ѯ���==" + hql);

			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.queryAllItem()�������ã����ҷ��������ļ�¼��");
		} catch (Exception e) {
			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.queryAllItem()���ҷ��������ļ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@Override
	public EdcZskterminalOrm findItemByKey(EdcZskterminalOrm key, UserData ud) throws OAException {
		List<EdcZskterminalOrm> edcAlipayWeChatList = new ArrayList<EdcZskterminalOrm>();
		EdcZskterminalOrm model = new EdcZskterminalOrm();
		try {
			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.findItemByKey()��ʼ���ã�����һ���ն����ϼ�¼��");

			StringBuffer bufferHql = new StringBuffer(" from EdcZskterminalOrm  t where 1=1");

			if (key != null) {

				if (key.getMerchantId() != null && !key.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + key.getMerchantId() + "'");

				if (key.getTerminalId() != null && !key.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + key.getTerminalId() + "'");
				
				if (key.getModuleId() != null && !key.getModuleId().equals(""))
					bufferHql.append(" and t.moduleId = '" + key.getModuleId() + "'");

			}

			String hql = bufferHql.toString();
			edcAlipayWeChatList = findByHQL(hql, ud);

			if (edcAlipayWeChatList.size() > 0)
				model = (EdcZskterminalOrm) edcAlipayWeChatList.get(0);

			log.info(" ��ѯ���: hql==" + hql);
			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.findItemByKey()�������ã�����һ���ն����ϼ�¼��");
		} catch (Exception e) {

			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.findItemByKey()����һ���ն����ϼ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}	

	@Override
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.deleteItem()��ʼ���ã�ɾ���ն����ϼ�¼��");

			for (int i = 0; i < keys.size(); i++) {
				EdcZskterminalOrm model = findItemByKey((EdcZskterminalOrm) keys.get(i), ud);
				System.out.println(model);
				delete(model, ud);
			}

			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.deleteItem()�������ã�ɾ���ն����ϼ�¼��");
		} catch (Exception e) {

			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.deleteItem()ɾ���ն����ϼ�¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@Override
	public void createItem(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.createItem()��ʼ���ã����һ���ն����ϼ�¼��");

			save(edcZskterminalOrm, ud);

			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.createItem()�������ã����һ���ն����ϼ�¼��");
		} catch (Exception e) {

			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.createItem()���һ���ն����ϼ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@Override
	public void saveItem(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.saveItem()��ʼ���ã�����һ���ն����ϼ�¼��");

			update(edcZskterminalOrm, ud);

			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.saveItem()�������ã�����һ���ն����ϼ�¼��");
		} catch (Exception e) {

			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.saveItem()����һ���ն����ϼ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

}
