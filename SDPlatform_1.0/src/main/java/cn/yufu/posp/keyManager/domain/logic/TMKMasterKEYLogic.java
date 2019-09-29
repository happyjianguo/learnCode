package cn.yufu.posp.keyManager.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.keyManager.dao.hibernate.hql.TMKMasterKEYDaoHibernateHQL;
import cn.yufu.posp.keyManager.domain.model.BtsKey;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;

public class TMKMasterKEYLogic extends BaseLogic implements TMKMasterKEYLogicInterface {

	private static final Log log = LogFactory.getLog("key");

	public PageInfoModel queryAllItem(BtsKey queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("TMKMasterKEYLogic.queryAllItem()开始调用：分页查询。");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			TMKMasterKEYDaoHibernateHQL dao = (TMKMasterKEYDaoHibernateHQL) getBean("TMKMasterKEYDao");

			// 得到记录的总条数
			totalCount = dao.queryCount(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("返回结果集长度==" + list.size() + "起始位长度==" + startIndex + "页码数==" + (startIndex + pageSize) / pageSize + "总记录数==" + totalCount + "");

			log.info("TMKMasterKEYLogic.queryAllItem()结束调用：分页查询。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TMKMasterKEYLogic.ueryAllItem()分页查询，调用时出现异常");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	public HashMap findItemByKey(BtsKey key, UserData ud) throws OAException {
		BtsKey newModel = new BtsKey();
		HashMap map = new HashMap();
		try {
			log.info("TMKMasterKEYLogic.findItemByKey()开始调用：查找一条信息。");

			TMKMasterKEYDaoHibernateHQL dao = (TMKMasterKEYDaoHibernateHQL) getBean("TMKMasterKEYDao");
			newModel = dao.findItemByKey(key, ud);

			if (newModel != null) {
				EdcCommonDaoHibernateHQL commondao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
				newModel.setMerchantName(commondao.findMerchantNameByKey(newModel.getMerchantId(), ud));
			}
			map.put("Infolist", newModel);

			log.info("TMKMasterKEYLogic.findItemByKey()结束调用：查找一条信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TMKMasterKEYLogic.findItemByKey()查找一条信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	}

	public void saveItem(BtsKey btsKey, UserData ud) throws OAException {
		try {
			log.info("TMKMasterKEYLogic.saveItem()开始调用：保存 、修改 信息。");

			TMKMasterKEYDaoHibernateHQL dao = (TMKMasterKEYDaoHibernateHQL) getBean("TMKMasterKEYDao");

			dao.saveItem(btsKey, ud);

			log.info("TMKMasterKEYLogic.saveItem()结束调用：保存 、修改 信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TMKMasterKEYLogic.saveItem()保存 、修改 调用时出现异常");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	public void createItem(BtsKey btsKey, UserData ud) throws OAException {
		try {
			log.info("TMKMasterKEYLogic.createItem()开始调用：新建一条记录。" + ud.getUserId());

			TMKMasterKEYDaoHibernateHQL dao = (TMKMasterKEYDaoHibernateHQL) getBean("TMKMasterKEYDao");
			dao.createItem(btsKey, ud);

			log.info("TMKMasterKEYLogic.createItem()结束调用：新建一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TMKMasterKEYLogic.createItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}
	//导出Excel和TxT文件的公共函数
	public List queryExport(BtsKey queryModel, UserData ud) throws OAException {
		TMKMasterKEYDaoHibernateHQL dao = (TMKMasterKEYDaoHibernateHQL) getBean("TMKMasterKEYDao");
		return dao.queryExport(queryModel, ud);
	}
	
}
