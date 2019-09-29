package cn.yufu.posp.sysModule.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.sysModule.dao.hibernate.hql.SysModuleDaoHibernateHQLImpl;
import cn.yufu.posp.sysModule.domain.model.SysModuleModel;

public class SysModuleLogic extends BaseLogic implements SysModuleLogicInterface {

	private static final Log log = LogFactory.getLog("sys");

	public SysModuleLogic() {

	}

	/**
	 * 查找所有记录
	 */
	public PageInfoModel queryAll(SysModuleModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("SysModuleLogic.queryAll()开始调用：分页查询。" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			SysModuleDaoHibernateHQLImpl dao = (SysModuleDaoHibernateHQLImpl) getBean("sysModuleDao");

			// 得到记录的总条数
			totalCount = dao.querySum(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("SysModuleLogic.queryAll()结束调用：分页查询。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleLogic.queryAll()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return page;
	}

	/**
	 * 删除一条记录
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("SysModuleLogic.deleteItem()开始调用：删除一条记录。" + ud.getUserId());

			SysModuleDaoHibernateHQLImpl dao = (SysModuleDaoHibernateHQLImpl) getBean("sysModuleDao");

			dao.deleteItem(newKeys, ud);

			log.info("SysModuleLogic.deleteItem()结束调用：删除一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleLogic.deleteItem()删除一条记录,调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * 新建一条记录
	 */
	public void createItem(SysModuleModel newModel, UserData ud) throws OAException {
		try {
			log.info("SysModuleLogic.createItem()开始调用：新建一条记录。" + ud.getUserId());

			SysModuleDaoHibernateHQLImpl dao = (SysModuleDaoHibernateHQLImpl) getBean("sysModuleDao");
			dao.createItem(newModel, ud);

			log.info("SysModuleLogic.createItem()结束调用：新建一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleLogic.createItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * 显示一条记录
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		SysModuleModel newModel = new SysModuleModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("SysModuleLogic.findItem()开始调用：显示一条记录。" + ud.getUserId());

			SysModuleDaoHibernateHQLImpl dao = (SysModuleDaoHibernateHQLImpl) getBean("sysModuleDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {

				throw new OAException("您要查找的信息不存在！");
			}
			// 传model
			parameteMap.put("Infolist", newModel);

			log.info("SysModuleLogic.findItem()结束调用：显示一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleLogic.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * 修改一条记录
	 */
	public void saveItem(SysModuleModel newModel, UserData ud) throws OAException {
		try {
			log.info("SysModuleLogic.saveItem()开始调用：修改一条记录。" + ud.getUserId());
			SysModuleDaoHibernateHQLImpl dao = (SysModuleDaoHibernateHQLImpl) getBean("sysModuleDao");
			dao.saveItem(newModel, ud);
			log.info("SysModuleLogic.saveItem()结束调用：修改一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleLogic.saveItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

}
