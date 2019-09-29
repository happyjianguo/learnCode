package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.dao.hibernate.hql.MccParamDaoHibernateHQL;
import cn.yufu.posp.merchant.domain.model.MccParamModel;

public class MccParamLogic extends BaseLogic implements MccParamLogicInterface {

	private static final Log log = LogFactory.getLog("merchant");

	public MccParamLogic() {

	}

	/**
	 * 查询所有记录
	 */
	public PageInfoModel queryAll(MccParamModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("MccParamLogic.queryAll()开始调用：分页查询。" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			MccParamDaoHibernateHQL dao = (MccParamDaoHibernateHQL) getBean("mccDao");

			// 得到记录的总条数
			totalCount = dao.querySum(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("MccParamLogic.queryAll()结束调用：分页查询。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamLogic.queryAll()调用时出现异常。");
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
			log.info("MccParamLogic.deleteItem(List newKeys, UserData ud)开始调用：删除一条记录。" + ud.getUserId());

			MccParamDaoHibernateHQL dao = (MccParamDaoHibernateHQL) getBean("mccDao");

			dao.deleteItem(newKeys, ud);

			log.info("MccParamLogic.deleteItem(List newKeys, UserData ud)结束调用：删除一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamLogic.deleteItem(List newKeys, UserData ud)删除一条记录,调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * 显示一条记录
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		MccParamModel newModel = new MccParamModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("MccParamLogic.findItem(long key, UserData ud)开始调用：显示一条记录。" + ud.getUserId());

			MccParamDaoHibernateHQL dao = (MccParamDaoHibernateHQL) getBean("mccDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				//2015.11.11 zhoulong update 
				//throw new OAException("您要查找的信息不存在！");
				newModel = new MccParamModel("", "", "");  //商户类型查询不到，则商户名称显示空字符串；
			}

			// 传model
			parameteMap.put("Infolist", newModel);

			log.info("MccParamLogic.findItem(long key, UserData ud)结束调用：显示一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamLogic.findItem(long key, UserData ud)显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * 修改一条记录
	 */
	public void saveItem(MccParamModel newModel, UserData ud) throws OAException {
		try {
			log.info("MccParamLogic.saveItem(MccParamModel newModel, UserData ud)开始调用：修改一条记录。" + ud.getUserId());
			MccParamDaoHibernateHQL dao = (MccParamDaoHibernateHQL) getBean("mccDao");
			dao.saveItem(newModel, ud);
			log.info("MccParamLogic.saveItem(MccParamModel newModel, UserData ud)结束调用：修改一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamLogic.saveItem(MccParamModel newModel, UserData ud)调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * 新建一条记录
	 */
	public void createItem(MccParamModel newModel, UserData ud) throws OAException {
		try {
			log.info("MccParamLogic.createItem(MccParamModel newModel, UserData ud)开始调用：新建一条记录。" + ud.getUserId());

			MccParamDaoHibernateHQL dao = (MccParamDaoHibernateHQL) getBean("mccDao");
			dao.createItem(newModel, ud);

			log.info("MccParamLogic.createItem(MccParamModel newModel, UserData ud)结束调用：新建一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamLogic.createItem(MccParamModel newModel, UserData ud)调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

}
