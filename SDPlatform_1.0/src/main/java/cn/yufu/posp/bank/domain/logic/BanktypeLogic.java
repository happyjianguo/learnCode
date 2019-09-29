package cn.yufu.posp.bank.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.bank.dao.hibernate.hql.BanktypeDaoHibernateHQL;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public class BanktypeLogic extends BaseLogic implements BanktypeLogicInterface {

	private static final Log log = LogFactory.getLog("bankInfo");

	public BanktypeLogic() {

	}

	/**
	 * 查询所有记录
	 */
	public PageInfoModel queryAll(Banktype queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("BanktypeLogic.queryAll()开始调用：分页查询。" + ud.getUserId());
			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;
			BanktypeDaoHibernateHQL dao = (BanktypeDaoHibernateHQL) getBean("banktypeDao");
			// 得到记录的总条数
			totalCount = dao.querySum(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);
			
			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			log.info("totalCount=" + totalCount + ";list.size()=" + list.size());
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("BanktypeLogic.queryAll()结束调用：分页查询。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("BanktypeLogic.queryAll()调用时出现异常。");
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
			log.info("BanktypeLogic.deleteItem()开始调用：删除一条记录。" + ud.getUserId());

			BanktypeDaoHibernateHQL dao = (BanktypeDaoHibernateHQL) getBean("banktypeDao");

			dao.deleteItem(newKeys, ud);

			log.info("BanktypeLogic.deleteItem()结束调用：删除一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeLogic.deleteItem()删除一条记录,调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * 显示一条记录
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		Banktype newModel = new Banktype();

		HashMap parameteMap = new HashMap();
		try {
			log.info("BanktypeLogic.findItem()开始调用：显示一条记录。" + ud.getUserId());

			BanktypeDaoHibernateHQL dao = (BanktypeDaoHibernateHQL) getBean("banktypeDao");
			newModel = dao.findItem(newKey, ud);
			// 传model
			parameteMap.put("Infolist", newModel);

			log.info("BanktypeLogic.findItem()结束调用：显示一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeLogic.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * 修改一条记录
	 */
	public void saveItem(Banktype newModel, UserData ud) throws OAException {
		try {
			log.info("BanktypeLogic.saveItem()开始调用：修改一条记录。" + ud.getUserId());
			BanktypeDaoHibernateHQL dao = (BanktypeDaoHibernateHQL) getBean("banktypeDao");
			dao.saveItem(newModel, ud);
			log.info("BanktypeLogic.saveItem()结束调用：修改一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeLogic.saveItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * 新建一条记录
	 */
	public void createItem(Banktype newModel, UserData ud) throws OAException {
		try {
			log.info("BanktypeLogic.createItem()开始调用：新建一条记录。" + ud.getUserId());

			BanktypeDaoHibernateHQL dao = (BanktypeDaoHibernateHQL) getBean("banktypeDao");
			dao.createItem(newModel, ud);

			log.info("BanktypeLogic.createItem()结束调用：新建一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeLogic.createItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

}
