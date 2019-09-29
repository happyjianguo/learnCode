package cn.yufu.posp.cardBinArea.dao.hibernate.hql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.bank.domain.model.BankInfoId;
import cn.yufu.posp.cardBinArea.domain.model.EdcCardBinArea;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;

public class EdcCardBinAreaDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl
		implements EdcCardBinAreaDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("EdcCardBinArea");

	public EdcCardBinAreaDaoHibernateHQLImpl() {

	}

	/**
	 * 查询所有对象
	 */
	public List queryAll(EdcCardBinArea newQueryModel, int startIndex,
			int maxresults, String sortfield, String sortType, UserData ud)
			throws OAException {
		List list = null;
		try {
			log
					.info("EdcCardBinAreaDaoHibernateHQLImpl.querySum()开始调用：通过查询条件分页查询信息。");
			DetachedCriteria dcr = DetachedCriteria.forClass(EdcCardBinArea.class);

			if (newQueryModel.getCardBin() != null
					&& !newQueryModel.getCardBin().equals(""))
				dcr.add(Restrictions.eq("cardBin", newQueryModel.getCardBin()));
			
			if (newQueryModel.getMerchantId() != null
					&& !newQueryModel.getMerchantId().equals(""))
				dcr.add(Restrictions
						.eq("merchantId", newQueryModel.getMerchantId()));
			
			if (newQueryModel.getTerminalId() != null
					&& !newQueryModel.getTerminalId().equals(""))
				dcr.add(Restrictions
						.eq("terminalId", newQueryModel.getTerminalId()));
			
			if (newQueryModel.getStatus() != null
					&& !newQueryModel.getStatus().equals(""))
				dcr.add(Restrictions
						.eq("status", newQueryModel.getStatus()));			

			// 添加排序信息
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("cardBin"));
				dcr.addOrder(Order.asc("merchantId"));
				dcr.addOrder(Order.asc("terminalId"));

			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			log
					.info("EdcCardBinAreaDaoHibernateHQLImpl.querySum()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log
					.error("EdcCardBinAreaDaoHibernateHQLImpl.querySum()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException("查询所有区域卡卡BIN时出现异常");
		}
		return list;
	}

	/**
	 * 查询所有对象的个数
	 */
	public int querySum(EdcCardBinArea queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log
					.info("EdcCardBinAreaDaoHibernateHQLImpl.querySum()开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer(
					"select count(*) from EdcCardBinArea o where 1=1");

			if (queryModel.getCardBin() != null
					&& !queryModel.getCardBin().equals(""))
				bufferHql.append(" and o.cardBin = '" + queryModel.getCardBin()
						+ "'");

			if (queryModel.getMerchantId() != null
					&& !queryModel.getMerchantId().equals(""))
				bufferHql.append(" and o.merchantId = '"
						+ queryModel.getMerchantId() + "'");

			if (queryModel.getTerminalId() != null
					&& !queryModel.getTerminalId().equals(""))
				bufferHql.append(" and o.terminalId = '"
						+ queryModel.getTerminalId() + "'");
			
			
			if (queryModel.getStatus() != null
					&& !queryModel.getStatus().equals(""))
				bufferHql.append(" and o.status = '"
						+ queryModel.getStatus() + "'");
			
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log
					.info("EdcCardBinAreaDaoHibernateHQLImpl.querySum()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardBinAreaDaoHibernateHQLImpl.querySum()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询区域卡卡BIN个数时出现异常");
		}
		return count;
	}

	/**
	 * 删除一条记录
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("EdcCardBinAreaDaoHibernateHQLImpl.deleteItem()调用开始：删除一条记录。");

			// 这种删法，会连子表一同删除

			for (int i = 0; i < newKeys.size(); i++) {
				EdcCardBinArea m = null;
				try {
					m = findItem(new String(newKeys.get(i) + ""), ud);
				} catch (Exception e) {
					throw new OAException("查找要删除信息时出现异常！");
				}
				if (m != null) {
					StringBuffer bufferHql = new StringBuffer(
							"delete from EDC_CARD_BIN_AREA  where card_bin='"
									+ m.getCardBin() + "' and merchant_id='"
									+ m.getMerchantId() + "' and terminal_id='"
									+ m.getTerminalId() + "'");
					String hql = bufferHql.toString();
					saveOrUpdateOrDeleteBySQL(hql, ud);
				} else {
					throw new OAException("您要删除的信息不存在！");
				}
			}
			log.info("EdcCardBinAreaDaoHibernateHQLImpl.deleteItem()结束调用：删除一条记录。");
		} catch (Exception e) {
			log
					.error("EdcCardBinAreaDaoHibernateHQLImpl.deleteItem()删除一条记录，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
	}

	/**
	 *显示一条记录
	 */
	public EdcCardBinArea findItem(String newKey, UserData ud) throws OAException {
		EdcCardBinArea model = null;
		try {
			log.info("BankInfoDaoHibernateHQLImpl.findItem()开始调用：显示一条记录。");
			List list = null;
			Session session = getSession();
			// 基本信息
			Criteria cr = session.createCriteria(EdcCardBinArea.class);
			String[] params = newKey.split(",");
			log.info("param:" + params[0] + "\t" + params[1]+ "\t" + params[2]);
//			if (params[0] != null && !"".equals(params[0])) {
//				int i = params[0].length();
//				if (i < 11) {
//					for (int j = 1; j <= 11 - i; j++) {
//						params[0] += " ";
//					}
//				}
//			}
//			if (params[1] != null && !"".equals(params[1])) {
//				int i = params[1].length();
//				if (i < 2) {
//					for (int j = 1; j <= 2 - i; j++) {
//						params[1] += " ";
//					}
//				}
//			}
			cr.add(Restrictions.eq("cardBin", params[0]));
			cr.add(Restrictions.eq("merchantId", params[1]));
			cr.add(Restrictions.eq("terminalId", params[2]));

			list = findByCriteria(cr, ud);
			if (list.size() > 0) {
				model = (EdcCardBinArea) list.get(0);
			}
			log.info("BankInfoDaoHibernateHQLImpl.findItem()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoDaoHibernateHQLImpl.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("查询联行基本信息时抛异常！");
		}

		return model;
	}


	/**
	 *修改一条记录
	 */
	public void saveItem(EdcCardBinArea newModel, UserData ud) throws OAException {
		try {
			log.info("EdcCardBinAreaDaoHibernateHQLImpl.saveItem()开始调用：修改一条记录。");
			log.info("修改 EdcCardBinArea=" + newModel.getCardBin());
			try {
				update(newModel, ud);
				log
						.info("EdcCardBinAreaDaoHibernateHQLImpl.saveItem()结束调用：修改一条记录。");
			} catch (Exception e) {
				throw new OAException("修改区域卡卡BIN时出现异常！");
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("EdcCardBinAreaDaoHibernateHQLImpl.saveItem()修改一条记录出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());
			throw new OAException(e.getMessage());

		}
	}

	/**
	 *新建一条记录
	 */
	public void createItem(EdcCardBinArea newModel, UserData ud)
			throws OAException {
		try {
			log.info("EdcCardBinAreaDaoHibernateHQLImpl.createItem()开始调用：保存一个信息。");

			try {
				save(newModel, ud);
			} catch (Exception e) {
				throw new OAException("增加联行记录时出现异常");
			}

			log.info("EdcCardBinAreaDaoHibernateHQLImpl.createItem()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log
					.error("EdcCardBinAreaDaoHibernateHQLImpl.createItem()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("增加联行记录时出现异常");
		}
	}

	@Override
	public void update(Object entity, UserData ud) {
		EdcCardBinArea bi = (EdcCardBinArea) entity;
		String sql = "update EdcCardBinArea set updateOper='"
				+ bi.getUpdateOper() + "',updateDate='" + bi.getUpdateDate()
				+ "',status='" + bi.getStatus() + "'";
		sql = sql + " where cardBin='" + bi.getCardBin() + "' and merchantId='"
				+ bi.getMerchantId() + "' and terminalId='"
				+ bi.getTerminalId() + "' ";
		log.info("修改sql==" + sql);
		bulkUpdate(sql, ud);
	}
		
	/**
	 * 获取地区码表MAP
	 * @return
	 * @throws OAException
	 */
	public Map getAreaCodeMap() throws OAException {
		List list = null;
		Map<String, String> areaMap = new HashMap<String, String>();
		try {
			log.info("EdcCardBinAreaDaoHibernateHQLImpl.getAreaCodeMap()开始调用。");
			String sql = " select area_code,area_name FROM area_code_info";
			list = findBySQL(sql, null);
			Object[] obj = null;
			for (int i = 0; i < list.size(); i++) {
				obj = (Object[]) list.get(i);
				areaMap.put((String) obj[0], (String) obj[1]);
			}
			log.info("EdcCardBinAreaDaoHibernateHQLImpl.getAreaCodeMap()结束调用。");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("EdcCardBinAreaDaoHibernateHQLImpl.getAreaCodeMap()查询城市信息，出现异常。");
			throw new OAException("getAreaCodeMap出现异常");
		}
		return areaMap;
	}
	/**
	 * 验证商户编号是否唯一
	 */
	public EdcCardBinArea findItemById(String newKey,String merchantId,String terminalId, UserData ud) throws OAException {
		EdcCardBinArea model = null;
		try {
			log.info("EdcCardBinAreaDaoHibernateHQLImpl.findItemById()开始调用。");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(EdcCardBinArea.class);
			cr.add(Restrictions.eq("cardBin", newKey));
			cr.add(Restrictions.eq("merchantId", merchantId));
			cr.add(Restrictions.eq("terminalId", terminalId));

			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (EdcCardBinArea) list.get(0);
			log.info("EdcCardBinAreaDaoHibernateHQLImpl.findItemById()结束调用。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardBinAreaDaoHibernateHQLImpl.findItemById()出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("验证卡BIN唯一性时出错！");
		}
		return model;
	}
}
