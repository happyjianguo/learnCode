package cn.yufu.posp.client.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.client.dao.hibernate.hql.OaClientComputerInfoDaoHibernateHQL;
import cn.yufu.posp.client.domain.model.OaClientComputerInfoModel;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public class OaClientComputerInfoLogic extends BaseLogic implements OaClientComputerInfoLogicInterface {

	private static final Log log = LogFactory.getLog("errctl");

	public OaClientComputerInfoLogic() {

	}

	/**
	 * 查找所有记录
	 */
	public PageInfoModel queryAll(OaClientComputerInfoModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("OaClientComputerInfoLogic.queryAll()开始调用：分页查询。" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			OaClientComputerInfoDaoHibernateHQL dao = (OaClientComputerInfoDaoHibernateHQL) getBean("OaClientComputerInfoDao");

			// 得到记录的总条数
			totalCount = dao.querySum(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("OaClientComputerInfoLogic.queryAll()结束调用：分页查询。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoLogic.queryAll()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	/**
	 * 删除一条记录
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("OaClientComputerInfoLogic.deleteItem(List newKeys, UserData ud)开始调用：删除一条记录。" + ud.getUserId());

			OaClientComputerInfoDaoHibernateHQL dao = (OaClientComputerInfoDaoHibernateHQL) getBean("OaClientComputerInfoDao");

			dao.deleteItem(newKeys, ud);

			log.info("OaClientComputerInfoLogic.deleteItem(List newKeys, UserData ud)结束调用：删除一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoLogic.deleteItem(List newKeys, UserData ud)删除一条记录,调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	/**
	 * 新建一条记录
	 */
	public void createItem(OaClientComputerInfoModel newModel, UserData ud) throws OAException {
		try {
			log.info("OaClientComputerInfoLogic.createItem(OaClientComputerInfoModel newModel, UserData ud)开始调用：新建一条记录。" + ud.getUserId());

			OaClientComputerInfoDaoHibernateHQL dao = (OaClientComputerInfoDaoHibernateHQL) getBean("OaClientComputerInfoDao");
			dao.createItem(newModel, ud);

			log.info("OaClientComputerInfoLogic.createItem(OaClientComputerInfoModel newModel, UserData ud)结束调用：新建一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoLogic.createItem(OaClientComputerInfoModel newModel, UserData ud)调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	/**
	 * 显示一条记录
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		OaClientComputerInfoModel newModel = new OaClientComputerInfoModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("OaClientComputerInfoLogic.findItem(long key, UserData ud)开始调用：显示一条记录。" + ud.getUserId());

			OaClientComputerInfoDaoHibernateHQL dao = (OaClientComputerInfoDaoHibernateHQL) getBean("OaClientComputerInfoDao");
			newModel = dao.findItem(newKey, ud);

			// 传model
			parameteMap.put("Infolist", newModel);

			log.info("OaClientComputerInfoLogic.findItem(long key, UserData ud)结束调用：显示一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoLogic.findItem(long key, UserData ud)显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return parameteMap;
	}

	/**
	 * 修改一条记录
	 */
	public void saveItem(OaClientComputerInfoModel newModel, UserData ud) throws OAException {
		try {
			log.info("OaClientComputerInfoLogic.saveItem(OaClientComputerInfoModel newModel, UserData ud)开始调用：修改一条记录。" + ud.getUserId());
			OaClientComputerInfoDaoHibernateHQL dao = (OaClientComputerInfoDaoHibernateHQL) getBean("OaClientComputerInfoDao");
			dao.saveItem(newModel, ud);
			log.info("OaClientComputerInfoLogic.saveItem(OaClientComputerInfoModel newModel, UserData ud)结束调用：修改一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoLogic.saveItem(OaClientComputerInfoModel newModel, UserData ud)调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

}
