package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.merchant.domain.model.TblMerchantTranParamModel;
import cn.yufu.posp.merchant.domain.model.TblNoPasswdCardBinModel;

public class TblMerchantTranParamDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements TblMerchantTranParamDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public TblMerchantTranParamDaoHibernateHQLImpl() {

	}

	/**
	 * 查询所有对象
	 */
	public List queryAll(TblMerchantTranParamModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.queryAll()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(TblMerchantTranParamModel.class);

			// 按商户编号查询
			if (newQueryModel.getMerchantId() != null && !newQueryModel.getMerchantId().equals(""))
				dcr.add(Restrictions.eq("merchantId", newQueryModel.getMerchantId()));

			// 添加排序信息
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("merchantId"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.queryAll()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("TblMerchantTranParamDaoHibernateHQLImpl.queryAll()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询商户交易参数时抛异常！");
		}
		return list;
	}

	/**
	 * 查询所有对象的个数
	 */
	public int querySum(TblMerchantTranParamModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.querySum()开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from TblMerchantTranParamModel o where 1=1");

			// 按商户编号查询
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals(""))
				bufferHql.append(" and o.merchantId = '" + queryModel.getMerchantId() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.querySum()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamDaoHibernateHQLImpl.querySum()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询商户交易参数对象个数时抛异常！");
		}
		return count;
	}

	/**
	 *显示一条记录
	 */
	public TblMerchantTranParamModel findItem(String newKey, UserData ud) throws OAException {
		TblMerchantTranParamModel model = null;
		try {
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.findItem()开始调用：显示一条记录。");
			List list = null;
			Session session = getSession();

			// 基本信息
			Criteria cr = session.createCriteria(TblMerchantTranParamModel.class);
			String[] params = newKey.split(",");
			cr.add(Restrictions.eq("merchantId", params[0]));
			list = findByCriteria(cr, ud);
			if (list.size() > 0) {
				model = (TblMerchantTranParamModel) list.get(0);
				model.setMerchantId(model.getMerchantId());
			}

			log.info("TblMerchantTranParamDaoHibernateHQLImpl.findItem()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamDaoHibernateHQLImpl.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("查询商户交易参数对象时抛异常！");
		}

		return model;
	}

	/**
	 *修改一条记录
	 */
	public void saveItem(TblMerchantTranParamModel newModel, UserData ud) throws OAException {
		try {
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.saveItem()开始调用：修改一条记录。");

			saveOrUpdate(newModel, ud);
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.saveItem()结束调用：修改一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamDaoHibernateHQLImpl.saveItem()修改一条记录出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("修改商户交易参数时抛异常！");

		}
	}

	/**
	 *新建一条记录
	 */
	public void createItem(TblMerchantTranParamModel newModel, UserData ud) throws OAException {
		// 验证商户编号是否已经存在在数据库中
		MerchantBaseModel msm = findItemById(newModel.getMerchantId(), ud);
		if (msm == null) {
			throw new OAException("商户编号:" + newModel.getMerchantId() + " 的记录在数据库中不存在！");
		}
		// 验证编号是否已经存在后操作
		try {
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.createItem()开始调用：验证信息是否已经存在。");
			String newKey = newModel.getMerchantId();
			TblMerchantTranParamModel sm = findItem(newKey, ud);
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.createItem()结束调用：验证信息是否已经存在。");
			if (sm != null) {
				throw new OAException("商户编号:" + newModel.getMerchantId() + " 的记录已经在商户交易参数表中存在！");
			}

		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}

		try {
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.createItem()开始调用：保存一个信息。");
			save(newModel, ud);
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.createItem()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("TblMerchantTranParamDaoHibernateHQLImpl.createItem()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("添加商户交易参数时抛异常！");
		}
	}


	/**
	 * 验证商户编号是否存在
	 */
	public MerchantBaseModel findItemById(String newKey, UserData ud) throws OAException {
		MerchantBaseModel model = null;
		try {
			log.info("MerchantDaoHibernateHQLImpl.findItemById()开始调用：验证商户编号是否唯一。");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(MerchantBaseModel.class);
			cr.add(Restrictions.eq("merchantId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (MerchantBaseModel) list.get(0);

			log.info("MerchantDaoHibernateHQLImpl.findItemById()结束调用：验证商户编号是否唯一。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.findItemById()验证商户编号是否唯一，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("验证商户编号唯一性时出错！");
		}
		return model;
	}
	
	
	public String checkMerchantId(String merchantId, UserData ud) throws OAException {
		int returnFlagOne=0;
		int returnFlagTwo=0;
		String returnFlag="";
		// 验证商户编号是否已经存在在数据库中
			MerchantBaseModel msm = findItemById(merchantId, ud);
			if (msm != null) {
				returnFlagOne=1;
			}else{
				returnFlagOne=0;
			}
			// 验证编号是否已经存在后操作
			try {
				log.info("TblMerchantTranParamDaoHibernateHQLImpl.createItem()开始调用：验证信息是否已经存在。");
				TblMerchantTranParamModel sm = findItem(merchantId, ud);
				log.info("TblMerchantTranParamDaoHibernateHQLImpl.createItem()结束调用：验证信息是否已经存在。");
				if (sm != null) {
					returnFlagTwo=0;
				}else{
					returnFlagTwo=1;
				}

			} catch (Exception e) {
				throw new OAException(e.getMessage());
			}
			if(returnFlagOne==1&&returnFlagTwo==1){
				returnFlag="1";//正确
			}
			if(returnFlagOne==0){
				returnFlag="2";//错误：此商户在商户表中不存在
			}
			if(returnFlagTwo==0){
				returnFlag="3";//错误：此商户在商户交易参数表中存在
			}	
		return returnFlag;
	}
	
	
	/**
	 * 封装商户名称
	 */
	public String findMerchantName(String merchantId, UserData ud) {
		String merName = "";
		Session session = getSession();
		Criteria cr = session.createCriteria(MerchantBaseModel.class);
		cr.add(Restrictions.eq("merchantId", merchantId));
		List list = findByCriteria(cr, ud);
		if (list != null && list.size() > 0) {
			MerchantBaseModel m = (MerchantBaseModel) list.get(0);
			merName = m.getMerchantCname();
		}
		return merName;
	}
}
