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
import cn.yufu.posp.terminalmanager.domain.model.SysParameter;

public class SysParameterDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements SysParameterDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("sysParameter");

	/**
	 * @roseuid 44BAF72B0232
	 */
	public SysParameterDaoHibernateHQLImpl() {
	}

	public void createItem(SysParameter sysParameter, UserData ud) throws OAException {
		try {
			log.info("SysParameterDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����������������Ϣ��");

			save(sysParameter, ud);

			log.info("SysParameterDaoHibernateHQLImpl.createItem()�������ã�����һ����������������Ϣ��");
		} catch (Exception e) {

			log.info("SysParameterDaoHibernateHQLImpl.createItem()����һ����������������Ϣ�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("SysParameterDaoHibernateHQLImpl.deleteItem()��ʼ���ã�ɾ����������������Ϣ��");

			for (int i = 0; i < keys.size(); i++) {

				delete(findItemByKey((SysParameter) keys.get(i), ud), ud);
			}

			log.info("SysParameterDaoHibernateHQLImpl.deleteItem()�������ã�ɾ����������������Ϣ��");
		} catch (Exception e) {

			log.info("SysParameterDaoHibernateHQLImpl.deleteItem()ɾ����������������Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public SysParameter findItemByKey(SysParameter sysParameter, UserData ud) throws OAException {
		SysParameter model = null;
		try {
			log.info("SysParameterDaoHibernateHQLImpl.findItemByKey()��ʼ���ã���ʾһ����¼��");

			StringBuffer bufferHql = new StringBuffer(" from SysParameter t where 1=1");

			if (sysParameter.getId() != null) {
				// ���������Ʋ�ѯ
				if (sysParameter.getId().getParamName() != null && !sysParameter.getId().getParamName().equals(""))
					bufferHql.append(" and t.id.paramName = '" + sysParameter.getId().getParamName() + "'");

				// ������״̬��ѯ
				if (sysParameter.getEnable() != null && !sysParameter.getEnable().equals(""))
					bufferHql.append(" and t.enable = '" + sysParameter.getEnable() + "'");
			}

			String hql = bufferHql.toString();
			List<SysParameter> sysParameterList = new ArrayList<SysParameter>();
			sysParameterList = findByHQL(hql, ud);
			if (sysParameterList.size() > 0)
				model = (SysParameter) sysParameterList.get(0);

			// System.out.println("��hql=="+hql+"��===��sysParameterList.size()=="+sysParameterList.size()+"��");

			log.info("SysParameterDaoHibernateHQLImpl.findItemByKey()�������ã���ʾһ����¼��");
		} catch (Exception e) {

			log.info("SysParameterDaoHibernateHQLImpl.findItemByKey()��ʾһ����¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public List queryAllItem(SysParameter sysParameter, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List<SysParameter> list = new ArrayList<SysParameter>();
		try {
			log.info("SysParameterDaoHibernateHQLImpl.queryAllItem()��ʼ���ã���ѯ���������ļ�¼��");

			StringBuffer bufferHql = new StringBuffer(" from SysParameter t where 1=1");

			if (sysParameter.getId() != null) {
				// ���������Ʋ�ѯ
				if (sysParameter.getId().getParamName() != null && !sysParameter.getId().getParamName().equals(""))
					bufferHql.append(" and t.id.paramName = '" + sysParameter.getId().getParamName() + "'");
				// ���������Ͳ�ѯ
				if (sysParameter.getId().getParamType() != null && !sysParameter.getId().getParamType().equals(""))
					bufferHql.append(" and t.id.paramType = '" + sysParameter.getId().getParamType() + "'");
				// ������״̬��ѯ
				if (sysParameter.getEnable() != null && !sysParameter.getEnable().equals(""))
					bufferHql.append(" and t.enable = '" + sysParameter.getEnable() + "'");
			}
			// ���������Ϣ
			if (sortfield != null && sortType != null) {
				bufferHql.append(" order by t." + sortfield + " " + sortType + " ");
			} else {
				bufferHql.append(" order by t.id.paramType,t.paramValue asc ");
			}
			String hql = bufferHql.toString();
			List<SysParameter> sysParameterList = new ArrayList<SysParameter>();
			sysParameterList = findByHQL(hql, ud);
			int endIndex = startIndex + maxresults;
			if (endIndex > sysParameterList.size())
				endIndex = sysParameterList.size();

			list = sysParameterList.subList(startIndex, endIndex);

			log.info("��ѯ��䣺hql" + hql);
			log.info("SysParameterDaoHibernateHQLImpl.queryAllItem()�������ã���ѯ���������ļ�¼��");
		} catch (Exception e) {

			log.info("SysParameterDaoHibernateHQLImpl.queryAllItem()��ѯ���������ļ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int queryCount(SysParameter sysParameter, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("SysParameterDaoHibernateHQLImpl.queryCount()��ʼ���ã���ѯ���������ļ�¼����");

			StringBuffer bufferHql = new StringBuffer("select count(*) from SysParameter t where 1=1");

			if (sysParameter.getId() != null) {
				// ���������Ʋ�ѯ
				if (sysParameter.getId().getParamName() != null && !sysParameter.getId().getParamName().equals(""))
					bufferHql.append(" and t.id.paramName like '%" + sysParameter.getId().getParamName() + "%'");
				// ���������Ͳ�ѯ
				if (sysParameter.getId().getParamType() != null && !sysParameter.getId().getParamType().equals(""))
					bufferHql.append(" and t.id.paramType = '" + sysParameter.getId().getParamType() + "'");
				// ������״̬��ѯ
				if (sysParameter.getEnable() != null && !sysParameter.getEnable().equals(""))
					bufferHql.append(" and t.enable = '" + sysParameter.getEnable() + "'");
			}

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("��ѯ��䣺hql" + hql);
			log.info("SysParameterDaoHibernateHQLImpl.queryCount()�������ã���ѯ���������ļ�¼����");
		} catch (Exception e) {

			log.info("SysParameterDaoHibernateHQLImpl.queryCount()��ѯ���������ļ�¼���������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	public void saveItem(SysParameter sysParameter, UserData ud) throws OAException {
		try {
			log.info("SysParameterDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸���Ϣ��");

			update(sysParameter, ud);

			log.info("SysParameterDaoHibernateHQLImpl.saveItem()�������ã��޸���Ϣ��");
		} catch (Exception e) {

			log.info("SysParameterDaoHibernateHQLImpl.saveItem()�޸���Ϣ�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

	@Override
	public List queryAllItem(SysParameter sysParameter, UserData ud) throws OAException {
		List<SysParameter> list = new ArrayList<SysParameter>();
		try {
			log.info("SysParameterDaoHibernateHQLImpl.queryAllItem()��ʼ���ã���ѯ���������ļ�¼��");

			StringBuffer bufferHql = new StringBuffer(" from SysParameter t where 1=1");

			if (sysParameter.getId() != null) {
				// ���������Ʋ�ѯ
				if (sysParameter.getId().getParamType() != null && !sysParameter.getId().getParamType().equals(""))
					bufferHql.append(" and t.id.paramType = '" + sysParameter.getId().getParamType() + "'");
				// ���������Ͳ�ѯ
				if (sysParameter.getId().getParamName() != null && !sysParameter.getId().getParamName().equals(""))
					bufferHql.append(" and t.id.paramName like '%" + sysParameter.getId().getParamName() + "%'");
			}
			bufferHql.append(" and t.enable = '1' ");
			String hql = bufferHql.toString();
			list = findByHQL(hql, ud);

			log.info("��ѯ��䣺hql" + hql);
			log.info("SysParameterDaoHibernateHQLImpl.queryAllItem()�������ã���ѯ���������ļ�¼��");
		} catch (Exception e) {

			log.info("SysParameterDaoHibernateHQLImpl.queryAllItem()��ѯ���������ļ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@Override
	public SysParameter findItemByKeyValue(SysParameter sysParameter, UserData ud) throws OAException {
		SysParameter model = null;
		try {
			log.info("SysParameterDaoHibernateHQLImpl.findItemByKeyValue()��ʼ���ã���ʾһ����¼��");

			StringBuffer bufferHql = new StringBuffer(" from SysParameter t where 1=1");

			if (sysParameter.getId() != null) {
				// ���������Ʋ�ѯ
				if (sysParameter.getId().getParamType() != null && !sysParameter.getId().getParamType().equals(""))
					bufferHql.append(" and t.id.paramType = '" + sysParameter.getId().getParamType() + "'");
				// ���������Ʋ�ѯ
				if (sysParameter.getParamValue() != null && !sysParameter.getParamValue().equals(""))
					bufferHql.append(" and t.paramValue = '" + sysParameter.getParamValue() + "'");

			}

			String hql = bufferHql.toString();
			List<SysParameter> sysParameterList = new ArrayList<SysParameter>();
			sysParameterList = findByHQL(hql, ud);
			if (sysParameterList.size() > 0)
				model = (SysParameter) sysParameterList.get(0);

			log.info("SysParameterDaoHibernateHQLImpl.findItemByKeyValue()�������ã���ʾһ����¼��");
		} catch (Exception e) {

			log.info("SysParameterDaoHibernateHQLImpl.findItemByKeyValue()��ʾһ����¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

}
