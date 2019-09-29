package cn.yufu.posp.bank.dao.hibernate.hql;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;

public class BanktypeDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements BanktypeDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("bankInfo");

	public BanktypeDaoHibernateHQLImpl() {

	}

	/**
	 * 查询所有对象
	 */
	public List queryAll(Banktype newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("BanktypeDaoHibernateHQLImpl.querySum()开始调用：通过查询条件分页查询信息。");
			DetachedCriteria dcr = DetachedCriteria.forClass(Banktype.class);

			// 按商户编号查询
			if (newQueryModel.getTypeName() != null && !newQueryModel.getTypeName().equals(""))
				dcr.add(Restrictions.like("typeName", "%" + newQueryModel.getTypeName() + "%"));
			// 按银行类型
			if (newQueryModel.getBankType() != null && !newQueryModel.getBankType().equals(""))
				dcr.add(Restrictions.like("bankType",newQueryModel.getBankType() + "%"));

			// 添加排序信息
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("bankType"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			log.info("BanktypeDaoHibernateHQLImpl.querySum()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeDaoHibernateHQLImpl.querySum()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException("查询所有银行类型时出现异常");
		}
		return list;
	}

	/**
	 * 查询所有对象的个数
	 */
	public int querySum(Banktype queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("BanktypeDaoHibernateHQLImpl.querySum()开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from Banktype o where 1=1");

			// 按商户编号查询
			if (queryModel.getTypeName() != null && !queryModel.getTypeName().equals(""))
				bufferHql.append(" and o.typeName like '%" + queryModel.getTypeName() + "%'");

			// 按商户状态查询
			if (queryModel.getBankType() != null && !queryModel.getBankType().equals(""))
				bufferHql.append(" and o.bankType like '" + queryModel.getBankType() + "%'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("BanktypeDaoHibernateHQLImpl.querySum()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeDaoHibernateHQLImpl.querySum()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询银行类型个数时出现异常");
		}
		return count;
	}

	/**
	 * 删除一条记录
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("BanktypeDaoHibernateHQLImpl.deleteItem()调用开始：删除一条记录。");

			// 这种删法，会连子表一同删除

			for (int i = 0; i < newKeys.size(); i++) {
				Banktype m = null;
				try {
					m = findItem(new String(newKeys.get(i) + ""), ud);
				} catch (Exception e) {
					throw new OAException("查找要删除信息时出现异常！");
				}
				if (m != null) {
					StringBuffer bufferHql = new StringBuffer("delete from Banktype  where bank_type='" + m.getBankType()
							+ "'");
					String hql = bufferHql.toString();
					saveOrUpdateOrDeleteBySQL(hql, ud);
				} else {
					throw new OAException("您要删除的信息不存在！");
				}
			}
			log.info("BanktypeDaoHibernateHQLImpl.deleteItem()结束调用：删除一条记录。");
		} catch (Exception e) {
			log.error("BanktypeDaoHibernateHQLImpl.deleteItem()删除一条记录，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
	}

	/**
	 *显示一条记录
	 */
	public Banktype findItem(String newKey, UserData ud) throws OAException {
		Banktype model = null;
		try {
			log.info("BanktypeDaoHibernateHQLImpl.findItem()开始调用：显示一条记录。");
			List list = null;
			Session session = getSession();
			// 基本信息
			Criteria cr = session.createCriteria(Banktype.class);
			cr.add(Restrictions.eq("bankType", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0) {
				model = (Banktype) list.get(0);
			}
			log.info("BanktypeDaoHibernateHQLImpl.findItem()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeDaoHibernateHQLImpl.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("查询银行类型时抛异常！");
		}

		return model;
	}

	/**
	 *修改一条记录
	 */
	public void saveItem(Banktype newModel, UserData ud) throws OAException {
		try {
			log.info("BanktypeDaoHibernateHQLImpl.saveItem()开始调用：修改一条记录。");
			try {
				update(newModel, ud);
				log.info("BanktypeDaoHibernateHQLImpl.saveItem()结束调用：修改一条记录。");
			} catch (Exception e) {
				throw new OAException("修改银行类型时出现异常！");
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("BanktypeDaoHibernateHQLImpl.saveItem()修改一条记录出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());
			throw new OAException(e.getMessage());

		}
	}

	/**
	 *新建一条记录
	 */
	public void createItem(Banktype newModel, UserData ud) throws OAException {

		// 唯一性验证
		try {
			log.info("BanktypeDaoHibernateHQLImpl.createItem()开始调用：验证信息是否已经存在。");
			Banktype sm = findItem(newModel.getBankType(), ud);
			log.info("BanktypeDaoHibernateHQLImpl.createItem()结束调用：验证信息是否已经存在。");
			if (sm != null) {
				throw new OAException("银行类型:" + newModel.getBankType() + " 的记录已经在数据库中存在，请重新输入！");
			}
		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}

		try {
			log.info("BanktypeDaoHibernateHQLImpl.createItem()开始调用：保存一个信息。");

			try {
				save(newModel, ud);
			} catch (Exception e) {
				throw new OAException("增加银行类型时出现异常");
			}

			log.info("BanktypeDaoHibernateHQLImpl.createItem()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("BanktypeDaoHibernateHQLImpl.createItem()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("增加银行类型时出现异常");
		}
	}

	
}
