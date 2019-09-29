package cn.yufu.posp.sysparam.dao.hibernate.hql;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.sysparam.domain.model.SysParam;

public class SysParamDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements SysParamDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("sysparam");

	/**
	 * @roseuid 44BAF72B0232
	 */
	public SysParamDaoHibernateHQLImpl() {

	}

	/**
	 *��KEY���һ���
	 */
	public SysParam querySysParamByKey(String newKey, UserData ud) throws OAException {
		SysParam officeSuppliesTypeModel = null;
		try {
			log.info("SysParamDaoHibernateHQLImpl.querySysParamByKey()��ʼ���ã��õ�������Ϣ��");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(SysParam.class);
			// cr.add(Restrictions.eq("jgId",newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				officeSuppliesTypeModel = (SysParam) list.get(0);

			log.info("SysParamDaoHibernateHQLImpl.querySysParamByKey()�������ã��õ�������Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysParamDaoHibernateHQLImpl.querySysParamByKey()ͨ����Ż����������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return officeSuppliesTypeModel;
	}

	/**
	 *�������
	 */
	public void saveSysParam(SysParam newModel, UserData ud) throws OAException {
			log.info("SysParamDaoHibernateHQLImpl.saveSysParam()��ʼ���ã����������");
			update(newModel, ud);
			log.info("SysParamDaoHibernateHQLImpl.saveSysParam()�������ã����������");
	}

}
