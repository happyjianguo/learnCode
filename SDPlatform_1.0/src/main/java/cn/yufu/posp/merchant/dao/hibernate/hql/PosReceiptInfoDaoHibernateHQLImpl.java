package cn.yufu.posp.merchant.dao.hibernate.hql;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.PosReceiptInfoModel;

public class PosReceiptInfoDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements PosReceiptInfoDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public PosReceiptInfoDaoHibernateHQLImpl() {

	}

	/**
	 * 查询所有对象
	 */
	public List queryAll(PosReceiptInfoModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("PosReceiptInfoDaoHibernateHQLImpl.queryAll()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(PosReceiptInfoModel.class);

			// 按状态标识查询
			if (newQueryModel.getStatus() != null && !newQueryModel.getStatus().equals(""))
				dcr.add(Restrictions.eq("status", newQueryModel.getStatus()));

			// 添加排序信息
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// 按状态标识 排序
				dcr.addOrder(Order.asc("hotline"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			
			log.info("PosReceiptInfoDaoHibernateHQLImpl.queryAll()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("PosReceiptInfoDaoHibernateHQLImpl.queryAll()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询POS签购单要素设置类时抛异常！");
		}
		return list;
	}

	/**
	 * 查询所有对象的个数
	 */
	public int querySum(PosReceiptInfoModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("PosReceiptInfoDaoHibernateHQLImpl.querySum()开始调用：查询符合条件的数量。");
			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from PosReceiptInfoModel o where 1=1");

			// 按状态标识查询
			if (queryModel.getStatus() != null && !queryModel.getStatus().equals(""))
				bufferHql.append(" and o.status = '" + queryModel.getStatus() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			
			log.info("PosReceiptInfoDaoHibernateHQLImpl.querySum()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("PosReceiptInfoDaoHibernateHQLImpl.querySum()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询POS签购单要素设置类对象个数时抛异常！");
		}
		return count;
	}

	
	/**
	 *显示一条记录
	 */
	public PosReceiptInfoModel findItem(String newKey, UserData ud) throws OAException {
		PosReceiptInfoModel model = null;
		try {
			log.info("PosReceiptInfoDaoHibernateHQLImpl.findItem()开始调用：显示一条记录。");
			
			List list = null;
			Session session = getSession();
			
			// 基本信息
			Criteria cr = session.createCriteria(PosReceiptInfoModel.class);
			String[] params = newKey.split(",");
			cr.add(Restrictions.eq("status",params[0]));
			
			list = findByCriteria(cr, ud);
			if (list.size() > 0) {
				model = (PosReceiptInfoModel) list.get(0);
				model.setStatus(model.getStatus());
			}

			log.info("PosReceiptInfoDaoHibernateHQLImpl.findItem()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("PosReceiptInfoDaoHibernateHQLImpl.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("查询POS签购单要素设置类对象时抛异常！");
		}

		return model;
	}

	/**
	 *修改一条记录
	 */
	public void saveItem(PosReceiptInfoModel newModel, UserData ud) throws OAException {
		try {
			log.info("PosReceiptInfoDaoHibernateHQLImpl.saveItem()开始调用：修改一条记录。");

			update(newModel, ud);
			
			log.info("PosReceiptInfoDaoHibernateHQLImpl.saveItem()结束调用：修改一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("PosReceiptInfoDaoHibernateHQLImpl.saveItem()修改一条记录出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("修改POS签购单要素设置类时抛异常！");

		}
	}
	
	
	public void update(Object entity, UserData ud) {
		PosReceiptInfoModel newModel = (PosReceiptInfoModel) entity;
		String sql = new String("update PosReceiptInfoModel  set hotline='" + newModel.getHotline() + "',telSupport='" + newModel.getTelSupport()
				+ "',adInfo='" + newModel.getAdInfo() + "',adYesNoFlag='" + newModel.getAdYesNoFlag()+ "',status='" + newModel.getStatus() 
				+ "',createDate='" + newModel.getCreateDate() + "',Update_Oper='" + newModel.getUpdateOper()+ "',updateDate='" + newModel.getUpdateDate()
				+ "' where status='" + newModel.getStatus()  + "'");
		bulkUpdate(sql, ud);
	}

}
