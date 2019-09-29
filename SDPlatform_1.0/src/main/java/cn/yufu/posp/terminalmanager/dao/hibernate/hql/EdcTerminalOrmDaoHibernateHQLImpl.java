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
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.createItem()开始调用：添加一条终端资料记录。");

			save(edcTerminalOrm, ud);

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.createItem()结束调用：添加一条终端资料记录。");
		} catch (Exception e) {

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.createItem()添加一条终端资料记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.deleteItem()开始调用：删除终端资料记录。");

			for (int i = 0; i < keys.size(); i++) {
				delete(findItemByKey((EdcTerminalOrm) keys.get(i), ud), ud);
			}

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.deleteItem()结束调用：删除终端资料记录。");
		} catch (Exception e) {

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.deleteItem()删除终端资料记录，出现异常。");
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
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.findItemByKey()开始调用：查找一条终端资料记录。");

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

			log.info(" 查询语句: hql==" + hql);
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.findItemByKey()结束调用：查找一条终端资料记录。");
		} catch (Exception e) {

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.findItemByKey()查找一条终端资料记录，出现异常。");
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
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.queryAllItem()开始调用】：查找符合条件的记录。");

			StringBuffer bufferHql = new StringBuffer(" from EdcTerminalOrm  t where 1=1");

			if (edcTerminalOrm != null) {
				// 按商户编号查询
				if (edcTerminalOrm.getMerchantId() != null && !edcTerminalOrm.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + edcTerminalOrm.getMerchantId() + "'");

				// 按终端编号查询
				if (edcTerminalOrm.getTerminalId() != null && !edcTerminalOrm.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + edcTerminalOrm.getTerminalId() + "'");
				
				// 按银行终端号查询
				if (edcTerminalOrm.getBankTerminalId() != null && !edcTerminalOrm.getBankTerminalId().equals(""))
					bufferHql.append(" and t.bankTerminalId = '" + edcTerminalOrm.getBankTerminalId() + "'");

				if (edcTerminalOrm.getLogonStatus() != null && !edcTerminalOrm.getLogonStatus().equals(""))
					bufferHql.append(" and t.logonStatus = '" + edcTerminalOrm.getLogonStatus() + "'");
			}

			// 添加排序信息
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

			log.info("查询语句==" + hql);

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.queryAllItem()结束调用：查找符合条件的记录。");
		} catch (Exception e) {
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.queryAllItem()查找符合条件的记录，出现异常。");
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
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.queryCount()开始调用：查询符合条件的终端资料数量。");

			StringBuffer bufferHql = new StringBuffer("select count(*) from EdcTerminalOrm  t where 1=1");

			if (edcTerminalOrm != null) {
				// 按商户编号查询
				if (edcTerminalOrm.getMerchantId() != null && !edcTerminalOrm.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + edcTerminalOrm.getMerchantId() + "'");

				// 按终端编号查询
				if (edcTerminalOrm.getTerminalId() != null && !edcTerminalOrm.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + edcTerminalOrm.getTerminalId() + "'");

				if (edcTerminalOrm.getLogonStatus() != null && !edcTerminalOrm.getLogonStatus().equals(""))
					bufferHql.append(" and t.logonStatus = '" + edcTerminalOrm.getLogonStatus() + "'");
			}
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());

			log.info("查询语句==" + hql);
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.queryCount()出现异常：查询符合条件的终端资料数量。");
		} catch (Exception e) {

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.queryCount()查询符合条件的终端资料数量，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	public void saveItem(EdcTerminalOrm edcTerminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.saveItem()开始调用：保存一条终端资料记录。");

			update(edcTerminalOrm, ud);

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.saveItem()结束调用：保存一条终端资料记录。");
		} catch (Exception e) {

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.saveItem()保存一条终端资料记录，出现异常。");
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
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.findItemByKey()开始调用：查找一条终端资料记录。");

			StringBuffer bufferHql = new StringBuffer(" from EdcTerminalOrm  t where ");
			bufferHql.append(" t.bankMerchantId='" + merchantId + "'");
			bufferHql.append(" and t.bankTerminalId='" + terminalId + "'");

			String hql = bufferHql.toString();
			edcTerminalOrmList = findByHQL(hql, null);

			if (edcTerminalOrmList.size() > 0)
				model = (EdcTerminalOrm) edcTerminalOrmList.get(0);

			log.info(" 查询语句: hql==" + hql);
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.findItemByKey()结束调用：查找一条终端资料记录。");
		} catch (Exception e) {

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.findItemByKey()查找一条终端资料记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	//校验联合主键唯一性PKEY
	public String checkEdcTerminalOrmPKEY(String merchantId,String terminalId,String moduleId) throws OAException {
		log.info("EdcTerminalOrmDaoHibernateHQLImpl.checkEdcTerminalOrmPKEY()开始调用：校验联合主键唯一性PKEY。");
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
			log.info(" 查询语句: hql==" + hql);
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.checkEdcTerminalOrmPKEY()结束校验联合主键唯一性PKEY。");
		} catch (Exception e) {

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.checkEdcTerminalOrmPKEY()校验联合主键唯一性PKEY，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return checkFlag;
	}
	
	
	//校验索引唯一性ORM
	public String checkEdcTerminalOrmORM(String bankMerchantId,String bankTerminalId,String moduleId) throws OAException {
		log.info("EdcTerminalOrmDaoHibernateHQLImpl.checkEdcTerminalOrmPKEY()开始调用：校验索引唯一性ORM。");
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
			log.info(" 查询语句: hql==" + hql);
			log.info("EdcTerminalOrmDaoHibernateHQLImpl.checkEdcTerminalOrmPKEY()结束校验索引唯一性ORM。");
		} catch (Exception e) {

			log.info("EdcTerminalOrmDaoHibernateHQLImpl.checkEdcTerminalOrmPKEY()校验索引唯一性ORM，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return checkFlag;
	}
}
