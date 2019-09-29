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
import cn.yufu.posp.terminalmanager.domain.model.EdcSwitch;

public class EdcSwitchDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements EdcSwitchDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("edcSwitch");

	/**
	 * @roseuid 44BAF72B0232
	 */
	public EdcSwitchDaoHibernateHQLImpl() {
	}

	public void createItem(EdcSwitch edcSwitch, UserData ud) throws OAException {
		try {
			log.info("EdcSwitchDaoHibernateHQLImpl.createItem()开始调用：保存一个终端转换信息。");

			save(edcSwitch, ud);

			log.info("EdcSwitchDaoHibernateHQLImpl.createItem()结束调用：保存一个终端转换信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcSwitchDaoHibernateHQLImpl.createItem()保存一个终端转换信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcSwitchDaoHibernateHQLImpl.deleteItem()开始调用：删除终端转换信息。");

			for (int i = 0; i < keys.size(); i++) {

				delete(findItemByKey((EdcSwitch) keys.get(i), ud), ud);
			}

			log.info("EdcSwitchDaoHibernateHQLImpl.deleteItem()结束调用：删除终端转换信息。");
		} catch (Exception e) {
			log.info("EdcSwitchDaoHibernateHQLImpl.deleteItem()删除终端转换信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public EdcSwitch findItemByKey(EdcSwitch edcSwitch, UserData ud) throws OAException {
		EdcSwitch model = null;
		try {
			log.info("EdcSwitchDaoHibernateHQLImpl.findItemByKey()开始调用：显示一条记录。");

			StringBuffer bufferHql = new StringBuffer(" from EdcSwitch  t where 1=1");

			if (edcSwitch.getId() != null) {
				// 按商户编号查询
				// if (edcSwitch.getId().getMerchantId()!= null&&
				// !edcSwitch.getId().getMerchantId().equals(""))
				bufferHql.append(" and t.id.merchantId = '" + edcSwitch.getId().getMerchantId() + "'");
				bufferHql.append(" and t.id.bankType = '" + edcSwitch.getId().getBankType() + "'");
				bufferHql.append(" and t.id.terminalId = '" + edcSwitch.getId().getTerminalId() + "'");
				bufferHql.append(" and t.othTerminalId = '" + edcSwitch.getOthTerminalId() + "'");
			}

			String hql = bufferHql.toString();
			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				model = (EdcSwitch) list.get(0);

			log.info("查询语句==" + hql);
			log.info("符合条件的记录数==" + list.size());
			log.info("EdcSwitchDaoHibernateHQLImpl.findItemByKey()开始调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcSwitchDaoHibernateHQLImpl.findItemByKey()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public List queryAllItem(EdcSwitch edcSwitch, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List<EdcSwitch> list = new ArrayList<EdcSwitch>();
		List<EdcSwitch> ecSwitchList = new ArrayList<EdcSwitch>();
		try {
			log.info("EdcSwitchDaoHibernateHQLImpl.queryAllItem()开始调用：查询符合条件的数量。");

			StringBuffer bufferHql = new StringBuffer(" from EdcSwitch  t where 1=1");

			if (edcSwitch.getId() != null) {
				// 按商户编号查询
				if (edcSwitch.getId().getMerchantId() != null && !edcSwitch.getId().getMerchantId().equals(""))
					bufferHql.append(" and t.id.merchantId = '" + edcSwitch.getId().getMerchantId() + "'");

				// 按银行类型查询
				if (edcSwitch.getId().getBankType() != null && !edcSwitch.getId().getBankType().equals(""))
					bufferHql.append(" and t.id.bankType = '" + edcSwitch.getId().getBankType() + "'");
			}

			// 添加排序信息
			if (sortfield != null && sortType != null) {
				bufferHql.append(" order by t." + sortfield + " " + sortType + " ");
			} else {
				bufferHql.append(Order.desc(" order by id.merchantId "));
			}

			String hql = bufferHql.toString();
			list = findByHQL(hql, ud);
			ecSwitchList = findByHQL(hql, ud);
			int endIndex = startIndex + maxresults;
			if (endIndex > ecSwitchList.size())
				endIndex = ecSwitchList.size();

			list = ecSwitchList.subList(startIndex, endIndex);

			log.info("EdcSwitchDaoHibernateHQLImpl.queryAllItem()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcSwitchDaoHibernateHQLImpl.queryAllItem()查询符合条件的数量，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int queryCount(EdcSwitch edcSwitch, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("EdcSwitchDaoHibernateHQLImpl.queryCount()开始调用：查询符合条件的数量。");

			StringBuffer bufferHql = new StringBuffer("select count(*) from EdcSwitch  t where 1=1");

			if (edcSwitch.getId() != null) {
				// 按商户编号查询
				if (edcSwitch.getId().getMerchantId() != null && !edcSwitch.getId().getMerchantId().equals(""))
					bufferHql.append(" and t.id.merchantId = '" + edcSwitch.getId().getMerchantId() + "'");

				// 按银行类型查询
				if (edcSwitch.getId().getBankType() != null && !edcSwitch.getId().getBankType().equals(""))
					bufferHql.append(" and t.id.bankType = '" + edcSwitch.getId().getBankType() + "'");
			}

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());

			log.info("EdcSwitchDaoHibernateHQLImpl.queryCount()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcSwitchDaoHibernateHQLImpl.queryCount()查询符合条件的数量，出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	public void saveItem(EdcSwitch edcSwitch, UserData ud) throws OAException {
		try {
			log.info("EdcSwitchDaoHibernateHQLImpl.saveItem()开始调用：保存 、修改终端转换。");

			update(edcSwitch, ud);

			log.info("EdcSwitchDaoHibernateHQLImpl.saveItem()结束调用：保存 、修改终端转换。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcSwitchDaoHibernateHQLImpl.saveItem()保存 、修改终端转换，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

}
