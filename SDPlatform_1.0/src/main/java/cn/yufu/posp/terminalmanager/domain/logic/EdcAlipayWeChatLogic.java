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
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcAlipayWeChatDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcNewfkterminalOrmDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.EdcAlipayWeChat;
import cn.yufu.posp.terminalmanager.domain.model.EdcNewfkterminalOrm;

public class EdcAlipayWeChatLogic extends BaseLogic implements
		EdcAlipayWeChatLogicInterface {

	private static final Log log = LogFactory.getLog("edcTerminal");

	/**
	 * @roseuid 44BAF7190128
	 */
	public EdcAlipayWeChatLogic() {

	}

	@SuppressWarnings("unchecked")
	public PageInfoModel queryAllItem(EdcAlipayWeChat edcAlipayWeChat,
			PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("EdcAlipayWeChatLogic.queryAllItem()开始调用：分页查询。");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			EdcAlipayWeChatDaoHibernateHQL dao = (EdcAlipayWeChatDaoHibernateHQL) getBean("EdcAlipayWeChatDao");

			// 得到记录的总条数
			totalCount = dao.queryCount(edcAlipayWeChat, ud);
			// 防止最后一页没有东西
			currentPage = pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(edcAlipayWeChat, startIndex, pageSize,
					page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("返回结果集长度==" + list.size() + "起始位长度==" + startIndex
					+ "页码数==" + (startIndex + pageSize) / pageSize + "总记录数=="
					+ totalCount);

			log.info("EdcAlipayWeChatLogic.queryAllItem()结束调用：分页查询。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcAlipayWeChatLogic.queryAllItem()分页查询，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	public void createItem(EdcAlipayWeChat edcAlipayWeChat, UserData ud)
			throws OAException {
		try {
			log.info("EdcAlipayWeChatLogic.createItem()开始调用：新建终端资料信息。");

			EdcAlipayWeChatDaoHibernateHQL dao = (EdcAlipayWeChatDaoHibernateHQL) getBean("EdcAlipayWeChatDao");
			dao.createItem(edcAlipayWeChat, ud);

			log.info("EdcAlipayWeChatLogic.createItem()结束调用：新建终端资料信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcAlipayWeChatLogic.createItem()新建终端资料信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcAlipayWeChatLogic.deleteItem()开始调用：删除终端资料信息。");

			// 删除终端资料设定
			EdcAlipayWeChatDaoHibernateHQL dao = (EdcAlipayWeChatDaoHibernateHQL) getBean("EdcAlipayWeChatDao");
			dao.deleteItem(keys, ud);

			log.info("EdcAlipayWeChatLogic.deleteItem()结束调用：删除终端资料信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcAlipayWeChatLogic.deleteItem()删除终端资料信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public HashMap findItemByKey(EdcAlipayWeChat key, UserData ud)
			throws OAException {
		EdcAlipayWeChat newModel = new EdcAlipayWeChat();
		HashMap map = new HashMap();
		try {
			log.info("EdcAlipayWeChatLogic.findItemByKey()开始调用：查找一条信息。");

			EdcAlipayWeChatDaoHibernateHQL dao = (EdcAlipayWeChatDaoHibernateHQL) getBean("EdcAlipayWeChatDao");
			newModel = dao.findItemByKey(key, ud);

			/*
			 * if (newModel != null) { EdcCommonDaoHibernateHQL commondao =
			 * (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			 * newModel.setMerchanName
			 * (commondao.findMerchantNameByKey(newModel.getMerchantId(), ud));
			 * }
			 */
			map.put("Infolist", newModel);

			log.info("EdcAlipayWeChatLogic.findItemByKey()结束调用：查找一条信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcAlipayWeChatLogic.findItemByKey()查找一条信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	}

	public void saveItem(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud)
			throws OAException {
		try {
			log.info("EdcAlipayWeChatLogic.saveItem()开始调用：保存 、修改 信息。");

			EdcNewfkterminalOrmDaoHibernateHQL dao = (EdcNewfkterminalOrmDaoHibernateHQL) getBean("EdcNewfkterminalOrmDao");

			dao.saveItem(edcNewfkterminalOrm, ud);

			log
					.info("EdcAlipayWeChatLogic.EdcNewfkterminalOrmLogic.saveItem()结束调用：保存 、修改 信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcAlipayWeChatLogic.saveItem()保存 、修改 调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	public void saveUploadItem(List<EdcAlipayWeChat> edcAlipayWeChatList,
			UserData ud) throws OAException {
		try {
			EdcAlipayWeChatDaoHibernateHQL dao = (EdcAlipayWeChatDaoHibernateHQL) getBean("EdcAlipayWeChatDao");
			for (int i = 0; i < edcAlipayWeChatList.size(); i++) {
				// 创建新的信息
				dao.createItem(edcAlipayWeChatList.get(i), ud);
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("EdcAlipayWeChatLogic.saveUploadItem()保存终端信息及同步终端主密钥表。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}

}
