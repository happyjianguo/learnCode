package cn.yufu.posp.jgmanager.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.jgmanager.dao.hibernate.hql.JgDaoHibernateHQL;
import cn.yufu.posp.jgmanager.domain.model.JgModel;

public class JgLogic extends BaseLogic implements JgLogicInterface {

	private static final Log log = LogFactory.getLog("jg");

	/**
	 * @roseuid 44BAF7190128
	 */
	public JgLogic() {

	}

	/**
	 * 查找机构信息
	 */
	public PageInfoModel queryJg(JgModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("JgLogic.queryJG()开始调用：分页查询。");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			JgDaoHibernateHQL jgDao = (JgDaoHibernateHQL) getBean("JgDao");

			// 得到记录的总条数
			totalCount = jgDao.queryJgCount(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = jgDao.queryJg(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			// System.out.println(list.size() +
			// "================rtertwetert=============" + totalCount);
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("JgLogic.queryJg()结束调用：分页查询。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgLogic.queryJg()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	/**
	 * 删除机构信息
	 */
	public void deleteJg(List newKeys, UserData ud) throws OAException {
		try {
			log.info("deleteJg(List newKeys, UserData ud)开始调用：删除机构信息。");

			JgDaoHibernateHQL jgDao = (JgDaoHibernateHQL) getBean("JgDao");

			jgDao.deleteJg(newKeys, ud);
			jgDao.deleteUser(newKeys, ud);
			log.info("deleteJg(List newKeys, UserData ud)结束调用：删除机构信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("deleteJgs(List newKeys, UserData ud)删除机构信息,调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	/**
	 * 新建机构信息
	 */
	public void createJg(JgModel newModel, UserData ud) throws OAException {
		try {
			log.info("createJg(JgModel newJg, UserData ud)开始调用：新建机构信息。");

			JgDaoHibernateHQL jgDao = (JgDaoHibernateHQL) getBean("JgDao");
			jgDao.createJg(newModel, ud);

			log.info("createJg(JgModel newJg, UserData ud)结束调用：新建机构信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("createJg(JgModel newJg, UserData ud)调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	/**
	 * 按key查机构信息
	 */
	public HashMap queryJgByKey(String newKey, UserData ud) throws OAException {
		JgModel newModel = new JgModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("JgLogic.queryJgByKey(long key, UserData ud)开始调用：查机构信息。");

			JgDaoHibernateHQL jgDao = (JgDaoHibernateHQL) getBean("JgDao");
			newModel = jgDao.queryJgByKey(newKey, ud);

			// 传model
			parameteMap.put("Infolist", newModel);
			log.info("JgLogic.queryJgByKey(long key, UserData ud)结束调用：查机构信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgLogic.queryJgByKey(long key, UserData ud)查机构信息，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return parameteMap;
	}

	/**
	 * 保存机构信息
	 */
	public void saveJg(JgModel newModel, UserData ud) throws OAException {
		try {
			log.info("saveJg(JgModel newJg, UserData ud)开始调用：保存机构信息。");
			JgDaoHibernateHQL jgDao = (JgDaoHibernateHQL) getBean("JgDao");
			jgDao.saveJg(newModel, ud);
			log.info("saveJg(JgModel newJg, UserData ud)结束调用：保存机构信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("saveJg(JgModel newJg, UserData ud)调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

}
