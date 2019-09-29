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
	 * ��ѯ���ж���
	 */
	public List queryAll(MerchantBaseModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("MerchantDaoHibernateHQLImpl.querySum()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(MerchantBaseModel.class);

			// ���̻���Ų�ѯ
			if (newQueryModel.getMerchantId() != null && !newQueryModel.getMerchantId().equals(""))
				dcr.add(Restrictions.eq("merchantId", newQueryModel.getMerchantId()));

			// ���̻�״̬��ѯ
			if (newQueryModel.getMerchantStat() != null && !newQueryModel.getMerchantStat().equals(""))
				dcr.add(Restrictions.eq("merchantStat", newQueryModel.getMerchantStat()));

			// ���������Ϣ
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("merchantId"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			/*
			 * String sql =
			 * "select mb.*,mc.mcc_name from merchant_base mb, mcc_param mc where mb.mcc=mc.mcc "
			 * ; //���̻���Ų�ѯ if(newQueryModel.getMerchantId()!=null &&
			 * !newQueryModel.getMerchantId().equals("") ){ sql = sql +
			 * "and mb.merchant_id='"+newQueryModel.getMerchantId()+"' "; }
			 * //���̻�״̬��ѯ if(newQueryModel.getMerchantStat()!=null &&
			 * !newQueryModel.getMerchantStat().equals("") ){ sql = sql +
			 * "and mb.merchant_stat='"+newQueryModel.getMerchantStat()+"' "; }
			 * //���������Ϣ if(sortType != null && sortfield != null) { if
			 * (sortType.equals("asc")) sql = sql +
			 * " order by mb."+sortfield+" asc"; else sql = sql +
			 * " order by mb."+sortfield+" desc"; } else { //�� id ���� sql = sql +
			 * " order by mb.merchant_id desc"; } list =
			 * findBySQL(sql,startIndex, maxresults, ud);
			 */

			log.info("MerchantDaoHibernateHQLImpl.querySum()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.querySum()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 * ��ѯ��¼����
	 */
	public int querySum(MerchantBaseModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("MerchantDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from MerchantBaseModel o where 1=1");

			// ���̻���Ų�ѯ
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals(""))
				bufferHql.append(" and o.merchantId = '" + queryModel.getMerchantId() + "'");

			// ���̻�״̬��ѯ
			if (queryModel.getMerchantStat() != null && !queryModel.getMerchantStat().equals(""))
				bufferHql.append(" and o.merchantStat = '" + queryModel.getMerchantStat() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("MerchantDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;
	}

	/**
	 * ��ѯ��Ʒ��������
	 */
	@SuppressWarnings("unchecked")
	public List<String> getMccsName(String mcc) {
		List<String> mccNameList = new ArrayList<String>();

		String sql = "select MCC_NAME from MCC_PARAM where MCC='" + mcc + "'";
		mccNameList = this.getHibernateTemplate().find(sql);
		return mccNameList;
	}

	/**
	 * ��ѯ�������϶���
	 */
	public MerchantExtraModel findExtraItem(String newKey, UserData ud) throws OAException {
		MerchantExtraModel model = null;
		try {
			log.info("MerchantDaoHibernateHQLImpl.findExtraItem()��ʼ���ã���ѯ�������϶���");
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

			log.info("MerchantDaoHibernateHQLImpl.findExtraItem()�������ã���ѯ�������϶���");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.findExtraItem()��ѯ�������϶��󣬳����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	/**
	 * ��ѯ�޶����϶���
	 */
	public MerchantRefundModel findRefundItem(String newKey, UserData ud) throws OAException {
		MerchantRefundModel model = null;
		try {
			log.info("MerchantDaoHibernateHQLImpl.findRefundItem()��ʼ���ã���ѯ�޶����϶���");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(MerchantRefundModel.class);
			cr.add(Restrictions.eq("merchantId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (MerchantRefundModel) list.get(0);

			log.info("MerchantDaoHibernateHQLImpl.findRefundItem()�������ã���ѯ�޶����϶���");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.findRefundItem()��ѯ�޶����϶��󣬳����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	/**
	 *��ʾһ����¼
	 */
	public MerchantBaseModel findItem(String newKey, UserData ud) throws OAException {
		MerchantBaseModel model = null;
		// MerchantExtraModel extraModel = new MerchantExtraModel();
		// MerchantRefundModel fundModel = new MerchantRefundModel();
		try {
			log.info("MerchantDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			List list = null;
			List list1 = null;
			List list2 = null;
			Session session = getSession();

			// ������Ϣ
			Criteria cr = session.createCriteria(MerchantBaseModel.class);
			cr.add(Restrictions.eq("merchantId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (MerchantBaseModel) list.get(0);

			// ��������
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

			// �˻�����
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

			log.info("MerchantDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

		return model;
	}

	/**
	 * ɾ��һ����¼
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("MerchantDaoHibernateHQLImpl.deleteItem(List newKeys, UserData ud)���ÿ�ʼ��ɾ��һ����¼��");

			// ����ɾ���������ӱ�һͬɾ��

			for (int i = 0; i < newKeys.size(); i++) {

				MerchantBaseModel mbm = findItem(new String(newKeys.get(i) + ""), ud);
				// ɾ��������Ϣ
				delete(mbm, ud);
				// ������Ϣ
				MerchantExtraModel me = null;
				me = this.findExtraItem(new String(newKeys.get(i) + ""), ud);
				if (me != null) {
					delete(me, ud);
				}
				// �޶���Ϣ
				MerchantRefundModel mrm = null;
				mrm = this.findRefundItem(new String(newKeys.get(i) + ""), ud);
				if (mrm != null) {
					delete(mrm, ud);
				}
			}

			log.info("MerchantDaoHibernateHQLImpl.deleteItem(List newKeys, UserData ud)�������ã�ɾ��һ����¼��");
		} catch (Exception e) {
			log.error("MerchantBaseModel.deleteItem(List newKeys, UserData ud)ɾ��һ����¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("ɾ���̻�������Ϣʱ���쳣��");
		}
	}

	/**
	 *�޸�һ����¼
	 */
	public void saveItem(MerchantBaseModel newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸�һ����¼��");
			// ��������
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

			// �˻�����
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
			
			log.info("MerchantDaoHibernateHQLImpl.saveItem()�������ã��޸�һ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.saveItem()�޸�һ����¼�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("�޸��̻�����ʱ���쳣��");

		}
	}

	/**
	 *�½�һ����¼
	 */
	public void createItem(MerchantBaseModel newModel, UserData ud) throws OAException {
		
		try {
			log.info("MerchantDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����Ϣ��");

			save(newModel, ud);
			// ��������
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

			// �˻�����
			MerchantRefundModel rModel = new MerchantRefundModel();
			rModel.setMerchantId(newModel.getMerchantId());
			rModel.setRefundCheck(newModel.getRefundCheck());
			rModel.setRefundLimit(newModel.getRefundLimit());
			rModel.setUpdateDate(newModel.getUpdateDate());
			rModel.setUpdateOper(newModel.getUpdateOper());
			rModel.setUpdateTime(newModel.getUpdateTime());
			save(rModel, ud);
			//�̻������Ϣ
			MerchantIdentity identity = new MerchantIdentity();
			identity.setMerchantId(newModel.getMerchantId());
			identity.setMerchantCname(newModel.getMerchantCname());
			identity.setStatus("0");//--0����
			save(identity,ud);

			log.info("MerchantDaoHibernateHQLImpl.createItem()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.createItem()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}
	}

	/**
	 * ��֤�̻�����Ƿ�Ψһ
	 */
	public MerchantBaseModel findItemById(String newKey, UserData ud) throws OAException {
		MerchantBaseModel model = null;
		try {
			log.info("MerchantDaoHibernateHQLImpl.findItemById()��ʼ���ã���֤�̻�����Ƿ�Ψһ��");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(MerchantBaseModel.class);
			cr.add(Restrictions.eq("merchantId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (MerchantBaseModel) list.get(0);

			log.info("MerchantDaoHibernateHQLImpl.findItemById()�������ã���֤�̻�����Ƿ�Ψһ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.findItemById()��֤�̻�����Ƿ�Ψһ�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��֤�̻����Ψһ��ʱ����");
		}
		return model;
	}

	/**
	 * ��ѯ��������
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
	 *��ѯ����
	 * 
	 * public List findAllJG(UserData ud) {
	 * log.info("MerchantDaoHibernateHQLImpl.findAllJG()��ʼ���ã���ѯ�������ơ�"); List
	 * list = new ArrayList(); try{ Session session = getSession(); Criteria cr
	 * = session.createCriteria(JgModel.class); list = findByCriteria(cr,ud);
	 * System.out.println("��ѯ����������"+list.size()); }catch(Exception e){
	 * e.printStackTrace(); }
	 * log.info("MerchantDaoHibernateHQLImpl.findAllJG()�������ã���ѯ�������ơ�"); return
	 * list;
	 * 
	 * }
	 */

	/**
	 * ajax ��ѯǩԼ�кź�������
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
	 * ajax ��ѯ�̻�����
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
		// ��֤�Ƿ��Ѿ�����-������Ϣ ������ɾ��
		try {
			log.info("MerchantDaoHibernateHQLImpl.createItem()��ʼ���ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			MerchantBaseBo sm = findBaseInfoById(model.getMerchantId(), ud);
			log.info("MerchantDaoHibernateHQLImpl.createItem()�������ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			if (sm != null) {
				deleteBaseInfo(sm, ud);
			}

		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}
		Transaction t = this.getSession().beginTransaction();
		try {
			log.info("MerchantDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����Ϣ��");

			save(model, ud);
			// ��������

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

			log.info("MerchantDaoHibernateHQLImpl.createItem()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.createItem()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}
	}

	public MerchantBaseBo findBaseInfoById(String merchantId, UserData ud) throws OAException {
		MerchantBaseBo model = null;
		try {
			log.info("MerchantDaoHibernateHQLImpl.findBaseInfoById()��ʼ���ã���֤�̻���Ϣ�Ƿ���ڡ�");
			List list = null;
			String hql = " from MerchantBaseBo where 1=1 and merchantId='"+merchantId+"'";
			list = findByHQL(hql , ud);
			if (list.size() > 0)
				model = (MerchantBaseBo) list.get(0);

			log.info("MerchantDaoHibernateHQLImpl.findBaseInfoById()�������ã���֤�̻���Ϣ�Ƿ���ڡ�");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.findBaseInfoById()��֤�̻���Ϣ�Ƿ���ڣ������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��֤�̻���Ϣ�Ƿ����");
		}
		return model;
	}

	/**
	 * ɾ��һ����¼
	 */
	public void deleteBaseInfo(MerchantBaseBo sm, UserData ud) throws OAException {
		Transaction t = this.getSession().beginTransaction();
		try {
			log.info("MerchantDaoHibernateHQLImpl.deleteItem(List newKeys, UserData ud)���ÿ�ʼ��ɾ��һ����¼��");

			t.begin();
			delete(sm, ud);
			// ������Ϣ
//			MerchantExtraModel me = null;
//			me = this.findExtraItem(, ud);
//			if (me != null) {
//				delete(me, ud);
//			}

			t.commit();

			log.info("MerchantDaoHibernateHQLImpl.deleteItem(List newKeys, UserData ud)�������ã�ɾ��һ����¼��");
		} catch (Exception e) {
			log.error("MerchantBaseModel.deleteItem(List newKeys, UserData ud)ɾ��һ����¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("ɾ���̻�������Ϣʱ���쳣��");
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
