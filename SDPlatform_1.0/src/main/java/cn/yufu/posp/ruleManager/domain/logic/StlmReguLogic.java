package cn.yufu.posp.ruleManager.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.dao.hibernate.hql.MccParamDaoHibernateHQL;
import cn.yufu.posp.merchant.dao.hibernate.hql.MerchantCardDaoHibernateHQL;
import cn.yufu.posp.merchant.dao.hibernate.hql.MerchantDaoHibernateHQL;
import cn.yufu.posp.merchant.domain.model.MccParamModel;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.ruleManager.dao.hibernate.hql.StlmReguDaoHibernateHQL;
import cn.yufu.posp.ruleManager.domain.model.TblStlmRegu;

public class StlmReguLogic extends BaseLogic implements StlmReguLogicInterface {

	private static final Log log = LogFactory.getLog("rule");

	public StlmReguLogic() {

	}

	/**
	 * 查询所有记录
	 */
	public PageInfoModel queryAll(TblStlmRegu queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("StlmReguLogic.queryAll()开始调用：分页查询。" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			StlmReguDaoHibernateHQL dao = (StlmReguDaoHibernateHQL) getBean("stlmReguDao");

			// 得到记录的总条数
			totalCount = dao.querySum(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			MerchantDaoHibernateHQL mercDao = (MerchantDaoHibernateHQL) getBean("merchantDao");
			TblStlmRegu stlmRegu = new TblStlmRegu();
			for (int i = 0; i < list.size(); i++) {
				// 商品类型名称
				stlmRegu = (TblStlmRegu) list.get(i);
				if (!"ALL".equals(stlmRegu.getMcc().trim().toUpperCase())){
					stlmRegu.setMccName(mercDao.findMccName(stlmRegu.getMcc(), ud));
				}else{
					stlmRegu.setMccName("全部类型");
				}
			}

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("StlmReguLogic.queryAll()结束调用：分页查询。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguLogic.queryAll()调用时出现异常。");
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

			log.info("StlmReguLogic.deleteItem()开始调用：删除一条记录。" + ud.getUserId());

			StlmReguDaoHibernateHQL dao = (StlmReguDaoHibernateHQL) getBean("stlmReguDao");

			dao.deleteItem(newKeys, ud);

			log.info("StlmReguLogic.deleteItem()结束调用：删除一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguLogic.deleteItem()删除一条记录,调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * 显示一条记录
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		TblStlmRegu newModel = new TblStlmRegu();

		HashMap parameteMap = new HashMap();
		try {
			log.info("StlmReguLogic.findItem()开始调用：显示一条记录。" + ud.getUserId());

			StlmReguDaoHibernateHQL dao = (StlmReguDaoHibernateHQL) getBean("stlmReguDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("您要查找的信息不存在！");
			}

			// 传model
			parameteMap.put("Infolist", newModel);

			log.info("StlmReguLogic.findItem()结束调用：显示一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguLogic.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * 修改一条记录
	 */
	public void saveItem(TblStlmRegu newModel, UserData ud) throws OAException {
		try {
			// 验证商户类型是否存在
			if (newModel.getMcc() != null && !"".equals(newModel.getMcc()) && !"ALL".equals(newModel.getMcc().trim().toUpperCase())) {
				MccParamDaoHibernateHQL mdao = (MccParamDaoHibernateHQL) getBean("mccDao");
				MccParamModel mpm = mdao.findItem(newModel.getMcc().toUpperCase(), ud);
				if (mpm == null) {
					throw new OAException("您输入的商户类型在数据库中不存在，请重新输入！");
				}
			}
			if (!"ALL".equals(newModel.getMchtNo().trim().toUpperCase())) {
				MerchantCardDaoHibernateHQL mercDao = (MerchantCardDaoHibernateHQL) getBean("merCardDao");
				// 验证商户号是否已经存在在数据库中
				MerchantBaseModel msm = mercDao.findItemById(newModel.getMchtNo().toUpperCase(), ud);
				if (msm == null) {
					throw new OAException("商户编号:" + newModel.getMchtNo() + " 的记录在数据库中不存在！");
				}
			}
//			if (!"ALL".equals(newModel.getTermNo().trim().toUpperCase())) {
//				EdcCommonDaoHibernateHQL dao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
//				EdcTerminal edcTerminal = new EdcTerminal();
//				EdcTerminalId id = new EdcTerminalId();
//				id.setTerminalId(newModel.getTermNo().toUpperCase());
//				edcTerminal.setId(id);
//				List<EdcTerminal> list = dao.findEdcTerminalItemByKey(edcTerminal, ud);
//				if (list.size() < 1) {
//					throw new OAException("终端号:" + newModel.getTermNo() + " 的记录在数据库中不存在！");
//				}
//			}
			log.info("StlmReguLogic.saveItem()开始调用：修改一条记录。" + ud.getUserId());
			StlmReguDaoHibernateHQL dao = (StlmReguDaoHibernateHQL) getBean("stlmReguDao");
			dao.saveItem(newModel, ud);
			log.info("StlmReguLogic.saveItem()结束调用：修改一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguLogic.saveItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * 新建一条记录
	 */
	public void createItem(TblStlmRegu newModel, UserData ud) throws OAException {
		try {
			log.info("StlmReguLogic.createItem()开始调用：新建一条记录。" + ud.getUserId());
			// 验证商户类型是否存在
			if (newModel.getMcc() != null && !"".equals(newModel.getMcc()) && !"ALL".equals(newModel.getMcc().trim().toUpperCase())) {
				MccParamDaoHibernateHQL mdao = (MccParamDaoHibernateHQL) getBean("mccDao");
				MccParamModel mpm = mdao.findItem(newModel.getMcc().toUpperCase(), ud);
				if (mpm == null) {
					throw new OAException("您输入的商户类型在数据库中不存在，请重新输入！");
				}
			}
			if (!"ALL".equals(newModel.getMchtNo().trim().toUpperCase())) {
				MerchantCardDaoHibernateHQL mercDao = (MerchantCardDaoHibernateHQL) getBean("merCardDao");
				// 验证商户号是否已经存在在数据库中
				MerchantBaseModel msm = mercDao.findItemById(newModel.getMchtNo().toUpperCase(), ud);
				if (msm == null) {
					throw new OAException("商户编号:" + newModel.getMchtNo() + " 的记录在数据库中不存在！");
				}
			}
			// if (!"ALL".equals(newModel.getTermNo().trim().toUpperCase())) {
			// EdcCommonDaoHibernateHQL dao = (EdcCommonDaoHibernateHQL)
			// getBean("EdcCommonDao");
			// EdcTerminal edcTerminal = new EdcTerminal();
			// EdcTerminalId id = new EdcTerminalId();
			// id.setTerminalId(newModel.getTermNo().toUpperCase());
			// edcTerminal.setId(id);
			// List<EdcTerminal> list =
			// dao.findEdcTerminalItemByKey(edcTerminal, ud);
			// if (list.size() < 1) {
			// throw new OAException("终端号:" + newModel.getTermNo() +
			// " 的记录在数据库中不存在！");
			// }
			// }
			StlmReguDaoHibernateHQL dao = (StlmReguDaoHibernateHQL) getBean("stlmReguDao");
			dao.createItem(newModel, ud);

			log.info("StlmReguLogic.createItem()结束调用：新建一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguLogic.createItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}

}
