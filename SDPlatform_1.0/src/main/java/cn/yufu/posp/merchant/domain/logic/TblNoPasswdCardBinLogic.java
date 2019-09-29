package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.dao.hibernate.hql.MerchantCardDaoHibernateHQL;
import cn.yufu.posp.merchant.dao.hibernate.hql.TblNoPasswdCardBinDaoHibernateHQL;
import cn.yufu.posp.merchant.domain.model.TblNoPasswdCardBinModel;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogic;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.terminalmanager.domain.model.Cardtype;

public class TblNoPasswdCardBinLogic extends BaseLogic implements TblNoPasswdCardBinLogicInterface {

	private static final Log log = LogFactory.getLog("merchant");

	public TblNoPasswdCardBinLogic() {

	}

	/**
	 * 查询所有记录
	 */
	public PageInfoModel queryAll(TblNoPasswdCardBinModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("TblNoPasswdCardBinLogic.queryAll()开始调用：分页查询。" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			TblNoPasswdCardBinDaoHibernateHQL dao = (TblNoPasswdCardBinDaoHibernateHQL) getBean("tblNoPasswdCardBinDao");

			// 得到记录的总条数
			totalCount = dao.querySum(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("TblNoPasswdCardBinLogic.queryAll()结束调用：分页查询。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinLogic.queryAll()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return page;
	}



	/**
	 * 显示一条记录
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		TblNoPasswdCardBinModel newModel = new TblNoPasswdCardBinModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("TblNoPasswdCardBinLogic.findItem(long key, UserData ud)开始调用：显示一条记录。" + ud.getUserId());

			TblNoPasswdCardBinDaoHibernateHQL dao = (TblNoPasswdCardBinDaoHibernateHQL) getBean("tblNoPasswdCardBinDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("您要查找的信息不存在！");
			}
			
			parameteMap.put("Infolist", newModel);

			log.info("TblNoPasswdCardBinLogic.findItem(long key, UserData ud)结束调用：显示一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinLogic.findItem(long key, UserData ud)显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * 修改一条记录
	 */
	public void saveItem(TblNoPasswdCardBinModel newModel, UserData ud) throws OAException {
		try {
			log.info("TblNoPasswdCardBinLogic.saveItem(TblNoPasswdCardBinModel newModel, UserData ud)开始调用：修改一条记录。" + ud.getUserId());
			TblNoPasswdCardBinDaoHibernateHQL dao = (TblNoPasswdCardBinDaoHibernateHQL) getBean("tblNoPasswdCardBinDao");
			dao.saveItem(newModel, ud);
			log.info("TblNoPasswdCardBinLogic.saveItem(TblNoPasswdCardBinModel newModel, UserData ud)结束调用：修改一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinLogic.saveItem(TblNoPasswdCardBinModel newModel, UserData ud)调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * 新建一条记录
	 */
	public void createItem(TblNoPasswdCardBinModel newModel, UserData ud) throws OAException {
		try {
			log.info("TblNoPasswdCardBinLogic.createItem(TblNoPasswdCardBinModel newModel, UserData ud)开始调用：新建一条记录。" + ud.getUserId());

			TblNoPasswdCardBinDaoHibernateHQL dao = (TblNoPasswdCardBinDaoHibernateHQL) getBean("tblNoPasswdCardBinDao");
			dao.createItem(newModel, ud);

			log.info("TblNoPasswdCardBinLogic.createItem(TblNoPasswdCardBinModel newModel, UserData ud)结束调用：新建一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinLogic.createItem(TblNoPasswdCardBinModel newModel, UserData ud)调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}
	
	/**
	 * 删除一条记录
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("TblNoPasswdCardBinLogic.deleteItem(List newKeys, UserData ud)开始调用：删除一条记录。" + ud.getUserId());

			TblNoPasswdCardBinDaoHibernateHQL dao = (TblNoPasswdCardBinDaoHibernateHQL) getBean("tblNoPasswdCardBinDao");
			
			dao.deleteItem(newKeys, ud);

			log.info("TblNoPasswdCardBinLogic.deleteItem(List newKeys, UserData ud)结束调用：删除一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinLogic.deleteItem(List newKeys, UserData ud)删除一条记录,调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}


	/**
	 * // 验证卡BIN是否已经存在
	 */
	public String findfirstCardBinByKey(TblNoPasswdCardBinModel model, UserData ud) throws OAException {
		try {
			
			TblNoPasswdCardBinDaoHibernateHQL dao = (TblNoPasswdCardBinDaoHibernateHQL) getBean("tblNoPasswdCardBinDao");
			
			return dao.findfirstCardBinByKey(model, ud);

			
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinLogic.findfirstCardBinByKey(String newKeys, UserData ud)验证卡信息是否已经存在,调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}
	
	
}
