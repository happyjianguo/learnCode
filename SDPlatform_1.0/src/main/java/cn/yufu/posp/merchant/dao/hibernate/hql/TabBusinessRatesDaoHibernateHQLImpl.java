package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.text.DecimalFormat;
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
import cn.yufu.posp.merchant.domain.model.TabBusinessRatesModel;


public class TabBusinessRatesDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements TabBusinessRatesDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public TabBusinessRatesDaoHibernateHQLImpl() {

	}

	/**
	 * 查询所有对象
	 */
	public List queryAll(TabBusinessRatesModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("TabBusinessRatesDaoHibernateHQLImpl.querySum()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(TabBusinessRatesModel.class);

			//按业务ID查询
			 if (newQueryModel.getBusinessid() != null &&!newQueryModel.getBusinessid().equals(""))
			 dcr.add(Restrictions.eq("businessid",newQueryModel.getBusinessid()));
			
		
			// 添加排序信息
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("businessid"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			log.info("TabBusinessRatesDaoHibernateHQLImpl.querySum()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesDaoHibernateHQLImpl.querySum()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 * 查询记录总数
	 */
	public int querySum(TabBusinessRatesModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("TabBusinessRatesDaoHibernateHQLImpl.querySum()开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from TabBusinessRatesModel o where 1=1");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("TabBusinessRatesDaoHibernateHQLImpl.querySum()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesDaoHibernateHQLImpl.querySum()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;
	}

	/**
	 *显示一条记录
	 */
	public TabBusinessRatesModel findItem(String newKey, UserData ud) throws OAException {
		TabBusinessRatesModel model = null;
		try {
			log.info("TabBusinessRatesDaoHibernateHQLImpl.findItem()开始调用：显示一条记录。");
			Session session = getSession();
			// 基本信息
			Criteria cr = session.createCriteria(TabBusinessRatesModel.class);
			cr.add(Restrictions.eq("businessid", newKey));
			List list = findByCriteria(cr, ud);
			if (list.size() > 0){
				model = (TabBusinessRatesModel) list.get(0);
			}
			log.info("TabBusinessRatesDaoHibernateHQLImpl.findItem()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesDaoHibernateHQLImpl.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

		return model;
	}


	/**
	 *修改一条记录
	 */
	public void saveItem(TabBusinessRatesModel newModel, UserData ud) throws OAException {
		try {
			log.info("TabBusinessRatesDaoHibernateHQLImpl.saveItem()开始调用：修改一条记录。");

			update(newModel, ud);
			
			log.info("TabBusinessRatesDaoHibernateHQLImpl.saveItem()结束调用：修改一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesDaoHibernateHQLImpl.saveItem()修改一条记录出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("修改业务场景费率时抛异常！");

		}
	}

	/**
	 *新建一条记录
	 */
	public void createItem(TabBusinessRatesModel newModel, UserData ud) throws OAException {	
		try {
			log.info("TabBusinessRatesDaoHibernateHQLImpl.createItem()开始调用：保存一个信息。");
			// 创建业务ID(businessid)
			String businessid = findItemBusinessId(newModel,ud);
			newModel.setBusinessid(businessid);
			save(newModel, ud);
			log.info("TabBusinessRatesDaoHibernateHQLImpl.createItem()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesDaoHibernateHQLImpl.createItem()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}
	}
	
	
	
	/**
	 * 创建业务ID(businessid)
	 */
	public String findItemBusinessId(TabBusinessRatesModel queryModel, UserData ud) throws OAException {
		String businessid = "";
		int count = 0;
		try {
			log.info("TabBusinessRatesDaoHibernateHQLImpl.findItemBusinessId()开始调用：创建业务ID(businessid)。");
			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select MAX(businessid) from TabBusinessRatesModel o where 1=1");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0){
				if(list.get(0)!=null){
					count = Integer.parseInt(list.get(0).toString());
					businessid=new DecimalFormat("000").format(count+1);	
				}else{
					businessid="001";
				}
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesDaoHibernateHQLImpl.findItemBusinessId()创建业务ID(businessid)调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return businessid;
	}
	
	//查询业务记录
	public List<TabBusinessRatesModel> queryAllTabBusinessRates() throws OAException {
		List list = null;
		try {
			log.info("TabBusinessRatesDaoHibernateHQLImpl.queryAllTabBusinessRates()开始调用：通过查询业务记录。");
			DetachedCriteria dcr = DetachedCriteria.forClass(TabBusinessRatesModel.class);
			// 按业务 ID 排序
			dcr.addOrder(Order.asc("businessid"));
			list = findBYCriteria(dcr, null);
			log.info("TabBusinessRatesDaoHibernateHQLImpl.queryAllTabBusinessRates()结束调用：通过查询业务记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("TabBusinessRatesDaoHibernateHQLImpl.queryAllTabBusinessRates()通过查询业务记录，出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询业务记录报错！");
		}
		return list;
	}
}
