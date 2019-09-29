package cn.yufu.posp.cardBinArea.dao.hibernate.hql;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.Map;
import cn.yufu.posp.cardBinArea.domain.model.CardBinArea;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;

public class CardBinAreaDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl
		implements CardBinAreaDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("CardBinArea");

	public CardBinAreaDaoHibernateHQLImpl() {

	}

	/**
	 * 查询所有对象
	 */
	public List queryAll(CardBinArea newQueryModel, int startIndex,
			int maxresults, String sortfield, String sortType, UserData ud)
			throws OAException {
		List list = null;
		try {
			log
					.info("CardBinAreaDaoHibernateHQLImpl.querySum()开始调用：通过查询条件分页查询信息。");
			DetachedCriteria dcr = DetachedCriteria.forClass(CardBinArea.class);

			// 按商户编号查询
			if (newQueryModel.getCardBin() != null
					&& !newQueryModel.getCardBin().equals(""))
				dcr.add(Restrictions.eq("cardBin", newQueryModel.getCardBin()));
			// 按银行类型
			if (newQueryModel.getAreaCode() != null
					&& !newQueryModel.getAreaCode().equals(""))
				dcr.add(Restrictions
						.eq("areaCode", newQueryModel.getAreaCode()));
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
				dcr.addOrder(Order.asc("updateDate"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			log
					.info("CardBinAreaDaoHibernateHQLImpl.querySum()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log
					.error("CardBinAreaDaoHibernateHQLImpl.querySum()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException("查询所有区域卡卡BIN时出现异常");
		}
		return list;
	}

	/**
	 * 查询所有对象的个数
	 */
	public int querySum(CardBinArea queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log
					.info("CardBinAreaDaoHibernateHQLImpl.querySum()开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer(
					"select count(*) from CardBinArea o where 1=1");

			// 按商户编号查询
			if (queryModel.getCardBin() != null
					&& !queryModel.getCardBin().equals(""))
				bufferHql.append(" and o.cardBin = '" + queryModel.getCardBin()
						+ "'");

			// 按商户状态查询
			if (queryModel.getAreaCode() != null
					&& !queryModel.getAreaCode().equals(""))
				bufferHql.append(" and o.areaCode = '"
						+ queryModel.getAreaCode() + "'");
			
			// 按商户状态查询
			if (queryModel.getStatus() != null
					&& !queryModel.getStatus().equals(""))
				bufferHql.append(" and o.status = '"
						+ queryModel.getStatus() + "'");
			
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log
					.info("CardBinAreaDaoHibernateHQLImpl.querySum()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaDaoHibernateHQLImpl.querySum()调用时出现异常。");
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
			log.info("CardBinAreaDaoHibernateHQLImpl.deleteItem()调用开始：删除一条记录。");

			// 这种删法，会连子表一同删除

			for (int i = 0; i < newKeys.size(); i++) {
				CardBinArea m = null;
				try {
					m = findItem(new String(newKeys.get(i) + ""), ud);
				} catch (Exception e) {
					throw new OAException("查找要删除信息时出现异常！");
				}
				if (m != null) {
					StringBuffer bufferHql = new StringBuffer(
							"delete from CARD_BIN_AREA  where card_bin='"
									+ m.getCardBin() + "' ");
					String hql = bufferHql.toString();
					saveOrUpdateOrDeleteBySQL(hql, ud);
				} else {
					throw new OAException("您要删除的信息不存在！");
				}
			}
			log.info("CardBinAreaDaoHibernateHQLImpl.deleteItem()结束调用：删除一条记录。");
		} catch (Exception e) {
			log
					.error("CardBinAreaDaoHibernateHQLImpl.deleteItem()删除一条记录，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
	}

	/**
	 *显示一条记录
	 */
	public CardBinArea findItem(String newKey, UserData ud) throws OAException {
		CardBinArea model = null;
		try {
			log.info("CardBinAreaDaoHibernateHQLImpl.findItem()开始调用：显示一条记录。");
			List list = null;
			Session session = getSession();
			// 基本信息
			Criteria cr = session.createCriteria(CardBinArea.class);
			if(newKey!=null&&!"".equals(newKey)){				
				cr.add(Restrictions.eq("cardBin", newKey));
			}
			list = findByCriteria(cr, ud);
			if (list.size() > 0) {
				model = (CardBinArea) list.get(0);
			}
			log.info("CardBinAreaDaoHibernateHQLImpl.findItem()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaDaoHibernateHQLImpl.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("查询区域卡卡BIN时抛异常！");
		}

		return model;
	}

	/**
	 *修改一条记录
	 */
	public void saveItem(CardBinArea newModel, UserData ud) throws OAException {
		try {
			log.info("CardBinAreaDaoHibernateHQLImpl.saveItem()开始调用：修改一条记录。");
			log.info("修改 cardBinArea=" + newModel.getCardBin());
			try {
				update(newModel, ud);
				log
						.info("CardBinAreaDaoHibernateHQLImpl.saveItem()结束调用：修改一条记录。");
			} catch (Exception e) {
				throw new OAException("修改区域卡卡BIN时出现异常！");
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("CardBinAreaDaoHibernateHQLImpl.saveItem()修改一条记录出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());
			throw new OAException(e.getMessage());

		}
	}

	/**
	 *新建一条记录
	 */
	public void createItem(CardBinArea newModel, UserData ud)
			throws OAException {
		try {
			log.info("CardBinAreaDaoHibernateHQLImpl.createItem()开始调用：保存一个信息。");

			try {
				save(newModel, ud);
			} catch (Exception e) {
				throw new OAException("增加联行记录时出现异常");
			}

			log.info("CardBinAreaDaoHibernateHQLImpl.createItem()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log
					.error("CardBinAreaDaoHibernateHQLImpl.createItem()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("增加联行记录时出现异常");
		}
	}

	@Override
	public void update(Object entity, UserData ud) {
		CardBinArea bi = (CardBinArea) entity;
		String sql = "update CardBinArea set areaCode='" + bi.getAreaCode()
				+ "', updateOper='" + bi.getUpdateOper() + "',updateDate='"
				+ bi.getUpdateDate() + "',status='" + bi.getStatus() + "'";
		sql=sql+" where cardBin='"+bi.getCardBin()+"' ";
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
			log.info("CardBinAreaDaoHibernateHQLImpl.getAreaCodeMap()开始调用。");
			String sql = " select area_code,area_name FROM area_code_info";
			list = findBySQL(sql, null);
			Object[] obj = null;
			for (int i = 0; i < list.size(); i++) {
				obj = (Object[]) list.get(i);
				areaMap.put((String) obj[0], (String) obj[1]);
			}
			log.info("CardBinAreaDaoHibernateHQLImpl.getAreaCodeMap()结束调用。");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("CardBinAreaDaoHibernateHQLImpl.getAreaCodeMap()查询城市信息，出现异常。");
			throw new OAException("getAreaCodeMap出现异常");
		}
		return areaMap;
	}
	/**
	 * 验证商户编号是否唯一
	 */
	public CardBinArea findItemById(String newKey, UserData ud) throws OAException {
		CardBinArea model = null;
		try {
			log.info("CardBinAreaDaoHibernateHQLImpl.findItemById()开始调用。");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(CardBinArea.class);
			cr.add(Restrictions.eq("cardBin", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (CardBinArea) list.get(0);
			log.info("CardBinAreaDaoHibernateHQLImpl.findItemById()结束调用。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaDaoHibernateHQLImpl.findItemById()出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("验证卡BIN唯一性时出错！");
		}
		return model;
	}
}
