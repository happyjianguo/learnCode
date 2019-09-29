package cn.yufu.posp.bank.dao.hibernate.hql;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.bank.domain.model.BankInfoId;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;

public class BankInfoDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements BankInfoDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("bankInfo");

	public BankInfoDaoHibernateHQLImpl() {

	}

	/**
	 * 查询所有对象
	 */
	public List queryAll(BankInfoId newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("BankInfoDaoHibernateHQLImpl.querySum()开始调用：通过查询条件分页查询信息。");
			DetachedCriteria dcr = DetachedCriteria.forClass(BankInfoId.class);

			// 按商户编号查询
			if (newQueryModel.getQueryBankId() != null && !newQueryModel.getQueryBankId().equals(""))
				dcr.add(Restrictions.like("bankId", "%" + newQueryModel.getQueryBankId() + "%"));
			// 按银行类型
			if (newQueryModel.getQueryHostId() != null && !newQueryModel.getQueryHostId().equals(""))
				dcr.add(Restrictions.eq("bankType", newQueryModel.getQueryHostId()));

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

			log.info("BankInfoDaoHibernateHQLImpl.querySum()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoDaoHibernateHQLImpl.querySum()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException("查询所有联行基本信息时出现异常");
		}
		return list;
	}

	/**
	 * 查询所有对象的个数
	 */
	public int querySum(BankInfoId queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("BankInfoDaoHibernateHQLImpl.querySum()开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from BankInfoId o where 1=1");

			// 按商户编号查询
			if (queryModel.getQueryBankId() != null && !queryModel.getQueryBankId().equals(""))
				bufferHql.append(" and o.bankId = '" + queryModel.getQueryBankId() + "'");

			// 按商户状态查询
			if (queryModel.getQueryHostId() != null && !queryModel.getQueryHostId().equals(""))
				bufferHql.append(" and o.bankType = '" + queryModel.getQueryHostId() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("BankInfoDaoHibernateHQLImpl.querySum()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoDaoHibernateHQLImpl.querySum()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询联行基本信息个数时出现异常");
		}
		return count;
	}

	/**
	 * 删除一条记录
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("BankInfoDaoHibernateHQLImpl.deleteItem()调用开始：删除一条记录。");

			// 这种删法，会连子表一同删除

			for (int i = 0; i < newKeys.size(); i++) {
				BankInfoId m = null;
				try {
					m = findItem(new String(newKeys.get(i) + ""), ud);
				} catch (Exception e) {
					throw new OAException("查找要删除信息时出现异常！");
				}
				if (m != null) {
					StringBuffer bufferHql = new StringBuffer("delete from BANK_INFO  where BANK_ID='" + m.getBankId() + "' and" + " HOST_ID='" + m.getHostId()
							+ "'");
					String hql = bufferHql.toString();
					saveOrUpdateOrDeleteBySQL(hql, ud);
				} else {
					throw new OAException("您要删除的信息不存在！");
				}
			}
			log.info("BankInfoDaoHibernateHQLImpl.deleteItem()结束调用：删除一条记录。");
		} catch (Exception e) {
			log.error("BankInfoDaoHibernateHQLImpl.deleteItem()删除一条记录，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
	}

	/**
	 *显示一条记录
	 */
	public BankInfoId findItem(String newKey, UserData ud) throws OAException {
		BankInfoId model = null;
		try {
			log.info("BankInfoDaoHibernateHQLImpl.findItem()开始调用：显示一条记录。");
			List list = null;
			Session session = getSession();
			// 基本信息
			Criteria cr = session.createCriteria(BankInfoId.class);
			String[] params = newKey.split(",");
			log.info("param:" + params[0] + "\t" + params[1]);
			if (params[0] != null && !"".equals(params[0])) {
				int i = params[0].length();
				if (i < 11) {
					for (int j = 1; j <= 11 - i; j++) {
						params[0] += " ";
					}
				}
			}
			if (params[1] != null && !"".equals(params[1])) {
				int i = params[1].length();
				if (i < 2) {
					for (int j = 1; j <= 2 - i; j++) {
						params[1] += " ";
					}
				}
			}
			cr.add(Restrictions.eq("bankId", params[0]));
			cr.add(Restrictions.eq("hostId", params[1]));
			list = findByCriteria(cr, ud);
			if (list.size() > 0) {
				model = (BankInfoId) list.get(0);
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
	public void saveItem(BankInfoId newModel, UserData ud) throws OAException {
		try {
			log.info("BankInfoDaoHibernateHQLImpl.saveItem()开始调用：修改一条记录。");
			log.info("修改 bakid=" + newModel.getBankId());
			// 验证输入的管辖行和主机号是否存在，如果存在，通过验证
			try {
				log.info("BankInfoDaoHibernateHQLImpl.createItem()开始调用：验证信息是否已经存在。");
				String newKey = newModel.getAdmBankId() + "," + newModel.getAdmHostId();
				BankInfoId sm = findItem(newKey, ud);
				log.info("BankInfoDaoHibernateHQLImpl.createItem()结束调用：验证信息是否已经存在。");
				if (sm == null) {
					throw new OAException("您输入的管辖行行号:" + newModel.getAdmBankId() + ",管辖行主机号：" + newModel.getAdmHostId() + " 的记录在数据库中不存在，请重新输入");
				}
			} catch (Exception e) {
				throw new OAException(e.getMessage());
			}
			try {
				update(newModel, ud);
				log.info("BankInfoDaoHibernateHQLImpl.saveItem()结束调用：修改一条记录。");
			} catch (Exception e) {
				throw new OAException("修改联行基本信息时出现异常！");
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("BankInfoDaoHibernateHQLImpl.saveItem()修改一条记录出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());
			throw new OAException(e.getMessage());

		}
	}

	/**
	 *新建一条记录
	 */
	public void createItem(BankInfoId newModel, UserData ud) throws OAException {

		// 唯一性验证
		try {
			log.info("BankInfoDaoHibernateHQLImpl.createItem()开始调用：验证信息是否已经存在。");
			String newKey = newModel.getBankId() + "," + newModel.getHostId();
			BankInfoId sm = findItem(newKey, ud);
			log.info("BankInfoDaoHibernateHQLImpl.createItem()结束调用：验证信息是否已经存在。");
			if (sm != null) {
				throw new OAException("联行行号:" + newModel.getBankId() + ",主机号：" + newModel.getHostId() + " 的记录已经在数据库中存在！");
			}
		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}
		// 验证输入的管辖行和主机号是否存在，如果存在，通过验证
		try {
			log.info("BankInfoDaoHibernateHQLImpl.createItem()开始调用：验证信息是否已经存在。");
			String newKey = newModel.getAdmBankId() + "," + newModel.getAdmHostId();
			BankInfoId sm = findItem(newKey, ud);
			log.info("BankInfoDaoHibernateHQLImpl.createItem()结束调用：验证信息是否已经存在。");
			if (sm == null) {
				throw new OAException("您输入的管辖行行号:" + newModel.getAdmBankId() + ",管辖行主机号：" + newModel.getAdmHostId() + " 的记录在数据库中不存在，请重新输入");
			}
		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}

		try {
			log.info("BankInfoDaoHibernateHQLImpl.createItem()开始调用：保存一个信息。");

			try {
				save(newModel, ud);
			} catch (Exception e) {
				throw new OAException("增加联行记录时出现异常");
			}

			log.info("BankInfoDaoHibernateHQLImpl.createItem()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("BankInfoDaoHibernateHQLImpl.createItem()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("增加联行记录时出现异常");
		}
	}

	/**
	 * 查询银行类型名称
	 */
	@SuppressWarnings("unchecked")
	public List<Banktype> findMerchantName(UserData ud) {

		List<Banktype> list = new ArrayList<Banktype>();
		Session session = getSession();
		Criteria cr = session.createCriteria(Banktype.class);
		list = findByCriteria(cr, ud);
		return list;

	}

	/**
	 * 根据银行类型编号找到银行类型名称
	 */
	public String findTypeNameById(String bankType, UserData ud) {
		String typeName = "";
		Banktype bt = null;
		StringBuilder sb = new StringBuilder(" from Banktype where 1=1 ");
		sb.append(" and id.bankType = "+bankType.trim());
		List list = findByHQL(sb.toString(), ud);
		if (list != null && list.size() > 0) {
			bt = (Banktype) list.get(0);
			if (bt != null) {
				typeName = bt.getTypeName();
			}
		}
		return typeName;
	}

	@Override
	public void update(Object entity, UserData ud) {
		BankInfoId bi = (BankInfoId) entity;
		String sql = "update BankInfoId set bankName='" + bi.getBankName() + "', bankType='" + bi.getBankType() + "', admBankId='" + bi.getAdmBankId()
				+ "', admHostId='" + bi.getAdmHostId() + "', " + "address='" + bi.getAddress() + "', postCode='" + bi.getPostCode() + "', email='"
				+ bi.getEmail() + "', fax='" + bi.getFax() + "', telex='" + bi.getTelex() + "', " + "authTel='" + bi.getAuthTel() + "', settleTel='"
				+ bi.getSettleTel() + "', gmName='" + bi.getGmName() + "', gmTel='" + bi.getGmTel() + "', mngName1='" + bi.getMngName1() + "'," + "mngTel1='"
				+ bi.getMngTel1() + "', mngName2='" + bi.getMngName2() + "', mngTel2='" + bi.getMngTel2() + "', mngName3='" + bi.getMngName3() + "', mngTel3='"
				+ bi.getMngTel3() + "'," + "authMngName='" + bi.getAuthMngName() + "', authMngTel='" + bi.getAuthMngTel() + "', settMngName='"
				+ bi.getSettMngName() + "',settMngTel='" + bi.getSettMngTel() + "'," + "cardMngName='" + bi.getCardMngName() + "', cardMngTel='"
				+ bi.getCardMngTel() + "', nasMngName='" + bi.getNasMngName() + "', nasMngTel='" + bi.getNasMngTel() + "'," + "updateOper='"
				+ bi.getUpdateOper() + "', updateDate='" + bi.getUpdateDate() + "', updateTime='" + bi.getUpdateTime() + "' where bankId='" + bi.getBankId()
				+ "' and hostId='" + bi.getHostId() + "'";
		log.info("修改sql==" + sql);
		bulkUpdate(sql, ud);
	}

}
