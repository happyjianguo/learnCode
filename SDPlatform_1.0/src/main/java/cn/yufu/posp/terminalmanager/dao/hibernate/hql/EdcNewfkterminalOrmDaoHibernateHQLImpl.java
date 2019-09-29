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
			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.createItem()开始调用：添加一条终端资料记录。");

			save(edcNewfkterminalOrm, ud);

			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.createItem()结束调用：添加一条终端资料记录。");
		} catch (Exception e) {

			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.createItem()添加一条终端资料记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.deleteItem()开始调用：删除终端资料记录。");

			for (int i = 0; i < keys.size(); i++) {
				delete(findItemByKey((EdcNewfkterminalOrm) keys.get(i), ud), ud);
			}

			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.deleteItem()结束调用：删除终端资料记录。");
		} catch (Exception e) {

			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.deleteItem()删除终端资料记录，出现异常。");
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
			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.findItemByKey()开始调用：查找一条终端资料记录。");

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

			log.info(" 查询语句: hql==" + hql);
			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.findItemByKey()结束调用：查找一条终端资料记录。");
		} catch (Exception e) {

			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.findItemByKey()查找一条终端资料记录，出现异常。");
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
			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.queryAllItem()开始调用】：查找符合条件的记录。");

			StringBuffer bufferHql = new StringBuffer(" from EdcNewfkterminalOrm  t where 1=1");

			if (edcNewfkterminalOrm != null) {
				// 按商户编号查询
				if (edcNewfkterminalOrm.getMerchantId() != null && !edcNewfkterminalOrm.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + edcNewfkterminalOrm.getMerchantId() + "'");

				// 按终端编号查询
				if (edcNewfkterminalOrm.getTerminalId() != null && !edcNewfkterminalOrm.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + edcNewfkterminalOrm.getTerminalId() + "'");

				if (edcNewfkterminalOrm.getLogonStatus() != null && !edcNewfkterminalOrm.getLogonStatus().equals(""))
					bufferHql.append(" and t.logonStatus = '" + edcNewfkterminalOrm.getLogonStatus() + "'");
			}

			// 添加排序信息
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

			log.info("查询语句==" + hql);

			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.queryAllItem()结束调用：查找符合条件的记录。");
		} catch (Exception e) {
			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.queryAllItem()查找符合条件的记录，出现异常。");
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
			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.queryCount()开始调用：查询符合条件的终端资料数量。");

			StringBuffer bufferHql = new StringBuffer("select count(*) from EdcNewfkterminalOrm  t where 1=1");

			if (edcNewfkterminalOrm != null) {
				// 按商户编号查询
				if (edcNewfkterminalOrm.getMerchantId() != null && !edcNewfkterminalOrm.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + edcNewfkterminalOrm.getMerchantId() + "'");

				// 按终端编号查询
				if (edcNewfkterminalOrm.getTerminalId() != null && !edcNewfkterminalOrm.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + edcNewfkterminalOrm.getTerminalId() + "'");
				
				if (edcNewfkterminalOrm.getLogonStatus() != null && !edcNewfkterminalOrm.getLogonStatus().equals(""))
					bufferHql.append(" and t.logonStatus = '" + edcNewfkterminalOrm.getLogonStatus() + "'");
			}
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());

			log.info("查询语句==" + hql);
			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.queryCount()出现异常：查询符合条件的终端资料数量。");
		} catch (Exception e) {

			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.queryCount()查询符合条件的终端资料数量，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	public void saveItem(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.saveItem()开始调用：保存一条终端资料记录。");

			update(edcNewfkterminalOrm, ud);

			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.saveItem()结束调用：保存一条终端资料记录。");
		} catch (Exception e) {

			log.info("EdcNewfkterminalOrmDaoHibernateHQLImpl.saveItem()保存一条终端资料记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

}
