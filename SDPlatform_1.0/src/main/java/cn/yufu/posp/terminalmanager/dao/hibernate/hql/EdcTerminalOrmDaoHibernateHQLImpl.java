package cn.yufu.posp.terminalmanager.dao.hibernate.hql;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Order;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalOrm;

public class EdcTerminalOrmDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements EdcTerminalOrmDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("edcTerminal");

	/**
	 * @roseuid 44BAF72B0232
	 */
	public EdcTerminalOrmDaoHibernateHQLImpl() {
	}

	public void createItem(EdcTerminalOrm edcTerminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.createItem()��ʼ���ã����һ���ն����ϼ�¼��");

			save(edcTerminalOrm, ud);

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.createItem()�������ã����һ���ն����ϼ�¼��");
		} catch (Exception e) {

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.createItem()���һ���ն����ϼ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.deleteItem()��ʼ���ã�ɾ���ն����ϼ�¼��");

			for (int i = 0; i < keys.size(); i++) {
				delete(findItemByKey((EdcTerminalOrm) keys.get(i), ud), ud);
			}

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.deleteItem()�������ã�ɾ���ն����ϼ�¼��");
		} catch (Exception e) {

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.deleteItem()ɾ���ն����ϼ�¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public EdcTerminalOrm findItemByKey(EdcTerminalOrm edcTerminalOrm, UserData ud) throws OAException {
		List<EdcTerminalOrm> edcTerminalOrmList = new ArrayList<EdcTerminalOrm>();
		EdcTerminalOrm model = new EdcTerminalOrm();
		try {
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.findItemByKey()��ʼ���ã�����һ���ն����ϼ�¼��");

			StringBuffer bufferHql = new StringBuffer(" from EdcTerminalOrm  t where 1=1");

			if (edcTerminalOrm != null) {

				if (edcTerminalOrm.getMerchantId() != null && !edcTerminalOrm.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + edcTerminalOrm.getMerchantId() + "'");

				if (edcTerminalOrm.getTerminalId() != null && !edcTerminalOrm.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + edcTerminalOrm.getTerminalId() + "'");

				if (edcTerminalOrm.getModuleId() != null && !edcTerminalOrm.getModuleId().equals(""))
					bufferHql.append(" and t.moduleId = '" + edcTerminalOrm.getModuleId() + "'");

			}

			String hql = bufferHql.toString();
			edcTerminalOrmList = findByHQL(hql, ud);

			if (edcTerminalOrmList.size() > 0)
				model = (EdcTerminalOrm) edcTerminalOrmList.get(0);

			log.info(" ��ѯ���: hql==" + hql);
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.findItemByKey()�������ã�����һ���ն����ϼ�¼��");
		} catch (Exception e) {

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.findItemByKey()����һ���ն����ϼ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public List queryAllItem(EdcTerminalOrm edcTerminalOrm, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List<EdcTerminalOrm> list = new ArrayList<EdcTerminalOrm>();
		try {
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.queryAllItem()��ʼ���á������ҷ��������ļ�¼��");

			StringBuffer bufferHql = new StringBuffer(" from EdcTerminalOrm  t where 1=1");

			if (edcTerminalOrm != null) {
				// ���̻���Ų�ѯ
				if (edcTerminalOrm.getMerchantId() != null && !edcTerminalOrm.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + edcTerminalOrm.getMerchantId() + "'");

				// ���ն˱�Ų�ѯ
				if (edcTerminalOrm.getTerminalId() != null && !edcTerminalOrm.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + edcTerminalOrm.getTerminalId() + "'");
				
				// �������ն˺Ų�ѯ
				if (edcTerminalOrm.getBankTerminalId() != null && !edcTerminalOrm.getBankTerminalId().equals(""))
					bufferHql.append(" and t.bankTerminalId = '" + edcTerminalOrm.getBankTerminalId() + "'");

				if (edcTerminalOrm.getLogonStatus() != null && !edcTerminalOrm.getLogonStatus().equals(""))
					bufferHql.append(" and t.logonStatus = '" + edcTerminalOrm.getLogonStatus() + "'");
			}

			// ���������Ϣ
			if (sortfield != null && sortType != null) {
				bufferHql.append(" order by t." + sortfield + " " + sortType + " ");
			} else {
				bufferHql.append(Order.desc(" order by t.id.merchantId desc "));
			}

			String hql = bufferHql.toString();

			List<EdcTerminalOrm> edcTerminalOrmList = new ArrayList<EdcTerminalOrm>();
			edcTerminalOrmList = findByHQL(hql, ud);
			int endIndex = startIndex + maxresults;
			if (endIndex > edcTerminalOrmList.size())
				endIndex = edcTerminalOrmList.size();

			list = edcTerminalOrmList.subList(startIndex, endIndex);

			log.info("��ѯ���==" + hql);

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.queryAllItem()�������ã����ҷ��������ļ�¼��");
		} catch (Exception e) {
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.queryAllItem()���ҷ��������ļ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int queryCount(EdcTerminalOrm edcTerminalOrm, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.queryCount()��ʼ���ã���ѯ�����������ն�����������");

			StringBuffer bufferHql = new StringBuffer("select count(*) from EdcTerminalOrm  t where 1=1");

			if (edcTerminalOrm != null) {
				// ���̻���Ų�ѯ
				if (edcTerminalOrm.getMerchantId() != null && !edcTerminalOrm.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + edcTerminalOrm.getMerchantId() + "'");

				// ���ն˱�Ų�ѯ
				if (edcTerminalOrm.getTerminalId() != null && !edcTerminalOrm.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + edcTerminalOrm.getTerminalId() + "'");

				if (edcTerminalOrm.getLogonStatus() != null && !edcTerminalOrm.getLogonStatus().equals(""))
					bufferHql.append(" and t.logonStatus = '" + edcTerminalOrm.getLogonStatus() + "'");
			}
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());

			log.info("��ѯ���==" + hql);
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.queryCount()�����쳣����ѯ�����������ն�����������");
		} catch (Exception e) {

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.queryCount()��ѯ�����������ն����������������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	public void saveItem(EdcTerminalOrm edcTerminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.saveItem()��ʼ���ã�����һ���ն����ϼ�¼��");

			update(edcTerminalOrm, ud);

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.saveItem()�������ã�����һ���ն����ϼ�¼��");
		} catch (Exception e) {

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.saveItem()����һ���ն����ϼ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

	public EdcTerminalOrm queryModualBy(String merchantId, String terminalId) throws OAException {
		List<EdcTerminalOrm> edcTerminalOrmList = new ArrayList<EdcTerminalOrm>();
		EdcTerminalOrm model = new EdcTerminalOrm();
		try {
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.findItemByKey()��ʼ���ã�����һ���ն����ϼ�¼��");

			StringBuffer bufferHql = new StringBuffer(" from EdcTerminalOrm  t where ");
			bufferHql.append(" t.bankMerchantId='" + merchantId + "'");
			bufferHql.append(" and t.bankTerminalId='" + terminalId + "'");

			String hql = bufferHql.toString();
			edcTerminalOrmList = findByHQL(hql, null);

			if (edcTerminalOrmList.size() > 0)
				model = (EdcTerminalOrm) edcTerminalOrmList.get(0);

			log.info(" ��ѯ���: hql==" + hql);
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.findItemByKey()�������ã�����һ���ն����ϼ�¼��");
		} catch (Exception e) {

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.findItemByKey()����һ���ն����ϼ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	//У����������Ψһ��PKEY
	public String checkEdcTerminalOrmPKEY(String merchantId,String terminalId,String moduleId) throws OAException {
		log.info("EdcTerminalOrmDaoHibernateHQLImpl.checkEdcTerminalOrmPKEY()��ʼ���ã�У����������Ψһ��PKEY��");
		List<EdcTerminalOrm> edcTerminalOrmList = new ArrayList<EdcTerminalOrm>();
		String checkFlag="0";
		StringBuffer bufferHql = new StringBuffer(" from EdcTerminalOrm  t where ");
		bufferHql.append(" t.merchantId='" + merchantId + "'");
		bufferHql.append(" and t.terminalId='" + terminalId + "'");
		bufferHql.append(" and t.moduleId='" + moduleId + "'");
		String hql = bufferHql.toString();
		try {
			edcTerminalOrmList = findByHQL(hql, null);
			if(edcTerminalOrmList != null && !edcTerminalOrmList.isEmpty()){
				 checkFlag="1";
			}
			log.info(" ��ѯ���: hql==" + hql);
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.checkEdcTerminalOrmPKEY()����У����������Ψһ��PKEY��");
		} catch (Exception e) {

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.checkEdcTerminalOrmPKEY()У����������Ψһ��PKEY�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return checkFlag;
	}
	
	
	//У������Ψһ��ORM
	public String checkEdcTerminalOrmORM(String bankMerchantId,String bankTerminalId,String moduleId) throws OAException {
		log.info("EdcTerminalOrmDaoHibernateHQLImpl.checkEdcTerminalOrmPKEY()��ʼ���ã�У������Ψһ��ORM��");
		List<EdcTerminalOrm> edcTerminalOrmList = new ArrayList<EdcTerminalOrm>();
		String checkFlag="0";
		StringBuffer bufferHql = new StringBuffer(" from EdcTerminalOrm  t where ");
		bufferHql.append(" t.bankMerchantId='" + bankMerchantId + "'");
		bufferHql.append(" and t.bankTerminalId='" + bankTerminalId + "'");
		bufferHql.append(" and t.moduleId='" + moduleId + "'");
		String hql = bufferHql.toString();
		try {
			edcTerminalOrmList = findByHQL(hql, null);
			if(edcTerminalOrmList != null && !edcTerminalOrmList.isEmpty()){
				 checkFlag="1";
			}
			log.info(" ��ѯ���: hql==" + hql);
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.checkEdcTerminalOrmPKEY()����У������Ψһ��ORM��");
		} catch (Exception e) {

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.checkEdcTerminalOrmPKEY()У������Ψһ��ORM�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return checkFlag;
	}
}
