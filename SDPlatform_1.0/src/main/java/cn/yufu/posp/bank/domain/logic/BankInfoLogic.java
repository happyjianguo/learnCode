package cn.yufu.posp.bank.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.bank.dao.hibernate.hql.BankInfoDaoHibernateHQL;
import cn.yufu.posp.bank.domain.model.BankInfoId;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public class BankInfoLogic extends BaseLogic implements BankInfoLogicInterface {

	private static final Log log = LogFactory.getLog("bankInfo");

	public BankInfoLogic() {

	}

	/**
	 * 查询所有记录
	 */
	public PageInfoModel queryAll(BankInfoId queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("BankInfoLogic.queryAll()开始调用：分页查询。" + ud.getUserId());
			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;
			BankInfoDaoHibernateHQL dao = (BankInfoDaoHibernateHQL) getBean("bankinfoDao");
			// 得到记录的总条数
			totalCount = dao.querySum(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);
			
			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			log.info("totalCount=" + totalCount + ";list.size()=" + list.size());
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					BankInfoId bankInfo = (BankInfoId) list.get(i);
					bankInfo.setBankTypeName(dao.findTypeNameById(bankInfo.getBankType().trim(), ud));
				}
			}
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("BankInfoLogic.queryAll()结束调用：分页查询。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("BankInfoLogic.queryAll()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return page;
	}

	/**
	 * 删除一条记录
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("BankInfoLogic.deleteItem()开始调用：删除一条记录。" + ud.getUserId());

			BankInfoDaoHibernateHQL dao = (BankInfoDaoHibernateHQL) getBean("bankinfoDao");

			dao.deleteItem(newKeys, ud);

			log.info("BankInfoLogic.deleteItem()结束调用：删除一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoLogic.deleteItem()删除一条记录,调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * 显示一条记录
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		BankInfoId newModel = new BankInfoId();

		HashMap parameteMap = new HashMap();
		try {
			log.info("BankInfoLogic.findItem()开始调用：显示一条记录。" + ud.getUserId());

			BankInfoDaoHibernateHQL dao = (BankInfoDaoHibernateHQL) getBean("bankinfoDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("您要查找的信息不存在！");
			} else {
				newModel.setBankTypeName(dao.findTypeNameById(newModel.getBankType(), ud));
			}

			// 传model
			parameteMap.put("Infolist", newModel);

			log.info("BankInfoLogic.findItem()结束调用：显示一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoLogic.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * 修改一条记录
	 */
	public void saveItem(BankInfoId newModel, UserData ud) throws OAException {
		try {
			log.info("BankInfoLogic.saveItem()开始调用：修改一条记录。" + ud.getUserId());
			BankInfoDaoHibernateHQL dao = (BankInfoDaoHibernateHQL) getBean("bankinfoDao");
			dao.saveItem(newModel, ud);
			log.info("BankInfoLogic.saveItem()结束调用：修改一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoLogic.saveItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * 新建一条记录
	 */
	public void createItem(BankInfoId newModel, UserData ud) throws OAException {
		try {
			log.info("BankInfoLogic.createItem()开始调用：新建一条记录。" + ud.getUserId());

			BankInfoDaoHibernateHQL dao = (BankInfoDaoHibernateHQL) getBean("bankinfoDao");
			dao.createItem(newModel, ud);

			log.info("BankInfoLogic.createItem()结束调用：新建一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoLogic.createItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

}
