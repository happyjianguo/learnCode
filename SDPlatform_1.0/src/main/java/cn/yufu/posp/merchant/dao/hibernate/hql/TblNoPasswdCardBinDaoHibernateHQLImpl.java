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
import cn.yufu.posp.merchant.domain.model.MerchantCardModel;
import cn.yufu.posp.merchant.domain.model.TblNoPasswdCardBinModel;

public class TblNoPasswdCardBinDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements TblNoPasswdCardBinDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public TblNoPasswdCardBinDaoHibernateHQLImpl() {

	}

	/**
	 * 查询所有对象
	 */
	public List queryAll(TblNoPasswdCardBinModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.queryAll()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(TblNoPasswdCardBinModel.class);

			// 按卡BIN查询
			if (newQueryModel.getQueryCardBin()!= null && !newQueryModel.getQueryCardBin().equals(""))
				dcr.add(Restrictions.eq("cardBinInfo", newQueryModel.getQueryCardBin()));
			// 添加排序信息
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("cardBinInfo"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.queryAll()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("TblNoPasswdCardBinDaoHibernateHQLImpl.queryAll()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询授权免密卡BIN信息时抛异常！");
		}
		return list;
	}

	/**
	 * 查询所有对象的个数
	 */
	public int querySum(TblNoPasswdCardBinModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.querySum()开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from TblNoPasswdCardBinModel o where 1=1");

			// 按卡BIN查询
			if (queryModel.getQueryCardBin()!= null && !queryModel.getQueryCardBin().equals(""))
				bufferHql.append(" and o.cardBinInfo = '"+ queryModel.getQueryCardBin() +"'");
			
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.querySum()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinDaoHibernateHQLImpl.querySum()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询授权免密卡BIN信息对象个数时抛异常！");
		}
		return count;
	}

	/**
	 *显示一条记录
	 */
	public TblNoPasswdCardBinModel findItem(String newKey, UserData ud) throws OAException {
		TblNoPasswdCardBinModel model = null;
		try {
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.findItem()开始调用：显示一条记录。");
			List list = null;
			Session session = getSession();

			// 基本信息
			Criteria cr = session.createCriteria(TblNoPasswdCardBinModel.class);
			String[] params = newKey.split(",");
			cr.add(Restrictions.eq("cardBinInfo", params[0]));
			list = findByCriteria(cr, ud);
			if (list.size() > 0) {
				model = (TblNoPasswdCardBinModel) list.get(0);
			}

			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.findItem()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinDaoHibernateHQLImpl.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("查询授权免密卡BIN信息对象时抛异常！");
		}

		return model;
	}

	/**
	 *修改一条记录
	 */
	public void saveItem(TblNoPasswdCardBinModel newModel, UserData ud) throws OAException {
		try {
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.saveItem()开始调用：修改一条记录。");

			saveOrUpdate(newModel, ud);
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.saveItem()结束调用：修改一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinDaoHibernateHQLImpl.saveItem()修改一条记录出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("修改授权免密卡BIN信息时抛异常！");

		}
	}

	/**
	 *新建一条记录
	 */
	public void createItem(TblNoPasswdCardBinModel newModel, UserData ud) throws OAException {
		// 验证卡BIN是否已经存在
		try {
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.createItem()开始调用：验证信息是否已经存在。");
			String newKey ="";
			String length ="0";
			
			newKey = newModel.getFirstCardBin().substring(0, 9);
			List listCardBin = queryAllCardBin(newKey, ud);
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.createItem()结束调用：验证信息是否已经存在。");
			if(listCardBin.size()>0){
				length=((TblNoPasswdCardBinModel)listCardBin.get(0)).getDataLength().trim();
				
				if("9".equals(newModel.getDataLength())){
					throw new OAException("卡信息:" + newModel.getFirstCardBin()+ " 的记录在数据库中存在！");			
				}
				if("10".equals(newModel.getDataLength())){
					
					if(((TblNoPasswdCardBinModel)listCardBin.get(0)).getFirstCardBin().equals(newModel.getFirstCardBin())){
						throw new OAException("卡信息:" + newModel.getFirstCardBin()+ " 的记录在数据库中存在！");	
					}	
						
					if("10".equals(length)){
						throw new OAException("卡信息:" + newModel.getFirstCardBin()+ " 的记录在数据库中存在！");	
					}
				}
				if("11".equals(newModel.getDataLength())){
					if(((TblNoPasswdCardBinModel)listCardBin.get(0)).getFirstCardBin().equals(newModel.getFirstCardBin())){
						throw new OAException("卡信息:" + newModel.getFirstCardBin()+ " 的记录在数据库中存在！");	
					}	
					if(!"11".equals(length)){
						throw new OAException("卡信息:" + newModel.getFirstCardBin()+ " 的记录在数据库中存在！");	
					}
				}
			}
						
		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}

		try {
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.createItem()开始调用：保存一个信息。");

			save(newModel, ud);

			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.createItem()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinDaoHibernateHQLImpl.createItem()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException("添加授权免密卡BIN信息时抛异常！");
		}
	}

	
	/**
	 * 查询所有对象
	 */
	public List queryAllCardBin(String queryCardBin, UserData ud) throws OAException {
		List listCardBin = null;
		try {
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.queryAll()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(TblNoPasswdCardBinModel.class);

			// 按卡BIN查询
			if (queryCardBin!= null && !queryCardBin.equals(""))
				dcr.add(Restrictions.like("firstCardBin", "%" + queryCardBin + "%"));
			listCardBin = findBYCriteria(dcr, ud);
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.queryAll()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("TblNoPasswdCardBinDaoHibernateHQLImpl.queryAll()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询授权免密卡BIN信息时抛异常！");
		}
		return listCardBin;
	}

	
	/**
	 * 删除一条记录
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.deleteItem()调用开始：删除一条记录。");

			for (int i = 0; i < newKeys.size(); i++) {
				TblNoPasswdCardBinModel m = findItem(new String(newKeys.get(i) + ""), ud);
				StringBuffer bufferHql = new StringBuffer("delete from TBL_NO_PASSWD_CARDBIN  where FIRST_CARD_BIN='" + m.getFirstCardBin() + "'");
				String hql = bufferHql.toString();
				saveOrUpdateOrDeleteBySQL(hql, ud);

			}

			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.deleteItem()结束调用：删除一条记录。");
		} catch (Exception e) {
			log.error("TblNoPasswdCardBinDaoHibernateHQLImpl.deleteItem()删除一条记录，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("删除授权免密卡BIN信息时抛异常！");
		}
	}
	
	public String findfirstCardBinByKey(TblNoPasswdCardBinModel newModel, UserData ud) throws OAException {
		String result ="0";
		String length ="0";
		try {		
			String newKey ="";
			newKey = newModel.getFirstCardBin().substring(0, 9);
			List listCardBin = queryAllCardBin(newKey, ud);
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.createItem()结束调用：验证信息是否已经存在。");
			if(listCardBin.size()>0){
				length=((TblNoPasswdCardBinModel)listCardBin.get(0)).getDataLength().trim();
				
				if("9".equals(newModel.getDataLength())){
					result="-1";
					return result;
				}
				if("10".equals(newModel.getDataLength())){	
					if(((TblNoPasswdCardBinModel)listCardBin.get(0)).getFirstCardBin().equals(newModel.getFirstCardBin())){
						result="-1";
						return result;
					}	
					if(!"10".equals(length)){
						result="-1";
						return result;
					}
				}
				if("11".equals(newModel.getDataLength())){
					if(((TblNoPasswdCardBinModel)listCardBin.get(0)).getFirstCardBin().equals(newModel.getFirstCardBin())){
						result="-1";
						return result;
					}
					if(!"11".equals(length)){
						result="-1";
						return result;
					}
				}
			}
			
		} catch (Exception e) {

			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return result;
	}
}
