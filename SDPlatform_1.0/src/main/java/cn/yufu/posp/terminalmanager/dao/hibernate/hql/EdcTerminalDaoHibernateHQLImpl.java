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
			log.info("EdcTerminalDaoHibernateHQLImpl.createItem()开始调用：添加一条终端资料记录。");

			save(edcTerminal, ud);

			//创建数据到终端业务角色信息表
			if(edcTerminal.getSoftVer().trim().equals("mul")){
				TabTermIdBusRoleModel tModel = new TabTermIdBusRoleModel();	
				tModel.setMerchantId(edcTerminal.getId().getMerchantId());
				tModel.setTerminalId(edcTerminal.getId().getTerminalId());
				tModel.setBusRoleId(edcTerminal.getBusRoleId());
				save(tModel, ud);	
			}
			
			log.info("EdcTerminalDaoHibernateHQLImpl.createItem()结束调用：添加一条终端资料记录。");
		} catch (Exception e) {

			log.info("EdcTerminalDaoHibernateHQLImpl.createItem()添加一条终端资料记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalDaoHibernateHQLImpl.deleteItem()开始调用：删除终端资料记录。");

			for (int i = 0; i < keys.size(); i++) {
				delete(findItemByKey((EdcTerminal) keys.get(i), ud), ud);
				
				// 终端业务角色信息表
				TabTermIdBusRoleModel me = null;
				me = this.findTabTermIdBusRole((EdcTerminal)keys.get(i), ud);
				if (me != null) {
					delete(me, ud);
				}
			}

			
			log.info("EdcTerminalDaoHibernateHQLImpl.deleteItem()结束调用：删除终端资料记录。");
		} catch (Exception e) {

			log.info("EdcTerminalDaoHibernateHQLImpl.deleteItem()删除终端资料记录，出现异常。");
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
			log.info("EdcTerminalDaoHibernateHQLImpl.findItemByKey()开始调用：查找一条终端资料记录。");

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
			
			//添加终端业务角色信息
			TabTermIdBusRoleModel tModel = this.findTabTermIdBusRole(edcTerminal, ud);
			if (tModel != null) {
				model.setBusRoleId(tModel.getBusRoleId() == null ? "" : tModel.getBusRoleId());
				model.setBusRoleName(findBusRoleName(tModel.getBusRoleId(),ud).trim());
			}

			log.info(" 查询语句: hql==" + hql);
			log.info("EdcTerminalDaoHibernateHQLImpl.findItemByKey()结束调用：查找一条终端资料记录。");
		} catch (Exception e) {

			log.info("EdcTerminalDaoHibernateHQLImpl.findItemByKey()查找一条终端资料记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	/**
	 * 查询终端业务角色信息
	 */
	public TabTermIdBusRoleModel findTabTermIdBusRole(EdcTerminal edcTerminal, UserData ud) throws OAException {
		TabTermIdBusRoleModel tModel = null;
		try {
			log.info("EdcTerminalDaoHibernateHQLImpl.findTabTermIdBusRole()开始调用：查询终端业务角色信息对象。");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(TabTermIdBusRoleModel.class);
			cr.add(Restrictions.eq("merchantId", edcTerminal.getId().getMerchantId()));
			cr.add(Restrictions.eq("terminalId", edcTerminal.getId().getTerminalId()));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				tModel = (TabTermIdBusRoleModel) list.get(0);

			log.info("MerchantDaoHibernateHQLImpl.findExtraItem()结束调用：查询终端业务角色信息对象。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.findExtraItem()查询终端业务角色信息对象，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return tModel;
	}
	
	
	
	/**
	 * 查询业务角色菜单信息
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
			log.info("EdcTerminalDaoHibernateHQLImpl.queryAllItem()开始调用：查找符合条件的记录。");

			StringBuffer bufferHql = new StringBuffer(" from EdcTerminal  t where 1=1");

			if (edcTerminal.getId() != null) {
				// 按商户编号查询
				if (edcTerminal.getId().getMerchantId() != null && !edcTerminal.getId().getMerchantId().equals(""))
					bufferHql.append(" and t.id.merchantId = '" + edcTerminal.getId().getMerchantId() + "'");

				// 按终端编号查询
				if (edcTerminal.getId().getTerminalId() != null && !edcTerminal.getId().getTerminalId().equals(""))
					bufferHql.append(" and t.id.terminalId = '" + edcTerminal.getId().getTerminalId() + "'");
			}
			// 按终端类型查询
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
			// 添加排序信息
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

			log.info("查询语句==" + hql);

			log.info("EdcTerminalDaoHibernateHQLImpl.queryAllItem()结束调用：查找符合条件的记录。");
		} catch (Exception e) {
			log.info("EdcTerminalDaoHibernateHQLImpl.queryAllItem()查找符合条件的记录，出现异常。");
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
			log.info("EdcTerminalDaoHibernateHQLImpl.queryCount()开始调用：查询符合条件的终端资料数量。");

			StringBuffer bufferHql = new StringBuffer("select count(*) from EdcTerminal  t where 1=1");

			if (edcTerminal.getId() != null) {
				// 按商户编号查询
				if (edcTerminal.getId().getMerchantId() != null && !edcTerminal.getId().getMerchantId().equals(""))
					bufferHql.append(" and t.id.merchantId = '" + edcTerminal.getId().getMerchantId() + "'");

				// 按终端编号查询
				if (edcTerminal.getId().getTerminalId() != null && !edcTerminal.getId().getTerminalId().equals(""))
					bufferHql.append(" and t.id.terminalId = '" + edcTerminal.getId().getTerminalId() + "'");
			}
			// 按终端类型查询
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

			log.info("查询语句==" + hql);
			log.info("EdcTerminalDaoHibernateHQLImpl.queryCount()出现异常：查询符合条件的终端资料数量。");
		} catch (Exception e) {

			log.info("EdcTerminalDaoHibernateHQLImpl.queryCount()查询符合条件的终端资料数量，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	public void saveItem(EdcTerminal edcTerminal, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalDaoHibernateHQLImpl.saveItem()开始调用：保存一条终端资料记录。");

			update(edcTerminal, ud);

			//创建数据到终端业务角色信息表
			if(edcTerminal.getSoftVer().trim().equals("mul")){
				TabTermIdBusRoleModel tModel = new TabTermIdBusRoleModel();	
				tModel.setMerchantId(edcTerminal.getId().getMerchantId());
				tModel.setTerminalId(edcTerminal.getId().getTerminalId());
				tModel.setBusRoleId(edcTerminal.getBusRoleId());
				saveOrUpdate(tModel,ud);
			}else{
				// 终端业务角色信息表
				TabTermIdBusRoleModel me = null;
				me = this.findTabTermIdBusRole(edcTerminal, ud);
				if (me != null) {
					delete(me, ud);
				}
			}
			log.info("EdcTerminalDaoHibernateHQLImpl.saveItem()结束调用：保存一条终端资料记录。");
		} catch (Exception e) {

			log.info("EdcTerminalDaoHibernateHQLImpl.saveItem()保存一条终端资料记录，出现异常。");
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
			log.info("EdcTerminalDaoHibernateHQLImpl.queryTerminalListByMerchantId()开始调用：查找符合条件的记录。");
			StringBuffer bufferHql = new StringBuffer(" from EdcTerminal  t where t.id.merchantId='"+merchantId+"'");
			bufferHql.append(" and (t.id.merchantId,t.id.terminalId) not in ");
			bufferHql.append(" (select a.merchantId,a.terminalId from EdcCardBinArea a where a.cardBin='"+cardBin+"') ");
			bufferHql.append(" order by t.id.terminalId asc ");

			String hql = bufferHql.toString();
			terminalList = findByHQL(hql, ud);
			log.info("查询语句==" + hql);
			log.info("EdcTerminalDaoHibernateHQLImpl.queryTerminalListByMerchantId()结束调用：查找符合条件的记录。");
		} catch (Exception e) {
			log.info("EdcTerminalDaoHibernateHQLImpl.queryTerminalListByMerchantId()查找符合条件的记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return terminalList;
	}
	
	/**
	 * 判断所有的终端是否都是该商户的
	 * @param merchantId
	 * @param terminals
	 * @param ud
	 * @return	true：有不是该商户的终端；false：都是该商户的终端
	 * @throws OAException
	 */
	public boolean checkTerminalIsNotMechants(String merchantId, String terminals,
			UserData ud) throws OAException {
		int count = 0;
		String terminalIdStr="'"+terminals.replaceAll(",", "','")+"'";
		String terId[]=terminals.split(",");
		int terIdSize=terId.length;
		try {
			log.info("EdcTerminalDaoHibernateHQLImpl.queryTerminalListByMerchantId()开始调用：查找符合条件的记录。");
			StringBuffer bufferHql = new StringBuffer(
					"select count(*) from EdcTerminal  t where t.id.merchantId='"
							+ merchantId + "' ");
			bufferHql.append(" and t.id.terminalId in ("+terminalIdStr+")");
			String hql = bufferHql.toString();
			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());

			log.info("查询语句==" + hql);
			log.info("EdcTerminalDaoHibernateHQLImpl.queryTerminalListByMerchantId()结束调用：查找符合条件的记录。");
		} catch (Exception e) {
			log.info("EdcTerminalDaoHibernateHQLImpl.queryTerminalListByMerchantId()查找符合条件的记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return terIdSize-count>0;
	}

}
