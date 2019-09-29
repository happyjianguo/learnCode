package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.dao.hibernate.hql.TblMerchantTranParamDaoHibernateHQL;
import cn.yufu.posp.merchant.dao.hibernate.hql.TblNoPasswdCardBinDaoHibernateHQL;
import cn.yufu.posp.merchant.domain.model.TblMerchantTranParamModel;
import cn.yufu.posp.merchant.domain.model.TblNoPasswdCardBinModel;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogic;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.terminalmanager.domain.model.Cardtype;

public class TblMerchantTranParamLogic extends BaseLogic implements TblMerchantTranParamLogicInterface {

	private static final Log log = LogFactory.getLog("merchant");

	public TblMerchantTranParamLogic() {

	}

	/**
	 * 查询所有记录
	 */
	public PageInfoModel queryAll(TblMerchantTranParamModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("TblMerchantTranParamLogic.queryAll()开始调用：分页查询。" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			TblMerchantTranParamDaoHibernateHQL dao = (TblMerchantTranParamDaoHibernateHQL) getBean("tblMerchantTranParamDao");

			// 得到记录的总条数
			totalCount = dao.querySum(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("TblMerchantTranParamLogic.queryAll()结束调用：分页查询。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamLogic.queryAll()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return page;
	}



	/**
	 * 显示一条记录
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		TblMerchantTranParamModel newModel = new TblMerchantTranParamModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("TblMerchantTranParamLogic.findItem(long key, UserData ud)开始调用：显示一条记录。" + ud.getUserId());

			TblMerchantTranParamDaoHibernateHQL dao = (TblMerchantTranParamDaoHibernateHQL) getBean("tblMerchantTranParamDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("您要查找的信息不存在！");
			}
			
			// 封装商户名称
			newModel.setMerchantName(dao.findMerchantName(newModel.getMerchantId(), ud));
						
			parameteMap.put("Infolist", newModel);

			log.info("TblMerchantTranParamLogic.findItem(long key, UserData ud)结束调用：显示一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamLogic.findItem(long key, UserData ud)显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * 修改一条记录
	 */
	public void saveItem(TblMerchantTranParamModel newModel, UserData ud) throws OAException {
		try {
			log.info("TblMerchantTranParamLogic.saveItem(TblMerchantTranParamModel newModel, UserData ud)开始调用：修改一条记录。" + ud.getUserId());
			TblMerchantTranParamDaoHibernateHQL dao = (TblMerchantTranParamDaoHibernateHQL) getBean("tblMerchantTranParamDao");
			dao.saveItem(newModel, ud);
			log.info("TblMerchantTranParamLogic.saveItem(TblMerchantTranParamModel newModel, UserData ud)结束调用：修改一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamLogic.saveItem(TblMerchantTranParamModel newModel, UserData ud)调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * 新建一条记录
	 */
	public void createItem(TblMerchantTranParamModel newModel, UserData ud) throws OAException {
		try {
			log.info("TblMerchantTranParamLogic.createItem(TblMerchantTranParamModel newModel, UserData ud)开始调用：新建一条记录。" + ud.getUserId());

			TblMerchantTranParamDaoHibernateHQL dao = (TblMerchantTranParamDaoHibernateHQL) getBean("tblMerchantTranParamDao");
			dao.createItem(newModel, ud);

			log.info("TblMerchantTranParamLogic.createItem(TblMerchantTranParamModel newModel, UserData ud)结束调用：新建一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamLogic.createItem(TblMerchantTranParamModel newModel, UserData ud)调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}


	/**
	 * // 验证商户编号是否已经存在
	 */
	public String checkMerchantId(String merchantId, UserData ud) throws OAException {
		try {
			
			TblMerchantTranParamDaoHibernateHQL dao = (TblMerchantTranParamDaoHibernateHQL) getBean("tblMerchantTranParamDao");
			
			return dao.checkMerchantId(merchantId, ud);

			
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinLogic.findfirstCardBinByKey(String newKeys, UserData ud)验证卡信息是否已经存在,调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}
}
