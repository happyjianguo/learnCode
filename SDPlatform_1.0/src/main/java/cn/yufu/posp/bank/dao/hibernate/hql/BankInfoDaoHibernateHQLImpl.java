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
	 * ��ѯ���ж���
	 */
	public List queryAll(BankInfoId newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("BankInfoDaoHibernateHQLImpl.querySum()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
			DetachedCriteria dcr = DetachedCriteria.forClass(BankInfoId.class);

			// ���̻���Ų�ѯ
			if (newQueryModel.getQueryBankId() != null && !newQueryModel.getQueryBankId().equals(""))
				dcr.add(Restrictions.like("bankId", "%" + newQueryModel.getQueryBankId() + "%"));
			// ����������
			if (newQueryModel.getQueryHostId() != null && !newQueryModel.getQueryHostId().equals(""))
				dcr.add(Restrictions.eq("bankType", newQueryModel.getQueryHostId()));

			// ���������Ϣ
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("updateDate"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			log.info("BankInfoDaoHibernateHQLImpl.querySum()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoDaoHibernateHQLImpl.querySum()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("��ѯ�������л�����Ϣʱ�����쳣");
		}
		return list;
	}

	/**
	 * ��ѯ���ж���ĸ���
	 */
	public int querySum(BankInfoId queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("BankInfoDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from BankInfoId o where 1=1");

			// ���̻���Ų�ѯ
			if (queryModel.getQueryBankId() != null && !queryModel.getQueryBankId().equals(""))
				bufferHql.append(" and o.bankId = '" + queryModel.getQueryBankId() + "'");

			// ���̻�״̬��ѯ
			if (queryModel.getQueryHostId() != null && !queryModel.getQueryHostId().equals(""))
				bufferHql.append(" and o.bankType = '" + queryModel.getQueryHostId() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("BankInfoDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯ���л�����Ϣ����ʱ�����쳣");
		}
		return count;
	}

	/**
	 * ɾ��һ����¼
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("BankInfoDaoHibernateHQLImpl.deleteItem()���ÿ�ʼ��ɾ��һ����¼��");

			// ����ɾ���������ӱ�һͬɾ��

			for (int i = 0; i < newKeys.size(); i++) {
				BankInfoId m = null;
				try {
					m = findItem(new String(newKeys.get(i) + ""), ud);
				} catch (Exception e) {
					throw new OAException("����Ҫɾ����Ϣʱ�����쳣��");
				}
				if (m != null) {
					StringBuffer bufferHql = new StringBuffer("delete from BANK_INFO  where BANK_ID='" + m.getBankId() + "' and" + " HOST_ID='" + m.getHostId()
							+ "'");
					String hql = bufferHql.toString();
					saveOrUpdateOrDeleteBySQL(hql, ud);
				} else {
					throw new OAException("��Ҫɾ������Ϣ�����ڣ�");
				}
			}
			log.info("BankInfoDaoHibernateHQLImpl.deleteItem()�������ã�ɾ��һ����¼��");
		} catch (Exception e) {
			log.error("BankInfoDaoHibernateHQLImpl.deleteItem()ɾ��һ����¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
	}

	/**
	 *��ʾһ����¼
	 */
	public BankInfoId findItem(String newKey, UserData ud) throws OAException {
		BankInfoId model = null;
		try {
			log.info("BankInfoDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			List list = null;
			Session session = getSession();
			// ������Ϣ
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
			log.info("BankInfoDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��ѯ���л�����Ϣʱ���쳣��");
		}

		return model;
	}

	/**
	 *�޸�һ����¼
	 */
	public void saveItem(BankInfoId newModel, UserData ud) throws OAException {
		try {
			log.info("BankInfoDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸�һ����¼��");
			log.info("�޸� bakid=" + newModel.getBankId());
			// ��֤����Ĺ�Ͻ�к��������Ƿ���ڣ�������ڣ�ͨ����֤
			try {
				log.info("BankInfoDaoHibernateHQLImpl.createItem()��ʼ���ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
				String newKey = newModel.getAdmBankId() + "," + newModel.getAdmHostId();
				BankInfoId sm = findItem(newKey, ud);
				log.info("BankInfoDaoHibernateHQLImpl.createItem()�������ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
				if (sm == null) {
					throw new OAException("������Ĺ�Ͻ���к�:" + newModel.getAdmBankId() + ",��Ͻ�������ţ�" + newModel.getAdmHostId() + " �ļ�¼�����ݿ��в����ڣ�����������");
				}
			} catch (Exception e) {
				throw new OAException(e.getMessage());
			}
			try {
				update(newModel, ud);
				log.info("BankInfoDaoHibernateHQLImpl.saveItem()�������ã��޸�һ����¼��");
			} catch (Exception e) {
				throw new OAException("�޸����л�����Ϣʱ�����쳣��");
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("BankInfoDaoHibernateHQLImpl.saveItem()�޸�һ����¼�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());
			throw new OAException(e.getMessage());

		}
	}

	/**
	 *�½�һ����¼
	 */
	public void createItem(BankInfoId newModel, UserData ud) throws OAException {

		// Ψһ����֤
		try {
			log.info("BankInfoDaoHibernateHQLImpl.createItem()��ʼ���ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			String newKey = newModel.getBankId() + "," + newModel.getHostId();
			BankInfoId sm = findItem(newKey, ud);
			log.info("BankInfoDaoHibernateHQLImpl.createItem()�������ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			if (sm != null) {
				throw new OAException("�����к�:" + newModel.getBankId() + ",�����ţ�" + newModel.getHostId() + " �ļ�¼�Ѿ������ݿ��д��ڣ�");
			}
		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}
		// ��֤����Ĺ�Ͻ�к��������Ƿ���ڣ�������ڣ�ͨ����֤
		try {
			log.info("BankInfoDaoHibernateHQLImpl.createItem()��ʼ���ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			String newKey = newModel.getAdmBankId() + "," + newModel.getAdmHostId();
			BankInfoId sm = findItem(newKey, ud);
			log.info("BankInfoDaoHibernateHQLImpl.createItem()�������ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			if (sm == null) {
				throw new OAException("������Ĺ�Ͻ���к�:" + newModel.getAdmBankId() + ",��Ͻ�������ţ�" + newModel.getAdmHostId() + " �ļ�¼�����ݿ��в����ڣ�����������");
			}
		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}

		try {
			log.info("BankInfoDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����Ϣ��");

			try {
				save(newModel, ud);
			} catch (Exception e) {
				throw new OAException("�������м�¼ʱ�����쳣");
			}

			log.info("BankInfoDaoHibernateHQLImpl.createItem()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("BankInfoDaoHibernateHQLImpl.createItem()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("�������м�¼ʱ�����쳣");
		}
	}

	/**
	 * ��ѯ������������
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
	 * �����������ͱ���ҵ�������������
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
		log.info("�޸�sql==" + sql);
		bulkUpdate(sql, ud);
	}

}
