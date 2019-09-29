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
	 * 查询所有对象
	 */
	public List queryAll(OperateLog newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("LogDaoHibernateHQLImpl.querySum()开始调用：通过查询条件分页查询信息。");
			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer(" from OperateLog o where 1=1");

			if (newQueryModel.getType() != null && !newQueryModel.getType().equals(""))
				bufferHql.append(" and o.type = '" + newQueryModel.getType() + "'");

			if (newQueryModel.getCreateDate() != null && !newQueryModel.getCreateDate().equals(""))
				bufferHql.append(" and o.createDate = '" + newQueryModel.getCreateDate() + "'");

			// 添加排序信息
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					bufferHql.append(" order by "+sortfield+" asc");
				else
					bufferHql.append(" order by "+sortfield+" desc");
			} else {
				bufferHql.append(" order by logId ");
			}

			list = findByHQL(bufferHql.toString(), startIndex, maxresults, ud);

			log.info("LogDaoHibernateHQLImpl.querySum()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("LogDaoHibernateHQLImpl.querySum()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 * 查询记录总数
	 */
	public int querySum(OperateLog queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("LogDaoHibernateHQLImpl.querySum()开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from OperateLog o where 1=1");

			// 按商户编号查询
			if (queryModel.getType() != null && !queryModel.getType().equals(""))
				bufferHql.append(" and o.type = '" + queryModel.getType() + "'");

			// 按商户状态查询
			if (queryModel.getCreateDate() != null && !queryModel.getCreateDate().equals(""))
				bufferHql.append(" and o.createDate = '" + queryModel.getCreateDate() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("LogDaoHibernateHQLImpl.querySum()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("LogDaoHibernateHQLImpl.querySum()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;
	}



	/**
	 *显示一条记录
	 */
	public OperateLog findItem(String newKey, UserData ud) throws OAException {
		OperateLog model = null;
		try {
			log.info("LogDaoHibernateHQLImpl.findItem()开始调用：显示一条记录。");
			List list = null;
			Session session = getSession();
			list = findByHQL("from OperateLog o where 1=1 and logId = "+newKey , ud);
			if (list.size() > 0)
				model = (OperateLog) list.get(0);
			
			log.info("LogDaoHibernateHQLImpl.findItem()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("LogDaoHibernateHQLImpl.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

		return model;
	}

	/**
	 *新建一条记录
	 */
	public void createItem(OperateLog newModel, UserData ud) throws OAException {
		
		try {
			log.info("LogDaoHibernateHQLImpl.createItem()开始调用：保存一个信息。");

			newModel.setCreateOper(ud.getUserId());
			newModel.setCreateDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
			newModel.setCreateTime(new SimpleDateFormat("HHmmss").format(new Date()));
			save(newModel, ud);
			log.info("LogDaoHibernateHQLImpl.createItem()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("LogDaoHibernateHQLImpl.createItem()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}
	}

}
