package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.dao.hibernate.hql.MerchantUserDaoHibernateHQL;
import cn.yufu.posp.merchant.domain.model.MerchantUser;
import cn.yufu.posp.merchant.domain.model.MerchantUserRela;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalId;

public class MerchantUserLogic extends BaseLogic implements MerchantUserLogicInterface {

	private static final Log log = LogFactory.getLog("merchant");

	public MerchantUserLogic() {

	}

	/**
	 * 查询所有记录
	 */
	public PageInfoModel queryAll(MerchantUser queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("MerchantUserLogic.queryAll()开始调用：分页查询。" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			MerchantUserDaoHibernateHQL dao = (MerchantUserDaoHibernateHQL) getBean("merchantUserDao");

			// 得到记录的总条数
			totalCount = dao.querySum(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("MerchantUserLogic.queryAll()结束调用：分页查询。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantUserLogic.queryAll()调用时出现异常。");
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

			log.info("MerchantUserLogic.deleteItem()开始调用：删除一条记录。" + ud.getUserId());

			MerchantUserDaoHibernateHQL dao = (MerchantUserDaoHibernateHQL) getBean("merchantUserDao");

			for (int i = 0; i < newKeys.size(); i++) {
				String tmp = newKeys.get(i) + "";
				// 判断该商户编号是否在终端资料设定信息表里，如果在，则不能删除
				EdcTerminal et = new EdcTerminal();
				EdcTerminalId etd = new EdcTerminalId();
				EdcCommonDaoHibernateHQL edao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
				if (tmp != null && !"".equals(tmp)) {
					etd.setMerchantId(tmp);
					et.setId(etd);
					List<EdcTerminal> list = edao.findEdcTerminalItemByKey(et, ud);
					if (list.size() > 0) {
						throw new OAException("您要删除的商户 " + tmp + " 存在在终端资料设定表中，不能删除，请执行相关操作后再删除！");
					}
				}

			}
			dao.deleteItem(newKeys, ud);
		
			log.info("MerchantUserLogic.deleteItem()结束调用：删除一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantUserLogic.deleteItem()删除一条记录,调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * 显示一条记录
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		MerchantUser newModel = new MerchantUser();

		HashMap parameteMap = new HashMap();
		try {
			log.info("MerchantUserLogic.findItem()开始调用：显示一条记录。" + ud.getUserId());

			MerchantUserDaoHibernateHQL dao = (MerchantUserDaoHibernateHQL) getBean("merchantUserDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("您要查找的信息不存在！");
			}

			// 传model
			parameteMap.put("Infolist", newModel);

			log.info("MerchantUserLogic.findItem()结束调用：显示一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantUserLogic.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * 修改一条记录
	 */
	public void saveItem(MerchantUser newModel, UserData ud) throws OAException {
		try {

			log.info("MerchantUserLogic.saveItem()开始调用：修改一条记录。" + ud.getUserId());
			MerchantUserDaoHibernateHQL dao = (MerchantUserDaoHibernateHQL) getBean("merchantUserDao");
			dao.saveItem(newModel, ud);
			log.info("MerchantUserLogic.saveItem()结束调用：修改一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantUserLogic.saveItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * 新建一条记录
	 */
	public void createItem(MerchantUser newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantUserLogic.createItem()开始调用：新建一条记录。" + ud.getUserId());
			MerchantUserDaoHibernateHQL dao = (MerchantUserDaoHibernateHQL) getBean("merchantUserDao");
			
			dao.createItem(newModel, ud);

			log.info("MerchantUserLogic.createItem()结束调用：新建一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantUserLogic.createItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}


}
