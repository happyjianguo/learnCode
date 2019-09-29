package cn.yufu.posp.logManager.dao.hibernate.hql;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.logManager.domain.model.OperateLog;

public class LogDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements LogDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public LogDaoHibernateHQLImpl() {

	}

	/**
	 * ��ѯ���ж���
	 */
	public List queryAll(OperateLog newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("LogDaoHibernateHQLImpl.querySum()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer(" from OperateLog o where 1=1");

			if (newQueryModel.getType() != null && !newQueryModel.getType().equals(""))
				bufferHql.append(" and o.type = '" + newQueryModel.getType() + "'");

			if (newQueryModel.getCreateDate() != null && !newQueryModel.getCreateDate().equals(""))
				bufferHql.append(" and o.createDate = '" + newQueryModel.getCreateDate() + "'");

			// ���������Ϣ
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					bufferHql.append(" order by "+sortfield+" asc");
				else
					bufferHql.append(" order by "+sortfield+" desc");
			} else {
				bufferHql.append(" order by logId ");
			}

			list = findByHQL(bufferHql.toString(), startIndex, maxresults, ud);

			log.info("LogDaoHibernateHQLImpl.querySum()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("LogDaoHibernateHQLImpl.querySum()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 * ��ѯ��¼����
	 */
	public int querySum(OperateLog queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("LogDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from OperateLog o where 1=1");

			// ���̻���Ų�ѯ
			if (queryModel.getType() != null && !queryModel.getType().equals(""))
				bufferHql.append(" and o.type = '" + queryModel.getType() + "'");

			// ���̻�״̬��ѯ
			if (queryModel.getCreateDate() != null && !queryModel.getCreateDate().equals(""))
				bufferHql.append(" and o.createDate = '" + queryModel.getCreateDate() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("LogDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("LogDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;
	}



	/**
	 *��ʾһ����¼
	 */
	public OperateLog findItem(String newKey, UserData ud) throws OAException {
		OperateLog model = null;
		try {
			log.info("LogDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			List list = null;
			Session session = getSession();
			list = findByHQL("from OperateLog o where 1=1 and logId = "+newKey , ud);
			if (list.size() > 0)
				model = (OperateLog) list.get(0);
			
			log.info("LogDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("LogDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

		return model;
	}

	/**
	 *�½�һ����¼
	 */
	public void createItem(OperateLog newModel, UserData ud) throws OAException {
		
		try {
			log.info("LogDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����Ϣ��");

			newModel.setCreateOper(ud.getUserId());
			newModel.setCreateDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
			newModel.setCreateTime(new SimpleDateFormat("HHmmss").format(new Date()));
			save(newModel, ud);
			log.info("LogDaoHibernateHQLImpl.createItem()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("LogDaoHibernateHQLImpl.createItem()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}
	}

}
