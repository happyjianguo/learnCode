/**
 *包名:cn.yufu.posp.terminalmanager.domain.logic
 *描述:package cn.yufu.posp.terminalmanager.domain.logic;
 */
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
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcZskterminalOrmDaoHibernateHQLImpl;
import cn.yufu.posp.terminalmanager.domain.model.EdcAlipayWeChat;
import cn.yufu.posp.terminalmanager.domain.model.EdcZskterminalOrm;

/**
 * EdcZskterminalOrmLogic.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:gll 
 * 时间:2018年3月2日
 * 描述:专属卡终端
 */
public class EdcZskterminalOrmLogic extends BaseLogic implements EdcZskterminalOrmLogicInterface {

	private static final Log log = LogFactory.getLog("edcTerminal");
	
	/**
	 * @roseuid 44BAF7190128
	 */
	public EdcZskterminalOrmLogic() {

	}
	
	@Override
	public PageInfoModel queryAllItem(EdcZskterminalOrm edcZskterminalOrm, PageInfoModel pageInfo, UserData ud)
			throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("EdcZskterminalOrmLogic.queryAllItem()开始调用：分页查询。");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			EdcZskterminalOrmDaoHibernateHQLImpl dao = (EdcZskterminalOrmDaoHibernateHQLImpl) getBean("EdcZskterminalOrmDao");

			// 得到记录的总条数
			totalCount = dao.queryCount(edcZskterminalOrm, ud);
			// 防止最后一页没有东西
			currentPage = pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(edcZskterminalOrm, startIndex, pageSize,
					page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("返回结果集长度==" + list.size() + "起始位长度==" + startIndex
					+ "页码数==" + (startIndex + pageSize) / pageSize + "总记录数=="
					+ totalCount);

			log.info("EdcZskterminalOrmLogic.queryAllItem()结束调用：分页查询。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcZskterminalOrmLogic.queryAllItem()分页查询，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	@Override
	public HashMap findItemByKey(EdcZskterminalOrm key, UserData ud) throws OAException {
		EdcZskterminalOrm newModel = new EdcZskterminalOrm();
		HashMap map = new HashMap();
		try {
			log.info("EdcZskterminalOrmLogic.findItemByKey()开始调用：查找一条信息。");

			EdcZskterminalOrmDaoHibernateHQLImpl dao = (EdcZskterminalOrmDaoHibernateHQLImpl) getBean("EdcZskterminalOrmDao");
			newModel = dao.findItemByKey(key, ud);

			/*
			 * if (newModel != null) { EdcCommonDaoHibernateHQL commondao =
			 * (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			 * newModel.setMerchanName
			 * (commondao.findMerchantNameByKey(newModel.getMerchantId(), ud));
			 * }
			 */
			map.put("Infolist", newModel);

			log.info("EdcZskterminalOrmLogic.findItemByKey()结束调用：查找一条信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcZskterminalOrmLogic.findItemByKey()查找一条信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	 }

	@Override
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcZskterminalOrmLogic.deleteItem()开始调用：删除终端资料信息。");

			// 删除终端资料设定
			EdcZskterminalOrmDaoHibernateHQLImpl dao = (EdcZskterminalOrmDaoHibernateHQLImpl) getBean("EdcZskterminalOrmDao");
			dao.deleteItem(keys, ud);

			log.info("EdcZskterminalOrmLogic.deleteItem()结束调用：删除终端资料信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcZskterminalOrmLogic.deleteItem()删除终端资料信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@Override
	public void createItem(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcZskterminalOrmLogic.createItem()开始调用：新建终端资料信息。");

			EdcZskterminalOrmDaoHibernateHQLImpl dao = (EdcZskterminalOrmDaoHibernateHQLImpl) getBean("EdcZskterminalOrmDao");
			dao.createItem(edcZskterminalOrm, ud);

			log.info("EdcZskterminalOrmLogic.createItem()结束调用：新建终端资料信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcZskterminalOrmLogic.createItem()新建终端资料信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@Override
	public void saveItem(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcZskterminalOrmLogic.saveItem()开始调用：保存 、修改 信息。");

			EdcZskterminalOrmDaoHibernateHQLImpl dao = (EdcZskterminalOrmDaoHibernateHQLImpl) getBean("EdcZskterminalOrmDao");

			dao.saveItem(edcZskterminalOrm, ud);

			log.info("EdcZskterminalOrmLogic.EdcNewfkterminalOrmLogic.saveItem()结束调用：保存 、修改 信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcZskterminalOrmLogic.saveItem()保存 、修改 调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	@Override
	public void saveUploadItem(List<EdcZskterminalOrm> edcZskterminalOrmList, UserData ud) throws OAException {
		try {
			EdcZskterminalOrmDaoHibernateHQLImpl dao = (EdcZskterminalOrmDaoHibernateHQLImpl) getBean("EdcZskterminalOrmDao");
			for (int i = 0; i < edcZskterminalOrmList.size(); i++) {
				// 创建新的信息
				dao.createItem(edcZskterminalOrmList.get(i), ud);
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("EdcZskterminalOrmLogic.saveUploadItem()保存终端信息及同步终端主密钥表。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}

}
