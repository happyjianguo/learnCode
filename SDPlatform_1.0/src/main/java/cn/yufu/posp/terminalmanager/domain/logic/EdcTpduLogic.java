package cn.yufu.posp.terminalmanager.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcTpduDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.SysParameterDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.SysParameter;
import cn.yufu.posp.terminalmanager.domain.model.SysParameterId;
import cn.yufu.posp.terminalmanager.domain.model.TPreTpdu;

public class EdcTpduLogic extends BaseLogic implements EdcTpduLogicInterface {

	private static final Log log = LogFactory.getLog("edcTpdu");

	/**
	 * @roseuid 44BAF7190128
	 */
	public EdcTpduLogic() {

	}

	public void createItem(TPreTpdu tPreTpdu, UserData ud) throws OAException {
		try {
			log.info("EdcTpduLogic.createItem(TPreTpdu tPreTpdu, UserData ud)开始调用：新建终端TPDU信息。");

			EdcTpduDaoHibernateHQL dao = (EdcTpduDaoHibernateHQL) getBean("EdcTpduDao");
			dao.createItem(tPreTpdu, ud);

			log.info("EdcTpduLogic.createItem(TPreTpdu tPreTpdu, UserData ud)结束调用：新建终端TPDU信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTpduLogic.createItem(TPreTpdu tPreTpdu, UserData ud)调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcTpduLogic.deleteItem(List keys, UserData ud)开始调用：删除终端TPDU信息。");

			EdcTpduDaoHibernateHQL dao = (EdcTpduDaoHibernateHQL) getBean("EdcTpduDao");
			dao.deleteItem(keys, ud);

			log.info("EdcTpduLogic.deleteItem(List keys, UserData ud)结束调用：删除终端TPDU信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTpduLogic.deleteItem(List keys, UserData ud)调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public HashMap findItemByKey(String key, UserData ud) throws OAException {
		TPreTpdu newModel = new TPreTpdu();
		HashMap map = new HashMap();
		try {
			log.info("EdcTpduLogic.findItemByKey(String key, UserData ud)开始调用：查找一条终端TPDU信息。");

			EdcTpduDaoHibernateHQL dao = (EdcTpduDaoHibernateHQL) getBean("EdcTpduDao");
			newModel = dao.findItemByKey(key, ud);

			map.put("Infolist", newModel);

			log.info("EdcTpduLogic.findItemByKey(String key, UserData ud)结束调用：查找一条终端TPDU信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTpduLogic.findItemByKey(String key, UserData ud)查找一条终端TPDU信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public PageInfoModel queryAllItem(TPreTpdu tPreTpdu, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("==EdcTpduLogic.EdcTpduLogic.queryAllItem()开始调用：分页查询。");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			EdcTpduDaoHibernateHQL dao = (EdcTpduDaoHibernateHQL) getBean("EdcTpduDao");

			// 得到记录的总条数
			totalCount = dao.queryCount(tPreTpdu, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(tPreTpdu, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			SysParameterDaoHibernateHQL tranTypeDao = (SysParameterDaoHibernateHQL) getBean("SysParameterDao");
			for (int i = 0; i < list.size(); i++) {
				SysParameter sysParameter = new SysParameter();
				SysParameterId id = new SysParameterId();
				id.setParamType("MODULE_ID");
				sysParameter.setId(id);
				sysParameter.setParamValue(String.valueOf(((TPreTpdu) list.get(i)).getModuleId()));
				SysParameter parameter = tranTypeDao.findItemByKeyValue(sysParameter, ud);
				((TPreTpdu) list.get(i)).setCh_moduleId(parameter.getId().getParamName());
			}

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("EdcTpduLogic.queryAllItem()结束调用：分页查询。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("=EdcTpduLogic.queryAllItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	public void saveItem(TPreTpdu tPreTpdu, UserData ud) throws OAException {
		try {
			log.info("EdcTpduLogic.saveItem(TPreTpdu tPreTpdu, UserData ud)开始调用：保存 、修改 终端TPDU信息。");

			EdcTpduDaoHibernateHQL dao = (EdcTpduDaoHibernateHQL) getBean("EdcTpduDao");
			dao.saveItem(tPreTpdu, ud);

			log.info("EdcTpduLogic.saveItem(TPreTpdu tPreTpdu, UserData ud)结束调用：保存 、修改 终端TPDU信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTpduLogic.saveItem(TPreTpdu tPreTpdu, UserData ud)保存 、修改 调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

}
