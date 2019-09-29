package cn.yufu.posp.terminalmanager.dao.hibernate.hql;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.TabBusRoleMenuModel;
import cn.yufu.posp.merchant.domain.model.TabTermIdBusRoleModel;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;

public class EdcTerminalDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements EdcTerminalDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("edcTerminal");

	/**
	 * @roseuid 44BAF72B0232
	 */
	public EdcTerminalDaoHibernateHQLImpl() {
	}

	public void createItem(EdcTerminal edcTerminal, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalDaoHibernateHQLImpl.createItem()��ʼ���ã����һ���ն����ϼ�¼��");

			save(edcTerminal, ud);

			//�������ݵ��ն�ҵ���ɫ��Ϣ��
			if(edcTerminal.getSoftVer().trim().equals("mul")){
				TabTermIdBusRoleModel tModel = new TabTermIdBusRoleModel();	
				tModel.setMerchantId(edcTerminal.getId().getMerchantId());
				tModel.setTerminalId(edcTerminal.getId().getTerminalId());
				tModel.setBusRoleId(edcTerminal.getBusRoleId());
				save(tModel, ud);	
			}
			
			log.info("EdcTerminalDaoHibernateHQLImpl.createItem()�������ã����һ���ն����ϼ�¼��");
		} catch (Exception e) {

			log.info("EdcTerminalDaoHibernateHQLImpl.createItem()���һ���ն����ϼ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalDaoHibernateHQLImpl.deleteItem()��ʼ���ã�ɾ���ն����ϼ�¼��");

			for (int i = 0; i < keys.size(); i++) {
				delete(findItemByKey((EdcTerminal) keys.get(i), ud), ud);
				
				// �ն�ҵ���ɫ��Ϣ��
				TabTermIdBusRoleModel me = null;
				me = this.findTabTermIdBusRole((EdcTerminal)keys.get(i), ud);
				if (me != null) {
					delete(me, ud);
				}
			}

			
			log.info("EdcTerminalDaoHibernateHQLImpl.deleteItem()�������ã�ɾ���ն����ϼ�¼��");
		} catch (Exception e) {

			log.info("EdcTerminalDaoHibernateHQLImpl.deleteItem()ɾ���ն����ϼ�¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public EdcTerminal findItemByKey(EdcTerminal edcTerminal, UserData ud) throws OAException {
		List<EdcTerminal> edcTerminalList = new ArrayList<EdcTerminal>();
		EdcTerminal model = new EdcTerminal();
		try {
			log.info("EdcTerminalDaoHibernateHQLImpl.findItemByKey()��ʼ���ã�����һ���ն����ϼ�¼��");

			StringBuffer bufferHql = new StringBuffer(" from EdcTerminal  t where 1=1");

			if (edcTerminal.getId() != null) {

				if (edcTerminal.getId().getMerchantId() != null && !edcTerminal.getId().getMerchantId().equals(""))
					bufferHql.append(" and t.id.merchantId = '" + edcTerminal.getId().getMerchantId() + "'");

				if (edcTerminal.getId().getTerminalId() != null && !edcTerminal.getId().getTerminalId().equals(""))
					bufferHql.append(" and t.id.terminalId = '" + edcTerminal.getId().getTerminalId() + "'");

			}

			String hql = bufferHql.toString();
			edcTerminalList = findByHQL(hql, ud);

			if (edcTerminalList.size() > 0)
				model = (EdcTerminal) edcTerminalList.get(0);
			
			//����ն�ҵ���ɫ��Ϣ
			TabTermIdBusRoleModel tModel = this.findTabTermIdBusRole(edcTerminal, ud);
			if (tModel != null) {
				model.setBusRoleId(tModel.getBusRoleId() == null ? "" : tModel.getBusRoleId());
				model.setBusRoleName(findBusRoleName(tModel.getBusRoleId(),ud).trim());
			}

			log.info(" ��ѯ���: hql==" + hql);
			log.info("EdcTerminalDaoHibernateHQLImpl.findItemByKey()�������ã�����һ���ն����ϼ�¼��");
		} catch (Exception e) {

			log.info("EdcTerminalDaoHibernateHQLImpl.findItemByKey()����һ���ն����ϼ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	/**
	 * ��ѯ�ն�ҵ���ɫ��Ϣ
	 */
	public TabTermIdBusRoleModel findTabTermIdBusRole(EdcTerminal edcTerminal, UserData ud) throws OAException {
		TabTermIdBusRoleModel tModel = null;
		try {
			log.info("EdcTerminalDaoHibernateHQLImpl.findTabTermIdBusRole()��ʼ���ã���ѯ�ն�ҵ���ɫ��Ϣ����");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(TabTermIdBusRoleModel.class);
			cr.add(Restrictions.eq("merchantId", edcTerminal.getId().getMerchantId()));
			cr.add(Restrictions.eq("terminalId", edcTerminal.getId().getTerminalId()));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				tModel = (TabTermIdBusRoleModel) list.get(0);

			log.info("MerchantDaoHibernateHQLImpl.findExtraItem()�������ã���ѯ�ն�ҵ���ɫ��Ϣ����");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.findExtraItem()��ѯ�ն�ҵ���ɫ��Ϣ���󣬳����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return tModel;
	}
	
	
	
	/**
	 * ��ѯҵ���ɫ�˵���Ϣ
	 */
	public String findBusRoleName(String busRoleId, UserData ud) throws OAException {
		String busRoleName = "";
		TabBusRoleMenuModel bt = null;
		StringBuilder sb = new StringBuilder(" from TabBusRoleMenuModel where 1=1 ");
		sb.append(" and busRoleId = "+busRoleId.trim());
		List list = findByHQL(sb.toString(), ud);
		if (list != null && list.size() > 0) {
			bt = (TabBusRoleMenuModel) list.get(0);
			if (bt != null) {
				busRoleName = bt.getBusRoleName();
			}
		}
		return busRoleName;
	}
	
	@SuppressWarnings("unchecked")
	public List queryAllItem(EdcTerminal edcTerminal, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List<EdcTerminal> list = new ArrayList<EdcTerminal>();
		try {
			log.info("EdcTerminalDaoHibernateHQLImpl.queryAllItem()��ʼ���ã����ҷ��������ļ�¼��");

			StringBuffer bufferHql = new StringBuffer(" from EdcTerminal  t where 1=1");

			if (edcTerminal.getId() != null) {
				// ���̻���Ų�ѯ
				if (edcTerminal.getId().getMerchantId() != null && !edcTerminal.getId().getMerchantId().equals(""))
					bufferHql.append(" and t.id.merchantId = '" + edcTerminal.getId().getMerchantId() + "'");

				// ���ն˱�Ų�ѯ
				if (edcTerminal.getId().getTerminalId() != null && !edcTerminal.getId().getTerminalId().equals(""))
					bufferHql.append(" and t.id.terminalId = '" + edcTerminal.getId().getTerminalId() + "'");
			}
			// ���ն����Ͳ�ѯ
			if (edcTerminal.getSoftVer() != null&& !edcTerminal.getSoftVer().equals("")) {
				if ("mul".equals(edcTerminal.getSoftVer().trim())) {
					bufferHql.append(" and t.softVer = '" + edcTerminal.getSoftVer() + "'");
				}
				if ("common".equals(edcTerminal.getSoftVer().trim())) {
					bufferHql.append(" and t.softVer != '" + "mul" + "'");
					bufferHql.append(" or t.softVer is null");
				}
			}
			
			if (edcTerminal.getDownloadMode() != null && !edcTerminal.getDownloadMode().equals(""))
				bufferHql.append(" and t.downloadMode = '" + edcTerminal.getDownloadMode() + "'");
			// ���������Ϣ
			if (sortfield != null && sortType != null) {
				bufferHql.append(" order by t." + sortfield + " " + sortType + " ");
			} else {
				bufferHql.append(Order.desc(" order by t.id.merchantId desc "));
			}

			String hql = bufferHql.toString();

			List<EdcTerminal> edcTerminalList = new ArrayList<EdcTerminal>();
			edcTerminalList = findByHQL(hql, ud);
			int endIndex = startIndex + maxresults;
			if (endIndex > edcTerminalList.size())
				endIndex = edcTerminalList.size();

			list = edcTerminalList.subList(startIndex, endIndex);

			log.info("��ѯ���==" + hql);

			log.info("EdcTerminalDaoHibernateHQLImpl.queryAllItem()�������ã����ҷ��������ļ�¼��");
		} catch (Exception e) {
			log.info("EdcTerminalDaoHibernateHQLImpl.queryAllItem()���ҷ��������ļ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int queryCount(EdcTerminal edcTerminal, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("EdcTerminalDaoHibernateHQLImpl.queryCount()��ʼ���ã���ѯ�����������ն�����������");

			StringBuffer bufferHql = new StringBuffer("select count(*) from EdcTerminal  t where 1=1");

			if (edcTerminal.getId() != null) {
				// ���̻���Ų�ѯ
				if (edcTerminal.getId().getMerchantId() != null && !edcTerminal.getId().getMerchantId().equals(""))
					bufferHql.append(" and t.id.merchantId = '" + edcTerminal.getId().getMerchantId() + "'");

				// ���ն˱�Ų�ѯ
				if (edcTerminal.getId().getTerminalId() != null && !edcTerminal.getId().getTerminalId().equals(""))
					bufferHql.append(" and t.id.terminalId = '" + edcTerminal.getId().getTerminalId() + "'");
			}
			// ���ն����Ͳ�ѯ
			if (edcTerminal.getSoftVer() != null&& !edcTerminal.getSoftVer().equals("")) {
				if ("mul".equals(edcTerminal.getSoftVer().trim())) {
					bufferHql.append(" and t.softVer = '" + edcTerminal.getSoftVer() + "'");
				}
				if ("common".equals(edcTerminal.getSoftVer().trim())) {
					bufferHql.append(" and t.softVer != '" + "mul" + "'");
					bufferHql.append(" or t.softVer is null");
				}
			}
			if (edcTerminal.getDownloadMode() != null && !edcTerminal.getDownloadMode().equals(""))
				bufferHql.append(" and t.downloadMode = '" + edcTerminal.getDownloadMode() + "'");
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());

			log.info("��ѯ���==" + hql);
			log.info("EdcTerminalDaoHibernateHQLImpl.queryCount()�����쳣����ѯ�����������ն�����������");
		} catch (Exception e) {

			log.info("EdcTerminalDaoHibernateHQLImpl.queryCount()��ѯ�����������ն����������������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	public void saveItem(EdcTerminal edcTerminal, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalDaoHibernateHQLImpl.saveItem()��ʼ���ã�����һ���ն����ϼ�¼��");

			update(edcTerminal, ud);

			//�������ݵ��ն�ҵ���ɫ��Ϣ��
			if(edcTerminal.getSoftVer().trim().equals("mul")){
				TabTermIdBusRoleModel tModel = new TabTermIdBusRoleModel();	
				tModel.setMerchantId(edcTerminal.getId().getMerchantId());
				tModel.setTerminalId(edcTerminal.getId().getTerminalId());
				tModel.setBusRoleId(edcTerminal.getBusRoleId());
				saveOrUpdate(tModel,ud);
			}else{
				// �ն�ҵ���ɫ��Ϣ��
				TabTermIdBusRoleModel me = null;
				me = this.findTabTermIdBusRole(edcTerminal, ud);
				if (me != null) {
					delete(me, ud);
				}
			}
			log.info("EdcTerminalDaoHibernateHQLImpl.saveItem()�������ã�����һ���ն����ϼ�¼��");
		} catch (Exception e) {

			log.info("EdcTerminalDaoHibernateHQLImpl.saveItem()����һ���ն����ϼ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}
	
	public List<EdcTerminal> queryTerminalListByMerchantId(String merchantId,
			String cardBin, UserData ud) throws OAException {
		List<EdcTerminal> terminalList = new ArrayList<EdcTerminal>();
		List list = null;
		try {
			log.info("EdcTerminalDaoHibernateHQLImpl.queryTerminalListByMerchantId()��ʼ���ã����ҷ��������ļ�¼��");
			StringBuffer bufferHql = new StringBuffer(" from EdcTerminal  t where t.id.merchantId='"+merchantId+"'");
			bufferHql.append(" and (t.id.merchantId,t.id.terminalId) not in ");
			bufferHql.append(" (select a.merchantId,a.terminalId from EdcCardBinArea a where a.cardBin='"+cardBin+"') ");
			bufferHql.append(" order by t.id.terminalId asc ");

			String hql = bufferHql.toString();
			terminalList = findByHQL(hql, ud);
			log.info("��ѯ���==" + hql);
			log.info("EdcTerminalDaoHibernateHQLImpl.queryTerminalListByMerchantId()�������ã����ҷ��������ļ�¼��");
		} catch (Exception e) {
			log.info("EdcTerminalDaoHibernateHQLImpl.queryTerminalListByMerchantId()���ҷ��������ļ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return terminalList;
	}
	
	/**
	 * �ж����е��ն��Ƿ��Ǹ��̻���
	 * @param merchantId
	 * @param terminals
	 * @param ud
	 * @return	true���в��Ǹ��̻����նˣ�false�����Ǹ��̻����ն�
	 * @throws OAException
	 */
	public boolean checkTerminalIsNotMechants(String merchantId, String terminals,
			UserData ud) throws OAException {
		int count = 0;
		String terminalIdStr="'"+terminals.replaceAll(",", "','")+"'";
		String terId[]=terminals.split(",");
		int terIdSize=terId.length;
		try {
			log.info("EdcTerminalDaoHibernateHQLImpl.queryTerminalListByMerchantId()��ʼ���ã����ҷ��������ļ�¼��");
			StringBuffer bufferHql = new StringBuffer(
					"select count(*) from EdcTerminal  t where t.id.merchantId='"
							+ merchantId + "' ");
			bufferHql.append(" and t.id.terminalId in ("+terminalIdStr+")");
			String hql = bufferHql.toString();
			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());

			log.info("��ѯ���==" + hql);
			log.info("EdcTerminalDaoHibernateHQLImpl.queryTerminalListByMerchantId()�������ã����ҷ��������ļ�¼��");
		} catch (Exception e) {
			log.info("EdcTerminalDaoHibernateHQLImpl.queryTerminalListByMerchantId()���ҷ��������ļ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return terIdSize-count>0;
	}

}
