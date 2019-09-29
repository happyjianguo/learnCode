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
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcNewfkterminalOrmDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.EdcNewfkterminalOrm;

public class EdcNewfkterminalOrmLogic extends BaseLogic implements EdcNewfkterminalOrmLogicInterface {

	private static final Log log = LogFactory.getLog("edcTerminal");

	/**
	 * @roseuid 44BAF7190128
	 */
	public EdcNewfkterminalOrmLogic() {

	}

	public void createItem(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcNewfkterminalOrmLogic.createItem()开始调用：新建终端资料信息。");

			EdcNewfkterminalOrmDaoHibernateHQL dao = (EdcNewfkterminalOrmDaoHibernateHQL) getBean("EdcNewfkterminalOrmDao");
			dao.createItem(edcNewfkterminalOrm, ud);

			log.info("EdcNewfkterminalOrmLogic.createItem()结束调用：新建终端资料信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcNewfkterminalOrmLogic.createItem()新建终端资料信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcNewfkterminalOrmLogic.deleteItem()开始调用：删除终端资料信息。");

			// 删除终端资料设定
			EdcNewfkterminalOrmDaoHibernateHQL dao = (EdcNewfkterminalOrmDaoHibernateHQL) getBean("EdcNewfkterminalOrmDao");
			dao.deleteItem(keys, ud);

			log.info("EdcNewfkterminalOrmLogic.deleteItem()结束调用：删除终端资料信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcNewfkterminalOrmLogic.deleteItem()删除终端资料信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public HashMap findItemByKey(EdcNewfkterminalOrm key, UserData ud) throws OAException {
		EdcNewfkterminalOrm newModel = new EdcNewfkterminalOrm();
		HashMap map = new HashMap();
		try {
			log.info("EdcNewfkterminalOrmLogic.findItemByKey()开始调用：查找一条信息。");

			EdcNewfkterminalOrmDaoHibernateHQL dao = (EdcNewfkterminalOrmDaoHibernateHQL) getBean("EdcNewfkterminalOrmDao");
			newModel = dao.findItemByKey(key, ud);

			if (newModel != null) {
				EdcCommonDaoHibernateHQL commondao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
				newModel.setMerchanName(commondao.findMerchantNameByKey(newModel.getMerchantId(), ud));
			}
			map.put("Infolist", newModel);

			log.info("EdcNewfkterminalOrmLogic.findItemByKey()结束调用：查找一条信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcNewfkterminalOrmLogic.findItemByKey()查找一条信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public PageInfoModel queryAllItem(EdcNewfkterminalOrm edcNewfkterminalOrm, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("EdcNewfkterminalOrmLogic.queryAllItem()开始调用：分页查询。");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			EdcNewfkterminalOrmDaoHibernateHQL dao = (EdcNewfkterminalOrmDaoHibernateHQL) getBean("EdcNewfkterminalOrmDao");

			// 得到记录的总条数
			totalCount = dao.queryCount(edcNewfkterminalOrm, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(edcNewfkterminalOrm, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("返回结果集长度==" + list.size() + "起始位长度==" + startIndex + "页码数==" + (startIndex + pageSize) / pageSize + "总记录数==" + totalCount);

			log.info("EdcNewfkterminalOrmLogic.queryAllItem()结束调用：分页查询。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcNewfkterminalOrmLogic.ueryAllItem()分页查询，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	public void saveItem(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcNewfkterminalOrmLogic.saveItem()开始调用：保存 、修改 信息。");

			EdcNewfkterminalOrmDaoHibernateHQL dao = (EdcNewfkterminalOrmDaoHibernateHQL) getBean("EdcNewfkterminalOrmDao");

			dao.saveItem(edcNewfkterminalOrm, ud);

			log.info("EdcNewfkterminalOrmLogic.EdcNewfkterminalOrmLogic.saveItem()结束调用：保存 、修改 信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcNewfkterminalOrmLogic.saveItem()保存 、修改 调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	public void saveUploadItem(List<EdcNewfkterminalOrm> edcNewfkterminalOrmList, UserData ud) throws OAException {
		try {
			EdcNewfkterminalOrmDaoHibernateHQL dao = (EdcNewfkterminalOrmDaoHibernateHQL) getBean("EdcNewfkterminalOrmDao");
			for (int i = 0; i < edcNewfkterminalOrmList.size(); i++) {
				// 创建新的信息
				dao.createItem(edcNewfkterminalOrmList.get(i), ud);
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("EdcNewfkterminalOrmLogic.saveUploadItem()保存终端信息及同步终端主密钥表。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}

}
