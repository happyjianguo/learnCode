package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MccParamModel;
import cn.yufu.posp.merchant.domain.model.MerchantBaseBo;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.merchant.domain.model.MerchantExtraModel;
import cn.yufu.posp.merchant.domain.model.MerchantIdentity;
import cn.yufu.posp.merchant.domain.model.MerchantRefundModel;
import cn.yufu.posp.sysparam.domain.model.AreaCodeInfo;

public class MerchantDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements MerchantDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public MerchantDaoHibernateHQLImpl() {

	}

	/**
	 * 查询所有对象
	 */
	public List queryAll(MerchantBaseModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("MerchantDaoHibernateHQLImpl.querySum()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(MerchantBaseModel.class);

			// 按商户编号查询
			if (newQueryModel.getMerchantId() != null && !newQueryModel.getMerchantId().equals(""))
				dcr.add(Restrictions.eq("merchantId", newQueryModel.getMerchantId()));

			// 按商户状态查询
			if (newQueryModel.getMerchantStat() != null && !newQueryModel.getMerchantStat().equals(""))
				dcr.add(Restrictions.eq("merchantStat", newQueryModel.getMerchantStat()));

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

			/*
			 * String sql =
			 * "select mb.*,mc.mcc_name from merchant_base mb, mcc_param mc where mb.mcc=mc.mcc "
			 * ; //按商户编号查询 if(newQueryModel.getMerchantId()!=null &&
			 * !newQueryModel.getMerchantId().equals("") ){ sql = sql +
			 * "and mb.merchant_id='"+newQueryModel.getMerchantId()+"' "; }
			 * //按商户状态查询 if(newQueryModel.getMerchantStat()!=null &&
			 * !newQueryModel.getMerchantStat().equals("") ){ sql = sql +
			 * "and mb.merchant_stat='"+newQueryModel.getMerchantStat()+"' "; }
			 * //添加排序信息 if(sortType != null && sortfield != null) { if
			 * (sortType.equals("asc")) sql = sql +
			 * " order by mb."+sortfield+" asc"; else sql = sql +
			 * " order by mb."+sortfield+" desc"; } else { //按 id 排序 sql = sql +
			 * " order by mb.merchant_id desc"; } list =
			 * findBySQL(sql,startIndex, maxresults, ud);
			 */

			log.info("MerchantDaoHibernateHQLImpl.querySum()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.querySum()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 * 查询记录总数
	 */
	public int querySum(MerchantBaseModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("MerchantDaoHibernateHQLImpl.querySum()开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from MerchantBaseModel o where 1=1");

			// 按商户编号查询
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals(""))
				bufferHql.append(" and o.merchantId = '" + queryModel.getMerchantId() + "'");

			// 按商户状态查询
			if (queryModel.getMerchantStat() != null && !queryModel.getMerchantStat().equals(""))
				bufferHql.append(" and o.merchantStat = '" + queryModel.getMerchantStat() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("MerchantDaoHibernateHQLImpl.querySum()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.querySum()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;
	}

	/**
	 * 查询商品类型名称
	 */
	@SuppressWarnings("unchecked")
	public List<String> getMccsName(String mcc) {
		List<String> mccNameList = new ArrayList<String>();

		String sql = "select MCC_NAME from MCC_PARAM where MCC='" + mcc + "'";
		mccNameList = this.getHibernateTemplate().find(sql);
		return mccNameList;
	}

	/**
	 * 查询附加资料对象
	 */
	public MerchantExtraModel findExtraItem(String newKey, UserData ud) throws OAException {
		MerchantExtraModel model = null;
		try {
			log.info("MerchantDaoHibernateHQLImpl.findExtraItem()开始调用：查询附加资料对象。");
//			StringBuffer hql = new StringBuffer(" from MerchantExtraModel where 1=1 ");
//			hql.append(" and merchantId ='"+newKey+"'");
//			List list = findByHQL(hql.toString(), ud);
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(MerchantExtraModel.class);
			cr.add(Restrictions.eq("merchantId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (MerchantExtraModel) list.get(0);

			log.info("MerchantDaoHibernateHQLImpl.findExtraItem()结束调用：查询附加资料对象。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.findExtraItem()查询附加资料对象，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	/**
	 * 查询限额资料对象
	 */
	public MerchantRefundModel findRefundItem(String newKey, UserData ud) throws OAException {
		MerchantRefundModel model = null;
		try {
			log.info("MerchantDaoHibernateHQLImpl.findRefundItem()开始调用：查询限额资料对象。");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(MerchantRefundModel.class);
			cr.add(Restrictions.eq("merchantId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (MerchantRefundModel) list.get(0);

			log.info("MerchantDaoHibernateHQLImpl.findRefundItem()结束调用：查询限额资料对象。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.findRefundItem()查询限额资料对象，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	/**
	 *显示一条记录
	 */
	public MerchantBaseModel findItem(String newKey, UserData ud) throws OAException {
		MerchantBaseModel model = null;
		// MerchantExtraModel extraModel = new MerchantExtraModel();
		// MerchantRefundModel fundModel = new MerchantRefundModel();
		try {
			log.info("MerchantDaoHibernateHQLImpl.findItem()开始调用：显示一条记录。");
			List list = null;
			List list1 = null;
			List list2 = null;
			Session session = getSession();

			// 基本信息
			Criteria cr = session.createCriteria(MerchantBaseModel.class);
			cr.add(Restrictions.eq("merchantId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (MerchantBaseModel) list.get(0);

			// 附加资料
			MerchantExtraModel extraModel = this.findExtraItem(newKey, ud);
			/*
			 * Criteria cr1=session.createCriteria(MerchantExtraModel.class);
			 * cr1.add(Restrictions.eq("merchantId",newKey)); list1 =
			 * findByCriteria(cr1,ud);
			 * System.out.println("list1.size::"+list1.size());
			 * if(list1.size()>0) extraModel = (MerchantExtraModel)list1.get(0);
			 */
			if (extraModel != null) {
				model.setSndName(extraModel.getSndName() == null ? "" : extraModel.getSndName());
				model.setSndAcct(extraModel.getSndAcct() == null ? "" : extraModel.getSndAcct());
				model.setSndBank(extraModel.getSndBank() == null ? "" : extraModel.getSndBank());
				model.setRcvName(extraModel.getRcvName() == null ? "" : extraModel.getRcvName());
				model.setRcvBank(extraModel.getRcvBank() == null ? "" : extraModel.getRcvBank());
				model.setRcvAcct1(extraModel.getRcvAcct1() == null ? "" : extraModel.getRcvAcct1());
				model.setRcvAcct2(extraModel.getRcvAcct2() == null ? "" : extraModel.getRcvAcct2());
			}

			// 退货限制
			/*
			 * Criteria cr2=session.createCriteria(MerchantRefundModel.class);
			 * cr2.add(Restrictions.eq("merchantId",newKey)); list2 =
			 * findByCriteria(cr2,ud);
			 * System.out.println("list2.size::"+list2.size());
			 * if(list2.size()>0) fundModel = (MerchantRefundModel)list2.get(0);
			 */
			MerchantRefundModel fundModel = this.findRefundItem(newKey, ud);
			if (fundModel != null) {
				model.setRefundLimit(fundModel.getRefundLimit() == null ? "" : fundModel.getRefundLimit());
				model.setRefundCheck(fundModel.getRefundCheck() == null ? "" : fundModel.getRefundCheck());
			}

			log.info("MerchantDaoHibernateHQLImpl.findItem()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

		return model;
	}

	/**
	 * 删除一条记录
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("MerchantDaoHibernateHQLImpl.deleteItem(List newKeys, UserData ud)调用开始：删除一条记录。");

			// 这种删法，会连子表一同删除

			for (int i = 0; i < newKeys.size(); i++) {

				MerchantBaseModel mbm = findItem(new String(newKeys.get(i) + ""), ud);
				// 删除基本信息
				delete(mbm, ud);
				// 附加信息
				MerchantExtraModel me = null;
				me = this.findExtraItem(new String(newKeys.get(i) + ""), ud);
				if (me != null) {
					delete(me, ud);
				}
				// 限额信息
				MerchantRefundModel mrm = null;
				mrm = this.findRefundItem(new String(newKeys.get(i) + ""), ud);
				if (mrm != null) {
					delete(mrm, ud);
				}
			}

			log.info("MerchantDaoHibernateHQLImpl.deleteItem(List newKeys, UserData ud)结束调用：删除一条记录。");
		} catch (Exception e) {
			log.error("MerchantBaseModel.deleteItem(List newKeys, UserData ud)删除一条记录，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("删除商户资料信息时抛异常！");
		}
	}

	/**
	 *修改一条记录
	 */
	public void saveItem(MerchantBaseModel newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantDaoHibernateHQLImpl.saveItem()开始调用：修改一条记录。");
			// 附加资料
			MerchantExtraModel eModel = new MerchantExtraModel();
			eModel.setMerchantId(newModel.getMerchantId());
			eModel.setRcvAcct1(newModel.getRcvAcct1());
			eModel.setRcvAcct2(newModel.getRcvAcct2());
			eModel.setRcvBank(newModel.getRcvBank());
			eModel.setRcvName(newModel.getRcvName());
			eModel.setSndAcct(newModel.getSndAcct());
			eModel.setSndBank(newModel.getSndBank());
			eModel.setSndName(newModel.getSndName());
			/*
			 * System.out.println("1:"+eModel.getMerchantId());
			 * System.out.println("2:"+eModel.getRcvAcct1());
			 * System.out.println("3:"+eModel.getRcvAcct2());
			 * System.out.println("4:"+eModel.getRcvBank());
			 * System.out.println("5:"+eModel.getRcvName());
			 * System.out.println("6:"+eModel.getSndAcct());
			 * System.out.println("7:"+eModel.getSndBank());
			 * System.out.println("8:"+eModel.getSndName());
			 */

			// 退货限制
			MerchantRefundModel rModel = new MerchantRefundModel();
			rModel.setMerchantId(newModel.getMerchantId());
			rModel.setRefundCheck(newModel.getRefundCheck());
			rModel.setRefundLimit(newModel.getRefundLimit());
			rModel.setUpdateDate(newModel.getUpdateDate());
			rModel.setUpdateOper(newModel.getUpdateOper());
			rModel.setUpdateTime(newModel.getUpdateTime());
			/*
			 * System.out.println("9:"+rModel.getMerchantId());
			 * System.out.println("10:"+rModel.getRefundCheck());
			 * System.out.println("11:"+rModel.getRefundLimit());
			 * System.out.println("12:"+rModel.getUpdateOper());
			 * System.out.println("13:"+rModel.getUpdateDate());
			 * System.out.println("14:"+rModel.getUpdateTime());
			 */
			merge(newModel, ud);
			merge(eModel, ud);
			merge(rModel, ud);
			
			log.info("MerchantDaoHibernateHQLImpl.saveItem()结束调用：修改一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.saveItem()修改一条记录出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("修改商户资料时抛异常！");

		}
	}

	/**
	 *新建一条记录
	 */
	public void createItem(MerchantBaseModel newModel, UserData ud) throws OAException {
		
		try {
			log.info("MerchantDaoHibernateHQLImpl.createItem()开始调用：保存一个信息。");

			save(newModel, ud);
			// 附加资料
			MerchantExtraModel eModel = new MerchantExtraModel();
			eModel.setMerchantId(newModel.getMerchantId());
			eModel.setRcvAcct1(newModel.getRcvAcct1());
			eModel.setRcvAcct2(newModel.getRcvAcct2());
			eModel.setRcvBank(newModel.getRcvBank());
			eModel.setRcvName(newModel.getRcvName());
			eModel.setSndAcct(newModel.getSndAcct());
			eModel.setSndBank(newModel.getSndBank());
			eModel.setSndName(newModel.getSndName());
			save(eModel, ud);

			// 退货限制
			MerchantRefundModel rModel = new MerchantRefundModel();
			rModel.setMerchantId(newModel.getMerchantId());
			rModel.setRefundCheck(newModel.getRefundCheck());
			rModel.setRefundLimit(newModel.getRefundLimit());
			rModel.setUpdateDate(newModel.getUpdateDate());
			rModel.setUpdateOper(newModel.getUpdateOper());
			rModel.setUpdateTime(newModel.getUpdateTime());
			save(rModel, ud);
			//商户身份信息
			MerchantIdentity identity = new MerchantIdentity();
			identity.setMerchantId(newModel.getMerchantId());
			identity.setMerchantCname(newModel.getMerchantCname());
			identity.setStatus("0");//--0新增
			save(identity,ud);

			log.info("MerchantDaoHibernateHQLImpl.createItem()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.createItem()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}
	}

	/**
	 * 验证商户编号是否唯一
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

	/**
	 * 查询类型名称
	 */
	public String findMccName(String mcc, UserData ud) {
		String mccName = "";
		StringBuffer hql = new StringBuffer(" from MccParamModel where 1=1 ");
		hql.append(" and mcc ='"+mcc+"'");
		List list = findByHQL(hql.toString(), ud);
		if (list != null && list.size() > 0) {
			MccParamModel mm = (MccParamModel) list.get(0);
			mccName = mm.getMccName();
		}
		return mccName;
	}

	/**
	 *查询机构
	 * 
	 * public List findAllJG(UserData ud) {
	 * log.info("MerchantDaoHibernateHQLImpl.findAllJG()开始调用：查询机构名称。"); List
	 * list = new ArrayList(); try{ Session session = getSession(); Criteria cr
	 * = session.createCriteria(JgModel.class); list = findByCriteria(cr,ud);
	 * System.out.println("查询机构个数："+list.size()); }catch(Exception e){
	 * e.printStackTrace(); }
	 * log.info("MerchantDaoHibernateHQLImpl.findAllJG()结束调用：查询机构名称。"); return
	 * list;
	 * 
	 * }
	 */

	/**
	 * ajax 查询签约行号和主机号
	 */
	public List<MerchantBaseModel> findSignBankInfo(String bankId,UserData ud) {
		List<MerchantBaseModel> list = new ArrayList<MerchantBaseModel>();
		MerchantBaseModel m = null;
		String sql = "select bank_id, host_id, bank_name from bank_info where bank_id like '%" + bankId + "%' order by bank_id asc";
		Connection conn = this.getSession().connection();
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				m = new MerchantBaseModel();
				m.setSignBankId(rs.getString("bank_id"));
				m.setSignHostId(rs.getString("host_id"));
				m.setBankName(rs.getString("bank_name"));
				list.add(m);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return list;
	}

	/**
	 * ajax 查询商户类型
	 */
	public List<MerchantBaseModel> findMccInfo(String mcc,UserData ud) {
		List<MerchantBaseModel> list = new ArrayList<MerchantBaseModel>();
		MerchantBaseModel m = null;
		StringBuffer hql = new StringBuffer(" from MccParamModel where 1=1 ");
		hql.append(" and mcc like '%"+mcc+"%' order by mcc asc ");
		List<MccParamModel> mccParamList = findByHQL(hql.toString(), ud);
		for(MccParamModel mccParam : mccParamList){
			m = new MerchantBaseModel();
			m.setMcc(mccParam.getMcc());
			m.setMccName(mccParam.getMccName());
			list.add(m);
		}
		return list;
	}

	@Override
	public void saveBaseInfo(MerchantBaseBo model, UserData ud) throws OAException {
		// 验证是否已经存在-基本信息 存在则删除
		try {
			log.info("MerchantDaoHibernateHQLImpl.createItem()开始调用：验证信息是否已经存在。");
			MerchantBaseBo sm = findBaseInfoById(model.getMerchantId(), ud);
			log.info("MerchantDaoHibernateHQLImpl.createItem()结束调用：验证信息是否已经存在。");
			if (sm != null) {
				deleteBaseInfo(sm, ud);
			}

		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}
		Transaction t = this.getSession().beginTransaction();
		try {
			log.info("MerchantDaoHibernateHQLImpl.createItem()开始调用：保存一个信息。");

			save(model, ud);
			// 附加资料

//			MerchantExtraModel eModel = new MerchantExtraModel();
//			eModel.setMerchantId(model.getMerchantId());
//			eModel.setRcvAcct1(model.getRcvAcct1());
//			eModel.setRcvAcct2(model.getRcvAcct2());
//			eModel.setRcvBank(model.getRcvBank());
//			eModel.setRcvName(model.getRcvName());
//			eModel.setSndAcct(model.getSndAcct());
//			eModel.setSndBank(model.getSndBank());
//			eModel.setSndName(model.getSndName());
//			save(eModel, ud);

			t.commit();

			log.info("MerchantDaoHibernateHQLImpl.createItem()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.createItem()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}
	}

	public MerchantBaseBo findBaseInfoById(String merchantId, UserData ud) throws OAException {
		MerchantBaseBo model = null;
		try {
			log.info("MerchantDaoHibernateHQLImpl.findBaseInfoById()开始调用：验证商户信息是否存在。");
			List list = null;
			String hql = " from MerchantBaseBo where 1=1 and merchantId='"+merchantId+"'";
			list = findByHQL(hql , ud);
			if (list.size() > 0)
				model = (MerchantBaseBo) list.get(0);

			log.info("MerchantDaoHibernateHQLImpl.findBaseInfoById()结束调用：验证商户信息是否存在。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.findBaseInfoById()验证商户信息是否存在，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("验证商户信息是否存在");
		}
		return model;
	}

	/**
	 * 删除一条记录
	 */
	public void deleteBaseInfo(MerchantBaseBo sm, UserData ud) throws OAException {
		Transaction t = this.getSession().beginTransaction();
		try {
			log.info("MerchantDaoHibernateHQLImpl.deleteItem(List newKeys, UserData ud)调用开始：删除一条记录。");

			t.begin();
			delete(sm, ud);
			// 附加信息
//			MerchantExtraModel me = null;
//			me = this.findExtraItem(, ud);
//			if (me != null) {
//				delete(me, ud);
//			}

			t.commit();

			log.info("MerchantDaoHibernateHQLImpl.deleteItem(List newKeys, UserData ud)结束调用：删除一条记录。");
		} catch (Exception e) {
			log.error("MerchantBaseModel.deleteItem(List newKeys, UserData ud)删除一条记录，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("删除商户资料信息时抛异常！");
		}
	}

	@Override
	public List<AreaCodeInfo> findArea(AreaCodeInfo area, UserData ud) {
		MerchantBaseModel m = null;
		StringBuffer hql = new StringBuffer(" from AreaCodeInfo where 1=1 ");
		if(area.getAreaCode()!=null&&!"".equals(area.getAreaCode())){
			hql.append(" and areaCode like '%"+area.getAreaCode()+"%' ");
		}
		if(area.getAreaName()!=null&&!"".equals(area.getAreaName())){
			hql.append(" and areaName like '%"+area.getAreaName()+"%' ");
		}
		hql.append(" order by areaCode asc ");
		List<AreaCodeInfo> areaList = findByHQL(hql.toString(), ud);
		return areaList;
	}

}
