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
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcBlackDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.EdcBlack;

public class EdcBlackLogic extends BaseLogic implements EdcBlackLogicInterface {

	private static final Log log = LogFactory.getLog("edcTerminal");

	/**
	 * @roseuid 44BAF7190128
	 */
	public EdcBlackLogic() {

	}

	public void createItem(EdcBlack edcTerminal, UserData ud) throws OAException {
		try {
			log.info("EdcBlackLogic.createItem()开始调用：新建信息。");

			EdcBlackDaoHibernateHQL dao = (EdcBlackDaoHibernateHQL) getBean("edcBlackDao");
			dao.createItem(edcTerminal, ud);

			log.info("EdcBlackLogic.createItem()结束调用：新建信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcBlackLogic.createItem()新建信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcBlackLogic.deleteItem()开始调用：删除信息。");

			// 删除终端资料设定
			EdcBlackDaoHibernateHQL dao = (EdcBlackDaoHibernateHQL) getBean("edcBlackDao");
			dao.deleteItem(keys, ud);

			log.info("EdcBlackLogic.deleteItem()结束调用：删除信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcBlackLogic.deleteItem()删除信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public HashMap findItemByKey(EdcBlack key, UserData ud) throws OAException {
		EdcBlack newModel = new EdcBlack();
		HashMap map = new HashMap();
		try {
			log.info("EdcBlackLogic.findItemByKey()开始调用：查找一条信息。");

			EdcBlackDaoHibernateHQL dao = (EdcBlackDaoHibernateHQL) getBean("edcBlackDao");
			newModel = dao.findItemByKey(key, ud);

			if (newModel != null) {
				EdcCommonDaoHibernateHQL commondao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
				newModel.setMerchantName(commondao.findMerchantNameByKey(newModel.getMerchantId(), ud));
			}
			map.put("Infolist", newModel);

			log.info("EdcBlackLogic.findItemByKey()结束调用：查找一条信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcBlackLogic.findItemByKey()查找一条信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public PageInfoModel queryAllItem(EdcBlack edcTerminal, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("EdcBlackLogic.queryAllItem()开始调用：分页查询。");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			EdcBlackDaoHibernateHQL dao = (EdcBlackDaoHibernateHQL) getBean("edcBlackDao");

			// 得到记录的总条数
			totalCount = dao.queryCount(edcTerminal, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(edcTerminal, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("返回结果集长度==" + list.size() + "起始位长度==" + startIndex + "页码数==" + (startIndex + pageSize) / pageSize + "总记录数==" + totalCount + "");

			log.info("EdcBlackLogic.queryAllItem()结束调用：分页查询。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcBlackLogic.ueryAllItem()分页查询，调用时出现异常");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	public void saveItem(EdcBlack edcTerminal, UserData ud) throws OAException {
		try {
			log.info("EdcBlackLogic.saveItem()开始调用：保存 、修改 信息。");

			EdcBlackDaoHibernateHQL dao = (EdcBlackDaoHibernateHQL) getBean("edcBlackDao");

			dao.saveItem(edcTerminal, ud);

			log.info("EdcBlackLogic.EdcBlackLogic.saveItem()结束调用：保存 、修改 信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcBlackLogic.saveItem()保存 、修改 调用时出现异常");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

}
