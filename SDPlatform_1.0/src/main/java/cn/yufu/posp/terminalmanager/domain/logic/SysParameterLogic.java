package cn.yufu.posp.terminalmanager.domain.logic;

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
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.SysParameterDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.SysParameter;

public class SysParameterLogic extends BaseLogic implements SysParameterLogicInterface {

	private static final Log log = LogFactory.getLog("sysParameter");

	/**
	 * @roseuid 44BAF7190128
	 */
	public SysParameterLogic() {

	}

	public void createItem(SysParameter sysParameter, UserData ud) throws OAException {
		try {
			log.info("SysParameterLogic.createItem()开始调用：新建一条信息。");

			SysParameterDaoHibernateHQL dao = (SysParameterDaoHibernateHQL) getBean("SysParameterDao");
			dao.createItem(sysParameter, ud);

			log.info("SysParameterLogic.createItem()结束调用：新建一条信息。");

		} catch (Exception e) {

			log.info("SysParameterLogic.createItem()新建一条信息，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("SysParameterLogic.deleteItem()开始调用：删除信息。");

			SysParameterDaoHibernateHQL dao = (SysParameterDaoHibernateHQL) getBean("SysParameterDao");
			dao.deleteItem(keys, ud);

			log.info("SysParameterLogic.deleteItem()结束调用：删除信息。");

		} catch (Exception e) {

			log.info("SysParameterLogic.deleteItem()删除信息，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public HashMap findItemByKey(SysParameter key, UserData ud) throws OAException {
		SysParameter newModel = new SysParameter();
		HashMap map = new HashMap();
		try {
			log.info("SysParameterLogic.findItemByKey()开始调用：查找一条信息。");

			SysParameterDaoHibernateHQL dao = (SysParameterDaoHibernateHQL) getBean("SysParameterDao");
			newModel = dao.findItemByKey(key, ud);

			map.put("Infolist", newModel);

			log.info("SysParameterLogic.findItemByKey()结束调用：查找一条信息。");

		} catch (Exception e) {

			log.info("SysParameterLogic.findItemByKey()查找一条信息，出现异常");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public PageInfoModel queryAllItem(SysParameter sysParameter, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("SysParameterLogic.queryAllItem()开始调用：分页查询");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			SysParameterDaoHibernateHQL dao = (SysParameterDaoHibernateHQL) getBean("SysParameterDao");

			// 得到记录的总条数
			totalCount = dao.queryCount(sysParameter, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(sysParameter, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("长度==" + list.size() + "起始位长度==" + startIndex + "页码数==" + (startIndex + pageSize) / pageSize + "总记录数==" + totalCount + "");
			log.info("SysParameterLogic.queryAllItem()结束调用：分页查询");

		} catch (Exception e) {

			log.info("SysParameterLogic.queryAllItem()分页查询，出现异常");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	public void saveItem(SysParameter sysParameter, UserData ud) throws OAException {
		try {
			log.info("SysParameterLogic.saveItem()开始调用：修改一条信息");

			SysParameterDaoHibernateHQL dao = (SysParameterDaoHibernateHQL) getBean("SysParameterDao");
			dao.saveItem(sysParameter, ud);

			log.info("SysParameterLogic.saveItem()结束调用：修改一条信息");

		} catch (Exception e) {

			log.info("SysParameterLogic.saveItem()修改一条信息，出现异常");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	@Override
	public List queryAllItem(SysParameter sysParameter, UserData ud) throws OAException {
		List list = new ArrayList();

		try {
			log.info("SysParameterLogic.queryAllItem()开始调用：分页查询");
			SysParameterDaoHibernateHQL dao = (SysParameterDaoHibernateHQL) getBean("SysParameterDao");

			list = dao.queryAllItem(sysParameter, ud);

			log.info("SysParameterLogic.queryAllItem()结束调用：分页查询");

		} catch (Exception e) {

			log.info("SysParameterLogic.queryAllItem()分页查询，出现异常");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return list;
	}

	@Override
	public SysParameter findItemByKeyValue(SysParameter key, UserData ud) throws OAException {
		SysParameter newModel = new SysParameter();
		try {
			log.info("SysParameterLogic.findItemByKeyValue()开始调用：查找一条信息。");

			SysParameterDaoHibernateHQL dao = (SysParameterDaoHibernateHQL) getBean("SysParameterDao");
			newModel = dao.findItemByKeyValue(key, ud);

			log.info("SysParameterLogic.findItemByKeyValue()结束调用：查找一条信息。");

		} catch (Exception e) {

			log.info("SysParameterLogic.findItemByKeyValue()查找一条信息，出现异常");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return newModel;
	}

}
