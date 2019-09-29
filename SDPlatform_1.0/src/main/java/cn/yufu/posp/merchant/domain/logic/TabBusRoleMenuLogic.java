package cn.yufu.posp.merchant.domain.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.dao.hibernate.hql.TabBusRoleMenuDaoHibernateHQL;
import cn.yufu.posp.merchant.dao.hibernate.hql.TabBusRoleMenuDaoHibernateHQL;
import cn.yufu.posp.merchant.domain.model.TabBusRoleMenuModel;
import cn.yufu.posp.merchant.domain.model.MerchantUserRela;
import cn.yufu.posp.merchant.domain.model.TabBusinessRatesModel;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcTerminalDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.EdcNewfkterminalOrm;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalId;

public class TabBusRoleMenuLogic extends BaseLogic implements TabBusRoleMenuLogicInterface {

	private static final Log log = LogFactory.getLog("merchant");

	public TabBusRoleMenuLogic() {

	}

	/**
	 * 查询所有记录
	 */
	public PageInfoModel queryAll(TabBusRoleMenuModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("TabBusRoleMenuLogic.queryAll()开始调用：分页查询。" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			TabBusRoleMenuDaoHibernateHQL dao = (TabBusRoleMenuDaoHibernateHQL) getBean("tabBusRoleMenuDao");

			// 得到记录的总条数
			totalCount = dao.querySum(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);
			
			
			
			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("TabBusRoleMenuLogic.queryAll()结束调用：分页查询。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuLogic.queryAll()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	

	/**
	 * 显示一条记录
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		TabBusRoleMenuModel newModel = new TabBusRoleMenuModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("TabBusRoleMenuLogic.findItem()开始调用：显示一条记录。" + ud.getUserId());

			TabBusRoleMenuDaoHibernateHQL dao = (TabBusRoleMenuDaoHibernateHQL) getBean("tabBusRoleMenuDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("您要查找的信息不存在！");
			}

			// 传model
			parameteMap.put("Infolist", newModel);

			log.info("TabBusRoleMenuLogic.findItem()结束调用：显示一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuLogic.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * 修改一条记录
	 */
	public void saveItem(TabBusRoleMenuModel newModel, UserData ud) throws OAException {
		try {
			log.info("TabBusRoleMenuLogic.saveItem()开始调用：修改一条记录。" + ud.getUserId());
			TabBusRoleMenuDaoHibernateHQL dao = (TabBusRoleMenuDaoHibernateHQL) getBean("tabBusRoleMenuDao");
			dao.saveItem(newModel, ud);
			log.info("TabBusRoleMenuLogic.saveItem()结束调用：修改一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuLogic.saveItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * 新建一条记录
	 */
	public void createItem(TabBusRoleMenuModel newModel, UserData ud) throws OAException {
		try {
			log.info("TabBusRoleMenuLogic.createItem()开始调用：新建一条记录。" + ud.getUserId());
			TabBusRoleMenuDaoHibernateHQL dao = (TabBusRoleMenuDaoHibernateHQL) getBean("tabBusRoleMenuDao");
			
			dao.createItem(newModel, ud);

			log.info("TabBusRoleMenuLogic.createItem()结束调用：新建一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuLogic.createItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}

	//获取业务角色菜单列表
	public List<TabBusRoleMenuModel> findBusRoleList(UserData ud) throws OAException{
		TabBusRoleMenuDaoHibernateHQL dao = (TabBusRoleMenuDaoHibernateHQL) getBean("tabBusRoleMenuDao");
		return dao.findBusRoleList(ud);
	}
	
	//验证业务角色中文名称的唯一性
	public List<TabBusRoleMenuModel> findBusRoleNameKey(TabBusRoleMenuModel key, UserData ud) throws OAException {
		List<TabBusRoleMenuModel> list = new ArrayList<TabBusRoleMenuModel>();
		try {

			TabBusRoleMenuDaoHibernateHQL dao = (TabBusRoleMenuDaoHibernateHQL) getBean("tabBusRoleMenuDao");
			
			list = dao.findBusRoleNameKey(key, ud);
		} catch (Exception e) {
			log.info("验证验证业务角色中文名称的唯一性,出现异常");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return list;
	}	
	

	
	
}
